<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
    <body>
        <h1>Remove Book</h1>

        <c:if test="${not empty errorMsg}">
            <p><c:out value="${errorMsg}"/></p>
        </c:if>
        <form:form method="post" modelAttribute="removeBook" action="${pageContext.request.contextPath}/books/remove">
        <form:input path="title" type="text"/>
            <button type="submit">Remove</button></form:form>
    </body>
</html>