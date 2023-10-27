
//$(function(){}); 이거 안에 넣으니까 적용 안되는디?? 기능이 겹쳐서 그런듯 : DOMcontentLoaded 이것도 웹페이지 전부 로드되고 스크립트 처리하는기능임
	

document.addEventListener('DOMContentLoaded', function(){

	// 마스킹 처리
	const userIdElement = document.getElementById('userId'); // document 빠트리지말기
	const userNameElement = document.getElementById('userName'); 
	
	// 아이디 마스킹
	const originalUserId = userIdElement.innerText;
	const maskedUserId = originalUserId.substring(0, 3) + "****";
	userIdElement.innerText = maskedUserId;
	
	// 이름 마스킹
	const originalUserName = userNameElement.innerText;
	const maskedUserName = originalUserName.substring(0, 1) + "*" + originalUserName.substring(2);
	userNameElement.innerText = maskedUserName;
	
	// 생년월일 출력
	const userBirthElement = document.getElementById('userBirth');
	
	const userBirth = userBirthElement.innerText;
	console.log("userBirth : " + userBirth);
	
	const year = userBirth.substring(0, 4);
	console.log("year : " + year);
	
	const month = userBirth.substring(4, 6);
	console.log("month : " + month);
	
	const date = userBirth.substring(6);
	console.log("date : " + date);
	
	userBirthElement.innerText = year + "년 " + month + "월 " + date + "일";

	// 이메일 출력
	const emailInput1 = document.getElementById('userEmail1'); // 여긴 왜 const지?? / getElementsByName이라 배열로 나와서 Id로 찾는거임
	const emailInput2 = document.getElementById('userEmail2');

	const userEmail = emailInput1.value; // value 값 가져와지네? / = 을 기준으로 오른쪽 값을 왼쪽 값에 대입
	console.log("userEmail : " + userEmail); 
		
	const emailParts = userEmail.split('@');
	console.log("emailParts : " + emailParts); 
	
	emailInput1.value = emailParts[0]; // = 을 기준으로 오른쪽 값을 왼쪽 값에 대입
	emailInput2.value = emailParts[1];
	
	// 휴대폰 출력
	const hpInput1 = document.getElementById('userHp1');
	const hpInput2 = document.getElementById('userHp2');
	const hpInput3 = document.getElementById('userHp3');
	
	const userHp = hpInput1.value;
	console.log("userHp : " + userHp);
	 
	const hpParts = userHp.split('-');
	console.log("hpParts : " + hpParts);
	
	hpInput1.value = hpParts[0];
	hpInput2.value = hpParts[1];
	hpInput3.value = hpParts[2];
	
	
	
	// 이메일 수정
	const btnEmail = document.getElementById('btnChangeEmail');
	const emailDomainSelect = document.getElementById('emailDomain');
	
	btnEmail.addEventListener('click', function(e){
			
			
			// button이라 폼 전송 되므로 기본기능 막아주기
			e.preventDefault();
			
			//alert('here1');
			emailInput1.readOnly = false;		
			
			emailDomainSelect.addEventListener('change', function(){
			//alert('here2');	
			// 선택된 옵션값 가져오기 / emailDomainSelect의 option이 바뀔 때 그에 따라  선택된 옵션값을 가져와야지!
			const selectedOption = emailDomainSelect.options[emailDomainSelect.selectedIndex].value; // emailDomainSelect의 선택된 option의 value값
			console.log('selectedOption : ' + selectedOption);
			
			if(selectedOption === 'direct'){ // selectedOption 문자열이므로 direct 문자열로 비교해야함!!! 그냥 direct로 비교 XXX
				emailInput2.readOnly = false;	
				emailInput2.value = '';	
			}else{
				emailInput2.value = selectedOption;	
			}
		});	
		
	});
	
	// 휴대폰 수정
	const btnHp = document.getElementById('btnChangeHp');	
	
	btnHp.addEventListener('click', function(e){
		
		e.preventDefault();
		
		hpInput1.readOnly = false;
		hpInput2.readOnly = false;
		hpInput3.readOnly = false;
		
	});	
	
	// 수정하기 버튼 누를때 
	const btnInfoChange = document.getElementById('btnInfoChange');
	const formInfo = document.getElementById('formInfo');
	
	btnInfoChange.addEventListener('click', function(){
		
		// 이메일 수정
		const emailInput = document.getElementById('userEmail');
		emailInput.value = emailInput1.value + '@' + emailInput2.value;
		console.log('email : ' + emailInput.value);
		
		// 휴대폰 수정
		const hpInput = document.getElementById('userHp');
		hpInput.value = hpInput1.value + '-' + hpInput2.value + '-' + hpInput3.value;
		console.log('hp : ' + hpInput.value);
		
		// 자바스크립트로 formInfo.submit(); 전송할 필요없다, button  기본 기능이 폼전송이므로 
	});
});









