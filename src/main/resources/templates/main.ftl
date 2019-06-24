<#import "parts/common.ftl" as c>
<#import "parts/isAdmin.ftl" as a>
<#import "parts/pager.ftl" as p>

<@c.page>

    <@a.adm>
        <a href="/create" class="btn btn-primary active mb-3" role="button" aria-pressed="true">Створити тест</a>
    </@a.adm>
<br>
<form method="get" action="/main" class="form-inline">
    <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Введіть назву">
    <button type="submit" class="btn btn-primary ml-2">Пошук</button>
</form>
<br>

<@p.pager url page/>

<div class="row my-3 justify-content-start">
<#list page.content as test>

<div class="col col-5">
    <div class="card w-100 mb-3">
        <div class="card-header">
            <h5>  ${test.name}</h5>
        </div>
        <div class="card-body">
            <div class="card-title mb-4">
                <i> ${test.description}</i>
            </div>
            <p class="card-text">Час проходження: ${test.time}</p>
            <p class="card-text">Кількість спроб: ${test.countTry}</p>
            <p class="card-text">Кількість питань: ${count_q[test?index]}</p>

            <#if (count_g[test?index]>0) >
            <p class="card-text">Спроб залишилося: ${count_g[test?index]}</p>
            <a href="/test/${test.id}" class="btn btn-outline-secondary">Перейти до тесту</a>
            </#if>
                <#if count_g[test?index]<0>
                    <p class="card-text">Необмежена кількість спроб</p>
                    <a href="/test/${test.id}" class="btn btn-outline-secondary">Перейти до тесту</a>
             </#if>

    <#if count_g[test?index]==0>
    <p class="card-text">Всі спроби вичерпано</p>
    </#if>
                <@a.adm>
                <a href="/edit/${test.id}" role="button" aria-pressed="true">Редагувати</a>
                </@a.adm>
    </div>
</div>
</div>






<#else>
    Тестів не знайдено
</#list>
</div>


</@p.pager url page />

</@c.page>