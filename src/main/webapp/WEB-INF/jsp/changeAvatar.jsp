<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/css/changeAvatar.css"/>
        <link rel="stylesheet" type="text/css" href="/css/woofer-input-box.css"/>
        <link rel="stylesheet" type="text/css" href="/css/woofer-buttons.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        
        <a href="/">
            <img src="/img/logo-100-text.png" alt="Woofer"
                 style="display: block;"/>
        </a>
        
        <div id="changeAvatarText">Change Avatar</div>
        <div>
            <form:form commandName="changeAvatarForm" name="changeAvatarForm"
                       action="/changeAvatar" method="POST"
                       enctype="multipart/form-data">
                <div class="errorText">&nbsp;
                    <c:out value="${uploadError}"/>
                </div>
                <div style="text-align: center">
                    <img src="/avatar/${fn:escapeXml(user.username)}"/>
                    <input type="file" class="avatar-upload-input"
                           name="avatar"/>
                </div>
                <div style="text-align: center; margin-top: 25px;">
                    <a class="woofer-gray-button">Remove and Use Default</a>
                    <input type="submit" class="woofer-blue-button" value="Upload New Avatar"/>
                </div>
            </form:form>
        </div>
        
    </body>
</html>
