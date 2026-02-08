package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mode;

public class DAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/modeme";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	private int maxid=0;
	
	//最大id値を取得
	public int id() {

		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){

				//SQL文作成・実行
				String sql="SELECT * FROM MODE;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				//SELECT文を実行し、結果表を取得
				ResultSet rs = pStmt.executeQuery();
				
				//SELECT文の結果をModeに格納
				while(rs.next()) {
					maxid = rs.getInt("ID");
				}
						
		}catch(SQLException e) {
			e.printStackTrace();
			return maxid=0;
		}
		return maxid;
	}
	
	//ランダム値でテーブルより検索
	public Mode select(int ran) {
		Mode mode;
		int id=0;
		String question="";
		String answer="";
		String comment="";
		
		
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){

				//ランダム値をもとに、SQL文作成・実行
				String sql="SELECT * FROM MODE WHERE ID="+ran+";";
		
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				//SELECT文を実行し、結果表を取得
				ResultSet rs = pStmt.executeQuery();
			
				
				//SELECT文の結果をModeに格納
				while(rs.next()) {
					id = rs.getInt("ID");
					question=rs.getString("QUESTION");
					answer=rs.getString("ANSWER");
					comment=rs.getString("COMMENT");
				}
				
				mode=new Mode(id,question,answer,comment);
		
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mode;
	}
	
	//テーブル検索・一覧
	public List<Mode> list() {
		List<Mode> list=new ArrayList<>();
		
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){

				//SQL文作成・実行
				String sql="SELECT * FROM MODE;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				//SELECT文を実行し、結果表を取得
				ResultSet rs = pStmt.executeQuery();
				
				//SELECT文の結果をModeに格納
				while(rs.next()) {
					int id = rs.getInt("ID");
					String question=rs.getString("QUESTION");
					String answer=rs.getString("ANSWER");
					String comment=rs.getString("COMMENT");
					Mode mode=new Mode(id,question,answer,comment);
					list.add(mode);
				}
				
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	//テーブル追加
	public boolean insert(Mode mode) {

		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
				
				//SQL文作成・実行
				String sql="INSERT INTO MODE (ID, QUESTION,ANSWER,COMMENT) VALUES("+mode.getId()+",?,?,?);";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				pStmt.setString(1, mode.getQuestion());
				pStmt.setString(2, mode.getAnswer());
				pStmt.setString(3, mode.getComment());
				
				//アカウントテーブルへINSERT
				int result =pStmt.executeUpdate();				
				
				pStmt.close();
				conn.close();
						
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
