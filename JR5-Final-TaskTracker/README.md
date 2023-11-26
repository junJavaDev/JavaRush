## [REST API](http://localhost:8080/doc)

## Концепция:
- Spring Modulith
  - [Spring Modulith: достигли ли мы зрелости модульности](https://habr.com/ru/post/701984/)
  - [Introducing Spring Modulith](https://spring.io/blog/2022/10/21/introducing-spring-modulith)
  - [Spring Modulith - Reference documentation](https://docs.spring.io/spring-modulith/docs/current-SNAPSHOT/reference/html/)

```
  url: jdbc:postgresql://localhost:5432/jira
  username: jira
  password: JiraRush
```
- Есть 2 общие таблицы, на которых не fk
  - _Reference_ - справочник. Связь делаем по _code_ (по id нельзя, тк id привязано к окружению-конкретной базе)
  - _UserBelong_ - привязка юзеров с типом (owner, lead, ...) к объекту (таска, проект, спринт, ...). FK вручную будем проверять

## Аналоги
- https://java-source.net/open-source/issue-trackers

## Тестирование
- https://habr.com/ru/articles/259055/

**Список выполненных задач:**</br>
- Разобраться со структурой проекта (onboarding).</br>
- Удалить социальные сети: vk, yandex. 
- Вынести чувствительную информацию (логин, пароль БД, идентификаторы для OAuth регистрации/авторизации, настройки почты) в отдельный проперти файл. Значения этих проперти должны считываться при старте сервера из переменных окружения машины.
- Переделать тесты так, чтоб во время тестов использовалась in memory БД (H2), а не PostgreSQL. Для этого нужно определить 2 бина, и выборка какой из них использовать должно определяться активным профилем Spring. 
- Написать тесты для всех публичных методов контроллера `ProfileRestController`. 
- Добавить новый функционал: добавления тегов к задаче. Фронт делать необязательно. 
- Добавить возможность подписываться на задачи, которые не назначены на текущего пользователя. (Рассылку уведомлений/письма о смене статуса задачи делать не нужно). 
- Написать `Dockerfile` для основного сервера 
- Написать `docker-compose` файл для запуска контейнера сервера вместе с БД и nginx. Для nginx используй конфиг-файл `config/nginx.conf`. При необходимости файл конфига можно редактировать. 
- Добавить локализацию на английском языке. 
- Реализовать бэклог (backlog) – полный список задач (с пейджингом), которые должны быть выполнены и еще не относятся ни к какому спринту. (бек + фронт)

**Дополнительно:**

- Добавил профиль dev - запуск приложения с h2 бд в памяти и сервером для подключения к базе из idea. Переменные окружения в нём не используются.
- Добавил параметр app.email-confirmation-disabled для профилей dev и prod, для регистрации пользователя в приложении без проверки email.
- Исправил ошибку в nginx.conf, из-за которой не подгружались static файлы
- Добавил возможность регистрации по oauth2 в nginx, т.к. ключи привязаны к localhost:8080, пришлось его также занять nginx-ом
- Добавил возможность смены языков во фронте

**Список невыполненных задач:**</br>
- Добавить автоматический подсчет времени (сколько времени задача находилась в работе, тестировании и т. д.). Для этого нужно искать записи в таблице `activity` по задаче. Предопределённые показания:
  _Время окончания разработки минус время начала работы над задачей.
  Время конца тестирования минус время конца разработки._ 
- Переделать механизм распознавания «свой-чужой» между фронтом и беком с `JSESSIONID` на JWT. Из сложностей – тебе придётся переделать отправку форм с фронта, чтоб добавлять хедер аутентификации.</font>
