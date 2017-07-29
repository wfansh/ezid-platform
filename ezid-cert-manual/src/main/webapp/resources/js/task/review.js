var current_id=$("#person_list li").eq(0).attr('id');//第一个人的ID
var current_video=$("#person_list li").eq(0).attr('videoFilePath');//第一个人的ID

getComment(current_id,current_video)

//先选中第一个人
selectPerson(current_id);

$(document).ready(function(){
	//点击审核名单
	$("#person_list a").not(".active").click(function(){
		var id=$(this).parent().attr("id");
		selectPerson(id);
	});

	//点击审核通过	
	$("#approve_btn").click(function(){
		approvePerson(current_id);
		selectPerson(findNextPersonId(current_id));
		countPerson();
	});

	//点击审核不通过	
	$("#ban_btn .selectReason").click(function(){
		banPerson(current_id,$(this).attr("id"));
		selectPerson(findNextPersonId(current_id));
		countPerson();
	});
	
	//点击审核不通过	
	$("#unclaim_btn").click(function(){
		unclaimPerson(current_id);
		selectPerson(findNextPersonId(current_id));
		countPerson();
	});
	
	//提交并继续按钮倒计时, 具体倒计时时间根据测试结果做调整,暂定每单3秒
	var remaining = $("#person_list li").length * 3;
	var scButton = $("#submitTask_continue");
	var scText = scButton.text();
	var countdown = setInterval(function() {
		if (--remaining) {
			scButton.text(scText + " (" + remaining + ")");
		} else {
			clearInterval(countdown);
			scButton.text(scText);
			scButton.removeAttr("disabled");
		}
	}, 1000);
	
});

function selectPerson(id){
	if(id== undefined){
		$(".buttons").hide();
		$("#submit_info").show();
	}else{
		$(".buttons").show();
		$("#submit_info").hide();
	}
	current_id=id;
	//审核名单选择当前人员
	$("#person_list li").each(function(){
		$(this).removeClass("active");
	});
	$("#person_list #"+id).addClass("active");

	//图片信息更换为当前人员
	$("#person_pic .shenhe").hide();
	$("#person_pic #"+id).fadeIn();
	$('#unpassComment').text("");
	var person=$("#person_list #"+id);
	var comment = person.find("#comment").val();
	if(comment!=""&&comment!="通过"&&comment!="unclaim"&&comment!=undefined){
		$('#unpassComment').text("不通过理由：" + comment);
	}
    
	getVideos(id);
}
function approvePerson(id){
	//审核名单打钩
	var person=$("#person_list #"+id);
	person.find("#result").val("pass");
	person.find("#comment").val("通过");
	person.find("i").eq(0).removeClass();
	person.find("i").eq(0).addClass("fa fa-check 2x pull-right");
	person.removeClass();
	person.addClass("done");
}
function banPerson(id,reason){
	//审核名单打叉
	var person=$("#person_list #"+id);
	person.find("#result").val("reject");
	person.find("#comment").val(reason);
	person.find("i").eq(0).removeClass();
	person.find("i").eq(0).addClass("fa fa-ban 2x pull-right");
	person.removeClass();
	person.addClass("done");
}
function unclaimPerson(id){
	//审核名单放弃
	var person=$("#person_list #"+id);
	person.find("#result").val("unclaim");
	person.find("#comment").val("unclaim");
	person.find("i").eq(0).removeClass();
	person.find("i").eq(0).addClass("fa fa-mail-forward 2x pull-right");
	person.removeClass();
	person.addClass("done");
}
function countPerson(){
	//更新人数统计
	var count=$("#person_list .fa").length;
	var totalCount=$("#person_list li").length;
	$("#person_count").text(count+"/"+totalCount);
	if(count==totalCount){
		$(".submit_btn").show();
	}
}
function findNextPersonId(id){
	var nextId=$("#person_list #"+id).next().attr("id");
	if(nextId== undefined && $('.not-done').length){
		nextId=$('.not-done').eq(0).attr("id");//若最后一个过后，之前还有没完成的，则跳至第一个没完成的
	}	
	return nextId;
}
function submitTask(nextAction,str){
	var $form=$("#task_form");
	$form.find("#nextAction").val(nextAction);
	var btn = $("#submitTask_"+nextAction);
	btn.button('loading');
    $("#submitTask_"+str).remove();
	$form.submit();
}
function getDetailInfo(){
	//点击查看详细信息
	$('#detailInfo #detail_main').load("../../cert/get_detail_info/"+current_id, "");
	$('#detailInfo #myModalLabel').text("详细信息");
	$('#detailInfo #idcardPhoto').attr("src",$("#person_pic #"+current_id+" #idcardPhoto").attr("src"));
	$('#detailInfo #personName').text($("#person_list .active a").eq(0).text());	
	$('#detailInfo').modal({backdrop:'static'});
}

function getPhotos(){
	//点击查看详细信息
	$('#photoVideo #detail_main').load("../../cert/get_detail_photo/"+current_id, function() {
		$('.ui-lightbox').magnificPopup({
			type: 'image',
			closeOnContentClick: false,
			closeBtnInside: true,
			fixedContentPos: true,
			mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
			image: {
				verticalFit: true,
				tError: '此图片无法读取.'
			}
		});
	});
	$('#photoVideo #myModalLabel').text("更多照片");	
	$('#photoVideo').modal({backdrop:'static'});
}

function getVideos(id){
    $("#person_video_"+id).load("../task/get_detail_video/"+current_id, "");
}

function photoCompare(certFormId, photoIndex){
	//弹出对比图片框
	var $m=$("#photo_compare");
	$m.find("#left").css("width","auto");
	$m.find("#left").css("height",window.innerHeight-250);
	$m.find("#left").attr("src",$("#person_pic #"+certFormId+" img:first").attr("src"));
	
	$m.find("#right").css("width","auto");
	$m.find("#right").css("height",window.innerHeight-250);
	$m.find("#right").attr("src",$("#person_pic #"+certFormId+" #photo_"+photoIndex).attr("src"));
	
	$m.modal({backdrop:'static'});
}

function otherRejectReason(){
	//填入自定义未通过理由
	var $m=$("#other_reason");
	$m.find(".modal-dialog").css("margin-top",$("#other_reason_li").offset().top+30);
	$m.find(".modal-dialog").css("margin-left",$("#other_reason_li").offset().left-100);
	$m.find("#reason").val("");
	$m.modal({backdrop:'static'});
}

//点击自定义未通过理由确认
$("#other_reason_submit_btn").click(function(){
	var reason=$("#other_reason #reason").val();
	banPerson(current_id,reason);
	selectPerson(findNextPersonId(current_id));
	countPerson();
});

//旋转视频
function rotate_flv(id, index){
	$("#flv_"+id+"_"+index).toggle();
	$("#flv180_"+id+"_"+index).toggle();
}

function getComment(id, videoFilePath){
	$('#unpassComment').text("");
	var person=$("#person_list #"+id);
	var comment = person.find("#comment").val();
	if(comment!=""&&comment!="通过"&&comment!="unclaim"&&comment!=undefined){
		$('#unpassComment').text("不通过理由：" + comment);
	}

	getVideos(id)
}


//鼠标滑轮滚动后执行的函数
//delta > 0 = 向上滚动
//delta < 0 = 向下滚动
var selectPic="";
$("#photo_compare #idcardPhoto").mouseenter(function(){
	selectPic="#idcardPhoto";
});
$("#photo_compare #idcardPhoto").mouseout(function(){
	selectPic="";
});
$("#photo_compare #photo").mouseenter(function(){
	selectPic="#photo";
});
$("#photo_compare #photo").mouseout(function(){
	selectPic="";
});

//if(document.addEventListener){
//	document.addEventListener('DOMMouseScroll',mousewheelEvent,false);//fireFox
//	}
//document.onmousewheel=mousewheelEvent;//IE/Opera/Chrome

function mousewheelEvent(e, delta) {
	console.info(e);
	console.info(e.detail);
	console.info(e.wheelDelta);
	if($("#photo_compare").css("display")!="none"){
		if(selectPic!=""){
			if(e.detail>0||e.wheelDelta>0){
                var width = $("#photo_compare " + selectPic).width();
                $("#photo_compare "+selectPic).width(width*1.2);
                console.info($("#photo_compare " + selectPic).width());
                while($("#photo_compare " + selectPic).width() == 0){
                    $("#photo_compare "+selectPic).width(width*1.2);
                    console.info($("#photo_compare " + selectPic).width());
                }
                if($("#photo_compare " + selectPic).width() != width){
                     $("#photo_compare "+selectPic).width(width)
                 }else{
                     $("#photo_compare "+selectPic).height($("#photo_compare "+selectPic).height()*1.2);
                 }
            }else{
                $("#photo_compare "+selectPic).width($("#photo_compare "+selectPic).width()*0.8);
				$("#photo_compare "+selectPic).height($("#photo_compare "+selectPic).height()*0.8);
			}			
		}
	}
}   
          

