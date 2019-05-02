function detail1(contentId,lol){
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
			if(myItem.firstimage==undefined){
				var image =contextPath+ "/resources/img/data/No_Image_Available.jpg";
			}	else{
				var image = myItem.firstimage;
			}
			console.log(myItem.length);
			var title = myItem.title;
			$('#nunu'+lol).append(title);
			$('#juso'+lol).attr("src",image);
			
			
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) { 
			alert("Status: " + textStatus +"and "+ "Error: " + errorThrown); 
		}  
	});
};

function detail2(contentId,lol){
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
				var image =contextPath+ "/resources/img/data/No_Image_Available.jpg";
			else
				var image = myItem.firstimage;
			console.log(myItem.length);
			var title = myItem.title;
			
			
			$('#nunum'+lol).append(title);
			$('#jusom'+lol).attr("src",image);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) { 
			alert("Status: " + textStatus +"and "+ "Error: " + errorThrown); 
		}  
	});
};