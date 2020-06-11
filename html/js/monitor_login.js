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


//Demo
layui.use('form', function () {
	var form = layui.form;
	//监听提交
	form.on('submit(formDemo)', function (data) {
		//		console.log("手机号==",cellphone,password,code);
		$.ajax({
			type: "post",
			url: "/apiz/monitor_login/login",
			//数据传入后端
			data: {
				cellphone: JSON.parse(JSON.stringify(data.field.cellphone)),
				password: JSON.parse(JSON.stringify(data.field.password)),
				code: JSON.parse(JSON.stringify(data.field.code)),
			},
			dataType: "json",
			success: function (data) {
				console.log("成功后返回的数据", data);
				//如果成功，将id，用户名，状态存入sessionStorage，跳转页面，失败弹窗
				if (data.code == 1) {
					//sessionStorage.setItem("ip",50),
					//sessionStorage.setItem("post",5000),
					linklinux();
					sessionStorage.setItem("id", data.userid);
						sessionStorage.setItem("name", data.username);
						sessionStorage.setItem("status", data.code);
						location.href = "monitor_main.html"
				} else {
					alert(data.msg);
					imgChange();
				}
				//				alert(data.msg);
			},
			error: function (data) {
				console.log("失败后返回的数据", data);
			}
		})
		return false;
	});
});

function linklinux() {
	local = "192.168.1.50";
	var command = "bash ~/sh/webskstart.sh";	//打开获取主机信息的脚本
	$.ajax({
		//请求类型
		type: "post",
		//请求路径
		url: "/apiz/monitor_main/linklinux",
		//请求参数
		data: {
			local: local,
			command: command,
		},
		//返回数据类型
		dataType: "json",
		//请求成功后调用函数
		success: function (data) {
			console.log("成功后返回的数据", data);
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
}

