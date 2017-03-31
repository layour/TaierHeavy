summerready = function() {
	$summer.fixStatusBar($summer.byId('header'));
	bindEvents()
}
function bindEvents() {
	var mySwiper = new Swiper('.swiper-container', {
		loop : false,
		//onlyExternal : true,
		//touchAngle : 10,
		onInit : function(swiper) {
			var H = $(".um-content").height();
			$(".swiper-slide").css('height', H + 'px');
			$(".swiper-wrapper").css('height', H + 'px');
		},
		onSlideChangeEnd : function(swiper) {
			$('.team li').eq(swiper.activeIndex).addClass("active").siblings().removeClass("active");
		}
	})
	$('.team li').on('click', function(e) {
		$(this).addClass("active").siblings().removeClass("active");
		mySwiper.swipeTo($(e.currentTarget).index(), 300, false);
	});
	$('.swiper-slide li').on('click', function() {
		summer.openWin({
			"id" : 'view',
			"url" : 'html/view.html'
		});
	});
	/*var iScroller = new iScroll("list", {
		onRefresh : function() {		
		},
		onScrollMove : function() {
		},
		onScrollEnd : function() {
			$('.list1').append(
				'<li>'+
				'<div class="um-label um-box-justify">'+
				'<div>qqq</div>'+
				'<div>'+
				'<span class="um-green mr15">已通过</span><i class="ti-angle-right"></i>'+
				'</div>'+
				'</div>'+
				'</li>')}	
	})*/
}
