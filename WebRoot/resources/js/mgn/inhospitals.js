$(function(){
	
});

function leave(admission_number){
	var param={};
	param.admission_number = admission_number;
	var dataParam = {
			url : rootPath + "/mgn/leavehospital/leavehospital",
			param : param,
			call : function(data){
				if( data && data.result ){
					if( data.result == true){
						showAlert('操作成功', function(){
							location.reload();
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