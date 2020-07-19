//载入时生成图片验证码
$(function () {
	var img = document.getElementsByClassName("checkimg")[0];
	img.src = "/apiz/imgGetCode";
})
// 切换验证码
function imgChange() {
	var img = document.getElementsByClassName("checkimg")[0];
	var time = new Date().getTime();
	img.src = "/apiz/imgGetCode?t=" + time;
}

//手机号栏失焦时，验证手机号
$(".cellphone").blur(function(){
var reg=/^1\d{10}$/;			//手机号正则
var cellphone=$('.cellphone').val()
var status=reg.test(cellphone);			//判断
if(status==false){
document.getElementById("label2").style.display="block";
document.getElementById("label2").innerHTML="<font color='red'>手机号格式不符合</font>"
}else{
document.getElementById("label2").style.display="none";
}
})


//根据返回状态，提示错误
function checkinfo(status){
switch(status) {
     case 0:
document.getElementById("label2").style.display="block";
document.getElementById("label2").innerHTML="<font color='red'>手机号必须11位</font>"
document.getElementById("label1").style.display="none";
document.getElementById("label3").style.display="none";
document.getElementById("label4").style.display="none";
        break;
     case 1:
document.getElementById("label2").style.display="block";
document.getElementById("label2").innerHTML="<font color='red'>手机号格式不符合</font>"
document.getElementById("label1").style.display="none";
document.getElementById("label3").style.display="none";
document.getElementById("label4").style.display="none";
        break;
     case 2:
document.getElementById("label2").style.display="block";
document.getElementById("label2").innerHTML="<font color='red'>手机号不存在</font>"
document.getElementById("label1").style.display="none";
document.getElementById("label3").style.display="none";
document.getElementById("label4").style.display="none";
        break;
     case 3:
document.getElementById("label1").style.display="block";
document.getElementById("label1").innerHTML="<font color='red'>用户名与手机号不匹配</font>"
document.getElementById("label2").style.display="none";
document.getElementById("label3").style.display="none";
document.getElementById("label4").style.display="none";
        break;
case 4:
document.getElementById("label3").style.display="block";
document.getElementById("label3").innerHTML="<font color='red'>两次密码不一致</font>"
document.getElementById("label1").style.display="none";
document.getElementById("label2").style.display="none";
document.getElementById("label4").style.display="none";
        break;
case 5:
document.getElementById("label3").style.display="block";
document.getElementById("label3").innerHTML="<font color='red'>重置密码与原密码相同</font>"
document.getElementById("label1").style.display="none";
document.getElementById("label2").style.display="none";
document.getElementById("label4").style.display="none";
        break;
case 6:
document.getElementById("label4").style.display="block";
document.getElementById("label4").innerHTML="<font color='red'>验证码错误</font>"
document.getElementById("label1").style.display="none";
document.getElementById("label2").style.display="none";
document.getElementById("label3").style.display="none";
        break;
     default:
document.getElementById("label1").style.display="none";
document.getElementById("label2").style.display="none";
document.getElementById("label3").style.display="none";
document.getElementById("label4").style.display="none";
}
}

// Demo
layui.use('form', function () {
	var form = layui.form;
	// 监听提交
	form.on('submit(formDemo)', function (data) {
		console.log(465879);
		// console.log("手机号==",cellphone,password,code);
		$.ajax({
			type: "post",
			url: "/apiz/register_upwd/upwd",
			// 数据传入后端
			data: {
				name: $('.name').val(),
				cellphone: $('.cellphone').val(),
				password: $('.password').val(),
				password1: $('.password1').val(),
				code: $('.code').val(),
			},
			dataType: "json",
			success: function (data) {
				console.log("成功后返回的数据", data);
				// 如果成功，弹出设置成功，跳转到登录页，失败弹窗
				console.log(data)
				if (data.code == 1) {
					alert(data.msg);
					location.href = "register_login.html"
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

