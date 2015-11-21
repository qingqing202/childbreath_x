$(function(){
	getQuestionnaires();
});

function getQuestionnaires(){
	var dataParam = {
			url : rootPath + "/mgn/questionnaire/getquestionnaires",
			param : {},
			call : function(data){
				if( data && data.questionnaires ){
					var html="";
					for( var i in data.questionnaires ){
						var q = data.questionnaires[i];
						html += "<tr>";
						html += "<td>"+q.id+"</td>";
						html += "<td><a href='"+rootPath + "/mgn/questionnaire/edit?id="+q.id+"'>"+q.name+"</a></td>";
						html += "<td><button class='customBtn deleteBtn' onclick=deleteQuestionnaire('"+q.id+"')></button></td>";
						html += "</tr>"
					}
					$('#questionnaireTable tbody').html(html);
				}
			}
	};
	getAjaxData(dataParam);
}

function deleteQuestionnaire(id){
	confirmDelete(function(){
		var param = {};
		param.id = id;
		var dataParam = {
				url : rootPath + "/mgn/questionnaire/delete",
				param : param,
				call : function(data){
					if( data ){
						if(data.result){
							showAlert('删除成功');
							getQuestionnaires();					
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