package model;

import java.util.ArrayList;
import java.util.List;

import dao.DAO;

public class Logic {
	
	//問題出題
	public Mode trining() {
		//DAOのselectメソッドへランダム数値を渡し、
		//それをもとにSELECT文実行、Modeで返される
		DAO dao=new DAO();
	
		//ランダム (maxid)
		int ran =(int)(Math.random()*dao.id())+1;
		Mode mode=dao.select(ran);
		return mode;
	}
	
	//正解・不正解の判定
	public String check(String select,Mode mode) {
		String check="";
		//選択されたボタンとANSERが合っている場合
		if(select.equals(mode.getAnswer())) {
			check="正解！";
		}else {
			check="不正解！";
		}
		return check;
	}
	
	//一覧表示
	public List<Mode> list(){
		//DAOへSELECT文を実行してテーブル内容を表示
		DAO dao=new DAO();
		List<Mode> list=dao.list();
		return list;
	}
	
	//テーブルへ追加
	public boolean insert(String question,String answer,String comment) {
		DAO dao=new DAO();
		int id=dao.id()+1;
		Mode mode=new Mode(id,question,answer,comment,10);
		boolean result=dao.insert(mode);
		return true;
	}
	
	//ランク判定（集中モード：１分）
	public List<String> rank(int count) {
		List<String> rank=new ArrayList<>();
		if(count>=50) {
			rank.add("Sランク");
			rank.add("すごいね！このまま維持していこう！");
		}else if(count>=40) {
			rank.add("Aランク");
			rank.add("ここまできたね！次はSランク目指そう！");
		}else {
			rank.add("Bランク");
			rank.add("正解率とスピード上げていこう！できるよ！");
		}
		return rank;
	}
	
	
	//苦手問題集中トレーニング　missの問題をランダムで選択
	public Mode miss(){
		//その中からidをランダムに選ぶ
		//DAOのselectメソッドへランダム数値を渡し、
		//それをもとにSELECT文実行、Idリストで返される
		DAO dao=new DAO();
		List<Integer> missId= dao.miss();
		
		//テーブル項目「missが１以上」のものをテーブル検索
        // 配列のインデックス（配列の長さ内の数）をランダムに選択
        int index = (int)(Math.random() * missId.size());

        //インデックス（ランダム）により番号を抽出
        int random = missId.get(index);
		
		//ランダム 
		Mode mode=dao.select(random);
		return mode;
	}
	
	//問題苦手集中トレーニング　結果によりmiss増減（テーブル）
	public boolean missupdate(Mode mode,int miss) {
		DAO dao=new DAO();
		boolean bl=dao.missupdate(mode,miss);
		return true;
	}
	
	//ランク判定（時短モード：20秒）
	public List<String> rankshort(int count) {
		List<String> rank=new ArrayList<>();
		if(count>=15) {
			rank.add("Sランク");
			rank.add("すごいね！このまま維持していこう！");
		}else if(count>=12) {
			rank.add("Aランク");
			rank.add("ここまできたね！次はSランク目指そう！");
		}else {
			rank.add("Bランク");
			rank.add("正解率とスピード上げていこう！できるよ！");
		}
		return rank;
	}
	
	
	//ランク判定（瞬発力トレ：2秒）
	public List<String> rankEx(int count) {
		List<String> rank=new ArrayList<>();
		if(count>=5) {
			rank.add("Sランク");
			rank.add("すごいね！このまま維持していこう！");
		}else if(count>=3) {
			rank.add("Aランク");
			rank.add("ここまできたね！次はSランク目指そう！");
		}else {
			rank.add("Bランク");
			rank.add("正解率とスピード上げていこう！できるよ！");
		}
		return rank;
	}
	
	//テーブル変更
	public Mode update(int id) {
		DAO dao=new DAO();
		Mode mode=dao.update(id);
		return mode;
	}
	
	//テーブル内容を変更
	public boolean updateform(Mode mode) {
		DAO dao=new DAO();
		boolean result=dao.updateform(mode);
		return true;
	}
}
