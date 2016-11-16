<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Article Management Page</title>
<%
	String path = request.getContextPath();
%>
<script src="./js/jquery-1.11.2.min.js">
	
</script>
<script type="text/javascript">
	function turn(object) {
		$.get("./"+object, function(data, status) {
			document.write(data);
		});
	}
</script>
</head>
<body>

	<h1>后台管理员</h1>
	<table>
		<tr>
			<td colspan="2"><form action="./article.do"><input type="submit"/ value="管理article"></form></td>
		</tr>
		<tr>
			<td colspan="2"><form action="./location.do"><input type="submit"/ value="管理location"></form></td>
		</tr>
		<tr>
			<td colspan="2"><form action="./profartist.do"><input type="submit"/ value="管理profartist"></form></td>
		</tr>
		<tr>
			<td colspan="2"><form action="./craftsman.do"><input type="submit"/ value="管理craftsman"></form></td>
		</tr>
		<tr>
			<td colspan="2"><form action="./fineart.do"><input type="submit"/ value="管理fineart"></form></td>
		</tr>
		<tr>
			<td colspan="2"><form action="./artifact.do"><input type="submit"/ value="管理artifact"></form></td>
		</tr>
		<tr>
			<td colspan="2"><form action="./message.do"><input type="submit"/ value="管理消息"></form></td>
		</tr>
	</table>
</body>
</html>