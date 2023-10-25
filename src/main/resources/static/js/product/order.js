$(function(){
			//포인트 적용
	$('#pointbtn').click(function(e){
		e.preventDefault();		
		var totalprice = parseFloat($('.ordertotal').text().replace(/,/g, ''));
		var nowpoint = $('.userpoint').val();
		var total = $('.totalprice').val();
		console.log("nowpoint" + nowpoint);
		console.log("total" + total);
		var point = $('.point2').val();
		console.log("point" + point);
		var pspoint = parseFloat(point);
		console.log("pspoint" + pspoint);
		console.log("total" + pspoint);
		if(pspoint > totalprice){
			alert("전체 금액 이상 할인할수없습니다. 다시 확인 해주십시요.");
			return;
		}
		else if(pspoint > nowpoint){
			alert("포인트가 부족합니다. 다시 확인 부탁드립니다.");
			return;
		}
		if(pspoint < 5000 && pspoint > 0){
			alert("5000점 이상부터 사용할수있습니다.");
			return;
		}
		else{
			$('.nowpoint').text((nowpoint - pspoint).toLocaleString());
			$('.ordertotal').text((total - pspoint).toLocaleString());
	
			if(pspoint != 0){
				$('.orduserdPoint').text(" - "+pspoint);	
				$('input[name=ordusedPoint]').val(pspoint);
			}
			else{
				$('.orderpointdiscount').text(0);
				$('input[name=ordusedPoint]').val(0);
			}
			
		}
		
	});
	
	//BuyButton
	$('.buybtn').click(function(e){
		e.preventDefault();
		 // 사용자에게 구매 여부를 묻는 확인 대화상자를 띄웁니다.
	    
		//결제방법 체크여부
        var selectedPayment = $('input[name="ordPayment"]:checked').val();

        // 선택된 항목이 없는 경우 경고 메시지를 표시합니다.
        if (!selectedPayment) {
            alert('결제 옵션을 선택해주세요.');
            return;
        } 
        else{
        	var confirmation = confirm("구매하시겠습니까?");
        	if (confirmation) { 
    	        $('#buyform').submit();
    	    }    	
        }

	    });
	
});