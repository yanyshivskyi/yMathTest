<#import "parts/common.ftl" as c>

<@c.page>
    <h4>Тест</h4>

    <form id="nameTest" action="/create" role="form" enctype="multipart/form-data" method="post">
        <div class="row my-3 justify-content-start">
            <div class="col col-1 col-form-label">Назва:</div>
            <div class="col col-4"><input type="text" class="form-control" id="testname" name="textTest"
                                          placeholder="Введіть назву теста"></div>
            <div class="col col-2 col-form-label offset-md-1">Ліміт часу:</div>
            <div class="col col-2"><input type="time" class="form-control" id="limit_time"></div>
            <div class="w-100 mb-3"></div>
            <div class="col col-1 col-form-label">Опис:</div>
            <div class="col col-4"><input type="text" class="form-control" id="description"
                                          placeholder="Введіть опис теста"></div>
            <div class="col col-2 col-form-label offset-md-1">Кількість спроб:</div>
            <div class="col col-2"><input type="text" class="form-control" id="count_test"></div>
            <div class="w-100 mb-5"></div>
        </div>

        <div class="btn-toolbar mb-5" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group mr-2 ask-list ask-btn-group" role="group" aria-label="First group">
                <button type="button" class="btn btn-secondary btn-info" id="btn-0">1</button>
            </div>
        </div>
        <div class="chg-area">
            <fieldset id="ask-0">
                <h4>Питання №<b class="ask-num">1</b></h4>
                <div class="row my-3 justify-content-start form-group">
                    <label class="control-label col-1" for="typeAnswer">Тип питання</label>
                    <div class="col col-md-4">
                        <select id="typeAnswer" class="form-control">
                            <option value="0" selected>1 правильна відповідь</option>
                            <option value="1">Декілька правильних відповідей</option>
                            <option value="2">Введення відповіді з клавіатури</option>
                            <option value="3">Відповідність</option>
                        </select>
                    </div>
                    <div class="w-100 mb-2"></div>
                    <div class="col col-1 col-form-label">Запитання:</div>
                    <div class="col col-4"><input type="text" class="form-control" id="testnametc"
                                                  placeholder="Введіть запитання"></div>
                    <div class="w-100 mb-3"></div>
                    <div class="col col-4" id="img1"><input type="file" name="file"></div>
                    <div class="w-100 mb-1"></div>
                    <div class="col col-4" id="img2"><input type="file" name="file" id="file"></div>
                </div>

                <h4>Відповіді </h4>
                <div class="jumbotron">
                    <div id="answerTest-0" class="ans-area ans-area-0">
                        <div class="d-flex flex-row-reverse">
                            <button type="button" class="btn btn-secondary add-ans">+</button>
                        </div>
                        <div class="row my-3 justify-content-start ans-0">
                            <label class="col col-1 col-form-label pl-5">
                                <input class="form-check-input" type="radio" name="rg-0" id="r-0-0" value="0" checked>
                            </label>
                            <div class="col col-4">
                                <input type="text" class="form-control" id="d1-0-0" placeholder="Введіть відповідь">
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
                        <div class="row my-3 justify-content-start ans-0">
                            <label class="col col-1 col-form-label pl-5">
                                <input class="form-check-input" type="checkbox" name="chg-0" id="ch-0-0" value="0" checked>
                            </label>
                            <div class="col col-4">
                                <input type="text" class="form-control" id="d2-0-0" placeholder="Введіть відповідь">
                            </div>
                            <div class="col col-1 ml-4">
                                <button type="button" class="btn btn-danger del-ans">Видалити</button>
                            </div>
                        </div>
                    </div>
                    <div id="answerTest-2" class="ans-area ans-area-2">
                        <div class="row my-3 justify-content-start">
                            <div class="col col-8">
                                <input type="text" class="form-control" id="onl-0" placeholder="Введіть відповідь">
                            </div>
                        </div>
                    </div>
                    <div id="answerTest-3" class="ans-area ans-area-3">
                        <div class="d-flex flex-row-reverse">
                            <button type="button" class="btn btn-secondary add-ans">+</button>
                        </div>
                        <div class="row my-3 justify-content-start ans-0">
                            <div class="col col-4">
                                <input type="text" class="form-control" id="p1-0-0" placeholder="Введіть відповідь">
                            </div>
                            <div class="col col-4">
                                <input type="text" class="form-control" id="p2-0-0" placeholder="Введіть відповідь">
                            </div>
                            <div class="col col-1 ml-4">
                                <button type="button" class="btn btn-danger del-ans">Видалити</button>
                            </div>
                        </div>
                    </div>
                </div>
            </fieldset>
        </div>
        <button type="button" class="btn btn-info" id="add-ask">Додати питання</button>
        <button type="submit">Save</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>


    <script type="text/javascript">
        //ограничиваем весь код в одной области видимости
        (() => {
            //скрытие невыбранных типов ответов
            $('#answerTest-1').hide();
        $('#answerTest-2').hide();
        $('#answerTest-3').hide();

        $('#img2').hide();

        //номер текущего вопроса
        let curAsk = 0;
        //выбранный тип ответов
        let curAnsType = 0;
        //количество ответов
        let curAnsCount = 1;
        //нумерация ответов (сделано, чтобы не было пролем с ид-инпутов при удалении и добавлении)
        let curAnsNumb = 0;
        //общее к-во вопросов
        let allAskCount = 1;

        //класс, ответственный за запоминание общих данных вопросов (для перехода по вкладкам)
        function Ask() {
            let askArr = [];

            this.pushAsk = function (id, type = 0, ansCount = 1, ansNum=0) {
                //если записан такой вопрос
                if(askArr[id]){
                    //обновляем данные
                    askArr[id].type = type;
                    askArr[id].ansCount = ansCount;
                    askArr[id].ansNum = ansNum;
                } else {
                    //создаём новый элемент
                    askArr.push({type: type, ansCount: ansCount, ansNum: ansNum});
                }
            };
            //получаем данные по впоросу с заданым номером
            this.getAsk = function (num) {
                return askArr[num];
            }
        }
        //объект для работы со списком вопросов
        askArr = new Ask();

        // создание нового блока для ответа
        const createAskBlock = function (typeAns, reset=false) {
            let block = '';
            if(!reset && typeAns!==2){
                curAnsNumb++;
            }
            switch (typeAns) {
                case 0: {
                    block = `
                <div class="row my-3 justify-content-start">
                    <label class="col col-1 col-form-label pl-5">
                        <input class="form-check-input" type="radio" name="rg-${curAsk}" id="r-${curAsk}-${curAnsNumb}"
                        value="${curAnsNumb}">
                    </label>
                    <div class="col col-4">
                        <input type="text" class="form-control" id="d1-${curAsk}-${curAnsNumb}" placeholder="Введіть відповідь">
                    </div>
                    <div class="col col-1 ml-4">
                        <button type="button" class="btn btn-danger del-ans">Видалити</button>
                    </div>
                </div>`;
                    break;
                }
                case 1: {
                    block = `
                <div class="row my-3 justify-content-start">
                    <label class="col col-1 col-form-label pl-5">
                        <input class="form-check-input" type="checkbox" name="chg-${curAsk}" id="ch-${curAsk}-${curAnsNumb}" value=${curAnsNumb}>
                    </label>
                    <div class="col col-4">
                        <input type="text" class="form-control" id="d2-${curAsk}-${curAnsNumb}" placeholder="Введіть відповідь">
                    </div>
                    <div class="col col-1 ml-4">
                        <button type="button" class="btn btn-danger del-ans">Видалити</button>
                    </div>
                </div>`;
                    break;
                }
                case 2: {
                    block = `
                <div class="row my-3 justify-content-start">
                        <div class="col col-8">
                            <input type="text" class="form-control" id="onl-${curAsk}" placeholder="Введіть відповідь">
                        </div>
                    </div>`;
                    break;
                }
                case 3: {
                    block = `<div class="row my-3 justify-content-start">
                        <div class="col col-4">
                            <input type="text" class="form-control" id="p1-${curAsk}-${curAnsNumb}" placeholder="Введіть відповідь">
                        </div>
                        <div class="col col-4">
                            <input type="text" class="form-control" id="p2-${curAsk}-${curAnsNumb}" placeholder="Введіть відповідь">
                        </div>
                        <div class="col col-1 ml-4">
                            <button type="button" class="btn btn-danger del-ans">Видалити</button>
                        </div>
                    </div>`;
                    break;
                }
            }
            if(!reset && typeAns!==2){
                curAnsCount++;
            }
            return block;
        };

        // сброс блока ответов к начальному состоянию
        const resetAnsBlock = function (ansType) {
            $(`#ask-${curAsk} .ans-area-${ansType} .row`).remove();
            $(`#ask-${curAsk} .ans-area-${ansType}`).append(createAskBlock(ansType, true));
        };

        // прослушка события изменения типа события
        $('.chg-area').on('change', '#typeAnswer', function () {
            $(`#ask-${curAsk} #answerTest-${curAnsType}`).hide();
            //сброс данных блока
            curAnsNumb = 0;
            resetAnsBlock(curAnsType);
            //Можно добавить проверку на наличие данных в блоке и вывести предупреждение, что данные будут стерты
            curAnsType = $(this).find(":selected").index();
            let currentAnsBlock = $(`#ask-${curAsk} #answerTest-${curAnsType}`);
            currentAnsBlock.show();
            curAnsCount = currentAnsBlock.find('.row').length;


        });

        // прослушка события добавления ответа
        $('.chg-area').on('click', '.add-ans', function () {
            if (curAnsCount !== 10) {
                $(`#ask-${curAsk} .ans-area-${curAnsType}`).append(createAskBlock(curAnsType));
            }
        });

        // прослушка события удаления ответа
        $('.chg-area').on('click', '.del-ans', function () {
            curAnsCount--;
            this.closest(".row").remove();
        });

        // прослушка события изменения вкладки вопроса
        $('.ask-btn-group').on('click', 'button', function () {
            if(curAsk === allAskCount-1){
                askArr.pushAsk(curAsk, curAnsType, curAnsCount, curAnsNumb)
            }
            let selId = +this.id.split('-')[1];
            if (curAsk !== selId) {
                $(`#btn-${curAsk}`).removeClass('btn-info');
                $(`#btn-${selId}`).addClass('btn-info');


                let ask = askArr.getAsk(selId);
                $(`fieldset#ask-${selId}`).show();
                $(`fieldset#ask-${curAsk}`).hide();
                curAsk = selId;
                curAnsType = ask.type;
                curAnsCount = ask.ansCount;
                curAnsNumb = ask.ansNum;
            }
        });

        // добавление вопроса
        $('#add-ask').on('click', function () {
            let nameInput = $('#testnametc');
            let typeInput = $('#typeAnswer');
            let prevAsk = curAsk;

            askArr.pushAsk(curAsk, curAnsType, curAnsCount, curAnsNumb);

            curAsk = allAskCount;
            allAskCount++;

            curAnsType = 0;
            curAnsCount = 0;
            curAnsNumb = 0;

            // create new button with ask number
            let btnPrev = $(`#btn-${prevAsk}`);
            let btnCopy = btnPrev.clone();
            btnCopy.text(curAsk + 1);
            btnCopy.attr('id', 'btn-'+curAsk);
            $('.ask-list').append(btnCopy);
            btnPrev.removeClass('btn-info');

            setTimeout(function (){
                let fieldset = $(`.chg-area #ask-${prevAsk}`).clone();
                fieldset.attr('id', 'ask-' + curAsk);
                fieldset.appendTo('.chg-area');
            }, 0);

            setTimeout(function () {
                resetAnsBlock(0);
                resetAnsBlock(1);
                resetAnsBlock(2);
                resetAnsBlock(3);
                curAnsCount++;
            }, 4);

            setTimeout(function () {
                $(`#ask-${curAsk} #answerTest-1`).hide();
                $(`#ask-${curAsk} #answerTest-2`).hide();
                $(`#ask-${curAsk} #answerTest-3`).hide();
                $(`#ask-${curAsk} .ask-num`).text(curAsk + 1);
                $(`fieldset#ask-${prevAsk}`).hide();
            }, 4);
        });
        })();
    </script>
</@c.page>