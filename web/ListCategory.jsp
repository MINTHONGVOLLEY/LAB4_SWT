<%-- 
    Document   : ListCategory
    Created on : May 19, 2024, 8:35:35 AM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="includes/head.jsp" %>
        <title>List-Cate</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-info">
            <a class="navbar-brand" href="home">Home</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-item nav-link" href="list-cate">List Category</a>
                    <a class="nav-item nav-link" href="AddCategory.jsp">Add Category</a>
                </div>
            </div>
        </nav>

        <div class="container mt-4">
            <h3 class="text-center mb-4">Category List</h3>
            <table class="table table-striped table table-bordered">
                <thead style="background: #566787">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Name</th>
                         <th scope="col">Status</th>
                        <th scope="col">Update</th>
                        <th scope="col">Delete</th>
                    </tr>
                </thead>
                <tbody style="background: #6dabe4">
                <c:forEach items="${cate}" var="o">
                    <tr>
                        <td>${o.categoryId}</td>
                        <td>${o.categoryName}</td>
                        <td>  <c:choose>
                                    <c:when test="${o.categoryStatus == 0}">
                                        Deactivate
                                    </c:when>
                                    <c:otherwise>
                                        Activate
                                    </c:otherwise>
                                </c:choose></td>
                        <td>
                            <a href="update-cate?categoryId=${o.categoryId}" class="btn btn-primary mr-2">Update</a>
                        </td>
                         <td>
                            <a href="delete-cate?categoryId=${o.categoryId}" class="btn btn-danger mr-2">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

</html>
