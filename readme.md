<h1># Module 1.2 Exercise 2</h1>

<h2>## Задание</h2>

<p>Необходимо реализовать консольное CRUD приложение, которое имеет следующие сущности:
    <ul>
        <li>Developer</li>
        <li>Skill</li>
        <li>Account</li>
        </ul>
</p>
<p>
    Developer содержит Set skills + Account account
</p>
<p>
    В качестве хранилища данных необходимо использовать текстовые файлы:
<ul>
    <li> developers.txt</li>
    <li>skills.txt</li>
    <li>accounts.txt</li>
</ul>
</p>
<p>
    Пользователь в консоли должен иметь возможность создания, получения, редактирования и удаления данных.

    Слои:
    <ul>
    <li>model - POJO клаcсы</li>
    <li>repository - классы, реализующие доступ к текстовым файлам</li>
    <li>controller - обработка запросов от пользователя</li>
    <li>view - все данные, необходимые для работы с консолью</li>
    </ul>
</p>
