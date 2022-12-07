<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head lang="ru">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <title>Start</title>
</head>
<body>
<div>
    <div>
        <h1 class="lead mb-4"><%= "Пролог" %></h1>
        <div class="col-lg-6 mx-auto">
            <p class="lead mb-4">
                Ты стоишь в космическом порту и готов подняться на борт своего корабля.
                Разве ты не об этом мечтал? Стать капитаном галактического судна с экипажем,
                который будет совершать подвиги под твоим командованием.
                Так что вперед!
            </p>
        </div>


        <h1 class="lead mb-4"><%= "Знакомство с экипажем" %></h1>
        <div class="col-lg-6 mx-auto">
            <p class="lead mb-4">
                Когда ты поднялся на борт корабля, тебя поприветствовала девушка с черной папкой в руках
                Здравствуйте, командир! Я Зинаида, ваша помощница. Видите?
                Там в углу пьет кофе наш штурман - сержант Перегарный Штейф,
                под штурвалом спит бортмеханик - Черный Богдан,
                а фотографирует его Сергей Стальная пятка - наш навигатор.
                А как обращаться к вам?
            </p>
        </div>
    </div>
    <div>
        <form action="${pageContext.request.contextPath}/start" method="post">
            <div class="input-group mb-3">
                <span class="input-group-text" id="inputGroup-sizing-default">Ваше имя</span>
                <input id="name" type="text" name="userName" class="form-control" aria-label="10"
                       aria-describedby="inputGroup-sizing-default" required>
            </div>
            <button type="submit" class="btn btn-primary">Представиться</button>
        </form>
    </div>
</div>

<script src="${pageContext.request.contextPath}/bootstrap/bootstrap.js"></script>
</body>
</html>