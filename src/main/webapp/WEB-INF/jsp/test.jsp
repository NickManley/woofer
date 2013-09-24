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
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script type="text/javascript">
        $(document).ready(function() {
            $.ajax({
                url: "http://localhost:8084/test",
                type: "POST",
                datatype: "JSON",
	        success: function(data) {
                    alert(data.test);
                    $("#ele").text(data.test)
                }
            });
        });
        </script>
        <title>JSP Page</title>
    </head>
    <body>

        <h1 id="ele">...</h1>

    </body>
</html>
