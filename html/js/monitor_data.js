var url1= new Array();
var url2 = new Array();
var local;
var post;
var pwd = new Array();
var status = 0;

$(function () {
	// sessionStorage.setItem("ip", 50);
	// sessionStorage.setItem("post", 8088);
	// var status = 0;
	img();
	login();
})


//freight数据处理
$("#freight").on("click", function () {
	ls_freight()
	freight()
})


function freight() {
	var command = "bash /home/zhaochaoqun/sh/freight_websk.sh";
	local="192.168.1.50"
	console.log(command);
	$.ajax({
		// 请求类型
		type: "post",
		// 请求路径
		url: "/apiz/monitor_main/linklinux",
		// 请求参数
		data: {
			local: local,
			command: command,
		},
		// 返回数据类型
		dataType: "json",
		// 请求成功后调用函数
		success: function (data) {
			console.log("查看hadoop freight文件夹", data);
			
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
}


function ls_freight() {
	var i = 0;
	var ws = new WebSocket('ws://192.168.1.50:8081/');
	var linux = new Array()	// 存linux拿到的信息
	pwd=[]
	console.log("获取地址");
	// 获取linux信息
	ws.onmessage = function (event) {
		linux[i] = event.data;
		var a = linux[i].split(" ");
		var size=a.length
		console.log(a[size-1]);
		pwd[i]=a[size-1]
		// spark_freight(pwd[i])
		i = i + 1;
	}
}

function spark_freight(pwd1) {
	var command = "/home/zhaochaoqun/spark/spark-2.4.2-bin-hadoop2.7/bin/spark-submit "+
	 "--class freight --master spark://namenode1:7077,namenode2:7077 /home/zhaochaoqun/data/airport.jar "+ pwd1;
	local="192.168.1.50"
	console.log(command);
	$.ajax({
		// 请求类型
		type: "post",
		// 请求路径
		url: "/apiz/monitor_main/linklinux",
		// 请求参数
		data: {
			local: local,
			command: command,
		},
		// 返回数据类型
		dataType: "json",
		// // 请求成功后调用函数
		// success: function (data) {
		// 	console.log("处理freight数据", data);
		// },
		// error: function (data) {
		// 	console.log("失败后返回的数据", data);
		// }
	})
	alert("已发送请求")
}

//passenger数据处理
$("#passenger").on("click", function () {
	passenger()
})

function passenger() {
	var command = "bash /home/zhaochaoqun/sh/passenger_websk.sh";
	local="192.168.1.50"
	console.log(command);
	$.ajax({
		// 请求类型
		type: "post",
		// 请求路径
		url: "/apiz/monitor_main/linklinux",
		// 请求参数
		data: {
			local: local,
			command: command,
		},
		// 返回数据类型
		dataType: "json",
		// 请求成功后调用函数
		success: function (data) {
			console.log("查看hadoop passenger文件夹", data);
			ls_passenger()
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
}


function ls_passenger() {
	var i = 0;
	var ws = new WebSocket('ws://192.168.1.50:8082/');
	var linux = new Array()	// 存linux拿到的信息
	pwd=[]
	// 获取linux信息
	ws.onmessage = function (event) {
		linux[i] = event.data;
		var a = linux[i].split(" ");
		var size=a.length
		console.log(a[size-1]);
		pwd[i]=a[size-1]
		spark_passenger(pwd[i])
		i = i + 1;
	}
}

function spark_passenger(pwd1) {
	var command = "/home/zhaochaoqun/spark/spark-2.4.2-bin-hadoop2.7/bin/spark-submit "+
	 "--class passenger --master spark://namenode1:7077,namenode2:7077 /home/zhaochaoqun/data/airport.jar "+ pwd1;
	local="192.168.1.50"
	console.log(command);
	$.ajax({
		// 请求类型
		type: "post",
		// 请求路径
		url: "/apiz/monitor_main/linklinux",
		// 请求参数
		data: {
			local: local,
			command: command,
		},
		// 返回数据类型
		dataType: "json",
		// // 请求成功后调用函数
		// success: function (data) {
		// 	console.log("处理passenger数据", data);
		// },
		// error: function (data) {
		// 	console.log("失败后返回的数据", data);
		// }
	})
	alert("已发送请求")
}

/////////////////////////////////////


//删除文件
function hadooprm(url) {
	local = "192.168.1." + sessionStorage.getItem("ip");
	post = sessionStorage.getItem("post");
	var command = "hadoop fs -rm "+url;
	$.ajax({
		// 请求类型
		type: "post",
		// 请求路径
		url: "/apiz/monitor_main/linklinux",
		// 请求参数
		data: {
			local: local,
			command: command,
		},
		// 返回数据类型
		dataType: "json",
		// 请求成功后调用函数
		success: function (data) {
			console.log("成功后返回的数据", data);
			status = data.status;
			if (status == '-1') {
				console.log("删除文件")
			}
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
}


//货运数据预测
function freightforecast() {
	$.ajax({
		// 请求类型
		type: "post",
		// 请求路径
		url: "/apiz/monitor_data/freightforecast",
		// 返回数据类型
		dataType: "json",
		// 请求成功后调用函数
		success: function (data) {
			console.log("成功后返回的数据", data);
			alert(data.msg)
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
}

//客运数据预测
function passengerforecast() {
	$.ajax({
		// 请求类型
		type: "post",
		// 请求路径
		url: "/apiz/monitor_data/passengerforecast",
		// 返回数据类型
		dataType: "json",
		// 请求成功后调用函数
		success: function (data) {
			console.log("成功后返回的数据", data);
			alert(data.msg)
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
}










// 显示头像
function img() {
	var userid = sessionStorage.getItem("id");
	var username = sessionStorage.getItem("name");
	$(".user").html("");
	var txt = "";
	txt += `<img src="/apiz/monitorheadImg?id=${userid}" onerror="defaultImg(this)" style="
    width: 50px;
    height: 50px;
    border-radius: 50px;">${username}`
	$(".user").append(txt);
}
function defaultImg(img) {
	var id = sessionStorage.getItem("id")
	img.src = "/images/default_user.png";
}
//登录判断
function login() {
	var status = sessionStorage.getItem("status");
	if (status != 1) {
		alert("请先登录");
		sessionStorage.clear();
		location.href = "monitor_login.html";
	}
}

//注意：导航 依赖 element 模块，否则无法进行功能性操作	
layui.use('element', function () {
	var element = layui.element;

	//…
});