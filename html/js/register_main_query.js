var unit="吨";
var pagefirst=1;
var pagenow="1";
var pagemax=1;
var selectdata;


$(function () {
	login();
	img();
	paging();
	selectfreight();
})

$("#freigth").on("click", function () {
	console.log("ok")
	$('#airportname').val("")
	$('#yearstart').val("")
	$('#yearend').val("")
	$('#monthstart').val("")
	$('#monthend').val("")
	pagenow=1;
	selectfreight()
	paging()
})

$("#passenger").on("click", function () {
	console.log("ok")
	$('#airportname').val("")
	$('#yearstart').val("")
	$('#yearend').val("")
	$('#monthstart').val("")
	$('#monthend').val("")
	pagenow=1;
	selectpassenger()
	paging()
})



//查询货运机场
function selectfreight() {
	$.ajax({
		async: false,
		//请求类型
		type: "post",
		//请求路径
		url: "/apiz/airquery/airfreight",
		//请求参数
		data: {
			airportname:$('#airportname').val(),
			yearstart:$('#yearstart').val(),
			yearend:$('#yearend').val(),
			monthstart:$('#monthstart').val(),
			monthend:$('#monthend').val(),
			page:pagenow,
		},
		//返回数据类型
		dataType: "json",
		//请求成功后调用函数
		success: function (data) {
			console.log("成功后返回的数据111", data);
			unit="吨";
			selectdata=data.airfreight;
			pagemax=data.pagemax;
			console.log(selectdata);
			table();
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
}

//查询客运机场
function selectpassenger() {
	$.ajax({
		async: false,
		//请求类型
		type: "post",
		//请求路径
		url: "/apiz/airquery/airpassenger",
		//请求参数
		data: {
			airportname:$('#airportname').val(),
			yearstart:$('#yearstart').val(),
			yearend:$('#yearend').val(),
			monthstart:$('#monthstart').val(),
			monthend:$('#monthend').val(),
			page:pagenow,
		},
		//返回数据类型
		dataType: "json",
		//请求成功后调用函数
		success: function (data) {
			console.log("成功后返回的数据111", data);
			unit="人次";
			selectdata=data.airpassenger;
			pagemax=data.pagemax;
			console.log(selectdata);
			table();
		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
}


//变换表格
function table(){
	$("#tbody").html("");
	var txt = "";
	for(var i=0;i<selectdata.length;i++){
		txt += `<tr>
		<td>${i+1}</td>
		<td>${selectdata[i].airportname}</td>
		<td>${selectdata[i].year}</td>
		<td>${selectdata[i].month}</td>
		<td>${selectdata[i].value}</td>
		<td>${unit}</td>
	 	</tr>`
	}
	$("#tbody").append(txt);
}


//点击搜索
$("#selectbtn").on("click", function () {
	console.log("ok")
	pagenow=1;
	selectfreight()
	paging()
})


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


//分页
function paging(){
	// selectfreight();
	layui.use('laypage', function(){
		var laypage = layui.laypage;
		//执行一个laypage实例
		laypage.render({
			elem: 'test1'
			,count: pagemax //数据总数，从服务端得到
			,limit:10
			,layout:["count","prev","page","next","skip"]
			,jump: function(obj, first){
			  //obj包含了当前分页的所有参数，比如：
			  console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
			  console.log(obj.limit); //得到每页显示的条数
			  //首次不执行
			  if(!first){
				//do something
				pagenow=obj.curr;
				console.log("当前第几页",pagenow)
				if(unit=="吨"){
				selectfreight();}
				else{
					selectpassenger();
				}
			  }
			}
		  });
	  });
}
