<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Mode,java.util.Calendar,java.text.SimpleDateFormat,java.util.Date" %>
<%
	//セッションスコープからメモリストを取得
	Mode mode=(Mode)session.getAttribute("mode");
	Mode nextmode=(Mode)session.getAttribute("nextmode");
	String check=(String)session.getAttribute("check");
	Date explosivedate=(Date)session.getAttribute("explosivedate");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mode-Me</title>
<style>
	body{	background-color:#FFF;	}
	body.check{	background-color:#FFDBC9;	}
</style>
<script src="<%= request.getContextPath() %>/action.js" defer> </script>
</head>
<body>
	<h1>Mode-Me　【 瞬発力トレーニング 】</h1>
	<form action="MainServlet" method="post">
	<h2>問題：<%= mode.getQuestion() %></h2>
	<p>答え：
		<input type="submit" name="reply" value="過去の自分">
		<input type="submit" name="reply" value="将来の自分">
		<input type="submit" name="reply" value="戒めの自分">
		<input type="submit" name="reply" value="集中する自分">
	</p>
	</form>
	<a href="index.jsp">トップへ</a>
	<br>
	<div id="timer" data-endtime="<%= explosivedate.getTime() %>"></div>
</body>
</html>