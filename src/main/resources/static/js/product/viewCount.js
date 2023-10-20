 $(function(){
	let num = parseInt($("input[name='count']").val());
	let price1 = $('.productdisprice').val();
	
	let totalprice = 0;
	$('.increase').click(function(){
		num = num+1;
		$("input[name='count']").val(num);
		updatePrice();
		console.log("price1"+price1);
	});
	
	$('.decrease').click(function(){
		if(num > 1){
			num = num-1;
			$("input[name='count']").val(num);
			updatePrice();
				
		}
		console.log("num3"+num);
		console.log("price1"+price1);
	
	});
	
	$('.cart').click(function(e){
		e.preventDefault();
		
		
	    var result = confirm("장바구니에 담으시겠습니까?");
			if (result === true) {
			    // 확인 버튼이 클릭된 경우
				 $('.buy').submit();
			} else {
			    // 취소 버튼이 클릭된 경우
			}	
		  
	})
	$('.order').click(function(e){
		e.preventDefault();
		
		var userSession = sessionStorage.getItem("memberdto");
	    var result = confirm("구매하시겠습니까?");
			if (result === true) {
				var input = $("<input>")
			    .attr("type", "hidden")
			    .attr("name", "buytype")
			    .val("buytype");
				$('.order').append(input);

			    // 확인 버튼이 클릭된 경우
				 $('.buy').attr('action', 'productOrder');
				 $('.buy').submit();
			} else {
			    // 취소 버튼이 클릭된 경우
			}	
		  
	})
	
	function updatePrice(){
		totalprice =  price1 * num;
		$('.totalPrice').text(totalprice.toLocaleString());
		$('.tot').val(totalprice);
		console.log("totprice"+totalprice);
	}
	
	
});