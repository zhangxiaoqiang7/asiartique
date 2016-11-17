// 解析URL中的key值
$(function() {
	// 方法二：
	(function($) {
		$.getUrlParam = function(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null)
				return unescape(r[2]);
			return null;
		}
	})(jQuery);
	// 方法二：
	// var xx = $.getUrlParam('awid');
});
// artwork页面js
$(document)
		.ready(
				function() {
					var artworkid = $.getUrlParam('awid');
					var type = $.getUrlParam('type');
					var artname = $.getUrlParam('artname');
					var artid = $.getUrlParam('artid');
					var alink = '<a href="artist.jsp?artid='+artid+'&type='+type+'">'+artname+'</a>';
					$('#artistName').html(alink);//导航栏中的artist name
					$('#artname').html(artname);// artist name
					// alert(artworkid+': '+type);
					// alert(type);
					if (type == 'allcraftsmen') {
						var request = './user/artifact?id=' + artworkid;// type
																		// =
																		// allcraftsmen
						var imgurl = './images/artifact/';
						$('#artistType').html('<a href="allartist.jsp?type=allcraftsmen">Artifact</a>');
					} else {
						var request = './user/fineart?id=' + artworkid;// type
																		// =
																		// allprofartists
						var imgurl = './images/fineart/';
						$('#artistType').html('<a href="allartist.jsp?type=allprofartists">Fine Art</a>');
					}
					;
					$
							.get(
									request,
									function(data, status) {
										// alert("data: " + data + "\nstatus" +
										// status);
										// document.write(data+"</br>");
										var jsonData = JSON.parse(data);
										if (type == 'allcraftsmen') {
											var artwork = jsonData;// type =
																	// allcraftsmen
										} else {
											var artwork = jsonData;// type =
																	// allprofartists
										}
										;
										/*
										 * 暂时不显示这些信息
										 * $('#aw_about').html(artwork[0].description);//artwork
										 * description
										 * 
										 * $('#price').html(artwork[0].price);//artwork
										 * price
										 * $('#date').html(artwork[0].date);//artwork
										 * date
										 */
										$('#artworkName').html(artwork[0].name);//导航栏的artwork name
										$('#workname').html(artwork[0].name);// artwork
																				// name
										$('#width').html(
												artwork[0].width + ' cm');// artwork
																			// width
										$('#height').html(
												artwork[0].height + ' cm');// artwork
																			// height
										var picnum = artwork[0].picnum;
										for (var i = 1; i < picnum + 1; i++) {
											var url = imgurl + artworkid + '_'
													+ i + '.jpg';
											// alert("a");
											$('.slider-nav')
													.append(
															'<div class="imgslider">\
          <img style="height: 100px; width: auto;" src="'
																	+ url
																	+ '" alt="..."></div>');
										}
										;
										$('.slider-nav').slick({
											slidesToShow : 0,
											slidesToScroll : 1,
											infinite : false,
											// asNavFor: '.slider-for',
											dots : false,
											centerMode : true,
											focusOnSelect : true,
											variableWidth : true
										});
										// jingtai
										var url = imgurl + artworkid + '_' + 1
												+ '.jpg';
										$('.slider-for')
												.append(
														'<div class="jqzoom" id="zoomimg"><img style="height: 400px; width: auto;" src="'
																+ url
																+ '" alt="..." jqimg="'
																+ url
																+ '"></div>');
										$(".jqzoom").jqueryzoom({
											xzoom : 300, // zooming div
															// default
															// width(default
															// width value is
															// 200)
											yzoom : 300, // zooming div
															// default
															// width(default
															// height value is
															// 200)
											offset : -180, // zooming div
															// default
															// offset(default
															// offset value is
															// 10)
											position : "right", // zooming div
																// position(default
																// position
																// value is
																// "right")
											preload : 1, // preload of images
															// :1 by default
											lens : 1
										// lens over the image 1 by default
										});
										// var j =
										// $('.slider-nav').slick('slickCurrentSlide');
										// alert('hello'+j);
										// var isrc =
										// imgurl+artworkid+'_'+1+'.jpg';
										// $('#zoomimg').html('<img
										// style="height: 400px; width: auto;"
										// src="'+isrc+'" alt="..."
										// jqimg="'+isrc+'">');
										$('.slider-nav')
												.on(
														'afterChange',
														function(event, slick,
																currentSlide) {
															// alert(currentSlide);
															// xiu gai dui ying
															// de detail
															var i = currentSlide + 1;
															isrc = imgurl
																	+ artworkid
																	+ '_' + i
																	+ '.jpg';
															$('#zoomimg')
																	.html(
																			'<img style="height: 400px; width: auto;" src="'
																					+ isrc
																					+ '" alt="..." jqimg="'
																					+ isrc
																					+ '">');
														});
									});

					// click contact
					$('#contact').click(function() {
						alert('contact artwork');
						// todo something contact
					});
					// click follow
					$('#follow').click(function() {
						alert('follow artwork');
						// todo something follow
					});
					$("#sticker").stickyfloat({
						duration : 400,
						offsetY : 0
					});// 控制右侧块的移动
				});

// contact for price ， submit触发以后
function contactFunction() {
	var fullname = $("#contact_fullname").val();
	var artworkid = $.getUrlParam('awid');
	var emailaddress = $("#contact_email").val();
	var info = $("#contact_info").text();
	var type = $.getUrlParam('type');
	alert(type);
	if (type == 'allprofartists') {
		$.post("./user/fineart/addMsg", {
			artwork : artworkid,
			name : fullname,
			email : emailaddress,
			msg : info
		}, function(data, status) {
			alert("数据：" + data + "\n状态：" + status);
		});
	}
	if (type == 'allcraftsmen') {
		$.post("./user/artifact/addMsg", {
			artwork : artworkid,
			name : fullname,
			email : emailaddress,
			msg : info
		}, function(data, status) {
			alert("数据：" + data + "\n状态：" + status);
		});
	}
}