<#macro login path>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Логін:</label>
        <div class="col-sm-6">
            <input type="text" name="username" class="form-control" placeholder="Логін" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Пароль:</label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Пароль" />
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
<button class="btn btn-primary" type="submit">Вхід</button>
</form>
</#macro>

<#macro registration path>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Логін:</label>
        <div class="col-sm-6">
            <input type="text" name="username" class="form-control" placeholder="Логін" />
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Пароль:</label>
        <div class="col-sm-6">
            <input type="text" name="password" class="form-control" placeholder="Пароль" />
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">ПІБ:</label>
        <div class="col-sm-6">
            <input type="text" name="stfio" class="form-control" placeholder="ПІБ:" />
        </div>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Група:</label>
        <div class="col-sm-6">
            <input type="text" name="groupst" class="form-control" placeholder="Група:" />
        </div>
    </div>

    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Створити користувача</button>
</form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Вихід</button>
</form>
</#macro>

<#macro sign>
<form action="/login" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Авторизація</button>
</form>
</#macro>