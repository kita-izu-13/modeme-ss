<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Mode,java.util.List" %>
<%
	//セッションスコープから取得
	Mode updateMode=(Mode)session.getAttribute("updateMode");
	int id=updateMode.getId();
	String question=updateMode.getQuestion();
	String answer=updateMode.getAnswer();
	String comment=updateMode.getComment();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mode-Me</title>
<style>
	textarea{
		width:170px;
		height:50px;
	}
</style>
</head>
<body>
	<h1>Mode-Me</h1>
	<br>
	<p>変更するよ！編集したら、「変更」ボタンを押してね♪</p>
	<form action="MainServlet" method="post">
	<input type="hidden" name="id" value="<%= id %>">
	<p>　問　　題：<input type="text" name="question" value="<%= question %>">
	<p>　答　　え：<input type="text" name="answer" value="<%= answer %>">
	<p>　コメント：<textarea name="comment"><%= comment %></textarea>
	</p>
	<input type="submit" name="submit" value="変更">
	</form>
	<br>
	<a href="index.jsp">トップへ</a>
</body>
</html>