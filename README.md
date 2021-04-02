# 2009 2671 SpotrLab
Мини фитнес-приложение

Общие требования. 
- В рамках выполнения контрольной работы должно быть разработано web-приложение с серверной частью на языке Java(Servlets либо Spring) c использованием, HTML,JSP(либо Freemarker или Thymeleaf), опционально использование CSS, Javascript. 
- В качестве контейнера рекомендуется использовать Apache Tomcat. 
- В качестве БД рекомендуется использовать MySQL. 
- Сборка и размещение приложения должно выполняться с использованием Maven скрипта. 

Хранение и доступ к данным. 
- Данные размещаются в базе данных. 
- Доступ к данным осуществляется с использованием JDBC драйвера, поставляемым производителем СУБД. База данных должна состоять из 5 таблиц(минимум). 
- Предметная область для БД выбирается самостоятельно и согласовывается.
- Доступ к приложения к БД осуществлять через JDBC API либо ORM(Hibirnate).
- Необходимо реализовать CRUD (Create,Read,Update,Delete)  операции минимум для нескольких таблицы и отображение остальных таблиц.

Интерфейс
- Интерфейс приложения должен состоять из нескольких веб-страниц и предоставлять возможность просмотра, добавления, изменения и удаления данных. 
- Плюсом будет кастомизация веб-страниц посредствам CSS, Bootstrap.

Примечание: 
- Веб-приложение должно быть связано со спортом, фитнесом для человека(к примеру, упражения, которые он делал, калории и прочее
