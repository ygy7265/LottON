$(document).ready(function(){
	
	$('.productDelete').click(function() {
        var checkBoxArr = [];
        
        // 체크된 체크박스를 순회하면서 데이터 추출
        $('input[name=chk]:checked').each(function() {
            var $row = $(this).closest('tr'); // 현재 체크박스가 속한 행
            
            var prodNoValue = $row.find('td:eq(2)').text(); // 3번째 열의 데이터 (상품코드)

            checkBoxArr.push(prodNoValue);
        });

        console.log(checkBoxArr);
		
        var confirmDelete = confirm("선택한 상품을 삭제하시겠습니까?");
        
	    $.ajax({
	        type: "DELETE",
	        url: "/admin/layout/product/selectProductDelete",
	        traditional: true,
	        data: JSON.stringify(checkBoxArr), // 배열을 JSON 형식으로 변환
	        contentType: "application/json", // 데이터 형식을 JSON으로 지정
	        success: function(result) {
	            console.log(result);
	        },
	        error: function(xhr, status, error) {
	            alert(error);
	        }
	    });
	});
	
	
	 $('.delete-product-link').click(function (e) {
        e.preventDefault();
        var $link = $(this); // 클릭한 링크를 선택합니다.
        var productNo = $link.closest('tr').find('td:eq(2)').text(); // 상품 번호를 가져옵니다.

        if (confirm('상품을 삭제하시겠습니까?')) {
            // 여기에서 AJAX DELETE 요청을 보내고 productNo를 전송합니다.
            $.ajax({
                url: '/admin/layout/product/productDelete/' + productNo,
                type: 'DELETE',
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                    // 성공했을 때 수행할 작업을 여기에 추가하세요.
                },
                error: function (xhr, status, error) {
                    console.error(xhr.responseText);
                }
            });
        }
    });
    
});