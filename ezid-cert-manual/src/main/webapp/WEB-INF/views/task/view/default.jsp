<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
<div id="content">		
	
	<div id="content-header">	
		<h1>
			被审核人详细信息
			<span style="float:right;margin-right:80px">
				<a href="javascript:history.go(-1);" class="btn  btn-info  btn">返  回 上 一 页</a>
			</span>
		</h1>
	</div> <!-- #content-header -->	


	<div id="content-container">
		<div class="row">
			<!--div class="col-md-12">
				<ol class="breadcrumb">
					<li><a href="<c:url value="/task/history" />">历史审核记录列表</a></li>
 					<li class="active">历史审核记录详细信息</li>
				</ol>
			</div-->
			<div class="col-md-10">

				<div class="row">
					<div class="col-md-3 col-sm-4" style="max-width:300px">
						<div class="thumbnail">
							<img src="<c:url value="/res/${task.photoFilePaths[0]}" />" alt="Profile Picture" onerror="this.src='<c:url value="/img/default_photo.jpg'" />"/>								
						</div> 
						<br>
						<ul class="nav nav-pills nav-stacked" id="myTab">
					        <li class="active">
					        	<a data-toggle="tab" href="#photo-tab">
					        		<i class="fa fa-picture-o"></i> 
					        		&nbsp;&nbsp;照片
					        	</a>
					        </li>
                            <c:if test="${not empty task.videoFilePaths}">
						        <li class="">
						        	<a data-toggle="tab" href="#video-tab">
						        		<i class="fa fa-video-camera"></i> 
						        		&nbsp;&nbsp;视频
						        	</a>
						        </li>
                            </c:if>
					      </ul>
					</div> 

					<div class="col-md-9 col-sm-8">		
					<h2>${task.processInstanceId}</h2>					
					<h4>
						<c:choose>
							<c:when test="${task.processing != 'true'}">
								<sec:authorize access="hasAnyRole('admin')">
									<a href="javascript:toRetrial('${task.processInstanceId}');" class="btn btn-primary"><s:message code="page.view.retrial"/></a>
									&nbsp;&nbsp;
								</sec:authorize>

								<c:if test="${task.result == 'true'}">
									通过
								</c:if>
								<c:if test="${task.result != 'true'}">
									不通过
									<c:if test="${fn:length(task.resultDesc) > 30}">
	                                	<span style="margin-left:50px" data-toggle="tooltip" data-placement="right" title="${task.resultDesc}">${fn:substring(task.resultDesc,0,30)}···</span>
	                                </c:if>
	                                <c:if test="${fn:length(task.resultDesc) <= 30}">
	                                    <span style="margin-left:50px">${task.resultDesc}</span>
	                                </c:if>
								</c:if>
							</c:when>
							<c:when test="${task.processing == 'true'}">
								认证中
							</c:when>
						</c:choose>
						</h4>							
						<hr/>
                        <h3>${task.sex} <c:if test="${not empty task.age}"> ${task.age}岁</c:if></h3>
                        <hr/>
                        <div class="tab-content stacked-content">
						
						<div id="photo-tab" class="tab-pane fade active in">
							<div class="feed-subject">
								<p><h3><i class="fa fa-picture-o"></i> 照片</h3></p>
							</div> <!-- /.feed-subject -->

							<div class="feed-content">
								<c:if test="${empty task.photoFilePaths}">
									该认证人员尚未上传照片
								</c:if>
								<div class="row">
									<c:if test="${not empty task.idcardPrintFilePath}">
										<div class="col-md-3" style="margin-bottom:10px">
											<div class="thumbnail">
												<div class="thumbnail-view"  style="text-align:center;margin:0 auto;">
													<a href="<c:url value="/res/${task.idcardPrintFilePath}"/>" class="thumbnail-view-hover ui-lightbox"></a>
						            				<img src="<c:url value="/res/${task.idcardPrintFilePath}"/>" style="width: 100%" alt="照片" onerror="this.src='<c:url value="/img/default_photo.jpg'" />" />
						            				<span>身份证</span>
						        				</div>
					          				</div>			
										</div> <!-- /.col -->
									</c:if>
									
									<c:if test="${not empty task.idcardPhotoFilePath}">
										<div class="col-md-3" style="margin-bottom:10px">
											<div class="thumbnail">
												<div class="thumbnail-view"  style="text-align:center;margin:0 auto;">
													<a href="<c:url value="/res/${task.idcardPhotoFilePath}"/>" class="thumbnail-view-hover ui-lightbox"></a>
						            				<img src="<c:url value="/res/${task.idcardPhotoFilePath}"/>" style="width: 100%" alt="照片" onerror="this.src='<c:url value="/img/default_photo.jpg'" />" />
						            				<span>身份证</span>
						        				</div>
					          				</div>			
										</div> <!-- /.col -->
									</c:if>
									
									<c:forEach items="${task.photoFilePaths}" var="photoPath">
										<div class="col-md-4 col-sm-8" style="margin-bottom:10px">
											<div class="thumbnail">
												<div class="thumbnail-view" style="text-align:center;margin:0 auto;">
													<a href="<c:url value="/res/${photoPath}"/>" class="thumbnail-view-hover ui-lightbox"></a>
						            				<img src="<c:url value="/res/${photoPath}"/>" style="width: 100%" alt="照片" onerror="this.src='<c:url value="/img/default_photo.jpg'" />" />
						        				</div>											
					          				</div>			
										</div>
									</c:forEach>
									
									<!-- /.col -->
								</div>
							</div> <!-- /.feed-content -->
						</div>
						
						<div id="video-tab" class="tab-pane fade in">
							<div class="feed-subject">
								<p><h3><i class="fa fa-video-camera"></i> 视频</h3></p>
							</div> <!-- /.feed-subject -->

							<div class="feed-content">
								<c:choose>
									<c:when test="${empty task.videoFilePaths}">
										该认证人员尚未上传视频
									</c:when>
									<c:otherwise>
										<div class="row">
											<c:forEach items="${task.videoFilePaths}" var="videoPath" varStatus="varStat">
												<div class="col-md-5 col-sm-8" style="margin:10px">
													<div id="flv_${varStat.index}" class="thumbnail" style="height:550px;width:370px">
														<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0">
															<embed src="<c:url value="/img/vcastr3.swf"/>" allowFullScreen="true" 
																FlashVars="xml=<vcastr>
																	<channel>
																		<item>
																			<source><c:url value="/res/${videoPath}"/></source>
																			<duration>10</duration>
																			<title>视频</title>
																		</item>
																	</channel>
																	<config>
																		<controlPanelBgColor>0x000000</controlPanelBgColor>
																		<isAutoPlay>false</isAutoPlay>
																		<bufferTime></bufferTime>
																		<controlPanelMode>none</controlPanelMode>
																	</config>
																</vcastr>"
																menu="false" quality="high" width="360" height="540" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
														</object>
													</div>
		
													<div id="flv180_${varStat.index}" class="thumbnail" style="height:550px;width:370px;display:none">
														<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0">
															<embed src="<c:url value="/img/vcastr3.swf"/>" allowFullScreen="true"
                                                                   FlashVars="xml=<vcastr>
							    	                		           <channel>
								    	             		               <item>
										    		                            <source><c:url value="/res/${task.videoThumbFilePaths[varStat.index]}"/></source>
										    		                            <duration>10</duration>
										    		                            <title>视频</title>
									    	                              </item>
								    	                            </channel>
								    	                            <config>
								    	                                 <controlPanelBgColor>0x000000</controlPanelBgColor>
								    	                                 <isAutoPlay>false</isAutoPlay>
								    	                                 <bufferTime></bufferTime>
								    	                                 <controlPanelMode>none</controlPanelMode>
								    	             	              </config>
					                                       		   </vcastr>"
                                                                   menu="false" quality="high" width="360" height="540" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
														</object>
													</div>
		
													<button class="text-center btn btn-default video-rotate-btn" type="button" onclick="rotate_flv('${varStat.index}')">
														<i class="fa fa-refresh"></i>
													</button>
												</div>
											</c:forEach>
										</div>								
									</c:otherwise>
								</c:choose>
							</div> <!-- /.feed-content -->
						</div>
						</div>
					</div> <!-- col -->
				</div>
			</div>
		</div> <!-- /.row -->
	</div> <!-- /#content-container -->
</div> <!-- #content -->
	
	<div id="retrialDialog" class="modal fade">
	  <div class="modal-dialog">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h3 id="retrialDialogTitle" class="modal-title">重审任务</h3>
		  </div>
		  
		  <div id="retrialDialogContent" class="modal-body">
				<form id="retrial_task_form" class="form-horizontal" role="form" method="post" action="<c:url value="/task/retrialTask"/>">
					<input type="hidden" id="id" name="id" value="${task.processInstanceId}">
					<div class="form-group">
						<label class="col-md-2">结果</label>
						<div data-toggle="buttons" class="btn-group col-md-10">
						  <label class="btn btn-tertiary active" onclick="hideReason()">
						    <input type="radio" id="result" name="result" value="pass" checked="checked" > 通过
						  </label>
						  <label class="btn btn-tertiary" onclick="showReason()">
						    <input type="radio" id="result" name="result" value="reject"> 未通过
						  </label>
						</div>
					</div>
					
					<div id="commonDiv" class="form-group">
						<label class="col-md-2">备注</label>
						<div class="col-md-10">
						    <input type="text" id="common" name="common" class="form-control dropdown-toggle" data-toggle="dropdown" placeholder="备注" onfocus=this.blur() onkeypress="javascript:return false"/>
						    <span id="commonMsg" class="help-block" style="color:red"></span>
							<ul class="dropdown-menu">
							<c:forEach items="${taskRejectReasonList}" var="reason">
								<c:if test="${!empty(reason.subReasonList)}">
								<li class="dropdown-submenu">
						      		<a href="javascript:;">${reason.name}</a>
						      		<ul class="dropdown-menu">
						      			<c:forEach items="${reason.subReasonList}" var="subReason">
				                    	<li>
				                        	<a class="selectReason" data-toggle="tooltip" data-placement="right" title="${subReason.name}"
                                                       href="javascript:selectReason('${reason.name}','${subReason.name}');" style="width:400px; text-overflow:ellipsis; white-space:nowrap;overflow:hidden;"
                                                       id="${subReason.fullName}">${subReason.name}</a>
				                    	</li>
				                    	</c:forEach>
				                	</ul>
						      	</li>	
						      	</c:if>
							</c:forEach>
							</ul>
						</div>
					</div>
				</form>
		  </div>
		  <div class="modal-footer">
			<button id="submit_form_btn" type="button" class="btn btn-primary"><s:message code="page.common.confirm"/></button>
			<button id="retrialDialogCancelBtn" type="button" class="btn btn-default" data-dismiss="modal"><s:message code="page.common.cancel"/></button>
		  </div>
		</div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->