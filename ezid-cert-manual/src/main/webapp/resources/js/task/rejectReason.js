$(document).ready(function() {
	$("#submit_form_btn").click(function(){
		$("#reason_form").submit();
	});
});

function addRejectReason(id,name){
	$('#add_reject_reason_dialog #parentId').val(id);
	$('#add_reject_reason_dialog #parentName').val(name);
	$('#add_reject_reason_dialog').modal({backdrop:'static'});
}
function deleteRejectReason(id){
	confirmDialog("提示",
			"是否确认删除该理由?",
			function(){
				window.location="rejectReason/delete/"+id;
			},
			true);
}