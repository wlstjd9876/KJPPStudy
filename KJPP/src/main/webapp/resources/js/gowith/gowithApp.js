$(document).ready(function(){
   //동행등록 유효성 체크
   $('#app_form').submit(function(){
     
      if($('#app_member').val()==''){
    	  $('#app_member_msg').text('모집 인원을 입력하세요!').css('color','red');
         return false;
      }
      if($('#app_gen').val()==''){
    	  $('#app_gen_msg').text('성별을 입력하세요!').css('color','red');
         return false;
      }
   });
});