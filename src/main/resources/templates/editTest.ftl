<#import "parts/common.ftl" as c>

<@c.page>
<form id="nameTest" action="/create" role="form" enctype="multipart/form-data" method="post">

    <a href="http://hijos.ru/nabor-formul-v-latex/">Введення формул в Latex</a>
    <h4>Тест</h4>

    <div class="row my-3 justify-content-start">
        <div class="col col-1 col-form-label">Назва:</div>

        <div class="col col-4">
            <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}"
                   id="testname" name="name" placeholder="Введіть назву теста"
                   value="<#if testt??>${testt.name}</#if>">
            <#if nameError??>
            <div class="invalid-feedback">
                ${nameError}
            </div>
        </#if>
    </div>

    <div class="col col-2 col-form-label offset-md-1">Ліміт часу:</div>
    <div class="col col-2"><input type="time" value="$(testt.time}" min="00:00" max="03:00" class="form-control" name="time" id="limit_time"></div>
    <div class="w-100 mb-3"></div>
    <div class="col col-1 col-form-label">Опис:</div>
    <div class="col col-4"><input type="text" class="form-control" id="description" name="description"
                                  placeholder="Введіть опис теста" value="${testt.description}"></div>
    <div class="col col-2 col-form-label offset-md-1">Кількість спроб:</div>
    <div class="col col-2">
        <input type="number" class="form-control ${(countTryError??)?string('is-invalid', '')}"  value="${testt.countTry}" name="countTry" min="0" id="count_test" data-toggle="tooltip" data-placement="top" title="0-Необмежена кількість спроб" required>
    </div>

    <div class="w-100 mb-5"></div>
    </div>


    <div class="btn-toolbar mb-5" role="toolbar" aria-label="Toolbar with button groups">

        <div class="btn-group mr-2 ask-list ask-btn-group" role="group" aria-label="First group">
            <#list answers as answer>
            <button type="button" class="btn btn-secondary btn-info" id="btn-${answer?index}">${answer?index+1}</button>
            </#list>
        </div>
        <button type="button" class="btn btn-info" id="add-ask">Додати питання</button>
    </div>

    <#list answers as answer>
    <div class="chg-area">
        <fieldset id="ask-${answer?index}">
            <h4>Питання №<b class="ask-num">${answer?index+1}</b></h4>
            <div class="row my-3 justify-content-start form-group">
                <label class="control-label col-1" for="typeAnswer">Тип питання</label>
                <div class="col col-md-4">
                    <select id="typeAnswer" name="cquesttype" class="form-control">
                        <option <#if quests[answer?index].type=='0'> selected </#if> value="0"  >1 правильна відповідь</option>
                        <option <#if quests[answer?index].type=='1'> selected </#if> value="1"  >Декілька правильних відповідей</option>
                        <option <#if quests[answer?index].type=='2'> selected </#if> value="2"  >Введення відповіді з клавіатури</option>
                        <option <#if quests[answer?index].type=='3'> selected </#if> value="3"  >Відповідність</option>
                    </select>
                </div>
                <div class="w-100 mb-2"></div>
                <div class="col col-1 col-form-label">Запитання:</div>
    <div class="col col-7"><textarea row="3" name="cquest" class="form-control" id="testnametc">${quests[answer?index].questionText} </textarea> </div>
                <div class="col col-1 ">Кількість балів:</div>
                <div class="col col-1"><input type="text" name="countPoint" class="form-control" id="countpp" placeholder="Кількість балів" value="${quests[answer?index].countPoint}"></div>
                <div class="w-100 mb-3"></div>
                <div class="col col-4" id="img1" ><input type="file" name="filename1"></div>
                <div class="w-100 mb-1"></div>
                <div class="col col-4" id="img2" ><input type="file" name="filename2" id="file"></div>
            </div>

            <h4>Відповіді </h4>
            <div class="jumbotron w-75 py-5">
                <#if quests[answer?index].type=="0">
                <div id="answerTest-0" class="ans-area ans-area-0">
                    <div class="d-flex flex-row-reverse mr-5">
                        <button type="button" class="btn btn-success add-ans">+</button>
                    </div>

                    <#list answer as ans>
                    <div class="row my-3 justify-content-start">
                        <label class="col col-1 col-form-label pl-5">
                        <class="col col-1 col-form-label pl-5">
                            <input class="form-check-input" type="radio" id="r-${answer?index}-${ans?index}" name="rg-${ans?index}" <#if ans.getCorrect()=='1'> checked </#if> value="${ans?index}" >

                        </label>
                        <div class="col col-4">
                            <input type="text" class="form-control" id="d1-${answer?index}-${ans?index}" value="${ans.answerText}" name="canswer" placeholder="Введіть відповідь" required>
                        </div>
                        <div class="col col-1 ml-4">
                            <button type="button" class="btn btn-danger del-ans">Видалити</button>
                        </div>
                    </div>
                    </#list>
                </div>
                </#if>

                <#if quests[answer?index].type=="1">
                <div id="answerTest-1" class="ans-area ans-area-1">
                    <div class="d-flex flex-row-reverse mr-5">
                        <button type="button" class="btn btn-success add-ans">+</button>
                    </div>
                    <#list answer as ans>
                    <div class="row my-3 justify-content-start">
                        <label class="col col-1 col-form-label pl-5">
                            <input class="form-check-input" type="checkbox" name="canswercor" id="ch-${answer?index}-${ans?index}" <#if ans.getCorrect()=='1'> checked </#if> value="${ans?index}"  >
                        </label>
                        <div class="col col-4">
                            <input type="text" class="form-control" id="d2-${answer?index}-${ans?index}" name="canswer" value="${ans.answerText}" placeholder="Введіть відповідь" required>
                        </div>
                        <div class="col col-1 ml-4">
                            <button type="button" class="btn btn-danger del-ans">Видалити</button>
                        </div>
                    </div>
                    </#list>
                </div>
                </#if>

                <#if quests[answer?index].type=="2">
                <div id="answerTest-2" class="ans-area ans-area-2">

                    <#list answer as ans>
                    <div class="row my-3 justify-content-start">
                        <div class="col col-8">
                            <input type="text" class="form-control" id="onl-${answer?index}" name ="canswer" value="${ans.answerText}" placeholder="Введіть відповідь" required>
                        </div>
                    </div>
                    </#list>
                </div>
                </#if>

                <#if quests[answer?index].type=="3">
                <div id="answerTest-3" class="ans-area ans-area-3">
                    <div class="d-flex flex-row-reverse mr-5">
                        <button type="button" class="btn btn-success add-ans">+</button>
                    </div>
                    <#list answer as ans>
                    <div class="row my-3 justify-content-start">
                        <div class="col col-4">
                            <input type="text" class="form-control" id="p1-${answer?index}-${ans?index}" value="${ans.answerText}" name ="canswer" placeholder="Введіть відповідь">
                        </div>
                        <div class="col col-4">
                            <input type="text" class="form-control" id="p2-${answer?index}-${ans?index}" value="${ans.conformity}" name ="canswer" placeholder="Введіть відповідь">
                        </div>
                        <div class="col col-1 ml-4">
                            <button type="button" class="btn btn-danger del-ans">Видалити</button>
                        </div>
                    </div>
                    </#list>

                </div>
                </#if>
            </div>
            <input type="hidden" name="canswer" value="**/**" />
            <input type="hidden" name="canswercor" value="**/**" />
        </fieldset>
    </div>
</#list>

    <button class="btn btn-primary mb-3" type="submit">Зберегти тест</button>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>

</form>

<script src="/static/edit.js"></script>

</@c.page>
