<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
			alert('일정을 불러왔습니다.');
			opener.document.getElementById('go_startdate').value = '${s_startdate}';
			opener.document.getElementById('go_enddate').value = '${s_enddate}';
			opener.document.getElementById('s_num').value = '${s_num}';
			self.close();
</script>