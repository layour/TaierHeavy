// JavaScript Document
//页面入口
summerready = function(){
	imgPath();
	handleEvents();
}
function closeWin(){
	summer.closeWin();
}
function hideTitle(){
	$("#header").animate({top:-44},200);
}
/**获取图片路径
  *path 图片路径
*/
function imgPath(){
	var path = summer.pageParam.param.path;
	console.log(path);
	var img = new Image();
	img.src = path;
	$("img").attr("src",path);
	img.onload = function(){
		$("div.picShow").css({
			'display':'block'
		});
	}
}
/**增加图片的事件
  *pinch手指缩放
  *touch位移
*/
function handleEvents(){
	$("#main").on("tap",function(){
		$("#header").animate({top:0},200);
	});
	$("#header").on("tap",function(e){
		if(e.target==$(".um-back").get(0)){
			return false;
		}
		hideTitle();
	});
	var initialScale = 1;
	var touchFlag = false;
	var pinchFlag = false;
	var X0 = 0,Y0 = 0;
	var X = 0,Y = 0;
	var clientX = 0,clientY = 0;
	var W = 0,H = 0;
	$("div.picShow img").on("pinchstart",function(e){
		e.preventDefault();
		pinchFlag = true;
		$("div.picShow img").css({
			'transition':'all 0.05s linear 0s',
			'-webkit-transition':'all 0.05s linear 0s'
		});
	}).on("pinch",function(e){//缩放
		e.stopPropagation();
		if( touchFlag ){
			return false;
		}
		var scale = e.originalEvent.detail.scale;
		var curScale = initialScale*scale;
		if( curScale < 0.2 ){
			curScale = 0.2;
		}
		$("div.picShow img").css({
			'transform':'translate(0,0) scale(' + curScale + ',' + curScale + ')',
			'-webkit-transform':'translate(0,0) scale(' + curScale + ',' + curScale + ')'
		});
		$("div.picShow img").css({
			'transition':'all 0s linear 0s',
			'-webkit-transition':'all 0s linear 0s'
		});
	}).on("pinchend",function(e){
		e.stopPropagation();
		var scale = e.originalEvent.detail.scale;
		initialScale *= scale;
		if( initialScale<1 ){
			initialScale = 1;
			modifyAnimateTime();
		}else if( initialScale>2 ){
			initialScale = 2;
			modifyAnimateTime();
		}
		$("div.picShow img").css({
			'transform':'translate(0,0) scale(' + initialScale + ',' + initialScale + ')',
			'-webkit-transform':'translate(0,0) scale(' + initialScale + ',' + initialScale + ')'
		});
	});
	$("div.picShow").on("touchstart",function(e){
		e.preventDefault();
		pinchFlag = false;
		var touch = e.originalEvent.changedTouches[0];
		clientX = touch.clientX;
		clientY = touch.clientY;
		W = $("div.picShow").width();
		H = $("div.picShow").height();
	}).on("touchmove",function(e){
		if( touchFlag ){
			return false;
		}
		var touch = e.originalEvent.changedTouches[0];
		X = touch.clientX - clientX + X0;
		Y = touch.clientY - clientY + Y0;
		if( pinchFlag ){
			X = 0;
			Y = 0;
		}
		$("div.picShow img").css({
			'transform':'translate(' + X + 'px,' + Y + 'px) scale(' + initialScale + ',' + initialScale + ')',
			'-webkit-transform':'translate(' + X + 'px,' + Y + 'px) scale(' + initialScale + ',' + initialScale + ')'
		});
	}).on("touchend",function(e){
		var touch = e.originalEvent.changedTouches[0];
		X0 += touch.clientX - clientX;
		Y0 += touch.clientY - clientY;
		if( pinchFlag ){
			X0 = 0;
			Y0 = 0;
		}
		var X1 = W*(initialScale - 1)/2;
		var X2 = -W*(initialScale - 1)/2;
		var Height = $summer.winHeight();
		Y1 = H*initialScale/2 - Height/2;
		Y2 = -(H*initialScale/2 - Height/2);
		if( X0 > X1 ){
			X0 = X1;
			modifyAnimateTime();
		}else if( X0 < X2 ){
			X0 = X2;
			modifyAnimateTime();
		}
		if( H*initialScale < Height ){
			if( Y0 < Y1 ){
				Y0 = Y1;
				modifyAnimateTime();
			}else if( Y0 > Y2 ){
				Y0 = Y2;
				modifyAnimateTime();
			}
		}else if( H*initialScale >= Height ){
			if( Y0 > Y1 ){
				Y0 = Y1;
				modifyAnimateTime();
			}else if( Y0 < Y2 ){
				Y0 = Y2;
				modifyAnimateTime();
			}
		}
		$("div.picShow img").css({
			'transform':'translate(' + X0 + 'px,' + Y0 + 'px) scale(' + initialScale + ',' + initialScale + ')',
			'-webkit-transform':'translate(' + X0 + 'px,' + Y0 + 'px) scale(' + initialScale + ',' + initialScale + ')'
		});
	});
	
	function modifyAnimateTime(){
		touchFlag = true;
		$("div.picShow img").css({
			'transition':'all 0.1s ease-in 0s',
			'-webkit-transition':'all 0.1s ease-in 0s'
		});
		setTimeout(function(){
			touchFlag = false;
			$("div.picShow img").css({
				'transition':'all 0s linear 0s',
				'-webkit-transition':'all 0s linear 0s'
			});
		},100);
	}
}