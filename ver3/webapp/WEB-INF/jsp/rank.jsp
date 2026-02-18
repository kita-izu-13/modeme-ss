<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,java.util.List" %>
<%
	//セッションスコープから取得
	List<String> rank=(List<String>)session.getAttribute("rank");
	int count=(int)session.getAttribute("count");
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
	<% for(String str:rank){ %>
	<h2>　<%= str %></h2>
	<% } %>
	<p>正解数：<%= count %></p>
	<br>
	<a href="index.jsp">トップへ</a>
</body>
