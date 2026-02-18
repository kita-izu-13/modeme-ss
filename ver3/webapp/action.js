"use strict"

//body取得
const body=document.querySelector("body");
//Id「check」取得して、textContentで文字取得 （html文字列を含まない）
const check = (document.getElementById("check")).textContent;
console.log(check);

if(check==="不正解！"){
	console.log("背景色を変える");
	body.classList.add("check");
}else{
	console.log("背景色を戻す");
	body.classList.remove("check");
}

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


