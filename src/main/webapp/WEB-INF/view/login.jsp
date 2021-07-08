<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script defer src="/js/login.js"></script>
    <title></title>
</head>
<body>
<h1>로그인</h1>
    <form action="/login" id="form" method="post">
        <input type="text" name="username" value="123">
        <input type="password" name="password" value="123">
        <input type="button" id="button1" value="로그인">
    </form>
<a href="/join">회원가입</a>
</body>
</html>