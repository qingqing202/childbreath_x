$(function(){
	getQuestions();
	$('#filter_type').change(getQuestions);
	$('#filter_suit').change(getQuestions);
});

function getQuestions(){
	var param = {};
	param.type = $('#filter_type').val();
	param.suitability = $('#filter_suit').val();
	
	var dataParam = {
			url : rootPath + "/mgn/question/getquestions",
			param : param,
			call : function(data){
				if( data && data.questions ){
					var html="";
					for( var i in data.questions ){
						var q = data.questions[i];
						html += "<div class='question-mgr '>";
						html += "<div class=''>";
						html += "<div class='q-q'><p>"+"Q"+(i-1+2)+" :&nbsp"+q.q+"</p></div>";
						if( 'select' == q.type && q.options && q.options.length>0){
							html += "<div class='q-opt'><ol class='upper-alpha'>";
							for( var p in q.options ){
								var opi = q.options[p];
								html += "<li>"+opi+"</li>";
							}
							html += "</ol></div>"
						}
						html += "</div><div class=''>";
						html += "<button class='customBtn editBtn' onclick='editQuestion("+q.id+")'></button>";
						html += "<button class='customBtn deleteBtn' onclick='deleteQuestion("+q.id+")'></button>";
						html += "</div>";
						html += "</div>";
					}
					$('#questionsDev').html(html);
				}
			}
	};
	getAjaxData(dataParam);
}

function editQuestion(id){
	var URL = rootPath+"/mgn/question/edit?id="+id;
	
	URL = URL.replace(/\#/g, "%23");
	URL = URL.replace(/\+/g, "%2B");
	URL = URL.replace(/\ /g, "%20");
	URL = encodeURI(URL);
	location.href =URL;//location.href
}

function deleteQuestion(id){
	confirmDelete(function(){
		var param = {};
		param.id=id;
		
		var dataParam = {
				url : rootPath + "/mgn/question/delete",
				param : param,
				call : function(data){
					if( data && data.result ){
						if( data.result == true){
							showAlert('删除成功');
						}else{
							showAlert('删除失败');
						}
					}else{
						showAlert('删除失败');
					}
				}
		};
		getAjaxData(dataParam);
	});
}