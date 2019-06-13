<#import "parts/common.ftl" as c>

<@c.page>
<div class ="mb-3">
<h5> <a href="/registration" class="badge badge-primary">Зареєструвати студента</a> </h5>
</div>

Список зареєстрованих користувачів:

<table class="table table-bordered w-75">
    <thead>
    <tr>
        <th>Логін</th>
        <th>Група</th>
        <th>Ролі</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
    <#if user.username!='admin'>
    <tr>
        <td>${user.username}</td>
        <td>${user.groupst}</td>
        <td><#list user.roles as role>${role}<#sep>, </#list></td>
        <td><a href="/user/${user.id}">Редагувати</a></td>
    </tr>
    </#if>
    </#list>
    </tbody>
</table>
</@c.page>