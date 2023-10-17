$(document).ready(function(){
    var gnb = $('#gnb > li > a');

    gnb.click(function(e){
        e.preventDefault();
        
        var isOpen = $(this).next().is(':visible');

        if(isOpen){
            // 현재 서브메뉴를 닫음
            $(this).next().slideUp(200);
        }else{
            // 현재 서브메뉴를 펼침
            $(this).next().slideDown(200);
        }
    });
    
    $('input[name=all]').change(function(){
		const isChecked = $(this).is(':checked');
		
		if(isChecked){
			// 전체선택
			$('input[name=chk]').prop('checked', true);
		}else{
			// 전체해제
			$('input[name=chk]').prop('checked', false);
		}
		
	});
	
	$('.productDelete').click(function() {
        var checkBoxArr = [];
        
        // 체크된 체크박스를 순회하면서 데이터 추출
        $('input[name=chk]:checked').each(function() {
            var $row = $(this).closest('tr'); // 현재 체크박스가 속한 행

            var sellerValue = $row.find('td:eq(8)').text(); // 8번째 열의 데이터 (판매자)
            var prodNoValue = $row.find('td:eq(3)').text(); // 3번째 열의 데이터 (상품코드)

            checkBoxArr.push(sellerValue, prodNoValue);
        });

        console.log(checkBoxArr);
		
        var confirmDelete = confirm("선택한 상품을 삭제하시겠습니까?");
        
	    $.ajax({
	        type: "DElETE",
	        url: "/admin/layout/product/productDelete",
	        traditional: true,
	        data: {
	            checkBoxArr: checkBoxArr
	        },
	        success: function(result) {
	            console.log(result);
	        },
	        error: function(xhr, status, error) {
	            alert(error);
	        }
	    });
	});
	
	$('.btnSearch').click(function() {

		var seller = $('td:eq(8)').text();
		
	    
	    var search = $('input[name=search]').val();
	    // 선택한 검색 카테고리 값을 가져옴
	    var searchCategory = $('#searchCategory').val();
	    	
	});
	
	$(".dtnProductDelete").click(function (e) {
		e.preventDefault();
		$('.delete').submit();
	})
    
});