# Проект по автоматизации тестирования UI для сайта компании [Habr](https://habr.com/ru/)
<p align="center">
<img src="images/habr_ru.png">
</p>
🔎🎓💻 Хабр - это русскоязычный веб-сайт, представляющий собой платформу для публикации новостей, аналитических статей, мыслей и дискуссий в области информационных технологий, бизнеса и интернета. 

## Содержание:

- [Технологии и инструменты](#tools)
- [Тестовое покрытие](#cases)
- [Запуск тестов в Jenkins](#remoterun)
- [Allure отчёт](#report)
- [Уведомления в Telegram](#telegram)
- [Видео с примером запуска тестов в Selenoid](#video)


<a id="tools"></a>

## Технологии и инструменты:

<div align="center">
<a href="https://www.jetbrains.com/idea/"><img alt="InteliJ IDEA" height="50" src="images/icons/IntelliJ_IDEA.png" width="50"/></a>
<a href="https://github.com/"><img alt="GitHub" height="50" src="images/icons/GitHub.png" width="50"/></a>  
<a href="https://www.java.com/"><img alt="Java" height="50" src="images/icons/Java_logo.png" width="50"/></a>
<a href="https://gradle.org/"><img alt="Gradle" height="50" src="images/icons/Gradle.png" width="50"/></a>  
<a href="https://junit.org/junit5/"><img alt="JUnit 5" height="50" src="images/icons/JUnit5.png" width="50"/></a>
<a href="https://selenide.org/"><img alt="Selenide" height="50" src="images/icons/Selenide.png" width="50"/></a>
<a href="https://aerokube.com/selenoid/"><img alt="Selenoid" height="50" src="images/icons/Selenoid.png" width="50"/></a>
<a href="https://rest-assured.io/"><img alt="RestAssured" height="50" src="images/icons/RestAssured.png" width="50"/></a>
<a href="https://www.jenkins.io/"><img alt="Jenkins" height="50" src="images/icons/Jenkins.png" width="50"/></a>
<a href="https://github.com/allure-framework/"><img alt="Allure Report" height="50" src="images/icons/AllureReports.png" width="50"/></a>
<a href="https://qameta.io/"><img alt="Allure TestOps" height="50" src="images/icons/AllureTestOps.svg" width="50"/></a>
<a href="https://www.atlassian.com/software/jira"><img alt="Jira" height="50" src="images/icons/Jira.png" width="50"/></a>  
<a href="https://telegram.org/"><img alt="Telegram" height="50" src="images/icons/Telegram.png" width="50"/></a>
</div>

<p></p>

Автотесты для данного проекта написаны на <code>Java</code> с использованием фреймворка <code>Selenide</code>. При проектировании тестов применён паттерн <code>PageObject</code>.

В качестве фреймворка для запуска тестов используется <code>Junit5</code>, а в качестве сборщика проекта - <code>Gradle</code>.

Произведена настройка CI в <code>Jenkins</code>, при запуске прогонов из которого тесты выполняются в удалённом браузере в <code>Selenoid</code>.

По результатам каждого тестового прогона создаётся <code>Allure</code> отчёт для визуализации результатов прогона.

Для тест-менеджмента настроена интеграция с <code>Allure TestOps</code>, которая, в свою очередь, интегрирована с таск-трекером <code>Jira</code>

После прогона тестов <code>Telegram</code> бот присылает сообщение с информацией о прошедшем прогоне

<a id="cases"></a>

## Выполняемые проверки:
### Страница авторизации пользователя

✅ Проверка перехода на страницу приветствия при успешной авторизации

✅ Проверка получения сообщения об ошибке при непроставлении чекбокса 'Я не робот'

✅ Проверка получения сообщения об ошибке при попытке авторизации без логина

✅ Проверка получения сообщения об ошибке при попытке авторизации без пароля

✅ Проверка названия формы авторизации. Название

✅ Проверка наполнения формы авторизации. Поля

✅ Проверка наполнения формы авторизации. Выпадающий список

### Поиск статей

✅ Проверка, что при поиске по существующему значению, результат поиска не будет равен 0

✅ Проверка, что при поиске по ключевому слову, будут найдены определенные статьи

✅ Проверка, что при поиске по несуществующему значению, результат поиска будет равен 0



<a id="remoterun"></a>

## Запуск тестов в [Jenkins](https://jenkins.autotests.cloud/job/ForFinalProject/)
Для запуска тестов в Jenkins нужно нажать на кнопку Build With Parameters и настроить необходимые параметры

<p align="center">
<img src="images/запуск в дженкинс.png">
</p>

<a id="report"></a>

## [Allure отчёт](https://jenkins.autotests.cloud/job/C24-tarabne-maxidom_ui_tests/allure/)
### Графики

<p align="center">
<img src="images/результат прогона.png">
<img src="images/результат прогона 2.png">
</p>

### Тесты

<p align="center">
<img src="images/тесты.png">
<img src="images/упавший тест.png">
</p>

<a id="telegram"></a>

## Уведомление в Telegram

По результатам каждого прогона тестов в Jenkins отправляется сообщение в Telegram. Сообщение содержит информацию о прогоне, а также диаграмму со статистикой прохождения тестов.

<p align="center">
<img src="images/отчет в телеге.jpeg" width="400">
</p>

<a id="video"></a>

## Видео с примером запуска тестов в Selenoid
В Allure отчёте Получаем видео прохождения тестов:

<p align="center">
<img src="images/empty-result.gif" width="1000">
</p>

<p align="center">
<img src="images/key-search.gif" width="1000">
</p>
