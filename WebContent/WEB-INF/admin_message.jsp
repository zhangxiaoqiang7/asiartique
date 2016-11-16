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
	function showFinearts() {
		$.get("./user/allfinearts/?page=0", function(data, status) {
			document.getElementById("fineartcontent").innerText = data;
		});
	}
	function showallmessages() {
		var type = document.getElementById('finearts').checked;
		if (type) {
			$.get("./admin/msgs?type=1", function(data, status) {
				document.getElementById("artworksmsg").innerText = data;
			});
		} else {
			$.get("./admin/msgs?type=2", function(data, status) {
				document.getElementById("artworksmsg").innerText = data;
			});
		}
	}
	function showmessage() {
		var type = document.getElementById('fineart').checked;
		var artwork=document.getElementById('artworkid1').value;
		if (type) {
			$.get("./admin/fineart/getMsg?artwork="+artwork, function(data, status) {
				document.getElementById("artworkmsg").innerText = data;
			});
		} else {
			$.get("./admin/artifact/getMsg?artwork="+artwork, function(data, status) {
				document.getElementById("artworkmsg").innerText = data;
			});
		}
	}
	function delmessage(){
		var type=document.getElementById('fineart2').checked;
		var artwork=document.getElementById('artworkid2').value;
		var email=document.getElementById("email").value;
		if (type) {
			$.get("./admin/fineart/delMsg?artwork="+artwork+"&email="+email, function(data, status) {
				alert(data);
			});
		} else {
			$.get("./admin/artifact/delMsg?artwork="+artwork+"&email="+email, function(data, status) {
				alert(data);
			});
		}
	}
</script>
</head>
<body>
	<h1>所有artwork相关的留言</h1>
	<label><textarea rows="10" cols="80" id="artworksmsg"></textarea></label>
	<br>
	<form>
		<input type="radio" name="artworks" id="finearts" checked />fineart <input
			type="radio" name="artworks" id="artifacts" />artifact
	</form>
	<label><button onclick="showallmessages()">查看留言</button></label>

	<h1>某个artwork相关的留言</h1>
	<label><textarea rows="10" cols="80" id="artworkmsg"></textarea></label>
	<br>
	<form>
		<input type="radio" name="artwork" id="fineart" checked />fineart <input
			type="radio" name="artwork" id="artifact" />artifact
		<br>
		Message ID: <input type="text" name="artwork" id="artworkid1">
	</form>
	<label><button onclick="showmessage()">查看留言</button></label>

	<br>************************
	<h1>所有fineart</h1>
	<label><textarea rows="10" cols="80" id="fineartcontent"></textarea></label>
	<br>
	<label><button onclick="showFinearts()">查看fineart</button></label>
	<br>************************

	<h1>删除留言</h1>
	<form>
		<table>
			<tr>
				<td colspan="2">Message ID:<input type="text" id="artworkid2"></td>
			</tr>
			<tr>
				<td colspan="2">Email:<input type="text" id="email"></td>
			</tr>
			<tr>
				<td colspan="2">
						<input type="radio" name="artwork2" id="fineart2" value="" checked />fineart <input
							type="radio" name="artwork2" id="artifact2" value="" />artifact
				</td>
			</tr>
		</table>
	</form>
	<label><button onclick="delmessage()">删除留言</button></label><br><br><br>
</body>
</html>