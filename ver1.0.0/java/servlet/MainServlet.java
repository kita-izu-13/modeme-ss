package servlet;

import java.io.IOException;

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

		//Logicへ処理
		Logic logic=new Logic();
		Mode mode=logic.trining();
		
		//セッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("mode",mode);
		
		//mainへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//答えのボタンが押された場合
		request.setCharacterEncoding("UTF-8");
		String select=request.getParameter("select");
		
		HttpSession session = request.getSession();
		//セッションスコープより取得
		Mode mode=(Mode)session.getAttribute("mode");
		
		//Logicへ処理
		Logic logic=new Logic();
		Mode nextmode=logic.trining();
		String check=logic.check(select, mode);
		
		//セッショントスコープに保存
		session.setAttribute("nextmode",nextmode);
		session.setAttribute("check",check);
		
		//mainへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);
		
		//セッションスコープnextmode→modeへ保存
		session.setAttribute("mode",nextmode);
		
	}

}
