//用于显示走势图的js
var status="freight"
var trendname="货运走势图"
var year;
var sum;
var trenddata=[];


$(function () {
	login();
	img();
	freighttrend();
	passengertrend();
})


// //点击货运机场
// $("#freighttrend").on("click", function () {
// 	// status="freight"
// 	// console.log("111")
// 	freighttrend();
// })
// //点击客运机场
// $("#passengetrend").on("click", function () {
// 	// status="passenger"
// 	// console.log(sdadas)
// 	passengertrend();
// })

//点击搜索
// $("#select").on("click", function () {
// 	airportname=$('#airportname').val()
// 	if(airportname==""){
// 		airportname="所有"
// 	}
// 	$(".selectair").html("");
// 	var txt = "";
// 	txt += `显示 ${airportname} 机场07-20年2月数据`
// 	$(".selectair").append(txt);
// 	freighttrend();
// 	passengertrend();
// })


//查询货运机场
function freighttrend() {
	$.ajax({
		//请求类型
		type: "post",
		//请求路径
		url: "/apiz/airtrend/freight_trend",
		//请求参数
		data: {
			airportname:$('#airportname').val()
		},
		//返回数据类型
		dataType: "json",
		//请求成功后调用函数
		success: function (data) {
			console.log("成功后返回的数据111", data);
			status="freight"
			trendname="货运走势图"
			year=data.year;
			sum=data.sum;
			echarts_trend();
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
}
//查询客运机场分布
function passengertrend() {
	$.ajax({
		//请求类型
		type: "post",
		//请求路径
		url: "/apiz/airtrend/passenger_trend",
		//请求参数
		data: {
			airportname:$('#airportname').val()
		},
		//返回数据类型
		dataType: "json",
		//请求成功后调用函数
		success: function (data) {
			console.log("成功后返回的数据", data);
			status="passenger"
			trendname="客运走势图"
			year=data.year;
			sum=data.sum;
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
		},
		xAxis: {
			type: 'category',
			data: year,
			name: '年',
		},
		yAxis: {
			type: 'value',
			name: "吨",
		},
		tooltip: {
			trigger: 'axis'
		},
		series: [{
			data: sum,
			type: 'line',
			// itemStyle : { normal: {label : {show: true}}}
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
