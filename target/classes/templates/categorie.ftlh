<#import "main.ftlh" as m>

<@m.page title="">
    <!---->


    <#assign
    known=Session.SPRING_SECURITY_CONTEXT??>
    <#if known>
        <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        habilitations= user.authorities>
    <#else>
        <#assign
        name="unknown"
        >
    </#if>

    <!---->

    <form action="/regleselect" method="post">

        <div class="row">
            <div class="col-1">
                <div class="fixedbutton">
                    <button class="btn btnSelectedRules btn-light align-center" type="submit">Voir la sélection</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                </div>
            </div>
            <div class="col-11">

                <ul class="TouteLesListe">
                    <div class="DivTitreTopPage">
                        <h1 class="TitreTopPage">Liste des règles locales</h1>
                        <#if habilitations??>
                            <#list habilitations as role>
                                (${role})<#assign role="${role}">
                            </#list>
                        </#if>

                    </div>
                    <br>
                    <#list categories as categorie>

                        <div class="categorieAll ">
                            <div class="card row shadow p-1 mb-2 sm-white rounded titrecateg soulignergolf"
                                 onclick="AfficherMasquerCateg(${categorie.id})">
                                <div class="card-body">
                                    <h5>${categorie.code}.${categorie.title}</h5>
                                    <!--   <input value="Afficher / Masquer" type="button" class="btn btn-success" onclick="AfficherMasquerCateg(${categorie.id})"></input>-->

                                </div>
                            </div>


                            <div id="${categorie.id}" class="divCateg" style="display:none;">
                                <#if role??>  <#if role=="GESTIONNAIRE"||role=="ADMIN">
                                    <a href="/Editeur/changecateg/${categorie.id}" role="button"
                                       class="btn btn-sm btn-outline-light lienHref">Changer le nom de la catégorie</a>
                                </#if></#if>
                                <#if role??>  <#if role=="GESTIONNAIRE"||role=="ADMIN">
                                    <a href="/Gestionnaire/newcateg/${categorie.id}"
                                       class="btn btn-sm btn-outline-light lienHref">Nouvelle
                                        catégorie</a>
                                    <a href="/Gestionnaire/newsouscateg/${categorie.id}"
                                       class="btn btn-sm btn-outline-light lienHref">Nouvelle sous-catégorie</a>
                                </#if></#if>
                                <#if role??>  <#if role=="ADMIN">
                                    <a href="/Admin/categoriedelete/${categorie.id}"
                                       class="btn btn-sm btn-outline-light lienHref">Suprimer la catégorie </a>
                                </#if></#if>

                                <#list categorie.getSousCategories() as souscat>
                                    <div class="card row shadow-sm p-1 mb-2 sm-white rounded titresouscateg soulignergolf"
                                         onclick="AfficherMasquerCateg(1${souscat.id})">
                                        <div class="card-body">
                                            <h6>${categorie.code}.${souscat.numordre}.${souscat.title}</h6>
                                            <!--  <input value="Afficher/Masquer" type="button" class="btn btn-success" onclick="AfficherMasquerCateg(1${souscat.id})"></input>-->
                                        </div>
                                    </div>

                                    <div id="1${souscat.id}" class="divSousCateg " style="display:none;">

                                        <#if role??>  <#if role=="GESTIONNAIRE"|| role=="ADMIN">
                                            <a href="/Editeur/changesouscateg/${souscat.id}"
                                               class="btn btn-sm btn-outline-light lienHref">Changer le nom
                                               de la sous-catégorie</a>
                                           <#-- <a href="Editeur/changesouscategobjet/${souscat.id}"
                                               class="btn btn-sm btn-outline-light lienHref">Changer l'objet</a>-->
                                        </#if></#if>
                                        <#if role??>  <#if role=="ADMIN">
                                            <a href="/Admin/souscategoriedelete/${souscat.id}"
                                               class="btn btn-sm btn-outline-light lienHref">Suprimer la
                                                sous-catégorie </a>
                                        </#if></#if>
                                        <#if role??>  <#if role=="GESTIONNAIRE" || role=="ADMIN">
                                            <a href="/Gestionnaire/newnextregle/${souscat.id}"
                                               class="btn btn-sm btn-outline-light lienHref">Nouvelle Règle</a>
                                        </#if></#if>


                                        <div class="divRegle">
                                            <#list souscat.getRegles() as regles >
                                                <div class="card shadow p-3 mb-5 bg-white rounded divRegle soulignergolf">
                                                    <div class="card-body">

                                                        <div class="row">
                                                            <div class="col-1">
                                                                <div class="wrapInputCheckbox">
                                                                    <input type="checkbox" class="col-1 inputCheckbox changeItem-js itemChange-js" id="defaultUnchecked${regles.id}" name="idregles" value="${regles.id}" >
                                                                    <label class="col-11 label-wrapText"
                                                                           for="defaultUnchecked${regles.id}">
                                                                        <span class="input-psevdo" style="margin-left: -10px"></span>
                                                                        <span class="customTextCheckbox"></span>
                                                                    </label>
                                                                </div>
                                                            </div>
                                                            <div class="col-11 text-justify">
                                                                <h9>${categorie.code}.${souscat.numordre}
                                                                    -${regles.numordre}
                                                                    .${regles.corpus}</h9>

                                                            </div>

                                                        </div>
                                                        <div class="row" style="justify-content: center">
                                                            <#if role??>  <#if role=="EDITEUR" || role=="GESTIONNAIRE" || role=="ADMIN">
                                                                <a href="Editeur/changeregle/${regles.id}"
                                                                   class="btn btn-sm btn-outline-muted lienHref">Editer
                                                                    la Règle</a>
                                                            </#if></#if>
                                                            <#if role??>  <#if role=="ADMIN">
                                                                <a href="Admin/regledelete/${regles.id}"
                                                                   class="btn btn-sm btn-outline-muted lienHref">Suprimer
                                                                    la Règle </a>
                                                            </#if></#if>
                                                        </div>


                                                    </div>

                                                </div>
                                            </#list>
                                        </div>

                                    </div>
                                </#list>
                            </div>
                        </div>
                    </#list>


                </ul>
            </div>

        </div>


    </form>
    <script type="text/javascript">


        function AfficherMasquerCateg(id) {
            div = document.getElementById(id);
            if (div.style.display === "none") {
                div.style.display = "block";
            } else {
                div.style.display = "none";
            }
        }




    </script>



</@m.page>