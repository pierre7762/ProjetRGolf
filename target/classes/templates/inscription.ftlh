<#import "main.ftlh" as m>

<@m.page title="">

    <div class="container-fluid ">
        <div class="row main-content text-center" style=" margin-top: 20%">
            <div class="col-md-4 text-center company__info">
                <span class="company__logo"><h2><span class="fa fa-android"></span></h2></span>
                <h4 class="company_title">R&A</h4>
            </div>
            <div class="col-md-8 col-xs-12 col-sm-12 login_form">
                <div class="container-fluid" style="margin-top: -20px;height: 500px;">
                    <div class="row">
                        <h2>Inscription</h2>
                    </div>
                    <div class="row" >
                        <form action="/Admin/inscription" method="post">
                            <p> Nom d'utilisateur : <input minlength="2" maxlength="15" type="text" name="nom" required/> </p>

                            <#--p> Mot de passe (8 caractères mini): <input minlength="8" type="password" name="mdp" required/> </p>-->
                            <div>
                                <p for="pass">Nouveau mot de passe :</p>
                                <input type="password" minlength="8" maxlength="20" name="mdp" id="pass" required />
                            </div>
                            <div>
                                <p for="vpass" id="passs">Confirmer le mot de passe :</p>
                                <input  type = "password" minlength="8" maxlength="20" name="passwordConf" id="vpass" onchange="passverif()" required/>
                            </div>

                            <p> Adresse e-mail : <input type="email" minlength="6" name="mail" required/> </p>

                            <select name="idrole" class="col-9">
                                <#list roles as role>
                                    <option value="${role.id}">${role.name}</option>
                                </#list>
                            </select><br>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                            <button class=" btn btn-Ligth" style="text-align: center; border: #064a20 1px solid;margin-top: 3px" id="valider"> valider </button>
                        </form>

                    </div>


                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function passverif() {
            var pass = document.getElementById('pass').value;
            var vpass = document.getElementById('vpass').value;
            if( pass != vpass ) {
                document.getElementById('passs').style.color = "#f00";
                document.getElementById('valider').setAttribute("disabled","");
            } else {
                document.getElementById('passs').style.color = "#0f0";
                document.getElementById('valider').removeAttribute("disabled");

            }
        }

    </script>

</@m.page>