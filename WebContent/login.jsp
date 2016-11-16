<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login</title>
<%
	String path = request.getContextPath();
%>
<script src="./js/jquery.min.js">
	
</script>
</head>
<body>
	<script>
		function login() {
			$.get("./testlogin?email=123@qq.com&password=0000", function(data,
					status) {
				alert("登录成功");
			});
		}
		function f() {
			var cookie = document.cookie;
			if (cookie.length <= 0) {
				alert("请先登录");
			} else {
				alert("？？");
				var arrCookie = cookie.split(";");
				var email;
				var password;
				for (var i = 0; i < arrCookie.length; i++) {
					var arr = arrCookie[i].split("=");
					if ("email" == arr[0]) {
						email = arr[1];
					}
					if ("password" == arr[0]) {
						password = arr[1];
					}
				}
				$.post("./testlogin", {
					email : email,
					password : password
				}, function(data, status) {
					document.write("免密码登陆了");
				});
			}
		}
		function upload() {
			$.get("./user/article?email=123@qq.com&password=0000", function(
					data, status) {
				alert("登录成功");
			});
		}
	</script>
	<button onclick="f()">需要登录的操作</button>
	<form action="<%=path%>/user/article/addArticle" method="post"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td><label for="file">上传文件：</label></td>
				<td><input type="file" id="file" name="picture2" value="" /></td>
			</tr>
			<tr>
				<td colspan="2">发布者：<input type="text" name="author"></td>
			</tr>
			<tr>
				<td colspan="2">题目：<input type="text" name="title"></td>
			</tr>
			<tr>
				<td colspan="2">发布时间：<input type="text" name="date"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="上传文章" /></td>
			</tr>
		</table>
	</form>
	<!-- <button onclick="login()">登录</button> -->

</body>
</html>