//载入时生成图片验证码
$(function () {
	var img = document.getElementsByClassName("checkimg")[0];
	img.src = "/apiz/imgGetCode";
})
//切换验证码
function imgChange() {
	var img = document.getElementsByClassName("checkimg")[0];
	var time = new Date().getTime();
	img.src = "/apiz/imgGetCode?t=" + time;
}


$(".cellphone").blur(function(){
var reg=/^1\d{10}$/;			//手机号正则
var cellphone=$('.cellphone').val()
console.log(cellphone)
var status=reg.test(cellphone);			//判断
if(status==false){
document.getElementById("label1").style.display="block";
document.getElementById("label1").innerHTML="<font color='red'>手机号格式不符合</font>"
}else{
document.getElementById("label1").style.display="none";
}
})

//根据返回状态，提示错误
function checkinfo(status){
switch(status) {
     case 0:
document.getElementById("label1").style.display="block";
document.getElementById("label1").innerHTML="<font color='red'>手机号必须11位</font>"
document.getElementById("label2").style.display="none";
document.getElementById("label3").style.display="none";
        break;
     case 1:
document.getElementById("label3").style.display="block";
document.getElementById("label3").innerHTML="<font color='red'>验证码错误</font>"
document.getElementById("label1").style.display="none";
document.getElementById("label2").style.display="none";
        break;
     case 2:
document.getElementById("label1").style.display="block";
document.getElementById("label1").innerHTML="<font color='red'>账号不存在</font>"
document.getElementById("label2").style.display="none";
document.getElementById("label3").style.display="none";
        break;
     case 3:
document.getElementById("label2").style.display="block";
document.getElementById("label2").innerHTML="<font color='red'>密码错误</font>"
document.getElementById("label1").style.display="none";
document.getElementById("label3").style.display="none";
        break;
     case 4:
document.getElementById("label1").style.display="block";
document.getElementById("label1").innerHTML="<font color='red'>手机号格式不正确</font>"
document.getElementById("label2").style.display="none";
document.getElementById("label3").style.display="none";
        break;
     default:
document.getElementById("label1").style.display="none";
document.getElementById("label2").style.display="none";
document.getElementById("label3").style.display="none";
}
}

//Demo
layui.use('form', function () {
	var form = layui.form;
	//监听提交
	form.on('submit(formDemo)', function (data) {
		//		console.log("手机号==",cellphone,password,code);
		$.ajax({
			type: "post",
			url: "/apiz/admin_login/login",
			//数据传入后端
			data: {
				cellphone: $('.cellphone').val(),
				password: $('.password').val(),
				code: $('.code').val(),
			},
			dataType: "json",
			success: function (data) {
				console.log("成功后返回的数据", data);
				//如果成功，将id，用户名，状态存入sessionStorage，跳转页面，失败弹窗
				if (data.code == 1) {
					sessionStorage.setItem("id", data.userid);
						sessionStorage.setItem("name", data.username);
						sessionStorage.setItem("status", data.code);
						location.href = "admin_main.html"
				} else {
					//alert(data.msg);
					checkinfo(data.status);
					$('.code').val("");
					imgChange();
				}
			},
			error: function (data) {
				console.log("失败后返回的数据", data);
			}
		})
		return false;
	});
});

