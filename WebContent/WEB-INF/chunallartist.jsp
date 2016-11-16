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
    <link rel="stylesheet" type="text/css" href="slick/slick.css"/>
    <link rel="stylesheet" type="text/css" href="slick/slick-theme.css"/>
    <!-- bootstrap-select -->
    <link rel="stylesheet" href="bootstrap-select/bootstrap-select.min.css">
    <!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css" href="mycss/custom.css"/>
    <link rel="stylesheet" type="text/css" href="mycss/default.css"/>
     <link rel="stylesheet" type="text/css" href="mycss/Supernice1.css"/>
     <style type="text/css">
         .thumbnail{
            border-style: hidden;
         }
     </style>
    <script type="text/javascript">
      function loginfunction(){
        x=document.getElementById("login_error");
        x.innerHTML = "testing";
      }
    </script>>
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
                    <a href="#">HOME</a>
                <li> 
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">ARTIST<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                      <li> 
                        <a href="allartist.jsp?type=allprofartists">FINE ART</a>                         
                      </li>
                      <li>
                        <a href="allartist.jsp?type=allcraftsmen">ARTIFACT</a>                        
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

  <!-- allartist in pictures -->
  <div class="container">
    <div class="row clearfix" style="padding-bottom: 20px;">
        <div class="col-xs-12">
          <h2 id='type'></h2>
        </div>
    </div>
    <div class="row clearfix grid" id="allArtist">
       <!-- content -->
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

    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="slick/slick.min.js"></script>
    <script src="imagesloaded/imagesloaded.pkgd.min.js"></script>
    <script src="masonry/masonry.pkgd.min.js"></script>
    <script src="bootstrap-select/bootstrap-select.min.js"></script>
    <script type="text/javascript" src="js/jquery.scrollUp.min.js"></script>
    <!-- <script type="text/javascript" src="js/jquery.sticky.js"></script> -->
    <script type="text/javascript" src="myjs/allartist.js"></script>
  </body>
</html>