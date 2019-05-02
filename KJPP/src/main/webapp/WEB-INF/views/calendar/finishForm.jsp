<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/full/jscolor.js"></script>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
.hn{font-family: 'Hanna', sans-serif;
   }
</style>
<style>
	.styleWrap {
		padding: 50px;
	}
	
	.styleWrap .styleBtnGroup {
		max-width: 800px;
		margin: auto;
	}
	
	.styleWrap .styleBtnGroup .styleMethod {
		padding: 40px;
		box-shadow: none;
		position: relative;
	}
	
	.styleWrap .styleBtnGroup .styleMethod.active {
		outline: none !important;
	}
	
	.styleWrap .styleBtnGroup .styleMethod.active .method {
		border-color: #4cd264;
		outline: none !important;
		box-shadow: 0px 3px 22px 0px #7b7b7b;
	}
	
	.styleWrap .styleBtnGroup .styleMethod .method {
		position: absolute;
		right: 3px;
		top: 3px;
		bottom: 3px;
		left: 3px;
		background-size: contain;
		background-position: center;
		background-repeat: no-repeat;
		border: 2px solid transparent;
		transition: all 0.5s;
	}
	
	.styleWrap .styleBtnGroup .styleMethod .method.alone-female {
		background-image: url("${pageContext.request.contextPath}/resources/img/cal-style/capture_01.gif")
	}
	.styleWrap .styleBtnGroup .styleMethod .method.alone-male {
		background-image: url("${pageContext.request.contextPath}/resources/img/cal-style/capture_02.gif")
	}
	.styleWrap .styleBtnGroup .styleMethod .method.couple {
		background-image: url("${pageContext.request.contextPath}/resources/img/cal-style/capture_03.gif")
	}
	.styleWrap .styleBtnGroup .styleMethod .method.female {
		background-image: url("${pageContext.request.contextPath}/resources/img/cal-style/capture_04.gif")
	}
	.styleWrap .styleBtnGroup .styleMethod .method.male {
		background-image: url("${pageContext.request.contextPath}/resources/img/cal-style/capture_05.gif")
	}
	.styleWrap .styleBtnGroup .styleMethod .method.together {
		background-image: url("${pageContext.request.contextPath}/resources/img/cal-style/capture_06.gif")
	}
	.styleWrap .styleBtnGroup .styleMethod .method.together-child {
		background-image: url("${pageContext.request.contextPath}/resources/img/cal-style/capture_07.gif")
	}
	.styleWrap .styleBtnGroup .styleMethod .method.together-grandparent {
		background-image: url("${pageContext.request.contextPath}/resources/img/cal-style/capture_08.gif")
	}
	.styleWrap .styleBtnGroup .styleMethod .method.grandparent {
		background-image: url("${pageContext.request.contextPath}/resources/img/cal-style/capture_09.gif")
	}
	
	.styleWrap .styleBtnGroup .styleMethod .method:hover {
		border-color: #4cd264;
		outline: none !important;
	}
	
	/* 이미지 추가 */
	.img-upload-btn { 
    position: relative; 
    overflow: hidden; 
    padding-top: 95%;
} 

.img-upload-btn input[type=file] { 
    position: absolute; 
    top: 0; 
    right: 0; 
    min-width: 100%; 
    min-height: 100%; 
    font-size: 100px; 
    text-align: right; 
    filter: alpha(opacity=0); 
    opacity: 0; 
    outline: none; 
    background: white; 
    cursor: inherit; 
    display: block; 
} 

.img-upload-btn i { 
    position: absolute;
    height: 16px;
    width: 16px;
    top: 50%;
    left: 50%;
    margin-top: -8px;
    margin-left: -8px;
}

.btn-radio {
    position: relative; 
    overflow: hidden; 
}

.btn-radio input[type=radio] { 
    position: absolute; 
    top: 0; 
    right: 0; 
    min-width: 100%; 
    min-height: 100%; 
    font-size: 100px; 
    text-align: right; 
    filter: alpha(opacity=0); 
    opacity: 0; 
    outline: none; 
    background: white; 
    cursor: inherit; 
    display: block; 
}
</style>
<script type="text/javascript">
var i = 0;

/* 이미지 추가 */
(function ($) {
	
    
    $.fn.imagePicker = function(options) {
        
        // Define plugin options
        var settings = $.extend({
	         // Input name attribute
	         name: "",
            // Classes for styling the input
            class: "form-control btn btn-default btn-block",
            // Icon which displays in center of input
            icon: "glyphicon glyphicon-plus"
        }, options );
        
        // Create an input inside each matched element
        return this.each(function() {
           
            $(this).html(create_btn(this, settings));
        });
    };
 
    // Private function for creating the input element
    function create_btn(that, settings, input_id) {
       i+=1;
       
        // The input icon element
        var picker_btn_icon = $('<i class="'+settings.icon+'"></i>');
        
        // The actual file input which stays hidden
        var picker_btn_input
        if(input_id==undefined){
           picker_btn_input = $('<input type="file" name="'+settings.name + i + '" id="'+settings.name + i + '" />');
        }else{
           picker_btn_input = $('<input type="file" name="'+input_id + '" id="'+input_id + '" />');
        }
        // The actual element displayed
        var picker_btn = $('<div class="'+settings.class+' img-upload-btn"></div>').append(picker_btn_icon).append(picker_btn_input);
        
        // File load listener
        picker_btn_input.change(function() {
           
           var input_id = $(this).attr('id');
           
            if ($(this).prop('files')[0]) {
               $('#finish_form').prepend($(this).css('display','none'));
               
                // Use FileReader to get file
                var reader = new FileReader();
                
                // Create a preview once image has loaded
                reader.onload = function(e) {
                    var preview = create_preview(that, e.target.result, settings, input_id);
                    $(that).html(preview);
                }
                
                // Load image
                reader.readAsDataURL(picker_btn_input.prop('files')[0]);
            }
        });

        return picker_btn;
    };
    
    // Private function for creating a preview element
    function create_preview(that, src, settings, input_id) {
        
            // The preview image
            var picker_preview_image = $('<img src="'+src+'" class="img-responsive img-rounded" />');
            
            // The remove image button
            var picker_preview_remove = $('<button class="btn btn-link" data-id="'+input_id+'"><small>Remove</small></button>');
            
            // The preview element
            var picker_preview = $('<div class="text-center"></div>').append(picker_preview_image).append(picker_preview_remove);

            // Remove image listener
            picker_preview_remove.click(function() {
               var data_id = $(this).attr('data-id');
                var btn = create_btn(that, settings, data_id);
                $(that).html(btn);
                //파일 전송을 위한 숨겨진 태그 삭제
                $('#'+data_id).remove();
            });
            
            return picker_preview;
    };
}( jQuery ));

$(document).ready(function(){
   $('.img-picker').imagePicker({name: 'upload_s_photo'});  
});
</script>
<div class="container" style="padding: 10px;">
	<div class="row hn">
		<form:form commandName="calendarCommand" action="finish.do" id="finish_form" enctype="multipart/form-data">
			<!-- 왼쪽 -->
			<br>
			<div class="col-md-6 hn">
				<%-- <form:hidden path="s_num"/> --%>
				<form:hidden value="${user_email}" path="email"/>
				<div class="form-group">
					<label for="s_title">여행 제목</label>
					<form:input path="s_title" class="form-control"/>
				</div>
				<div class="form-group">
					<label for="s_startdate">출발 일자</label>
					<input type="date" name="s_startdate" id="s_startdate" class="form-control">
					<input type="date" name="s_enddate" id="s_enddate" class="form-control">
					<label for="checkbox-inline">
						<input type="checkbox" name="s_finish" value="0">미정
					</label>
				</div>
				<!-- 여행테마 -->
				<div class="form-group">
					<label for="s_style">여행 테마</label>
					<div class="styleWrap">
						<div class="btn-group styleBtnGroup btn-group-justified" data-toggle="buttons">
							<div>
								<label class="btn styleMethod">
									<div class="method alone-female"></div>
									<input type="radio" name="s_style" value="1">
								</label>
					            <label class="btn styleMethod">
					            	<div class="method alone-male"></div>
					                <input type="radio" name="s_style" value="2"> 
					            </label>
					            <label class="btn styleMethod">
				            		<div class="method couple"></div>
					                <input type="radio" name="s_style" value="3">
					            </label>
							</div>
							<div>
								<label class="btn styleMethod">
									<div class="method female"></div>
					                <input type="radio" name="s_style" value="4"> 
					            </label>
					            <label class="btn styleMethod">
				            		<div class="method male"></div>
					                <input type="radio" name="s_style" value="5"> 
					            </label>
					            <label class="btn styleMethod">  
				            		<div class="method together"></div>
					                <input type="radio" name="s_style" value="6"> 
					            </label>
							</div>
							<div>
								<label class="btn styleMethod">
				            		<div class="method together-child"></div>
					                <input type="radio" name="s_style" value="7"> 
					            </label>
					            <label class="btn styleMethod">
				            		<div class="method together-grandparent"></div>
					                <input type="radio" name="s_style" value="8"> 
					            </label>
					            <label class="btn styleMethod">
				            		<div class="method grandparent"></div>
					                <input type="radio" name="s_style" value="9"> 
					            </label>
					        </div>
				        </div>
					</div>
				</div>
				<!-- 여행테마 끝 -->
				<div class="form-group">
					<label for="s_share">공개여부</label>
					<label for="s_share_0" class="radio-inline">
						<input type="radio" name="s_share" id="s_share_0" value="0">공개
					</label>
					<label for="s_share_1" class="radio-inline">
						<input type="radio" name="s_share" id="s_share_1" value="1">비공개
					</label>
				</div>
				<div class="form-group">
					<label for="s_traffic">교통수단</label>
					<label class="checkbox-inline">
						<input type="checkbox" name="s_traffic" id="s_traffic_1" value="1">차량
					</label>
					<label class="checkbox-inline">
						<input type="checkbox" name="s_traffic" id="s_traffic_2" value="2">고속/시외버스
					</label>
					<label class="checkbox-inline">
						<input type="checkbox" name="s_traffic" id="s_traffic_3" value="3">기차/KTX
					</label>
					<label class="checkbox-inline">
						<input type="checkbox" name="s_traffic" id="s_traffic_4" value="4">비행기
					</label>
				</div>
			</div>
			<!-- 오른쪽 -->
			<div class="col-md-6 hn">
				<!-- 일정 색상 -->
				<div class="form-group">
					<label for="s_color">일정 색상</label>
					<input id="s_color" class="jscolor form-control" name="s_color" value="fea501">
				</div>
				<div class="form-group">
					<label for="s_tag">연관 검색어</label>
					<input type="text" name="s_tag" id="s_tag" class="form-control" placeholder="#연관검색어">
				</div>
				<!-- 이미지 넣기 -->
				<div class="row">
	                <div class="form-group col-sm-2"> 
	                    <div class="img-picker"></div>
	                </div> 
	                <div class="form-group col-sm-2"> 
	                    <div class="img-picker"></div>
	                </div> 
	                <div class="form-group col-sm-2"> 
	                    <div class="img-picker"></div>
	                </div> 
	                <div class="form-group col-sm-2"> 
	                    <div class="img-picker"></div>
	                </div> 
	                <div class="form-group col-sm-2"> 
	                    <div class="img-picker"></div>
	                </div>
            	</div>
            	<div class="row">
	                <div class="form-group col-sm-2"> 
	                    <div class="img-picker"></div>
	                </div> 
	                <div class="form-group col-sm-2"> 
	                    <div class="img-picker"></div>
	                </div> 
	                <div class="form-group col-sm-2"> 
	                    <div class="img-picker"></div>
	                </div> 
	                <div class="form-group col-sm-2"> 
	                    <div class="img-picker"></div>
	                </div> 
	                <div class="form-group col-sm-2"> 
	                    <div class="img-picker"></div>
	                </div>
            	</div>
            	<div id="photo"></div>
				<div class="form-group">
					<label for="s_content">메모</label>
					<textarea rows="5" cols="40" name="s_content" class="form-control" style="resize:none; "></textarea>
				</div>
			</div>
			<!-- 버튼 -->
			<div class="col-md-12 hn" style="text-align: center;">
			<br>
				<input type="submit" value="등록" class="btn btn-default" >
			</div>
		</form:form>
	</div>
</div>