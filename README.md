# Time Converter Java Web-Application

Это веб-приложение представляет собой конвертер времени, разработанный на Java с использованием Spring Boot Framework.

## Описание

Приложение позволяет конвертировать время из одного часового пояса в другой. В основе реализации лежит архитектурный паттерн MVC (Model-View-Controller), что обеспечивает четкое разделение бизнес-логики, представления и управления веб-запросами.

## Структура проекта

Проект состоит из двух основных классов:

- **TimeConverterApplication**: Этот класс является точкой входа в приложение Spring Boot. Он содержит метод `main`, который запускает приложение.
- **TimeConverterController**: Этот класс является контроллером Spring MVC, который обрабатывает HTTP-запросы, связанные с конвертацией времени. Он содержит метод для конвертации времени.

## Требования

- Java 21
- Spring Boot 3.x.x
- Maven 3.x.x

## Запуск приложения

1. **Клонирование репозитория**: Клонируйте репозиторий с проектом на свой локальный компьютер. Если вы используете Git, вы можете сделать это с помощью команды `git clone`.
2. **Сборка и запуск приложения**: Перейдите в директорию вашего проекта с помощью команды `cd`. Затем вы можете собрать и запустить свое приложение с помощью команды `mvn spring-boot:run` в терминале.
3. **Проверка работы приложения**: После успешного запуска приложения вы можете проверить его работу, открыв веб-браузер и перейдя по адресу `http://localhost:8080/convert?timeInSeconds=1609459200`. Вы должны увидеть время в текущем часовом поясе и GMT в формате JSON.