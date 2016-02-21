(function(){
	showStatus();
})();

function is_valid(){
	var s = clicked;
	if (s == "send") {
		if (($('#s11').is(':checked')) || ($('#s12').is(':checked'))) {
		} else {
			showDialog("请最少选择一个文件", "");
			return false;
		}
		if ($('#inputEmail3').val().length == 0) {
			$('#inputEmail3').focus();
			return false;
		}
		showLoadingToast();
		return true;
	}
}

function showStatus() {
	var s = document.getElementById("status").innerText;
	if ( s == null ) {
		return;
	}
	if ( s == "0") {
		return;
	} else if ( s == "-1" ) {
		showDialog("下载失败，请稍后重试","");
		return;
	} else if ( s == "-2" ) {
		showDialog("邮件发送失败，请稍后重试","");
		return;
	} else if ( s == "2" ) {
		showToast("邮件发送完成","");
		return;
	} else {
		return;
	}
}


function showToast() {
		var $toast = $('#toast');
		if ($toast.css('display') != 'none') {
			return;
		}
		$toast.show();
		setTimeout(function () {
			$toast.hide();
		}, 2000);
}

function showLoadingToast() {
	var $loadingToast = $('#loadingToast');
	if ($loadingToast.css('display') != 'none') {
		return;
	}

	$loadingToast.show();
	/*
	setTimeout(function () {
		$loadingToast.hide();
	}, 0);
	*/
}

function showDialog(str1, str2) {
	var $dialog = $('#dialog2');
	$("#dialog_t").html(str1);
	$("#dialog_p").html(str2);

	$dialog.show();
	$dialog.find('.weui_btn_dialog').one('click', function () {
		$dialog.hide();
	});
}