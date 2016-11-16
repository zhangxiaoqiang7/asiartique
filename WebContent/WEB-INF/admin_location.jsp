<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Location Management Page</title>
<%
	String path = request.getContextPath();
%>
<script src="./js/jquery-1.11.2.min.js">
	
</script>
<script type="text/javascript">
var picnum=0;
function showLocations(){
	$.get("./user/allLocations?page=0", function(data,
			status) {
		document.getElementById("locationcontent").innerText=data;
	});
}
function addAnfile(){
	if(picnum==4) {
		alert("到达最大上传数目");
		return;
	}
	var input = document.createElement('input');  //创建input节点
	input.setAttribute('type', 'file');  //定义类型是文本输入
	document.getElementById('location').appendChild(input ); //添加到form中显示
	picnum=picnum+1;
}
function testContent(obj){
	if(document.getElementById(obj).value.length==0){
		alert('不能为空');
		}
	}
function addLocation(){
	var picnum=0;
	for(var i=1;i<5;i++){
		var file=document.getElementById("file"+i);
		if(file.value.length!=0) picnum=picnum+1;
	}
	//alert(picnum);
	//alert(document.getElementById("picnum").value);
	document.getElementById("picnum").value=picnum;
	document.getElementById("location").submit();
}

</script>
</head>
<body>

	<h1>添加Location</h1>
	<form action="<%=path%>/admin/location/addLocation" method="post"
		enctype="multipart/form-data" id="location">
		<table>
			<tr>
				<td colspan="2">地名：<input type="text" name="name"></td>
			</tr>
			<tr>
				<td colspan="2">描述：<input type="text" name="description"></td>
			</tr>
			<tr>
				<td><label for="file">上传图片1：</label></td>
				<td><input type="file" id="file1" name="picture1" value="" /></td>
			</tr>
			<tr>
				<td><label for="file">上传图片2：</label></td>
				<td><input type="file" id="file2" name="picture2" value="" /></td>
			</tr>
			<tr>
				<td><label for="file">上传图片3：</label></td>
				<td><input type="file" id="file3" name="picture3" value="" /></td>
			</tr>
			<tr>
				<td><label for="file">上传图片4：</label></td>
				<td><input type="file" id="file4" name="picture4" value="" /></td>
			</tr>
		</table>
		<input type="hidden" id="picnum" name="picnum" value="0">
	</form>
	<button onclick="addLocation()">添加Location</button>
	
	

	<h1>所有Location</h1>
	<label><textarea rows="10" cols="80" id="locationcontent"></textarea></label>
	<br>
	<label><button onclick="showLocations()">查看Location</button></label>
	
	<h1>删除Location</h1>
	<form action="<%=path%>/admin/location/delLocation">
		<table>
			<tr>
				<td colspan="2">Location ID：<input type="text" name="id"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="删除Location" /></td>
			</tr>
		</table>
	</form>
	
</body>
</html>