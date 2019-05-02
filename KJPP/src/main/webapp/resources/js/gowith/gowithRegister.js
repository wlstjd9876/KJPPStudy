$(document).ready(function(){
   //동행등록 유효성 체크
   var form = document.getElementById('registerform');
   
   form.onsubmit=function(){
	  
	   var go_startdate = document.getElementById('go_startdate');
	   var go_enddate = document.getElementById('go_enddate');
	   var go_deadline = document.getElementById('go_deadline');
	   
	   var today = new Date();
	   var startdate = new Date(go_startdate.value);
	   var enddate = new Date(go_enddate.value);
	   var deadline = new Date(go_deadline.value);	   
	   
      var go_member = document.getElementById('go_member');
      if(go_member.value==''){
         alert('모집 인원을 입력하세요!');
         go_member.focus();
         return false;         
      }
      
      var go_age = document.getElementById('go_age');
      if(go_age[0].selected){
         alert('연령대를 선택하세요!');
         go_age.focus();
         return false;
      }

      var go_gen = document.getElementById('go_gen');
      if(go_gen[0].selected){
         alert('성별을 선택하세요!');
         go_gen.focus();
         return false;
      }
      
      var go_type = document.getElementById('go_type');
      if(go_type[0].selected){
         alert('여행타입을 선택하세요!');
         go_type.focus();
         return false;
      }
      
      var go_area = document.getElementById('go_area');
      if(go_area.value==''){
         alert('여행지를 입력하세요!');
         go_area.focus();
         return false;
      }      
      
      if(go_startdate.value==''){
         alert('여행 출발일을 입력하세요!');
         go_startdate.focus();
         return false;
      }
      
      if(today>startdate){
          alert("출발일은 오늘이후여야 합니다!");
          return false;
      }      
      
      if(go_enddate.value==''){
         alert('여행 도착일을 입력하세요!');
         go_enddate.focus();
         return false;
      }
      
      if(startdate>enddate){
          alert("출발일과 도착일을 확인해주세요!");
          return false;
      }          
      
      if(go_deadline.value==''){
         alert('신청 마감일을 입력하세요!');
         go_deadline.focus();
         return false;
      }
      
      if(startdate-1<deadline){
          alert("신청 마감일은 출발날짜보다 최소 하루 빨라야 합니다!");
          return false;
      }       
      
   };
});