$(function(){
		
	$(".arrow").click(function(){
		if($(".sm_tx").is(":hidden")){
			$(".sm_tx").slideDown();
			}
		else{
			$(".sm_tx").slideUp();
			}	
		})
		
	})

function add(){
	var quantity = $("#shuliang").val();
	quantity = parseInt(quantity) + 1;
	$("#shuliang").val(quantity);
}
function minus(){
	var quantity = $("#shuliang").val();
	quantity = parseInt(quantity) - 1;
	$("#shuliang").val(quantity);
	if(quantity < 0 ){
		alert("数量不能为负数！")
		$("#shuliang").val(0);
		}
}