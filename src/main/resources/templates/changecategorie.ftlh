<#import "main.ftlh" as m>

<@m.page title="">


    <h1>Changez le Nom de la catégorie</h1>


    <form action="/Editeur/changecategorie/${categorie.id}" method="post" class="FormChangeRegles">
        <h4>Nom actuel : ${categorie.title}</h4>
        <br>
        <div class="form-group">
            <textarea id="message" name="newtitre" class="form-field" placeholder="Your Message" rows="6" required></textarea>
            <label for="message" class="form-label">Changez ici le nom de la catégorie.</label>
        </div>
        <button class="btn btnForm" type="submit">Modifier</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

</@m.page>