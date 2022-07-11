<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <html>
        <head>
            <title>Download Text Files</title>
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
        </head>
        <body class="container">
            <h1>Download Website Files</h1>

            <!-- error message -->
            <c:if test="${param.success}">
                <div class="alert alert-success">
                    Yay! You successfully downloaded the website.
                </div>
            </c:if>

            <c:if test="${param.error}">
                <div class="alert alert-danger">
                    ${param.description}
                </div>
            </c:if>

            <form action="/html" method="get">
                <div class="form-group">
                    <label for="file">File Name</label>
                    <input type="text" class="form-control" id="file"
                           name="url" placeholder="Enter URL">
                </div>
                <button type="submit" class="btn btn-primary">Download</button>
            </form>

            <div th:text="${websiteName}"></div>

            <a href="/report">View Reports of a given website!</a>
        </body>
    </html>