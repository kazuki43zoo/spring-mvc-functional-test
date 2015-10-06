<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
</head>

<c:set var="userId">
    A0000001
</c:set>

<a href="<spring:url value='/menu' />">メニューへ</a>


<spring:url value="/users/{userId}" var="userUrl">
    <spring:param name="userId" value="${userId}"/>
</spring:url>

<a href="${userUrl}"><c:out value="${userId}"/></a>

<spring:url value="/users" var="userUrl">
    <spring:param name="userId" value="${userId}"/>
</spring:url>

<a href="${userUrl}"><c:out value="${userId}"/></a>



<a href="${spring:mvcUrl('MC#view').build()}">メニューへ</a>
<a href="${spring:mvcUrl('UC#viewDetailWithPathVariable').arg(0, userId).build()}"><c:out value="${userId}"/></a>
<%--<a href="${spring:mvcUrl('TC#viewDetail').arg(0, now).build()}">--%>
    <%--<fmt:parseDate value="${now}" pattern="yyyy-MM-dd" var="parsedNowDate"/>--%>
    <%--<fmt:formatDate value="${parsedNowDate}" pattern="yyyy年M月"/>--%>
<%--</a>--%>
<a href="${spring:mvcUrl('TC#viewDetail').arg(0, now).build()}">当月</a>

</body>
</html>
