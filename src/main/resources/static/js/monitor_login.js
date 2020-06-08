//载入时生成图片验证码
$(function() {
	var img = document.getElementsByClassName("checkimg")[0];
	img.src = "imgGetCode";
})
//切换验证码
function imgChange() {
	var img = document.getElementsByClassName("checkimg")[0];
	var time = new Date().getTime();
	img.src = "imgGetCode?t=" + time;
}

//点击登录后
$(".primary_button").on("click", function(){
	//取到前端输入的数据
	var cellphone=$(".cellphone").val();
	var password=$(".password").val();
	var code=$(".code").val();
	console.log("手机号==",cellphone,password,code);
	$.ajax({
		type: "post",
		url: "monitor_login/login",
		//数据传入后端
		data:{
			cellphone:cellphone,
			password:password,
			code:code,
		},
		dataType: "json",
		success: function(data){
			console.log("成功后返回的数据",data);
			//如果成功，将id，用户名，状态存入sessionStorage，跳转页面，失败弹窗
			if(data.code==1){
//			sessionStorage.setItem("ip",130),
//			sessionStorage.setItem("post",1300),
			sessionStorage.setItem("id",data.userid),
			sessionStorage.setItem("name",data.username),
			sessionStorage.setItem("status",data.code),
			location.href="redirect?page=monitor_main"
			}else{
				alert(data.msg);
			}
//			alert(data.msg);
		},
		error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
})