<#import "main.ftlh" as m>

<@m.page title="">

    <h1 class="TitreTopPage">Liste des Règles séléctionnées</h1>

    <div class="TouteLesListe">
        <button class="btn-copy btn btn-danger" id="copy" type="button">Copier les Règles Locales dans le presse-papier
        </button>
        <div class="presse-papier">

            <#list listeregles as regle>


                    <p>- ${regle.getCorpus()}</p>

            </#list>









        </div>
    </div>

    <script>
        var toCopy = document.querySelector('.presse-papier').innerText,
            btnCopy = document.getElementById('copy');

        btnCopy.addEventListener('click', function () {
            navigator.clipboard.writeText(toCopy);
            document.execCommand('copy');
            return false;
        });

    </script>
</@m.page>