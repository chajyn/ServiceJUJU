<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>stock market</title>

    <link rel="stylesheet" href="./css/index.css" type="text/css">
    <link rel="stylesheet" href="./css/main.css" type="text/css">
    <link rel="stylesheet" href="./css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/normalize.css" type="text/css">
    <script src="js/index.js"></script>
</head>
<body>
    <main id="main-page" class="page">
        <div>
            <h1 id="logo">STOCK<br>MARKET</h1>
            <div class="bg-img"></div>
            <div class="main-contents">
                <h1>주식을 쇼핑하다</h1>
                <form action="/main">
                    <input type="submit" name="search" value="시작하기">
                </form>
            </div>
        </div>
    </main>
    <section id="sub-page" class="page">
        <div>
            <p>주식투자를 쉽고 빠르게</p>
            <p>Easy Way to SMART TRADING</p>
        </div>
    </section>
    <nav id="index-navigator" class="wrap">
        <div class="circle circle1"></div>
        <div class="circle"></div>
        <div class="circle"></div>
    </nav>
</body>

</html>