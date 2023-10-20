$(function(){
			$('.agree').click(function(){
				
				const chk1 = $('input[name=agree1]').is(':checked');
				const chk2 = $('input[name=agree2]').is(':checked');
				const chk3 = $('input[name=agree3]').is(':checked');
				const chk4 = $('input[name=agree4]').is(':checked');
				
				if(!chk1){
					alert('사이트 이용약관에 동의체크 하시기 바랍니다.');
					return false;
				}
				
				if(!chk2){
					alert('전자금융거래 이용약관에 동의체크 하시기 바랍니다.');
					return false;
				}
				
				if(!chk3){
					alert('개인정보 취급방침 약관에 동의체크 하시기 바랍니다.');
					return false; // .agree click 할 때 false이니까 submit 실행 안됨
				}
				
				return true; // .agree click 할 때 true이면 submit 기능이 실행된다
			});
		});