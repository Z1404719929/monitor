//用于显示地图的js

var airdata=[];


$(function () {
	login();
	img();
	airfreight();
})







//点击运量走势
$(".map").on("click", function () {
	$("#freighttrend").attr("id","airfreight");//改变id
	$("#passengertrend").attr("id","airpassenger");//改变id
	document.getElementById("airportname").style.display="none";
	document.getElementById("select").style.display="none";
	airfreight();
})


//点击货运机场分布
$("#airfreight").on("click", function () {
	airfreight();
	console.log("成功")
})
//点击客运机场分布
$("#airpassenger").on("click", function () {
	airpassenger();
	console.log("成功")
})

//查询货运机场分布
function airfreight() {
	$.ajax({
		//请求类型
		type: "post",
		//请求路径
		url: "/apiz/airmap/airfreight",
		//返回数据类型
		dataType: "json",
		//请求成功后调用函数
		success: function (data) {
			console.log("成功后返回的数据111", data);
			airdata=data;
			console.log(airdata.airdata.length)
			echarts_map();
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
}
//查询客运机场分布
function airpassenger() {
	$.ajax({
		//请求类型
		type: "post",
		//请求路径
		url: "/apiz/airmap/airpassenger",
		//返回数据类型
		dataType: "json",
		//请求成功后调用函数
		success: function (data) {
			// console.log("成功后返回的数据", data);
			airdata=data
			console.log(airdata.airdata.length)
			echarts_map();
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
}

//合并机场名，坐标
var convertData = function (airdata) {
   var res = [];
//    console.log(1111,airdata,airdata.airdata[0].longitude,airdata.airdata[0].latitude)
   for (var i = 0; i < airdata.airdata.length; i++) {
	var geoCoord = airdata.airdata[i].airportname;
	   if (geoCoord) {
		   res.push({
			   name: airdata.airdata[i].airportname,
			   value: [airdata.airdata[i].longitude,airdata.airdata[i].latitude]
		   });
		//    console.log(11111,airdata.airdata[i].airportname)
	   }
   }
   return res;
};

function echarts_map(){
	var container = echarts.init(document.getElementById("container"));
	console.log(222222222)
   option = {
	   title: {
		   text: '俄罗斯货运机场分布图 - 百度地图',
		   subtext: 'data from PM25.in',
		   left: 'center'
	   },
	   tooltip : {
		   formatter: '{b}：{c}',	//定义提示框内容机场名，经纬度
		   trigger: 'item'
	   },
	   bmap: {
		   center: [104, 55],
		   zoom: 5,
		   roam: true,
		   mapStyle: {
			   styleJson: [{
				   'featureType': 'water',
				   'elementType': 'all',
				   'stylers': {
					   'color': '#d1d1d1'
				   }
			   }, {
				   'featureType': 'land',
				   'elementType': 'all',
				   'stylers': {
					   'color': '#f3f3f3'
				   }
			   }, {
				   'featureType': 'railway',
				   'elementType': 'all',
				   'stylers': {
					   'visibility': 'off'
				   }
			   }, {
				   'featureType': 'highway',
				   'elementType': 'all',
				   'stylers': {
					   'color': '#fdfdfd'
				   }
			   }, {
				   'featureType': 'highway',
				   'elementType': 'labels',
				   'stylers': {
					   'visibility': 'off'
				   }
			   }, {
				   'featureType': 'arterial',
				   'elementType': 'geometry',
				   'stylers': {
					   'color': '#fefefe'
				   }
			   }, {
				   'featureType': 'arterial',
				   'elementType': 'geometry.fill',
				   'stylers': {
					   'color': '#fefefe'
				   }
			   }, {
				   'featureType': 'poi',
				   'elementType': 'all',
				   'stylers': {
					   'visibility': 'off'
				   }
			   }, {
				   'featureType': 'green',
				   'elementType': 'all',
				   'stylers': {
					   'visibility': 'off'
				   }
			   }, {
				   'featureType': 'subway',
				   'elementType': 'all',
				   'stylers': {
					   'visibility': 'off'
				   }
			   }, {
				   'featureType': 'manmade',
				   'elementType': 'all',
				   'stylers': {
					   'color': '#d1d1d1'
				   }
			   }, {
				   'featureType': 'local',
				   'elementType': 'all',
				   'stylers': {
					   'color': '#d1d1d1'
				   }
			   }, {
				   'featureType': 'arterial',
				   'elementType': 'labels',
				   'stylers': {
					   'visibility': 'off'
				   }
			   }, {
				   'featureType': 'boundary',
				   'elementType': 'all',
				   'stylers': {
					   'color': '#fefefe'
				   }
			   }, {
				   'featureType': 'building',
				   'elementType': 'all',
				   'stylers': {
					   'color': '#d1d1d1'
				   }
			   }, {
				   'featureType': 'label',
				   'elementType': 'labels.text.fill',
				   'stylers': {
					   'color': '#999999'
				   }
			   }]
		   }
	   },
	   series : [
		   {
			//    name: '坐标',
			   type: 'scatter',
			   coordinateSystem: 'bmap',
			   data: convertData(airdata),
			   //标点size
			   symbolSize: 10,
			   encode: {
				   value: 2
			   },
			   label: {
				   formatter: '{b}',
				   position: 'right',
				   show: false
			   },
			   itemStyle: {
				   color: 'purple'
			   },
			   emphasis: {
				   label: {
					   show: true
				   }
			   }
		   },
	   ]
   };
container.setOption(option,true);	
// console.log(convertData(airdata))
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















