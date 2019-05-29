<#include "security.ftl">
<#macro adm>
    <#if isAdmin>
        <#nested />
    </#if>
</#macro>