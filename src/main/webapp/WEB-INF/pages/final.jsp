<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<head lang="ru">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="${pageContext.request.contextPath}/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <title>Final</title>

</head>
<body>
<div>
    <div class="position-absolute top-0 start-50 translate-middle-x">
        <div class="p-3 mb-2 bg-primary text-white" >
            <h1>${text}</h1>
        </div>
    </div>
    <div class="position-absolute top-50 start-50 translate-middle">

        <br><br>
        <form action="" method="post">
            <input type="hidden" name="nextQuestionId" value="0">
            <button type="submit" class="btn btn-primary">Играть снова</button>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrap.js"></script>
</body>
</html>
