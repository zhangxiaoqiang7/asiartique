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
function showarticle(){
	$.get("./user/allarticles?page=0", function(data,
			status) {
		document.getElementById("articlecontent").innerText=data;
	});
}
</script>
</head>
<body>

	<h1>添加文章</h1>
	<form action="<%=path%>/admin/article/addArticle" method="post"
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

	<h1>所有文章</h1>
	<label><textarea rows="10" cols="80" id="articlecontent"></textarea></label>
	<br>
	<label><button onclick="showarticle()">查看文章</button></label>
	
	<h1>删除文章</h1>
	<form action="<%=path%>/admin/article/delArticle">
		<table>
			<tr>
				<td colspan="2">Article ID：<input type="text" name="id"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="删除文章" /></td>
			</tr>
		</table>
	</form>
	
</body>
</html>