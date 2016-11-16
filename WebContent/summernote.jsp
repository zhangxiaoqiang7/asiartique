<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
		<!-- include libraries(jQuery, bootstrap) -->
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 

<!-- include summernote css/js-->
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.2/summernote.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
  		$('#summernote').summernote();
	});

	$('#summernote').summernote({
  		height: 300,                 // set editor height
 	    minHeight: null,             // set minimum height of editor
   		maxHeight: null,             // set maximum height of editor
  		focus: true                  // set focus to editable area after initializing summernote
  	});
  	function submit() {
  		var markupStr = $('#summernote').summernote('code');
  		alert(markupStr);
  	}
</script>
<style>
.button {
  padding: 10px 20px;
  font-size: 14px;
  text-align: center;
  cursor: pointer;
  outline: none;
  color: #fff;
  background-color: #4CAF50;
  border: none;
  border-radius: 15px;
  box-shadow: 0 9px #999;
}

.button:hover {background-color: #3e8e41}

.button:active {
  background-color: #3e8e41;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}
</style>
</head>
<body>
	<div id="summernote">Hello Summernote</div>
	<button onclick="submit()"  class="button"> Submit</button>

</body>
</html>
