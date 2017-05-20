/**
 * @version 20140807
 * @author kaungyeehein@gmil.com
 * @copyright (c) 2013 jpmmdictionary.iiquick.cloudbees.net
 * @license GPL
 */
$(document).ready(function() {

    /* Search Click */
    $("#search-btn").click(function() {
	var input = document.getElementById("search-input").value;
	document.getElementById("search-hidden").value = input;
	search(input);
    });

    /* Clear Click */
    $("#clear-btn").click(function() {
	$("#search-input").val("");
	document.getElementById("detres").innerHTML = lang["lbl-insertoneword"];
    });

    /* Keydown in search input */
    $("#search-input").bind("keyup", function(e) {
	var input = document.getElementById("search-input").value;
	setTimeout(function() {
	    if (lang["name"] == "ZawgyiOne") {
		input = Z1_Uni(input);
	    }
	    detect(input);
	    if (e.keyCode == 13) {
		$("#search-btn").click();
	    }
	}, 5);
    });

    /* Zawgyi One Button Click */
    $("#zawgyi1-btn").click(function() {
	$("#myanmar3-btn").removeClass('btn-success');
	$("#zawgyi1-btn").removeClass('btn-default');
	$("#myanmar3-btn").addClass('btn-default');
	$("#zawgyi1-btn").addClass('btn-success');
	changelang("zawgyi");
    });

    /* Myanmar3 Button Click */
    $("#myanmar3-btn").click(function() {
	$("#zawgyi1-btn").removeClass('btn-success');
	$("#myanmar3-btn").removeClass('btn-default');
	$("#zawgyi1-btn").addClass('btn-default');
	$("#myanmar3-btn").addClass('btn-success');
	changelang("mm3");
    });

    /* New word Click */
    $("#lbl-newword").click(function() {
	$("#wordInsertModal").modal("show");
    });

    /* Auto Complete */
    $("#search-input").typeahead({
	items : 7,
	minLength : 1,
	source : function(query, process) {
	    if (lang["name"] == "ZawgyiOne") {
		query = Z1_Uni(query);
	    }
	    var sby = detectlanguage(query);
	    query = encodeURI(query);
	    return $.ajax({
		crossDomain : true,
		async : "false",
		processData : true,
		type : "GET",
		url : rootURL + "/GetWordList.do?sby=" + sby[0] + "&input=" + query,
		success : function(results) {
		    console.log("get data " + query);
		    if (lang["name"] == "ZawgyiOne") {
			results = Uni_Z1(results);
		    }
		    var availableTags = results.split(",");
		    console.log(availableTags);
		    return process(availableTags);
		}
	    });
	}
    });
});

/* Click back top */
$("#back-top").click(function() {
    gotoTop();
});

/* Back top function */
function gotoTop() {
    $("body,html").animate({
	scrollTop : 0
    }, 800);
    return false;
}

/* Goto Search Result */
function gotoSearchResult() {
    $("body,html").animate({
	scrollTop : $("#search-btn").offset().top + 50
    }, 800);
    return false;
}

/* System Dialog */
function systemDialog(mtitle, msg) {
    if (mtitle == null) {
	mtitle = "System Alert";
    }
    if (msg == null) {
	msg = "This function is currently unavailable !";
    }
    BootstrapDialog.show({
	title : mtitle,
	message : msg,
	buttons : [ {
	    label : "Close",
	    hotkey : 67,
	    action : function(dialogItself) {
		dialogItself.close();
	    }
	} ]
    });
}

/* Old function */
var mm3 = new Array();
mm3["name"] = "Myanmar3"; 
mm3["lbl-insert-form"] = "ဝေါဟာရအသစ် ထည့်မည်"; 
mm3["lbl-wt"] = "ဝါစင်္ဂ"; 
mm3["lbl-insert-btn"] = "ထည့်သွင်းမည်"; 
mm3["lbl-insertoneword"] = "စာလုံးတစ်လုံးအနည်းဆုံးရိုက်ထည့်ပါ"; 
mm3["lbl-input"] = "ရှာလိုသည်ကိုရိုက်ထည့်ပါ ..."; 
mm3["lbl-search"] = "ရှာရန်"; 
mm3["lbl-newword"] = "ဝေါဟာရအသစ်ထပ်ထည့်ရန်"; 
mm3["lbl-insertlang"] = "ထည့်သွင်းသောဘာသာ"; 
mm3["lbl-unknow"] = "မသိပါ"; 
mm3["lbl-en"] = "အင်္ဂလိပ်ဘာသာ"; 
mm3["lbl-mm"] = "မြန်မာဘာသာ"; 
mm3["lbl-jm"] = "ဂျပန်ဘာသာ"; 
mm3["lbl-jk"] = "ဂျပန်ဘာသာ (ခန်းဂျီး)"; 
mm3["lbl-edit-form"] = "ပြင်ဆင်မည်";
mm3["lbl-correct"] = "မှန်သည်"; 
mm3["lbl-incorrect"] = "မှားသည်"; 
mm3["lbl-delete"] = "ဖျက်မည်"; 
mm3["msg-err-db"] = "ဒေတာဘေ့ ကို ဆက်သွယ် လို့မရပါ။"; 
mm3["msg-err-lang"] = "ထည့်သွင်းသောဘာသာစကား မှားယွင်းနေပါသည်။"; 
mm3["msg-err-advice"]="စကားလုံး မှန်/မှား အကြံအဉာဏ်ပေးခြင်း မအောင်မြင်ပါ။";
mm3["msg-err-en"] = "အင်္ဂလိပ်အက္ခရာ ထည့်သွင်းပေးရမည်ဖြစ်သည်။"; 
mm3["msg-err-mm"] = "မြန်မာအက္ခရာ ထည့်သွင်းပေးရမည်ဖြစ်သည်။"; 
mm3["msg-err-jm"] = "ဂျပန် Katakana သို့မဟုတ် Hiragana ထည့်သွင်းပေးရမည်ဖြစ်သည်။"; 
mm3["msg-err-jk"] = "ဂျပန်ခန်းဂျီး ထည့်သွင်းပေးရမည်ဖြစ်သည်။"; 
mm3["msg-meaning"] = "၏အဓိပ္ပါယ်မှာ"; 
mm3["msg-is"] = "ဖြစ်သည်။"; 
mm3["msg-searching"] = "ရှာနေပါသည်"; 
mm3["msg-selectwt"] = "ဝါစင်္ဂ အမျိုးအစား ရွေးချယ်ပေးရန်လိုအပ်ပါသည်။"; 
mm3["msg-err-atlest2"] = "အနည်းဆုံး ဘာသာနှစ်ခု ရိုက်သွင်းပါ။";
mm3["msg-editnote"] = "မှတ်ချက်။ အနည်းဆုံး ၃ ယောက် က အမှား ဟု သက်မှတ်မှသာ ဖျက်ပိုင်ခွင့် ရှိမည်ဖြစ်သည်။"; 
mm3["msg-insertnote"] = "မှတ်ချက်။ အဘိဓာန်တွင် ဂျပန်ဘာသာ ဆိုသည်မှာ Katakana သို့မဟုတ် Hiragana ကိုထည့်သွင်းရန် ဆိုလိုခြင်းဖြစ်သည်။ အဓိပ္ပါယ်တူပါက စာလုံးများကို ကော်မာ သို့မဟုတ် ပုဒ်ကလေး အသုံးပြု၍ ထည့်သွင်းနိုင်သည်။ အကွက်အားလုံးကို ဖြည့်သွင်းပေးရန် မလိုပါ။";

var zawgyi = new Array();
zawgyi["name"] = "ZawgyiOne";
zawgyi["lbl-insert-form"] = Uni_Z1(mm3["lbl-insert-form"]);
zawgyi["lbl-wt"] = Uni_Z1(mm3["lbl-wt"]);
zawgyi["lbl-insert-btn"] = Uni_Z1(mm3["lbl-insert-btn"]);
zawgyi["lbl-insertoneword"] = Uni_Z1(mm3["lbl-insertoneword"]);
zawgyi["lbl-input"] = Uni_Z1(mm3["lbl-input"]);
zawgyi["lbl-search"] = Uni_Z1(mm3["lbl-search"]);
zawgyi["lbl-newword"] = Uni_Z1(mm3["lbl-newword"]);
zawgyi["lbl-insertlang"] = Uni_Z1(mm3["lbl-insertlang"]);
zawgyi["lbl-unknow"] = Uni_Z1(mm3["lbl-unknow"]);
zawgyi["lbl-en"] = Uni_Z1(mm3["lbl-en"]);
zawgyi["lbl-mm"] = Uni_Z1(mm3["lbl-mm"]);
zawgyi["lbl-jm"] = Uni_Z1(mm3["lbl-jm"]);
zawgyi["lbl-jk"] = Uni_Z1(mm3["lbl-jk"]);
zawgyi["lbl-edit-form"] = Uni_Z1(mm3["lbl-edit-form"]);
zawgyi["lbl-correct"] = Uni_Z1(mm3["lbl-correct"]);
zawgyi["lbl-incorrect"] = Uni_Z1(mm3["lbl-incorrect"]);
zawgyi["lbl-delete"] = Uni_Z1(mm3["lbl-delete"]);
zawgyi["msg-err-db"] = Uni_Z1(mm3["msg-err-db"]);
zawgyi["msg-err-lang"] = Uni_Z1(mm3["msg-err-lang"]);
zawgyi["msg-err-advice"] = Uni_Z1(mm3["msg-err-advice"]);
zawgyi["msg-err-en"] = Uni_Z1(mm3["msg-err-en"]);
zawgyi["msg-err-mm"] = Uni_Z1(mm3["msg-err-mm"]);
zawgyi["msg-err-jm"] = Uni_Z1(mm3["msg-err-jm"]);
zawgyi["msg-err-jk"] = Uni_Z1(mm3["msg-err-jk"]);
zawgyi["msg-meaning"] = Uni_Z1(mm3["msg-meaning"]);
zawgyi["msg-is"] = Uni_Z1(mm3["msg-is"]);
zawgyi["msg-searching"] = Uni_Z1(mm3["msg-searching"]);
zawgyi["msg-selectwt"] = Uni_Z1(mm3["msg-selectwt"]);
zawgyi["msg-err-atlest2"] = Uni_Z1(mm3["msg-err-atlest2"]);
zawgyi["msg-editnote"] = Uni_Z1(mm3["msg-editnote"]);
zawgyi["msg-insertnote"] = Uni_Z1(mm3["msg-insertnote"]);

/**
 * Default Myanmar Font System
 */
var lang = zawgyi;
function changelang(value) {
    if (value == "mm3") {
	lang = mm3;
    } else if (value == "zawgyi") {
	lang = zawgyi;
    }
    document.getElementById("lbl-insert-form").innerHTML = lang["lbl-insert-form"];
    document.getElementById("lbl-wt").innerHTML = lang["lbl-wt"];
    document.getElementById("lbl-en").innerHTML = lang["lbl-en"];
    document.getElementById("lbl-jk").innerHTML = lang["lbl-jk"];
    document.getElementById("lbl-jm").innerHTML = lang["lbl-jm"];
    document.getElementById("lbl-mm").innerHTML = lang["lbl-mm"];
    document.getElementById("btn-insert2").innerHTML = lang["lbl-insert-btn"];
    document.getElementById("lbl-insertnote").innerHTML = lang["msg-insertnote"];
    document.getElementById("detres").innerHTML = lang["lbl-insertoneword"];
    document.getElementById("search-input").placeholder = lang["lbl-input"];
    document.getElementById("search-input").value = '';
    document.getElementById("lbl-search").innerHTML = lang["lbl-search"];
    document.getElementById("lbl-newword").innerHTML = lang["lbl-newword"];
    document.getElementById("search-result").innerHTML = '';
}
changelang("zawgyi");

/**
 * Show alert message on container using with Twitter Bootstrap 3.
 * @param {container} Html parent elements, id or class name. (*) mandatory
 * @param {message} Alert message string. (*) mandatory
 * @param {type} success, info, warning, danger (default)
 * @param {autoclose} true (default 5 second), false, millisecond
 * @param {remove} true (default), false
 */
function showAlert(container, message, type, autoclose, remove) {
    var $container = $(container);
    if (type && (type == "success" || type == "info" || type == "warning")) {
	type = 'alert-' + type;
    } else {
	type = 'alert-danger';
    }
    var $alert = $('<div class="alert ' + type + ' text-left" role="alert">' + '<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>' + '<p>' + message + '</p></div>').hide();
    if (remove != false)
	$container.find('div.alert').remove();
    $container.prepend($alert);
    $alert.slideDown();
    if (autoclose != false) {
	var time = 5000;
	if (typeof autoclose === 'number' && autoclose % 1 === 0)
	    time = autoclose;
	window.setTimeout(function() {
	    $alert.fadeTo(500, 0, function() {
		$alert.slideUp(300, null, function() {
		    $alert.remove();
		});
	    });
	}, time);
    }
}

/**
 * This is main search function with ajax
 * 
 * @param input
 */
function search(input) {
    var $searchResult = $("#search-result");
    var $result = null;
    if (input != "" && input != " " && input != "  ") {
	var sby = detect(input);
	if (sby[0] != "") {
	    $result = $('<div class="panel panel-default" style=""><div class="panel-heading"><span class="text-primary">\u00A0 ' + input + ' </span>' + lang["msg-meaning"] + '</div><div class="panel-body"><p class="text-center"><img class="hidden-xs" src="img/search-icon.gif"><br/>' + lang["msg-searching"] + '</p>' + '<div class="progress visible-xs-block"><div class="progress-bar progress-bar-striped active"  role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div></div></div></div>');
	    $searchResult.html($result);
	    gotoSearchResult();
	    if (lang["name"] == "ZawgyiOne") {
		input = Z1_Uni(input);
	    }
	    input = encodeURI(input);
	    $.ajax({
		url : rootURL + "/Search.do?input=" + input + "&sby=" + sby[0],
		success : function(data) {
		    if (data == "fail") {
			systemDialog(null, lang["msg-err-db"]);
			$searchResult.html('');
			gotoTop();
		    } else {
			if (lang["name"] == "ZawgyiOne") {
			    data = Uni_Z1(data);
			}
			$result = $('' + data);
			$searchResult.html($result);
			gotoSearchResult();
		    }
		}
	    });
	} else {
	    systemDialog("Warning", lang["msg-err-lang"]);
	    $searchResult.html('');
	    gotoTop();
	}
    } else {
	$searchResult.html('');
	gotoTop();
    }
}

/**
 * Change input detect status
 * 
 * @param input
 * @returns {Array}
 */
function detect(input) {
    var sby = new Array();
    if (input != "") {
	sby = detectlanguage(input);
	document.getElementById("detres").innerHTML = lang["lbl-insertlang"] + " = " + sby[1];
    } else {
	document.getElementById("detres").innerHTML = lang["lbl-insertoneword"];
    }
    return sby;
}

/**
 * Return sby sby[0] = en, mm, jm, jk syb[1] = lang["lbl-en"], lang["lbl-mm"],
 * lang["lbl-jm"], lang["lbl-jk"]
 * 
 * @param input
 * @returns {Array}
 */
function detectlanguage(input) {
    input = input.trim();
    var sby = new Array();
    if (input.length > 0 && input.charAt(0) == "(") {
	if (input.length > 1 && input.charAt(1) >= "0" && input.charAt(1) <= "z") {
	    sby[0] = "en";
	    sby[1] = lang["lbl-en"];
	    for (var i = 2; i < input.length; i++) {
		var c = input.charAt(i);
		if ((c < "0" || c > "z" || c == ".") && (c != "(" && c != ")" && c != " " && c != ",")) {
		    sby[0] = "";
		    sby[1] = lang["lbl-unknow"];
		    break;
		}
	    }
	} else if ((input.length > 1 && input.charAt(1) >= "\u1000" && input.charAt(1) <= "\u109F") || input.charAt(1) == "\uFEFF") {
	    sby[0] = "mm";
	    sby[1] = lang["lbl-mm"];
	    for (var i = 2; i < input.length; i++) {
		var c = input.charAt(i);
		if ((c < "\u1000" || c > "\u109F" || c == "။") && (c != "(" && c != ")" && c != " " && c != "၊" && c != "\uFEFF")) {
		    sby[0] = "";
		    sby[1] = lang["lbl-unknow"];
		    break;
		}
	    }
	} else if (input.length > 1 && input.charAt(1) >= "あ" && input.charAt(1) <= "￥") {
	    /*
	     * (input>="あ" && input<="ロ") (input>="一" && input<="頻")
	     */
	    sby[0] = "jm";
	    sby[1] = lang["lbl-jm"];
	    for (var i = 1; i < input.length; i++) {
		var c = input.charAt(i);
		if ((c < "あ" || c > "￥") && (c != "(" && c != ")" && c != " " && c != "、")) {
		    sby[0] = "";
		    sby[1] = lang["lbl-unknow"];
		    break;
		}
		if (c >= "一" && c <= "頻") {
		    sby[0] = "jk";
		    sby[1] = lang["lbl-jk"];
		}
	    }
	} else {
	    sby[0] = "";
	    sby[1] = lang["lbl-unknow"];
	}
    } else if (input >= "0" && input <= "z") {
	sby[0] = "en";
	sby[1] = lang["lbl-en"];
	for (var i = 1; i < input.length; i++) {
	    var c = input.charAt(i);
	    if ((c < "0" || c > "z") && (c != "(" && c != ")" && c != " " && c != ",")) {
		sby[0] = "";
		sby[1] = lang["lbl-unknow"];
		break;
	    }
	}
    } else if (input >= "\u1000" && input <= "\u109F") {
	sby[0] = "mm";
	sby[1] = lang["lbl-mm"];
	for (var i = 1; i < input.length; i++) {
	    var c = input.charAt(i);
	    if ((c < "\u1000" || c > "\u109F" || c == "။") && (c != "(" && c != ")" && c != " " && c != "၊" && c != "\uFEFF")) {
		sby[0] = "";
		sby[1] = lang["lbl-unknow"];
		break;
	    }
	}
    } else if (input >= "あ" && input <= "￥") {
	/*
	 * (input>="あ" && input<="ロ") (input>="一" && input<="頻")
	 */
	sby[0] = "jm";
	sby[1] = lang["lbl-jm"];
	for (var i = 0; i < input.length; i++) {
	    var c = input.charAt(i);
	    if ((c < "あ" || c > "￥") && (c != "(" && c != ")" && c != " " && c != "、")) {
		sby[0] = "";
		sby[1] = lang["lbl-unknow"];
		break;
	    }
	    if (input.charAt(i) >= "一" && input.charAt(i) <= "頻") {
		sby[0] = "jk";
		sby[1] = lang["lbl-jk"];
	    }
	}
    } else if (input == "") {
	sby[0] = "blank";
	sby[1] = "";
    } else {
	sby[0] = "";
	sby[1] = lang["lbl-unknow"];
    }
    return sby;
}

/**
 * This function is edit the word correct, incorrect or delete
 * 
 * @param word
 * @param id
 * @param table
 * @param correct
 * @param incorrect
 */
function editDialog(word, id, table, correct, incorrect) {
    var btn_class = "";
    var delete_class = " disabled";
    if ($(window).width() < 768) {
	btn_class = " btn-block";
    }
    if (incorrect > 2) {
	delete_class = "";
    }
    var searchHidden = $('#search-hidden').val();
    BootstrapDialog.show({
	title : lang["lbl-edit-form"],
	message : " <span class='text-primary'>\u00A0" + searchHidden + "</span>\u00A0" + lang["msg-meaning"] + "<span class='text-primary'>\u00A0" + word + "</span>\u00A0" + lang["msg-is"] + "<br/><br/><small class='text-warning'>" + lang["msg-editnote"] + "</small>",
	buttons : [ {
	    icon : 'glyphicon glyphicon-ok',
	    label : lang["lbl-correct"] + " <span class='badge'>" + correct + "</span>",
	    hotkey : 67,
	    cssClass : "btn-success" + btn_class,
	    autospin : true,
	    action : function(dialogItself) {
		dialogItself.enableButtons(false);
		$.ajax({
		    url : rootURL + '/IncreaseCorrect.do',
		    data : {
			't' : table,
			'id' : id
		    },
		    complete : function() {
			dialogItself.close();
		    },
		    success : function(data) {
			var res = "" + data;
			if (res == "success") {
			    var s = document.getElementById("search-hidden").value;
			    search(s);
			} else {
			    systemDialog(null, lang["msg-err-advice"]);
			}
		    }
		});
	    }
	}, {
	    icon : 'glyphicon glyphicon-remove',
	    label : lang["lbl-incorrect"] + " <span class='badge'>" + incorrect + "</span>",
	    hotkey : 73,
	    cssClass : "btn-warning" + btn_class,
	    autospin : true,
	    action : function(dialogItself) {
		dialogItself.enableButtons(false);
		$.ajax({
		    url : rootURL + '/IncreaseIncorrect.do',
		    data : {
			't' : table,
			'id' : id
		    },
		    complete : function() {
			dialogItself.close();
		    },
		    success : function(data) {
			var res = "" + data;
			if (res == "success") {
			    var s = document.getElementById("search-hidden").value;
			    search(s);
			} else {
			    systemDialog(null, lang["msg-err-advice"]);
			}
		    }
		});
	    }
	}, {
	    icon : 'glyphicon glyphicon-trash',
	    label : lang["lbl-delete"],
	    hotkey : 68,
	    cssClass : "btn-danger" + btn_class + delete_class,
	    autospin : true,
	    action : function(dialogItself) {
		dialogItself.enableButtons(false);
		$.ajax({
		    url : rootURL + '/Delete.do',
		    data : {
			't' : table,
			'id' : id
		    },
		    complete : function() {
			dialogItself.close();
		    },
		    success : function(data) {
			var res = "" + data;
			if (res == "success") {
			    var s = document.getElementById("search-hidden").value;
			    search(s);
			} else {
			    systemDialog(null, lang["msg-err-advice"]);
			}
		    }
		});
	    }
	} ]
    });
}

/**
 * Insert new word function
 */
function insertWord() {
    var $insertModal = $('#wordInsertModal');
    var $wtId = $("#wtId");
    var $wordEn = $("#wordEn");
    var $wordJk = $("#wordJk");
    var $wordJm = $("#wordJm");
    var $wordMm = $("#wordMm");
    var $formWtId = $('#form-wtId');
    var $formWordEn = $('#form-wordEn');
    var $formWordJk = $('#form-wordJk');
    var $formWordJm = $('#form-wordJm');
    var $formWordMm = $('#form-wordMm');
    var $btn_insert = $("#btn-insert2");
    var $ico_insert = $btn_insert.find('.button-icon');

    var wtId = $wtId.val();
    var wordEn = $wordEn.val();
    var wordJk = $wordJk.val();
    var wordJm = $wordJm.val();
    var wordMm = $wordMm.val();
    if (lang["name"] == "ZawgyiOne") {
	wordEn = Z1_Uni(wordEn);
	wordJk = Z1_Uni(wordJk);
	wordJm = Z1_Uni(wordJm);
	wordMm = Z1_Uni(wordMm);
    }

    var langEn = detectlanguage(wordEn);
    var langJk = detectlanguage(wordJk);
    var langJm = detectlanguage(wordJm);
    var langMm = detectlanguage(wordMm);

    $formWtId.removeClass('has-success');
    $formWtId.find(".form-control-feedback").remove();
    $formWordEn.removeClass('has-success');
    $formWordEn.find(".form-control-feedback").remove();
    $formWordJk.removeClass('has-success');
    $formWordJk.find(".form-control-feedback").remove();
    $formWordJm.removeClass('has-success');
    $formWordJm.find(".form-control-feedback").remove();
    $formWordMm.removeClass('has-success');
    $formWordMm.find(".form-control-feedback").remove();
    var inputCount = 0;
    if (wtId == "default") {
	showAlert($insertModal.find(".modal-body"), lang["msg-selectwt"], "warning", false, true);
	$wtId.focus();
	return false;
    } else {
	$formWtId.addClass('has-success');
	$formWtId.append('<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
	if (wordEn != "") {
	    if (langEn[0] == "en") {
		$formWordEn.addClass('has-success');
		$formWordEn.append('<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
	    } else {
		showAlert($insertModal.find(".modal-body"), lang["msg-err-en"], "warning", false, true);
		$wordEn.focus();
		return false;
	    }
	}
	if (wordJk != "") {
	    if (langJk[0] == "jk") {
		$formWordJk.addClass('has-success');
		$formWordJk.append('<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
	    } else {
		showAlert($insertModal.find(".modal-body"), lang["msg-err-jk"], "warning", false, true);
		$wordJk.focus();
		return false;
	    }
	}
	if (wordJm != "") {
	    if (langJm[0] == "jm") {
		$formWordJm.addClass('has-success');
		$formWordJm.append('<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
	    } else {
		showAlert($insertModal.find(".modal-body"), lang["msg-err-jm"], "warning", false, true);
		$wordJm.focus();
		return false;
	    }
	}
	if (wordMm != "") {
	    if (langMm[0] == "mm") {
		$formWordMm.addClass('has-success');
		$formWordMm.append('<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
	    } else {
		showAlert($insertModal.find(".modal-body"), lang["msg-err-mm"], "warning", false, true);
		$wordMm.focus();
		return false;
	    }
	}

	if ((wordEn != "" && wordJk != "") || (wordEn != "" && wordJm != "") || (wordEn != "" && wordMm != "") || (wordJk != "" && wordJm != "") || (wordJk != "" && wordMm != "") || (wordJm != "" && wordMm != "")) {
	    $btn_insert.attr('disabled', 'disabled');
	    $wtId.attr('disabled', 'disabled');
	    $wordEn.attr('disabled', 'disabled');
	    $wordJk.attr('disabled', 'disabled');
	    $wordJm.attr('disabled', 'disabled');
	    $wordMm.attr('disabled', 'disabled');
	    $ico_insert.hide();
	    $btn_insert.prepend('<span class="glyphicon glyphicon-asterisk icon-spin"></span>');

	    $(document).ready(function() {
		$.ajax({
		    url : rootURL + "/InsertWord.do",
		    data : {
			'wtId' : wtId,
			'wordEn' : wordEn,
			'wordJk' : wordJk,
			'wordJm' : wordJm,
			'wordMm' : wordMm
		    },
		    complete : function() {
			$insertModal.modal('hide');
		    },
		    success : function(data) {
			if (lang["name"] == "ZawgyiOne") {
			    data = Uni_Z1(data);
			}
			var res = "" + data;
			systemDialog("System Information", res);
			$wtId.val("default");
			$wordEn.val("");
			$wordJk.val("");
			$wordJm.val("");
			$wordMm.val("");
			$insertModal.find('div.alert').remove();
			$btn_insert.removeAttr('disabled');
			$wtId.removeAttr('disabled');
			$wordEn.removeAttr('disabled');
			$wordJk.removeAttr('disabled');
			$wordJm.removeAttr('disabled');
			$wordMm.removeAttr('disabled');
			$formWtId.removeClass('has-success');
			$formWtId.find(".form-control-feedback").remove();
			$formWordEn.removeClass('has-success');
			$formWordEn.find(".form-control-feedback").remove();
			$formWordJk.removeClass('has-success');
			$formWordJk.find(".form-control-feedback").remove();
			$formWordJm.removeClass('has-success');
			$formWordJm.find(".form-control-feedback").remove();
			$formWordMm.removeClass('has-success');
			$formWordMm.find(".form-control-feedback").remove();
			$ico_insert.show();
			$btn_insert.find(".icon-spin").remove();
		    }
		});
	    });
	} else {
	    showAlert($insertModal.find(".modal-body"), lang["msg-err-atlest2"], "warning", false, true);
	    $wordEn.focus();
	}
    }
}

/**************************iiquick-main script end****************************/

// JavaScript Document
// Version: 1.0
// Author: The` The` Aye
// Release:August 07,2009
// Name:Unicode5.1 to ZawgyiOne Converter
function Uni_Z1(utext) {
    var zgtext = utext;

    zgtext = zgtext.replace(/\u104E\u1004\u103A\u1038/g, '\u104E');
    zgtext = zgtext.replace(/\u102B\u103A/g, '\u105A');
    zgtext = zgtext.replace(/\u102D\u1036/g, '\u108E');
    zgtext = zgtext.replace(/\u103F/g, '\u1086');

    zgtext = zgtext.replace(/(\u102F[\u1036]?)\u1037/g, function($0, $1) {
	return $1 ? $1 + '\u1094' : $0 + $1;
    });
    zgtext = zgtext.replace(/(\u1030[\u1036]?)\u1037/g, function($0, $1) {
	return $1 ? $1 + '\u1094' : $0 + $1;
    });
    zgtext = zgtext.replace(/(\u1014[\u103A\u1032]?)\u1037/g, function($0, $1) {
	return $1 ? $1 + '\u1094' : $0 + $1;
    });
    zgtext = zgtext.replace(/(\u103B[\u1032\u1036]?)\u1037/g, function($0, $1) {
	return $1 ? $1 + '\u1095' : $0 + $1;
    });

    zgtext = zgtext.replace(/(\u103D[\u1032]?)\u1037/g, function($0, $1) {
	return $1 ? $1 + '\u1095' : $0 + $1;
    });

    zgtext = zgtext.replace(/([\u103B\u103C\u103D][\u102D\u1036]?)\u102F/g, function($0, $1) {
	return $1 ? $1 + '\u1033' : $0 + $1;
    });
    zgtext = zgtext.replace(/((\u1039[\u1000-\u1021])[\u102D\u1036]?)\u102F/g, function($0, $1) {
	return $1 ? $1 + '\u1033' : $0 + $1;
    });
    zgtext = zgtext.replace(/([\u100A\u100C\u1020\u1025\u1029][\u102D\u1036]?)\u102F/g, function($0, $1) {
	return $1 ? $1 + '\u1033' : $0 + $1;
    });
    zgtext = zgtext.replace(/([\u103B\u103C][\u103D]?[\u103E]?[\u102D\u1036]?)\u1030/g, function($0, $1) {
	return $1 ? $1 + '\u1034' : $0 + $1;

    });
    // uu - 2
    zgtext = zgtext.replace(/((\u1039[\u1000-\u1021])[\u102D\u1036]?)\u1030/g, function($0, $1) {
	return $1 ? $1 + '\u1034' : $0 + $1;

    });
    // uu - 2
    zgtext = zgtext.replace(/([\u100A\u100C\u1020\u1025\u1029][\u102D\u1036]?)\u1030/g, function($0, $1) {
	return $1 ? $1 + '\u1034' : $0 + $1;

    });
    // uu - 2

    zgtext = zgtext.replace(/(\u103C)\u103E/g, function($0, $1) {
	return $1 ? $1 + '\u1087' : $0 + $1;

    });
    // ha - 2

    zgtext = zgtext.replace(/\u1009(?=[\u103A])/g, '\u1025');
    zgtext = zgtext.replace(/\u1009(?=\u1039[\u1000-\u1021])/g, '\u1025');

    // E render
    zgtext = zgtext.replace(/([\u1000-\u1021\u1029])(\u1039[\u1000-\u1021])?([\u103B-\u103E\u1087]*)?\u1031/g, "\u1031$1$2$3");

    // Ra render

    zgtext = zgtext.replace(/([\u1000-\u1021\u1029])(\u1039[\u1000-\u1021\u1000-\u1021])?(\u103C)/g, "$3$1$2");

    // Kinzi
    zgtext = zgtext.replace(/\u1004\u103A\u1039/g, "\u1064");
    // kinzi
    zgtext = zgtext.replace(/(\u1064)([\u1031]?)([\u103C]?)([\u1000-\u1021])\u102D/g, "$2$3$4\u108B");
    // reordering kinzi lgt
    zgtext = zgtext.replace(/(\u1064)(\u1031)?(\u103C)?([ \u1000-\u1021])\u102E/g, "$2$3$4\u108C");
    // reordering kinzi lgtsk
    zgtext = zgtext.replace(/(\u1064)(\u1031)?(\u103C)?([ \u1000-\u1021])\u1036/g, "$2$3$4\u108D");
    // reordering kinzi ttt
    zgtext = zgtext.replace(/(\u1064)(\u1031)?(\u103C)?([ \u1000-\u1021])/g, "$2$3$4\u1064");
    // reordering kinzi

    // Consonant

    zgtext = zgtext.replace(/\u100A(?=[\u1039\u102F\u1030])/g, "\u106B");
    // nnya - 2
    zgtext = zgtext.replace(/\u100A/g, "\u100A");
    // nnya

    zgtext = zgtext.replace(/\u101B(?=[\u102F\u1030])/g, "\u1090");
    // ra - 2
    zgtext = zgtext.replace(/\u101B/g, "\u101B");
    // ra

    zgtext = zgtext.replace(/\u1014(?=[\u1039\u103D\u103E\u102F\u1030])/g, "\u108F");
    // na - 2
    zgtext = zgtext.replace(/\u1014/g, "\u1014");
    // na

    // Stacked consonants
    zgtext = zgtext.replace(/\u1039\u1000/g, "\u1060");
    zgtext = zgtext.replace(/\u1039\u1001/g, "\u1061");
    zgtext = zgtext.replace(/\u1039\u1002/g, "\u1062");
    zgtext = zgtext.replace(/\u1039\u1003/g, "\u1063");
    zgtext = zgtext.replace(/\u1039\u1005/g, "\u1065");
    zgtext = zgtext.replace(/\u1039\u1006/g, "\u1066");
    // 1067
    zgtext = zgtext.replace(/([\u1001\u1002\u1004\u1005\u1007\u1012\u1013\u108F\u1015\u1016\u1017\u1019\u101D])\u1066/g, function($0, $1) {
	return $1 ? $1 + '\u1067' : $0 + $1;

    });
    // 1067
    zgtext = zgtext.replace(/\u1039\u1007/g, "\u1068");
    zgtext = zgtext.replace(/\u1039\u1008/g, "\u1069");

    zgtext = zgtext.replace(/\u1039\u100F/g, "\u1070");
    zgtext = zgtext.replace(/\u1039\u1010/g, "\u1071");
    // 1072 omit (little shift to right)
    zgtext = zgtext.replace(/([\u1001\u1002\u1004\u1005\u1007\u1012\u1013\u108F\u1015\u1016\u1017\u1019\u101D])\u1071/g, function($0, $1) {
	return $1 ? $1 + '\u1072' : $0 + $1;

    });
    // 1067
    zgtext = zgtext.replace(/\u1039\u1011/g, "\u1073");
    // \u1074 omit(little shift to right)
    zgtext = zgtext.replace(/([\u1001\u1002\u1004\u1005\u1007\u1012\u1013\u108F\u1015\u1016\u1017\u1019\u101D])\u1073/g, function($0, $1) {
	return $1 ? $1 + '\u1074' : $0 + $1;

    });
    // 1067
    zgtext = zgtext.replace(/\u1039\u1012/g, "\u1075");
    zgtext = zgtext.replace(/\u1039\u1013/g, "\u1076");
    zgtext = zgtext.replace(/\u1039\u1014/g, "\u1077");
    zgtext = zgtext.replace(/\u1039\u1015/g, "\u1078");
    zgtext = zgtext.replace(/\u1039\u1016/g, "\u1079");
    zgtext = zgtext.replace(/\u1039\u1017/g, "\u107A");
    zgtext = zgtext.replace(/\u1039\u1018/g, "\u107B");
    zgtext = zgtext.replace(/\u1039\u1019/g, "\u107C");
    zgtext = zgtext.replace(/\u1039\u101C/g, "\u1085");

    zgtext = zgtext.replace(/\u100F\u1039\u100D/g, "\u1091");
    zgtext = zgtext.replace(/\u100B\u1039\u100C/g, "\u1092");
    zgtext = zgtext.replace(/\u1039\u100C/g, "\u106D");
    zgtext = zgtext.replace(/\u100B\u1039\u100B/g, "\u1097");
    zgtext = zgtext.replace(/\u1039\u100B/g, "\u106C");
    zgtext = zgtext.replace(/\u100E\u1039\u100D/g, "\u106F");
    zgtext = zgtext.replace(/\u100D\u1039\u100D/g, "\u106E");

    zgtext = zgtext.replace(/\u1009(?=\u103A)/g, "\u1025");
    // u
    zgtext = zgtext.replace(/\u1025(?=[\u1039\u102F\u1030])/g, "\u106A");
    // u - 2
    zgtext = zgtext.replace(/\u1025/g, "\u1025");
    // u
    // ///////////////////////////////////

    zgtext = zgtext.replace(/\u103A/g, "\u1039");
    // asat

    zgtext = zgtext.replace(/\u103B\u103D\u103E/g, "\u107D\u108A");
    // ya wa ha
    zgtext = zgtext.replace(/\u103D\u103E/g, "\u108A");
    // wa ha

    zgtext = zgtext.replace(/\u103E\u102F/g, '\u1088');// ha u

    zgtext = zgtext.replace(/\u103E\u1030/g, '\u1089');// ha uu

    zgtext = zgtext.replace(/\u103B/g, "\u103A");
    // ya
    zgtext = zgtext.replace(/\u103C/g, "\u103B");
    // ra
    zgtext = zgtext.replace(/\u103D/g, "\u103C");
    // wa
    zgtext = zgtext.replace(/\u103E/g, "\u103D");
    // ha
    zgtext = zgtext.replace(/\u103A(?=[\u103C\u103D\u108A])/g, "\u107D");
    // ya - 2

    zgtext = zgtext.replace(/(\u100A(?:[\u102D\u102E\u1036\u108B\u108C\u108D\u108E])?)\u103D/g, function($0, $1) {
	// return $1 ? $1 + '\u1087 ' : $0 + $1;
	return $1 ? $1 + '\u1087' : $0;

    });
    // ha - 2

    zgtext = zgtext.replace(/\u103B(?=[\u1000\u1003\u1006\u100F\u1010\u1011\u1018\u101A\u101C\u101E\u101F\u1021])/g, "\u107E");
    // great Ra with wide consonants
    zgtext = zgtext.replace(/\u107E([\u1000-\u1021\u108F])(?=[\u102D\u102E\u1036\u108B\u108C\u108D\u108E])/g, "\u1080$1");
    // great Ra with upper sign
    zgtext = zgtext.replace(/\u107E([\u1000-\u1021\u108F])(?=[\u103C\u108A])/g, "\u1082$1");
    // great Ra with under signs

    zgtext = zgtext.replace(/\u103B([\u1000-\u1021\u108F])(?=[\u102D \u102E \u1036 \u108B \u108C \u108D \u108E])/g, "\u107F$1");
    // little Ra with upper sign

    zgtext = zgtext.replace(/\u103B([\u1000-\u1021\u108F])(?=[\u103C\u108A])/g, "\u1081$1");
    // little Ra with under signs

    zgtext = zgtext.replace(/(\u1014[\u103A\u1032]?)\u1037/g, function($0, $1) {
	return $1 ? $1 + '\u1094' : $0 + $1;

    });
    // aukmyint
    zgtext = zgtext.replace(/(\u1033[\u1036]?)\u1094/g, function($0, $1) {
	return $1 ? $1 + '\u1095' : $0 + $1;

    });
    // aukmyint
    zgtext = zgtext.replace(/(\u1034[\u1036]?)\u1094/g, function($0, $1) {
	return $1 ? $1 + '\u1095' : $0 + $1;

    });
    // aukmyint
    zgtext = zgtext.replace(/([\u103C\u103D\u108A][\u1032]?)\u1037/g, function($0, $1) {
	return $1 ? $1 + '\u1095' : $0 + $1;

    });
    // aukmyint
    return zgtext;

}
// Uni_Z1

/****************************************************************/

// JavaScript Document
// Version : 1.0
// Author : The` The` Aye
// Release : August 07, 2009
// Name : ZawgyiOne to Unicode5.1 Converter
function Z1_Uni(zgtext) {
    var utext = zgtext;

    utext = utext.replace(/\u106A/g, " \u1009");
    utext = utext.replace(/\u1025(?=[\u1039\u102C])/g, "\u1009");
    // new
    utext = utext.replace(/\u1025\u102E/g, "\u1026");
    // new
    utext = utext.replace(/\u106B/g, "\u100A");
    utext = utext.replace(/\u1090/g, "\u101B");
    utext = utext.replace(/\u1040/g, "\u1040");

    utext = utext.replace(/\u108F/g, "\u1014");
    utext = utext.replace(/\u1012/g, "\u1012");
    utext = utext.replace(/\u1013/g, "\u1013");
    // ///////////

    utext = utext.replace(/[\u103D\u1087]/g, "\u103E");
    // ha
    utext = utext.replace(/\u103C/g, "\u103D");
    // wa
    utext = utext.replace(/[\u103B\u107E\u107F\u1080\u1081\u1082\u1083\u1084]/g, "\u103C");
    // ya yint(ra)
    utext = utext.replace(/[\u103A\u107D]/g, "\u103B");
    // ya

    utext = utext.replace(/\u103E\u103B/g, "\u103B" + "\u103E");
    // reorder

    utext = utext.replace(/\u108A/g, "\u103D" + "\u103E");
    // wa ha

    // //////////////////// Reordering

    utext = utext.replace(/(\u1031)?(\u103C)?([\u1000-\u1021])\u1064/g, "\u1064$1$2$3");
    // reordering kinzi
    utext = utext.replace(/(\u1031)?(\u103C)?([\u1000-\u1021])\u108B/g, "\u1064$1$2$3\u102D");
    // reordering kinzi lgt
    utext = utext.replace(/(\u1031)?(\u103C)?([\u1000-\u1021])\u108C/g, "\u1064$1$2$3\u102E");
    // reordering kinzi lgtsk
    utext = utext.replace(/(\u1031)?(\u103C)?([\u1000-\u1021])\u108D/g, "\u1064$1$2$3\u1036");
    // reordering kinzi ttt

    // //////////////////////////////////////

    utext = utext.replace(/\u105A/g, "\u102B" + "\u103A");
    utext = utext.replace(/\u108E/g, "\u102D" + "\u1036");
    // lgt ttt
    utext = utext.replace(/\u1033/g, "\u102F");
    utext = utext.replace(/\u1034/g, "\u1030");
    utext = utext.replace(/\u1088/g, "\u103E" + "\u102F");
    // ha u
    utext = utext.replace(/\u1089/g, "\u103E" + "\u1030");
    // ha uu

    // /////////////////////////////////////

    utext = utext.replace(/\u1039/g, "\u103A");
    utext = utext.replace(/[\u1094\u1095]/g, "\u1037");
    // aukmyint

    // ///////////////////////////////////// Pasint order human error
    utext = utext.replace(/([\u1000-\u1021])([\u102C\u102D\u102E\u1032\u1036]){1,2}([\u1060\u1061\u1062\u1063\u1065\u1066\u1067\u1068\u1069\u1070\u1071\u1072\u1073\u1074\u1075\u1076\u1077\u1078\u1079\u107A\u107B\u107C\u1085])/g, "$1$3$2");
    // new

    // ///////////

    utext = utext.replace(/\u1064/g, "\u1004\u103A\u1039");

    utext = utext.replace(/\u104E/g, "\u104E\u1004\u103A\u1038");

    utext = utext.replace(/\u1086/g, "\u103F");

    utext = utext.replace(/\u1060/g, '\u1039\u1000');
    utext = utext.replace(/\u1061/g, '\u1039\u1001');
    utext = utext.replace(/\u1062/g, '\u1039\u1002');
    utext = utext.replace(/\u1063/g, '\u1039\u1003');
    utext = utext.replace(/\u1065/g, '\u1039\u1005');
    utext = utext.replace(/[\u1066\u1067]/g, '\u1039\u1006');
    utext = utext.replace(/\u1068/g, '\u1039\u1007');
    utext = utext.replace(/\u1069/g, '\u1039\u1008');
    utext = utext.replace(/\u106C/g, '\u1039\u100B');
    utext = utext.replace(/\u1070/g, '\u1039\u100F');
    utext = utext.replace(/[\u1071\u1072]/g, '\u1039\u1010');
    utext = utext.replace(/[\u1073\u1074]/g, '\u1039\u1011');
    utext = utext.replace(/\u1075/g, '\u1039\u1012');
    utext = utext.replace(/\u1076/g, '\u1039\u1013');
    utext = utext.replace(/\u1077/g, '\u1039\u1014');
    utext = utext.replace(/\u1078/g, '\u1039\u1015');
    utext = utext.replace(/\u1079/g, '\u1039\u1016');
    utext = utext.replace(/\u107A/g, '\u1039\u1017');
    utext = utext.replace(/\u107B/g, '\u1039\u1018');
    utext = utext.replace(/\u107C/g, '\u1039\u1019');
    utext = utext.replace(/\u1085/g, '\u1039\u101C');
    utext = utext.replace(/\u106D/g, '\u1039\u100C');

    utext = utext.replace(/\u1091/g, '\u100F\u1039\u100D');
    utext = utext.replace(/\u1092/g, '\u100B\u1039\u100C');
    utext = utext.replace(/\u1097/g, '\u100B\u1039\u100B');
    utext = utext.replace(/\u106F/g, '\u100E\u1039\u100D');
    utext = utext.replace(/\u106E/g, '\u100D\u1039\u100D');

    // ///////////////////////////////////////////////////////

    utext = utext.replace(/(\u103C)([\u1000-\u1021])(\u1039[\u1000-\u1021])?/g, "$2$3$1");
    // reordering ra

    utext = utext.replace(/(\u103E)(\u103D)([\u103B\u103C])/g, "$3$2$1");
    utext = utext.replace(/(\u103E)([\u103B\u103C])/g, "$2$1");

    utext = utext.replace(/(\u103D)([\u103B\u103C])/g, "$2$1");

    utext = utext.replace(/(([\u1000-\u101C\u101D\u101E-\u102A\u102C\u102E-\u103F\u104C-\u109F]))(\u1040)/g, function($0, $1) {
	return $1 ? $1 + '\u101D' : $0 + $1;

    });
    // zero and wa

    utext = utext.replace(/(\u1040)(?=([\u1040\u1047])*([\u1000-\u101C\u101D\u101E-\u102A\u102C\u102E-\u103F\u104C-\u109F]))/g, "\u101D"); // zero
    // and
    // wa

    utext = utext.replace(/(([\u1000-\u101C\u101D\u101E-\u102A\u102C\u102E-\u103F\u104C-\u109F]))(\u1047)/g, function($0, $1) {
	return $1 ? $1 + '\u101B' : $0 + $1;

    });
    // seven and ra

    utext = utext.replace(/(\u1047)(?=([\u1047])*([\u1000-\u101C\u101D\u101E-\u102A\u102C\u102E-\u103F\u104C-\u109F]))/g, "\u101B"); // seven
    // and
    // ra

    utext = utext.replace(/(\u1031)?([\u1000-\u1021])(\u1039[\u1000-\u1021])?([\u102D\u102E\u1032])?([\u1036\u1037\u1038]{0,2})([\u103B-\u103E]{0,3})([\u102F\u1030])?([\u1036\u1037\u1038]{0,2})([\u102D\u102E\u1032])?/g, "$2$3$6$1$4$9$7$5$8");
    // reordering storage order

    utext = utext.replace(/(\u103A)(\u1037)/g, "$2$1");
    // For Latest Myanmar3

    utext = utext.replace(/(\u1036)(\u102F)/g, "$2$1");
    // For Latest Myanmar3

    return utext;

}
// Z1_Uni

/****************************************************************/
