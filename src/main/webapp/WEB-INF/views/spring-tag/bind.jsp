<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
</head>


<div>
    <spring:bind path="bindForm">
        path : ${status.path}<br>
        error : ${status.error}<br>
        value : ${status.value}<br>
        actualValue : ${status.actualValue}<br>
        displayValue : ${status.displayValue}<br>
        errorCode : ${status.errorCode}<br>
        errorCodes : ${status.errorCodes}<br>
        errorMessage : ${status.errorMessage}<br>
        errorMessages : ${status.errorMessages}<br>
        errors : ${status.errors}<br>
        expression : ${status.expression}<br>
        valueType : ${status.valueType}<br>
        editor : ${status.editor}<br>
    </spring:bind>

</div>
<hr>
<spring:htmlEscape defaultHtmlEscape="true"/>
<div>
    <spring:bind path="bindForm.text" htmlEscape="true">
        path : ${status.path}<br>
        error : ${status.error}<br>
        value : ${status.value}<br>
        actualValue : ${status.actualValue}<br>
        displayValue : ${status.displayValue}<br>
        errorCode : ${status.errorCode}<br>
        errorCodes : ${status.errorCodes}<br>
        errorMessage : ${status.errorMessage}<br>
        errorMessages : ${status.errorMessages}<br>
        errors : ${status.errors}<br>
        expression : ${status.expression}<br>
        valueType : ${status.valueType}<br>
        editor : ${status.editor}<br>
    </spring:bind>

</div>
<hr>
<div>

    <form:select path="bindForm.date">
        <c:forEach items="${targetDateList}" var="targetDate">
            <spring:transform value="${targetDate}" var="formattedTargetDate"/>
            <form:option value="${formattedTargetDate}">${formattedTargetDate}</form:option>
        </c:forEach>
    </form:select>


    <spring:nestedPath path="bindForm">
        <form:select path="date">
            <c:forEach items="${targetDateList}" var="targetDate">
                <spring:transform value="${targetDate}" var="formattedTargetDate"/>
                <form:option value="${formattedTargetDate}">${formattedTargetDate}</form:option>
            </c:forEach>
        </form:select>

        <spring:bind path="date">
            <select name="${status.expression}">
                <c:forEach items="${targetDateList}" var="targetDate">
                    <spring:transform value="${targetDate}" var="formattedTargetDate"/>
                    <option value="${formattedTargetDate}"
                            <c:if test="${status.value == formattedTargetDate}">selected</c:if>>
                            ${formattedTargetDate}
                    </option>
                </c:forEach>
            </select>
            <br>
            path : ${status.path}<br>
            error : ${status.error}<br>
            value : ${status.value}<br>
            actualValue : ${status.actualValue}<br>
            displayValue : ${status.displayValue}<br>
            errorCode : ${status.errorCode}<br>
            errorCodes : ${status.errorCodes}<br>
            errorMessage : ${status.errorMessage}<br>
            errorMessages : ${status.errorMessages}<br>
            errors : ${status.errors}<br>
            expression : ${status.expression}<br>
            valueType : ${status.valueType}<br>
            editor : ${status.editor}<br>
        </spring:bind>
    </spring:nestedPath>
</div>
<hr>

</body>
</html>
