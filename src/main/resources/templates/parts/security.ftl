<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
    >
<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        isAdmin = user.isAdmin()
        isSign=true
    >
<#else>
    <#assign
        name = "Гість"
        isSign=false
        isAdmin=false
    >
</#if>