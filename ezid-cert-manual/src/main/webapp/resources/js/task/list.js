$(document).ready(function(){	
	initDataPicker();
	$('#search_form #timeCreateStart').datepicker ().on ('changeDate', function (e) {
		$('#search_form #timeCreateEnd').datepicker ('setStartDate', e.date);
	});
	$('#search_form #timeCreateEnd').datepicker ().on ('changeDate', function (e) {
		e.date.setDate(e.date.getDate() + 1);e.date.setDate(e.date.getDate() + 1);
		$('#search_form #timeCreateStart').datepicker ('setEndDate', e.date);
	});
	
	$('#search_form #dueDateStart').datepicker ().on ('changeDate', function (e) {
		$('#search_form #dueDateEnd').datepicker ('setStartDate', e.date);
	});
	$('#search_form #dueDateEnd').datepicker ().on ('changeDate', function (e) {
		e.date.setDate(e.date.getDate() + 1);
		$('#search_form #dueDateStart').datepicker ('setEndDate', e.date);
	});
});

function initDataPicker(){
	var timeCreateEnd = new Date(Date.parse($('#search_form #timeCreateEnd').val().replace(/-/g,   "/")));
	var dueDateEnd = new Date(Date.parse($('#search_form #dueDateEnd').val().replace(/-/g,   "/")));
	
	timeCreateEnd.setDate(timeCreateEnd.getDate() + 1);	
	dueDateEnd.setDate(dueDateEnd.getDate() + 1);
	
	$('#search_form #timeCreateEnd').datepicker ('setStartDate', new Date(Date.parse($('#search_form #timeCreateStart').val().replace(/-/g,   "/"))) );
	$('#search_form #timeCreateStart').datepicker ('setEndDate', timeCreateEnd);

	$('#search_form #dueDateEnd').datepicker ('setStartDate', new Date(Date.parse($('#search_form #dueDateStart').val().replace(/-/g,   "/"))) );
	$('#search_form #dueDateStart').datepicker ('setEndDate', dueDateEnd);
}

//弹出指派任务窗口
function assignTask(){
	var $form=$("#task_form");
	if($form.find("input[type=checkbox]:checked").length==0){
		alert_m("请至少勾选一个任务");
		return;
	}
	var btn = $("#submit_form_btn");
	btn.button('reset');
	$("#assignDialog").modal({backdrop:'static'});
}

//指派任务
function submitAssign() {
	var $form = $("#task_form");
	var originAssignees = new Array();
	$.each($("input[name='taskIds']:checked"), function() {
		var originAssignee = $(this).closest("tr").children().eq(2).text();
		originAssignees.push(originAssignee);
	});

	var input = $("<input>").attr({"type":"hidden", "name":"originAssignees"}).val(originAssignees);
	$form.append(input);
	
	$form.find("#assignee").val($("#assignDialogContent #select-input option:selected").val());
	
	var btn = $("#submit_form_btn");
	btn.button('loading');
	$form.submit();

	input.remove();
}

function page(n,s){	
	$("#search_form #pageStart").val(n);
	$("#search_form #pageSize").val(s);
	$("#search_form").attr("action","../task/listTask");
	$("#search_form").attr("target", "_self");
	$("#search_form").submit();
   	return false;
}

function toPage(){
    if($("#viewPageSize").val()==10){
        $("#search_form #pageStart").val(1);
        $("#search_form #pageSize").val(10);
    }
    if($("#viewPageSize").val()==50){
        $("#search_form #pageStart").val(1);
        $("#search_form #pageSize").val(50);
    }
    if($("#viewPageSize").val()==100){
        $("#search_form #pageStart").val(1);
        $("#search_form #pageSize").val(100);
    }
		$("#search_form").attr("action","../task/listTask");
		$("#search_form").attr("target", "_self");
		$("#search_form").submit();
}  

//提交搜索条件
function submitSearch(taskType){
	$("#search_form #pageStart").val(1);
	$("#search_form").attr("action","../task/listTask");
	$("#search_form").attr("target", "_self");
	$("#search_form").submit();
}

//清空搜索条件选项
function resetSearch(){
	$("#search_form .form-control").val("");	
	initDataPicker();
}
 