<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Artifact Management Page</title>
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

function showCraftsmen(){
	$.get("./user/allcraftsmen/?page=0", function(data,
			status) {
		document.getElementById("craftsmancontent").innerText=data;
	});
}

function showArtifacts(){
	$.get("./user/allartifacts/?page=0", function(data,
			status) {
		document.getElementById("artifactcontent").innerText=data;
	});
}

function addArtifact(){
	var picnum=0;
	for(var i=1;i<5;i++){
		var file=document.getElementById("file"+i);
		if(file.value.length!=0) picnum=picnum+1;
	}
	//alert(picnum);
	//alert(document.getElementById("picnum").value);
	document.getElementById("picnum").value=picnum;
	document.getElementById("artifact").submit();
}

</script>
</head>
<body>

	<h1>添加Artifact</h1>
	<form action="<%=path%>/admin/artwork/addArtifact" method="post"
		enctype="multipart/form-data" id="artifact">
		<table>
			<tr>
				<td colspan="2">作品名称：<input type="text" name="name"></td>
			</tr>
			<tr>
				<td colspan="2">描述：<input type="text" name="description"></td>
			</tr>
			<tr>
				<td colspan="2">作者ID：<input type="text" name="artist"></td>
			</tr>
			<tr>
				<td colspan="2">来源地ID：<input type="text" name="location">//下放有获得它的方法</td>
			</tr>
			<tr>
				<td colspan="2">价格：<input type="text" name="price">//下放有获得它的方法</td>
			</tr>
			<tr>
				<td colspan="2">标签：<input type="text" name="tags">//输入格式：tag1,tag2,tag3(英文,)</td>
			</tr>
			<tr>
				<td colspan="2">链接：<input type="text" name="link">//下放有获得它的方法</td>
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
	<button onclick="addArtifact()">添加Artifact</button>
	<br>************************
	<h3>所有Location</h3>
	<label><textarea rows="10" cols="80" id="locationcontent"></textarea></label>
	<br>
	<label><button onclick="showLocations()">查看Location</button></label>
	<br>
	<h3>所有craftsman</h3>
	<label><textarea rows="10" cols="80" id="craftsmancontent"></textarea></label>
	<br>
	<label><button onclick="showCraftsmen()">查看Craftsmen</button></label>
	<br>************************

	<h1>所有Artifact</h1>
	<label><textarea rows="10" cols="80" id="artifactcontent"></textarea></label>
	<br>
	<label><button onclick="showArtifacts()">查看Artifact</button></label>
	
	<h1>修改Artifact</h1>
	<form action="<%=path%>/admin/artwork/modifyArtifact" method="post"
		enctype="multipart/form-data" id="modifyArtifact">
		<table>
			<tr>
				<td colspan="2">作品ID：<input type="text" name="artifact"></td>
			</tr>
			<tr>
				<td colspan="2">作品名称：<input type="text" name="name"></td>
			</tr>
			<tr>
				<td colspan="2">描述：<input type="text" name="description"></td>
			</tr>
			<tr>
				<td colspan="2">作者ID：<input type="text" name="artist"></td>
			</tr>
			<tr>
				<td colspan="2">来源地ID：<input type="text" name="location">//下放有获得它的方法</td>
			</tr>
			<tr>
				<td colspan="2">价格：<input type="text" name="price">//下放有获得它的方法</td>
			</tr>
			<tr>
				<td colspan="2">标签：<input type="text" name="tags">//输入格式：tag1,tag2,tag3(英文,)</td>
			</tr>
			<tr>
				<td colspan="2">链接：<input type="text" name="link"></td>
			</tr>
			<tr>
				<td colspan="2">是否卖出y/n：<input type="text" name="sold"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="submit" value="提交修改"></td>
			</tr>
		</table>
		<input type="hidden" id="picnum" name="picnum" value="0">
	</form>
	
	<h1>删除Artifact</h1>
	<form action="<%=path%>/admin/artwork/delArtifact">
		<table>
			<tr>
				<td colspan="2">Artifact ID：<input type="text" name="id"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="删除Artifact" /></td>
			</tr>
		</table>
		
	</form>
	
</body>
</html>