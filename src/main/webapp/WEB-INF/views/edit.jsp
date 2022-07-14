<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <script type="text/javascript">
            function OnSubmitForm()
            {
                let title = document.getElementById("title").value;
                document.getElementById("editForm").action = "${pageContext.request.contextPath}/books/edit/" + title;
                return true;
            }
        </script>
    </head>

    <body>
        <h1>Edit Book</h1>

        <c:if test="${not empty errorMsg}">
            <p><c:out value="${errorMsg}"/></p>
        </c:if>
        <form:form id="editForm" method="post" modelAttribute="editBook" onsubmit="return OnSubmitForm();">
            Search by Title: <input id = "title" type="text"/>
            New Title: <form:input path="title" type="text"/>

            <button type="submit">Edit</button>
        </form:form>
    </body>
</html>