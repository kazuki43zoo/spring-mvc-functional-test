<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>

<div>
    <span id="title">
        <spring:message code="title.home"/>
    </span>
</div>
<div>
    <span id="passwordValidPolicy">
        <spring:message code="guidance.passwordValidPolicy" arguments="90"/>
    </span>
</div>
<div>
    <span id="passwordValidPolicy">
        <spring:message code="guidance.passwordValidPolicy">
            <spring:argument value="90"/>
            <spring:argument value="900"/>
        </spring:message>
    </span>
</div>

<div>
    <span id="passwordValidPolicy">
        <spring:message code="guidance.passwordValidPolicy" arguments="${[90,20]}"/>
    </span>
</div>
<div>
    <span id="passwordValidPolicy">
        <spring:message code="${message.code}" arguments="${message.arguments}"/>
    </span>
</div>
<div>
    <span id="passwordValidPolicy">
        <spring:message code="${message.code}" arguments="${message.argumentList}"/>
    </span>
</div>
<body>

</body>
</html>
