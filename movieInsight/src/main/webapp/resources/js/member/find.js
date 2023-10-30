	//이메일 인증 
    const logpass = document.getElementById("logpass");
    const logpassCheck = document.getElementById("logpassCheck");
   
   
 
   
		logpassCheck.addEventListener("click", function(){

	        /* fetch() API 방식 ajax */
	        fetch("/find/findId",{
	        	method:"POST",
	        	headers:{"Content-Type" : "application/json"},
	        	body : JSON.stringify(data)
	        })
	        .then(resp => resp.text())
	        .then(result => {
	            if(result > 0){
	                console.log("인증 번호가 발송되었습니다.")
	            }else{
	                console.log("인증번호 발송 실패")
	            }
	        })
	        .catch(err => {
	            console.log("이메일 발송 중 에러 발생");
	            console.log(err);
	        });
	        
	
	        alert("아이디가 발송 되었습니다.");
	
	
	  
	});
	    
	    
	    
	    
	    
    
    