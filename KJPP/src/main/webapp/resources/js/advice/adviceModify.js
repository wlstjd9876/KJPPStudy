$(document).ready(function(){
	//일정 상세 페이지 출력
	function viewDetail(adv_num){
		$.ajax({
			type:'get',
			data:{adv_num:adv_num},
			url:'updateFormDetailList.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				var adv_num = data.adv_num;
				
				var list = data.list;
				var adv_detail='';
				
				$(list).each(function(index, detail){
					//다른 일차일 때 새로운 테이블 생성
					if(index == 0 || list[index].ad_day != list[index-1].ad_day){
						//다음 일차를 만나면 전 일차의 div를 닫아줌
						if(index != 0){
							adv_detail += '	</table>';
							adv_detail += '</div>';
						}
						
						adv_detail += '<div class="detail">';
						adv_detail += '	<h3>' + detail.ad_day + '일 차</h3>';
						adv_detail += '	<table class="table">';
						adv_detail += '		<tr>';
						adv_detail += '			<td>시작시간</td>';
						adv_detail += '			<td>끝시간</td>';
						adv_detail += '			<td>메모</td>';
						adv_detail += '			<td>경비</td>';
						adv_detail += '		</tr>';
						adv_detail += '		<tr>';
						adv_detail += '			<td>' + detail.starttime + '</td>';
						adv_detail += '			<td>' + detail.endtime + '</td>';
						
						if(detail.ad_memo != null){
							adv_detail += '			<td>' + detail.ad_memo + '</td>';
						}else{
							adv_detail += '			<td>없음</td>';	
						}
						
						adv_detail += '			<td>' + detail.ad_money + '</td>';
						
						adv_detail += '		</tr>';
						
					//같은 일차일때 컬럼명은 없어지고 아래로 붙임
					}else if(list[index].ad_day == list[index-1].ad_day){
						
						adv_detail += '		<tr>';
						adv_detail += '			<td>' + detail.starttime + '</td>';
						adv_detail += '			<td>' + detail.endtime + '</td>';
						
						if(detail.ad_memo != null){
							adv_detail += '			<td>' + detail.ad_memo + '</td>';
						}else{
							adv_detail += '			<td>없음</td>';	
						}
						
						adv_detail += '			<td>' + detail.ad_money + '</td>';
						
						adv_detail += '		</tr>';
						
					}
					
				});
				
				adv_detail += '	</table>';
				/*adv_detail += '	<div class="form-group" style="text-align: center;">';
				adv_detail += '		<input type="button" value="수정" id="updateDetail" class="btn btn-default" onclick="location.href=\'updateDetail.do?adv_num=' + $('.adv_num').val() + '\'">';
				adv_detail += '	</div>';*/
				adv_detail += '</div>';
				
				$('.adv_Plan').append(adv_detail);
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	}
	
	//일정 상세 수정 폼
	/*$(document).on('click', '#updateDetail', function(){
		$.ajax({
			type:'get',
			data:{adv_num:adv_num},
			url:'updateDetail.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				var adv_num = data.adv_num;
				
				var list = data.list;
				var adv_detail='';
				
				$(list).each(function(index, detail){
					//다른 일차일 때 새로운 테이블 생성
					if(index == 0 || list[index].ad_day != list[index-1].ad_day){
						//다음 일차를 만나면 전 일차의 div를 닫아줌
						if(index != 0){
							adv_detail += '	</table>';
							adv_detail += '</div>';
						}
						
						adv_detail += '<div class="detail">';
						adv_detail += '	<h3>' + detail.ad_day + '일 차</h3>';
						adv_detail += '	<table class="table">';
						adv_detail += '		<tr>';
						adv_detail += '			<td>시작시간</td>';
						adv_detail += '			<td>끝시간</td>';
						adv_detail += '			<td>메모</td>';
						adv_detail += '			<td>경비</td>';
						adv_detail += '		</tr>';
						adv_detail += '		<tr>';
						adv_detail += '			<td>' + detail.starttime + '</td>';
						adv_detail += '			<td>' + detail.endtime + '</td>';
						
						if(detail.ad_memo != null){
							adv_detail += '			<td><input type="text" name="adv_memo" id="adv_memo" value=' + detail.ad_memo + '></td>';
						}else{
							adv_detail += '			<td><input type="text" name="adv_memo" id="adv_memo" value=' + detail.ad_memo + '>없음</td>';	
						}
						
						adv_detail += '			<td>' + detail.ad_money + '</td>';
						
						adv_detail += '		</tr>';
						
					//같은 일차일때 컬럼명은 없어지고 아래로 붙임
					}else if(list[index].ad_day == list[index-1].ad_day){
						
						adv_detail += '		<tr>';
						adv_detail += '			<td>' + detail.starttime + '</td>';
						adv_detail += '			<td>' + detail.endtime + '</td>';
						
						if(detail.ad_memo != null){
							adv_detail += '			<td>' + detail.ad_memo + '</td>';
						}else{
							adv_detail += '			<td>없음</td>';	
						}
						
						adv_detail += '			<td>' + detail.ad_money + '</td>';
						
						adv_detail += '		</tr>';
						
					}
					
				});
				
				adv_detail += '	</table>';
				adv_detail += '	<div class="form-group" style="text-align: center;">';
				adv_detail += '		<input type="button" value="수정" id="updateDetail" class="btn btn-default" onclick="updateDetail.do">';
				adv_detail += '	</div>';
				adv_detail += '</div>';
				
				$('.adv_Plan').append(adv_detail);
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});*/
	
	viewDetail($('.adv_num').val());
});