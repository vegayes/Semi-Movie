<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<%-- ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
	1) CSS 변경 ( hove 시 디자인 ) 
	2) 마이페이지 눌렀을 때 로그인이 되어있지 않으면 로그인 창으로 넘어가거나 경고창으로 알려주기 
★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ --%>

<%-- ◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎
	1) 로고 누르면 영화 메인 페이지로 보내기
◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎ --%>



<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>LOGIN_SIGNUP</title>
    
 	<link rel="stylesheet" href="/movieInsight/resources/css/member/login_signUp.css">
    
  </head>
  <body>
    <div class="section">
      <div class="container">
        <div class="row full-height justify-content-center">
          <div class="col-12 text-center align-self-center py-5">
            <img
              src="/movieInsight/resources/images/common/logo.png"
              style="margin: 0 auto; display: flex; margin-top: 24px"
            />
            <div class="section pb-5 pt-5 pt-sm-2 text-center">
              <h6 class="mb-0 pb-3">
                <span><label for="reg-log">LogIn</label> </span
                ><span><label for="reg-log">Sign Up</label></span>
              </h6>
              <input
                class="checkbox"
                type="checkbox"
                id="reg-log"
                name="reg-log"
              />
              <label for="reg-log"> </label>
              <div class="card-3d-wrap mx-auto">
                <div class="card-3d-wrapper">
                  <div class="card-front">
                    <div class="center-wrap">
                      <div class="section text-center">
                        <h4 class="mb-4 pb-3">Log In</h4>
                        <div class="form-group">
                          <p class="mb-0 mt-4 text-center">
                            <a href="/movieInsight/find/findID" class="link"
                              >Forgot ID?</a
                            >
                          </p>
                          <form action="/movieInsight/member/login"  method="post" name="login-form" id="loginFrm">
	                          <input
	                            name="memberId"
	                            class="form-style"
	                            placeholder="Your ID"
	                            id="memberId"
	                            autocomplete="off"
	                          />
	                          <i class="input-icon uil uil-at"></i>
	                        </div>
	                        <div class="form-group mt-2">
	                          <p class="mb-0 mt-4 text-center">
	                            <a href="/movieInsight/find/findPW" class="link"
	                              >Forgot PW?</a
	                            >
	                          </p>
	                          <input
	                            type="password"
	                            name="memberPw"
	                            class="form-style"
	                            placeholder="Your Password"
	                            id="memberPw"
	                            autocomplete="off"
	                          />
	                          <i class="input-icon uil uil-lock-alt"></i>
	                        </div>
	                        <label
	                          style="
	                            color: black;
	                            margin-bottom: 12px;
	                            display: block;
	                          "
	                        >
	                          <input
	                            type="checkbox"
	                            style="position: relative; left: 0"
	                          />
	                          Remember ID
	                        </label>
	                         <button type="submit"class="btn mt-4">LOGIN</button>
						</form>
                       
                      </div>
                    </div>
                  </div>
                  <div class="card-back">
                    <div class="center-wrap">
                      <div class="section text-center">
                        <div class="form-group">
                          <h5 class="labela">USER ID</h5>
                          
                          		<form action="/movieInsight/member/signUp" method="POST" name="inputMember" id="signUpFrm">
			                          <input
			                            type="text"
			                            name="memberId"
			                            class="form-style"
			                            placeholder="your Id"
			                            id="member_Id"
			                            autocomplete="off"
			                          />
			                          <button type="button" id="idCheck">중복확인</button>
			                          <p id="messageDiv_1"></p>
			                          <i class="input-icon uil uil-user"></i>
			                        </div>
			                        <div class="form-group mt-2">
			                          <h5 class="labela">PW</h5>
			
			                          <input
			                            type="password"
			                            name="memberPw"
			                            class="form-style"
			                            placeholder="Your Password"
			                            id="member_Pw"
			                            autocomplete="off"
			                          />
			                          <i class="input-icon uil uil-lock-alt"></i>
			                        </div>
			                        <div class="form-group mt-2">
			                          <h5 class="labela">PW Check</h5>
			
			                          <input
			                            type="password"
			                            name="memberPwCheck"
			                            class="form-style"
			                            placeholder="Your Password"
			                            id="member_PwConfirm"
			                            autocomplete="off"
			                          />
			                          <p id="messageDiv_2"></p>
			                          <i class="input-icon uil uil-lock-alt"></i>
			                        </div>
			                        <span id="ggg"></span>
			                        <div class="form-group mt-2">
			                          <h5 class="labela">EMAIL</h5>
			
			                          <input
			                            type="email"
			                            name="memberEmail"
			                            class="form-style"
			                            placeholder="Your Email"
			                            id="memberEmail"
			                            autocomplete="off"
			                          />
			                          <button id="sendAuthKeyBtn" type="button">인증하기</button>
			                          <i class="input-icon uil uil-lock-alt"></i>
			                       	   <p id="emailMessage">사용가능한 이메일을 입력해주세요.</p>
			                       	   <p id="authKeyMessage">05:00</p>
				                       	   <div class="signUp-input-area">
							                    <input type="text" name="authKey" id="authKey" s placeholder="인증번호 입력" maxlength="6" autocomplete="off" >
							                    
							                    <button id="checkAuthKeyBtn" type="button">인증확인</button>
					            		   </div>
			                        </div>
			                        <div class="form-group mt-2">
			                          <h5 class="labela">NICKNAME</h5>
			
			                          <input
			                            type="text"
			                            name="memberNickname"
			                            class="form-style"
			                            placeholder="Your Nickname"
			                            id="member_Nickname"
			                            autocomplete="off"
			                          />
                                <P id="nickMessage">메세지</P>
			                          <i class="input-icon uil uil-lock-alt"></i>
			                        </div>
			                        <div class="form-group mt-2">
			                          <h5 class="labela">GENDER</h5>
			
			                          <!-- <button class="gender" id="genderM" type="button" name="memberGender" value="M">남자</button>
			                          <button class="gender" id="genderF" type="button" name="memberGender" value="F">여자</button> -->

                                <input type="radio" class="gender" id="genderM" type="button" name="memberGender" value="M">남자
                                <input type="radio" class="gender" id="genderF" type="button" name="memberGender" value="F">여자
			                          <i class="input-icon uil uil-lock-alt"></i>
			                        </div>
                              <button id="signUpBut" class="btn mt-4 blue" type="submit">SIGN UP</button>
			                    </form>    
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
  
  <script src="/movieInsight/resources/js/member/login_signUp.js"></script>
  <script src="/movieInsight/resources/js/member/login.js"></script>
</html>
