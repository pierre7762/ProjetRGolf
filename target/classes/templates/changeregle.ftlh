<#import "main.ftlh" as m>

<@m.page title="">



    <h1>Changer la règle</h1>


    <form action="/Editeur/reglechange/${regle.id}" method="post" class="FormChangeRegles">
        <h4>Règle actuel : </h4>
        <p>${regle.corpus}</p>
        <br>
        <div class="form-group">
            <textarea id="message" name="newregle" class="form-field" placeholder="Your Message" rows="8" required>${regle.corpus}</textarea>
            <label for="message" class="form-label">Changer la règle</label>
        </div>
        <button class="btn btnForm" type="submit">Modifier</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>



</@m.page>