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
	var quantity = $("#counts").val();
	quantity = parseInt(quantity) + 1;
	$("#counts").val(quantity);
}
function minus(){
	var quantity = $("#counts").val();
	quantity = parseInt(quantity) - 1;
	$("#counts").val(quantity);
	if(quantity < 1 ){		
		$("#counts").val(1);
		}
}