<#import "main.ftlh" as m>

<@m.page title="">
    <#assign
    known=Session.SPRING_SECURITY_CONTEXT??>
    <#if known>
        <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name= user.getUsername()
        roles= user.authorities>

    <#else>
    </#if>

        <div class="row main-content " style=" margin-top: 20%">
            <div class="col-md-3 text-center company__info">
                <span class="company__logo"><h2><span class="fa fa-android"></span></h2></span>
                <h4 class="company_title">R&A</h4>
            </div>
            <div class="col-md-9 col-xs-12 col-sm-12 login_form">
                <div class="container-fluid">
                    <div class="row">
                        <h2>Gérer mon compte</h2>
                    </div>

                    <#assign
                    known=user??>
                    <#if known>
                        ${utilisateur.}

                    <#else>
                        <p>Inconnu</p>

                    </#if>


                        <#--<h5>${user.name}</h5>

                        <form action="/Admin/modifmail" method="post">
                            <input type="email" value="${user.mail}" name="mail">
                            <input type="hidden" name="userId" value="${user.id}">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button class=" btn btn-Ligth" style="text-align: center; border: #064a20 1px solid"> Modifier l'email </button>
                        </form>

                        <hr>
                        <form action="/Admin/modifpwd" method="post">
                            <p>
                                <label for="pass">Nouveau mot de passe :</label>
                                <input type="password" name="password" id="pass"  />
                            </p>
                            <p>
                                <label for="verif_pass" id="passs">Confirmer le mot de passe :</label>
                                <input  type = "password" name="passwordConf" id="vpass" onchange="passverif()"/>
                            </p>


                            <input type="hidden" name="userId" value="${user.id}">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button class=" btn btn-Ligth" style="text-align: center; border: #064a20 1px solid;margin-top: 3px" id="valider"> Modifier le mot de passe </button>

                        </form>
                        <hr>
                        <form action="/Admin/modifrole" method="post">
                            <h6>Rôle actuel : ${user.role.name}</h6>

                            <select name="idrole">
                                <option value="${user.role.id} " selected>Actuellement : ${user.role.name}</option>
                                <#list role as role>

                                    <option value="${role.id}">${role.name}</option>
                                </#list>
                            </select>


                            <input type="hidden" name="userId" value="${user.id}">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button class=" btn btn-Ligth" style="text-align: center; border: #064a20 1px solid"> Modifier le rôle</button>
                        </form>

-->






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

    </body>


</@m.page>