
		$(function(){
			
			let cartNo = null;
		    let count = 0; // 상품 수 초기화
		    let point = 0; // 포인트 초기화
		    let total = 0; // total 변수를 초기화합니다.
		    let delivery = 0; // 배송비 초기화
		    let discountPrice = 0; // 전체비용 초기화
			let orderdiscount = $('input.ordercount').val();
		    var listValues = [];
		    initialize();
	    	
	    	function initialize() {
	        $('.ordernodiscount').text("0");
	        $('.orderdiscount').text("0");
	        $('.ordercount').text("0");
	        $('.orderpoint').text("0");
	        $('.orderdelivery').text("0");
	        $('.ordertotal').text("0");
	        $('.ordertotal2').val("0");
	    	}
	    	
			 $('#all').click(function() {
	        // 전체 선택 체크박스의 상태
	        var isChecked = $(this).prop('checked');
   			
	  	 	 $('.Item').prop('checked', isChecked);
	
	   		
	        // 개별 체크박스들의 상태를 전체 선택 체크박스와 동일하게 설정
	  	   
		        if($('.Item:checked').length === 0){
		        	 initialize();
		        }
		        else{
		        	calculateSelectedItems(); // 업데이트된 선택 항목 계산	
		        }
		        
		       
	 	   });
	    
	     
		   $('.Item').click(function() {
		        // 개별 체크박스들 중에서 하나라도 선택 해제된 것이 있는지 확인
		        cartNo = $(this).val();
		        console.log("cartNo = " + cartNo);
		        if ($('.Item:checked').length < $('.Item').length) {
		            // 하나라도 선택 해제된 경우, 전체 선택 체크박스도 선택 해제
		            $('#all').prop('checked', false);
		        } else {
		            // 모두 선택된 경우, 전체 선택 체크박스도 선택
		            $('#all').prop('checked', true);
		        }
		        
		        if($('.Item:checked').length === 0){
		        	 initialize();
		        }
		        else{
		        	calculateSelectedItems(); // 업데이트된 선택 항목 계산	
		        }
	        
	   		 });
		
			    //할인금액계산
	    function calculateSelectedItems() {
	        var selectedItems = $('.Item:checked'); // 선택된 체크박스 아이템들을 가져옵니다.
	        count = selectedItems.length; // 선택된 상품 수 업데이트
	        total = 0; // 총 상품금액 초기화
	        var discount = 0; // 할인 총액 초기화
	        var selectedCheckbox = $(this); // 선택된 체크박스
	        var listCountValue = null;
	      
	        var listdeliveryValue = null;
	        var listpriceValue = null;
	        var listLastPriceValue = null;
	        var listNoDiscountPriceValue = null;
	        var listdiscountedPrice = null;
	        var listpointValue = null;
	        var listdiscountper = null;
	        
	        var listValues = [];
	        selectedItems.each(function() {
	            var row = $(this).closest('tr'); // 선택된 체크박스가 속한 행
	            var listCount = parseFloat(row.find('.count').val());  // 선택된 체크박스의 .listcount 값
	            var listpriceValue = parseFloat(row.find('.price').val());  // 선택된 체크박스의 .listprice 값
	            var listdiscountValue = parseFloat(row.find('.discount').val());  // 선택된 체크박스의 .listcount 값
	            var listProdNo = parseFloat(row.find('.prodNo').val());  // 선택된 체크박스의 .listprodNo 값
	            var listUid = $('.listuid').val(); // 선택된 체크박스의 .listcount 값을
	            var listdelivery = parseFloat(row.find('.delivery').val());  // 선택된 체크박스의 .listdelivery 값
	            var listprodName = row.find('.prodName').val();  // 선택된 체크박스의 .listpName 값
	            var listtotal = parseFloat(row.find('.total').val());  // 선택된 체크박스의 .listtotal 값
	            var listpoint = parseFloat(row.find('.point').val());  // 선택된 체크박스의 .listpoint 값
	            
	            //데이터 값 저장
	            listdeliveryValue += parseFloat(row.find('.delivery').val());  // 선택된 체크박스의 .listdelivery 값을 더합니다.
	            listpointValue += parseFloat(row.find('.point').val());  // 선택된 체크박스의 .listpoint 값을 더합니다.
	        	listCountValue += parseFloat(row.find('.count').val());  // 선택된 체크박스의 .listcount 값을 더합니다.
	        	listLastPriceValue = listCount * listpriceValue;
	        	listNoDiscountPriceValue += listLastPriceValue;
	
	            // 할인 적용된 가격 계산
	            var discountedPrice = listLastPriceValue - (listLastPriceValue * (listdiscountValue / 100));
	            var discountper = (listLastPriceValue * (listdiscountValue / 100));
	            //할인후 적용 금액
	            listdiscountedPrice += discountedPrice;
	            //할인금액
	            listdiscountper += discountper;
	            console.log('discountedPrice value: ' + Math.round(discountedPrice).toLocaleString());
	            
	            console.log('discountedPrice value: ' + Math.round(listdiscountedPrice).toLocaleString());
	            
	            
	            
	            console.log("listProdNo = " + listProdNo);
	            console.log("listUid = " + listUid);
	            console.log("listCount = " + listCount);
	            console.log("listpriceValue = " + listpriceValue);
	            console.log("listProdNo = " + listProdNo);
	            console.log("listtotal = " + listtotal);
	            console.log("listdelivery = " + listdelivery);
	            console.log("listpoint = " + listpoint);
	            console.log("cartNo = " + cartNo);
	            
	            
	            listValues.push({
	                count: listCount,
	                price: listpriceValue,
	                discount: listdiscountValue,
	                prodNo: listProdNo,
	                uid: "seller1",
	                delivery: listdelivery,
	                prodName: listprodName,
	                total: listtotal,
	                point: listpoint,
	               
	            });
	          
	            
	            $('.jsondata').val(JSON.stringify(listValues));
	            console.log("$('.jsondata') = " + $('.jsondata').val());
	            
	           
	        });
	     
	        // 업데이트된 값들을 화면에 표시
	        $('.ordernodiscount').text(listNoDiscountPriceValue.toLocaleString());
	        
	        $('.orderdiscount').text('- ' + Math.round(listdiscountper).toLocaleString());
	        // 업데이트된 상품 수량을 화면에 표시
	        $('.ordercount').text(listCountValue.toLocaleString());
	        $('.orderpoint').text(listpointValue.toLocaleString());
	        $('.orderdelivery').text(listdeliveryValue.toLocaleString());
	        $('.ordertotal').text(Math.round(listdiscountedPrice).toLocaleString());
	        $('.ordertotal2').val(Math.round(listdiscountedPrice).toLocaleString());
	    }
		function initialize() {
	        $('.ordernodiscount').text("0");
	        $('.orderdiscount').text("0");
	        $('.ordercount').text("0");
	        $('.orderpoint').text("0");
	        $('.orderdelivery').text("0");
	        $('.ordertotal').text("0");
	        $('.ordertotal2').val("0");
	    	}
	
	 $('#deleteSelected').click(function() {
  	 	 var selectedItems = $('.Item:checked'); // 선택된 체크박스 아이템들
  	 	 console.log("selectedItems"+selectedItems);
         var cartNos = selectedItems.map(function() {
            return $(this).val(); // 선택된 아이템의 data-cartno 속성 값
         }).get();
          $('#all').prop('checked', false);
	  	  console.log("cartNos"+cartNos);
	      $('.Item:checked').closest('tr').remove();
	      
		      	$.ajax({
						url:'/LotteON/product/'+cartNos,
						type:'DELETE',
						traditional : true,
						contentType: "application/x-www-form-urlencoded; charset=UTF-8",
						dataType:'json',
						success:function(data){
							console.log(data);
							if(data.result > 0){}
								
						}
					
				});
		 
		   if($('.Item').length === 0){
				$('.empty').append('<td colspan="7">장바구니에 상품이 없습니다.</td>');  
		  }
		
		initialize();		  
    });
	
			
		});