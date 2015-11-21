$(function(){
	$('#qtype').change(function(){
		var v = $(this).val();
		if( "select" == v ){
			$('#optionsgroup').show();
		}else{
			$('#optionsgroup').hide();
		}
	});
	$('#okbtn').click(submitquestion);
	$('#cancelbtn').click(function(){
		history.go(-1);
	});
});

function deleteOption( btnid ){
	confirmDelete(function(){
		$(btnid).parent().remove();
	});
}

function submitEdit(){
	$('#save').click();
}

$('#editOptionModal').on('shown.bs.modal', function(event){
	var modal = $(this);
	var contentinput = modal.find('.modal-body input');
	contentinput.focus();
});

$('#editOptionModal').on('show.bs.modal', function(event){
	var btn = $(event.relatedTarget);
	var actiontype = btn.data('actiontype');
	var content = btn.data('content');
	var modal = $(this);
	var contentinput = modal.find('.modal-body input');
	if( actiontype == "edit" ){
		contentinput.val(content);
	}else{
		contentinput.val('');
	}
	
	modal.find('.modal-body form').unbind('submit');
	modal.find('.modal-body form').submit(function(){
		$('#save').click();
		return false;
	});
	
	modal.find('.modal-footer #save').unbind('click');
	modal.find('.modal-footer #save').click(function(){
		if( actiontype == "edit" ){
			var html = contentinput.val() + ' <button class="customBtn editBtn" data-toggle="modal" data-target="#editOptionModal" data-actiontype="edit" data-content="'+contentinput.val()+'"></button><button class="customBtn deleteBtn" onclick="deleteOption(this)"></button>';
			btn.parent().html(html);
		}else{
			var html = '<li class="optionsli" value="'+contentinput.val()+'">' + contentinput.val() + ' <button class="customBtn editBtn" data-toggle="modal" data-target="#editOptionModal" data-actiontype="edit" data-content="'+contentinput.val()+'"></button>&nbsp;&nbsp;<button class="customBtn deleteBtn" onclick="deleteOption(this)"></button></li>';
			btn.prev('ol').append(html);
		}
		modal.modal('hide');
	});
});

function submitquestion(){
	var options = [];
	var i = 0;
	$('#optionsgroup').find("li").each(function(){
		var v = $(this).html();
		var index = v.indexOf("<button");
		var s = v.substr(0, index);
		options[i] = s;
		i++;
	});
	
	var questionid = $('#questionid').val();
	
	var param = {};
	if( questionid != '' ){
		param.id=questionid;
	}
	param.type = $('#qtype').val();
	param.suitability = $('#qsuitability').val();
	param.q = $('#qcontent').val();;
	param.options = options;
	
	var dataParam = {
			url : rootPath + "/mgn/question/editquestion",
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