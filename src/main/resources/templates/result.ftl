<#import "parts/common.ftl" as c>
<#import "parts/security.ftl" as sc>

<@c.page>
<br>

<#if sc.isAdmin>
<form method="get" action="/result" class="form-inline">
    <input type="text" id="filter1" name="filter1" class="form-control mr-2" value="${filter1?ifExists}" placeholder="Введіть назву теста">
    <input type="text" id="filter2" name="filter2" class="form-control mr-2" value="${filter2?ifExists}" placeholder="Введіть логін">
    <input type="text" id="filter3" name="filter3" class="form-control mr-2" value="${filter3?ifExists}" placeholder="Введіть групу">
    <button type="submit" class="btn btn-primary ml-1">Пошук</button>
    <button id="res" class="btn btn-primary ml-1">Скинути фільтр</button>
</form>
<br>
</#if>

<table class="table table-bordered w-100">
    <thead>
    <tr>
        <th>Тест</th>
        <th>Логін</th>
        <th>Група</th>
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
        <td>${result.user.groupst}</td>
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
        <td>${result.user.groupst}</td>
        <td>${result.myDate}</td>
        <td>${result.point}</td>
        <td>${maxPoint[result?index]}</td>
        <td>${result.point / maxPoint[result?index] *100 }</td>
    </tr>
    </#if>
<#else>
<td colspan="7">Жодного тесту не було знайдено</td>
</#list>
</tbody>
</table>


<script type="text/javascript" src="/static/res.js"></script>
</@c.page>