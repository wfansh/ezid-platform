<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<input type="hidden" id="root_url" value="<c:url value="/" />">

<!--提示对话框--> 
<div id="messageDialog" class="modal fade">
  <div class="modal-dialog">
	<div class="modal-content">
	  <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3 class="modal-title"><i class="fa fa-exclamation-circle"></i> 提示</h3>
	  </div>		  
	  <div id="messageBody" class="modal-body">
	  	
	  </div>
	  <div class="modal-footer">
		<button id="importCFCancelBtn" type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
	  </div>	  
	</div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	
<!--确认对话框--> 
<div id="confirmDialog" class="modal fade">
  <div class="modal-dialog">
	<div class="modal-content">
	  <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3 id="confirmDialogTitle" class="modal-title"><i class="fa fa-exclamation-circle"></i><s:message code="page.list.confirmDialogTitle"/></h3>
	  </div>
	  
	  <div id="confirmDialogContent" class="modal-body">
		<s:message code="page.list.confirmDialogContent"/>
	  </div>
	  <div class="modal-footer">
		<button id="confirmDialogOKBtn" type="button"  class="btn btn-primary" data-dismiss="modal"><s:message code="page.common.confirm"/></button>
		<button id="confirmDialogCancelBtn" type="button" class="btn btn-default" data-dismiss="modal"><s:message code="page.common.cancel"/></button>
	  </div>
	</div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<footer id="footer">
	<ul class="nav pull-right">
		<li>
			<a href="#" data-original-title="版本信息" data-content="<jsp:include page='version.jsp'/>" 
			data-trigger="hover" data-placement="top" data-toggle="tooltip" class="ui-popover" >
				平台版本
			</a>   |   
			<s:message code="page.footer.copyRight"/> &copy; 2014, <s:message code="page.footer.depart"/>.
		</li>
	</ul>
</footer>