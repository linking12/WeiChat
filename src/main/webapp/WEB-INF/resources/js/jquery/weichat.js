// iframe弹出层
function tanchuceng(width, height, tit, url) {
	var winWinth = $(window).width(), winHeight = $(document).height();
	$("body").append("<div class='winbj'></div>");
	$("body")
			.append(
					"<div class='tanChu'><div class='tanChutit'><span class='tanchuTxt'>"
							+ tit
							+ "</span><span class='tanchuClose'>关闭</span></div><iframe class='winIframe' frameborder='0' hspace='0' src="
							+ url + "></iframe></div>");
	$(".winbj").css({
		width : winWinth,
		height : winHeight,
		background : "#000",
		position : "absolute",
		left : "0",
		top : "0"
	});
	$(".winbj").fadeTo(0, 0.5);
	var tanchuLeft = $(window).width() / 2 - width / 2;
	var tanchuTop = $(window).height() / 2 - height / 2 +5;
	$(".tanChu").css({
		width : width,
		height : height,
		border : "3px #ccc solid",
		left : tanchuLeft,
		top : tanchuTop,
		background : "#fff",
		position : "absolute"
	});
	$(".tanChutit").css({
		width : width,
		height : "25px",
		"border-bottom" : "1px #ccc solid",
		background : "#eee",
		"line-height" : "25px"
	});
	$(".tanchuTxt").css({
		"text-indent" : "5px",
		"float" : "left",
		"font-size" : "14px"
	});
	$(".tanchuClose").css({
		"width" : "40px",
		"float" : "right",
		"font-size" : "12px",
		"color" : "#666",
		"cursor" : "pointer"
	});
	var winIframeHeight = height - 26;
	$(".winIframe").css({
		width : width,
		height : winIframeHeight,
		"border-bottom" : "1px #ccc solid",
		background : "#ffffff"
	});
	$(".tanchuClose").hover(function() {
		$(this).css("color", "#333");
	}, function() {
		$(this).css("color", "#666");
	});
	$(".tanchuClose").click(function() {
		$(".winbj").remove();
		$(".tanChu").remove();
	});
}

/*
 * jQuery HoverPulse Plugin by M. Alsup
 * Examples and docs at: http://malsup.com/jquery/hoverpulse/
 * Dual licensed under the MIT and GPL
 * Requires: jQuery v1.2.6 or later
 * @version: 1.01  26-FEB-2009
 */

(function($) {

$.fn.hoverpulse = function(options) {
    // in 1.3+ we can fix mistakes with the ready state
    if (this.length == 0) {
        if (!$.isReady && this.selector) {
            var s = this.selector, c = this.context;
            $(function() {
                $(s,c).hoverpulse(options);
            });
        }
        return this;
    }    
    
	var opts = $.extend({}, $.fn.hoverpulse.defaults, options);

	// parent must be relatively positioned
	this.parent().css({ position: 'relative' });
	// pulsing element must be absolutely positioned
	this.css({ position: 'absolute', top: 0, left: 0 });

	this.each(function() {
		var $this = $(this);
		var w = $this.width(), h = $this.height();
		$this.data('hoverpulse.size', { w: parseInt(w), h: parseInt(h) });
	});

	// bind hover event for behavior
	return this.hover(
		// hover over
		function() {
			var $this = $(this);
			$this.parent().css('z-index', opts.zIndexActive);
			
			var size = $this.data('hoverpulse.size');
			var w = size.w, h = size.h;
			$this.stop().animate({ 
				top:  ('-'+opts.size+'px'), 
				left: ('-'+opts.size+'px'), 
				height: (h+15*opts.size)+'px', 
				width:	(w+15*opts.size)+'px' 
			}, opts.speed);
		},
		// hover out
		function() {
			var $this = $(this);
			var size = $this.data('hoverpulse.size');
			var w = size.w, h = size.h;
			
			$this.stop().animate({ 
				top:  0, 
				left: 0, 
				height: (h+'px'), 
				width:	(w+'px') 
			}, opts.speed, function() {
				$this.parent().css('z-index', opts.zIndexNormal);
			});
		}
	);
};

$.fn.hoverpulse.defaults = {
	size:  20,
	speed: 200,
	zIndexActive: 100,
	zIndexNormal: 1
};

})(jQuery);