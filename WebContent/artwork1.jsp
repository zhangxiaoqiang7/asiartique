<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
     <meta charset="utf-8">
         <meta http-equiv="X-UA-Compatible" content="IE=edge">
       <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="image/index.ico">

    <title>Asiartique</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <!-- <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet"> -->
    <!-- slick css -->
    <link rel="stylesheet" type="text/css" href="slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="slick/slick-theme.css"/>

    <!-- bootstrap-select -->
    <link rel="stylesheet" href="bootstrap-select/bootstrap-select.min.css">
    <!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css" href="mycss/custom.css"/>
    <link rel="stylesheet" type="text/css" href="mycss/default.css"/>
    <link rel="stylesheet" type="text/css" href="mycss/Supernice.css"/>
    <style type="text/css">
         .thumbnail{
            border-style: hidden;
         }
        #img_wrapper {
  position: relative;
  width: 55%;
  height: auto;
  margin: 0 5% 0 auto;
  border: 12px solid #fff;
  border-radius: 10px;
  box-shadow: 1px 1px 5px rgba(50,50,50 0.5);
  float: left;
}
     </style>

     <script type="text/javascript">
$(document).ready(function()
{
  $("#gear").mlens(
  {
    imgSrc: $("#gear").attr("data-big"),    // path of the hi-res version of the image
    imgSrc2x: $("#gear").attr("data-big2x"),  // path of the hi-res @2x version of the image
                                                  //for retina displays (optional)
    lensShape: "circle",                // shape of the lens (circle/square)
    lensSize: ["20%","30%"],            // lens dimensions (in px or in % with respect to image dimensions)
                                        // can be different for X and Y dimension
    borderSize: 4,                  // size of the lens border (in px)
    borderColor: "#fff",            // color of the lens border (#hex)
    borderRadius: 0,                // border radius (optional, only if the shape is square)
    imgOverlay: $("#gear").attr("data-overlay"), // path of the overlay image (optional)
    overlayAdapt: true,    // true if the overlay image has to adapt to the lens size (boolean)
    zoomLevel: 1,          // zoom level multiplicator (number)
    responsive: true       // true if mlens has to be responsive (boolean)
  });
});
</script>

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <!-- <script src="../../assets/js/ie-emulation-modes-warning.js"></script> -->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
  
  <header>
      <!-- *** NAVBAR ***  -->
      <nav class="navbar navbar-default navbar-fixed-top ">
      <div class="container-fluid">
        <div class="navbar-header">
              <a class="navbar-brand" href="#">
                         <img src="image/index.ico"  alt=" logo" class="hidden-xs hidden-sm">
                           <img src="image/index.ico"  alt=" logo" class="visible-xs visible-sm"><span class="sr-only"></span>
                    </a>
                    <div class="navbar-buttons">
                                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                                    <span class="sr-only">Toggle navigation</span>
                                    <i class="glyphicon glyphicon-align-justify"></i>
                                </button>
                    </div>
        </div>
        <div class="navbar-collapse collapse" id="navbar">
          <!--form action="search-result-page.jsp" class="navbar-form navbar-left" role="search">
                           <div class="input-group">
                             <input type="text" class="form-control" placeholder="Search">
                              <span class="input-group-btn">
                                   <button class="btn btn-default" type="submit">Go!</button>
                              </span>
                          </div>
            </form -->

            <ul class="nav navbar-nav navbar-right"> 
              <li class="dropdown">
                    <a href="./">HOME</a>
                <li> 
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">ARTIST<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                      <li> 
                        <a href="#">FINE ART</a>                         
                      </li>
                      <li>
                        <a href="#">ARTIFACT</a>                        
                      </li>
                    </ul>
                </li> 
                <li class="dropdown">
                    <a href="#">ARTICLE</a>
                </li> 
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">ABOUT US<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                      <li> 
                        <a href="#">?</a>                         
                      </li>
                      <li>
                        <a href="#">?</a>                        
                      </li>
                    </ul>
                </li> 
                <li class="dropdown login">
                     <a href="#" data-toggle="modal" data-target="#login-modal"><i class="glyphicon glyphicon-user"></i> <span class="hidden-xs">SIGN IN</span></a>
                </li> 
                <li class="dropdown login">
                    <a href="#" data-toggle="modal" data-target="#registe-modal"><i class="glyphicon glyphicon-heart"></i> <span class="hidden-xs">SIGN UP</span></a>
                </li> 
            </ul>
        </div>
      </div>
      </nav>
      <!-- *** NAVBAR  END ***  -->
      <!-- *** LOGIN MODAL *** -->

        <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true">
            <div class="modal-dialog modal-sm">

                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="Login">Login</h4>
                    </div>
                    <div class="modal-body">
                        <form action="#" method="post">
                            <div class="form-group">
                                <input type="text" class="form-control" id="email_modal" placeholder="email">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" id="password_modal" placeholder="password">
                            </div>

                            <p class="text-center">
                                <button onclick="loginFunction()" class="btn"><i class="glyphicon glyphicon-user"></i> Log in</button>
                            </p>

                        </form>

                        <p class="text-center text-muted">Not registered yet?</p>
                        <p class="text-center text-muted"><a href="#"><strong>Register now</strong></a>!</p>

                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="registe-modal" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true">
            <div class="modal-dialog modal-sm">

                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="Login">Login</h4>
                    </div>
                    <div class="modal-body">
                        <form action="customer-orders.jsp" method="post">
                            <div class="form-group">
                                <input type="text" class="form-control" id="email_modal" placeholder="email">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" id="password_modal" placeholder="password">
                            </div>

                            <p class="text-center">
                                
                                <button class="btn btn-template-main"><i class="fa fa-sign-in"></i> Log in</button>
                            </p>

                        </form>

                        <p class="text-center text-muted">Not registered yet?</p>
                        <p class="text-center text-muted"><a href="customer-register.jsp"><strong>Register now</strong></a>!</p>

                    </div>
                </div>
            </div>
        </div>
        <!-- *** LOGIN MODAL END *** -->       
  </header>

    <!-- Begin page content -->
    <div class="container">
          <div>
            <img id="green_monster" src="res/2.jpg" alt="bike gear by Markus Spiske" data-big="images/gear-big.jpg" data-big2x="res/1.jpg" data-overlay="res/aaa.jpg" />
          </div>

          <div id="real_time_testing">
        <h2>Test the demo parameters</h2>
        <form id="real_time_form">
          <legend></legend>
          <fieldset>
            <label for="lensShape_param">lensShape [<i>circle</i> o <i>square</i>]:</label>
            <select name="lensShape_param">
              <option value="circle">circle</option>
              <option value="square">square</option>
            </select>
            <br class="esc" />
            <label for="lensSize_param">lensSize [<i>string px or %</i>]:</label>
            <input type="text" id="lensSize_param" name="lensSize_param" value="20%,30%" />
            <br class="esc" />
            <label for="borderSize_param">borderSize [<i>px number</i>]:</label>
            <input type="text" id="borderSize_param" name="borderSize_param" value="4" />
            <br class="esc" />
            <label for="borderColor_param">borderColor [<i>hex value</i>]:</label>
            <input type="text" id="borderColor_param" name="borderColor_param" value="#fff" />
            <br class="esc" />
            <span class="note">Only if <i>lensShape is square</i></span>
            <label for="boderRadius_param">borderRadius [<i>px number</i>]:</label>
            <input type="text" id="borderRadius_param" name="borderRadius_param" value="0" />
            <br class="esc" />
            <label for="zoomLevel_param">zoomLevel [<i>numero</i>]:</label>
            <input type="text" id="zoomLevel_param" name="zoomLevel_param" value="1" />
            <br class="esc" />
            <input id="form_button_reset" type="reset" value="Values reset" /> <input id="form_button" type="button" value="Apply changes" />
          </fieldset>
          <div id="risposta"></div>
        </form>
      </div>

    </div>



        <!-- *** COPYRIGHT ***
_________________________________________________________ -->

        <div id="copyright">
            <div class="container">
                <div class="col-md-12">
                    <p class="pull-left">&copy; 2016.Asiartique</p>
                    <p class="pull-right">
                       Pittsburgh, PA, 15213, United States
                    </p>

                </div>
            </div>
        </div>
        <!-- /#copyright -->

        <!-- *** COPYRIGHT END *** -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script> -->
    <!-- jQueryæä»¶ãå¡å¿å¨bootstrap.min.js ä¹åå¼å¥ -->
    <script src="js/jquery-1.11.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="slick/slick.min.js"></script>
    <script src="imagesloaded/imagesloaded.pkgd.min.js"></script>
    <script src="masonry/masonry.pkgd.min.js"></script>
    <script src="bootstrap-select/bootstrap-select.min.js"></script>
    <script type="text/javascript" src="js/stickyfloat.js"></script>
    <script type="text/javascript" src="js/jquery.scrollUp.min.js"></script>
    <!-- <script type="text/javascript" src="js/ui.core.js"></script> -->
    <!-- <script type="text/javascript" src="js/jquery.scrollFollow.js"></script> -->
    <script type="text/javascript" src="myjs/custom.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <!-- <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->

    <!-- zoom -->
    <script type="text/javascript" src="myjs/jquery.mlens-1.6.min.js"></script>
  </body>
</html>
