$(document).ready(function(){   
   
   var count;
   var numOfRows;
   var pageBlock;
   var currentPage;
   var keyfield;
   var keyword;
   
   //목록 호출   
   $("#memberBtn").click(function(){
      $("#memberModal").modal();
      
      //검색창 초기화
   	  $('#keyfield_member').val("");
      $('#keyword_member').val("");
            
      keyfield='';
      keyword='';
      currentPage = 1;
      getItems3();         
   }); 
   
   
   //목록 버튼 클릭
   $("#member_list_btn").click(function(){
	   
		  //검색창 초기화
	   	  $('#keyfield_member').val("");
	      $('#keyword_member').val("");
		   
	      $("#memberModal").modal();
	      keyfield='';
	      keyword='';
	      currentPage = 1;
	      getItems1();
	   }); 

   
   
 //검색
   $('#search_member').click(function(){
      
      //검색 결과 목록 호출
      keyfield = $('#keyfield_member').val();
      keyword = $('#keyword_member').val();
      currentPage = 1;
      getItems3();
            
   });
   function getItems3(){
      
       $.ajax({
          url: 'searchMember.do',
          data:{keyfield:keyfield,keyword:keyword,pageNo:currentPage},
          type: 'get',
          dataType: 'json',
          cache:false,
          timeout:30000,
          success: function(data){
             
             count = data.count;
             currentPage = data.pageNo; 
             numOfRows = data.rowCount;
             pageBlock = data.pageCount;
           
              $('#output3').empty(); //검색창 초기화 
              console.log(data); //콘솔에 데이터 보여줌
              
              if(count==0){
	        	   var output = '';
	        	   output += '<div class="row align-center hn">';
	        	   output += '<hr>';
	        	   output += '<h5>검색된 결과가 없습니다.</h5>';
	        	   output += '</div>';
	        	   
	        	   $('#output3').append(output);    
	        	   
	        	   $('#noPaging3').empty();
	        	   
	           }else{ 
              
	        	   var output = '';
	        	   output += '<div class="row align-center">';
	        	   output += '<hr>';
                         
                   $(data.member).each(function(index,item){ //item -> 배열 안에 있는 객체 (중괄호 안에 있는)
                           
                      output += '<div class="w3-card-4 w3-margin" align="center" style="width:90%">';
                      output += '	<div class="w3-container">';
                      output += '		<div style="width: 25%; height: 12%; float: left;">';
                      output += '		<img style="width: 100%; max-height: 50%" id="imageFile" class="card-img-top" align="middle" src="imageView3.do?email='+item.email+'" alt="프로필 사진">';
                      output += '		</div>';
                      output += '		<div style="width: 75%; height: 12%; float: right;">';
                      output += '			<h5 align="left"> 이메일 :' +item.email+'</h5>';
                      output += '			<h5 align="left"> 등급 :' +item.td_score+'</h5>';
                      output += '			<div align="right">';
                      output += '			<button class="btn btn-warning btn-sm" onclick="location.href=\'favDelete.do?f_num='+item.f_num+'\'">삭제</button>';
                      output += '			<button class="btn btn-primary btn-sm" onclick="location.href=\'#\'">내 일정에 추가</button>';
                      output += '			</div>';
                      output += '		</div>';
                      output += '	</div>';
                      output += '</div>';
                });
                 output += '</div>';
                 $('#output3').append(output);
                 
                 setPaging();
	           }                 
           },
          error: function() {
             alert("네트워크 오류 발생!");
          }  
       });
    };
    
    function setPaging(){
       $('#memberModal .paging_button').empty();
       var keyfield_member = $('#keyfield_member').val();
       var keyword_member = $('#keyword_member').val();
      var totalPage = Math.ceil(count/numOfRows);

          if(currentPage == undefined || currentPage == ''){
             currentPage = 1;
          }

          //현재 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
          if(currentPage > totalPage){
             currentPage = totalPage;
          }

          var startPage = Math.floor((currentPage-1)/pageBlock)*pageBlock + 1; //data
          var endPage = startPage + pageBlock -1;

          //마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
          if(endPage > totalPage){
             endPage = totalPage;
          }
          
          var add = '';

          if(startPage>pageBlock){
             add += '<li data-page='+(startPage-1)+'>Prev</li>'; 
          }

          for(var i=startPage;i<=endPage;i++){
             if(i!=currentPage)
                add += '<li><a href="#" data-page='+i+' data-keyfield="'+keyfield_member+'" data-keyword="'+keyword_member+'">'+i+'</a></li>';
             else
                add += '<li class="active"><a href="#" data-page='+i+'>'+i+'</a></li>';
          }

          if(endPage < totalPage){
             add += '<li data-page='+(startPage+pageBlock)+'>Next</li>';
          }
          
          $('#memberModal .paging_button').append(add); 
      }
       //페이지 버튼 이벤트 연결
      $(document).on('click','#memberModal .paging_button li a',function(event){
         //페이지 번호를 읽어들임
         if($(this).attr('data-page') != currentPage){
            currentPage = $(this).attr('data-page');
            keyfield = $(this).attr('data-keyfield');
            keyword = $(this).attr('data-keyword');
            //목록 호출
            getItems3();
         }
         
         event.preventDefault();
                  
      });
});
 