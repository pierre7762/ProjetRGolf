<#import "main.ftlh" as m>

<@m.page title="">



        <div class="row main-content " style=" margin-top: 20%">
            <div class="col-md-3 text-center company__info">
                <span class="company__logo"><h2><span class="fa fa-android"></span></h2></span>
                <h4 class="company_title">R&A</h4>
            </div>
            <div class="col-md-9 col-xs-12 col-sm-12 login_form">
                <div class="container-fluid">
                    <div class="row">
                        <h2>Gestion des utilisateurs</h2>
                    </div>
                    <ul>

                        <#list user as utilisateur>
                            <form action="/Admin/gestionUtilisateur", method="post">
                                <h5>${utilisateur.name} : ${utilisateur.role.name}</h5>

                                <p>email : ${utilisateur.mail}</p>
                                <input type="hidden" value="${utilisateur.id}" name="userId">


                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button class=" btn btn-Ligth" style="text-align: center; border: #064a20 1px solid;margin-top: 3px" id="valider"> Gérer ${utilisateur.name} </button>
                            </form>


                            <hr>



                        </#list>

                    </ul>


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