$(function(){

    // 판매자 정보 팝업 띄우기
    $('.latest .info .company > a').click(function(e){
        e.preventDefault();
        $('#popSeller').addClass('on');
    });

    // 문의하기 팝업 띄우기
    $('.btnQuestion').click(function(e){
        e.preventDefault();
        $('.popup').removeClass('on');
        $('#popQuestion').addClass('on');
    });

    // 주문상세 팝업 띄우기
    $('.latest .info .orderNo > a').click(function(e){
        e.preventDefault();
        $('#popOrder').addClass('on');
    });

    // 수취확인 팝업 띄우기
    $('.latest .confirm > .receive').click(function(e){
        e.preventDefault();
        var closestInput = $(this).closest('td').find('input');
        $('input[name=ordComNo]').remove();
        var inputDate = $('<input>',{type:'hidden',name:'ordComNo',value:closestInput.val()});
        console.log("log" + closestInput.val());
        $('#popReceive').addClass('on');
        
        $('#popReceive').append(inputDate);
    });
    $('.btnConfirm').click(function(e){
        e.preventDefault();
        var inputElement = $('input[name="ordComNo"]');
        
        $.ajax({
			url : '/LotteON/my/point/'+inputElement.val(),
			type : 'GET',
			dataType : 'json',
			success : function(result){
				if(result == 1){
					alert("수취확인 완료하였습니다.")
					location.reload("/LotteON/my/");
					
				}else{
					alert("수취확인 실패하였습니다.")
				}
					
			}
			
			
			
		})
        console.log("log" + inputElement.val());
        $('#popReceive').removeClass('on');
    });
    
    $('.btnReview').click(function(e){
		e.preventDefault();
		
		$('#reviewForm').submit();
    });
    

    // 상품평 작성 팝업 띄우기
    $('.latest .confirm > .review').click(function(e){
        e.preventDefault();
        start();
         var closestInput = $(this).closest('td').find('input[name=ordProdName]');
         var ordProdNo = $(this).closest('td').find('input[name=ordProdNo]');
         var ordCompleteNo = $(this).closest('td').find('input[name=ordCompleteNo]');
         $('.productNameReview').text(closestInput.val());
         $('.reviewProdNo').val(ordProdNo.val());
         $('.reviewOrdNo').val(ordCompleteNo.val());
         $('#popReview').addClass('on');
    });
               
    // 팝업 닫기
    $('.btnClose').click(function(){                
        $(this).closest('.popup').removeClass('on');                
    });
    $('.btnCancel').click(function(e){
		e.preventDefault();               
        $(this).closest('.popup').removeClass('on');                
    });

    // 상품평 작성 레이팅바 기능
    function start(){
		
		$(".my-rating").starRating({
        starSize: 20,
        useFullStars: true,
        strokeWidth: 0,
        useGradient: false,
        minRating: 1,
        ratedColors: ['#ffa400', '#ffa400', '#ffa400', '#ffa400', '#ffa400'],
        callback: function(currentRating, $el){
            $('.rating').val(currentRating);            
        }
    });
	}
    
});