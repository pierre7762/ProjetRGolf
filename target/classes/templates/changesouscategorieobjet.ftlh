<#import "main.ftlh" as m>

<@m.page title="">



    <h1>Changez l'objet de la sous-catégorie</h1>


    <form action="/Editeur/changesouscategorieobjet/${souscategorie.id}" method="post" class="FormChangeRegles">
        <h4>Objet actuel : ${souscategorie.objet}</h4>
        <br>
        <div class="form-group">
            <textarea id="message" name="newobjet" class="form-field" placeholder="Your Message" rows="6" required></textarea>
            <label for="message" class="form-label">Changez ici le nom de la sous-catégorie.</label>
        </div>
        <button class="btn btnForm" type="submit">Modifier</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>


<#--    <h1 style="margin-top: 10%">Changer l'Objet de la Sous Rubrique</h1>

    <form style="margin-top: 20%;" action="/Editeur/changesouscategorieobjet/${souscategorie.id}" method="post" class="row">
        Novelle Objet Sous Rubriques ?<input type="text" name="newobjet" required>
        <button type="submit">Ajout</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>-->

</@m.page>