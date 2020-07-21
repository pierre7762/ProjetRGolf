<#import "main.ftlh" as m>


<@m.page title="">

    <div class="container-fluid connexionformulaire">
        <div class="row main-content bg-success text-center">
            <div class="col-md-4 text-center company__info">
                <span class="company__logo"><h2><span class="fa fa-android"></span></h2></span>
                <h4 class="company_title">R&A</h4>
            </div>
            <div class="col-md-8 col-xs-12 col-sm-12 login_form">
                <div class="container-fluid">
                    <div class="row">
                        <h2>Connexion</h2>
                    </div>
                    <div class="row">

                        <form action="/login" method="post" class="form-group">
                            <div class="row">
                                <input type="text" name="username" id="username" class="form__input" placeholder="Username">
                            </div>
                            <div class="row">
                                <input type="password" name="password" id="password" class="form__input" placeholder="Mdp">
                            </div>
                            <div class="row">
                                <input type="submit" value="Connexion" class="btnCo">
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>

</@m.page>