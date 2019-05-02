$(document).ready(function(){
   var currentPage;
   var count;
   var rowCount;
   //댓글 목록
   function selectData(pageNum, num){
      currentPage = pageNum;
      
      if(pageNum == 1){
         //처음 호출시는 해당 ID의 div의 내부 내용물을 제거
         $('#output').empty();
      }
      
      //로딩 이미지 노출
      $('#loading').show();
      
      $.ajax({
         type:'post',
         data:{pageNum:pageNum, num:num},
         url:'listReply.do',
         dataType:'json',
         cache:false,
         timeout:30000,
         success:function(data){
            //로딩 이미지 감추기
            $('#loading').hide();
            count = data.count;
            rowCount = data.rowCount;
            
            var list = data.list;
            
            if(count < 0 || list == null){
               alert(count);
               alert('목록 호출 오류 발생!');
            }else{
               $(list).each(function(index,item){
                  var output = '<div class="item">';
                  output += '      <h4>' + item.email + '</h4>';
                  output += '      <div class="sub-item">';
                  output += '         <p>' + item.sr_content.replace(/\r\n/g,'<br>') + '</p>';
                  output += item.sr_date;
                  if($('#user_email').val()==item.email){
                     output += '      <input type="button" data-num="'+item.sr_num+'" data-id="'+item.email+'" value="수정" class="modify-btn">';
                     output += '      <input type="button" data-num="'+item.sr_num+'" data-id="'+item.email+'" value="삭제" class="delete-btn">'
                  }
                  output += '      <hr sizze="1" noshade>';
                  output += '     </div>';
                  output += '</div>';
                  
                  //문서 객체에 추가
                  $('#output').append(output);
                  
               });
               
               //paging button 처리
               if(currentPage>=Math.ceil(count/rowCount)){
                  //다음 페이지가 없음
                  $('.paging-button').hide();
               }else{//다음 페이지가 존재
                  $('.paging-button').show();
               }
            }
         },
         error:function(){
            //로딩 이미지 감추기
            $('#loading').hide();
            alert('댓글 목록 호출시 네트워크 오류');
         }
      });
   }
   //다음 댓글 보기 버튼 클릭시 데이터 추가
   $('.paging-button input').click(function(){
	  var pageNum = currentPage +1;
	  selectData(pageNum, $('#num').val());
   });
   
   
   //댓글 등록
   $('#sr_form').submit(function(event){
      if($('#sr_content').val()==''){
         alert('내용을 입력하세요');
         $('#sr_content').focus();
         return false;   
      }
      
      var data = $(this).serialize();
      
      //등록
      $.ajax({
         type:'post',
         data:data,
         url:'writeReply.do',
         dataType:'json',
         cache:false,
         timeout:30000,
         success:function(data){
            if(data.result == 'logout'){
               alert('로그인해야 작성할 수 있습니다.');
            }else if(data.result=='success'){
               //폼초기화
               initForm();
               //댓글 작성이 성공하면 새로 삽입한 글을 포함해서 첫번째 페이지의 게시글을 다시 호출함
               selectData(1,$('#num').val());
            }else{
               alert('등록시 오류 발생');
            }
         },
         error:function(){
            alert('댓글 등록시 네트워크 오류');
         }
      });
      //기본 이벤트 제거
      event.preventDefault();
   });
   
   //댓글 작성 폼 초기화
   function initForm(){
      $('textarea').val('');
      $('#sr_first .letter-count').text('300/300');
   }
   
   //textarea 내용 입력시 글자수 체크
   $(document).on('keyup','textarea',function(){
	  //남은 글자수를 구함
	   var inputLength = $(this).val().length;
	   
	   if(inputLength>300){
		   $(this).val($(this).val().substring(0,300));
	   }else{ //300자 이하인 경우
		   var remain = 300 -inputLength;
		   remain += '/300';
		   if($(this).attr('id')=='sr_content'){
			   //등록폼 글자수
			   $('#sr_first .letter-count').text(remain);
		   }else{
			   //수정폼 글자수
			   $('#msr_first .letter-count').text(remain);
		   }
	   
	   }
   });
   //댓글 수정 버튼 클릭시 수정폼 노출
   $(document).on('click','.modify-btn',function(){
	  //댓글 글번호
	   var sr_num = $(this).attr('data-num');
	  //작성자 아이디
	   var email= $(this).attr('data-id');
	  //댓글 내용
	   var content = $(this).parent().find('p').html().replace(/<br>/gi,'\n');
	   //댓글 수정폼 UI
	   var modifyUI = '<form id="msr_form">';
   	   modifyUI += '   <input type="hidden" name="sr_num" id="msr_num" value="'+sr_num+'">';
   	   modifyUI += '   <input type="hidden" name="email" id="muser_email" value="'+email+'">';
   	   modifyUI += '   <textarea rows="3" cols="50" name="sr_content" id="msr_content" class="rep-content">'+content+'</textarea>';
   	   modifyUI += '   <div id="msr_first"><span class="letter-count">300/300</span></div>';
	   modifyUI += '   <div id="msr_second" class="align-right">';
	   modifyUI += '   	<input type="submit" value="수정">';
	   modifyUI += '   	<input type="button" value="취소" class="re-reset">';
	   modifyUI += '   </div>';
	   modifyUI += '   <br size="1" noshade width="96%">';
	   modifyUI += '   </form>';
	   
	   //이전에 이미 수정하는 댓글이 있을 경우 수정버튼을 클릭하면 숨김 sub-item를 환원시키고 수정폼을 초기화함
	   initModifyForm();
	   
	   //지금 클릭해서 수정하고자 하는 데이터는 감추기
	   //수정버튼을 감싸고 있는 div 
	   $(this).parent().hide();
	   
	   //수정폼을 수정하고자하는 데이터가 있는 div에 노출
	   $(this).parents('.item').append(modifyUI);
	   
	   //입력한 글자수 셋팅
	   var inputLength = $('#msr_content').val().length;
	   var remain = 300 - inputLength;
	   remain += '/300';
	   
	   //문서 개게에 반영
	   $('#msr_fist .letter-count').text(remain);
   });
   
   //수정폼에서 취소 버튼 클릭시 수정폼 초기화
   $(document).on('click','.re-reset',function(){
	  initModifyForm();
   });

   	//댓글 수정 폼 초기화
   function initModifyForm(){
	   $('.sub-item').show();
	   $('#msr_form').remove();
   }
     
   //댓글 수정  
   $(document).on('submit', '#msr_form', function(event){
	  if($('#msr_content').val()==''){
		  alert('내용을 입력하세요');
		  $('#msr_content').focus()
		  return false;
	  } 
	  
	  //폼에 입력한 데이터 반환
	  var data = $(this).serialize();
	  
	  //수정
	  $.ajax({
		 url:'updateReply.do',
		 type:'post',
		 data:data,
		 dataType:'json',
		 cache:false,
		 timeout:30000,
		 success:function(data){
			 if(data.result == 'logout'){
				 alert('로그인해야 수정할 수 있습니다');
			 }else if(data.result == 'success'){
				 $('#msr_form').parent().find('p').html($('#msr_content').val().replace(/\n/g,'<br>'));
				 //수정폼 초기화
				 initModifyForm();
			 }else if(data.result == 'wrongAccess'){
				 alert('타인의 글은 수정할 수 없습니다.');
			 }else{
				 alert('댓글 수정시 오류 발생');
			 }
		 },
		 error:function(){
			 alert('댓글 수정시 네트워크 오류');
		 }
	  });
	  //기본 이벤트 제거
	  event.preventDefault();
   });
   
   
   
   //댓글 삭제
   $(document).on('click', '.delete-btn', function(){
      //댓글 번호
      var sr_num = $(this).attr('data-num');
      //작성자 아이디
      var email = $(this).attr('data-id');
      
      $.ajax({
         type:'post',
         url:'deleteReply.do',
         data:{sr_num:sr_num, email:email},
         dataType:'json',
         cache:false,
         timeout:30000,
         success:function(data){
            if(data.result == 'logout'){
               alert('로그인해야 삭제할 수 있습니다.');
            }else if(data.result == 'success'){
               alert('삭제완료!');
               //새로 목록 호출
               selectData(1,$('#num').val());
            }else if(data.result == 'wrongAccess'){
               alert('타인의 글을 삭제할 수 없습니다.');
            }else{
               alert('댓글 삭제시 오류 발생');
            }
         },
         error:function(){
            alert('댓글 삭제시 네트워크 오류');
         }
      });
   });
   
   //초기 데이터(목록) 호출
   selectData(1,$('#num').val());
});