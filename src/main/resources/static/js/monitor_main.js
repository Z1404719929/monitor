
$(function(){
	img();
	var ws = new WebSocket('ws://192.168.1.130:8080/');

	ws.onmessage = function(event) {
		console.log("websk");
	  console.log(event.data);
	};
})


//显示头像
function img(){
	var userid=sessionStorage.getItem("id");
	var username=sessionStorage.getItem("name");
	$(".user").html("");
	var txt="";
	txt +=`<img src="/headImg?id=${userid}"  style="
    width: 50px;
    height: 50px;
    border-radius: 40px;">${username}`
	$(".user").append(txt);
}
