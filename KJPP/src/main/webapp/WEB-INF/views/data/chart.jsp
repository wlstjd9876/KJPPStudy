<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.css"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
.hn{font-family: 'Hanna', sans-serif;
	}
</style>
<br><br>
<div class="container">
<div class="col-md-12 col-lg-12" align="center">
<img src="${pageContext.request.contextPath}/resources/img/data/stadistics.png" width="50">
<span class="hn" style="font-size: 37pt;"> 통계 자료 </span>
<img src="${pageContext.request.contextPath}/resources/img/data/search.png" width="50">
<br><br>
</div>

<br><br>
<!-- 인기 관광지 - 가로막대 그래프 -->
<div align="center" class="col-md-6 col-lg-6">
<br>
<div><h2 align="center" class="hn">인기 관광 정보 TOP 10</h2></div>
<br>
<canvas id="attraction" style="align-items center;"></canvas>
<br><br><br><br><br>
</div>

<!-- 즐겨찾기 장소 - 가로막대 그래프 -->
<div align="center" class="col-md-6 col-lg-6">
<br>
<div><h2 align="center" class="hn">즐겨찾기 장소 TOP 10</h2></div>
<br>
<canvas id="favorite"></canvas>
<br><br><br><br><br>
</div>

<!-- 회원들의 여행스타일 - 파이 그래프 -->
<div align="center" class="col-md-6 col-lg-6">
<div><h2 align="center" class="hn">회원들의 여행 스타일</h2></div>
<br>
<canvas id="style"></canvas>
<br><br><br><br><br>
</div>

<!-- 동행과 함께하는 여행 타입 - 파이 그래프 -->
<div align="center" class="col-md-6 col-lg-6">
<div><h2 align="center" class="hn">동행과 함께하는 여행 타입</h2></div>
<br>
<canvas id="type"></canvas>
<br><br><br><br><br>
</div>

<!-- 회원들의 여행 시기 - 세로막대 그래프 -->
<div align="center" class="col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1">
<div><h2 align="center" class="hn">회원들의 여행 시기</h2></div>
<br>
<canvas id="month" style="align-items center;"></canvas>
<br><br><br><br><br>
</div>
<br><br>
</div>


<script>
<!-- 인기 관광지 - 가로막대 그래프 -->
var ctx = document.getElementById('attraction').getContext('2d');
var attraction = new Chart(ctx, {
    type: 'horizontalBar',
    data: {
        labels: ['서울 밤도깨비 야시장', '서울세계불꽃축제', '제부도', '여의도 봄꽃축제', '화천산천어축제', '창덕궁 달빛기행','진해군항제', '남강유등축제', '평창송어축제', '오색별빛정원전'],
        datasets: [{
            label: ['2019 투어 API 조회수 기준'],
            data: [944741, 882294, 811704, 746728, 744444, 700550, 700249, 685523, 610634, 575965],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(100, 99, 132, 0.2)',
                'rgba(100, 162, 235, 0.2)',
                'rgba(100, 206, 86, 0.2)',
                'rgba(200, 50, 100, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
                'rgba(100, 99, 132, 1)',
                'rgba(100, 162, 235, 1)',
                'rgba(100, 206, 86, 1)',
                'rgba(200, 50, 100, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }
    }
});

<!-- 즐겨찾기 장소 - 가로막대 그래프 -->
var ctx2 = document.getElementById('favorite').getContext('2d');
var favorite = new Chart(ctx2, {
    type: 'horizontalBar',
    data: {
        labels: ['경복궁','헤이리 예술마을','남산서울타워','꽃지해수욕장','전주한옥마을','협재해변','한려수도조망케이블카','해동 용궁사','불국사','순천만습지'],
        datasets: [{
            label: '2019년 4월 회원들의 즐겨찾기 장소 기준',
            data: [29, 26, 24, 20, 17, 16, 15, 12, 9, 8],
            backgroundColor: [
            	 'rgba(255, 99, 132, 0.2)',
                 'rgba(54, 162, 235, 0.2)',
                 'rgba(255, 206, 86, 0.2)',
                 'rgba(75, 192, 192, 0.2)',
                 'rgba(153, 102, 255, 0.2)',
                 'rgba(255, 159, 64, 0.2)',
                 'rgba(100, 99, 132, 0.2)',
                 'rgba(100, 162, 235, 0.2)',
                 'rgba(100, 206, 86, 0.2)',
                 'rgba(200, 50, 100, 0.2)'
            ],
            borderColor: [
            	'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
                'rgba(100, 99, 132, 1)',
                'rgba(100, 162, 235, 1)',
                'rgba(100, 206, 86, 1)',
                'rgba(200, 50, 100, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }
    }
});


<!-- 회원들의 여행스타일 - 파이 그래프 -->
var ctx3 = document.getElementById('style').getContext('2d');
var style = new Chart(ctx3, {
    type: 'pie',
    data: {
        labels: ['여자혼자', '남자혼자', '커플/신혼', '여자끼리', '남자끼리', '남녀함께', '아이들과', '부모님과', '부모님끼리'],
        datasets: [{
            label: '# of Votes',
            data: [12, 10, 25, 22, 18, 7, 8, 16, 4],
            backgroundColor: [
            	'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(100, 99, 132, 0.2)',
                'rgba(100, 162, 235, 0.2)',
                'rgba(100, 206, 86, 0.2)'
            ],
            borderColor: [
            	'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
                'rgba(100, 99, 132, 1)',
                'rgba(100, 162, 235, 1)',
                'rgba(100, 206, 86, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }
    }
});

<!-- ======================값 세팅======================= -->
<!-- 동행과 함께하는 여행 타입 - 파이 그래프 -->
var ctx4 = document.getElementById('type').getContext('2d');

var labels4 = [];
var data4 = [];

var backgroundColors4 = [];
backgroundColors4.push('rgba(255, 99, 132, 0.2)');
backgroundColors4.push('rgba(54, 162, 235, 0.2)');
backgroundColors4.push('rgba(255, 206, 86, 0.2)');
backgroundColors4.push('rgba(75, 192, 192, 0.2)');
backgroundColors4.push('rgba(153, 102, 255, 0.2)');
backgroundColors4.push('rgba(255, 159, 64, 0.2)');

var borderColors4 = [];
borderColors4.push('rgba(255, 99, 132, 1)');
borderColors4.push('rgba(54, 162, 235, 1)');
borderColors4.push('rgba(255, 206, 86, 1)');
borderColors4.push('rgba(75, 192, 192, 1)');
borderColors4.push('rgba(153, 102, 255, 1)');
borderColors4.push('rgba(255, 159, 64, 1)');



<!-- ======================ajax 처리===================== -->
<!-- 여행 타입 -->
$.ajax({
    url: 'typeChart.do',
    type: 'get',
    dataType: 'json',
    cache:false,
    timeout:30000,
    success: function(data){
    	
    	var typeList = data.typeList;
    	
       $(typeList).each(function(index,item){
    	   labels4.push(item.go_type);
    	   data4.push(item.count);
       });
       createchart4();
     },
    error: function() {
       alert('오류가 발생했습니다!');
    }  
 });



<!-- 여행 시기 - 세로 막대 그래프 -->
var ctx5 = document.getElementById('month').getContext('2d');

var labels5 = [];
var data5 = [];

var backgroundColors5 = [];
backgroundColors5.push('rgba(255, 99, 132, 0.2)');
backgroundColors5.push('rgba(54, 162, 235, 0.2)');
backgroundColors5.push('rgba(255, 206, 86, 0.2)');
backgroundColors5.push('rgba(75, 192, 192, 0.2)');
backgroundColors5.push('rgba(153, 102, 255, 0.2)');
backgroundColors5.push('rgba(255, 159, 64, 0.2)');
backgroundColors5.push('rgba(100, 99, 132, 0.2)');
backgroundColors5.push('rgba(100, 162, 235, 0.2)');
backgroundColors5.push('rgba(100, 206, 86, 0.2)');
backgroundColors5.push('rgba(200, 50, 100, 0.2)');
backgroundColors5.push('rgba(100, 102, 255, 0.2)');
backgroundColors5.push('rgba(100, 159, 64, 0.2)');


var borderColors5 = [];
borderColors5.push('rgba(255, 99, 132, 1)');
borderColors5.push('rgba(54, 162, 235, 1)');
borderColors5.push('rgba(255, 206, 86, 1)');
borderColors5.push('rgba(75, 192, 192, 1)');
borderColors5.push('rgba(153, 102, 255, 1)');
borderColors5.push('rgba(255, 159, 64, 1)');
borderColors5.push('rgba(100, 99, 132, 1)');
borderColors5.push('rgba(100, 162, 235, 1)');
borderColors5.push('rgba(100, 206, 86, 1)');
borderColors5.push('rgba(200, 50, 100, 1)');
borderColors5.push('rgba(100, 102, 255, 1)');
borderColors5.push('rgba(100, 159, 64, 1)');


<!-- 여행 시기 -->
$.ajax({
    url: 'monthChart.do',
    type: 'get',
    dataType: 'json',
    cache:false,
    timeout:30000,
    success: function(data){
    	
    	var monthList = data.monthList;
    	
       $(monthList).each(function(index,item){
    	   labels5.push(item.startdate);
    	   data5.push(item.countmonth);
       });
       
       createchart5();
     },
    error: function() {
       alert('오류가 발생했습니다!');
    }  
 });



/* 여행 타입 차트 생성 */
function createchart4(){
	var type = new Chart(ctx4, {
	    type: 'pie',
	    data: {
	    	
	    	labels : labels4,
	        datasets: [{
	            label: '# of Votes',
	            
	            data: data4,
	            backgroundColor: backgroundColors4,
	            borderColor: borderColors4,
	        	
	            borderWidth: 1
	        }]	    
	    },
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero: true
	                }
	            }]
	        }
	    }
	});
}

<!-- 여행 시기 차트 생성 -->
function createchart5(){
	
	var month = new Chart(ctx5, {
	    type: 'bar',
	    data: {
	        labels: labels5,
	        datasets: [{
	        	label: '회원들의 여행 시기 기준',
	            data: data5,
	            backgroundColor: backgroundColors5,
	            borderColor: borderColors5,
	            borderWidth: 1
	        }]
	    },
	    options: {
	        scales: {
	            yAxes: [{
	            	
	                ticks: {
	                    beginAtZero: true
	                }
	            }]
	        }
	    }
	});
}


</script>