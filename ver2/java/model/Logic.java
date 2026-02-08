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
		Mode mode=new Mode(id,question,answer,comment);
		boolean result=dao.insert(mode);
		return true;
	}
	
	//ランク判定
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
}
