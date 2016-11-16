<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="images/index/index.png">

<title>Asiartique</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<!-- <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet"> -->
<!-- slick css -->
<link rel="stylesheet" type="text/css" href="slick/slick.css" />
<link rel="stylesheet" type="text/css" href="slick/slick-theme.css" />

<!-- bootstrap-select -->
<link rel="stylesheet" href="bootstrap-select/bootstrap-select.min.css">
<!-- Custom styles for this template -->
<link rel="stylesheet" type="text/css" href="mycss/custom.css" />
<link rel="stylesheet" type="text/css" href="mycss/default.css" />
<link rel="stylesheet" type="text/css" href="mycss/Supernice1.css" />
<link rel="stylesheet" href="css/jqzoom.css" type="text/css"
	media="screen" />
<style type="text/css">
.thumbnail {
	border-style: hidden;
}

body, h1, h2, h3, h4, h5, h6 {
	font-family: sans-serif, Georgia, Serif;
}
</style>

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
					<a class="navbar-brand" href="#"> <img
						src="images/index/index.png" alt=" logo"
						class="logo hidden-xs hidden-sm"> <img
						src="images/index/index.png" alt=" logo"
						class="logo visible-xs visible-sm"><span class="sr-only"></span>
					</a>
					<div class="navbar-buttons">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navbar"
							aria-expanded="false" aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span> <i
								class="glyphicon glyphicon-align-justify"></i>
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
						<li class="dropdown"><a href="./">HOME</a>
						<li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">ARTIST<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="allartist.jsp?type=allprofartists">FINE
										ART</a></li>
								<li><a href="allartist.jsp?type=allcraftsmen">ARTIFACT</a>
								</li>
							</ul></li>
						<li class="dropdown"><a href="#">ARTICLE</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">ABOUT US<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">?</a></li>
								<li><a href="#">?</a></li>
							</ul></li>
						<li class="dropdown login"><a href="#" data-toggle="modal"
							data-target="#login-modal"><i
								class="glyphicon glyphicon-user"></i> <span class="hidden-xs">SIGN
									IN</span></a></li>
						<li class="dropdown login"><a href="#" data-toggle="modal"
							data-target="#registe-modal"><i
								class="glyphicon glyphicon-heart"></i> <span class="hidden-xs">SIGN
									UP</span></a></li>
					</ul>
				</div>
			</div>
		</nav>
		<!-- *** NAVBAR  END ***  -->
		<!-- *** LOGIN MODAL *** -->

		<div class="modal fade" id="login-modal" tabindex="-1" role="dialog"
			aria-labelledby="Login" aria-hidden="true">
			<div class="modal-dialog modal-sm">

				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="Login">Login</h4>
					</div>
					<div class="modal-body">
						<form action="#" method="post">
							<div class="form-group">
								<input type="text" class="form-control" id="email_modal"
									placeholder="email">
							</div>
							<div class="form-group">
								<input type="password" class="form-control" id="password_modal"
									placeholder="password">
							</div>

							<p class="text-center">
								<button onclick="loginFunction()" class="btn">
									<i class="glyphicon glyphicon-user"></i> Log in
								</button>
							</p>

						</form>

						<p class="text-center text-muted">Not registered yet?</p>
						<p class="text-center text-muted">
							<a href="#"><strong>Register now</strong></a>!
						</p>

					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="registe-modal" tabindex="-1" role="dialog"
			aria-labelledby="Login" aria-hidden="true">
			<div class="modal-dialog modal-sm">

				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="Login">Login</h4>
					</div>
					<div class="modal-body">
						<form action="customer-orders.jsp" method="post">
							<div class="form-group">
								<input type="text" class="form-control" id="email_modal"
									placeholder="email">
							</div>
							<div class="form-group">
								<input type="password" class="form-control" id="password_modal"
									placeholder="password">
							</div>

							<p class="text-center">

								<button class="btn btn-template-main">
									<i class="fa fa-sign-in"></i> Log in
								</button>
							</p>

						</form>

						<p class="text-center text-muted">Not registered yet?</p>
						<p class="text-center text-muted">
							<a href="customer-register.jsp"><strong>Register now</strong></a>!
						</p>

					</div>
				</div>
			</div>
		</div>
		<!-- *** LOGIN MODAL END *** -->
	</header>

	<!-- Begin page content -->
	<div class="container mincontent">
		<div class="row">
			<div class="col-xs-8 col-md-8">
				<div class="col-xs-11 slider slider-for">
					<!-- content -->
				</div>
				<div class="col-xs-11 slider slider-nav" style="margin-top: 10px;">
					<!-- content -->
				</div>
				<div class="col-xs-12">
					<h3 id='workname'></h3>
					<h4>Width</h4>
					<p id="width"></p>
					<h4>Height</h4>
					<p id="height"></p>

					<!--h3>About</h3>
            <p id="aw_about"></p>
            <h3>Details</h3>
            <div class="col-xs-6">
              <h4>One detail</h4>
              <ul class="list-unstyled">
                <li>111</li>
                <li>222</li>
                <li>333</li>
              </ul>
              <h4>Two detail</h4>
              <ul class="list-unstyled">
                <li>111</li>
                <li>222</li>
                <li>333</li>
              </ul>
            </div>
            <div class="col-xs-6">
              <h4>Three detail</h4>
              <ul class="list-unstyled">
                <li>111</li>
                <li>222</li>
                <li>333</li>
              </ul>
              <h4>Four detail</h4>
              <ul class="list-unstyled">
                <li>111</li>
                <li>222</li>
                <li>333</li>
              </ul>
            </div -->
				</div>
			</div>
			<div id="sticker"
				class="col-xs-3 col-md-3 col-md-offset-1 col-xs-offset-1">
				<!-- <h3>Do you like this?</h3>
            <h4 id='artname'></h4>
            <div><label id='price'></label></div>
            <div><label id='date'></label></div>
            <div><button id='follow' class="btn btn-primary btn-lg btn-block" type="button">
                 FOLLOW <span class="badge">4</span></button></div>
            <div style="padding-top: 10px;"><button id='contact' class="btn btn-default btn-lg btn-block" type="button">CONTACT</button></div> -->
				<h3 align="center" style="padding-bottom: 5px">Contact us for
					price</h3>
				<h4 id='artname'></h4>
				<h4 id='workname'></h4>
				<form>
					<div class="form-group">
						<label for="contact_fullname">Full Name</label> <input type="text"
							class="form-control" id="contact_fullname"
							placeholder="Put your full name here">
					</div>
					<div class="form-group">
						<label for="contact_email">Email Address</label> <input
							type="email" class="form-control" id="contact_email"
							placeholder="Put your email address here">
					</div>
					<div class="form-group">
						<label for="contact_info">Leave us a message</label>

						<textarea class="form-control" rows="5" id="contact_info">Hi! I am kind of interested in this artwork. Could you please provide me more informations about this work? Thank you!</textarea>
					</div>
				</form>
				<button class="btn btn-primary" onclick="contactFunction()" style="margin-left: 38%">Submit</button>
			</div>
		</div>
	</div>



	<!-- *** COPYRIGHT ***
_________________________________________________________ -->

	<div id="copyright">
		<div class="container">
			<div class="col-md-12">
				<p class="pull-left">&copy; 2016.Asiartique</p>
				<p class="pull-right">Pittsburgh, PA, 15213, United States</p>

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
	<!-- <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script> -->
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="slick/slick.min.js"></script>
	<script src="imagesloaded/imagesloaded.pkgd.min.js"></script>
	<script src="masonry/masonry.pkgd.min.js"></script>
	<script src="bootstrap-select/bootstrap-select.min.js"></script>
	<script type="text/javascript" src="js/stickyfloat.js"></script>
	<script type="text/javascript" src="js/jquery.scrollUp.min.js"></script>
	<script type="text/javascript" src="js/jquery.jqzoom.min.js"></script>
	<!-- <script type="text/javascript" src="js/ui.core.js"></script> -->
	<!-- <script type="text/javascript" src="js/jquery.scrollFollow.js"></script> -->
	<script type="text/javascript" src="myjs/artwork.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<!-- <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->
</body>
</html>
