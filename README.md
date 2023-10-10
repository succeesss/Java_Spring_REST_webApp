# Java_Spring_REST_webApp
Полноценное веб-приложение "Система онлайн-опросов"

Курсовая работа по дисциплине "Современные технологии программирования" 2 курс.
Реализована авторизация, визуализация, и всё что нужно для работы сайта-опросника.

Постановка задачи была следующая:\
• Нужно использовать ORM для взаимодействия с базой данных.  \
• Должно быть реализовано добавление, редактирование и удаление 
объектов в базе данных. \
• Программа должна иметь функцию сортировки и фильтрации записей. \
• Необходимо создать страницу “Об авторе”. \
• Реализовать полезные функции, например, визуализация данных в виде
диаграммы.\
• Исключить возможность аварийного завершения программы.\
• Программа должна иметь полезные комментарии, с помощью которых 
при помощи инструмента JavaDoc можно сгенерировать составляемую 
документацию.\
• Клиент должен иметь графический интерфейс.\
• С помощью запросов к API, клиент должен соединяться с сервером.\
• Сервер должен обрабатывать информацию, полученную от клиента, 
хранить ее в базе данных и осуществлять запросы к ней.


Данный проект использует Spring Security для настройки авторизации и 
аутентификации приложения. Поэтому программа имеет следующие 
возможности:\
• Аутентификация - Spring Security предоставляет различные способы 
аутентификации пользователя, такие как базы данных, LDAP, 
OpenID, OAuth и т. д.\
• Авторизация - Spring Security позволяет определить права доступа 
пользователя к различным ресурсам в приложении.\
• Фильтры безопасности - Spring Security обеспечивает механизмы 
проверки безопасности на основе фильтров, которые обрабатывают 
каждый запрос в приложении.\
• Защита CSRF - Spring Security включает в себя механизм защиты от 
атак CSRF (межсайтовая подделка запросов).\
• Защита XSS - Spring Security позволяет защитить приложение от атак 
XSS (межсайтовые скрипты)


Сервер включает в себя компоненты:\
• Spring Web - фреймворк для разработки веб-приложений при помощи 
Spring MVC. По умолчанию Spring Web использует сервер Apache 
Tomcat, что обеспечивает быстрый и стабильный запуск приложений. 
Model-View-Controller (MVC) является основной архитектурой для 
разработки приложений в Spring Web. Она разделяет приложение на 
три основных компонента: модель, представление и контроллер. 
Данный подход облегчает разработку, тестирование и поддержку вебприложений.\
• Hibernate используется в Spring Boot для работы с базами данных. 
Hibernate является фреймворком для объектно-реляционного 
отображения (ORM), который предоставляет удобный способ 
работать с базами данных, используя объекты Java. Spring Boot
интегрирует Hibernate для упрощения настройки и использования 
ORM в приложениях. Hibernate позволяет создавать, изменять, 
удалять и извлекать данные из базы данных, а также выполнять 
сложные запросы и связывать объекты Java с таблицами базы данных.\
• Jakarta Persistence (ранее известный как Java Persistence API) 
используется в Spring Boot для работы с объектно-реляционным 
отображением (ORM) и управления постоянным состоянием 
объектов в базах данных.


