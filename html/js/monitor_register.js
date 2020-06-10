//载入时生成图片验证码
$(function() {
	var img = document.getElementsByClassName("checkimg")[0];
	img.src = "/apiz/imgGetCode";
})
//切换验证码
function imgChange() {
	var img = document.getElementsByClassName("checkimg")[0];
	var time = new Date().getTime();
	img.src = "/apiz/imgGetCode?t=" + time;
}

$(function() {
//省
	$.ajax({
		type: "post",
		url: "/apiz/monitor_center/sheng",
		dataType: "json",
		success: function(data){
			console.log(data.sheng[1].id)
			var province = data.sheng;
			$("#province").html("");
			txt="";
//			txt +=`<option value="${province.id}" class="province" name="province">${province.name}</option>`
			for(var i = 0;i<province.length;i++){
				txt +=`
                     <option onclick="provinceid('${province[i].id}')"  value="${province[i].id}">${province[i].name}</option>`
			}
			$("#province").append(txt);
			$("#county").html("");
			txt="";
			txt +=`<option value="110100">北京市</option>`
			$("#county").append(txt);
			$("#district").html("");
			txt="";
			txt +=`<option value="110101">东城区</option>`
			$("#district").append(txt);
				
		layui.form.render();		//重新渲染表单
		},
		error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
})
//Demo
layui.use('form', function(){
  var form = layui.form;
  //监听提交
  form.on('submit(formDemo)', function(data){
console.log(465879);
		$.ajax({
			type: "post",
			url: "/apiz/monitor_register/register",
			//数据传入后端
			data:{
				name:JSON.parse(JSON.stringify(data.field.name)),
				cellphone:JSON.parse(JSON.stringify(data.field.cellphone)),
				password:JSON.parse(JSON.stringify(data.field.password)),
				password1:JSON.parse(JSON.stringify(data.field.password1)),
				code:JSON.parse(JSON.stringify(data.field.code)),
				district:JSON.parse(JSON.stringify(data.field.quiz3)),
			},
			dataType: "json",
			success: function(data){
				console.log("成功后返回的数据",data);
//				//如果成功，弹出注册成功，跳转到登录页，失败弹窗
				if(data.code==1){
				alert(data.msg);
				location.href="monitor_login.html"
				}else{
					alert(data.msg);
					imgChange();
				}
//				alert(data.msg);
			},
			error: function(data){
				console.log("失败后返回的数据",data);
			}
		})
		 return false;
  });
});

////点击注册后
//$(".primary_button").on("click", function(){
//	//取到前端输入的数据
//	var name=$(".name").val();
//	var cellphone=$(".cellphone").val();
//	var password=$(".password").val();
//	var password1=$(".password1").val();
//	var code=$(".code").val();
//	console.log("手机号==",name,cellphone,password,password1,code);
//	$.ajax({
//		type: "post",
//		url: "/apiz/monitor_register/register",
//		//数据传入后端
//		data:{
//			name:name,
//			cellphone:cellphone,
//			password:password,
//			password1:password1,
//			code:code,
//		},
//		dataType: "json",
//		success: function(data){
//			console.log("成功后返回的数据",data);
//			//如果成功，弹出注册成功，跳转到登录页，失败弹窗
//			if(data.code==1){
//			alert(data.msg);
//			location.href="redirect?page=monitor_login"
//			}else{
//				alert(data.msg);
//			}
////			alert(data.msg);
//		},
//		error: function(data){
//			console.log("失败后返回的数据",data);
//		}
//	})
//})




layui.use('form', function(){
	var form = layui.form;
  //监听提交
	form.on("select(province)", function(data){
	$.ajax({
		type: "post",
		url: "/apiz/monitor_center/sheng",
		dataType: "json",
		success: function(data){
			var province = data.sheng;
			console.log("成功后返回的数据",province,district);
			$("#province").html("");
			txt="";
			txt +=`<option value="" class="province">省</option>`
			for(var i = 0;i<province.length;i++){
				txt +=`
                        <option onclick="provinceid('${province[i].id}')"  value="${province[i].id}">${province[i].name}</option>`
			}
			$("#province").append(txt);
			$("#county").html("");
			txt="";
			txt +=`<option value="">市</option>`
			$("#county").append(txt);
			$("#district").html("");
			txt="";
			txt +=`<option value="">区</option>`
			$("#district").append(txt);
		},
		error: function(data){
			console.log("失败后返回的数据",data);
		}
	})
})
});
//市
layui.use('form', function(){
	var form = layui.form;
  //监听提交
	form.on("select(province)", function(data){
        var id =data.value;			//获取省id
        $.ajax({
    		type: "post",
    		url: "/apiz/monitor_center/shi",
    		data:{
    			id:id,
    		},
    		dataType: "json",
    		success: function(data){
    			var county = data.shi;
    			console.log("市",county)
    			$("#county").html("");
    			txt="";
    			txt +=`<option value="">市</option>`
    			for(var i = 0;i<county.length;i++){
    				txt +=`
                            <option class="countyid" value="${county[i].id}">${county[i].name}</option>`
    			}
    			$("#county").append(txt);
    			$("#district").html("");
    			txt="";
    			txt +=`<option value="">区</option>`
    			$("#district").append(txt);
    			form.render();	//重新渲染
    		},
    		error: function(data){
    			console.log("失败后返回的数据",data);
    		}
    	})
});
});

//区
layui.use('form', function(){
	var form = layui.form;
  //监听提交
	form.on("select(county)", function(data){  
		var id = data.value;
		$.ajax({
			type: "post",
			url: "/apiz/monitor_center/qu",
			data:{
				id:id,
			},
			dataType: "json",
			success: function(data){
				var district = data.qu;
				$("#district").html("");
				txt="";
				txt +=`<option value="">区</option>`
				for(var i = 0;i<district.length;i++){
					txt +=`
	                        <option class="districtid" value="${district[i].id}">${district[i].name}</option>`
				}
				$("#district").append(txt);
				form.render();	//重新渲染
			},
			error: function(data){
				console.log("失败后返回的数据",data);
			}
		})
	});
});