
    
const checkObj = {
    "memberId" : false,
    "memberPw" : false,
    "memberPwConfirm" : false,
    "memberNickname" : false,
    "authKey" : false,
    "memberGender" : false
};


// 중복 확인 버튼에 대한 클릭 이벤트 핸들러를 등록
document.getElementById("idCheck").addEventListener("click", function() {
    // 아이디 입력 필드의 값을 가져옴
    var idInput = document.getElementById("member_Id");
    var idValue = idInput.value;
    
    // 입력 필드가 비어 있는지 확인
    if (!idValue) {
        showMessage("아이디를 입력해주세요");
        return; // 입력 필드가 비어 있으면 중복 확인을 수행하지 않음
    }
    
    // 여기에서 중복 확인을 수행하고, 결과를 result 변수에 할당
    // result가 서버에서 받아온 중복 확인 결과라고 가정
    var result = result;
    
    // 결과에 따라 메시지를 표시
    if (result === 1) {
        showMessage("중복된 아이디입니다.");
    } else {
        showMessage("사용 가능한 아이디입니다.");
        checkObj.memberId = true;
    }
});

// 메시지를 표시하는 함수
function showMessage(message) {
    // 결과 메시지를 표시할 요소를 찾음
    var messageDiv = document.getElementById("messageDiv_1");
    messageDiv.textContent = message;
}




// 비밀번호/비밀번호 확인 유효성 검사
const memberPw = document.getElementById("member_Pw");
const memberPwConfirm = document.getElementById("member_PwConfirm");
const pwMessage = document.getElementById("messageDiv_2");

// 비밀번호 입력 시 유효성 검사
memberPw.addEventListener("input", () => {

    // 비밀번호가 입력되지 않은 경우
    if(memberPw.value.trim().length == 0){
        memberPw.value = ""; // 띄어쓰지 못넣게 하기

        pwMessage.innerText = "영어,숫자,특수문자(!,@,#,-,_) 6~20글자 사이로 입력해주세요.";
        pwMessage.classList.remove("confirm", "error"); // 검정 글씨

        checkObj.memberPw = false; // 빈칸 == 유효 X
        return;
    }


    // 정규 표현식을 이용한 비밀번호 유효성 검사

    // 영어,숫자,특수문자(!,@,#,-,_) 6~20글자 사이
    const regEx = /^[a-zA-Z0-9\!\@\#\-\_]{6,20}$/;

    // 입력한 비밀번호가 유효한 경우
    if(regEx.test(memberPw.value)){
        checkObj.memberPw = true; 
        
        // 비밀번호가 유효하게 작성된 상태에서
        // 비밀번호 확인이 입력되지 않았을 때
        if(memberPwConfirm.value.trim().length == 0){

            pwMessage.innerText = "유효한 비밀번호 형식입니다";
            pwMessage.classList.add("confirm");
            pwMessage.classList.remove("error");
        
        }else{
            // 비밀번호가 유효하게 작성된 상태에서
            // 비밀번호 확인이 입력되어 있을 때

            // 비밀번호 == 비밀번호 확인  (같을 경우)
            if(memberPw.value == memberPwConfirm.value){
                pwMessage.innerText = "비밀번호가 일치합니다";
                pwMessage.classList.add("confirm");
                pwMessage.classList.remove("error");
                checkObj.memberPwConfirm = true;
                
            } else{ // 다를 경우
                pwMessage.innerText = "비밀번호가 일치하지 않습니다";
                pwMessage.classList.add("error");
                pwMessage.classList.remove("confirm");
                checkObj.memberPwConfirm = false;
            }
        }

        
    } else{ // 유효하지 않은 경우
        
        pwMessage.innerText = "비밀번호 형식이 유효하지 않습니다";
        pwMessage.classList.add("error");
        pwMessage.classList.remove("confirm");
        checkObj.memberPw = false; 
    }
});


// 비밀번호 확인 유효성 검사
memberPwConfirm.addEventListener('input', ()=>{

    if(checkObj.memberPw){ // 비밀번호가 유효하게 작성된 경우에

        // 비밀번호 == 비밀번호 확인  (같을 경우)
        if(memberPw.value == memberPwConfirm.value){
            pwMessage.innerText = "비밀번호가 일치합니다";
            pwMessage.classList.add("confirm");
            pwMessage.classList.remove("error");
            checkObj.memberPwConfirm = true;
            
        } else{ // 다를 경우
            pwMessage.innerText = "비밀번호가 일치하지 않습니다";
            pwMessage.classList.add("error");
            pwMessage.classList.remove("confirm");
            checkObj.memberPwConfirm = false;
        }

    } else { // 비밀번호가 유효하지 않은 경우
        checkObj.memberPwConfirm = false;
    }
});

const memberEmail = document.getElementById("memberEmail");
const emailMessage = document.getElementById("emailMessage");

memberEmail.addEventListener("input", () => {

    if(memberEmail.value.trim().length == 0) {
        memberEmail.value = "";

        emailMessage.innerText = "메일을 받으실 이메일을 입력해주세요.";

        checkObj.memberEmail = false;
        return;
    }

    const regEx = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

    if( regEx.test(memberEmail.value) ) {
        console.log("test?")
        fetch("/movieInsight/dupCheck/email?email=" + memberEmail.value)
		.then(res => res.text())
		.then(count => {
			
			//count : 중복되면 1, 중복 아니면 0
			if(count == 0) {
			 	emailMessage.innerText = "사용 가능한 이메일입니다.";
        		emailMessage.classList.add("confirm"); // .confirm 스타일 적용
        		emailMessage.classList.remove("error"); // .error 스타일 제거
        		checkObj.memberEmail = true;
			}else {
				emailMessage.innerText = "이미 사용중인 이메일입니다.";
        		emailMessage.classList.add("error"); // .error 스타일 적용
        		emailMessage.classList.remove("confirm"); // .confirm 스타일 제거
        		checkObj.memberEmail = false;
			}	
			
		})
		.catch(err => console.log(err));
    } else {
        emailMessage.innerText = "이메일 형식이 유효하지 않습니다.";
        emailMessage.classList.add("error");
        emailMessage.classList.remove("confirm");

        checkObj.memberEmail = false;
    }
});

// --------------------- 이메일 인증 ---------------------

// 인증번호 발송
const sendAuthKeyBtn = document.getElementById("sendAuthKeyBtn");
const authKeyMessage = document.getElementById("authKeyMessage");
let authTimer;
let authMin = 4;
let authSec = 59;

// 인증번호를 발송한 이메일 저장
let tempEmail;

sendAuthKeyBtn.addEventListener("click", function(){

    authMin = 4;
    authSec = 59;

    checkObj.authKey = false;

    if(checkObj.memberEmail){ // 중복이 아닌 이메일인 경우


        /* fetch() API 방식 ajax */
        fetch("/movieInsight/sendEmail/superEmail?memberEmail="+memberEmail.value)
        .then(resp => resp.text())
        .then(result => {
            if(result > 0){
                console.log("인증 번호가 발송되었습니다.")
                tempEmail = memberEmail.value;
            }else{
                console.log("인증번호 발송 실패")
            }
        })
        .catch(err => {
            console.log("이메일 발송 중 에러 발생");
            console.log(err);
        });
        

        alert("인증번호가 발송 되었습니다.");

        
        authKeyMessage.innerText = "05:00";
        authKeyMessage.classList.remove("confirm");

        authTimer = window.setInterval(()=>{
													// 삼항연산자  :  조건 	  ?   	true : false
            authKeyMessage.innerText = "0" + authMin + ":" + (authSec < 10 ? "0" + authSec : authSec);
            
            // 남은 시간이 0분 0초인 경우
            if(authMin == 0 && authSec == 0){
                checkObj.authKey = false;
                clearInterval(authTimer);
                return;
            }

            // 0초인 경우
            if(authSec == 0){
                authSec = 60;
                authMin--;
            }


            authSec--; // 1초 감소

        }, 1000)

    } else{
        alert("중복되지 않은 이메일을 작성해주세요.");
        memberEmail.focus();
    }

});

// 인증 확인
const authKey = document.getElementById("authKey");
const checkAuthKeyBtn = document.getElementById("checkAuthKeyBtn");

checkAuthKeyBtn.addEventListener("click", function(){

    if(authMin > 0 || authSec > 0){ // 시간 제한이 지나지 않은 경우에만 인증번호 검사 진행
        /* fetch API */
        const obj = {"inputKey":authKey.value, "email":tempEmail}
        const query = new URLSearchParams(obj).toString()
        // inputKey=123456&email=user01

        fetch("/movieInsight/sendEmail/checkAuthKey?" + query)
        .then(resp => resp.text())
        .then(result => {
            if(result > 0){
                clearInterval(authTimer);
                alert("인증번호가 일치합니다.")
                authKeyMessage.classList.add("confirm");
                checkObj.authKey = true;

            } else{
                alert("인증번호가 일치하지 않습니다.")
                checkObj.authKey = false;
            }
        })
        .catch(err => console.log(err));


    } else{
        alert("인증 시간이 만료되었습니다. 다시 시도해주세요.")
    }

});



// 닉네임 유효성 검사
const memberNickname = document.getElementById("memberNickname");
const nickMessage = document.getElementById('nickMessage');

// 닉네임이 입력이 되었을 때
memberNickname.addEventListener("input", ()=>{

    // 닉네임 입력이 되지 않은 경우
    if(memberNickname.value.trim() == ''){
        nickMessage.innerText = "한글,영어,숫자로만 2~10글자";
        nickMessage.classList.remove("confirm", "error");
        checkObj.memberNickname = false;
        memberNickname.value = ""; 
        return;
    }

    // 정규표현식으로 유효성 검사
    const regEx = /^[가-힣\w\d]{2,10}$/;

    if(regEx.test(memberNickname.value)){// 유효

        fetch("/movieInsight/member/dupCheck/nickname?memberNickname="+memberNickname.value)
        .then(resp => resp.text()) // 응답 객체를 text로 파싱(변환)
        .then(count => {

            if(count == 0){ // 중복 아닌 경우
                nickMessage.innerText = "사용 가능한 닉네임 입니다";
                nickMessage.classList.add("confirm");
                nickMessage.classList.remove("error");
                checkObj.memberNickname = true;
                
            }else{ // 중복인 경우
                nickMessage.innerText = "이미 사용중인 닉네임 입니다";
                nickMessage.classList.add("error");
                nickMessage.classList.remove("confirm");
                checkObj.memberNickname = false;
            }
        })
        .catch(err => console.log(err));

        


    } else{ // 무효
        nickMessage.innerText = "닉네임 형식이 유효하지 않습니다";
        nickMessage.classList.add("error");
        nickMessage.classList.remove("confirm");
        checkObj.memberNickname = false;
    }

});
    

// 버튼 요소를 가져옵니다.
const maleButton = document.getElementById("genderM");
const femaleButton = document.getElementById("genderF");

maleButton.addEventListener("click", function() {
    // 클릭된 버튼의 텍스트 색상을 파란색으로 변경
    maleButton.style.color = "blue";
    femaleButton.style.color = "";
    
    // checkObj 객체의 gender 속성을 true로 설정
    checkObj.gender = true;
});

femaleButton.addEventListener("click", function() {
    // 클릭된 버튼의 텍스트 색상을 파란색으로 변경
    femaleButton.style.color = "blue";
    maleButton.style.color = "";
    
    // checkObj 객체의 gender 속성을 true로 설정
    checkObj.gender = true;
});

maleButton.addEventListener("click", function() {
    checkObj.memberGender = true;
    // 다른 버튼의 memberGender는 false로 설정 (둘 중 하나만 true가 되도록)
    femaleButton.memberGender = false;
});

femaleButton.addEventListener("click", function() {
    checkObj.memberGender = true;
    // 다른 버튼의 memberGender는 false로 설정 (둘 중 하나만 true가 되도록)
    maleButton.memberGender = false;
});


    

document.getElementById("signUpFrm").addEventListener("submit", e => {
    for (let key in checkObj) {
        if (!checkObj[key]) {
            switch (key) {
                case "memberId":
                    alert("아이디가 유효하지 않습니다");
                    break;

                case "memberPw":
                    alert("비밀번호가 유효하지 않습니다");
                    break;

                case "memberPwConfirm":
                    alert("비밀번호가 확인되지 않았습니다");
                    break;

                case "memberNickname":
                    alert("닉네임이 유효하지 않습니다");
                    break;

                case "memberGender":
                    alert("성별을 선택해주세요");
                    break;
            }

            // 유효하지 않은 input 태그로 focus 이동
            document.getElementById(key).focus();

            e.preventDefault(); // form 태그 기본 이벤트 제거
            return; // 함수 종료
        }

    }


});
    
    

    
    
    
    
    
