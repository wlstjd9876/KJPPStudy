$(document).ready(function(){   
	
	var count;
	var numOfRows;
	var pageBlock;
	var currentPage;
	var keyfield;
	var keyword;
	
   //목록 호출   
   $("#scheduleBtn").click(function(){
      $("#scheduleModal").modal();
      keyfield='';
      keyword='';
      currentPage = 1;
      getItems2();         
   }); 
   
   
   //목록 버튼 클릭
   $("#schedule_list_btn").click(function(){
	   
		  //검색창 초기화
	   	  $('#keyfield_scheduler').val("");
	      $('#keyword_schedule').val("");
		   
	      $("#scheduleModal").modal();
	      keyfield='';
	      keyword='';
	      currentPage = 1;
	      getItems1();
	   }); 

   
   
 //검색
   $('#search_schedule').click(function(){
      
      //검색 결과 목록 호출
      keyfield = $('#keyfield_schedule').val();
      keyword = $('#keyword_schedule').val();
      currentPage = 1;
      getItems2();
            
   });
   function getItems2(){
		
	    $.ajax({
	       url: 'searchSchedule.do',
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
	    	 
	           $('#output2').empty(); //검색창 초기화 
	           console.log(data); //콘솔에 데이터 보여줌
	           
	           if(count==0){
	        	   var output = '';
	        	   output += '<div class="row align-center hn">';
	        	   output += '<hr>';
	        	   output += '<h5>검색된 결과가 없습니다.</h5>';
	        	   output += '</div>';
	        	   
	        	   $('#output2').append(output);  
	        	   
	        	   $('#noPaging2').empty();
	        	   
	           }else{ 
	           
	           var output = '';
	 			output += '<div class="row align-center">';
	 			output += '<hr>';
	 							
	 					$(data.schedule).each(function(index,item){ //item -> 배열 안에 있는 객체 (중괄호 안에 있는)
	 	                    
	 						output += '<div class="w3-card-4 w3-margin" align="center" style="width:90%">';
	 						output += '	<div class="w3-container">';
	 						output += '		<div style="width: 25%; height: 12%; float: left;">';
	 						output += '		<img width="60" height="60" src="../resources/img/star2.png">';
	 						output += '		</div>';
	 						output += '		<div style="width: 75%; height: 12%; float: right;">';
	 						output += '			<h5 align="left">제목 : '+item.s_title+'</h5>';
	 			            output += '			<h5 align="left">닉네임 : '+item.td_nickname+'</h5>';
	 						output += '			<div align="right">';
	 						output += '			<button class="btn btn-warning btn-sm" onclick="location.href=\'favDelete.do?f_num='+item.f_num+'\'">삭제</button>';
	 						output += '			<button class="btn btn-default btn-sm" onclick="location.href=\'#\'">일정 상세 보기</button>';
	 						output += '			<button class="btn btn-primary btn-sm" onclick="location.href=\'#\'">내 일정에 추가</button>';
	 						output += '			</div>';
	 						output += '		</div>';
	 						output += '	</div>';
	 						output += '</div>';
	 				});
	              output += '</div>';
	              $('#output2').append(output);
	              
	              setPaging();
	           }
	              
	        },
	       error: function() {
	          alert("네트워크 오류 발생!");
	       }  
	    });
	 };
	 
	 function setPaging(){
		 $('#scheduleModal .paging_button').empty();
		 var keyfield_schedule = $('#keyfield_schedule').val();
		 var keyword_schedule = $('#keyword_schedule').val();
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
		    	   add += '<li><a href="#" data-page='+i+' data-keyfield="'+keyfield_schedule+'" data-keyword="'+keyword_schedule+'">'+i+'</a></li>';
		       else
		    	   add += '<li class="active"><a href="#" data-page='+i+'>'+i+'</a></li>';
		    }

		    if(endPage < totalPage){
		       add += '<li data-page='+(startPage+pageBlock)+'>Next</li>';
		    }
		    
		    $('#scheduleModal .paging_button').append(add); 
		}
	    //페이지 버튼 이벤트 연결
		$(document).on('click','#scheduleModal .paging_button li a',function(event){
			//페이지 번호를 읽어들임
			if($(this).attr('data-page') != currentPage){
				currentPage = $(this).attr('data-page');
				keyfield = $(this).attr('data-keyfield');
				keyword = $(this).attr('data-keyword');
				//목록 호출
				getItems2();
			}
			
			event.preventDefault();
						
		});
});

