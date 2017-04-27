$(function(){
	$('#login').click(loginAction);
	$('#username').focus().blur(checkName);
	$('#password').blur(checkPassword);


});

function loginAction(){
	console.log('login click!');
	//收集用户名和密码数据
	var name = $('#username').val();
	var password = $("#password").val();
	//验证用户名和密码
	var pass = checkName()+checkPassword();
	if(pass != 2){
		return;
	}
	var paramter = {'username':name,'password':password};
	$.ajax({ 
		url: 'admin/login.do',
		data: paramter,
		dataType: "json",
		type: "POST",
		success: function(result) {
			console.log("------");
			console.log(result);
			if(result.state==0){
				var user = result.data;
				console.log(user.username);
				setCookie('username',user.username);
				location.href="index.html";
				return;
			}else if(result.state==2){
				//用户名错误
				$('#count-msg').html(result.message);
				return;
			}else if(result.state==3){
				//密码错误
				$('#password-msg').html(result.message);
				return;
			}
			alert(result.message);
		},
		error:function(){
			alert('Ajax请求失败');
		}
	});
}

function checkName(){
	var name = $('#username').val();
	if(name == null || name == ""){
		//提示错误
		$('#count-msg').html("用户名不能为空");
		return false;
	}
	var reg = /^\w{3,10}$/;
	if(!reg.test(name)){
		$('#count-msg').html("输入3-10个字母或数字或下划线");
		return false;
	}
	$('#count-msg').empty();
	return true;
}

function checkPassword(){
	var password = $('#password').val();
	if(password == null || password == ""){
		//提示错误
		$('#password-msg').html("密码不能为空");
		return false;
	}
	var reg = /^\w{3,10}$/;
	if(!reg.test(password)){
		$('#password-msg').html("输入3-10个字母或数字或下划线");
		return false;
	}
	$('#password-msg').empty();
	return true;
}