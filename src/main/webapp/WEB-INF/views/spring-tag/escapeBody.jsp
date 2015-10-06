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


<spring:escapeBody htmlEscape="true">${message}</spring:escapeBody><br>
<spring:escapeBody javaScriptEscape="true">${message}</spring:escapeBody><br>
<spring:escapeBody htmlEscape="false" javaScriptEscape="true">${message}</spring:escapeBody>
<script>
alert('<spring:escapeBody htmlEscape="false" javaScriptEscape="true">${message}</spring:escapeBody>');
</script>
<a onclick="return confirm('<spring:escapeBody htmlEscape="true" javaScriptEscape="true">${message}</spring:escapeBody>')" href="">閉じる</a>

<button onclick="return confirm(
        '<spring:escapeBody htmlEscape="true" javaScriptEscape="true">${message}</spring:escapeBody>')">
hasBinTag    終了
</button>

</body>
</html>
