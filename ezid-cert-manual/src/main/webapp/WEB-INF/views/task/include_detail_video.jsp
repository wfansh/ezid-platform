<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<c:choose>
	<c:when test="${empty task.videoFilePaths}">
		<%--该认证人员尚未上传视频--%>
	</c:when>
	<c:otherwise>
		<c:forEach items="${task.videoFilePaths}" var="videoPath" varStatus="varStat">
			<div class="col-sm-12" >
				<div id="flv_${task.processInstanceId}_${varStat.index}" class="thumbnail" >
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
                               menu="false" quality="high" width="360" height="540" wmode="transparent" type="application/x-shockwave-flash"
                               pluginspage="http://www.macromedia.com/go/getflashplayer" />
					</object>
				</div>	
				
				<div id="flv180_${task.processInstanceId}_${varStat.index}" class="thumbnail" style="display:none">
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
                               menu="false" quality="high" width="360" height="540" wmode="transparent" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
					</object>
				</div>
	            <br/>
				<div class="col-sm-12">
				<button class="text-center btn btn-default video-rotate-btn" type="button" onclick="rotate_flv('${task.processInstanceId}', '${varStat.index}')">
					<i class="fa fa-refresh"></i>
				</button>
	        </div>
        </c:forEach>
    </div>
<%--</div>--%>
	</c:otherwise>
</c:choose>