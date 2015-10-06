<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
</head>


<spring:hasBindErrors name="bindForm">
    <div id="messages">
        <p>入力値に誤りがあります。</p>
        <ul>
            <c:forEach items="${errors.allErrors}" var="error">
                <li><spring:message message="${error}"/></li>
            </c:forEach>
        </ul>
    </div>
</spring:hasBindErrors>

</body>
</html>
