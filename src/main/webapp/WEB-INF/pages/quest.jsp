<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<head lang="ru">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Quest</title>
    <link href="${pageContext.request.contextPath}/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container-fluid">
    <div>
        <div class="questions">
            <div class="row">
                <h1 class="display-6">${questionText}</h1>
            </div>
        </div>
    </div>
    <div class="answers">
        <div>
            <c:forEach items="${answers}" var="answer">
                <li>
                    <form action="${pageContext.request.contextPath}/quest" method="post">
                        <input type="hidden" name="nextQuestionId" value="${answer.getNextQuestionId()}"/>
                        <button type="submit">${answer.getText()}</button>
                    </form>
                </li>
            </c:forEach>
        </div>
    </div>
    <div>
        <div>
            <div id="statistics" class="position-absolute bottom-0 end-0">
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title">Статистика</h5>
                        <p class="card-text">Имя в игре: ${userName}</p>
                        <p class="card-text">IP address: <%=java.net.InetAddress.getLocalHost().getHostAddress()%></p>
                        <p class="card-text">Количество игр: ${countGames}</p>
                        <p class="card-text">Количество побед: ${countWin}</p>
                        <a href="${pageContext.request.contextPath}/../" class="card-link">Вернуться в начало</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrap.js"></script>
</body>
</html>
