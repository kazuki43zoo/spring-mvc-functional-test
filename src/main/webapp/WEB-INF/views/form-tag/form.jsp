<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Home</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
<div id="wrapper">
    <form:form modelAttribute="sampleForm">
    </form:form>
</div>
</body>
</html>
