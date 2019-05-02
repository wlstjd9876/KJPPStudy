/*
	$.ajax({        
	      url: '../publicData',
	      type: 'get',
	      dataType: 'json',
	      success: function(data){
	    	  console.log(data);
// 	          console.log(data.response.body.items.item);
	          var myItem = data.response.body.items.item;
	           
	          for(var i=0; myItem.length; i++){
	              var output = '';
	              console.log(myItem.length);
 	              output += '<h3>'+ i+'번째 ' + '여행지정보' +'</h3>'; 
 	              output += '<h3>'+myItem[i].title+'</h3>';
	              output += '<h4>'+myItem[i].addr1+'</h4>';
	              output += '<img src="' + myItem[i].firstimage + '" style="width: 100px; height: 100px;">';
	              output += '<br>';
	              output += '위도 : ' +'<h4>'+myItem[i].mapy+'</h4>';
	              output += '경도 : ' +'<h4>'+myItem[i].mapx+'</h4>';
	              output += '<input type="button" value="일정추가"  onclick="location.href=\'#\'" />';
	              document.span.innerHTML += output;  
	               $('#output').append(output); 
	          }
	      },
    	error: function(XMLHttpRequest, textStatus, errorThrown) { 
        	alert("Status: " + textStatus +" and "+ "Error: " + errorThrown); 
    	}  
});*/
	