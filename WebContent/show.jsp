<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <script>
		var cookie = document.cookie;
		var arrCookie = cookie.split("; ");
		var userId;
		//遍历cookie数组，处理每个cookie对
		for (var i = 0; i < arrCookie.length; i++) {
			var arr = arrCookie[i].split("=");
			//找到名称为userId的cookie，并返回它的值
			//if ("email" == arr[0]) {
				alert(arr[1]);
				//break;
			//}
		}
	</script>
    </body>
</html>