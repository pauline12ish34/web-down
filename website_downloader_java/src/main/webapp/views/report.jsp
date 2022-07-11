<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Get Report</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
        <h1>Report</h1>

            <!-- error message -->
            <c:if test="${param.success}">
                <div class="alert alert-success">
                    Website found in DB.
                </div>
            </c:if>

            <c:if test="${param.error}">
                <div class="alert alert-danger">
                        ${param.description}
                </div>
            </c:if>

        <form action="/report" method="post">
            <div class="form-group">
            <input type="text" name="websiteName" placeholder="Website name" class="form-control">
            </div>
            <input type="submit" value="Submit" class="btn btn-primary">
        </form>

            <div>
                <p></p>
            </div>

            <c:if test="${not empty sessionScope['links']}">
                <table class="table mt-5">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Link Url</th>
                            <th scope="col">Total downloaded kilobytes</th>
                            <th scope="col">Total elapse time</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${sessionScope['links']}" var="link">
                        <tr>
                            <th scope="row">1</th>
                            <td>${link.linkName}</td>
                            <td>${link.totalDownloadedKilobytes}</td>
                            <td>${link.totalElapseTime}</td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </body>
</html>