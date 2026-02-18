<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Mode,java.util.Calendar,java.text.SimpleDateFormat,java.util.Date" %>
<%
	//セッションスコープからメモリストを取得
	Mode mode=(Mode)session.getAttribute("mode");
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
	<h1>Mode-Me　【 苦手問題集中トレーニング 】</h1>

	<a href="index.jsp">トップへ</a>
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