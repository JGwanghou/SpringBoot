$(function(){

		$('.next').click(() => {
			if($('#check_1').is(':checked') == false){
				alert('동의 체크 후 회원가입이 가능합니다.');
				return false;
			}else if($('#check_2').is(':checked') == false){
				alert('동의 체크 후 회원가입이 가능합니다.');
				return false;
			}
		})
})
		//데이터 검증에 사용하는 정규표현식
		const reUid   = /^[a-z]+[a-z0-9]{5,19}$/g;
		const rePass  = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{5,16}$/; // 비밀번호 정규 표현식
		const reName  = /^[가-힣]+$/
		const reNick  = /^[a-zA-Zㄱ-힣0-9][a-zA-Zㄱ-힣0-9]*$/;
		let reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		let reHp    = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;

		// 폼 데이터 검증 결과 상태변수
		let isUidOk   = false;
		let isPassOk  = false;
		let isNameOk  = false;
		let isNickOk  = false;
		let isEmailOk = false;
		let isHpOk 	  = false;

		window.onload = function(){

			const btnCheckUid = document.getElementById('btnCheckUid');

			/*====== 아이디 중복체크 및 정규표현식 검사 ======== */
			btnCheckUid.addEventListener('click', ()=>{

				 // ID 정규표현식
				const uid = document.querySelector('input[name=uid]').value; // 입력한 아이디 정보

				// 만약 입력한 아이디정보가 정규표현식과 맞지않다!
				if(!reUid.test(uid)){

					const resultUid = document.querySelector('.resultUid');
					resultUid.innerText = '아이디 형식에 맞지 않습니다.';
					resultUid.style.color = "red";

					isUidOk = false;

					return
				// 여기까지 정규표현식에 맞는지 안 맞는지
				}else{
					// AJAX 전송
					const xhr = new XMLHttpRequest(); // 자바스크립트 ajax 객체
					xhr.open("GET", "/Farmstory/user/checkUid?uid="+uid);
					xhr.responseType = "json";
					xhr.send();
					xhr.onreadystatechange = () => {

						if(xhr.readyState == XMLHttpRequest.DONE){
							if(xhr.status == 200){
								const data = xhr.response;
								console.log(data);

								const resultUid = document.querySelector('.resultUid');
								if(data.result == 1){
									resultUid.innerText = '이미 사용중인 아이디 입니다.';
									resultUid.style.color = "red";
								}else{
									resultUid.innerText = '사용 가능한 아이디 입니다.';
									resultUid.style.color = "green";
								}
							}else{
								alert('Request fail...')
							}
						}
					}
					isUidOk = true;
				}
			});
			/*============ id 체크 종료============*/

			/*============ PW 일치여부, 정규표현식 조건 만족 ========= */


			// 비밀번호 다 입력하고 포커스아웃 될 시 실행될 코드
			pass2.addEventListener('focusout', ()=>{

                const pass1 = document.getElementById('pass1'); // 비밀번호
                const pass2 = document.getElementById('pass2'); // 비밀번호 확인
                const resultPass = document.querySelector('.resultPass') // 패스워드 관련 문장들 뜨는 것
                const Reg_submit = document.querySelector('#Reg_submit') // 최종 등록 버튼

				// 정규표현식 맞지 않을 경우
				if(!rePass.test(pass1.value)){
					resultPass.innerText = '숫자,영문,특수문자 포함 5자리 이상이어야 합니다.';
					resultPass.style.color = 'red';

					isPassOk = false;
                    return;
				}
				// 비밀번호 일치 여부
				if(pass1.value === pass2.value){
					resultPass.innerText = '비밀번호가 일치 합니다'
					resultPass.style.color = 'green';

					isPassOk = true;
				}else{
					resultPass.innerText = '비밀번호가 일치 하지 않습니다';
					resultPass.style.color = 'red';
					pass1.focus();

					isPassOk = false;
					return;
				}
			})
			/*=============== PW 검사 종료 ===================*/

			/*================ 이름 검사 ===================*/
			const name = document.getElementById('name')

			const nameResult = document.querySelector('.nameResult')
			name.addEventListener('focusout', ()=>{

				if(!reName.test(name.value)){
					nameResult.innerText = '유효한 이름이 아닙니다.';
					nameResult.style.color = 'red';
					isNameOk = false;
					return;
				}else{
					nameResult.innerText = '성공적으로 입력되었습니다.';
					nameResult.style.color = 'green';
					isNameOk = true;
				}
			})
			/*================ 이름 검사 종료 =================*/

			/*================ 별명 검사 =================*/
			const btnCheckNick = document.getElementById('btnCheckNick') // 중복확인

			btnCheckNick.addEventListener('click', ()=>{
                const nick = document.querySelector('input[name=nick]').value; // 별명 값
                const resultNick = document.querySelector('.resultNick'); // TEXT inner

			    if(!reNick.test(nick)){

                    resultNick.innerText = "입력할 수 없는 별명입니다."
                    resultNick.style.color = "red";

                    isNickOk = false;
                    return;
                }else{
                    const xhr = new XMLHttpRequest(); // 자바스크립트 ajax 객체

                    xhr.open("GET", "/Farmstory/user/checkNick?nick="+nick);
                    xhr.responseType = "json";
                    xhr.send();
                    xhr.onreadystatechange = () => {

                        if(xhr.readyState == XMLHttpRequest.DONE){
                            if(xhr.status == 200){
                                const data = xhr.response;
                                console.log(data);

                                if(data.result == 1){
                                    resultNick.innerText = '이미 사용중인 아이디 입니다.';
                                    resultNick.style.color = "red";

                                    isNickOk = false;
                                    return;
                                }else{
                                    resultNick.innerText = '사용 가능한 아이디 입니다.';
                                    resultNick.style.color = "green";

                                    isNickOk = true;
                                }
                            }else{
                                alert('Request fail...')
                            }
                        }
                    }

                }
			})
			/*================ 이메일 검사 ===================*/
            const email = document.getElementById('email')
            const resultEmail = document.querySelector('.resultEmail')

            email.addEventListener('focusout', ()=>{
                if(!reEmail.test(email.value)){
                    resultEmail.innerText = '올바른 이메일 형식이 아닙니다.'
                    resultEmail.style.color = "red";

                    isEmailOk=false;
                    return;
                }else{
                    resultEmail.innerText = '';
                    isEmailOk = true;
                }
            })

			/*================ 휴대폰 검사 ===================*/
			const hp = document.getElementById('hp')
			const resultHp = document.querySelector('.resultHp')

			hp.addEventListener('focusout', ()=>{
			    if(!reHp.test(hp.value)){
                    resultHp.innerText = '휴대폰 번호를 올바르게 입력하십시오.'
                    resultHp.style.color = "red";

                    isHpOk=false;
                    return;
                }else{
                    resultHp.innerText = '';
                    isHpOk = true;
                }
			})
            /*================ 휴대폰 검사 종료===================*/

            /*================ 최종 검사 ===================*/
            const sign = document.getElementById('Reg_submit')

            sign.addEventListener('click', ()=>{
                if(!isUidOk){
                    alert('아이디를 확인 하십시요.');
                    return false;
                }
                // 비밀번호 검증
                if(!isPassOk){alert('비밀번호가 유효하지 않습니다.'); return false;}
                // 이름 검증
                if(!isNameOk){alert('이름이 유효하지 않습니다.'); return false;}
                // 별명 검증
                if(!isNickOk){alert('별명이 유효하지 않습니다.'); return false;}
                // 이메일 검증
                if(!isEmailOk){alert('이메일이 유효하지 않습니다.'); return false;}
                // 이메일 인증 검증
                if(!isEmailAuthOk){alert('이메일을 인증 하셔야 합니다.'); return false;}
                // 휴대폰 검증
                if(!isHpOk){alert('휴대폰이 유효하지 않습니다.'); return false;}

                return true;
            })

		}//window.onload