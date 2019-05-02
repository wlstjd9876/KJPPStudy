function insert(contentid){

		
	$.ajax({        
			url: '../favinsert.do',
			data:{contentid:contentid},
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
		          alert("일정추가 오류 발생!");
		       }  
		});
	};
	
	



var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new daum.maps.LatLng(37.56794664916501, 126.98300418110988), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    };
var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다


//장소 검색 객체를 생성합니다
var ps = new daum.maps.services.Places();  

//검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new daum.maps.InfoWindow({zIndex:1});

//키워드로 장소를 검색합니다
searchPlaces();
//마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
    var imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new daum.maps.Size(36, 37),  // 마커 이미지의 크기
        imgOptions =  {
            spriteSize : new daum.maps.Size(36, 691), // 스프라이트 이미지의 크기
            spriteOrigin : new daum.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new daum.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new daum.maps.MarkerImage(imageSrc, imageSize, imgOptions),
            marker = new daum.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage 
        });

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.push(marker);  // 배열에 생성된 마커를 추가합니다

    return marker;
}
//키워드 검색을 요청하는 함수입니다
function searchPlaces() {

  var keyword = document.getElementById('keyword').value;

  if (!keyword.replace(/^\s+|\s+$/g, '')) {
      alert('키워드를 입력해주세요!');
      return false;
  }
  // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
  ps.keywordSearch( keyword, placesSearchCB); 
}
  
function placesSearchCB(data, status, pagination) {
  if (status === daum.maps.services.Status.OK) {

      // 정상적으로 검색이 완료됐으면
      // 검색 목록과 마커를 표출합니다
      displayPlaces(data);

      // 페이지 번호를 표출합니다
      displayPagination(pagination);

  } else if (status === daum.maps.services.Status.ZERO_RESULT) {

      alert('검색 결과가 존재하지 않습니다.');
      return;

  } else if (status === daum.maps.services.Status.ERROR) {

      alert('검색 결과 중 오류가 발생했습니다.');
      return;

  }
}
  
//검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {

  var listEl = document.getElementById('placesList'), 
  menuEl = document.getElementById('menu_wrap'),
  fragment = document.createDocumentFragment(), 
  bounds = new daum.maps.LatLngBounds(), 
  listStr = '';
  
  // 검색 결과 목록에 추가된 항목들을 제거합니다
  removeAllChildNods(listEl);

  // 지도에 표시되고 있는 마커를 제거합니다
  removeMarker();
  
  for ( var i=0; i<places.length; i++ ) {

      // 마커를 생성하고 지도에 표시합니다
      var placePosition = new daum.maps.LatLng(places[i].y, places[i].x),
          marker = addMarker(placePosition, i), 
          itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다
      
      // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
      // LatLngBounds 객체에 좌표를 추가합니다
      bounds.extend(placePosition);

      // 마커와 검색결과 항목에 mouseover 했을때
      // 해당 장소에 인포윈도우에 장소명을 표시합니다
      // mouseout 했을 때는 인포윈도우를 닫습니다
      (function(marker, title) {
          daum.maps.event.addListener(marker, 'mouseover', function() {
              displayInfowindow(marker, title);
          });

          daum.maps.event.addListener(marker, 'mouseout', function() {
              infowindow.close();
          });

          itemEl.onmouseover =  function () {
              displayInfowindow(marker, title);
          };

          itemEl.onmouseout =  function () {
              infowindow.close();
          };
      })(marker, places[i].place_name);

      fragment.appendChild(itemEl);
      
      
  }
  // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
  listEl.appendChild(fragment);
  menuEl.scrollTop = 0;

  // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
  map.setBounds(bounds);
}

//검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {

  var el = document.createElement('li'),
  itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
              '<div class="info">' +
              '   <h5>' + places.place_name + '</h5>';

  if (places.road_address_name) {
      itemStr += '    <span>' + places.road_address_name + '</span>' +
                  '   <span class="jibun gray">' +  places.address_name  + '</span>';
  } else {
      itemStr += '    <span>' +  places.address_name  + '</span>'; 
  }
               
    itemStr += '  <span class="tel">' + places.phone  + '</span>' +
              '</div>';           

  el.innerHTML = itemStr;
  el.className = 'item';

  return el;
}

//지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
  for ( var i = 0; i < markers.length; i++ ) {
      markers[i].setMap(null);
  }   
  markers = [];
}

//검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
  var paginationEl = document.getElementById('pagination'),
      fragment = document.createDocumentFragment(),
      i; 

  // 기존에 추가된 페이지번호를 삭제합니다
  while (paginationEl.hasChildNodes()) {
      paginationEl.removeChild (paginationEl.lastChild);
  }

  for (i=1; i<=pagination.last; i++) {
      var el = document.createElement('a');
      el.href = "#";
      el.innerHTML = i;

      if (i===pagination.current) {
          el.className = 'on';
      } else {
          el.onclick = (function(i) {
              return function() {
                  pagination.gotoPage(i);
              }
          })(i);
      }

      fragment.appendChild(el);
  }
  paginationEl.appendChild(fragment);
}

//검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
//인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

infowindow.setContent(content);
infowindow.open(map, marker);
}

//검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {   
while (el.hasChildNodes()) {
   el.removeChild (el.lastChild);
}
}

var marker = new daum.maps.Marker({ 
    // 지도 중심좌표에 마커를 생성합니다 
    position: map.getCenter() 
}); 
var markers = new Array;
//지도에 클릭 이벤트를 등록합니다
//지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
daum.maps.event.addListener(map, 'click', function(mouseEvent) {
if(markers!=null){
 for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    } 
markers = new Array; 
}    
   
 // 클릭한 위도, 경도 정보를 가져옵니다 
 var latlng = mouseEvent.latLng;
 var lat = latlng.getLat();
 var lon = latlng.getLng();
 marker.setPosition(latlng);
 /*alert("lat : " +lat + " and  lon : " +lon);*/
 
 
   $.ajax({        
         url: '../publicData',
         data:{lat:lat,lon:lon},
         type: 'get',
         dataType: 'json',
         cache:false,
            timeout:30000,
         success: function(data){
            console.log(data);
//             console.log(data.response.body.items.item);
             var myItem = data.response.body.items.item;
             var positions= new Array;
             $('#output').empty();
            
            if(myItem){
               
                  for(var i=0; i<myItem.length; i++){
                	  
                      var output = '';
                   
                      
                     if(myItem[i].firstimage==undefined){
                        var image = "../resources/img/data/No_Image_Available.jpg";
                     }else{
                        var image = myItem[i].firstimage;
                     }
                     if(myItem[i].addr1==undefined){
                         var addr = "해당 주소가 없습니다.";
                      }else{
                         var addr = myItem[i].addr1;
                      }
                      console.log(myItem.length);
     /*                  output += '<h3>'+ i+'번째 ' + '여행지정보' +'</h3>'; */
                      output += '<div class="w3-card-4 w3-margin hn" style=" border:3px solid white; width:80%;">';
                      output += 	'<div class="w3-container hn" style="padding-top: 15px; padding-bottom: 15px;">'
                      output += 		'<img src="' + image + '" style=" width: 99.9%; height: 300px;">';
                      output += 		'<h3 class="hn">'+myItem[i].title+'</h3>';
                      output += 		'<h4 class="hn">'+ addr +'</h4>';
                /*    output += 		'<h4>'+myItem[i].mapx+'</h4>';
                      output += 		'<h4>'+myItem[i].mapy+'</h4>';*/
                      output += 		'<button class="insertButton btn btn-default" style="float: right;" padding-left:100px;" onclick="javascript:insert('+myItem[i].contentid+');">일정추가</button>';
                      output += 		'<br>';
                      output += 	'</div>'
                      output += '</div>';
                   
                    /*  document.span.innerHTML += output;  */
                      $('#output').append(output);
                    positions.push(
                           {
                               title: myItem[i].title, 
                               latlng: new daum.maps.LatLng(myItem[i].mapy,myItem[i].mapx)
                           }     
                      ); 
                
                  }
                
               /*   alert(myItem.length);*/
                  for (var i = 0; i < positions.length; i ++) {
                       // 마커 이미지의 이미지 크기 입니다
                       var imageSize = new daum.maps.Size(34, 39); 
                       // 마커 이미지를 생성합니다    
                       var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize); 
                       // 마커를 생성합니다
                       var marker = new daum.maps.Marker({
                           map: map, // 마커를 표시할 지도
                           position: positions[i].latlng, // 마커를 표시할 위치
                           title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                           image : markerImage // 마커 이미지 
                       });
      markers.push(marker);
                   } 
                  
            }else{
               alert('관광지 정보가 없습니다.');
            }   
           /* alert(positions);*/
         },
     error: function(XMLHttpRequest, textStatus, errorThrown) { 
         alert("Status: " + textStatus +" and "+ "Error: " + errorThrown); 
     }  
});
});



// ======================================================마커이미지 표시

var imageSrc2 = '../resources/img/yap.png', // 마커이미지의 주소입니다  
    imageSize = new daum.maps.Size(34, 34), // 마커이미지의 크기입니다
    imageOption = {offset: new daum.maps.Point(5, 40)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
                                    //x,y축 숫자가 커지면 < ,^ 늘어남 
// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
var markerImage2 = new daum.maps.MarkerImage(imageSrc2, imageSize, imageOption),
    markerPosition = new daum.maps.LatLng(37.56794664916501, 126.98300418110988); // 마커가 표시될 위치입니다

var imageSrc = '../resources/img/yap2.png', // 마커이미지의 주소입니다  
imageSize = new daum.maps.Size(34, 34), // 마커이미지의 크기입니다
imageOption = {offset: new daum.maps.Point(5, 40)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
                                //x,y축 숫자가 커지면 < ,^ 늘어남 
//마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize, imageOption),
markerPosition = new daum.maps.LatLng(37.56794664916501, 126.98300418110988); // 마커가 표시될 위치입니다



marker.setMap(null);
// 마커를 생성합니다
var marker = new daum.maps.Marker({
    position:map.getCenter() , 
    image: markerImage2 // 마커이미지 설정 
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);  


 