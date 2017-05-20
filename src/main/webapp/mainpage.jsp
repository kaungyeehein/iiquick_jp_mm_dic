<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<!-- Bootstrap Style 3.2.0 -->
<!-- <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet"> -->
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Dialog Box Style -->
<link href="css/bootstrap-dialog.min.css" rel="stylesheet">
<!-- iiQuick Main Style -->
<link href="css/iiquick-main-201408070202.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

<!-- Favicons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="img/jpmm-logo-144x144.png">
<link rel="shortcut icon" href="img/jpmm-logo-32x32.png">
<script>
	var rootURL = "${pageContext.request.contextPath}";
</script>
<style>
#loading h3 {
	font-family: 'arial', 'sans-serif', 'tahoma';
}
</style>
</head>
<body>
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

	<div id="main" style="display:none">
	<!-- Navbar -->
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">

			<!-- Header -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#iiquick-navbar-collapse">
					<span class="sr-only">Menu</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">iiQUICK</a>
				<p class="navbar-text active">Japan Myanmar Dictionary</p>
			</div>

			<!-- Right Button -->
			<div class="collapse navbar-collapse" id="iiquick-navbar-collapse">
				<!-- <p class="navbar-text"><span class='badge'>Search 23011</span></p> -->
				<form class="navbar-form navbar-right">
					<div class="btn-group btn-group-sm">
						<button id="zawgyi1-btn" type="button" class="btn btn-success">ZawgyiOne</button>
						<button id="myanmar3-btn" type="button" class="btn  btn-default">Myanmar3</button>
					</div>
				</form>
			</div>
		</div>
	</nav>
	<!-- Navbar End -->

	<!-- Search -->
	<div class="container text-center">
		<div id="search-form">
			<div class="row">
				<img id="jpmm-logo" src="img/jpmm-logo-228x228.png" class="col-xs-6 col-sm-4 col-md-2 col-centered" alt="JPMM Dictionary Logo" />
			</div>
			<p id="detres">စာလံုးတစ္လံုးအနည္းဆံုးရိုက္ထည့္ပါ</p>
			<div class="row">
				<div class="col-xs-12 col-sm-10 col-md-8 col-lg-6 col-centered">
					<div id="search-group" class="input-group text-left">
						<input id="search-hidden" type="hidden" /> 
						<input id="search-input" type="text" class="form-control" data-provide="typeahead" placeholder="ရွာလိုသည္ကိုရိုက္ထည့္ပါ ..." autocomplete="off" spellcheck="false" />
						<span class="input-group-btn">
							<button id="clear-btn" class="btn btn-default" type="button" title="Clear"><span class="glyphicon glyphicon-remove"></span></button>
							<button id="search-btn" class="btn btn-default" type="button" title="Search"><span id="lbl-search">ရွာရန္</span><span class="glyphicon glyphicon-search"></span></button>
						</span>
					</div>
				</div>
			</div>
			<p>
				<a id="lbl-newword" href="#" title="Insert new word">ေဝါဟာရအသစ္ထပ္ထည့္ရန္</a>
			</p>
		</div>
	</div>
	<!-- Search End -->

	<!-- Result -->
	<div class="container" id="search-result">
	</div>
	<!-- Result End -->

	<!-- Footer -->
	<div class="container">
		<div id="footer" class="text-center">
			<h6>Developed by Kaung Yee Hein</h6>
			<h6>
				Contributed by 5th batch members of NTT Data Myanmar and our friends.<br /> Thanks for your attention.<br /> Since @ 2013 - 2014<br /> <a href="#" id="back-top">http://iiquick-ygnapi.rhcloud.com</a>
			</h6>
			<p>
				<small>
				<logic:present name="countEn">
					<abbr title="English words count" class="initialism">EN</abbr> <kbd>${countEn}</kbd>
				</logic:present>
				<logic:present name="countJk">
					| <abbr title="Japanese kanji words count" class="initialism">JK</abbr> <kbd>${countJk}</kbd>
				</logic:present>
				<logic:present name="countJm">
					| <abbr title="Japanese katakana & hiragana words count" class="initialism">JM</abbr> <kbd>${countJm}</kbd>
				</logic:present>
				<logic:present name="countMm">
					| <abbr title="Myanmar words count" class="initialism">MM</abbr> <kbd>${countMm}</kbd>
				</logic:present>
				</small>
			</p>
		</div>
	</div>
	
	<!-- Second Footer -->
	<div class="container">
		<p class="text-center">
			<a class="btn btn-social-icon btn-facebook" target="_blank" href="https://www.facebook.com/kaungyee.hein"><i class="fa fa-facebook"></i></a>
			<a class="btn btn-social-icon btn-github" target="_blank" href="https://github.com/kaungyeehein/iiquick"><i class="fa fa-github"></i></a>
			<a class="btn btn-social-icon btn-google-plus" target="_blank" href="https://plus.google.com"><i class="fa fa-google-plus"></i></a>
			<h6>
				<small>
					<div itemscope itemtype="http://data-vocabulary.org/Review" >
						<span itemprop="itemreviewed">iiQuick Dictionary</span>
						Reviewed by <span itemprop="reviewer"><a href="https://plus.google.com/101062938209335133413?rel=author" itemprop="url">Author</a></span> on
						<time itemprop="dtreviewed" datetime="2014-08-08">Aug 8</time>.
						<span itemprop="summary">iiQuick, Japan Myanmar Dictionary, Web Application for Desktop, Tablet, Mobile.</span>
						<span itemprop="description">This application is developed for open source and free project. We are intended for Myanmar user for learning Japanese language.</span>
						Rating: <span itemprop="rating">4.5</span>
					</div>
				</small>
			</h6>
		</p>
	</div>
	
	<!-- Word Insert Modal 2 -->
	<div class="modal fade type-primary" id="wordInsertModal" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					<h4 class="modal-title" id="lbl-insert-form">ေဝါဟာရအသစ္ ထည့္မည္</h4>
				</div>
				<div class="modal-body">
					<div class="form-horizontal">
						<div class="form-group has-feedback" id="form-wtId">
							<div class="col-sm-4 control-label" id="lbl-wt">ဝါစဂၤၤ</div>
							<div class="col-xs-12 col-sm-8">
								<select id="wtId" tabindex="1" class="form-control">
										<option value="default" selected="selected"></option>
										<logic:present name="lstWType">
											<logic:iterate id="wType" name="lstWType">
												<option value="${wType.id}">${wType.desc}</option>
											</logic:iterate>
										</logic:present>
								</select>
							</div>
						</div>
						<div class="form-group has-feedback" id="form-wordEn">
							<div class="col-sm-4 control-label"  id="lbl-en">အဂၤလိပ္ဘာသာ</div>
							<div class="col-xs-12 col-sm-8">
								<input type="text" name="wordEn" id="wordEn" maxlength="225" tabindex="2" class="form-control" placeholder="eg. car" />
							</div>
						</div>
						<div class="form-group has-feedback" id="form-wordJk">
							<div class="col-sm-4 control-label" id="lbl-jk">ဂ်ပန္ဘာသာ (ခန္းဂ်ီး)</div>
							<div class="col-xs-12 col-sm-8">
								<input type="text" name="wordJk" id="wordJk" maxlength="225" tabindex="3" class="form-control" placeholder="eg. 車" />
							</div>
						</div>
						<div class="form-group has-feedback" id="form-wordJm">
							<div class="col-sm-4 control-label" id="lbl-jm">ဂ်ပန္ဘာသာ</div>
							<div class="col-xs-12 col-sm-8">
								<input type="text" name="wordJm" id="wordJm" maxlength="225" tabindex="4" class="form-control" placeholder="eg. くるま"/>
							</div>
						</div>
						<div class="form-group has-feedback" id="form-wordMm">
							<div class="col-sm-4 control-label" id="lbl-mm">ျမန္မာဘာသာ</div>
							<div class="col-xs-12 col-sm-8">
								<input type="text" name="wordMm" id="wordMm" maxlength="225" tabindex="5" class="form-control" placeholder="eg. ကား"/>
							</div>
						</div>
					</div>
					<br/><small class='text-warning' id="lbl-insertnote">မွတ္ခ်က္။ အဘိဓာန္တြင္ ဂ်ပန္ဘာသာ ဆိုသည္မွာ Katakana သို႔မဟုတ္ Hiragana ကိုထည့္သြင္းရန္ ဆိုလိုျခင္းျဖစ္သည္။ အဓိပၸါယ္တူပါက စာလုံးမ်ားကို ေကာ္မာ သို႔မဟုတ္ ပုဒ္ကေလး အသုံးျပဳ၍ ထည့္သြင္းနိုင္သည္။ အကြက္အားလုံးကို ျဖည့္သြင္းေပးရန္ မလိုပါ။</small>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success" tabindex="6" onclick="javascript:insertWord()" id="btn-insert2"><span class="glyphicon glyphicon-send button-icon"></span> ထည့္သြင္းမည္</button>
				</div>
			</div>
		</div>
	</div>
	</div>
	
	<!-- JQuery 2.1.1 && Bootstrap 3.2.0 -->
	<!-- <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>	
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script> -->
	<script src="js/jquery-2.1.1.min.js"></script>
	<script src="js/bootstrap-3.2.0.min.js"></script>
	<!-- Dialog Box -->
	<script src="js/bootstrap-dialog.min.js"></script>
	<!-- Auto Complete -->
	<script src="js/bootstrap3-typeahead.min.js"></script>
	<!-- iiQuick Main -->
	<script src="js/iiquick-main-201408070202.js"></script>
	<script type="text/javascript">
	$(window).load(function() {
		$('#loading').hide();
		$('#main').show();
		<logic:notPresent name="lstWType">
		systemDialog(null,lang["msg-err-db"]);
		</logic:notPresent>
	});
</script>
</body>
</html>
