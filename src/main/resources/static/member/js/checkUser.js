/**
 * 사용자 개인정보 중복체크
 * 
 * 이름은 중복될 수 있기 때문에 중복체크 안 하고 validation에서 유효성 검사만 한다.
 */
$(function(){
	
	// 아이디 중복체크
	$('input[name=uid]').focusout(function(){
		
		//alert('focusout!');
		
		const uid = $('input[name=uid]').val();
		
		if(!uid.match(reUid)){
			$('.resultId').css('color', 'red').text('유효한 아이디가 아닙니다.');
			isUidOk = false;
			return; // 종료
		}
		
		/* 여기서는 jsonData 만들어서 AJAX 쏠 필요가 없다! uid와 reUid가 match 하면    			
		const jsonData = {
			"uid": uid
		};
		*/
		
		$.ajax({
			url:'/LotteON/member/check/uid/'+uid, // 위에서 만든 uid를 이렇게 참조해서 파라미터로 보내는구나, /LotteON 안 붙이면 적용 안됨 
			type:'GET',
			// data: jsonData, // url에 파라미터 대신 jsonData의 key,value로 uid 설정해서 해당주소로 보내는 건가?? ㅇㅇ맞음 / 그걸 UserCheckController에서 파라미터로 수신하고??
			dataType:'json',
			success:function(data){
				console.log("data : " + data);
				if(data >= 1){
					$('.resultId').css('color', 'red').text('이미 사용중인 아이디 입니다.');
					isUidOk = false;
				}else{
					$('.resultId').css('color', 'green').text('사용 가능한 아이디 입니다.');
					isUidOk = true;
				}
			}
		});
		
	}); // 아이디 중복체크 끝
	
	/*// 닉네임 중복체크
	$('input[name=nick]').focusout(function(){
		
		// 입력 데이터 가져오기
		const nick = $(this).val();
		
		if(!nick.match(reNick)){
			$('.resultNick').css('color', 'red').text('유효한 닉네임이 아닙니다.');
			isNickOk = false;
			return;
		}
		
		// JSON 생성
		const jsonData = {
			"nick": nick 
		};
		
		// 데이터 전송
		$.get('/Farmstory2/user/checkNick.do', jsonData, function(data){
			if(data.result >= 1){
				$('.resultNick').css('color', 'red').text('이미 사용중인 별명 입니다.');
				isNickOk = false;
			}else{
				$('.resultNick').css('color', 'green').text('사용 가능한 별명 입니다.');
				isNickOk = true;
			}
		});
		
	});// 닉네임 중복체크 끝
	*/
	
	// 이메일 중복체크 / 이메일이랑 휴대폰은 여기서 유효성 검사까지 같이 하네
	document.getElementsByName('email')[0].onfocusout = function(){ // onfocusout 으로 표현하네
		
		//alert('focusout!');
		
		const resultEmail = document.getElementById('resultEmail');
		
		// 입력 데이터 가져오기
		const email = this.value;
		
		if(!email.match(reEmail)){
			resultEmail.innerText = '유효한 이메일이 아닙니다.';
			resultEmail.style.color = 'red';
			isEmailOk = false;
			return;
		}

		// 데이터 전송
		const xhr = new XMLHttpRequest();
		xhr.open('GET', '/LotteON/member/check/email/'+email);
		xhr.send();
		
		// 응답 결과 (JSON 데이터 수신)
		xhr.onreadystatechange = function(){    				
			if(xhr.readyState == XMLHttpRequest.DONE){						
				if(xhr.status == 200){
					const data = JSON.parse(xhr.response);
					console.log('data : ' + data);
					
					if(data >= 1){
						resultEmail.innerText = '이미 사용중인 이메일 입니다.';
						resultEmail.style.color = 'red';
						isEmailOk = false;
					}else{
						resultEmail.innerText = '사용 가능한 이메일 입니다.';
						resultEmail.style.color = 'green';
						isEmailOk = true;
					}
				}
			}    				
		}// onreadystatechange end
	} // 이메일 중복체크 끝
	
	// 휴대폰 중복체크
	document.getElementsByName('hp')[0].addEventListener('focusout', function(){
	
		//alert('focusout!');
		
		const resultHp = document.getElementById('resultHp');
		const hp = this.value; // this는 getElementsByName('hp')[0] 을 가리킴
		
		if(!hp.match(reHp)){
			resultHp.innerText = '유효한 휴대폰번호가 아닙니다.';
			resultHp.style.color = 'red';
			isHpOk = false;
			return;
		}
		
		const url = '/LotteON/member/check/hp/'+this.value; // this.value는 hp랑 같은 값이라 hp로 표현해도 되고 this.value로 표현해도 되고.
		
		fetch(url)
			.then(response => response.json())
			.then(data => {
				console.log(data);
				
				if(data >= 1){
					resultHp.innerText = '이미 사용중인 휴대폰번호 입니다.';
					resultHp.style.color = 'red';
					isHpOk = false;
				}else{
					resultHp.innerText = '사용 가능한 휴대폰번호 입니다.';
					resultHp.style.color = 'green';
					isHpOk = true;
				}
			});
		
	}); // 휴대폰 중복체크 끝
	
}); // 사용자 개인정보 중복체크 끝