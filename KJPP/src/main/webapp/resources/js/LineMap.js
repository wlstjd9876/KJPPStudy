var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new daum.maps.LatLng(33.452739313807456, 126.5709308145358), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다


var positions = [
    {  
        title: '카카오', 
        latlng: new daum.maps.LatLng(33.450705, 126.570677)
    },
    {
        title: '생태연못', 
        latlng: new daum.maps.LatLng(33.450936, 126.569477)
    },
    {
        title: '텃밭', 
        latlng: new daum.maps.LatLng(33.450879, 126.569940)
    },
    {
        title: '근린공원',
        latlng: new daum.maps.LatLng(33.451393, 126.570738)
    }
];
var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 

for (var i = 0; i < positions.length; i ++) {
    
    // 마커 이미지의 이미지 크기 입니다
    var imageSize = new daum.maps.Size(24, 35); 
    
    // 마커 이미지를 생성합니다    
    var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize); 
    
    // 마커를 생성합니다
    var marker = new daum.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image : markerImage // 마커 이미지 
    });
}

var linePath = [];
for(var i = 0; i < positions.length; i ++){
	linePath.push(positions[i].latlng);
}

// 선을 구성하는 좌표 배열입니다. 이 좌표들을 이어서 선을 표시합니다

// 지도에 표시할 선을 생성합니다
var polyline = new daum.maps.Polyline({
    path: linePath, // 선을 구성하는 좌표배열 입니다
    strokeWeight: 5, // 선의 두께 입니다
    strokeColor: '#FFAE00', // 선의 색깔입니다
    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
    strokeStyle: 'solid' // 선의 스타일입니다
});

// 지도에 선을 표시합니다 
polyline.setMap(map);  

