<#import "parts/common.ftl" as c>

<@c.page>
    <h4>Тест</h4>
    <form id="nameTest" action="/create" role="form" enctype="multipart/form-data" method="post">

        <div class="row my-3 justify-content-start">
            <div class="col col-1 col-form-label">Назва:</div>
            <div class="col col-4">   <input type="text" class="form-control" id="testname" name="textTest" placeholder="Введіть назву теста"></div>
            <div class="col col-2 col-form-label offset-md-1">Ліміт часу:</div>
            <div class="col col-2"><input type="time" class="form-control" id="limit_time"></div>
            <div class="w-100 mb-3"></div>
            <div class="col col-1 col-form-label">Опис:</div>
            <div class="col col-4"><input type="text" class="form-control" id="description" placeholder="Введіть опис теста"></div>
            <div class="col col-2 col-form-label offset-md-1">Кількість спроб:</div>
            <div class="col col-2"><input type="text" class="form-control" id="count_test"></div>
            <div class="w-100 mb-5"></div>
        </div>

        <h4>Питання</h4>
        <div class="row my-3 justify-content-start form-group">
            <label class="control-label col-1" for="typeAnswer">Тип питання</label>
            <div class="col col-md-4">
                <select id="typeAnswer" class="form-control">
                    <option>1 правильна відповідь</option>
                    <option>Декілька правильних відповідей</option>
                    <option>Введення відповіді з клавіатури</option>
                    <option>Відповідність</option>
                </select>
            </div>
                <div class="w-100 mb-2"></div>
                <div class="col col-1 col-form-label">Запитання:</div>
                <div class="col col-4">   <input type="text" class="form-control" id="testnametc" placeholder="Введіть запитання"></div>
                <div class="w-100 mb-3"></div>
                <div class="col col-4" id="img1"> <input type="file" name="file"> </div>
                <div class="w-100 mb-1"></div>
                <div class="col col-4" id="img2"> <input type="file" name="file"> </div>
        </div>

        <h4>Відповіді </h4>

        <div class="jumbotron">
            <div class="btn-toolbar mb-5" role="toolbar" aria-label="Toolbar with button groups">
                <div class="btn-group mr-2" role="group" aria-label="First group">
                    <button type="button" class="btn btn-secondary">1</button>
                </div>
            </div>

            <div class="row my-3 justify-content-start" id="answerTest0">
                <div class="col col-1 col-form-label pl-5 ">  <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" value="3" checked> </div>
                <div class="col col-4"><input type="text" class="form-control" id="description0" placeholder="Введіть відповідь"></div>
                <div class="col col-1"> <button type="button" class="btn btn-secondary">+</button> </div>
                <div class="col col-1 ml-4"> <button type="button" class="btn btn-danger">Видалити</button> </div>
            </div>

            <button type="button" class="btn btn-info">Додати питання</button>

        </div>
        <button type="submit">Save</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>


<script type="text/javascript">
    $('#answerTest1').hide();
    $('#answerTest2').hide();
    $('#answerTest3').hide();

    $('#img2').hide();


    $('#typeAnswer').on('change', function() {
    $('#answerTest1').hide();
    var a =$(this).find(":selected").index();

    for(var i=0; i<4; i++){
        $('#answerTest'+i).hide();
        if(a==i) $('#answerTest'+i).show();
    }

});

/*
var input = document.createElement("input");
input.type = "text";
input.className = "#answerTest0"; // set the CSS class
container.appendChild(input);
*/

</script>
</@c.page>