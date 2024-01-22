<%@ include file="Common\header.jspf" %>
<body>
    <%@ include file="Common\navigation.jspf" %>
    <div class="container">
        <div><h1>Your todos are </h1></div>
        <table class = "table">
            <thead>
            <tr>
                <th>Description</th>
                <th>Target date</th>
                <th>Is done?</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${todos}" var="todo">
                <tr>
                    <td>${todo.description}</td>
                    <td>${todo.targetDate}</td>
                    <td>${todo.done}</td>
                    <td><a href="delete-todo?id=${todo.id}" class ="btn btn-warning">Delete</a></td>
                    <td><a href="update-todo?id=${todo.id}" class ="btn btn-success">Update</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="add-todo" class="btn btn-success">Add Todo</a>
    </div>
    <%@ include file="Common\footer.jspf" %>

