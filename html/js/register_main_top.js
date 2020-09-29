//用于显示走势图的js
var status="freight"
var trendname="货运走势图"
var airname;		//存年份
var value;		//存数据
var unit;		//单位

$(function () {
	login();
	img();
	freighttop();
	passengertop();
})



//点击搜索
$("#select").on("click", function () {
	console.log("ok")
	airportname=$('#airportname').val()
	start=$('#start').val()
	end=$('#end').val()
	if(airportname==""){
		airportname="所有"
	}
	if(start=="" && end==""){
		start="1"
		end="12"
	}
	$(".selectair").html("");
	var txt = "";
	txt += `显示 ${airportname} 机场 ${start} 至 ${end} 月数据(数据统计自2007-到2020年7月)`
	$(".selectair").append(txt);
	freighttrend();
	passengertrend();
})


//查询货运机场
function freighttop() {
	$.ajax({
		//请求类型
		type: "post",
		//请求路径
		url: "/apiz/airtop/freight",
		//返回数据类型
		dataType: "json",
		//请求成功后调用函数
		success: function (data) {
			console.log("成功后返回的数据", data);
			status="freight"
			trendname="货运TOP图"
			unit="吨";
			airname=data.airname;
			value=data.value;
			echarts_trend();
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
}
//查询客运机场分布
function passengertop() {
	$.ajax({
		//请求类型
		type: "post",
		//请求路径
		url: "/apiz/airtop/passenger",
		//返回数据类型
		dataType: "json",
		//请求成功后调用函数
		success: function (data) {
			console.log("成功后返回的数据", data);
			status="passenger"
			trendname="客运TOP图"
			unit="人次";
			airname=data.airname;
			value=data.value;
			echarts_trend()
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
}



function echarts_trend(){
	var container = echarts.init(document.getElementById(status));
	option = {
		title: {
			text: trendname,
			// fontStyle:'normal',
			// fontWeight:'normal',
		},
		// textStyle:{
		// 	fontStyle:'normal',
		// 	fontWeight:'normal',
		// },
		xAxis: {
			type: 'category',
			data: airname,
			name: '机场名',
		},
		yAxis: {
			type: 'value',
			name: unit,
		},
		toolbox: {
			show: true,
			feature: {
				dataZoom: {
					yAxisIndex: 'none'
				},
				dataView: {readOnly: false},
				magicType: {type: ['line', 'bar']},
				restore: {},
				saveAsImage: {}
			}
		},
		tooltip: {
			trigger: 'axis'
		},
		series: [{
			data: value,
			type: 'bar',
			showBackground: true,
			backgroundStyle: {
				color: 'rgba(220, 220, 220, 0.8)'
			},
			itemStyle: {
				normal:{
					color:'#61a0a8'
				}
			}
		}]
	};
	container.setOption(option,true);	
}



//显示头像
function img() {
	var userid = sessionStorage.getItem("id");
	var username = sessionStorage.getItem("name");
	$(".user").html("");
	var txt = "";
	txt += `<img src="/apiz/registerheadImg?id=${userid}" onerror="defaultImg(this)" style="
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
		location.href = "register_login.html";
	}
}

//注意：导航 依赖 element 模块，否则无法进行功能性操作	
layui.use('element', function () {
	var element = layui.element;

	//…
});
