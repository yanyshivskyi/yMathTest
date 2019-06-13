//ограничиваем весь код в одной области видимости
        (() => {
            //скрытие невыбранных типов ответов
            $('#answerTest-1').hide();
        $('#answerTest-2').hide();
        $('#answerTest-3').hide();
        $('#img2').hide();
        //номер текущего вопроса
        let curAsk = 0; //
        //выбранный тип ответов
        let curAnsType = 0; //
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
                          <input class="form-check-input" type="radio" id="r-${curAsk}-${curAnsNumb}" name="rg-${curAsk}" value="${curAnsNumb}" ${!curAnsNumb && 'checked' } >

                    </label>
                    <div class="col col-4">
                        <input type="text" class="form-control" id="d1-${curAsk}-${curAnsNumb}" name="canswer" placeholder="Введіть відповідь">
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
                        <input class="form-check-input" type="checkbox" name="canswercor" id="ch-${curAsk}-${curAnsNumb}" value="${curAnsNumb}" ${curAnsNumb==0 ? 'checked' :''} >
                    </label>
                    <div class="col col-4">
                        <input type="text" class="form-control" id="d2-${curAsk}-${curAnsNumb}" name="canswer" placeholder="Введіть відповідь">
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
                            <input type="text" class="form-control" id="onl-${curAsk}" name ="canswer" placeholder="Введіть відповідь">
                        </div>
                    </div>`;
                    break;
                }
                case 3: {
                    block = `<div class="row my-3 justify-content-start">
                        <div class="col col-4">
                            <input type="text" class="form-control" id="p1-${curAsk}-${curAnsNumb}"  name ="canswer" placeholder="Введіть відповідь">
                        </div>
                        <div class="col col-4">
                            <input type="text" class="form-control" id="p2-${curAsk}-${curAnsNumb}"  name ="canswer" placeholder="Введіть відповідь">
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
            $(`#ask-${curAsk} .ans-area-${curAnsType}`).append(createAskBlock(curAnsType, true));
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
                                     fieldset.find('input').each(function() {
                                         this.name= this.name.replace('-', '_');
                                     });
                                     fieldset.attr('id', 'ask-' + curAsk);
                                     fieldset.appendTo('.chg-area');
                                     $(`#ask-${curAsk} #testnametc`).val("");
                         }, 0);

            setTimeout(function () {
                       resetAnsBlock(0);
                       resetAnsBlock(1);
                       resetAnsBlock(2);
                       resetAnsBlock(3);
                       curAnsCount++;

                       let currentAnsBlock = $(`#ask-${curAsk} #answerTest-${curAnsType}`);
                       $(`#ask-${curAsk} .ans-area-${curAnsType}`).append(createAskBlock(curAnsType, true));
                       currentAnsBlock.show();
                       curAnsCount = currentAnsBlock.find('.row').length;

             }, 8);

            setTimeout(function () {
                $(`#ask-${curAsk} #answerTest-1`).hide();
                $(`#ask-${curAsk} #answerTest-2`).hide();
                $(`#ask-${curAsk} #answerTest-3`).hide();
                $(`#ask-${curAsk} .ask-num`).text(curAsk + 1);
                $(`fieldset#ask-${prevAsk}`).hide();

             }, 4);

        });

        $( "#nameTest" ).submit(function( event ) {
          var str="";
          for(var i=0; i<allAskCount; i++){
          if($(`input[name=rg-${i}]:checked`).val()) str+=$(`input[name=rg-${i}]:checked`).val();
          }
          if(str!=="") {let block = `<input type="hidden" name="canswer1" value="${str}" />`;
          $(`#nameTest`).append(block);
          }

          alert("Тест создан!");
        });



        })();