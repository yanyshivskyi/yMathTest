<#import "parts/common.ftl" as c>

<@c.page>
<form id="nameTest" action="/create" role="form" enctype="multipart/form-data" method="post">

    <h4>Тест</h4>

    <div class="row my-3 justify-content-start">
        <div class="col col-1 col-form-label">Назва:</div>
        <div class="col col-4"><input type="text" class="form-control" id="testname" name="textTest"
                                      placeholder="Введіть назву теста"></div>
        <div class="col col-2 col-form-label offset-md-1">Ліміт часу:</div>
        <div class="col col-2"><input type="time" value="00:15" min="00:00" max="03:00" class="form-control" name="lim_time" id="limit_time"></div>
        <div class="w-100 mb-3"></div>
        <div class="col col-1 col-form-label">Опис:</div>
        <div class="col col-4"><input type="text" class="form-control" id="description" name="descrip"
                                      placeholder="Введіть опис теста"></div>
        <div class="col col-2 col-form-label offset-md-1">Кількість спроб:</div>
        <div class="col col-2"><input type="text" class="form-control" name="count_tst" id="count_test"></div>
        <div class="w-100 mb-5"></div>
    </div>

    <div class="btn-toolbar mb-5" role="toolbar" aria-label="Toolbar with button groups">
        <div class="btn-group mr-2 ask-list ask-btn-group" role="group" aria-label="First group">
            <button type="button" class="btn btn-secondary btn-info" id="btn-0">1</button>
        </div>
        <button type="button" class="btn btn-info" id="add-ask">Додати питання</button>
    </div>
    <div class="chg-area">
        <fieldset id="ask-0">
            <h4>Питання №<b class="ask-num">1</b></h4>
            <div class="row my-3 justify-content-start form-group">
                <label class="control-label col-1" for="typeAnswer">Тип питання</label>
                <div class="col col-md-4">
                    <select id="typeAnswer" name="cquesttype" class="form-control">
                        <option value="0" selected>1 правильна відповідь</option>
                        <option value="1">Декілька правильних відповідей</option>
                        <option value="2">Введення відповіді з клавіатури</option>
                        <option value="3">Відповідність</option>
                    </select>
                </div>
                <div class="w-100 mb-2"></div>
                <div class="col col-1 col-form-label">Запитання:</div>
                <div class="col col-4"><input type="text" name="cquest" class="form-control" id="testnametc" placeholder="Введіть запитання"></div>
                <div class="w-100 mb-3"></div>
                <div class="col col-4" id="img1"><input type="file"></div>
                <div class="w-100 mb-1"></div>
                <div class="col col-4" id="img2"><input type="file" id="file"></div>
            </div>

            <h4>Відповіді </h4>
            <div class="jumbotron w-75 py-5">
                <div id="answerTest-0" class="ans-area ans-area-0">
                    <div class="d-flex flex-row-reverse mr-5">
                        <button type="button" class="btn btn-success add-ans">+</button>
                    </div>

                    <div class="row my-3 justify-content-start">
                        <label class="col col-1 col-form-label pl-5">
                            <input class="form-check-input" type="radio" id="r-0-0" name="rg-0" value="0" checked>
                        </label>
                        <div class="col col-4">
                            <input type="text" class="form-control" id="d1-0-0" name="canswer" placeholder="Введіть відповідь">
                        </div>
                        <div class="col col-1 ml-4">
                            <button type="button" class="btn btn-danger del-ans">Видалити</button>
                        </div>
                    </div>
                </div>

                <div id="answerTest-1" class="ans-area ans-area-1">
                    <div class="d-flex flex-row-reverse">
                        <button type="button" class="btn btn-secondary add-ans">+</button>
                    </div>
                </div>

                <div id="answerTest-2" class="ans-area ans-area-2">
                </div>

                <div id="answerTest-3" class="ans-area ans-area-3">
                    <div class="d-flex flex-row-reverse">
                        <button type="button" class="btn btn-secondary add-ans">+</button>
                    </div>
                </div>
            </div>
            <input type="hidden" name="canswer" value="**/**" />
            <input type="hidden" name="canswercor" value="**/**" />
        </fieldset>
    </div>


        <button class="btn btn-primary mb-3" type="submit">Зберегти тест</button>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>

</form>
<script src="/static/create.js"></script>
</@c.page>



