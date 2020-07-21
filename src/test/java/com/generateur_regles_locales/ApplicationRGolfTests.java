package com.generateur_regles_locales;

import com.generateur_regles_locales.models.*;
import com.generateur_regles_locales.service.JpaUserService;
import com.generateur_regles_locales.service.VerificationService;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationRGolfTests {

	@Autowired
	private CategorieRepository categorieRepository;

	@Autowired
	private SousCategorieRepository sousCategorieRepository;

	@Autowired
	private RegleRepository regleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private VerificationService verificationService;


	@Test
	@Transactional
	void testmultiple() {
		this.createCategorie();
		this.creatSousCategorie();
		this.createRegle();
		this.modifregle();
		this.deleteRegle();
		this.creationUtilisateur();
		this.inscription();

	}


	@Test
	@Transactional
	void createCategorie() {
		Categorie cat = new Categorie();
		cat.setTitle("Première catégorie");
		categorieRepository.save(cat);
		System.out.println("l'id est : " + cat.getId());
		Long idCat = cat.getId();
		assertThat(idCat).isNotNull();


		assertThat(cat.getSousCategories().size()).isEqualTo(0);

		int nbSousCat = cat.getSousCategories().size();
		System.out.println(("taille sous cat : " + nbSousCat));

		Long nbSousCatTotalInitial = sousCategorieRepository.count();
		System.out.println(("nb sous cat total bdd : " + nbSousCatTotalInitial));


		SousCategorie sousCat = new SousCategorie();
		sousCat.setTitle("hello");
		cat.getSousCategories().add(sousCat);

		sousCategorieRepository.save(sousCat);
		categorieRepository.save(cat);

		assertThat(sousCategorieRepository.count()).isEqualTo(nbSousCatTotalInitial + 1);
		Categorie cat0 = categorieRepository.getOne(cat.getId());
		assertThat(cat0.getSousCategories().size()).isEqualTo(1);
	}

	@Test
	@Transactional
	void creatSousCategorie() {

		Long nbSousCatInitial = sousCategorieRepository.count();
		System.out.println("nb de sous cat initial : " + nbSousCatInitial);

		SousCategorie sousCat = new SousCategorie();
		sousCat.setTitle("Hello");
		sousCategorieRepository.save(sousCat);
		Long nbSousCatApresAjout = sousCategorieRepository.count();
		System.out.println("nb de sous cat après ajout : " + nbSousCatApresAjout);

		assertThat(nbSousCatApresAjout).isEqualTo(nbSousCatInitial + 1);

		Regle regle = new Regle();
		sousCat.getRegles().add(regle);
		regleRepository.save(regle);
		sousCategorieRepository.save(sousCat);

		int nbReglesDansSousCat = sousCat.getRegles().size();
		System.out.println("Nombre de règles dans la sous-catégorie créée : " + nbReglesDansSousCat);

		assertThat(nbReglesDansSousCat).isEqualTo(1);

		SousCategorie scat0 = sousCategorieRepository.getOne(sousCat.getId());
		assertThat(scat0.getRegles().size()).isEqualTo(1);

	}


	@Test
	@Transactional
	void createRegle() {
		Long nbRegleInitail = regleRepository.count();
		System.out.println("nb de règles avant ajout : " + nbRegleInitail);

		Regle regle = new Regle();
		regle.setTitle("TitreTest");
		regle.setNumordre(1);
		regleRepository.save(regle);

		assertThat(regle.getTitle()).isNotNull();
		assertThat(regle.getId()).isNotNull();

		assertThat(regleRepository.count()).isEqualTo(nbRegleInitail + 1);


	}

	@Test
	public void testsplit() {

		String str = "<a href=\"http://www.pierre-lemere.fr\">click;</a>";
		str = str.replaceAll("[<>@{};]*", " ");

		System.out.println(str);
	}


	@Test
	@Transactional
	void deleteRegle() {
		long nbregledebut = regleRepository.count();

		Regle regle = regleRepository.getOne(2L);

		regleRepository.delete(regle);

		assertThat(regleRepository.count()).isEqualTo(nbregledebut - 1);


	}


	@Test
	@Transactional
	void modifregle() {
		long derniereregle = regleRepository.count() - 1;

		Regle regle = regleRepository.getOne(derniereregle);
		regle.setCorpus("blabla");

		String anciencorpus = regle.getCorpus();

		regle.setCorpus("ça a changé");
		String nvCorpus = regle.getCorpus();
		regleRepository.save(regle);

		assertThat(anciencorpus).isNotEqualTo(nvCorpus);
		assertThat(nvCorpus).isEqualTo("ça a changé");


	}

	@Test
	@Transactional
	void creationUtilisateur() {

		Long nombreUtilisateurInitial = userRepository.count();

		User user = new User();

		user.setName("banane");

		String nomsaisi = user.getName();

		String nomparser = Jsoup.parse(nomsaisi).text();

		if (nomsaisi.equals(nomparser)) {
			user.setName(nomparser);
		} else {
			System.out.println("merci de ne pas rentrer de code");
		}


	}

	@Test
	@Transactional
	public void inscription() {

		String nom = "pierre";
		String mail = "pierre.lemere@gmail.com";
		String mdp = "azertyui";
		Long idrole = 1L;

		Long nbUtilisateurInitial = userRepository.count();
		System.out.println("Nombre d'utilisateurs initial : " + nbUtilisateurInitial);


		Boolean verifok = verificationService.inputSafe(nom);
		if (verifok == false) {
			System.out.println("merci de ne pas essayer d'entrer du code");

		}

		Boolean verifok2 = verificationService.inputSafe(mail);
		if (verifok2 == false) {
			System.out.println("merci de ne pas essayer d'entrer du code");

		}
		Boolean verifok3 = verificationService.inputSafe(mdp);
		if (verifok3 == false) {
			System.out.println("merci de ne pas essayer d'entrer du code");

		}

		String usernom = Jsoup.parse(nom).text();
		String usermail = Jsoup.parse(mail).text();
		String usermdp = Jsoup.parse(mdp).text();

		if (usernom.isEmpty() == false && userRepository.findByName(usernom) == null) {
			User user = new User();
			user.setName(usernom);
			user.setMail(usermail);
			user.setPassword(usermdp);
			Role role = roleRepository.findById(idrole).get();
			if (role != null) {
				user.setRole(role);
				JpaUserService jus = new JpaUserService();
				jus.setbCryptPasswordEncoder(new BCryptPasswordEncoder());
				jus.setUserDao(userRepository);
				//jus.setGroupDao(roleRepository);
				jus.save(user); //la methode save code le mdp
			}

		}
		Long nbUtilisateurFin = userRepository.count();
		System.out.println("Nombre d'utilisateurs final : " + nbUtilisateurFin);

		assertThat(nbUtilisateurInitial).isLessThan(nbUtilisateurFin);
		assertThat(nbUtilisateurInitial + 1).isEqualTo(nbUtilisateurFin);

	}
}


