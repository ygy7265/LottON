$(document).ready(function(){

	$('.csDelete').click(function() {
        var checkBoxArr = [];
        
        // 체크된 체크박스를 순회하면서 데이터 추출
        $('input[name=chk]:checked').each(function() {
            var $row = $(this).closest('tr'); // 현재 체크박스가 속한 행
            
            var noValue = $row.find('td:eq(1)').text();

            checkBoxArr.push(noValue);
        });

        console.log(checkBoxArr);
		
        var confirmDelete = confirm("선택한 상품을 삭제하시겠습니까?");
        
	    $.ajax({
	        type: "DELETE",
	        url: "/admin/layout/cs/selectCsDelete",
	        traditional: true,
	        data: JSON.stringify(checkBoxArr), // 배열을 JSON 형식으로 변환
	        contentType: "application/json", // 데이터 형식을 JSON으로 지정
	        success: function(result) {
	            console.log(result);
	            alert("삭제 되었습니다")
	        },
	        error: function(xhr, status, error) {
	            alert("삭제 중 오류가 발생했습니다");
	        }
	    });
	});
	
	 $('.noticeDelete').click(function (e) {
	    e.preventDefault();
	    var $link = $(this);
	    var no = $link.closest('tr').find('td:eq(1)').text();
	
	    if (confirm('상품을 삭제하시겠습니까?')) {
			
	        $.ajax({
	            url: '/admin/layout/cs/noticeDelete/' + no,
	            type: 'DELETE',
	            dataType: 'json',
	            success: function (data) {
	                console.log(data);
	            },
	            error: function (xhr, status, error) {
	                console.error(xhr.responseText);
	            }
	        });
	    }
    });
    
	 $('.faqDelete').click(function (e) {
	    e.preventDefault();
	    var $link = $(this);
	    var no = $link.closest('tr').find('td:eq(1)').text();
	
	    if (confirm('상품을 삭제하시겠습니까?')) {
			
	        $.ajax({
	            url: '/admin/layout/cs/faqDelete/' + no,
	            type: 'DELETE',
	            dataType: 'json',
	            success: function (data) {
	                console.log(data);
	            },
	            error: function (xhr, status, error) {
	                console.error(xhr.responseText);
	            }
	        });
	    }
    });
    
	 $('.qnaDelete').click(function (e) {
	    e.preventDefault();
	    var $link = $(this);
	    var no = $link.closest('tr').find('td:eq(1)').text();
	
	    if (confirm('상품을 삭제하시겠습니까?')) {
			
	        $.ajax({
	            url: '/admin/layout/cs/qnaDelete/' + no,
	            type: 'DELETE',
	            dataType: 'json',
	            success: function (data) {
	                console.log(data);
	            },
	            error: function (xhr, status, error) {
	                console.error(xhr.responseText);
	            }
	        });
	    }
    });
	
	$('.noticeWrite').click(function(){
	   window.location.href = "/admin/layout/cs/noticeWrite"; 
	});
	
	$('.faqWrite').click(function(){
	   window.location.href = "/admin/layout/cs/faqWrite"; 
	});
	
	$('.qnaWrite').click(function(){
	   window.location.href = "/admin/layout/cs/qnaWrite"; 
	});
	
});