<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<div class="container">
	<div class="row">
		삭제 완료!
		<div style="text-align: center;">
			<input type="hidden" id="ar_num" value="${command.ar_num}">
			<input type="button" class="btn btn-default" onclick="window.opener.location.reload(true);self.close();" value="닫기">
		</div>
	</div>
</div>
