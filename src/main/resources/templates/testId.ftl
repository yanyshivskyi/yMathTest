<#import "parts/security.ftl" as sc>
<#import "parts/common.ftl" as c>


<@c.page>
    <div id="myclass2">
    <#list answers as answer>
        <b>Питання ${answer?index + 1}.</b> <em> ${quests[answer?index].countPoint} бал(бали) </em>       ${quests[answer?index].questionText}
        <#if quests[answer?index].filename1!="">
        <img src="/img/${quests[answer?index].filename1}" class="rounded float-left" alt="Адаптивные изображения" width="245px">
        </#if>
        <#if quests[answer?index].filename2!="">
        <img src="/img/${quests[answer?index].filename2}" id="main-img" class="rounded float-rigth" alt="Адаптивные изображения" width="245px" ">
        </#if>
        <br>
        <input type="hidden" class="myclass1" form="endtest" id="typeq_${answer?index}" value="${quests[answer?index].getType()}" />
        <input type="hidden" class="myclass1" form="endtest" id="point_${answer?index}" value="${quests[answer?index].countPoint}" />
            <#list answer as ans>


                <#if quests[answer?index].type=="0">
                    <input type="hidden" class="myclass1" form="endtest" id="correct_${answer?index}_${ans?index}" value="${ans.getCorrect()}" />
                    <label class="col col-1 col-form-label pl-5" id="text0ans-${answer?index}-${ans?index}">
                        <input class="form-check-input" type="radio" id="r-${answer?index}-${ans?index}" name="rg-${answer?index}" value="${ans?index}">
                        ${ans.answerText}
                    </label>
                    <br>
                </#if>
                <#if quests[answer?index].type=="1">
<input type="hidden" class="myclass1" form="endtest" id="correct_${answer?index}_${ans?index}" value="${ans.getCorrect()}" />
                    <label class="col col-1 col-form-label pl-5" id="tc1h-${answer?index}-${ans?index}">
                        <input class="form-check-input" type="checkbox" id="c1h-${answer?index}-${ans?index}" name="chh-${answer?index}" value="${answer?index}"} >
                        ${ans.answerText}
                    </label>
                <br>
                </#if>

                <#if quests[answer?index].type=="2">
<input type="hidden" class="myclass1" form="endtest" id="correct_${answer?index}_${0}" value="${ans.answerText}" />
                    <div class="col col-8 mt-3">
                        <input type="text" value="" class="form-control" id="onl-${answer?index}" name ="canswer" placeholder="Введіть відповідь">
                    </div>
                <br>
                </#if>
            <#else>
                Вопросов нет!
            </#list>
    <br>
    <#else>
        Тест пуст
    </#list>

<label id="ff" value="">
</label>




<form id="endtest" action="/test/{id}" role="form" enctype="multipart/form-data" target="iframe1" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="hidden" name="mystr" id="mystrr" value="${sc.name}"/>
    <input type="hidden" name="fpoint" id="flpoint" value=""/>
    <nav class="navbar">
        <button class="btn btn-primary mb-3" id="enterTest" type="submit">Завершити тест</button>
    </nav>


</form>

<iframe name="iframe1" style="position: absolute; display:none"></iframe>
</div>
<script type="text/javascript" src="/static/complete.js"></script>
<script type="text/javascript" src="/static/jj.js"></script>
</@c.page>