var province1, county1, district1;	//省市区


$(function () {
	login();
	img();
	img2();
	userinfo();
})

//查询用户所有信息

function userinfo() {
	//	var $ = layui.jquery, form = layui.form;
	var id = sessionStorage.getItem("id");
	console.log(id)
	$.ajax({
		//请求类型
		type: "post",
		//请求路径
		url: "/apiz/register_center/info",
		//请求参数
		data: {
			id: id,
		},
		//返回数据类型
		dataType: "json",
		//请求成功后调用函数
		success: function (data) {
			userinfo = data.userinfo;
			console.log(userinfo);
			$(".username").val(userinfo.userName);
			$(".cellphone").val(userinfo.cellphone);
			$(".born").val(userinfo.born.substring(0, 10));
			//性别
			$("input[name=sex][value='1']").attr("checked", userinfo.gender == "1" ? true : false);
			$("input[name=sex][value='2']").attr("checked", userinfo.gender == "2" ? true : false);
			province1 = data.province[0];
			county1 = data.county[0];
			district1 = data.district[0];
			//省
			$.ajax({
				type: "post",
				url: "/apiz/register_center/province",
				dataType: "json",
				success: function (data) {
					var province = data.sheng;
					$("#province").html("");
					txt = "";
					txt += `<option value="${province1.id}" class="province" name="province">${province1.name}</option>`
					for (var i = 0; i < province.length; i++) {
						txt += `
	 		                        <option onclick="provinceid('${province[i].id}')"  value="${province[i].id}">${province[i].name}</option>`
					}
					$("#province").append(txt);
					$("#county").html("");
					txt = "";
					txt += `<option value="${county1.id}">${county1.name}</option>`
					$("#county").append(txt);
					$("#district").html("");
					txt = "";
					txt += `<option value="${district1.id}">${district1.name}</option>`
					$("#district").append(txt);


					layui.form.render();		//重新渲染表单
				},
				error: function (data) {
					console.log("失败后返回的数据", data);
				}
			})


		},
		error: function (data) {
			console.log("失败后返回的数据", data);
		}
	})
}




//上传头像
layui.use('upload', function () {
	var $ = layui.jquery
		, upload = layui.upload;
	var id = sessionStorage.getItem("id");
	//普通图片上传
	var uploadInst = upload.render({
		elem: '#test2'
		, url: '/apiz/register_center/upload/'
		, accept: 'images'
		, size: 50000
		, data: {
			id: id,
		}
		, before: function (obj) {

			obj.preview(function (index, file, result) {
				//图片src和样式
				$('#demo1').attr('src', result);
				$('#demo1').attr('style', "width: 200px;height: 200px;border-radius: 200px;");
			});
		}
		, done: function (res) {
			//如果上传失败
			if (res.code > 0) {
				return layer.msg('上传失败');
			}
			//上传成功
			var demoText = $('#demoText');
			demoText.html('<span style="color: #4cae4c;">上传成功</span>');
			//在隐藏输入框src
			var fileupload = $(".image");
			fileupload.attr("value", res.data.src);
			console.log(fileupload.attr("value"));
		}
		, error: function () {
			//演示失败状态，并实现重传
			//            var demoText = $('#demoText');
			//            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
			//            demoText.find('.demo-reload').on('click', function(){
			//                uploadInst.upload();
			//            });
		}
	});


});




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
//头像2
function img2() {
	var userid = sessionStorage.getItem("id");
	var username = sessionStorage.getItem("name");
	$(".checkimg").html("");
	var txt = "";
	txt += `<img id="demo1" src="/apiz/registerheadImg?id=${userid}" onerror="defaultImg(this)" style="
    width: 200px;
    height: 200px;
    border-radius: 200px;">`
	$(".checkimg").append(txt);
}


//Demo表单的
layui.use('form', function () {
	var userid = sessionStorage.getItem("id");
	var form = layui.form;
	//监听提交
	form.on('submit(formDemo)', function (data) {
		//    layer.msg(JSON.stringify(data.field));							//取到表单提交的信息
		console.log(JSON.parse(JSON.stringify(data.field.username)));	//parse转换去掉双引号
		$.ajax({
			type: "post",
			url: "/apiz/register_center/changeUserinfo",
			data: {
				userid: userid,
				username: JSON.parse(JSON.stringify(data.field.username)),
				cellphone: JSON.parse(JSON.stringify(data.field.cellphone)),
				district: JSON.parse(JSON.stringify(data.field.quiz3)),
				born: JSON.parse(JSON.stringify(data.field.born)),
				gender: JSON.parse(JSON.stringify(data.field.sex)),
				images: JSON.parse(JSON.stringify(data.field.images)),
			},
			dataType: "json",
			success: function (data) {
				console.log("成功后返回的数据11", data.msg);
				alert(data.msg)
				location.href = "register_center.html"
			},
			error: function (data) {
				console.log("失败后返回的数据", data);
			}
		})


		return false;
	});
});



//省
//$(function(){
//	
//})

layui.use('form', function () {
	var form = layui.form;
	//监听提交
	form.on("select(province)", function (data) {
		$.ajax({
			type: "post",
			url: "/apiz/register_center/province",
			dataType: "json",
			success: function (data) {
				var province = data.sheng;
				console.log("成功后返回的数据", province, district);
				$("#province").html("");
				txt = "";
				txt += `<option value="" class="province">省</option>`
				for (var i = 0; i < province.length; i++) {
					txt += `
                        <option onclick="provinceid('${province[i].id}')"  value="${province[i].id}">${province[i].name}</option>`
				}
				$("#province").append(txt);
				$("#county").html("");
				txt = "";
				txt += `<option value="">市</option>`
				$("#county").append(txt);
				$("#district").html("");
				txt = "";
				txt += `<option value="">区</option>`
				$("#district").append(txt);
			},
			error: function (data) {
				console.log("失败后返回的数据", data);
			}
		})
	})
});
//市
layui.use('form', function () {
	var form = layui.form;
	//监听提交
	form.on("select(province)", function (data) {
		var id = data.value;			//获取省id
		$.ajax({
			type: "post",
			url: "/apiz/register_center/county",
			data: {
				id: id,
			},
			dataType: "json",
			success: function (data) {
				var county = data.shi;
				console.log("市", county)
				$("#county").html("");
				txt = "";
				txt += `<option value="">市</option>`
				for (var i = 0; i < county.length; i++) {
					txt += `
                            <option class="countyid" value="${county[i].id}">${county[i].name}</option>`
				}
				$("#county").append(txt);
				$("#district").html("");
				txt = "";
				txt += `<option value="">区</option>`
				$("#district").append(txt);
				form.render();	//重新渲染
			},
			error: function (data) {
				console.log("失败后返回的数据", data);
			}
		})
	});
});

//区
layui.use('form', function () {
	var form = layui.form;
	//监听提交
	form.on("select(county)", function (data) {
		var id = data.value;
		$.ajax({
			type: "post",
			url: "/apiz/register_center/district",
			data: {
				id: id,
			},
			dataType: "json",
			success: function (data) {
				var district = data.qu;
				$("#district").html("");
				txt = "";
				txt += `<option value="">区</option>`
				for (var i = 0; i < district.length; i++) {
					txt += `
	                        <option class="districtid" value="${district[i].id}">${district[i].name}</option>`
				}
				$("#district").append(txt);
				form.render();	//重新渲染
			},
			error: function (data) {
				console.log("失败后返回的数据", data);
			}
		})
	});
});


//登录判断
function login() {
	var status = sessionStorage.getItem("status");
	if (status != 1) {
		alert("请先登录");
		sessionStorage.clear();
		location.href = "register_login.html"
	}
}

//注意：导航 依赖 element 模块，否则无法进行功能性操作	
layui.use('element', function () {
	var element = layui.element;

	//…
});

layui.use('laydate', function () {
	var laydate = layui.laydate;
	//日期的
	//执行一个laydate实例
	laydate.render({
		elem: '#test1' //指定元素
	});
});