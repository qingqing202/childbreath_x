$(function(){
	getDiseases();
});

function getDiseases(){
	var dataParam = {
			url : rootPath + "/mgn/disease/getdiseases",
			param : {},
			call : function(data){
				if( data && data.diseases ){
					var html="";
					for( var i in data.diseases ){
						var q = data.diseases[i];
						html += "<tr>";
						html += "<td>"+(i-1+2)+"</td>";
						html += "<td>"+q.name+"</td>";
						html += "<td><button class='customBtn deleteBtn' onclick=deleteDisease('"+q.name+"')></button></td>";
						html += "</tr>"
					}
					$('#diseaseTable tbody').html(html);
				}
			}
	};
	getAjaxData(dataParam);
}

$('#addDiseaseModal').on('shown.bs.modal', function(event){
	var modal = $(this);
	var contentinput = modal.find('.modal-body input');
	contentinput.focus();
});

$('#addDiseaseModal').on('show.bs.modal', function(event){
	var modal = $(this);
	modal.find('.modal-body input').val('');
	modal.find('.modal-body form').unbind('submit');
	modal.find('.modal-body form').submit(function(){
		$('#save').click();
		return false;
	});
	
	modal.find('.modal-footer #save').unbind('click');
	modal.find('.modal-footer #save').click(function(){
		var param = {};
		param.name = $('#diseasename').val();
		var dataParam = {
				url : rootPath + "/mgn/disease/create",
				param : param,
				call : function(data){
					if( data ){
						if( 1 == data.result){
							getDiseases();
						}else if( 2 == data.result){
							showAlert('此疾病种类已存在，无需重复添加');
						}else{
							showAlert('添加失败');
						}						
					}else {
						showAlert('添加失败');
					}
				},
				error:function(){
					showAlert('网络错误,请重试');
				}
		};
		getAjaxData(dataParam);
	});
});

function deleteDisease(name){
	confirmDelete(function(){
		var param = {};
		param.name = name;
		var dataParam = {
				url : rootPath + "/mgn/disease/delete",
				param : param,
				call : function(data){
					if( data ){
						if(data.result){
							//showAlert('删除成功');
							getDiseases();						
						}else{
							showAlert('删除失败');
						}						
					}else {
						showAlert('删除失败');
					}
				},
				error:function(){
					showAlert('网络错误,请重试');
				}
		};
		getAjaxData(dataParam);
	});
}
