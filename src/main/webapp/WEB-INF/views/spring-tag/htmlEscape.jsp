<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
</head>


<c:set var="message">
    <b>aaaa</b>
</c:set>

<spring:htmlEscape defaultHtmlEscape="false"/>

<spring:message text="${message}"/><br>

<spring:htmlEscape defaultHtmlEscape="true"/>

<spring:message text="${message}"/><br>
</body>
</html>
