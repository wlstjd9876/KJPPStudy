$(document).ready(function(){
	var checkNickname = 0;
	
	//닉네임 중복 체크
	$('#confirmNickname').click(function(){
		if($('#td_nickname').val()==''){
			$('#message_nickname').css('color','red').text('닉네임을 입력하세요.');
			return;
		}
		
		//메시지 초기화
		$('#message_nickname').text('');
		//로딩 이미지 노출
		$('#loading').show();
		
		$.ajax({
			url:'confirmNickname.do',
			type:'post',
			data:{td_nickname:$('#td_nickname').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				//로딩 이미지 감추기
				$('#loading').hide();
				
				if(data.result == 'nicknameNotFound'){
					$('#message_nickname').css('color','#000').text('등록가능 닉네임');
					checkNickname = 1;
				}else if(data.result == 'nicknameDuplicated'){
					$('#message_nickname').css('color','red').text('중복된 닉네임');
					checkNickname = 0;
				}else{
					alert('닉네임 중복체크 오류');
				}
			},
			error:function(){
				//로딩 이미지 감추기
				$('#loading').hide();
				alert('닉네임 중복체크시 네트워크 오류 발생');
			}
		});
	});
	
	//닉네임 중복 안내 메시지 초기화 및 닉네임 중복 값 초기화
	$('#register_form #td_nickname').keydown(function(){
		checkNickname = 0;
		$('#message_nickname').text('');
	});
	
	//submit 이벤트 발생시 닉네임 중복 체크 여부 확인
	$('#nickname_form').submit(function(){
		if(checkNickname==0){
			$('#message_nickname').css('color','red').text('닉네임 중복 체크 필수!');
			if($('#td_nickname').val()==''){
				$('#td_nickname').focus();
			}
			return false;
		}
	});
	$('#nick_btn').click(function(){
		$('#nick_form').show();
		$('#nick_div').hide();
	});
	$('.nick_reset').click(function(){
		$('#nick_form').hide();
		$('#nick_div').show();
	});
});