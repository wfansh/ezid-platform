<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--百度统计--> 
<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fce34bedc82ab1499000524053ba6f8e6' type='text/javascript'%3E%3C/script%3E"));
</script>
<!--Basic--> 
<script src="<c:url value="/js/libs/jquery-1.9.1.min.js" />"></script>
<script src="<c:url value="/js/libs/jquery-ui-1.9.2.custom.min.js" />"></script>
<script src="<c:url value="/js/libs/bootstrap.min.js" />"></script>
<script src="<c:url value="/js/libs/bootstrap-switch.min.js" />"></script>

<!--Admin模板--> 
<script src="<c:url value="/js/plugins/icheck/jquery.icheck.js" />"></script>
<script src="<c:url value="/js/plugins/datepicker/bootstrap-datepicker.js" />"></script>
<script src="<c:url value="/js/plugins/timepicker/bootstrap-timepicker.js" />"></script>
<script src="<c:url value="/js/plugins/simplecolorpicker/jquery.simplecolorpicker.js" />"></script>
<script src="<c:url value="/js/plugins/select2/select2.js" />"></script>
<script src="<c:url value="/js/plugins/autosize/jquery.autosize.min.js" />"></script>
<script src="<c:url value="/js/plugins/textarea-counter/jquery.textarea-counter.js" />"></script>
<script src="<c:url value="/js/plugins/datatables/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/js/plugins/datatables/DT_bootstrap.js" />"></script>
<script src="<c:url value="/js/plugins/tableCheckable/jquery.tableCheckable.js" />"></script>
<script src="<c:url value="/js/plugins/icheck/jquery.icheck.min.js" />"></script>
<script src="<c:url value="/js/plugins/magnific/jquery.magnific-popup.js" />"></script>
<script src="<c:url value="/js/plugins/howl/howl.js" />"></script>
<script src="<c:url value="/js/plugins/fileupload/bootstrap-fileupload.js" />"></script>
<script src="<c:url value="/js/plugins/ajaxfileupload/ajaxfileupload.js" />"></script>
<script src="<c:url value="/js/plugins/treeTable/jquery.treeTable.js" />"></script>
<script src="<c:url value="/js/plugins/parsley/parsley.js" />"></script>
<script src="<c:url value="/js/jquery.validationEngine.js" />"></script>
<script src="<c:url value="/js/jquery.validationEngine-zh_CN.js" />"></script>
<script src="<c:url value="/js/App.js" />"></script>

<!--ezid定制--> 
<script src="<c:url value="/js/ezid-custom.js" />"></script>
<tiles:importAttribute name="jsFile" /> 
<script src="<c:url value="${jsFile}" />"></script>

<!--成功失败消息框--> 
<c:if test="${alertMsgTitle!= null}">
<script>
	notice("${alertMsgType}","${alertMsgTitle}","${alertMsgContent}");
</script>
</c:if>