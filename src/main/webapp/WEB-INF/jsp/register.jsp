<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/register.css"/>
        <link rel="stylesheet" type="text/css" href="/css/woofer-input-box.css"/>
        <link rel="stylesheet" type="text/css" href="/css/woofer-buttons.css"/>
        <title>JSP Page</title>
    </head>
    <body>

        <a href="/">
            <img src="/img/logo-100-text.png" alt="Woofer"
                 style="display: block;"/>
        </a>

        <div id="registerText">Register</div>       
        <div>
            <form:form commandName="registerForm" name="registerForm" method="POST"
                       action="/register">
                <div class="errorText">
                    <ul>
                        <form:errors path="username" element="li" delimiter="</li><li>"/>
                        <form:errors path="password" element="li" delimiter="</li><li>"/>
                        <form:errors path="confirmPassword" element="li" delimiter="</li><li>"/>
                        <form:errors path="email" element="li" delimiter="</li><li>"/>
                        <form:errors path="birthDate" element="li" delimiter="</li><li>"/>
                    </ul>
                </div>
                <table style="margin: 0 auto; text-align: right;">
                    <tr>
                        <td>Username: </td>
                        <td>
                            <input type="text" class="woofer-input-text" name="username"
                                   placeholder="username" value="${fn:escapeXml(registerForm.username)}"/>
                        </td>
                        <td>*</td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td>
                            <input type="password" class="woofer-input-text" name="password"
                                placeholder="password"/>
                        </td>
                        <td>*</td>
                    </tr>
                    <tr>
                        <td>Confirm Password: </td>
                        <td>
                            <input type="password" class="woofer-input-text" name="confirmPassword"
                                placeholder="confirm password"/>
                        </td>
                        <td>*</td>
                    </tr>
                    <tr>
                        <td>Email: </td>
                        <td>
                            <input type="text" class="woofer-input-text" name="email"
                                placeholder="email" value="${fn:escapeXml(registerForm.email)}"/>
                        </td>
                        <td>*</td>
                    </tr>
                    <tr>
                        <td>Birth Date: </td>
                        <td>
                            <select name="birthDay">
                                <c:forEach begin="1" end="31" var="i">
                                    <c:choose>
                                        <c:when test="${i == registerForm.birthDate.date}">
                                            <option value="${i}" selected>${i}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${i}">${i}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>&nbsp;
                            <select name="birthMonth">
                                <c:forEach begin="0" end="11" var="i">
                                    <c:choose>
                                        <c:when test="${i == registerForm.birthDate.month}">
                                            <option value="${i+1}" selected>${monthList[i]}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${i+1}">${monthList[i]}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <select name="birthYear">
                                <c:forEach begin="${startYear}" end="${endYear}" var="i">
                                    <c:choose>
                                        <c:when test="${i == registerForm.birthDate.year+1900}">
                                            <option value="${i}" selected>${i}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${i}">${i}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </td>
                        <td>*</td>
                    </tr>
                </table>
                <input type="hidden" name="birthDate" value="2002-02-28"/>
                <div style="text-align: center; margin-top: 25px;">
                    <input type="submit" class="woofer-blue-button" value="Register"/>
                </div>
            </form:form>
        </div>

    </body>
</html>
