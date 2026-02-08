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


		//セレクトボタンが押された場合（スタートor一覧）
		request.setCharacterEncoding("UTF-8");
		String select=request.getParameter("select");
		
		//セッションスコープ準備
		HttpSession session = request.getSession();	
		
		if(select==null) {
			int count=(int)session.getAttribute("count");
			
			List<String> rank=new ArrayList<>();
			Logic logic=new Logic();
			rank=logic.rank(count);
			
			//セッショントスコープに保存
			session.setAttribute("rank",rank);
			session.setAttribute("count",count);
			
			//rankへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/rank.jsp");
			dispatcher.forward(request, response);
			
		}
		
		//スタートボタンが押された場合
		else if(select.equals("スタート")) {
			
			//Calenderで60秒後設定
			Calendar aftercl=Calendar.getInstance();
			aftercl.add(Calendar.SECOND, 60);
			//Date型に変換
			Date afterdate=aftercl.getTime();
		
			//Logicへ処理
			Logic logic=new Logic();
			Mode mode=logic.trining();
			
			//count（正解回数）を準備
			int count=0;
			session.setAttribute("count",count);
			
			//セッションスコープに保存
			session.setAttribute("mode",mode);
			session.setAttribute("afterdate", afterdate);

			
			//mainへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}else if(select.equals("一覧")) {
			//一覧ボタンが押されたとき
			
			//Logicへ処理
			Logic logic=new Logic();
			List<Mode> list=logic.list();
			
			//セッションスコープに保存
			session.setAttribute("list",list);
			
			//mainへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/list.jsp");
			dispatcher.forward(request, response);
			
		}else if(select.equals("追加")) {
			//追加ボタンが押されたとき
			
			//mainへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/form.jsp");
			dispatcher.forward(request, response);
		
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String select=request.getParameter("select");
		String question=request.getParameter("question");
		String answer=request.getParameter("answer");
		String comment=request.getParameter("comment");
		
		HttpSession session = request.getSession();
		
		//答えのボタンが押された場合
		if(select!=null) {

			//セッションスコープより取得
			Mode mode=(Mode)session.getAttribute("mode");
			int count=(int)session.getAttribute("count");
			
			//Logicへ処理
			Logic logic=new Logic();
			Mode nextmode=logic.trining();
			String check=logic.check(select, mode);
			
			//checkが”正解！”の時、count＋1
			if(check.equals("正解！")) {
				count+=1;
			}
			
			//セッショントスコープに保存
			session.setAttribute("nextmode",nextmode);
			session.setAttribute("check",check);
			session.setAttribute("count",count);
			
			//mainへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/result.jsp");
			dispatcher.forward(request, response);
			
			//セッションスコープnextmode→modeへ保存
			session.setAttribute("mode",nextmode);
			
		//}
		
		}else {
	
			//追加フォームが入力された場合	
			
			//Logicへ処理
			Logic logic=new Logic();
			boolean bo=logic.insert(question,answer,comment);
			
			if(bo==true) {
				//mainへフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/formresult.jsp");
				dispatcher.forward(request, response);
			
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/form.jsp");
				dispatcher.forward(request, response);
			}
		}
		
	}

}
