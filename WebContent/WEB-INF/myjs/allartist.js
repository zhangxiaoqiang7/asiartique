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
// allartist页面js
$(document).ready(function(){
      // allartist页面获取所有的artist
      var type = $.getUrlParam('type');//判读要访问的artist类型，返回不同的数据
      if(type == 'allcraftsmen'){
          $('#type').html('ARTIFACT <small>we have <span id="count"></span> artists</small>');
          $('#artistType').html('Artifact');
      }else{
          $('#type').html('FINE ART <small>we have <span id="count"></span> artists</small>');
          $('#artistType').html('Fine Art');
      };
      // $('#count').html('10');//看效果的时候用
      request = "./user/"+type+'?page=0';
      $.get(request, function(data, status) {
        //alert("data: " + data + "\nstatus" + status);
        //document.write(data+"</br>");
    	//alert(data);
        var jsonData = JSON.parse(data);
        if(type == 'allcraftsmen'){
          var list = jsonData;//type = allcraftsmen
          var imgurl = './images/craftsman/';
        }else{
          var list = jsonData;//type = allprofartists
          var imgurl = './images/profartist/';
        };
        $('#count').html(list.length);//count
        for (var i = 0; i < list.length; i++) {
          var artid = list[i].id;
          var url = 'artist.jsp?artid='+artid+'&type='+type;
          // alert("a");
          $('#allArtist').append('<div class="col-xs-6 col-sm-4 col-md-3 column artist-item">\
          <div class="thumbnail zoomin"><a href="'+url+'">\
            <img src="'+imgurl+artid+'.jpg" alt="..." style="height: 200px;"><span class="vcenter">'+list[i].name+'</span></a>\
            </div>\
          </div>');
        };
            // init Masonry
        var $grid = $('#allArtist').masonry({
          // options...
          itemSelector: '.artist-item'
        });
        // layout Masonry after each image loads
        $grid.imagesLoaded().progress( function() {
          $grid.masonry('layout');
        });
      });
    //这个for循环仅仅是为了写代码方便保留，实际放到服务器中用的时候应删除
 /*   for (var i = 1; i < 16; i++) {
          var artid = 'testid';
          var url = "artist.html?artid="+artid+'&type='+'allcraftsmen';
          // alert("a");
          $('#allArtist').append('<div class="col-xs-6 col-sm-4 col-md-3 column artist-item">\
          <div class="thumbnail zoomin"><a href="'+url+'">\
            <img src="res/'+i+'.jpg" alt="..." style="height: 200px;"><span class="vcenter">text</span></a>\
            </div>\
          </div>');
    };*/
    //for end
      

});