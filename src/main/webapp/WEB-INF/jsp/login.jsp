<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/login.css"/>
        <link rel="stylesheet" type="text/css" href="/css/woofer-input-box.css"/>
        <link rel="stylesheet" type="text/css" href="/css/woofer-buttons.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        
        <a href="/">
            <img src="/img/logo-100-text.png" alt="Woofer"
                 style="display: block;"/>
        </a>
        
        <div id="loginText">Login</div>
        <div>
            <c:if test="${accountCreated != null}">
                <p style="text-align: center; color: #006400; font-weight: bold;">
                    Congratulations! Your account has been created. You may now login.
                </p>
            </c:if>
            <form:form commandName="loginForm" name="loginForm" action="/login" method="POST">
                <div class="errorText">&nbsp;
                    <form:errors path="*"/>
                    <c:if test="${invalidLogin}">
                        Invalid username or password
                    </c:if>
                </div>
                <div style="text-align: center;"><input type="text" class="woofer-input-text" name="username"
                       placeholder="username"/></div>
                <div style="text-align: center;"><input type="password" class="woofer-input-text" name="password"
                       placeholder="password"/></div>
                <div style="text-align: center; margin-top: 25px;">
                    <a href="/register" class="woofer-gray-button">Register</a>
                    <input type="submit" class="woofer-blue-button" value="Login"/>
                </div>
            </form:form>
        </div>
        
    </body>
</html>
