<#import "parts/common.ftl" as c>

<@c.page>
Редагування прав користувача

<form action="/user" method="post">
    <input type="text" name="username" value="${user.username}" readonly>
    <#list roles as role>
    <div>
        <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
    </div>
</#list>
<input type="hidden" value="${user.id}" name="us_id">
<input type="hidden" value="${_csrf.token}" name="_csrf">
<button class="btn btn-primary" type="submit">Зберегти</button>
</form>
</@c.page>