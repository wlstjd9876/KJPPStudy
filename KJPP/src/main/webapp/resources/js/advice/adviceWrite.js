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
      //날짜 미선택시
      if(startdate_val == ''){
         alert('시작 날짜를 선택해주세요.');
         return false;
      }
      if(enddate_val == ''){
         alert('종료 날짜를 선택해주세요.');
         return false;
      }

      //날짜를 -로 나누기
      var startdate_split = startdate_val.split('-');
      var enddate_split = enddate_val.split('-');
      
      //날짜셋팅
      var startdate = new Date(startdate_split[0], Number(startdate_split[1]-1), startdate_split[2]);
      var enddate = new Date(enddate_split[0], Number(enddate_split[1]-1), enddate_split[2]);
      
      //0부터 시작
      var between_date = (enddate.getTime() - startdate.getTime())/1000/60/60/24;
      
      
      if(startdate_val>enddate_val){
         alert("날짜를 다시 선택해 주세요.");
         $('#startdate').val('');
         $('#enddate').val('');
         return false;
      }
      
      //글 번호
      //일정 상세테이블 폼
      var detailUI =  '<div class="form-group hn">';
         detailUI += '   <div class="form-group hn">';
         
      for(var i=0; i<=between_date; i++){
         detailUI += '      <h2 class="hn">' + (i+1) + '일차</h2>';
         detailUI += '   </div>';
         detailUI += '   <div id="plan'+i+'" class="form-group">';
         detailUI += '      <div id="addPlan' + i + '" style="text-align: center;">일정을 추가해주세요!</div>';
         detailUI += '   </div>';
         detailUI += '   <div class="form-group hn">';
         //detailUI += '      <input type="button" value="일정 추가" id="search" class="btn btn-default" data-num="'+i+'" style="text-align: center;">';
         //모달 시작
         detailUI += '<div><br><button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">일정등록</button><br></div>';
         detailUI += '   <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalTitle" aria-hidden="true">';
         detailUI += '      <div class="modal-dialog" role="document">';
         detailUI += '         <div class="modal-content modaldetail">';
         detailUI += '            <div class="modal-header">';
         detailUI += '               <button type="button" class="close" data-dismiss="modal" aria-label="Close">';
         detailUI += '                  <span aria-hidden="true">&times;</span>';
         detailUI += '               </button>';
         detailUI += '               <h2 class="modal-title fontdetail hn" id="myModalLabel">일정상세등록</h2>';
         detailUI += '            </div>';
         detailUI += '            <div class="row">';
         detailUI += '               <form:form commandName="adviceDetailCommand" action="writeDetail.do" id="register_form" class="col-lg-10 formcenter">';
         detailUI += '                  <input type="hidden" id="adv_num" name="adv_num" value="${adv_num}">';
         detailUI += '                  <div class="form-group hn">';
         detailUI += '                     <label for="ad_code" class="fontdetail">관광지 코드</label>';
         detailUI += '                     <input type="text" name="ad_code" class="form-control kdk" />';
         detailUI += '                     <input type="button" class="popupBtn btn btn-default buttonleft" value="관광지 찾기">';
         detailUI += '                  </div><br>';
         detailUI += '                  <div class="form-group hn">';
         detailUI += '                     <label for="starttime">일정 시작 시간</label> <input type="time" name="starttime" id="starttime" class="form-control">';
         detailUI += '                  </div>';
         detailUI += '                  <div class="form-group hn">';
         detailUI += '                     <label for="endtime">일정 끝 시간</label> <input type="time" name="endtime" id="endtime" class="form-control">';
         detailUI += '                  </div>';
         detailUI += '                  <div class="form-group hn">';
         detailUI += '                     <label for="ad_memo">메모</label> <input type="text" name="ad_memo" class="form-control" />';
         detailUI += '                  </div>';
         detailUI += '                  <div class="form-group hn">';
         detailUI += '                     <label for="ad_money hn">여행경비</label> <input type="text" name="ad_money" class="form-control" />';
         detailUI += '                  </div>';
         detailUI += '                  <div class="form-group hn" style="text-align: center;">';
         detailUI += '                     <input type="submit" value="전송" class="btn btn-default">';
         detailUI += '                     <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>';
         detailUI += '                  </div>';
         detailUI += '               </form:form>';
         detailUI += '            </div>';
         detailUI += '         </div>';
         detailUI += '      </div>';
         detailUI += '   </div>';
         
      }
         
         detailUI += '   </div>';
         detailUI += '</div>';
         
         $('.adv_plan').append(detailUI);
         
         $(this).hide();
         $('.deletePlan').show();
   });
   
   $(document).on('click','.popupBtn',function(){
      window.open(
         context + "/data/location/listPopup.do",
         "childForm",
         "width=1200, height=650, resizable = no, scrollbars = no");
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
               adv_plan += '   <table class="table">';
               adv_plan += '      <tr>';
               adv_plan += '         <th style="width:20%;">번호</th>';
               adv_plan += '         <th style="width:60%;">제목</th>';
               adv_plan += '         <th style="width:20%;"></th>';
               adv_plan += '      </tr>';
               
               $(list).each(function(index, plan){
                  var startdate = '';
                  var enddate = '';
                  startdate=plan.s_startdate;
                  enddate=plan.s_enddate;
                  adv_plan += '      <tr>';
                  adv_plan += '         <td>' + plan.s_num + '</td>';
                  adv_plan += '         <td><a href="' + context + '/calendar/view.do?s_num=' + plan.s_num +'&s_startdate='+plan.s_startdate+'">' + plan.s_title + '</a></td>';
                  adv_plan += '         <td><input type="button" value="추가" class="btn btn-default" onclick="javascript:aaa('+plan.s_num+',\''+startdate+'\',\''+enddate+'\');" style="text-align: center;"></td>';
                  adv_plan += '      </tr>';
               });
               
               adv_plan += '   </table>';
               adv_plan += '   <div class="form-group" style="text-align: center;">'+data.pagingHtml+'</div>';
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
function aaa(s_num, s_startdate, s_enddate) {
   document.getElementById('startdate').value = s_startdate;
   document.getElementById('enddate').value = s_enddate;
   document.getElementById('s_num').value = s_num;
}