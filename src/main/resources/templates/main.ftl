<#import "parts/common.ftl" as c>
<#import "parts/isAdmin.ftl" as a>
<@c.page>

    <@a.adm>
        <a href="/create" class="btn btn-primary active mb-3" role="button" aria-pressed="true">Створити тест</a>
    </@a.adm>

<#list tests as test>
<div>
    <div class="card w-75 mb-3">
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
            <a href="/test/${test.id}" class="btn btn-outline-secondary">Перейти до тесту</a>
        </div>
    </div>
</div>
</#list>

</@c.page>