<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <spring:theme code="stylesheet" text="app/css/styles.css" var="stylesheet"/>
    <link rel="stylesheet" href="<c:url value="/resources/${stylesheet}"/>">
</head>

</body>
</html>
