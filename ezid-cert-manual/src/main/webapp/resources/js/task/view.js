$(document).ready(function(){
	$('#commonDiv').hide();
	$("#submit_form_btn").click(function(){
		retrialTaskSubmit();
	});
});

function rotate_flv(id){
	$("#flv_"+id).toggle();
	$("#flv180_"+id).toggle();
}

function toRetrial(id){
	$("#retrialDialog").modal({backdrop:'static'});
}

function hideReason(){
	$('#commonDiv').hide();
}

function showReason(){
	$('#commonDiv').show();
	$('#commonMsg').text('');
}

function selectReason(reason,subReason){
	$('#common').val(reason + '-' + subReason);
}

function retrialTaskSubmit(){
	if($("input[name='result']:checked").val() == "pass"){
		$('#common').val('通过');
		$("#retrial_task_form").submit();
	}else if($('#common').val() == ""){
		$('#commonMsg').text('请选择备注！');
		return;
	}else{
		$("#retrial_task_form").submit();
	}
}