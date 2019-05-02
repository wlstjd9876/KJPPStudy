<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>삭제하기</h1>
<script type="text/javascript">
	var result = '${result}';
	if(result == 'success'){
		alert('정상적으로 삭제되었습니다.');
		//연결된 곳을 열고 새로고침을 해라라는 뜻이다.
		opener.location.reload();
		//팝업창을 저절로 닫히게 하는것이다.
		self.close();
	}else{
		alert('오류가 발생했습니다.');
		opener.location.href="../main/main.do";
		self.close();
	}
</script>
</body>
</html>