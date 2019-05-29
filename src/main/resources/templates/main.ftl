<#import "parts/common.ftl" as c>
<#import "parts/isAdmin.ftl" as a>
<@c.page>

    <@a.adm>
        <a href="/create" class="btn btn-primary active mb-3" role="button" aria-pressed="true">Створити тест</a>
    </@a.adm>


<div>
    <form method="post" action="/main" enctype="multipart/form-data">
        <input type="text" name="question" placeholder="Введите вопрос" />
        <input type="text" name="answer" placeholder="Введите ответ">
        <input type="file" name="file">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Добавить</button>
    </form>
</div>

<div>Список сообщений</div>
<form method="get" action="/main">
    <input type="text" name="filter" value="${filter?ifExists}">
    <button type="submit">Найти</button>
</form>

</@c.page>