$(document).ready(function(){
	//동행등록 유효성 체크
	var form = document.getElementById('registerform');
	form.onsubmit=function(){
		var today = new Date();
		var start = new Date(startdate.value);
		var end = new Date(enddate.value);
      
		if(today>start){
			alert("출발일은 오늘이후여야 합니다!");
			return false;
		}

		if(start>end){
			alert("출발일 도착일을 확인해주세요!");
			return false;
		}
	};
	
	//일정 상세 폼 생성
	$(document).on('click','.makePlan',function(){
		//날짜 value 가져오기
		var startdate_val = $('#startdate').val();
		var enddate_val = $('#enddate').val();

		//날짜를 -로 나누기
		var startdate_split = startdate_val.split('-');
		var enddate_split = enddate_val.split('-');
		
		//날짜셋팅
		var startdate = new Date(startdate_split[0], Number(startdate_split[1]-1), startdate_split[2]);
		var enddate = new Date(enddate_split[0], Number(enddate_split[1]-1), enddate_split[2]);
		
		//0부터 시작
		var between_date = (enddate.getTime() - startdate.getTime())/1000/60/60/24;
		
		//날짜 미선택시
		if(startdate_val == ''){
			alert('시작 날짜를 선택해주세요.');
			return false;
		}
		if(enddate_val == ''){
			alert('종료 날짜를 선택해주세요.');
			return false;
		}
		if(startdate_val>enddate_val){
			alert("날짜를 다시 선택해 주세요.");
			$('#startdate').val('');
			$('#enddate').val('');
			return false;
		}
		
		//글 번호
		//일정 상세테이블 폼
		var detailUI =  '<div class="form-group">';
			detailUI += '	<div class="form-group">';
			
		for(var i=0; i<=between_date; i++){
			detailUI += '		<h2>' + (i+1) + '일차</h2>';
			detailUI += '	</div>';
			detailUI += '	<div class="form-group">';
			detailUI += '		<div id="addPlan' + i + '">Ajax사용부분</div>';
			detailUI += '	</div>';
			detailUI += '	<div class="form-group">';
			detailUI += '		<input type="button" value="즐겨찾기 추가" id="search" class="btn btn-default">';
		}
			
			detailUI += '	</div>';
			detailUI += '</div>';
		   
			$('.adv_plan').append(detailUI);
			
			$(this).hide();
			$('.deletePlan').show();
	});
	
	//일정 상세 폼 삭제
	$(document).on('click','.deletePlan',function(){
		$('.adv_plan').empty();
		
		$(this).hide();
		$('.makePlan').show();
	});
	
	//내 일정 가져오는 부분
	$('#getPlan').on('click', function(){
		var currentPage;
		var count;
		var rowCount;
		
		function myPlan(pageNum){
			$.ajax({
				type:'get',
				data:{pageNum:pageNum},
				url:'adviceMyPlan.do',
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					$('.adv_myPlan').empty();
					
					count = data.count;
					rowCount = data.rowCount;
					
					var list = data.list;
					
					var adv_plan = '<div class="form-group">';
					adv_plan += '	<table class="table">';
					adv_plan += '		<tr>';
					adv_plan += '			<th style="width:20%;">번호</th>';
					adv_plan += '			<th style="width:60%;">제목</th>';
					adv_plan += '			<th style="width:20%;"></th>';
					adv_plan += '		</tr>';
					
					$(list).each(function(index, plan){
						adv_plan += '		<tr>';
						adv_plan += '			<td>' + plan.s_num + '</td>';
						adv_plan += '			<td><a href="' + context + '/calendar/view.do?s_num=' + plan.s_num +'">' + plan.s_title + '</a></td>';
						adv_plan += '			<td><input type="button" value="추가" class="btn btn-default" onclick="location.href=\'adviceList.do\'"></td>';
						adv_plan += '		</tr>';
					});
					
					adv_plan += '	</table>';
					adv_plan += '	<div class="form-group" style="text-align: center;">'+data.pagingHtml+'</div>';
					adv_plan += '</div>';
					
					$('.adv_myPlan').append(adv_plan);
				},
				error:function(){
					alert('네트워크 오류 발생');
				}
			});
		}
		
		myPlan(1);
	});
	
	$(document).on('click', '#search', function(){
		window.open(context + '/advice/popup/searchLoc.do', '관광지 찾기', 'width=800,height=500,left=300,top=10,scrollbars=no,toolbar=no,location=no');
	});
	
});