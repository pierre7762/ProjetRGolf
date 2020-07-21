<#macro page title>
    <!doctype html>
    <html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>R & A</title>
        <!-- CSS only -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
              integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
              crossorigin="anonymous">
        <link rel="stylesheet" href="/css/style.css">
        <!-- JS, Popper.js, and jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
                crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
                integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
                crossorigin="anonymous"></script>

    </head>
    <body>
    <#assign
    known=Session.SPRING_SECURITY_CONTEXT??>
    <#if known>
        <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name= user.getUsername()
        roles= user.authorities>

    <#else>

    </#if>
    <#if roles??>
        <#list roles as role>
            <#assign role="${role}">
        </#list>
    </#if>


    <div class="container bg-light">
        <div class="pos-f-t">
            <div class="collapse" id="navbarToggleExternalContent">
                <div class="bg-light p-4 text-dark">

                        <ul class="navbar-nav" id="cemenu">

                            <li class="nav-item">
                                <a class="nav-link" href="/home">Accueil <span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/categories">Règles Locales</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="https://www.randa.org/en/rog/2019/pages/local-rules-creator" target="_blank">R&A</a>
                            </li>

                            <#--  affichage si admin connecté      -->
                            <#if habilitations??>
                                <#list habilitations as role>
                                    (${role})<#assign role="${role}">
                                </#list>
                            </#if>

                            <#if role??><#if role="ADMIN">
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" >
                                        Administration
                                    </a>
                                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <a class="dropdown-item" href="/Admin/inscription">Inscrire un utilisateur </a>
                                        <a class="dropdown-item" href="/Admin/listeUtilisateur">Gérer les utilisateurs</a>
                                    </div>
                                </li>
                            </#if></#if>
                            <#--  afficher connexion si pas d'utilisateur connecté     -->
                            <#assign
                            known=user??>
                            <#if known>
                            <#else>
                                <li class="nav-item">
                                    <a class="nav-link " href="/connexion">Connexion</a>
                                </li>
                            </#if>
                        </ul>
            </div>

        </div>


            <nav class="navbar navbar-light bg-light">

               <div class="menu">
                   <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
                       <span class="navbar-toggler-icon"></span>
                   </button>

                   <div class="collapse navbar-collapse text-black" id="navbarNav">
                   </div>

               </div>
                <#assign
                known=user??>
                <#if known>
                    <div class="row" style="text-align: center;padding: 0.4%;">
                        <h7 style="color: #064a20;margin-top: 1%;text-align: center;text-decoration: underline">${name}</h7>

                        <form action="/deconnexion", method="post">
                            <#--<a role="button" class="btn btn-success btn-sm" href="/deconnexion">Deconnexion</a>-->
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="btn btnDeco btn-outline-success btn-sm">Deconnexion</button>
                        </form>
                    </div>
                    <!-- border: #064a20 1px solid;-->
                <#else>

                </#if>
            </nav>



        <#nested user name role>


     </div>
    </div>


    </body>
</html>
</#macro>