$("#edit_pwd_form").validationEngine();

function pwdShow(){
    emptyModal("edit_pwd_dialog");
    $("#edit_pwd_dialog").modal({backdrop:'static'});
}
$(function(){
    $("#update_pwd_btn").click(function(){
        var newPasswordConf = $("#newPasswordConf_user").val();
        var newPassword = $("#newPassword_user").val();
        var oldPassword = $("#oldPassword_user").val();
        var contextPath = $("#contextPath_input").val();
        $("#errorLabel").empty();
        if(newPassword!=newPasswordConf){
            alert_m("请确认新密码输入是否一致")
            return;
        }
        var b = jQuery("#edit_pwd_form").validationEngine('validate');
        if(b){
            $.ajax({
                type:"post",
                url:contextPath+"/system/user/updatePWD",
                data:{
                    newPasswordConf:newPasswordConf,
                    newPassword:newPassword,
                    oldPassword:oldPassword
                },
                dataType:"JSON" ,
                success:function(data){
                    emptyModal("edit_pwd_dialog");
                    var msg=eval(data).data;
                    if(msg == "success"){
                        alert_m("修改成功,修改的密码将会在下次登录有效");
                    }else{
                        alert_m(msg)
                    }
                }
            })

        }
    });

})

//弹出提示窗
function alert_m(msg){
	$("#messageDialog #messageBody").html(msg);
	$("#messageDialog").modal({backdrop:'static'});
};

function emptyModal(modalId){
    var id = "#"+modalId+" input";
    var children = $(id);
    $("#"+modalId+" .formError").remove();
    children.each(function(){
        var node = $(this);
        if(node.attr("type")=="checkbox"){
            node.attr("checked",false);
        }
        $(this).val("")
    })
}

//弹出指定宽度提示窗
function alert_m(msg,width){
	$("#messageDialog .modal-dialog").css("width",width);
	$("#messageDialog #messageBody").html(msg);
	$("#messageDialog").modal({backdrop:'static'});
};

//弹出带确认后执行方法的提示窗
function confirmDialog(titleText, contentText, okFn, showCancel) {
	$("#confirmDialogTitle").html(titleText);
	$("#confirmDialogContent").html(contentText);
	$("#confirmDialogOKBtn").unbind('click');
	$("#confirmDialogOKBtn").click(okFn);
	if(showCancel) {
		$("#confirmDialogCancelBtn").show();
	} else {
		$("#confirmDialogCancelBtn").hide();
	}
    $("#confirmDialog").modal({backdrop:'static'});
}

//右下角弹出消息窗  
function notice(type,title,content){
	$.howl ({
		type: type
		, title: title
		, content: content
		, lifetime: 10000
	}); 
}


//$(".form-control").each(function(){
//	var reset_botton=$("<a></a>").html("<i class='fa fa-times'></i>");
//	reset_botton.addClass("reset-value");
//	reset_botton.attr("href","#");
//	$(this).after(reset_botton);
//});
//$(".reset-value").click(function(){
//	$(this).prev().val('');
//});

//
//
//$.get("../../../../../ezid-admin/task/getCurrentTaskCount", function(data){
//	var dataJson=eval(data);
//	if(dataJson.data["total"]>0){
//		$("#total_task_count").text(dataJson.data["total"]);
//	}else{
//		$("#total_task_count").empty();
//	}
//	if(dataJson.data["certification"]>0){
//		$("#certification_task_count").text(dataJson.data["certification"]);
//	}else{
//		$("#certification_task_count").empty();
//	}
//	if(dataJson.data["review"]>0){
//		$("#review_task_count").text(dataJson.data["review"]);
//	}else{
//		$("#review_task_count").empty();
//	}
//});
