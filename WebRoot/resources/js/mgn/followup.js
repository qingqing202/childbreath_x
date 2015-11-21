$(function(){
	
});

function followup(){
	var param={};
	param.admission_number = $('#admission_number').html();
	param.questionnaireId = $('#selGroup').val();
	
	var dataParam = {
			url : rootPath + "/mgn/followup/savefollowup",
			param : param,
			call : function(data){
				if( data && data.result ){
					if( data.result == true){
						showAlert('操作成功', function(){
							history.go(-1);
						});
					}else{
						showAlert('操作失败');
					}
				}else{
					showAlert('操作失败');
				}
			},
			error:function(){
				showAlert('网络错误,请重试');
			}
	};
	getAjaxData(dataParam);
}