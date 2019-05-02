<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	window.opener.document.getElementById("sd_num").value=document.getElementById("sd_num").value;
</script>
<div class="container">
	<div class="row">
		<input type="hidden" id="sd_num" value="${command.sd_num}">
		<h2>여행 상세</h2>
		<div class="form-horizontal">
	  		<div class="form-group">
		  		<div class="col-xs-3" style="text-align: left;">
					<label for="sd_code" class="control-label">관광지 이름</label>
				</div>
				<div class="col-xs-9">
					<input type="hidden" class="form-control" name="sd_code" id="sd_code" value="${command.sd_code}">${command.sd_code}
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-3" style="text-align: left;">
					<label for="sd_memo" class="control-label">여행 메모</label>
				</div>
				<div class="col-xs-9">
					<input type="hidden" class="form-control" name="sd_memo" id="sd_memo" value="${command.sd_memo}">${command.sd_memo}
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-3" style="text-align: left;">
					<label for="sd_money" class="control-label">경비</label>
				</div>
				<div class="col-xs-9">
					<input type="hidden" class="form-control" name="sd_money" id="sd_money" value="${command.sd_money}">${command.sd_money}원
				</div>
			</div>
		</div>
		<div class="form-group" style="text-align: center;">
			<button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/calendar/popup/updateDetail.do?sd_num=${command.sd_num}'">수정</button>
			<button type="button" class="btn btn-default" onclick="location.href='${pageContext.request.contextPath}/calendar/popup/deleteDetail.do?sd_num=${command.sd_num}'">삭제</button>
		</div>
	</div>
</div>