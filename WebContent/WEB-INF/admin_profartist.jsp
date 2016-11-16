<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profartist Management Page</title>
<%
	String path = request.getContextPath();
%>
<script src="./js/jquery-1.11.2.min.js">
	
</script>
<script type="text/javascript">
function showLocations(){
	$.get("./user/allLocations?page=0", function(data,
			status) {
		document.getElementById("locationcontent").innerText=data;
	});
}

function showprofartists(){
	$.get("./user/allprofartists/?page=0", function(data,
			status) {
		document.getElementById("profartistcontent").innerText=data;
	});
}
</script>
</head>
<body>

	<h1>添加Profartist</h1>
	<form action="<%=path%>/admin/artist/addProfartist" method="post"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td colspan="2">作者名：<input type="text" name="name"></td>
			</tr>
			<tr>
				<td colspan="2">描述：<input type="text" name="description"></td>
			</tr>
			<tr>
				<td colspan="2">来源地ID：<input type="text" name="location">//下放有获得它的方法</td>
			</tr>
			<tr>
				<td><label for="file">上传照片：</label></td>
				<td><input type="file" id="file" name="picture1" value="" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="添加profartist" /></td>
			</tr>
		</table>
	</form>
	<br>************************
	<h3>所有Location</h3>
	<label><textarea rows="10" cols="80" id="locationcontent"></textarea></label>
	<br>
	<label><button onclick="showLocations()">查看Location</button></label>
	<br>************************

	<h1>所有Profartist</h1>
	<label><textarea rows="10" cols="80" id="profartistcontent"></textarea></label>
	<br>
	<label><button onclick="showprofartists()">查看Profartist</button></label>
	
	<h1>删除profartist</h1>
	<form action="<%=path%>/admin/artist/delProfartist">
		<table>
			<tr>
				<td colspan="2">Profartist ID：<input type="text" name="id"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="删除profartist" /></td>
			</tr>
		</table>
		
	</form>
	
</body>
</html>