$(document).ready(function(){
   //동행등록 유효성 체크
   var form = document.getElementById('registerform');
   
   form.onsubmit=function(){  
	   
      var title = document.getElementById('title');
      if(title.value==''){
         alert('제목을 입력하세요!');
         title.focus();
         return false;         
      }
      
      var content = document.getElementById('content');
      if(content.value==''){
         alert('내용을 입력하세요!');
         content.focus();
         return false;
      }      
      
      
   };
});