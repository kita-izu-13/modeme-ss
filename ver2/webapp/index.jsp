<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mode-Me</title>
</head>
<body>
	<h1>Mode-Meへようこそ！</h1>
	<form action="MainServlet" method="get">
		<p>ゲームは「スタート」ボタンを押してね！</p>
		<input type="submit" name="select" value="スタート">
		<br>
		<p>リストは「一覧」ボタンを押してね！</p>
		<input type="submit" name="select" value="一覧">
		<br>
		<p>追加は「追加」ボタンを押してね！</p>
		<input type="submit" name="select" value="追加">
	</form>
</body>
</html>