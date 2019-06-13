<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div class="mb-1">Реєстрація студента</div>
    ${message?ifExists}
    <@l.registration "/registration" />
</@c.page>