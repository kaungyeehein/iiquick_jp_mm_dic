<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="description" content="iiQuick, Japan Myanmar Dictionary, Web Application for Desktop, Tablet, Mobile. Developed by: kaungyeehein@gmail.com">
<meta name="keywords" content="iiQuick, Japan, Myanmar, Dictionary, Japan Myanmar, Japan Myanmar Dictionary, iiQuick Japan Myanmar, iiQuick Japan Myanmar Dictionary, Japan Myanmar Learning, Learning, Japan Myanmar Language, Language">
<meta name="author" content="Kaung Yee Hein [kaungyeehein@gmail.com]">

<title>iiQuick Dictionary</title>

<style type="text/css">
body {
	padding-top: 70px;
}
</style>
<!-- Bootstrap Style 3.2.0 -->
<!-- <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet"> -->
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/iiquick-main-201408070202.css" rel="stylesheet">

<!-- Favicons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="img/jpmm-logo-144x144.png">
<link rel="shortcut icon" href="img/jpmm-logo-32x32.png">
<style>
*, body, div, h3 {
	font-family: 'arial', 'sans-serif', 'tahoma';
}
</style>
</head>
<body onload="loading()">
	<div id="loading"  class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-offset-3 col-sm-6 col-md-offset-4 col-md-4">
				<h3>Loading...</h3>
				<div class="progress">
					<div class="progress-bar progress-bar-striped active"  role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>
				</div>
			</div>
		</div>
	</div>
	<div style="font-family:'Glyphicons Halflings'">&nbsp;</div>
	<div style="font-family:'FontAwesome'">&nbsp;</div>
	<script type="text/javascript">
	var img = new Array();
	function preloadImg() {
		for (var i = 0; i < preloadImg.arguments.length; i++) {
			img[i] = new Image();
			img[i].src = preloadImg.arguments[i];
		}
	}
	preloadImg(
			"img/gray_jean.png",
			"img/jpmm-logo-144x144.png",
			"img/jpmm-logo-228x228.png",
			"img/jpmm-logo-32x32.png",
			"img/search-icon.gif"
	);
	function loading() {
		window.location.href = "Home.do";
/* 		var url = location.href;
		url = url.replace("/index.jsp", "");
		window.location = url + "/Home.do"; */
	}
	</script>
</body>
</html>