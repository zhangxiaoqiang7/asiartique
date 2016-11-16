<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fineart Management Page</title>
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

function showProfartists(){
	$.get("./user/allprofartists/?page=0", function(data,
			status) {
		document.getElementById("profartistcontent").innerText=data;
	});
}

function showFinearts(){
	$.get("./user/allfinearts/?page=0", function(data,
			status) {
		document.getElementById("fineartcontent").innerText=data;
	});
}

function addFineart(){
	var picnum=0;
	for(var i=1;i<5;i++){
		var file=document.getElementById("file"+i);
		if(file.value.length!=0) picnum=picnum+1;
	}
	//alert(picnum);
	//alert(document.getElementById("picnum").value);
	document.getElementById("picnum").value=picnum;
	document.getElementById("fineart").submit();
}

function modifyFineart(){
	
}
</script>
</head>
<body>

	<h1>添加Fineart</h1>
	<form action="<%=path%>/admin/artwork/addFineart" method="post"
		enctype="multipart/form-data" id="fineart">
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
				<td colspan="2">宽度：<input type="text" name="height"></td>
			</tr>
			<tr>
				<td colspan="2">高度：<input type="text" name="width"></td>
			</tr>
			<tr>
				<td colspan="2">完成时间：<input type="text" name="date">//输入格式：2016-11-5</td>
			</tr>
			<tr>
				<td colspan="2">颜色：<input type="text" name="color"></td>
			</tr>
			<tr>
				<td colspan="2">类别：<input type="text" name="kinds"></td>
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
	<button onclick="addFineart()">添加fineart</button>
	<br>************************
	<h3>所有Location</h3>
	<label><textarea rows="10" cols="80" id="locationcontent"></textarea></label>
	<br>
	<label><button onclick="showLocations()">查看Location</button></label>
	<br>
	<h3>所有profartist</h3>
	<label><textarea rows="10" cols="80" id="profartistcontent"></textarea></label>
	<br>
	<label><button onclick="showProfartists()">查看profartist</button></label>
	<br>************************

	<h1>所有fineart</h1>
	<label><textarea rows="10" cols="80" id="fineartcontent"></textarea></label>
	<br>
	<label><button onclick="showFinearts()">查看fineart</button></label>
	
	<h1>修改fineart</h1>
	<form action="<%=path%>/admin/artwork/modifyFineart" method="post"
		enctype="multipart/form-data" id="modifyFineart">
		<table>
			<tr>
				<td colspan="2">作品名称：<input type="text" name="name"></td>
			</tr>
			<tr>
				<td colspan="2">作品ID：<input type="text" name="fineart"></td>
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
				<td colspan="2">宽度：<input type="text" name="height"></td>
			</tr>
			<tr>
				<td colspan="2">高度：<input type="text" name="width"></td>
			</tr>
			<tr>
				<td colspan="2">完成时间：<input type="text" name="date">//输入格式：2016-11-5</td>
			</tr>
			<tr>
				<td colspan="2">颜色：<input type="text" name="color"></td>
			</tr>
			<tr>
				<td colspan="2">类别：<input type="text" name="kind"></td>
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
	<!-- <button onclick="modifyFineart()">添加fineart</button> -->
	
	<h1>删除fineart</h1>
	<form action="<%=path%>/admin/artwork/delFineart">
		<table>
			<tr>
				<td colspan="2">Artifact ID：<input type="text" name="id"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="删除fineart" /></td>
			</tr>
		</table>
		
	</form>
	
</body>
</html>