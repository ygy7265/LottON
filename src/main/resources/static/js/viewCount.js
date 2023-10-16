$(function(){
	let num = parseInt($("input[name='num']").val());
	let price1 = "[[${dto.price - ((dto.price * dto.discount)/100)}]]";
	
	let totalprice = 0;
	$('.increase').click(function(){
		num = num+1;
		$("input[name='num']").val(num);
		updatePrice();
		console.log("num"+num);
	});
	
	$('.decrease').click(function(){
		if(num > 1){
			num = num-1;
			$("input[name='num']").val(num);
			updatePrice();
				
		}
		console.log("num3"+num);
		console.log("price1"+price1);
	
	});
	
	function updatePrice(){
		totalprice =  price1 * num;
		$('.totalPrice').text(totalprice.toLocaleString());
		$('.tot').val(totalprice);
		console.log("totprice"+totalprice);
	}
});
