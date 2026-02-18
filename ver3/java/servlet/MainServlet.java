package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Logic;
import model.Mode;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//セレクトボタンが押された場合（スタートor瞬発力トレーニングor一覧or追加or変更）
		request.setCharacterEncoding("UTF-8");
		String select=request.getParameter("select");
		//Logicへ処理
		Logic logic=new Logic();
		List<Mode> list=logic.list();
		
		//セッションスコープ準備
		HttpSession session = request.getSession();	
		
		//セッションスコープに保存
		session.setAttribute("list",list);
		session.setAttribute("select",select);
		
		RequestDispatcher dispatcher;
		
		Mode mode=new Mode();
		
		//count（正解回数）を準備
		int count;
		
		if(select==null) {
			count=(int)session.getAttribute("count");
			String radio=(String)session.getAttribute("radio");
			
			List<String> rank=new ArrayList<>();

			if(radio==null) {
				//瞬発力トレーニングが選択された時、rank判定
				rank=logic.rankEx(count);
			}else if(radio.equals("集中")){
				//集中(１分)が選択された時、rank判定
				rank=logic.rank(count);
			}else {
				//時短(20秒)が選択された時、rankshort判定
				rank=logic.rankshort(count);
			}
			
			//セッショントスコープに保存
			session.setAttribute("rank",rank);
			session.setAttribute("count",count);
			
			//rankへフォワード
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/rank.jsp");
			dispatcher.forward(request, response);
			return;
			
		}
		
		switch(select) {
		case "スタート":
			//スタートボタンが押された場合
			String radio=request.getParameter("radio");	//集中(1分)or時短(20秒)
			count=0;
			
			Calendar aftercl=Calendar.getInstance();

			if(radio.equals("集中")){
				//集中(１分)が選択された時
				//Calenderで60秒後設定
				aftercl.add(Calendar.SECOND, 60);
			}else {
				//時短(20秒)が選択された時
				//Calenderで20秒後設定
				aftercl.add(Calendar.SECOND, 20);
			}
			
			//Date型に変換
			Date afterdate=aftercl.getTime();
		
			//Logicへ処理
			mode=logic.trining();
			
			//セッションスコープに保存
			session.setAttribute("mode",mode);
			session.setAttribute("afterdate", afterdate);
			session.setAttribute("radio", radio);
			session.setAttribute("count",count);

			//mainへフォワード
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
			break;
		case "苦手問題集中トレーニング":
			//苦手問題集中トレーニングボタンが押されたとき
			count=0;
		
			//Logicへ処理
			mode=logic.trining();
			
			//セッションスコープに保存
			session.setAttribute("mode",mode);
			session.setAttribute("count",count);
			
			//missへフォワード
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/miss.jsp");
			dispatcher.forward(request, response);
			break;
		case "瞬発力トレーニング":
			//瞬発力トレーニングボタンが押されたとき
			count=0;
			
			Calendar explosivecl=Calendar.getInstance();
			//Calenderで2秒後設定
			explosivecl.add(Calendar.SECOND, 2);
			
			//Date型に変換
			Date explosivedate=explosivecl.getTime();
		
			//Logicへ処理
			mode=logic.trining();
			
			//セッションスコープに保存
			session.setAttribute("mode",mode);
			session.setAttribute("explosivedate", explosivedate);
			session.setAttribute("count",count);
			
			//explosiveへフォワード
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/explosive.jsp");
			dispatcher.forward(request, response);
			break;
		case "一覧":
			//一覧ボタンが押されたとき
			
			//listへフォワード
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/list.jsp");
			dispatcher.forward(request, response);
			break;
			
		case "追加":
			//追加ボタンが押されたとき
			
			//formへフォワード
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/form.jsp");
			dispatcher.forward(request, response);
			break;
		case "変更":
			//変更ボタンが押されたとき
			
			//updateへフォワード
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/update.jsp");
			dispatcher.forward(request, response);
			break;
		default :
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String reply=request.getParameter("reply");	//回答ボタン
		String question=request.getParameter("question");
		String answer=request.getParameter("answer");
		String comment=request.getParameter("comment");
		String submit=request.getParameter("submit");	//送信（追加か変更）

		//セッションスコープより取得
		HttpSession session = request.getSession();
		Mode mode=(Mode)session.getAttribute("mode");
		int count=(int)session.getAttribute("count");
		String select=(String)session.getAttribute("select");
		
		//Logicへ処理
		Logic logic=new Logic();
		
		Mode nextmode;
		String check;
		
		boolean bo;
		RequestDispatcher dispatcher;
		
		//答えのボタンが押された場合
		switch(select) {
		case "スタート":	
			
			//Logicへ処理
			logic=new Logic();
			nextmode=logic.trining();
			check=logic.check(reply, mode);
			
			//checkが”正解！”の時、count＋1
			if(check.equals("正解！")) {
				count+=1;
			}
			
			//セッショントスコープに保存
			session.setAttribute("nextmode",nextmode);
			session.setAttribute("check",check);
			session.setAttribute("count",count);
			
			//resultへフォワード
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/result.jsp");
			dispatcher.forward(request, response);
			
			//セッションスコープnextmode→modeへ保存
			session.setAttribute("mode",nextmode);
			break;
		case "苦手問題集中トレーニング":	
			
			//Logicへ処理
			logic=new Logic();
			nextmode=logic.trining();
			check=logic.check(reply, mode);
			boolean bl=false;
			int miss=0;
			
			//checkが”正解！”の時、count＋1
			if(check.equals("正解！")) {
				count+=1;
				//Logicへmiss－1の処理
				miss =mode.getMiss()-1;
			}else if(check.equals("不正解！")) {
				//Logicへmiss＋１の処理
				miss =mode.getMiss()+1;
			}
			bl=logic.missupdate(mode,miss);
			
			//セッショントスコープに保存
			session.setAttribute("nextmode",nextmode);
			session.setAttribute("check",check);
			session.setAttribute("count",count);
			
			if(bl) {
			//missResultへフォワード
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/missResult.jsp");
			dispatcher.forward(request, response);
			
			//セッションスコープnextmode→modeへ保存
			session.setAttribute("mode",nextmode);
			}
			break;
		case "瞬発力トレーニング":	
			
			//Logicへ処理
			logic=new Logic();
			nextmode=logic.trining();
			check=logic.check(reply, mode);
			
			//checkが”正解！”の時、count＋1
			if(check.equals("正解！")) {
				Calendar explosivecl=Calendar.getInstance();
				//Calenderで2秒後設定
				explosivecl.add(Calendar.SECOND, 2);
				//Date型に変換
				Date explosivedate=explosivecl.getTime();
				session.setAttribute("explosivedate",explosivedate);
				count+=1;
			}
			
			//セッショントスコープに保存
			session.setAttribute("nextmode",nextmode);
			session.setAttribute("check",check);
			session.setAttribute("count",count);
			
			//explosiveResultへフォワード
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/explosiveResult.jsp");
			dispatcher.forward(request, response);
			
			//セッションスコープnextmode→modeへ保存
			session.setAttribute("mode",nextmode);
			break;
		default :
			break;
		}
		
		switch(submit) {
		case "追加":		
			//追加フォームが入力された場合	
			
			//Logicへ処理
			logic=new Logic();
			bo=logic.insert(question,answer,comment);
			
			if(bo==true) {
				//formresultへフォワード
				dispatcher = request.getRequestDispatcher("WEB-INF/jsp/formresult.jsp");
				dispatcher.forward(request, response);
			
			}else {
				dispatcher = request.getRequestDispatcher("WEB-INF/jsp/form.jsp");
				dispatcher.forward(request, response);
			}
			break;
		
		case "変更":			
			//変更フォームが入力された場合	
			
			//Logicへ処理
			logic=new Logic();
			//セッションスコープより取得
			int id=(int)session.getAttribute("id");
			mode=new Mode(id,question,answer,comment);
			bo=logic.updateform(mode);
			
			if(bo==true) {
				//formresultへフォワード
				dispatcher = request.getRequestDispatcher("WEB-INF/jsp/formresult.jsp");
				dispatcher.forward(request, response);
			
			}else {
				dispatcher = request.getRequestDispatcher("WEB-INF/jsp/form.jsp");
				dispatcher.forward(request, response);
			}
			break;
			
		case "選択":			
			//変更行が選択された場合(選択)
			
			String update=request.getParameter("update");	//変更するid
			int updateid=Integer.parseInt(update);
			
			//Logicへ処理
			logic=new Logic();
			Mode updateMode=logic.update(updateid);
			
			//セッショントスコープに保存
			session.setAttribute("updateMode",updateMode);
			session.setAttribute("id",updateid);
			
			if(updateMode!=null) {
				//updateFormへフォワード
				dispatcher = request.getRequestDispatcher("WEB-INF/jsp/updateForm.jsp");
				dispatcher.forward(request, response);
			
			}else {
				dispatcher = request.getRequestDispatcher("WEB-INF/jsp/update.jsp");
				dispatcher.forward(request, response);
			}
			break;
			
		default :
			break;

		}
	}
}
