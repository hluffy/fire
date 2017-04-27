$(function () {
	var name = getCookie("username");
	console.log(name);
	$("#admin_name").html("欢迎："+name);
});

function out(){
	console.log("out");
	delCookie("username");
	console.log("delCookie-username");
	delCookie("token");
	console.log("delCookie-token");
	location.href="login.html";
	return;
}