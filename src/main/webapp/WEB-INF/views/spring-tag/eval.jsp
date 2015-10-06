<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
</head>

<spring:message code="guidance.passwordValidPolicy">
    <spring:argument>
        <spring:eval expression="@appSettings.passwordValidDays"/>
    </spring:argument>
</spring:message>

</body>
</html>
