<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <body>
    <h1><b>Books</b></h1>

        <a href="<c:url value="/books/add" />">Add Books</a>
        <a href="<c:url value="/books/remove" />">Remove Books</a>
        <a href="<c:url value="/books/edit" />">Edit Books</a>

        <c:if test="${not empty books}">
            <table>
                <tr>
                    <th>Cover</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Rating</th>
                </tr>
                <c:forEach var="book" items="${books}">
                    <tr>
                        <td><img src="<c:out value="${book.cover}"/>" height="50px" width="50px"/></td>
                        <td><c:out value="${book.title}"/></td>
                        <td><c:out value="${book.author}"/></td>
                        <td><c:out value="${book.rating}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

    </body>
</html>