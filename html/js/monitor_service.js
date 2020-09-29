var jps1 = new Array();
var jps2 = new Array();
var local;
var post;
var status = 0;
// var nnjps=["NameNode","DFSZKFailoverController","Master"]
// var nnstatus=["开启","开启","开启"]
// var nnhost=["无","无","无"]
// var dnjps=["DataNode","JournalNode","QuorumPeerMain","Worker"]
// var dnstatus=["开启","开启","开启","开启"]
// var dnhost=["无","无","无","无"]

$(function () {
	sessionStorage.setItem("ip", 50);
	sessionStorage.setItem("post", 8081);
	var status = 0;
	img();
	jps();
	login();
})



function jps() {
	local = "192.168.1." + sessionStorage.getItem("ip");
	post = sessionStorage.getItem("post");
	var command = "bash ~/sh/webskjps.sh";
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
				linkjps();
			}
		},
		error: function (data) {
			console.log("失败后返回的数据", data);

		}
	})

}

function linkjps() {
	var i = 0;
	var ws = new WebSocket('ws://' + local + ':' + post + '/');
	var linux = new Array()	// 存linux拿到的信息

	// 获取linux信息
	ws.onmessage = function (event) {
		linux[i] = event.data;
		var a = linux[i].split(" ");
		console.log("874654", a[1])
		jps1[i] = a[0];
		jps2[i] = a[1];
		tbody();
		console.log(a[0]);
		console.log(a[1]);
		i = i + 1;
	}
}




function tbody() {
	var nnjps=["NameNode","DFSZKFailoverController","Master"]
	var nnstatus=["开启","开启","开启"]
	var nnhost=["无","无","无"]
	var dnjps=["DataNode","JournalNode","QuorumPeerMain","Worker"]
	var dnstatus=["开启","开启","开启","开启"]
	var dnhost=["无","无","无","无"]

	if(sessionStorage.getItem("ip")<60){
		if (status == '-1') {
			var txt = "";
			//判断进程是否开启
			for (var i = 0; i < nnjps.length; i++) {
				for(var j = 0; j < jps2.length; j++){
					if(jps2[j]==nnjps[i]){
						nnstatus[i]="关闭";
						nnhost[i]=jps1[j];
					}
				}
			}
			for(var i = 0; i < nnjps.length;i++){
				console.log(i)
				if(nnstatus[i]=="关闭"){
				$(".jpstbody").html("");
					txt += ` <tr>
					  <td>${nnjps[i]}</td>
					  <td>${nnhost[i]}</td>
					  <td><button type="button" class="layui-btn layui-btn-lg layui-btn-danger" onclick="kill(${nnhost[i]})">关闭进程</button></td>
						</tr>`
					$(".jpstbody").append(txt);
				}else{
					$(".jpstbody").html("");
					txt += ` <tr>
					  <td>${nnjps[i]}</td>
					  <td>无</td>
					  <td><button type="button" class="layui-btn layui-btn-lg" onclick='open1(\"${nnjps[i]}\")'>开启进程</button></td>
						</tr>`
					$(".jpstbody").append(txt);
				}
			}
		}
		if (status == '0') {
			var txt = "";
			for (var i = 0; i < nnjps.length; i++) {
			$(".jpstbody").html("");
			txt += ` <tr>
		      <td>${nnjps[i]}</td>
		      <td>无</td>
		      <td><button type="button" class="layui-btn layui-btn-lg" onclick="open1(\"${nnjps[i]}\")">开启进程</button></td>
		    </tr>`
			$(".jpstbody").append(txt);
			}
		}
	}
	else{
		if (status == '-1') {
			var txt = "";
			//判断进程是否开启
			for (var i = 0; i < dnjps.length; i++) {
				for(var j = 0; j < jps2.length; j++){
					if(jps2[j]==dnjps[i]){
						dnstatus[i]="关闭";
						dnhost[i]=jps1[j];
					}
				}
			}
			for(var i = 0; i < dnjps.length;i++){
				console.log(i)
				if(dnstatus[i]=="关闭"){
				$(".jpstbody").html("");
					txt += ` <tr>
					  <td>${dnjps[i]}</td>
					  <td>${dnhost[i]}</td>
					  <td><button type="button" class="layui-btn layui-btn-lg layui-btn-danger" onclick="kill(${dnhost[i]})">关闭进程</button></td>
						</tr>`
					$(".jpstbody").append(txt);
				}else{
					$(".jpstbody").html("");
					txt += ` <tr>
					  <td>${dnjps[i]}</td>
					  <td>无</td>
					  <td><button type="button" class="layui-btn layui-btn-lg" onclick='open1(\"${dnjps[i]}\")'>开启进程</button></td>
						</tr>`
					$(".jpstbody").append(txt);
				}
			}
		}
		if (status == '0') {
			var txt = "";
			for (var i = 0; i < dnjps.length; i++) {
			$(".jpstbody").html("");
			txt += ` <tr>
		      <td>${dnjps[i]}</td>
		      <td>无</td>
		      <td><button type="button" class="layui-btn layui-btn-lg" onclick="open1(\"${dnjps[i]}\")">开启进程</button></td>
		    </tr>`
			$(".jpstbody").append(txt);
			}
		}
	}
}

function kill(port) {
	console.log(port);
	var command = "kill -9 " + port;
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
			console.log("成功杀死进程", data);
			// location.href = "monitor_service.html"
			status=data.status
			if (status == '-1') {
			alert("成功")
			jps1.length = 0;
			jps2.length = 0;
			jps();
			}else{
				alert("失败")
			}
			
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
			//			location.href="monitor_service.html"
		}
	})
}

function open1(str) {
	console.log(str);
	var command = "bash /home/zhaochaoqun/sh/" + str + ".sh";

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
			console.log("成功后返回的数据", data);
			// location.href = "monitor_service.html"
			status=data.status
			if (status == '-1') {
			alert("成功")
			jps1.length = 0;
			jps2.length = 0;
			jps();
			}else{
				alert("失败")
			}
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
			//			location.href="monitor_service.html"
		}
	})
}

function change(id, obj) {
	jps1.length = 0;
	jps2.length = 0;
	status = 0;
	tbody();
	sessionStorage.setItem("ip", id);
	jps();

	$('.layui-this').removeClass('layui-this');	//样式改变
	$(obj).addClass("layui-this");
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