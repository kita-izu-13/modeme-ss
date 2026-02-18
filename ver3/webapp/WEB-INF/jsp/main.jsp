<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Mode" %>
<%
	//セッションスコープから取得
	Mode mode=(Mode)session.getAttribute("mode");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mode-Me</title>
</head>
<body>
	<h1>Mode-Me</h1>
	<br>
	
	<form action="MainServlet" method="post">
	<h2>問題：<%= mode.getQuestion() %></h2>
	<p>答え：
		<input type="submit" name="reply" value="過去の自分">
		<input type="submit" name="reply" value="将来の自分">
		<input type="submit" name="reply" value="戒めの自分">
		<input type="submit" name="reply" value="集中する自分">
	</p>
	</form>
</body>
</html>