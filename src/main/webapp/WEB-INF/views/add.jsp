<!DOCTYPE HTML>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <body>
        <h1>Add Book</h1>

        <form:form method="post" modelAttribute="newBook" action="${pageContext.request.contextPath}/books/add">
            <form:label path="title">Title: </form:label>
            <form:input path="title" type="text"/>

            <form:label path="cover">Cover: </form:label>
            <form:input path="cover" type="text"/>

            <form:label path="author">Author: </form:label>
            <form:input path="author" type="text"/>

            <form:label path="rating">Rating: </form:label>
            <form:input path="rating" type="range" min="1" max="5" value="5"/>

            <button type="submit">Add</button>
        </form:form>
    </body>
</html>