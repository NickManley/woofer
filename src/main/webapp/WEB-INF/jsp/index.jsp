<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/index.css"/>
        <link rel="stylesheet" type="text/css" href="/css/woofer-search-box.css"/>
        <link rel="stylesheet" type="text/css" href="/css/woofer-input-box.css"/>
        <link rel="stylesheet" type="text/css" href="/css/woofer-buttons.css"/>
        <title>JSP Page</title>
    </head>
    <body>

        <a href="/">
            <img src="/img/logo-100-text.png" alt="Woofer"
                 style="display: block;"/>
        </a>

        <jsp:include page="/WEB-INF/jspf/sidebar.jspf"/>

        <div class="mainbox">

            <c:if test="${user != null && isIndexOrOwnProfile == true}">
                <div class="woof-display-box"
                     style="margin-bottom: 25px;">
                    <div class="woof-text" style="margin: 10px;">
                        <form:form modelAttribute="messageForm" name="messageForm"
                                   action="/postMessage" method="POST">
                            Send your own WOOF<br/>
                            <textarea id="woofer-textarea" name="text"></textarea><br/>
                            <c:if test="${invalidWoof != null}">
                                <p class="errorText"><spring:message code="invalidWoof"/></p>
                            </c:if>
                            <input type="submit" class="woofer-blue-button" value="WOOF"/>
                        </form:form>
                    </div>
                </div>
            </c:if>

            <c:forEach var="message" items="${messages}">
                <div class="woof-display-box">
                    <div class="woof-avatar">
                        <img src="/avatar/${fn:escapeXml(message.user.username)}" alt="avatar"/>
                    </div>
                    <div class="woof-text">
                        <div class="woof-username">
                            <a href="/profile/${fn:escapeXml(message.user.username)}">
                                <c:out value="${message.user.username}"/>
                            </a>
                        </div>
                        <div>
                            <c:out value="${message.text}"/>
                        </div>
                    </div>
                </div>
            </c:forEach>
            
        </div>

    </body>
</html>
