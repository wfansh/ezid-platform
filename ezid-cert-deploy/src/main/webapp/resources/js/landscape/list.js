//页面初始化
$(document).ready(function(){
	//初始化开关
	$(".status_box").bootstrapSwitch();
	//radio按钮
	$('#addAppDialog input:checkbox, input:radio').iCheck({
		checkboxClass: 'icheckbox_minimal-blue',
		radioClass: 'iradio_minimal-blue',
		inheritClass: true
	}) 
	//点击开关
	$('.status_box').on('switchChange.bootstrapSwitch', function(event, state) {
		var resourceName=this.name;
		var executorId=this.id;
		$.post("landscape/updateLandscape/", { id: executorId, status: state }, function(
				result) {
			if(result.status=="success"){
				//去除error的desc的红色
				$("#desc_"+executorId).css({'color':'#000'});
				//更新error按钮颜色和文字变成off
				$("#"+executorId).prev().text("Off");
				$("#"+executorId).prev().removeClass("bootstrap-switch-danger");
				$("#"+executorId).prev().addClass("bootstrap-switch-default");
				//更新时间		
				$("#updateTime_"+executorId).text(result.data);	
				$("#updateTime_"+executorId).css({'color':'green'});
				if(state){
					notice("success","操作成功",resourceName+" 已打开。");
				}else{
					notice("warning","操作成功",resourceName+" 已关闭。");
				}				
			}
		});
	});
	//添加应用时点击app/manual
	$('#addAppDialog input').on('ifChecked', function(event){
		console.info($(this).val());
		if($(this).val()=="app"){
			$('#addAppDialog .app').show();
			$('#addAppDialog #userName').attr("required",true);
			$('#addAppDialog #password').attr("required",true);
		}	
		if($(this).val()=="manual"){
			$('#addAppDialog .app').hide();
			$('#addAppDialog #userName').attr("required",false);
			$('#addAppDialog #password').attr("required",false);
		}
	});
})

//添加Engine窗口
function toAddEngine(){
	$('#addEngineDialog').modal({backdrop:'static'});
}
function addEngine(){
	$('#add_engine_form').submit();
}
//添加App窗口
function toAddApp(engineId){
	$('#addAppDialog #engineId').val(engineId);
	$('#addAppDialog').modal({backdrop:'static'});
}
function addApp(){
	$('#add_app_form').submit();
}
//添加Executor窗口
function toAddExecutor(engineId,moduleId,resourceType){
	$('#addExecutorDialog #engineId').val(engineId);
	$('#addExecutorDialog #moduleId').val(moduleId);
	if(resourceType=="app"){
		$('#addExecutorDialog .app').show();
		$('#addExecutorDialog .manual').hide();
	}	
	if(resourceType=="manual"){
		$('#addExecutorDialog .manual').show();
		$('#addExecutorDialog .app').hide();
	}
	$('#addExecutorDialog').modal({backdrop:'static'});
}
function addExecutor(){
	$('#add_executor_form').submit();
}
//删除Resource窗口
function deleteResource(id,resourceType,resourceName){
	confirmDialog("确认删除", "是否确认删除这个"+resourceType, 
			function(){
				window.location="landscape/deleteLandscape/"+id+"/"+resourceType;
			}
			, true);
}
