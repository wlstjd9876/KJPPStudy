$(document).ready(function() {
	$('div.headerMenu div').click(function(){
		$('div.headerMenu div').removeClass('tab-active');
		$(this).addClass('tab-active');
	});
	detail();
});



//즐겨찾기
function insert(){
	$.ajax({        
	
			url: contextPath+'/favinsert.do',
			data:{contentid:contentId},
			type: 'POST',
			dataType: 'json',
			cache:false,
			timeout:30000,
			success: function(data){
				if(data.result=='success'){
					alert("일정에 추가되었습니다.");
				}else{
					alert("이미 있습니다.");
				}
				
			},
			 error: function() {
				 alert(contentId);
				 alert(contextPath+'/favinsert.do');
		          alert("일정추가 오류 발생!");
		       }  
		});
	};
//
	//추가이미지
	function imageo(){
		$.ajax({        
			url: contextPath+'/imageoAjax',
			data:{contentId:contentId},
			type: 'get',
			dataType: 'json',
			cache:false,
			timeout:30000,
			success: function(data){
				$('#output').empty();
				var output = '';
				
				output += '<div class="image hn col-lg-12">';
				output += '<div class="w3-content" align="center" style="width: 500px; height: 450px; border: 2px dotted;">  <br>';
				output += '<img class="mySlides" width="450px" height="300px" id="imageFile1" src="imageView1.do?num=${share.num}&photo_type=1" style="display: block;">';
				output += '<c:if test="${share.photo2!=null}">';
				output += '<img class="mySlides" width="450px" height="300px"  id="imageFile1" src="imageView1.do?num=${share.num}&photo_type=2" style="display: none;"></c:if>';
				output += '<c:if test="${share.photo2==null}"> ';
				output += '<img class="mySlides" width="450px" height="300px"  src="${pageContext.request.contextPath}/resources/img/noimage.png" style="display: none;"></c:if>';
				output += '<c:if test="${share.photo3!=null}">  <img class="mySlides" width="450px" height="300px"  id="imageFile1" src="imageView1.do?num=${share.num}&photo_type=3" style="display: none;"></c:if>';
				output += '<c:if test="${share.photo3==null}">  <img class="mySlides" width="450px" height="300px" src="${pageContext.request.contextPath}/resources/img/noimage.png" style="display: none;"></c:if>';
				output += '<div class="w3-row-padding w3-section">';
				output += '<div class="w3-col s4">';
				output += '<img class="demo w3-opacity w3-hover-opacity-off" width="120px" height="80px"';
				output += 'id="imageFile1" src="imageView1.do?num=${share.num}&photo_type=1" style="cursor: pointer; align-items: center;" align="middle"onclick="currentDiv(1)"></div>';
				output += '<div class="w3-col s4">';
				output += '<c:if test="${share.photo2==null}">';
				output += '<img class="demo w3-opacity w3-hover-opacity-off" width="120px" height="80px" src="${pageContext.request.contextPath}/resources/img/noimage.png" style="cursor: pointer; align-items: center;" align="middle"onclick="currentDiv(2)"></c:if>';
				output += '<c:if test="${share.photo2!=null}"> <img class="demo w3-opacity w3-hover-opacity-off" width="120px" height="80px"';
				output += 'id="imageFile1" src="imageView1.do?num=${share.num}&photo_type=2" style="cursor: pointer; align-items: center;" align="middle"';
				output += 'onclick="currentDiv(2)"></c:if></div>';
				output += '<div class="w3-col s4">';
				output += '<c:if test="${share.photo3==null}">';
				output += '<img class="demo w3-opacity w3-hover-opacity-off" width="120px" height="80px"';
				output += 'src="${pageContext.request.contextPath}/resources/img/noimage.png" style="cursor: pointer; align-items: center;" align="middle"';
				output += 'onclick="currentDiv(3)">';
				output += '</c:if>'; 
				output += '<c:if test="${share.photo3!=null}">';
				output += '<img class="demo w3-opacity w3-hover-opacity-off" width="120px" height="80px"';
				output += 'id="imageFile1" src="imageView1.do?num=${share.num}&photo_type=3" style="cursor: pointer; align-items: center;" align="middle"';
				output += 'onclick="currentDiv(3)">';
				output += '</c:if>';
				output += '</div>';
				output += '</div><br>';
				output += '</div>';
				output += '</div>';
				
				$('#output').append(output);
	
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) { 
				alert("Status: " + textStatus +"and "+ "Error: " + errorThrown); 
			}  
		});
	};

	
//================== 디테일=======================//
function detail(){
	$.ajax({        
		url: contextPath+'/detailAjax',
		data:{contentId:contentId},
		type: 'get',
		dataType: 'json',
		cache:false,
		timeout:30000,
		success: function(data){
			$('#output').empty();
			console.log(data);
			var myItem = data.response.body.items.item;
			var myBody = data.response.body;

			var output = '';
			if(myItem.firstimage==undefined)
				var image = contextPath+"/resources/img/data/No_Image_Available.jpg";
			else
				var image = myItem.firstimage;
			console.log(myItem.length);
			output += '		<img src="' + image + '" style="width: 100%; height: 300px;">';
			output += '				<h3 class="hn">'+myItem.title+'</h3>';
			if(myItem.addr2!=undefined)
				output += '				<b class="hn view-font"> 주소 : '+myItem.addr1+ myItem.addr2 + '</b>';
			else
				output += '				<h5 class="hn"> 주소 : '+myItem.addr1+'</h5>';
			output += '				<h5 class="hn"> 우편번호 : '+myItem.zipcode + '</h5>';
			output += '				<h5 class="hn"> 홈페이지 : '+myItem.homepage + '</h5>';
			output += '				<h5 class="hn"> 오버뷰 : '+myItem.overview + '</h5>';
			$('#output').append(output);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) { 
			alert("Status: " + textStatus +"and "+ "Error: " + errorThrown); 
		}  
	});
};


//================== 디테일=======================//
//================== 소개정보=======================//
function intro(){
	$.ajax({        
		url: contextPath+'/introAjax',
		data:{contentId:contentId, contenttypeid:contenttypeid},
		type: 'get',
		dataType: 'json',
		cache:false,
		timeout:30000,
		success: function(data){
			$('#output').empty();
			console.log(data);
			var myItem = data.response.body.items.item;
			var myBody = data.response.body;

			var output = '';
			output += '<div class="row">';
			console.log(myItem.length);
			output += '<div class="col-sm-6 col-md-6 padd-both">';
			if(contenttypeid==12){
				output += '         <div class="caption">';
				output += '         <ul class="loc-font">';
				if(myItem.accomcount!='' && myItem.accomcount!=undefined)
					output += '            <li  class="hn"><b> 수용인원 : </b>'+myItem.accomcount + '</li>';
				if(myItem.chkbabycarriage!='' && myItem.chkbabycarriage!=undefined)
					output += '            <li  class="hn"><b> 유모차대여 여부 : </b>'+myItem.chkbabycarriage + '</li>';
				if(myItem.chkcreditcard!='' && myItem.chkcreditcard!=undefined)
					output += '            <li class="hn"><b> 신용카드가능 여부 : </b>'+myItem.chkcreditcard + '</li>';
				if(myItem.chkpet!='' && myItem.chkpet!=undefined)
					output += '            <li class="hn"><b> 애완동물동반가능 여부 : </b>'+myItem.chkpet + '</li>';
				if(myItem.expagerange!='' && myItem.expagerange!=undefined)
					output += '            <li class="hn"><b> 체험가능 연령  : </b>'+myItem.expagerange + '</li>';
				if(myItem.expguide!='' && myItem.expguide!=undefined)
					output += '            <li class="hn"><b> 체험안내  : </b>'+myItem.expguide + '</li>';
				if(myItem.infocenter!='' && myItem.infocenter!=undefined)
					output += '            <li class="hn"><b> 문의 및 안내  : </b>'+myItem.infocenter + '</li>';
				if(myItem.opendate!='' && myItem.opendate!=undefined)
					output += '            <li class="hn"><b> 개장일  : </b>'+myItem.opendate + '</li>';
				if(myItem.parking!='' && myItem.parking!=undefined)
					output += '            <li class="hn"><b> 주차시설  : </b>'+myItem.parking + '</li>';
				if(myItem.restdate!='' && myItem.restdate!=undefined)
					output += '            <li class="hn"><b> 쉬는날  : </b>'+myItem.restdate + '</li>';
				if(myItem.useseason!='' && myItem.useseason!=undefined)
					output += '            <li class="hn"><b> 이용시기  : </b>'+myItem.useseason + '</li>';
				if(myItem.usetime!='' && myItem.usetime!=undefined)
					output += '            <li class="hn"><b> 이용시간  : </b>'+myItem.usetime + '</li>';
				output += '         </ul>';
				output += '   </div>';
			}else if(contenttypeid==14){
				output += '         <div class="caption">';
				output += '         <ul class="loc-font">';
				if(myItem.accomcountculture!='' && myItem.accomcountculture!=undefined)
					output += '            <li class="hn"><b> 수용인원  : </b>'+myItem.accomcountculture + '</li>';
				if(myItem.chkbabycarriageculture!='' && myItem.chkbabycarriageculture!=undefined)
					output += '            <li class="hn"><b> 유모차대여 정보  : </b>'+myItem.chkbabycarriageculture + '</li>';
				if(myItem.chkcreditcardculture!='' && myItem.chkcreditcardculture!=undefined)
					output += '            <li class="hn"><b> 신용카드가능 정보  : </b>'+myItem.chkcreditcardculture + '</li>';
				if(myItem.chkpetculture!='' && myItem.chkpetculture!=undefined)
					output += '            <li class="hn"><b> 애완동물동반가능 정보  : </b>'+myItem.chkpetculture + '</li>';
				if(myItem.discountinfo!='' && myItem.discountinfo!=undefined)
					output += '            <li class="hn"><b> 할인정보  : </b>'+myItem.discountinfo + '</li>';
				if(myItem.infocenterculture!='' && myItem.infocenterculture!=undefined)
					output += '            <li class="hn"><b> 문의 및 안내  : </b>'+myItem.infocenterculture + '</li>';
				if(myItem.parkingculture!='' && myItem.parkingculture!=undefined)
					output += '            <li class="hn"><b> 주차시설  : </b>'+myItem.parkingculture + '</li>';
				if(myItem.parkingfee!='' && myItem.parkingfee!=undefined)
					output += '            <li class="hn"><b> 주차요금  : </b>'+myItem.parkingfee + '</li>';
				if(myItem.restdateculture!='' && myItem.restdateculture!=undefined)
					output += '            <li class="hn"><b> 쉬는날  : </b>'+myItem.restdateculture + '</li>';
				if(myItem.usefee!='' && myItem.usefee!=undefined)
					output += '            <li class="hn"><b> 이용요금  : </b>'+myItem.usefee + '</li>';
				if(myItem.usetimeculture!='' && myItem.usetimeculture!=undefined)
					output += '            <li class="hn"><b> 이용시간  : </b>'+myItem.usetimeculture + '</li>';
				if(myItem.scale!='' && myItem.scale!=undefined)
					output += '            <li class="hn"><b> 규모  : </b>'+myItem.scale + '</li>';
				if(myItem.spendtime!='' && myItem.spendtime!=undefined)
					output += '            <li class="hn"><b> 관람 소요시간  : </b>'+myItem.spendtime + '</li>';
				output += '         </ul>';
				output += '   </div>';
			}else if(contenttypeid==15){
				output += '         <div class="caption">';
				output += '         <ul class="loc-font">';
				if(myItem.agelimit!='' && myItem.agelimit!=undefined)
					output += '            <li class="hn"><b> 관람 가능연령  : </b>'+myItem.agelimit + '</li>';
				if(myItem.bookingplace!='' && myItem.bookingplace!=undefined)
					output += '            <li class="hn"><b> 예매처  : </b>'+myItem.bookingplace + '</li>';
				if(myItem.discountinfofestival!='' && myItem.discountinfofestival!=undefined)
					output += '            <li class="hn"><b> 할인정보  : </b>'+myItem.discountinfofestival + '</li>';
				if(myItem.eventenddate!='' && myItem.eventenddate!=undefined)
					output += '            <li class="hn"><b> 행사 종료일  : </b>'+myItem.eventenddate + '</li>';
				if(myItem.eventhomepage!='' && myItem.eventhomepage!=undefined)
					output += '            <li class="hn"><b> 행사 홈페이지  : </b>'+myItem.eventhomepage + '</li>';
				if(myItem.eventplace!='' && myItem.eventplace!=undefined)
					output += '            <li class="hn"><b> 행사 장소  : </b>'+myItem.eventplace + '</li>';
				if(myItem.eventstartdate!='' && myItem.eventstartdate!=undefined)
					output += '            <li class="hn"><b> 행사 시작일  : </b>'+myItem.eventstartdate + '</li>';
				if(myItem.festivalgrade!='' && myItem.festivalgrade!=undefined)
					output += '            <li class="hn"><b> 축제등급  : </b>'+myItem.festivalgrade + '</li>';
				if(myItem.placeinfo!='' && myItem.placeinfo!=undefined)
					output += '            <li class="hn"><b> 행사장 위치안내  : </b>'+myItem.placeinfo + '</li>';
				if(myItem.playtime!='' && myItem.playtime!=undefined)
					output += '            <li class="hn"><b> 공연시간  : </b>'+myItem.playtime + '</li>';
				if(myItem.program!='' && myItem.program!=undefined)
					output += '            <li class="hn"><b> 행사 프로그램  : </b>'+myItem.program + '</li>';
				if(myItem.spendtimefestival!='' && myItem.spendtimefestival!=undefined)
					output += '            <li class="hn"><b> 관람 소요시간  : </b>'+myItem.spendtimefestival + '</li>';
				if(myItem.sponsor1!='' && myItem.sponsor1!=undefined)
					output += '            <li class="hn"><b> 주최자 정보  : </b>'+myItem.sponsor1 + '</li>';
				if(myItem.sponsor1tel!='' && myItem.sponsor1tel!=undefined)
					output += '            <li class="hn"><b> 주최자 연락처  : </b>'+myItem.sponsor1tel + '</li>';
				if(myItem.sponsor2!='' && myItem.sponsor2!=undefined)
					output += '            <li class="hn"><b> 주관사 정보  : </b>'+myItem.sponsor2 + '</li>';
				if(myItem.sponsor2tel!='' && myItem.sponsor2tel!=undefined)
					output += '            <li class="hn"><b> 주관사 연락처  : </b>'+myItem.sponsor2tel + '</li>';
				if(myItem.subevent!='' && myItem.subevent!=undefined)
					output += '            <li class="hn"><b> 부대행사  : </b>'+myItem.subevent + '</li>';
				if(myItem.usetimefestival!='' && myItem.usetimefestival!=undefined)
					output += '            <li class="hn"><b> 이용요금  : </b>'+myItem.usetimefestival + '</li>';
				output += '         </ul>';
				output += '   </div>';
			}else if(contenttypeid==25){
				output += '         <div class="caption">';
				output += '         <ul class="loc-font">';
				if(myItem.distance!='' && myItem.distance!=undefined)
					output += '            <li class="hn"><b>  코스 총거리  : </b>'+myItem.distance + '</li>';
				if(myItem.infocentertourcourse!='' && myItem.infocentertourcourse!=undefined)
					output += '            <li class="hn"><b> 문의 및 안내  : </b>'+myItem.infocentertourcourse + '</li>';
				if(myItem.schedule!='' && myItem.schedule!=undefined)
					output += '            <li class="hn"><b> 코스 일정  : </b>'+myItem.schedule + '</li>';
				if(myItem.taketime!='' && myItem.taketime!=undefined)
					output += '            <li class="hn"><b> 코스 총 소요시간  : </b>'+myItem.taketime + '</li>';
				if(myItem.theme!='' && myItem.theme!=undefined)
					output += '            <li class="hn"><b> 코스 테마  : </b>'+myItem.theme + '</li>';
				output += '         </ul>';
				output += '   </div>';
			}else if(contenttypeid==28){
				output += '         <div class="caption">';
				output += '         <ul class="loc-font">';
				if(myItem.accomcountleports!='' && myItem.accomcountleports!=undefined)
					output += '            <li class="hn"><b> 수용인원  : </b>'+myItem.accomcountleports + '</li>';
				if(myItem.chkbabycarriageleports!='' && myItem.chkbabycarriageleports!=undefined)
					output += '            <li class="hn"><b> 유모차대여 정보  : </b>'+myItem.chkbabycarriageleports + '</li>';
				if(myItem.chkcreditcardleports!='' && myItem.chkcreditcardleports!=undefined)
					output += '            <li class="hn"><b> 신용카드가능 정보  : </b>'+myItem.chkcreditcardleports + '</li>';
				if(myItem.chkpetleports!='' && myItem.chkpetleports!=undefined)
					output += '            <li class="hn"><b> 애완동물동반가능 정보  : </b>'+myItem.chkpetleports + '</li>';
				if(myItem.expagerangeleports!='' && myItem.expagerangeleports!=undefined)
					output += '            <li class="hn"><b> 체험 가능연령  : </b>'+myItem.expagerangeleports + '</li>';
				if(myItem.infocenterleports!='' && myItem.infocenterleports!=undefined)
					output += '            <li class="hn"><b> 문의 및 안내  : </b>'+myItem.infocenterleports + '</li>';
				if(myItem.openperiod!='' && myItem.openperiod!=undefined)
					output += '            <li class="hn"><b> 개장기간  : </b>'+myItem.openperiod + '</li>';
				if(myItem.parkingfeeleports!='' && myItem.parkingfeeleports!=undefined)
					output += '            <li class="hn"><b> 주차요금  : </b>'+myItem.parkingfeeleports + '</li>';
				if(myItem.parkingleports!='' && myItem.parkingleports!=undefined)
					output += '            <li class="hn"><b> 주차시설  : </b>'+myItem.parkingleports + '</li>';
				if(myItem.reservation!='' && myItem.reservation!=undefined)
					output += '            <li class="hn"><b> 예약안내  : </b>'+myItem.reservation + '</li>';
				if(myItem.restdateleports!='' && myItem.restdateleports!=undefined)
					output += '            <li class="hn"><b> 쉬는날  : </b>'+myItem.restdateleports + '</li>';
				if(myItem.scaleleports!='' && myItem.scaleleports!=undefined)
					output += '            <li class="hn"><b> 규모  : </b>'+myItem.scaleleports + '</li>';
				if(myItem.usefeeleports!='' && myItem.usefeeleports!=undefined)
					output += '            <li class="hn"><b> 입장료  : </b>'+myItem.usefeeleports + '</li>';
				if(myItem.usetimeleports!='' && myItem.usetimeleports!=undefined)
					output += '            <li class="hn"><b> 이용시간  : </b>'+myItem.usetimeleports + '</li>';   
				output += '         </ul>';
				output += '   </div>';
			}else if(contenttypeid==32){
				output += '         <div class="caption">';
				output += '         <ul class="loc-font">';
				if(myItem.accomcountlodging!='' && myItem.accomcountlodging!=undefined)
					output += '            <li class="hn"><b> 수용 가능인원  : </b>'+myItem.accomcountlodging + '</li>';
				if(myItem.benikia!='' && myItem.benikia!=undefined)
					output += '            <li class="hn"><b> 베니키아 여부  : </b>'+myItem.benikia + '</li>';
				if(myItem.checkintime!='' && myItem.checkintime!=undefined)
					output += '            <li class="hn"><b> 입실 시간  : </b>'+myItem.checkintime + '</li>';
				if(myItem.checkouttime!='' && myItem.checkouttime!=undefined)
					output += '            <li class="hn"><b> 퇴실 시간  : </b>'+myItem.checkouttime + '</li>';
				if(myItem.chkcooking!='' && myItem.chkcooking!=undefined)
					output += '            <li class="hn"><b> 객실내 취사 여부  : </b>'+myItem.chkcooking + '</li>';
				if(myItem.foodplace!='' && myItem.foodplace!=undefined)
					output += '            <li class="hn"><b> 식음료장  : </b>'+myItem.foodplace + '</li>';
				if(myItem.goodstay!='' && myItem.goodstay!=undefined)
					output += '            <li class="hn"><b> 굿스테이 여부  : </b>'+myItem.goodstay + '</li>';
				if(myItem.hanok!='' && myItem.hanok!=undefined)
					output += '            <li class="hn"><b> 한옥 여부  : </b>'+myItem.hanok + '</li>';
				if(myItem.infocenterlodging!='' && myItem.infocenterlodging!=undefined)
					output += '            <li class="hn"><b> 문의 및 안내  : </b>'+myItem.infocenterlodging + '</li>';
				if(myItem.parkinglodging!='' && myItem.parkinglodging!=undefined)
					output += '            <li class="hn"><b> 주차시설  : </b>'+myItem.parkinglodging + '</li>';
				if(myItem.pickup!='' && myItem.pickup!=undefined)
					output += '            <li class="hn"><b> 픽업 서비스  : </b>'+myItem.pickup + '</li>';
				if(myItem.roomcount!='' && myItem.roomcount!=undefined)
					output += '            <li class="hn"><b> 객실수  : </b>'+myItem.roomcount + '</li>';
				if(myItem.reservationloding!='' && myItem.reservationloding!=undefined)
					output += '            <li class="hn"><b> 예약안내  : </b>'+myItem.reservationloding + '</li>';
				if(myItem.reservationurl!='' && myItem.reservationurl!=undefined)
					output += '            <li class="hn"><b> 예약안내 홈페이지  : </b>'+myItem.reservationurl + '</li>';
				if(myItem.roomtype!='' && myItem.roomtype!=undefined)
					output += '            <li class="hn"><b> 객실유형  : </b>'+myItem.roomtype + '</li>';
				if(myItem.scalelodging!='' && myItem.scalelodging!=undefined)
					output += '            <li class="hn"><b> 규모  : </b>'+myItem.scalelodging + '</li>';
				if(myItem.subfacility!='' && myItem.subfacility!=undefined)
					output += '            <li class="hn"><b> 부대시설 (기타)  : </b>'+myItem.subfacility+ '</li>';
				if(myItem.barbecue!='' && myItem.barbecue!=undefined)
					output += '            <li class="hn"><b> 바베큐  : </b>'+myItem.barbecue + '</li>';
				if(myItem.beauty!='' && myItem.beauty!=undefined)
					output += '            <li class="hn"><b> 뷰티시설 정보  : </b>'+myItem.beauty + '</li>';
				if(myItem.beverage!='' && myItem.beverage!=undefined)
					output += '            <li class="hn"><b> 식음료장 여부  : </b>'+myItem.beverage + '</li>';
				if(myItem.bicycle!='' && myItem.bicycle!=undefined)
					output += '            <li class="hn"><b> 자전거 대여 여부  : </b>'+myItem.bicycle + '</li>';
				if(myItem.campfire!='' && myItem.campfire!=undefined)
					output += '            <li class="hn"><b> 캡프파이어 여부  : </b>'+myItem.campfire + '</li>';
				if(myItem.fitness!='' && myItem.fitness!=undefined)
					output += '            <li class="hn"><b> 휘트니스 센터 여부  : </b>'+myItem.fitness + '</li>';
				if(myItem.karaoke!='' && myItem.karaoke!=undefined)
					output += '            <li class="hn"><b> 노래방 여부  : </b>'+myItem.karaoke + '</li>';
				if(myItem.publicbath!='' && myItem.publicbath!=undefined)
					output += '            <li class="hn"><b> 공용 샤워실 여부  : </b>'+myItem.publicbath + '</li>';
				if(myItem.publicpc!='' && myItem.publicpc!=undefined)
					output += '            <li class="hn"><b> 공용 PC실 여부  : </b>'+myItem.publicpc + '</li>';
				if(myItem.sauna!='' && myItem.sauna!=undefined)
					output += '            <li class="hn"><b> 사우나실 여부  : </b>'+myItem.sauna + '</li>';
				if(myItem.seminar!='' && myItem.seminar!=undefined)
					output += '            <li class="hn"><b> 세미나실 여부  : </b>'+myItem.seminar + '</li>';
				if(myItem.sports!='' && myItem.sports!=undefined)
					output += '            <li class="hn"><b> 스포츠 시설 여부  : </b>'+myItem.sports + '</li>';
				if(myItem.refundregulation!='' && myItem.refundregulation!=undefined)
					output += '            <li class="hn"><b> 환불규정  : </b>'+myItem.refundregulation + '</li>';   
				output += '         </ul>';
				output += '   </div>';
			}else if(contenttypeid==38){
				output += '         <div class="caption">';
				output += '         <ul class="loc-font">';
				if(myItem.chkbabycarriageshopping!='' && myItem.chkbabycarriageshopping!=undefined)
					output += '            <li class="hn"><b> 유모차대여 정보  : </b>'+myItem.chkbabycarriageshopping + '</li>';
				if(myItem.chkcreditcardshopping!='' && myItem.chkcreditcardshopping!=undefined)
					output += '            <li class="hn"><b> 신용카드가능 정보  : </b>'+myItem.chkcreditcardshopping + '</li>';
				if(myItem.chkpetshopping!='' && myItem.chkpetshopping!=undefined)
					output += '            <li class="hn"><b> 애완동물동반가능 정보  : </b>'+myItem.chkpetshopping + '</li>';
				if(myItem.culturecenter!='' && myItem.culturecenter!=undefined)
					output += '            <li class="hn"><b> 문화센터 바로가기  : </b>'+myItem.culturecenter + '</li>';
				if(myItem.fairday!='' && myItem.fairday!=undefined)
					output += '            <li class="hn"><b> 장서는 날  : </b>'+myItem.fairday + '</li>';
				if(myItem.infocentershopping!='' && myItem.infocentershopping!=undefined)
					output += '            <li class="hn"><b> 문의 및 안내  : </b>'+myItem.infocentershopping + '</li>';
				if(myItem.opendateshopping!='' && myItem.opendateshopping!=undefined)
					output += '            <li class="hn"><b> 개장일  : </b>'+myItem.opendateshopping + '</li>';
				if(myItem.opendate!='' && myItem.opendate!=undefined)
					output += '            <li class="hn"><b> 영업시간  : </b>'+myItem.opendate + '</li>';
				if(myItem.parkingshopping!='' && myItem.parkingshopping!=undefined)
					output += '            <li class="hn"><b> 주차시설  : </b>'+myItem.parkingshopping + '</li>';
				if(myItem.restdateshopping!='' && myItem.restdateshopping!=undefined)
					output += '            <li class="hn"><b> 쉬는날  : </b>'+myItem.restdateshopping + '</li>';
				if(myItem.restroom!='' && myItem.restroom!=undefined)
					output += '            <li class="hn"><b> 화장실 설명  : </b>'+myItem.restroom + '</li>';
				if(myItem.saleitem!='' && myItem.saleitem!=undefined)
					output += '            <li class="hn"><b> 판매 품목  : </b>'+myItem.saleitem + '</li>';
				if(myItem.saleitemcost!='' && myItem.saleitemcost!=undefined)
					output += '            <li class="hn"><b> 판매 품목별 가격  : </b>'+myItem.saleitemcost + '</li>';
				if(myItem.scaleshopping!='' && myItem.scaleshopping!=undefined)
					output += '            <li class="hn"><b> 규모  : </b>'+myItem.scaleshopping + '</li>';
				if(myItem.shopguide!='' && myItem.shopguide!=undefined)
					output += '            <li class="hn"><b> 매장안내  : </b>'+myItem.shopguide + '</li>';
				output += '         </ul>';
				output += '   </div>';
			}else if(contenttypeid==39){
				output += '         <div class="caption">';
				output += '         <ul class="loc-font">';
				if(myItem.chkcreditcardfood!='' && myItem.chkcreditcardfood!=undefined)
					output += '            <li class="hn"><b> 신용카드가능 정보  : </b>'+myItem.chkcreditcardfood + '</li>';
				if(myItem.discountinfofood!='' && myItem.discountinfofood!=undefined)
					output += '            <li class="hn"><b> 할인정보  : </b>'+myItem.discountinfofood + '</li>';
				if(myItem.firstmenu!='' && myItem.firstmenu!=undefined)
					output += '            <li class="hn"><b> 대표 메뉴  : </b>'+myItem.firstmenu + '</li>';
				if(myItem.infocenterfood!='' && myItem.infocenterfood!=undefined)
					output += '            <li class="hn"><b> 문의 및 안내  : </b>'+myItem.infocenterfood + '</li>';
				if(myItem.kidsfacility!='' && myItem.kidsfacility!=undefined)
					output += '            <li class="hn"><b> 어린이 놀이방 여부  : </b>'+myItem.kidsfacility + '</li>';
				if(myItem.opendatefood!='' && myItem.opendatefood!=undefined)
					output += '            <li class="hn"><b> 개업일  : </b>'+myItem.opendatefood + '</li>';
				if(myItem.opentimefood!='' && myItem.opentimefood!=undefined)
					output += '            <li class="hn"><b> 영업시간  : </b>'+myItem.opentimefood + '</li>';
				if(myItem.packing!='' && myItem.packing!=undefined)
					output += '            <li class="hn"><b> 포장 가능  : </b>'+myItem.packing + '</li>';
				if(myItem.parkingfood!='' && myItem.parkingfood!=undefined)
					output += '            <li class="hn"><b> 주차시설  : </b>'+myItem.parkingfood + '</li>';
				if(myItem.reservationfood!='' && myItem.reservationfood!=undefined)
					output += '            <li class="hn"><b> 예약안내  : </b>'+myItem.reservationfood + '</li>';
				if(myItem.restdatefood!='' && myItem.restdatefood!=undefined)
					output += '            <li class="hn"><b> 쉬는날  : </b>'+myItem.restdatefood + '</li>';
				if(myItem.scalefood!='' && myItem.scalefood!=undefined)
					output += '            <li class="hn"><b> 규모  : </b>'+myItem.scalefood + '</li>';
				if(myItem.seat!='' && myItem.seat!=undefined)
					output += '            <li class="hn"><b> 좌석수  : </b>'+myItem.seat + '</li>';
				if(myItem.smoking!='' && myItem.smoking!=undefined)
					output += '            <li class="hn"><b> 금연/흡연 여부  : </b>'+myItem.smoking + '</li>';
				if(myItem.treatmenu!='' && myItem.treatmenu!=undefined)
					output += '            <li class="hn"><b> 취급 메뉴  : </b>'+myItem.treatmenu + '</li>';
				output += '         </ul>';
				output += '   </div>';
			}
			output += '</div>';
			output += '</div>';
			$('#output').append(output);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) { 
			alert("Status: " + textStatus +"and "+ "Error: " + errorThrown); 
		}  
	});
};
//================== 소개정보=======================//

