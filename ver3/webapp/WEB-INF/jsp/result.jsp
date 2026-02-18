<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Mode,java.util.Calendar,java.text.SimpleDateFormat,java.util.Date" %>
<%
	//セッションスコープからメモリストを取得
	Mode mode=(Mode)session.getAttribute("mode");
	Mode nextmode=(Mode)session.getAttribute("nextmode");
	String check=(String)session.getAttribute("check");
	Date afterdate=(Date)session.getAttribute("afterdate");
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
	<h1>Mode-Me</h1>
	<h2 id="check"><%= check %></h2>
	<p>問題：<%= mode.getQuestion() %></p>
	<p>正解：<%= mode.getAnswer() %></p>
	<p>　　<%= mode.getComment() %></p>
	<br>
	<form action="MainServlet" method="post">
	<h2>問題：<%= nextmode.getQuestion() %></h2>
	<p>答え：
		<input type="submit" name="reply" value="過去の自分">
		<input type="submit" name="reply" value="将来の自分">
		<input type="submit" name="reply" value="戒めの自分">
		<input type="submit" name="reply" value="集中する自分">
	</p>
	</form>
	<a href="index.jsp">トップへ</a>
	<br>
	<div id="timer" data-endtime="<%= afterdate.getTime() %>"></div>
</body>
</html>