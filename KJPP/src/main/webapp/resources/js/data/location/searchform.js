//================== 서치 시작=====================//
$.fn.toggleState = function(b) {
   $(this).stop().animate({
      width : b ? "494px" : "50px"
   }, 600, "easeOutElastic");
};

$(document).ready(function() {
   var pageBlock = 5;
   var container = $(".container");
   var boxContainer = $(".search-box-container");
   var submit = $(".submit");
   var searchBox = $(".search-box");
   var response = $(".response");
   var isOpen = false;
   var target = document.getElementById("areacode");
   var target2 = document.getElementById("sigungucode");
   var value;
   var keyword;
   submit.on("mousedown", function(e) {
      e.preventDefault();
      boxContainer.toggleState(!isOpen);
      isOpen = !isOpen;
      if (!isOpen) {
         handleRequest();
      } else {
         searchBox.focus();
      }
   });
   searchBox.keypress(function(e) {
      if (e.which === 13) {
         boxContainer.toggleState(false);
         isOpen = false;
         handleRequest();
      }
   });
   searchBox.blur(function() {
      boxContainer.toggleState(false);
      isOpen = false;
   });
   function handleRequest() {
      // You could do an ajax request here...
      value = searchBox.val();
      searchBox.val('');
      if (value.length > 0) {
         keyword = value;
         $('#output').empty();
         $('.paging_button').empty();
         response.text('Searching for "' + value + '" . . .');
         response.animate({
            opacity : 1
         }, 300);
         //=================검색 시작====================//
         getItems(1, value);
      }
   };
   function getItems(pageNo, value){
      $.ajax({        
         url: contextPath+'/searchAjax',
         data:{areaCode:target.options[target.selectedIndex].value, sigunguCode:target2.options[target2.selectedIndex].value, keyword:value, pageNo:pageNo},
         type: 'get',
         dataType: 'json',
         cache:false,
         timeout:30000,
         success: function(data){
            response.animate({
               opacity : 0
            }, 300);
            $('#output').empty();
            $('.paging_button').empty();
            console.log(data);
            
            var myItem = data.response.body.items.item;
            var myBody = data.response.body;

            var output = '';
            output += '<div class="row align-center">';
            if(myItem!=undefined){
               for(var i=0; i<myItem.length; i++){
                  if(myItem[i].firstimage==undefined)
                     var image = contextPath+"/resources/img/data/No_Image_Available.jpg";
                  else
                     var image = myItem[i].firstimage;
                  console.log(myItem.length);
                  output += '<div class="col-sm-6 col-md-3 align-center padd-both" onclick="currentDiv(3)>';
                  output += '   <div class="thumbnail">';
                  output += '      <img src="' + image + '" style="width: 100%; height: 300px;">';
                  output += '         <div class="caption">';
                  output += '            <h3 class="hn">'+myItem[i].title+'</h3>';
                  output += '            <h4 class="hn">'+myItem[i].addr1+'</h4>';
                  output += '            <p><a href="'+contextPath+'/data/location/view.do?contentId='+myItem[i].contentid+'&contenttypeid='+myItem[i].contenttypeid+'" class="btn btn-primary hn">자세히 보기</a></p>';
                  output += '         </div>';
                  output += '   </div>';
                  output += '</div>';
               }
               var totalPage = Math.ceil(myBody.totalCount/myBody.numOfRows);

               if(myBody.pageNo == undefined || myBody.pageNo == ''){
                  myBody.pageNo = 1;
               }

               //현재 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
               if(myBody.pageNo > totalPage){
                  myBody.pageNo = totalPage;
               }

               var startPage = Math.floor((myBody.pageNo-1)/pageBlock)*pageBlock + 1;
               var endPage = startPage + pageBlock -1;

               //마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
               if(endPage > totalPage){
                  endPage = totalPage;
               }
               var add = '';

               if(startPage>pageBlock){
                  add += '<li data-page='+(startPage-1)+' class="paging_btn hn">Prev</li>'; 
               }

               for(var i=startPage;i<=endPage;i++){
                  if(i!=pageNo)
                     add += '<li data-page='+i+' class="paging_btn myButton">'+i+'</li>';
                  else
                     add += '<li data-page='+i+' class="paging_btn myButton selbtn">'+i+'</li>';
               }

               if(endPage < totalPage){
                  add += '<li data-page='+(startPage+pageBlock)+' class="paging_btn hn">Next</li>';
               }
               $('.paging_button').append(add);

            }else{
               output += '<h3 class="hn">검색 결과가 없습니다!</h3>';
            }
            output += '</div>';
            $('#output').append(output);
         },
         error: function(XMLHttpRequest, textStatus, errorThrown) { 
            alert("Status: " + textStatus +"and "+ "Error: " + errorThrown); 
         }  
      });
   };

//   =================검색 끝=====================//

//   페이지 버튼 이벤트 연결
   $(document).on('click', '.paging_button li', function(){
      //페이지 번호를 읽어들임
      currentPage = $(this).attr('data-page');
      //목록호출
      getItems(currentPage,keyword);
   });

});
//================== 서치 끝 ======================//
//================== 디테일=======================//
//================== 디테일=======================//
//==================셀렉 시작======================//
function doChange(srcE){
   var val = srcE.options[srcE.selectedIndex].value;
   $.ajax({
      url : "sigunguAjax.do",
      type: "post",
      dataType: "json",
      data : {val :val},
      success:function(data){
         $("#sigungucode").find("option").remove().end().append("<option value='0'>시군구 선택</option>");
         $(data.list).each(function(index, result){
            $("#sigungucode").append("<option value='"+(index+1)+"'>"+result+"</option>");
         });
      },
      error:function(request,status,error){
         alert("uniteCommonAjax:request:"+request+"status:"+status+"error:"+error);
      }
   });   
}
//=================셀렉 끝======================//