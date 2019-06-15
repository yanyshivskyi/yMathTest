<#import "parts/common.ftl" as c>
<#import "parts/security.ftl" as sc>

<@c.page>

<table class="table table-bordered w-75">
    <thead>
    <tr>
        <th>Тест</th>
        <th>Логін</th>
        <th>Дата</th>
        <th>Кількість балів:</th>
        <th>Макс за тест:</th>
        <th>Оцінка(100б):</th>
    </tr>
    </thead>
    <tbody>
    <#list results as result>
    <#if sc.isAdmin==false && result.user.username==sc.name>
    <tr>
        <td>${result.test.name}</td>
        <td>${result.user.username}</td>
        <td>${result.myDate}</td>
        <td>${result.point}</td>
        <td>${maxPoint[result?index]}</td>
        <td>${result.point/maxPoint[result?index]*100}</td>
    </tr>
    </#if>
    <#if sc.isAdmin>
    <tr>
        <td>${result.test.name}</td>
        <td>${result.user.username}</td>
        <td>${result.myDate}</td>
        <td>${result.point}</td>
        <td>${maxPoint[result?index]}</td>
        <td>${result.point / maxPoint[result?index] *100 }</td>
    </tr>
    </#if>
<#else>
    Жодного тесту не було пройдено
</#list>
</tbody>
</table>

</@c.page>