var Login = function() {
	"use strict";

	return {
		init : init
	};

	function init() {
		$.support.placeholder = false;
		var test = document.createElement('input');
		if ('placeholder' in test)
			$.support.placeholder = true;

		if (!$.support.placeholder) {
			$('#login-form').find('label').show();
		}
	}
}();

$(function() {

	Login.init();

});

$(function() {
	$('#captchaImg').click(function() {
		var timestamp = (new Date()).valueOf();
		$(this).attr('src', 'jcaptcha.jpg?timestamp=' + timestamp);
	});
});

window.onbeforeunload = function() {
	// 关闭窗口时自动退出
	if (event.clientX > 360 && event.clientY < 0 || event.altKey) {
		alert(parent.document.location);
	}
};

function changeCaptcha() {
	var timestamp = (new Date()).valueOf();
	$('#captchaImg').attr('src', 'jcaptcha.jpg?timestamp=' + timestamp);
	event.cancelBubble = true;
}
