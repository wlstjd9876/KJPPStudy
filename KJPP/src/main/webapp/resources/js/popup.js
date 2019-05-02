$(document).ready(function(){
	$("#layerPopup").hide();
	$("#contents > a").click(function(){
		$("#contents > a").blur();
		$("#layerPopup").show();
		$("#layerPopup a").focus();
		return false;
	});
	$("#layerPopup a").keydown(function(e){
		if(e.shiftKey && e.keyCode == 9){ // Shift + Tab 키를 의미합니다.
			$("#contents > a").focus();
			$("#layerPopup").hide();
			return false;
		}
	});

	$("#layerPopup button").click(function(){
		$("#contents > a").focus();
		$("#layerPopup").hide();
	});
});