<#import "main.ftlh" as m>

<@m.page title="Cahngez Titre de la Sous Categorie">


    <h1>Changez le Nom de la sous-catégorie</h1>


    <form action="/Editeur/changesouscategorie/${souscategorie.id}" method="post" class="FormChangeRegles">
        <h4>Nom actuel : ${souscategorie.title}</h4>
        <br>
        <div class="form-group">
            <textarea id="message" name="newtitre" class="form-field" placeholder="Your Message" rows="6" required></textarea>
            <label for="message" class="form-label">Changez ici le nom de la sous-catégorie.</label>
        </div>
        <button class="btn btnForm" type="submit">Modifier</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

<#--
<h1 style="margin-top: 10%">Changer le titre de la Sous Rubrique</h1>

<form style="margin-top: 20%;" action="/changesouscategorie/${souscategorie.id}" method="post" class="row">
    Nom Sous Rubriques ?<input type="text" name="newtitre">
    <button type="submit">Ajout</button>

</form>

<form action="/Editeur/changesouscategorie/${souscategorie.id}" method="post">
    <div class="form-group">
        <label for="texarea1"> Nouvelle Regle ?</label>
        <input type="text" class="form-control" name="newtitre" id="texarea1" required></input>
        <button class="btn btn-primary" type="submit">Ajout</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </div>
</form>

-->
</@m.page>