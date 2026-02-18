<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Mode,java.util.List" %>
<%
	//セッションスコープから取得
	List<Mode> list=(List<Mode>)session.getAttribute("list");
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
	<a href="index.jsp">トップへ</a>
	
	<p>問　題　：　答　え　：　コ　メ　ン　ト</p>

	<% for(Mode mode:list){ %>
	<p>	<%= mode.getQuestion() %> ： 
		<%= mode.getAnswer() %> ： 
		<%= mode.getComment() %>	</p>
	<% } %>
	<a href="index.jsp">トップへ</a>
</body>
</html>