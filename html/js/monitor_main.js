var memory = new Array(60)	//存60秒内存使用量
var disk0;					//存磁盘总量
var disk1;					//存磁盘使用量
var disk2;					//存磁盘未使用量
var xAxis = new Array(60);	//横坐标
var local = "192.168.1." + sessionStorage.getItem("ip");
var status = "关闭";
var nn1 = "50"; var nn2 = "51"; var dn1 = "60"; var dn2 = "61"; var dn3 = "62";
var post = sessionStorage.getItem("post");

var num = 60;
memory.fill(0);	//开始时是0
for (var i = 0; i < xAxis.length; i++) {
	xAxis[i] = num;	//横坐标60-0
	num--;
}

$(function () {
	login();
	sessionStorage.setItem("ip", 50);
	sessionStorage.setItem("post", 5000);
	img();
	defaultimage();
	linklinux();
	changetable();
})


//连接主机的回调函数，每隔一秒访问一次
setInterval(function () {
	//	linklinux()
	var i = 0;
	local = "192.168.1." + sessionStorage.getItem("ip");
	post = sessionStorage.getItem("post");
	var ws = new WebSocket('ws://' + local + ':' + post + "/");
	var linux = new Array()	//存linux拿到的信息

	//获取linux信息
	ws.onmessage = function (event) {
		linux[i] = event.data;
		i = i + 1;
		//			  console.log("5555",linux.length)
		if (linux.length == 22 && status == "开启") {
			changetable();
			var arr1 = linux[9].split("=");	//内存使用量
			var arr2 = linux[8].split("=");	//内存总量

			memory.shift();		//移除首选项
			memory.push(arr1[1]);	//末位插入

			var arr3 = linux[14].split("=");	//盘符名称
			var arr4 = linux[15].split("=");	//分区总大小
			var arr5 = linux[18].split("=");	//分区使用量
			var arr6 = linux[16].split("=");	//分区未使用

			arr4[1] = Math.round(arr4[1] / 1024 / 1024);	//G做单位
			arr5[1] = Math.round(arr5[1] / 1024 / 1024);
			arr6[1] = Math.round(arr6[1] / 1024 / 1024);
			disk0 = arr4[1];
			disk1 = arr5[1];
			disk2 = arr6[1];

			//调用echarts图片
			echartsimg(arr2[1]);
		}
		//			if(status=="关闭"){
		//				defaultimage()
		//			}
	}
}, 1000)

//连接linux
function linklinux() {
	local = "192.168.1." + sessionStorage.getItem("ip");

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
			if (data.status == '-1') {
				status = "开启";
				changetable();
			}
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
			//			status="关闭";
			//			changetable();
		}
	})
}




//默认没有连接时显示的图片
function defaultimage() {
	//显示内存折线图
	memory.fill(0);
	disk0 = 0;
	disk1 = 0;
	disk2 = 0;
	echartsimg(100);
}

function echartsimg(arr2) {
	var main_image1 = echarts.init(document.querySelector('#main-image1'));
	option = {
		title: {
			text: '内存使用量',
			textStyle: {
				color: 'black',
				fontSize: 30
			},
			left: '15%',
		},
		xAxis: {
			type: 'category',
			boundaryGap: false,
			data: xAxis,
			name: '秒',
			nameLocation: 'end',
			nameTextStyle: {
				fontSize: 20,
			}
		},
		yAxis: {
			type: 'value',
			max: arr2,
			name: 'B',
			nameTextStyle: {
				fontSize: 20,
			}
		},
		makepoint: {},
		series: [{
			data: memory,
			type: 'line',
			areaStyle: {
				normal: {
					color: '#61a0a8' //改变区域颜色
				}
			},
			itemStyle: {
				normal: {
					color: '#61a0a8', //改变折线点的颜色
					lineStyle: {
						color: '#61a0a8', //改变折线颜色

					},
				}
			},
		}]
	};
	main_image1.setOption(option);

	//显示磁盘饼图
	var main_image2 = echarts.init(document.querySelector('#main-image2'));
	option = {
		title: {
			text: '磁盘使用情况',
			subtext: '总量' + disk0 + "GB",
			left: 'center'
		},
		tooltip: {
			trigger: 'item',
			formatter: '{a} <br/>{b} : {c} ({d}%)'
		},
		legend: {
			orient: 'vertical',
			left: 'left',
			data: ['已使用', '未使用']
		},
		series: [
			{
				name: '单位GB',
				type: 'pie',
				radius: '55%',
				center: ['50%', '60%'],
				data: [
					{ value: disk1, name: '已使用', itemStyle: { normal: { color: '#d48265' } } }, 	//改变折线点的颜色
					{ value: disk2, name: '未使用', itemStyle: { normal: { color: '#749f83' } } },
				],
				emphasis: {
					shadowBlur: 10,
					shadowOffsetX: 0,
					shadowColor: 'rgba(0, 0, 0, 0.5)'
				}
			}
		]
	};
	main_image2.setOption(option);
}


//切换主机
function change(id, post, obj) {
	sessionStorage.setItem("ip", id);
	sessionStorage.setItem("post", post);
	defaultimage();
	status = "关闭"	//默认关闭，连接成功时切换开启
	linklinux();
	//	linklinux();
	changetable();
	$('.layui-this').removeClass('layui-this');	//样式改变
	$(obj).addClass("layui-this");
}



//关闭主机
$(".stop").on("click", function () {
	var command = "shutdown -h -t 1";
	console.log(local, command)
	$.ajax({
		//请求类型
		type: "post",
		//请求路径
		url: "/apiz/monitor_main/linkroot",
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
			if (data.status == '-1') {
				status = "关闭";
				defaultimage();
				//				alert("关闭成功")
			} else {
				alert("关闭失败")
			}
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
})
//重启主机
$(".restart").on("click", function () {
	var command = "reboot";
	$.ajax({
		//请求类型
		type: "post",
		//请求路径
		url: "/apiz/monitor_main/linkroot",
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
			if (data.status == '-1') {
				alert("正在重启，稍后请刷新")
				status = "关闭";
				defaultimage();
			} else {
				alert("重启失败")
			}
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
})


//////////////////////////


//表格显示
function changetable() {
	$(".localtable").html("");
	var txt = "";
	txt += ` <tr>
      <td  style="font-size: 30px;">运行状态</td>
      <td style="font-size: 30px;">${status}</td>
    </tr>
    <tr>
      <td style="font-size: 30px;">ip地址</td>
      <td style="font-size: 30px;">${local}</td>
    </tr>
    </tr>`
	$(".localtable").append(txt);
}


//显示头像
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


$(".service").on("click", function(){
	sessionStorage.setItem("ip",50);
	sessionStorage.setItem("post",8081);

	local = "192.168.1.50";
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
		},
		error: function (data) {
			console.log("失败后返回的数据", data);

		}
	})
	location.href="monitor_service.html"
})