// 解析URL中的key值
$(function () {
    //方法二：
    (function ($) {
        $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
      }
    })(jQuery);
    //方法二：
    // var xx = $.getUrlParam('awid');
});
// artist页面js
$(document).ready(function(){
      //请求一个artist的所有信息
      var artistid = $.getUrlParam('artid');
      // alert(artistid);
      var type = $.getUrlParam('type');
      if(type == 'allcraftsmen'){
          var request = './user/craftsman?id='+artistid;//type = allcraftsmen
          var imgurl = './images/artifact/';
          $('#artistType').html('<a href="allartist.jsp?type=allcraftsmen">Artifact</a>');
      }else{
          var request = './user/profartist?id='+artistid;//type = allprofartists
          var imgurl = './images/fineart/';
          $('#artistType').html('<a href="allartist.jsp?type=allprofartists">Fine Art</a>');
      };
      $.get(request, function(data, status) {
        //alert("data: " + data + "\nstatus" + status);
        //document.write(data+"</br>");
        var artist = JSON.parse(data);
        $('#artistName').html(artist[0].name);//导航栏中的artist name
        $('#artname').html(artist[0].name);//artist name
        //为了让画家生卒年出现在名字下面，这里把description和location反过来了，后续再调整
        $('#artloc').html(artist[0].description);//artist location
        //$('#artdes').html(artist[0].location);//artist description

        var artworks = artist[0].works;//artist works
        $('#workcount').html(artworks.length+" works,"+artworks.length+" sold");//artist work count
        for (var i = 0; i < artworks.length; i++) {
          var artworkid = artworks[i];
          var url = "artwork.jsp?awid="+artworkid+'&type='+type+'&artname='+artist[0].name+'&artid='+artistid;
          // alert("a");
          $('.variable-width').append('<div class="image"><a href="'+url+'" class="thumbnail">\
            <img style="height: 250px;" src="'+imgurl+artworkid+'_1.jpg" alt="..."></a></div>');

          $('#grid').append('<div class="col-xs-6 col-sm-4 col-md-3 column grid-item">\
            <div class="thumbnail zoomin"><a href="'+url+'">\
            <img src="'+imgurl+artworkid+'_1.jpg" alt="..."></a>\
            </div>\
            </div>');
        };
        $('.variable-width').slick({
            dots: true,
        		infinite: false,
        		speed: 300,
        		slidesToShow: 1,
        		centerMode: false,
        		variableWidth: true,
        		autoplay: true
          });
            // init Masonry
          var $grid = $('#grid').masonry({
            // options...
            itemSelector: '.grid-item'
          });
          // layout Masonry after each image loads
          $grid.imagesLoaded().progress( function() {
            $grid.masonry('layout');
          });
      });
      
//      for (var i = 0; i < artworks.length; i++) {
//          var url = "artwork.html?awid="+'testworkid'+'&type='+'allcraftsmen'+'&artname='+'TestArtistName';
//          $('.variable-width').append('<div class="image"><a href="'+url+'" class="thumbnail">\
//            <img style="height: 250px;" src="res/'+i+'.jpg" alt="..."></a></div>');
//        };//for end
//        
//        $('.variable-width').slick({
//          dots: true,
//      		infinite: true,
//      		speed: 300,
//      		slidesToShow: 1,
//      		centerMode: true,
//      		variableWidth: true,
//      		autoplay: true
//        });
      //这个for循环仅仅是为了写代码方便保留，实际放到服务器中用的时候应删除
      /*for (var i = 1; i < 16; i++) {
        var index = i;
        var url = "artwork.html?awid="+'testworkid'+'&type='+'allcraftsmen'+'&artname='+'TestArtistName';
        $('.variable-width').append('<div class="image"><a href="'+url+'" class="thumbnail">\
          <img style="height: 250px;" src="res/'+i+'.jpg" alt="..."></a></div>');
      };//for end
      
      $('.variable-width').slick({
        dots: true,
    		infinite: true,
    		speed: 300,
    		slidesToShow: 1,
    		centerMode: true,
    		variableWidth: true,
    		autoplay: true
      });*/
      //这个for循环仅仅是为了写代码方便保留，实际放到服务器中用的时候应删除
      /*for (var i = 1; i < 16; i++) {
      	var index = i;
        var url = "artwork.html?awid="+'testworkid'+'&type='+'allcraftsmen'+'&artname='+'TestArtistName';
      	$('#grid').append('<div class="col-xs-6 col-sm-4 col-md-3 column grid-item">\
      	<div class="thumbnail zoomin"><a href="'+url+'">\
          <img src="res/'+index+'.jpg" alt="..."></a>\
          </div>\
        </div>');
      };*/
      //for end
});
// artist页面中的readmore实现
$(function(){
    var slideHeight = 50; // px
    var defHeight = $('#wrap').height();
    // alert(defHeight)
    if(defHeight >= slideHeight){
        $('#wrap').css('height' , slideHeight + 'px');
        $('#read-more').append('<a href="#">Read More</a>');
        $('#read-more a').click(function(){
            var curHeight = $('#wrap').height();
            // alert(curHeight)
            if(curHeight == slideHeight){
                // $('#wrap').removeAttr("style");
                $('#wrap').css('height' , 'auto');
                // alert(defHeight)
                // $('#wrap').animate({
                //   height: defHeight
                // }, "normal");
                $('#read-more a').html('Close');
                // $('#gradient').fadeOut();
            }else{
                $('#wrap').animate({
                  height: slideHeight
                }, "normal");
                $('#read-more a').html('Read More');
                // $('#gradient').fadeIn();
            }
            return false;
        });        
    }
});
