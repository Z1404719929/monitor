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

// Demo
layui.use('form', function () {
	var form = layui.form;
	// 监听提交
	form.on('submit(formDemo)', function (data) {
		console.log(465879);
		// console.log("手机号==",cellphone,password,code);
		$.ajax({
			type: "post",
			url: "/apiz/monitor_upwd/upwd",
			// 数据传入后端
			data: {
				name: JSON.parse(JSON.stringify(data.field.name)),
				cellphone: JSON.parse(JSON.stringify(data.field.cellphone)),
				password: JSON.parse(JSON.stringify(data.field.password)),
				password1: JSON.parse(JSON.stringify(data.field.password1)),
				code: JSON.parse(JSON.stringify(data.field.code)),
			},
			dataType: "json",
			success: function (data) {
				console.log("成功后返回的数据", data);
				// 如果成功，弹出设置成功，跳转到登录页，失败弹窗
				console.log(data)
				if (data.code == 1) {
					alert(data.msg);
					location.href = "monitor_login.html"
				} else {
					alert(data.msg);
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

// //点击注册后
// $(".primary_button").on("click", function(){
// //取到前端输入的数据
// var name=$(".name").val();
// var cellphone=$(".cellphone").val();
// var password=$(".password").val();
// var password1=$(".password1").val();
// var code=$(".code").val();
// console.log("手机号==",name,cellphone,password,password1,code);
// $.ajax({
// type: "post",
// url: "monitor_upwd/upwd",
// //数据传入后端
// data:{
// name:name,
// cellphone:cellphone,
// password:password,
// password1:password1,
// code:code,
// },
// dataType: "json",
// success: function(data){
// console.log("成功后返回的数据",data);
// //如果成功，弹出设置成功，跳转到登录页，失败弹窗
// if(data.code==1){
// alert(data.msg);
// location.href="redirect?page=monitor_login"
// }else{
// alert(data.msg);
// }
// },
//		error: function(data){
//			console.log("失败后返回的数据",data);
//		}
//	})
//})