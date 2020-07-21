<#import "main.ftlh" as m>

<@m.page title="Liste des Rubriques">
 <#--   <div class="ajoutcateg" style="margin-top: 10%">
    <h1>Ajouter le titre de la SousRubrique</h1>

    <form  action="/Gestionnaire/newsouscategorie/${categorie.id}" method="post" style="margin-top: 20%;" class="row">
        New name SousRubriques ?<input type="text" name="NewTitle" required>
        New OBJET SousRubriques ?<input type="text" name="newobjet" required>
        <input type="hidden" value="${incrementnumordre}" name="newnumordre">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit">Ajouter</button>
    </form>
    </div>-->

        <form action="/Gestionnaire/newsouscategorie/${categorie.id}" method="post">
            <h1>Ajouter une sous-catégorie</h1>
            <div class="form-group">
                <div class="form-group">
                    <textarea id="code" name="NewTitle" class="form-field" placeholder="Your Message" rows="2" required></textarea>
                    <label for="message" class="form-label" required>Nom de la sous-catégorie</label>
                </div>
                <div class="form-group">
                    <textarea id="code" name="newobjet" class="form-field" placeholder="Your Message" rows="2" ></textarea>
                    <label for="message" class="form-label" >Objet de la sous catégorie</label>
                </div>
                <input type="hidden" value="${incrementnumordre}" name="newnumordre">
            </div>


            <button class="btn btnForm" type="submit">Valider</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

</@m.page>