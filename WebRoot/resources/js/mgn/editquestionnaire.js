$(function(){
	$('#filter_type').change(getQuestions);
	$('#filter_suit').change(getQuestions);
	
	$('#okbtn').click(saveQuestionnaire);
	$('#cancelbtn').click(function(){
		history.go(-1);
	});
});

function removeQuestion(event){
	$(event.target).parents('tr').remove();
}

function upQuestion(event){
	var ob = event.target;
	$(event.target).parents('tr').prev().before($(event.target).parents('tr'));
}

function downQuestion(event){
	var ob = event.target;
	$(event.target).parents('tr').next().after($(event.target).parents('tr'));
}

var ids = [];
function getIntableQuestions(){
	ids=[];
	$('#questionsTable tbody tr').each(function(){
		var id = $(this).children().first().html();
		ids[ids.length] = id;
	});
}

function isIntable(id){
	var longid = parseInt(id);
	for(var i in ids ){
		var curid = parseInt(ids[i]);
		if( curid == longid )
			return true;
	}
	return false;
}

function getQuestions(){
	var param = {};
	param.type = $('#filter_type').val();
	param.suitability = $('#filter_suit').val();
	
	getIntableQuestions();
	
	var dataParam = {
			url : rootPath + "/mgn/question/getquestions",
			param : param,
			call : function(data){
				if( data && data.questions ){
					var html="";
					for( var i in data.questions ){
						var q = data.questions[i];
						if( !isIntable(q.id)){
							html += "<tr>";
							html += "<td><input type='checkbox' /><td>";
							html += "<td>"+q.id+"</td>";
							html += "<td>"+q.q+"</td>";
							html += "</tr>";
						}						
					}
					$('#questionsDiv table tbody').html(html);
				}
			}
	};
	getAjaxData(dataParam);
}

$('#addQuestionModal').on('show.bs.modal', function(event){
	getQuestions();
	var modal = $(this);
		
	modal.find('.modal-footer #save').unbind('click');
	modal.find('.modal-footer #save').click(function(){
		$('#questionsDiv input[type=checkbox]:checked').each(function(){
			var id = $(this).parent().next().next().html();
			var q = $(this).parent().next().next().next().html();
			var html = "<tr>";
			html += "<td>"+id+"</td>";
			html += "<td>"+q+"</td>";
			html += "<td><button class='customBtn deleteBtn' onclick='removeQuestion(event)'></button> <button class='customBtn upBtn' onclick='upQuestion(event)'></button> <button class='customBtn downBtn' onclick='downQuestion(event)'></button></td>"
			html += "</tr>";
			$('#questionsTable tbody').append(html);
		});
		modal.modal('hide');
	});
});

function saveQuestionnaire(){
	var param = {};
	var id = $('#qid').val();
	if( id != '' ){
		param.id=id;
	}
	var name = $('#qname').val();
	if( name == '' ){
		name = '未命名问卷';
	}
	param.name = name;
	
	getIntableQuestions();
	param.ids = ids;
	
	var dataParam = {
			url : rootPath + "/mgn/questionnaire/save",
			param : param,
			call : function(data){
				if( data && data.result ){
					if( data.result == true){
						showAlert('编辑成功', function(){
							history.go(-1);
						});
					}else{
						showAlert('编辑失败');
					}
				}else{
					showAlert('编辑失败');
				}
			},
			error:function(){
				showAlert('网络错误,请重试');
			}
	};
	getAjaxData(dataParam);
}