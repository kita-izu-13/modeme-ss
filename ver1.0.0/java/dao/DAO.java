package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Mode;

public class DAO {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/modeme";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
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
				//mode=new Mode(1,"自分を評価する時は？","過去の自分","これだけできてるよ！自信を持っていいよ！");
		
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mode;
	}
}
