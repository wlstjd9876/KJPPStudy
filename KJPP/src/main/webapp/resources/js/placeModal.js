$(document).ready(function(){   
	
	var count;
	var numOfRows;
	var pageBlock;
	var currentPage;
	var keyfield;
	var keyword;
	
   //목록 호출   
   $("#placeBtn").click(function(){
	   
	  //검색창 초기화
	  $('#keyfield_place').val("");
	  $('#keyword_place').val("");
	   
      $("#placeModal").modal();
      keyfield='';
      keyword='';
      currentPage = 1;
      getItems1();
   });  

   //목록 버튼 클릭
   $("#place_list_btn").click(function(){
	   
		  //검색창 초기화
	   	  $('#keyfield_place').val("");
	      $('#keyword_place').val("");
		   
	      $("#placeModal").modal();
	      keyfield='';
	      keyword='';
	      currentPage = 1;
	      getItems1();
	   }); 
   
   
   //검색
   $('#search_place').click(function(){
      
      //검색 결과 목록 호출
      keyfield = $('#keyfield_place').val();
      keyword = $('#keyword_place').val();
      currentPage = 1;
      getItems1();
            
   });
   function getItems1(){
		
	    $.ajax({
	       url: 'searchPlace.do',
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
	    	 
	           $('#output1').empty(); //검색창 초기화 
	           console.log(data); //콘솔에 데이터 보여줌
	           
	           
	           if(count==0){
	        	   var output = '';
	        	   output += '<div class="row align-center hn">';
	        	   output += '<hr>';
	        	   output += '<h5>검색된 결과가 없습니다.</h5>';
	        	   output += '</div>';
	        	   
	        	   $('#output1').append(output);
	        	   
	        	   $('#noPaging1').empty();
	        	   
	           }else{           
	           
	        	   var output = '';
	        	   output += '<div class="row align-center">';
	        	   output += '<hr>';
	 							
	 					$(data.place).each(function(index,item){ //item -> 배열 안에 있는 객체 (중괄호 안에 있는)
	 	                    
	 						output += '<div class="w3-card-4 w3-margin" align="center" style="width:90%">';
	 						output += '	<div class="w3-container">';
	 						output += '		<div style="width: 25%; height: 12%; float: left;">';
	 						output += '		<img style="width: 100%; height: 100%"  id="jusom'+(index+1)+'">';
	 						output += '		</div>';
	 						output += '<div style="float:left;">';
	 						output += ' <span style="text-align:left;">주소 : </span><span id=nunum'+(index+1)+'></span> ';
	 						output += '</div>';
	 						output += '		<div style="width: 75%; height: 12%; float: right;">';
	 						output += '<script type="text/javascript">detail2('+item.f_code1+','+(index+1)+');</script>';
	 						output += '			<div align="right">';
	 						output += '			<button class="btn btn-warning btn-sm" onclick="location.href=\'favDelete.do?f_num='+item.f_num+'\'">삭제</button>';
	 						output += '			<button class="btn btn-primary btn-sm" onclick="location.href=\'#\'">내 일정에 추가</button></div>';
	 						output += '		</div>';
	 						output += '	</div>';
	 						output += '</div>';
	 				});
	              output += '</div>';
	              $('#output1').append(output);
	              
	              setPaging();
	           }
	              
	        },
	       error: function() {
	          alert("네트워크 오류 발생!");
	       }  
	    });
	 };
	 
	 function setPaging(){
		 $('#placeModal .paging_button').empty();
		 var keyfield_place = $('#keyfield_place').val();
		 var keyword_place = $('#keyword_place').val();
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
		    	   add += '<li><a href="#" data-page='+i+' data-keyfield="'+keyfield_place+'" data-keyword="'+keyword_place+'">'+i+'</a></li>';
		       else
		    	   add += '<li class="active"><a href="#" data-page='+i+'>'+i+'</a></li>';
		    }

		    if(endPage < totalPage){
		       add += '<li data-page='+(startPage+pageBlock)+'>Next</li>';
		    }
		    
		    $('#placeModal .paging_button').append(add); 
		}
	    //페이지 버튼 이벤트 연결
		$(document).on('click','#placeModal .paging_button li a',function(event){
			//페이지 번호를 읽어들임
			if($(this).attr('data-page') != currentPage){
				currentPage = $(this).attr('data-page');
				keyfield = $(this).attr('data-keyfield');
				keyword = $(this).attr('data-keyword');
				//목록 호출
				getItems1();
			}
			
			event.preventDefault();
						
		});
});
