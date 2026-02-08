"use strict"

//IDにより終了時刻を取得
const timerDiv = document.getElementById("timer");
console.log(timerDiv);
const endTime = new Date(parseInt(timerDiv.dataset.endtime));
console.log(endTime);

//setIntervalメソッドを使い、1秒ごとに現在時刻チェック
const timer=setInterval(() => {
	const now = new Date();
	
	if(now >= endTime){
		clearInterval(timer);	//タイマー停止
		window.location.href="MainServlet";	//MainServletへ遷移（GET）
	}
},1000);