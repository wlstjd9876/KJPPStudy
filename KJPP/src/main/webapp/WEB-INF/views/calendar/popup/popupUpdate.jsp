<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<div class="container">
	<div class="row">
		���� �Ϸ�!
		<div style="text-align: center;">
			<input type="hidden" id="sd_num" value="${command.sd_num}">
			<input type="button" class="btn btn-default" onclick="window.opener.location.reload(true);self.close();" value="�ݱ�">
			<input type="button" class="btn btn-default" value="���" onclick="location.href='${pageContext.request.contextPath}/calendar/popup/detail.do?sd_num=${command.sd_num}'">
		</div>
	</div>
</div>
