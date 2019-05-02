//신청하기 popup창 js
$(document).ready(function(){
	$("#layerPopup").hide();
	$("#contents > a").click(function(){
		$("#contents > a").blur();
		$("#layerPopup").show();
		$("#layerPopup a").focus();
		return false;
	});
	$("#layerPopup a").keydown(function(e){
		if(e.shiftKey && e.keyCode == 9){ // Shift + Tab 키를 의미합니다.
			$("#contents > a").focus();
			$("#layerPopup").hide();
			return false;
		}
	});

	$("#layerPopup button").click(function(){
		$("#contents > a").focus();
		$("#layerPopup").hide();
	});

//신청목록보기 popup창 js
	$("#layerPopup1").hide();
	$("#contents1 > a").click(function(){
		$("#contents1 > a").blur();
		$("#layerPopup1").show();
		$("#layerPopup1 a").focus();
		return false;
	});
	$("#layerPopup1 a").keydown(function(e){
		if(e.shiftKey && e.keyCode == 9){ // Shift + Tab 키를 의미합니다.
			$("#contents1 > a").focus();
			$("#layerPopup1").hide();
			return false;
		}
	});

	$("#layerPopup1 button").click(function(){
		$("#contents1 > a").focus();
		$("#layerPopup1").hide();
	});
	
	//신청서 등록 안하면 필수항목이라고 체크해주는 js
	$(document).ready(function(){
		var checkApp_num = 0;

		//신청자 이메일 필수항목 체크
		$('#confirmApp').click(function(){
			if($('#email').val()==''){
				$('#message_email').css('color','red').text('이메일을 입력하세요');
				return;
			}
		});
	});



//사진 파일 js
	  var fileTarget = $('.filebox .upload-hidden');

	    fileTarget.on('change', function(){
	        if(window.FileReader){
	            var filename = $(this)[0].files[0].name;
	        } else {
	            var filename = $(this).val().split('/').pop().split('\\').pop();
	        }

	        $(this).siblings('.upload-name').val(filename);
	    });
	});