<%@page import="com.smict.product.model.ProductModel"%>
<%@ page language="java" import="java.util.*,java.text.DecimalFormat" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html >
  <head>
    <meta charset="UTF-8">
    <title>Login - Smart LDC Dental </title>
    <link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
    
    <link href="css/style-login.css" rel="stylesheet">
    
    
    <style>
    /* NOTE: The styles were added inline because Prefixfree needs access to your styles and they must be inlined if they are on local disk! */
    html,
    body {
    width: 100%;
    height: 100%;
    }
    body {
    margin: 0 auto;
    display: table;
    text-align: center;
    font-family: 'Open Sans', sans-serif;
    background: #81b5d6;
    max-width: 33em;
    }
    .wrap {
    margin-top:50px;
    }
    .flip-container {
    perspective: 1000;
    border-radius: 50%;
    margin: 0 auto 10px auto;
    }
    .logged-in {
    transform: rotateY(180deg);
    }
    .flip-container, .front, .back, .back-logo {
    width: 130px;
    height: 130px;
    }
    .flipper {
    transition-duration: 0.6s;
    transform-style: preserve-3d;
    }
    .front,
    .back {
    backface-visibility: hidden;
    position: absolute;
    top: 0;
    left: 0;
    background-size: cover;
    }
    .front {
    background: url(http://s8.postimg.org/y7z5wso29/Flip_Img.png) 0 0 no-repeat;
    }
    .back {
    transform: rotateY(180deg);
    background: url(http://s8.postimg.org/u04do1mmp/Flip_Img2.png) 0 0 no-repeat;
    }
    h1 {
    font-size: 22px;
    color: #FFF;
    }
    h1 span {
    font-weight: 300;
    }
    input[type=text],
    input[type=password] {
    color:#FFF;
    background: #68add8; /* Old browsers */
    background: linear-gradient(45deg,  #68add8 0%,#8cbede 100%); /* W3C */
    width:250px;
    height:40px;
    margin: 0 auto 10px auto;
    font-size:14px;
    padding-left:15px;
    border:none;
    box-shadow: -3px 3px #679acb ;
    -webkit-appearance:none;
    border-radius:0;
    border-top: 1px solid #92c5e2;
    border-right: 1px solid #92c5e2;
    }
    input::-webkit-input-placeholder {
    color: #FFF;
    }
    input:focus {
    outline:none;
    }
    input[type=submit] {
    color: #fff;
    background-color:#3f88b8;
    font-size: 14px;
    height: 40px;
    border: none;
    margin: 0 auto 0 17px;
    padding: 0 20px 0 20px;
    -webkit-appearance:none;
    border-radius:0;
    cursor: pointer;
    }
    input[type=submit]:hover {
    background-color:#3f7ba2;
    }
    a {
    color:#1c70a7;
    font-weight:600;
    font-size:12px;
    text-decoration:none;
    }
    a:hover {
    color:#3f7ba2;
    }
    .hint
    {
    width:250px;
    dislay:block;
    margin:80px auto 0 auto;
    text-align:left;
    }
    .hint p
    {
    padding: 5px 0 5px 0;
    color:#FFF;
    font-weight:600;
    font-size:20px;
    }
    .hint p span
    {
    font-weight:300;
    font-size:16px;
    }
    </style>
    <script src="js/login-prefixfree.min.js"></script>
    
  </head>
  <body>
    <div class="wrap">
      
      <div class="flip-container" id='flippr'>
        <div class="flipper">
          <div class="front"></div>
          <div class="back"></div>
        </div>
      </div>
      
      <h1 class="text" id="welcome">Smart LDC Dental</h1>
      
      <form action="attempt" method="post">
        <!-- <input type="text" name="AuthModel.empUsr" autofocus="autofocus" placeholder="Username"> -->
        <!-- <input type="password" name="AuthModel.empPWD" placeholder="Password"> -->
        <s:textfield type="text" 
            name="authModel.empUsr" 
            autofocus="autofocus" 
            autocomplete="off" 
            placeholder="Username"/>
            
        <s:textfield type="password" 
            name="authModel.empPWD" 
            placeholder="Password" 
            autocomplete="off" />
        
        <div class='login'>
          <a href="#"><i class="icon-cog"></i> I've fogotten my password</a>
          <input type='submit' value='Login' >
        </div><!-- /login -->
      </form>
        
        </div><!-- /wrap -->
        
        <script src='js/jquery-2.2.4/jquery.min.js'></script>
        <script src="js/index.js"></script>
      </body>
    </html>