var jps1=new Array();
var jps2=new Array();
var local;
var post=sessionStorage.getItem("post");
var status=0;

$(function(){
	sessionStorage.setItem("ip",50);
	var status=0;
	img();
	jps();
})



function jps(){
	local="192.168.1."+sessionStorage.getItem("ip");
	post=sessionStorage.getItem("post");
	var command="bash ~/sh/webskjps.sh";
	$.ajax({
		// 请求类型
		type:"post",
		// 请求路径
		url:"monitor_main/linklinux",
		// 请求参数
		data:{
			local:local,
			command:command,
		},
		// 返回数据类型
		dataType:"json",
		// 请求成功后调用函数
		success:function(data){
			console.log("成功后返回的数据",data);
			status=data.status;
			linkjps();
	 	},
		error:function(data){
			console.log("失败后返回的数据",data);

		}
	})

}

function linkjps(){
	var i=0;
	var ws = new WebSocket('ws://'+local+':'+post+'/');
//	console.log("111",ws)
	var linux=new Array()	// 存linux拿到的信息
	
	// 获取linux信息
	ws.onmessage = function(event) {
	linux[i]=event.data;
	var a=linux[i].split(" ");
	console.log("874654",a[1])
		jps1[i]=a[0];
		jps2[i]=a[1];
		console.log(a[0]);
		console.log(a[1]);
		tbody();
		i=i+1;
	}
}




function tbody(){
	if(status=='-1'){
	var txt="";
	for(var i=0;i<jps1.length;i++){
	$(".jpstbody").html("");
	txt +=` <tr>
      <td>${jps2[i]}</td>
      <td>${jps1[i]}</td>
      <td><button type="button" class="layui-btn layui-btn-lg layui-btn-danger" onclick="kill(${jps1[i]})">关闭进程</button></td>
    </tr>`
	$(".jpstbody").append(txt);
	}
	}
	if(status=='0'){
		var txt="";
		$(".jpstbody").html("");
		txt +=` <tr>
	      <td>没有连接到主机</td>
	      <td>没有连接到主机</td>
	      <td><button type="button" class="layui-btn layui-btn-lg layui-btn-danger">关闭进程</button></td>
	    </tr>`
		$(".jpstbody").append(txt);
	}
}

function kill(port){
	console.log(port);
	var command="kill -9 "+port;
	console.log(command);
	$.ajax({
		// 请求类型
		type:"post",
		// 请求路径
		url:"monitor_main/linklinux",
		// 请求参数
		data:{
			local:local,
			command:command,
		},
		// 返回数据类型
		dataType:"json",
		// 请求成功后调用函数
		success:function(data){
			console.log("成功后返回的数据",data);
			location.href="redirect?page=monitor_service"
	 	},
		error:function(data){
			console.log("失败后返回的数据",data);
//			location.href="redirect?page=monitor_service"
		}
	})
}

function change(id,obj){
	status=0;
	tbody();
	sessionStorage.setItem("ip",id);
	jps();

	$('.layui-this').removeClass('layui-this');	//样式改变
	$(obj).addClass("layui-this");
}



// 显示头像
function img(){
	var userid=sessionStorage.getItem("id");
	var username=sessionStorage.getItem("name");
	$(".user").html("");
	var txt="";
	txt +=`<img src="/headImg?id=${userid}" onerror="defaultImg(this)" style="
    width: 40px;
    height: 40px;
    border-radius: 50px;">${username}`
	$(".user").append(txt);
}
function defaultImg(img){
		img.src="images/user-lg.png";
}

