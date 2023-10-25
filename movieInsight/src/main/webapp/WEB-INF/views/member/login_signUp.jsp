<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

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
                            <a href="ooo_find_id.html" class="link"
                              >Forgot ID?</a
                            >
                          </p>
                          <input
                            type="email"
                            name="logemail"
                            class="form-style"
                            placeholder="Your Email"
                            id="logemail"
                            autocomplete="off"
                          />
                          <i class="input-icon uil uil-at"></i>
                        </div>
                        <div class="form-group mt-2">
                          <p class="mb-0 mt-4 text-center">
                            <a href="ooo_find_pw.html" class="link"
                              >Forgot PW?</a
                            >
                          </p>
                          <input
                            type="password"
                            name="logpass"
                            class="form-style"
                            placeholder="Your Password"
                            id="logpass"
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

                        <a href="#" class="btn mt-4">LOGIN</a>
                      </div>
                    </div>
                  </div>
                  <div class="card-back">
                    <div class="center-wrap">
                      <div class="section text-center">
                        <h4 class="mb-4 pb-3">Sign Up</h4>
                        <div class="form-group">
                          <h5 class="labela">USER ID</h5>
                          
                          		<form action="/member/login_signUp" method="POST" name="inputMember">
			                          <input
			                            type="text"
			                            name="memberId"
			                            class="form-style"
			                            placeholder="your Id"
			                            id="logname"
			                            autocomplete="off"
			                          />
			                          <button onclick="return false;" type="button" id="idCheck">중복확인</button>
			                          <i class="input-icon uil uil-user"></i>
			                        </div>
			                        <div class="form-group mt-2">
			                          <h5 class="labela">PW</h5>
			
			                          <input
			                            type="password"
			                            name="memberPw"
			                            class="form-style"
			                            placeholder="Your Password"
			                            id="asdasd"
			                            autocomplete="off"
			                          />
			                          <i class="input-icon uil uil-lock-alt"></i>
			                        </div>
			                        <div class="form-group mt-2">
			                          <h5 class="labela">PW Check</h5>
			
			                          <input
			                            type="password"
			                            name="memberPw"
			                            class="form-style"
			                            placeholder="Your Password"
			                            id="fff"
			                            onblur="document.getElementById('ggg').textContent = (document.getElementById('asdasd').textContent === document.getElementById('fff').textContent) ? '패스워드가 일치합니다' : '패스워드가 일치하지 않습니다d'"
			                            autocomplete="off"
			                          />
			                          <i class="input-icon uil uil-lock-alt"></i>
			                        </div>
			                        <span id="ggg"></span>
			                        <div class="form-group mt-2">
			                          <h5 class="labela">EMAIL</h5>
			
			                          <input
			                            type="email"
			                            name="memberEmail"
			                            class="form-style"
			                            placeholder="Your Password"
			                            id="logpass"
			                            autocomplete="off"
			                          />
			                          <button>인증</button>
			                          <i class="input-icon uil uil-lock-alt"></i>
			                        </div>
			                        <div class="form-group mt-2">
			                          <h5 class="labela">NICKNAME</h5>
			
			                          <input
			                            type="text"
			                            name="memberNickname"
			                            class="form-style"
			                            placeholder="Your Password"
			                            id="logpass"
			                            autocomplete="off"
			                          />
			                          <i class="input-icon uil uil-lock-alt"></i>
			                        </div>
			                        <div class="form-group mt-2">
			                          <h5 class="labela">GENDER</h5>
			
			                          <button class="gender">남자</button>
			                          <button class="gender">여자</button>
			                          <i class="input-icon uil uil-lock-alt"></i>
			                        </div>
			                        <a href="#" class="btn mt-4 blut">SIGN UP</a>
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
  
</html>
