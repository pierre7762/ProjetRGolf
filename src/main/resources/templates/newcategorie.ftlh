<#import "main.ftlh" as m>

<@m.page title="Liste des Rubriques">

    <div>
        <form action="/Gestionnaire/newcategorie" method="post">
            <h1>Nouvelle Catégorie ?</h1>
            <div class="form-group">
                <div class="form-group">
                    <textarea id="code" name="NewNameTitle" class="form-field" placeholder="Your Message" rows="2" required></textarea>
                    <label for="message" class="form-label" required>Nom de la catégorie.</label>
                </div>
            </div>
            <div class="form-group">
                <div class="form-group">
                    <textarea id="code" name="NewNameCode" class="form-field" placeholder="Code" rows="2" required></textarea>
                    <label for="message" class="form-label" required>Code (en lettre capital)</label>
                </div>
            </div>
            <button class="btn btnForm" type="submit">Valider</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>





</@m.page>