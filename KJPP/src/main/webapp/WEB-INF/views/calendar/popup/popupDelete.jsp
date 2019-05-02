<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<div class="container">
	<div class="row">
		삭제 완료!
		<div style="text-align: center;">
			<input type="hidden" id="sd_num" value="${command.sd_num}">
			<input type="button" class="btn btn-default" onclick="window.opener.location.reload(true);self.close();" value="닫기">
		</div>
	</div>
</div>
