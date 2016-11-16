showArticles();
function showArticles () {
	$('#articles').append('<div class="media">');
	for (var i = 5 - 1; i >= 0; i--) {
		$('#articles').append('<p><div class = "media-left media-top"><a href = "' + 'aaa" > <img class\
			= "media-object" src=" /' + i + '.jpg" alt ="..."> </a> </div><div class = "media-body">\
			<h4 class="media-heading">' + 'hahahahhahahahahahha' + '</h4><p>' + "introduction to this article" + '</p></div></p>')
	}
	
    $('#articles').append('</div>');
}


