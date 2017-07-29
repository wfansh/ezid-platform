
$(document).ready(function(){
	initDataPicker();
	$('#search_form #timeCreateStart').datepicker ().on ('changeDate', function (e) {
		$('#search_form #timeCreateEnd').datepicker ('setStartDate', e.date);
	});
	$('#search_form #timeCreateEnd').datepicker ().on ('changeDate', function (e) {
		e.date.setDate(e.date.getDate() + 1);
		$('#search_form #timeCreateStart').datepicker ('setEndDate', e.date);
	});
	$('#search_form #timeFinishStart').datepicker ().on ('changeDate', function (e) {
		$('#search_form #timeFinishEnd').datepicker ('setStartDate', e.date);
	});
	$('#search_form #timeFinishEnd').datepicker ().on ('changeDate', function (e) {
		e.date.setDate(e.date.getDate() + 1);
		$('#search_form #timeFinishStart').datepicker ('setEndDate', e.date);
	});
});

function initDataPicker(){
	var timeCreateEnd = new Date(Date.parse($('#search_form #timeCreateEnd').val().replace(/-/g,   "/")));
	var timeFinishEnd = new Date(Date.parse($('#search_form #timeFinishEnd').val().replace(/-/g,   "/")));
	
	timeCreateEnd.setDate(timeCreateEnd.getDate() + 1);	
	timeFinishEnd.setDate(timeFinishEnd.getDate() + 1);
	
	$('#search_form #timeCreateEnd').datepicker ('setStartDate', new Date(Date.parse($('#search_form #timeCreateStart').val().replace(/-/g,   "/"))) );
	$('#search_form #timeCreateStart').datepicker ('setEndDate', timeCreateEnd);

	$('#search_form #timeFinishEnd').datepicker ('setStartDate', new Date(Date.parse($('#search_form #timeFinishStart').val().replace(/-/g,   "/"))) );
	$('#search_form #timeFinishStart').datepicker ('setEndDate', timeFinishEnd);
}

function page(n,s){
	$("#search_form #pageStart").val(n);
	$("#search_form #pageSize").val(s);
	$("#search_form").attr("action","../task/historyTask");
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
		$("#search_form").attr("action","/ezid-cert-manual/task/historyTask");
		$("#search_form").attr("target", "_self");
		$("#search_form").submit();
}  

//提交搜索条件
function submitSearch(){
	$("#search_form #pageStart").val(1);
	$("#search_form").attr("action","../task/historyTask");
	$("#search_form").attr("target", "_self");
	$("#search_form").submit();
}

//清空搜索条件选项
function resetSearch(){
	$(".form-control:not(#taskOwnerSelf) ").val("");	
	initDataPicker();
}
 