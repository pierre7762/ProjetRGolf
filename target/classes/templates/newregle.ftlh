<#import "main.ftlh" as m>

<@m.page title="Liste des Rubriques">
    <form action="/Gestionnaire/newregletexte/${souscategorie.id}" method="post">
        <h1>Ajouter une règle</h1>
        <div class="form-group">
            <div class="form-group">
                <textarea id="code" name="newcorpus" class="form-field" placeholder="Your Message" rows="8" required></textarea>
                <label for="message" class="form-label" required>Rédiger la règle</label>
            </div>

            <input type="hidden" value="${incrementnumordre}" name="newnumordre">
        </div>


        <button class="btn btnForm" type="submit">Valider</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

   <#-- <h1 style="margin-top: 10%">Ajouter le texte de la Regle</h1>

    <form style="margin-top: 20%;" action="/Gestionnaire/newregletexte/${souscategorie.id}" method="post" class="row">

        New Corpus Regle ?<input type="text" name="newcorpus" required>
        <input type="hidden" value="${incrementnumordre}" name="newnumordre">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit">Ajouter</button>
    </form>-->

</@m.page>