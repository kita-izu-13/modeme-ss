<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mode-Me</title>
<style>
	input{
		margin-bottom:10px;
	}
</style>
</head>
<body>
	<h1>Mode-Meへようこそ！</h1>
	<form action="MainServlet" method="get">
		<p>通常トレーニングは、モードを選択して「スタート」ボタンを押してね！</p>
		<input type="submit" name="select" value="スタート">　
		<input type="radio" name="radio" value="集中" checked>1分(集中モード)
		<input type="radio" name="radio" value="時短">20秒(時短モード)
		<br>
		<p>苦手問題集中トレーニングは「苦手問題集中トレーニング」ボタンを押してね！</p>
		<input type="submit" name="select" value="苦手問題集中トレーニング">
		<br>
		<p>瞬発力トレーニングは「瞬発力トレーニング」ボタンを押してね！</p>
		<input type="submit" name="select" value="瞬発力トレーニング">
		<br>
		<p>リストは「一覧」ボタンを押してね！</p>
		<input type="submit" name="select" value="一覧">
		<br>
		<p>追加は「追加」ボタンを押してね！</p>
		<input type="submit" name="select" value="追加">
		<br>
		<p>変更は「変更」ボタンを押してね！</p>
		<input type="submit" name="select" value="変更">
	</form>
</body>
</html>