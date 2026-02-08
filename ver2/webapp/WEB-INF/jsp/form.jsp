<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<p>追加するよ！入力したら、「追加」ボタンを押してね♪</p>
	<form action="MainServlet" method="post">
	<p>　問　　題：<input type="text" name="question">
	<p>　答　　え：<input type="text" name="answer">
	<p>　コメント：<textarea name="comment"></textarea>
	</p>
	<input type="submit" value="追加">
	</form>
	<br>
	<a href="index.jsp">トップへ</a>
</body>
</html>