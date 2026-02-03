package model;

import dao.DAO;

public class Logic {
	public Mode trining() {
	//ランダム (4つ)
	int ran =(int)(Math.random()*4)+1;
	
	//DAOのselectメソッドへランダム数値を渡し、
	//それをもとにSELECT文実行、Modeで返される
	DAO dao=new DAO();
	Mode mode=dao.select(ran);
	return mode;
	}
	
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
}
