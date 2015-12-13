function validLogin(){
	if( $('#name').val().length == 0 ){
		$('#name').focus();
		$('#tipMsg').text('请填写患儿姓名');
		return false;
	}
	if( $('#hospitalId').val().length == 0 ){
		$('#hospitalId').focus();
		$('#tipMsg').text('请填写住院号');
		return false;
	}
	if( $('#webchatId').val().length == 0 ){
		$('#webchatId').focus();
		$('#tipMsg').text('请填写微信号');
		return false;
	}
	if( $('#telNum').val().length == 0 ){
		$('#telNum').focus();
		$('#tipMsg').text('请填写联系电话');
		return false;
	}

	var val=$('input:radio[name="sex"]:checked').val();
	if(val==null) {
		$('#tipMsg').text('请选择患儿性别');
		return false;
	}

	return true;
}