package web;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import metier.InscriptionsAdministrative;
import metier.InscriptionsEnligne;
import metier.InscriptionsPedagogique;
import metier.NoteCRUD;
import metierEntite.An_acad;
import metierEntite.Etablissement;
import metierEntite.Etape;
import metierEntite.Etudiant;
import metierEntite.Filiere;
import metierEntite.Module;
import metierEntite.Note;
import metierEntite.InscriptionAdministrative;
import metierEntite.InscriptionPedagogique;
import metierEntite.Semestre;

/**
 * Servlet implementation class Controlleur
 */
@WebServlet({ "/inscriptionE.php", "/login", "/inscriptionsE", "/supprimer.php", "/inscriptionAd.php",
		"/ajouter-Iadmin.php", "/add-inscriptionE", "/add-inscriptionEexcel", "/add-inscriptionEexcel.php",
		"/supprimerG.php", "/inscrireAd", "/inscrire-etape", "/inscriptionsA", "/supprimerAG.php",
		"/add-inscriptionAexcel", "/ajouter-note", "/ajouter-note-post", "/add-inscriptionAexcel.php",
		"/inscrire-Etap.php", "/inscrire-semestre", "/inscrire-Sem.php", "/inscrire-module", "/inscrire-Mod.php",
		"/home", "/choisir-filiere","/importer-note", "/importer-note-post" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class Controlleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	InputStream inputStream = null;
	InputStream inputNotes = null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controlleur() {
		super();
		

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		// Inscription en ligne
		InscriptionsEnligne metierInscriptionE = new InscriptionsEnligne();
		InscriptionsAdministrative metierInscriptionsAdministrative = new InscriptionsAdministrative();
		InscriptionsPedagogique meInscriptionsPedagogique = new InscriptionsPedagogique();
		List<Etudiant> etudiants = metierInscriptionE.listEt();
		List<Etablissement> etablissements = metierInscriptionsAdministrative.listEtab();
		List<Filiere> filieres = metierInscriptionsAdministrative.listFil();
		List<An_acad> An_acads = metierInscriptionsAdministrative.listAn();
		List<InscriptionAdministrative> inscriptionAdministratives = metierInscriptionsAdministrative.listInsA();
		List<Etape> etapes = meInscriptionsPedagogique.listEtapes();
		List<String> an_acadList = metierInscriptionsAdministrative.an_acad();
		List<Semestre> semestres = meInscriptionsPedagogique.listSe();
		List<Module> modules = meInscriptionsPedagogique.listMod();
		List<InscriptionPedagogique> listPeda = meInscriptionsPedagogique.getlistInP();
		NoteCRUD noteCRUD = new NoteCRUD();
		Boolean inscriptionE = false;
		int IDFiliere = 0;
		HttpSession session = request.getSession();

		// form
		if (path.equals("/inscriptionE.php")) {
			System.out.println("dans inscriptionE ");

			String massarEtud = request.getParameter("massarEtud");//
			String NomFr = request.getParameter("NomFr");//
			String NomAr = request.getParameter("NomAr");//
			String PrenomFr = request.getParameter("PrenomFr");//
			String PrenomAr = request.getParameter("PrenomAr");//
			String acad = request.getParameter("acad");
			String an_Bac = request.getParameter("an_Bac");
			String cin = request.getParameter("cin");
			String date = request.getParameter("dateN");
			System.out.print(date);
			System.out.print(massarEtud);
			Date dateN = null;
			try {
				dateN = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				System.out.println(dateN);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String date1in = aujourdhui();
			System.out.println(date1in);

			String villeBac = request.getParameter("villeBac");
			String lieuN_ar = request.getParameter("lieuN_ar");////
			String lieuN_fr = request.getParameter("lieuN_fr");//
			String lycee = request.getParameter("lycee");
			String villeNaissance = request.getParameter("villeNaissance");
			String mt = request.getParameter("mt");
			String nationalite = request.getParameter("nationalite");
			String province = request.getParameter("province");
			String sBac = request.getParameter("sBac");//
			String sexe = request.getParameter("sexe");
			String region = request.getParameter("region");

			Part filePart = request.getPart("photo");

			if (filePart != null) {

				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());

				inputStream = filePart.getInputStream();
			}

			String etatPhy = request.getParameter("etatPhy");
			String GroupSocio = request.getParameter("GroupSocio");

			Etudiant etudiant = new Etudiant(massarEtud, NomFr, NomAr, PrenomFr, PrenomAr, acad, an_Bac, cin, date1in,
					dateN, villeBac, lieuN_ar, lieuN_fr, villeNaissance, lycee, mt, nationalite, province, sBac, sexe,
					region, etatPhy, GroupSocio, inputStream);
			inscriptionE = metierInscriptionE.addEt(etudiant);

		}

		else if (inscriptionE == true) {
			response.sendRedirect("/miniprojet/success.jsp");

		}

		// Authentification

		else if (path.equals("/login")) {
			System.out.println("hereLogin");
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}

		// List des inscriptions en ligne
		else if (path.equals("/inscriptionsE")) {
			System.out.println("inscription");

			request.setAttribute("etudiants", etudiants);
			List<String> massarListSupp = new ArrayList<String>();
			request.setAttribute("massarListSupp", massarListSupp);
			this.getServletContext().getRequestDispatcher("/inscriptionsE.jsp").forward(request, response);
			return;
		}
		// Supprimer par masssarEtud
		else if (path.equals("/supprimer.php")) {
			Boolean ok = false;
			System.out.print("suppression ");
			String masssarEtud = request.getParameter("massarEtud");

			ok = metierInscriptionE.deleteEt(masssarEtud);
			System.out.println(ok);
			this.getServletContext().getRequestDispatcher("/inscriptionsE").forward(request, response);
			return;
		}

		else if (path.equals("/inscriptionAd.php")) {
			System.out.print("inscription admini ");
			String masssarEtud = request.getParameter("massarEtud");

			Etudiant etudiant = metierInscriptionE.getEt(masssarEtud);
			request.setAttribute("e", etudiant);
			request.setAttribute("etab", etablissements);
			request.setAttribute("fil", filieres);
			request.setAttribute("an_acad", An_acads);

			this.getServletContext().getRequestDispatcher("/add-inscriptionAd.jsp").forward(request, response);
			return;
		}

		else if (request.getServletPath().equals("/ajouter-note")) {
			request.setAttribute("modules", modules);
			request.setAttribute("etudiants", etudiants);

			this.getServletContext().getRequestDispatcher("/ajouter-note.jsp").forward(request, response);
		}
		else if(request.getServletPath().equals("/ajouter-note-post")) {
			System.out.println("MODULE === "+ request.getParameter("module"));
			int idModule = Integer.parseInt(request.getParameter("module"));
			String etudiant = request.getParameter("etudiant");
			double note = Double.parseDouble(request.getParameter("note"));
			
			Note noteObj = new Note(note, idModule, etudiant);
			
			try {
				noteCRUD.ajouterNote(noteObj);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			this.getServletContext().getRequestDispatcher("/ajouter-note.jsp").forward(request, response);
			return;
		}
		
		else if(request.getServletPath().equals("/importer-note"))
		{
			this.getServletContext().getRequestDispatcher("/importer-note.jsp").forward(request, response);
			return;
		}
		else if(request.getServletPath().equals("/importer-note-post")) {
			Part filePart = request.getPart("notes");

			if (filePart != null) {

				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());

				inputNotes = filePart.getInputStream();
			}
			
			try {
				noteCRUD.importerNotes(inputNotes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			this.getServletContext().getRequestDispatcher("/importer-note.jsp").forward(request, response);
			return;
		}
		// ajouter administrativement
		else if (path.equals("/ajouter-Iadmin.php")) {

			String massarEtud = request.getParameter("massarEtud");//
			String NomFr = request.getParameter("NomFr");//
			String NomAr = request.getParameter("NomAr");//
			String PrenomFr = request.getParameter("PrenomFr");//
			String PrenomAr = request.getParameter("PrenomAr");//
			String acad = request.getParameter("acad");
			String an_Bac = request.getParameter("an_Bac");
			String cin = request.getParameter("cin");
			String date = request.getParameter("dateN");

			String villeBac = request.getParameter("villeBac");
			String lieuN_ar = request.getParameter("lieuN_ar");////
			String lieuN_fr = request.getParameter("lieuN_fr");//
			String lycee = request.getParameter("lycee");
			String villeNaissance = request.getParameter("villeNaissance");
			String mt = request.getParameter("mt");
			String nationalite = request.getParameter("nationalite");
			String province = request.getParameter("province");
			String sBac = request.getParameter("sBac");//
			String sexe = request.getParameter("sexe");
			String region = request.getParameter("region");
			String etatPhy = request.getParameter("etatPhy");
			String GroupSocio = request.getParameter("GroupSocio");
			System.out.print(cin);
			String cne = request.getParameter("cne");
			String dip = request.getParameter("dip");
			String adressePerso = request.getParameter("adressePerso");
			String adresseParent = request.getParameter("adresseParent");

			String ville = request.getParameter("ville");
			String tele = request.getParameter("tele");

			String mail = request.getParameter("mail");
			String an_acad = request.getParameter("an_acad");
			String bourse = request.getParameter("bourse");
			String etab = request.getParameter("etab");
			System.out.print("okkk" + etab);
			String fil = request.getParameter("fil");

			Date dateN = null;
			try {
				dateN = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				System.out.println(dateN);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String date_validant = aujourdhui();
			InscriptionAdministrative inscriptionAdministrative = new InscriptionAdministrative(0, massarEtud, cne, dip,
					adressePerso, adresseParent, ville, tele, mail,
					metierInscriptionsAdministrative.getIdAn_acad(an_acad), bourse, etab, fil, date_validant, "admin");
			Etudiant etudiant = new Etudiant(massarEtud, NomFr, NomAr, PrenomFr, PrenomAr, acad, an_Bac, cin, null,
					dateN, villeBac, lieuN_ar, lieuN_fr, villeNaissance, lycee, mt, nationalite, province, sBac, sexe,
					region, etatPhy, GroupSocio, null);
			metierInscriptionE.updateEt(etudiant);
			metierInscriptionsAdministrative.addAdmini(inscriptionAdministrative);

		}
		// ajouter inscription en ligne admin

		else if (path.equals("/add-inscriptionE")) {

			this.getServletContext().getRequestDispatcher("/add-inscriptionE.jsp").forward(request, response);
			return;

		}

		// ajouter inscription en ligne admin fichier excel

		else if (path.equals("/add-inscriptionEexcel")) {

			this.getServletContext().getRequestDispatcher("/add-inscriptionEexcel.jsp").forward(request, response);
			return;

		} else if (path.equals("/add-inscriptionEexcel.php")) {

			Part filePart = request.getPart("fichier");

			if (filePart != null) {

				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());

				inputStream = filePart.getInputStream();
			}

			metierInscriptionE.addEtExcel(inputStream);
			this.getServletContext().getRequestDispatcher("/inscriptionsE").forward(request, response);
			return;

		} else if (path.equals("/supprimerG.php")) {
			for (String massarEtud : request.getParameterValues("massarEtud")) {
				metierInscriptionE.deleteEt(massarEtud);
			}
			this.getServletContext().getRequestDispatcher("/inscriptionsE").forward(request, response);
			return;
		}

		// list inscriptions administrative

		else if (path.equals("/inscriptionsA")) {
			request.setAttribute("insA", inscriptionAdministratives);
			request.setAttribute("etudiants", etudiants);
			request.setAttribute("fil", filieres);
			this.getServletContext().getRequestDispatcher("/inscriptionsA.jsp").forward(request, response);
			return;
		}

		// supprimer inscription administrtative
		else if (path.equals("/supprimerAG.php")) {
			for (String IDIA : request.getParameterValues("IDIA")) {
				metierInscriptionsAdministrative.deleteEt(Integer.parseInt(IDIA));
			}
			this.getServletContext().getRequestDispatcher("/inscriptionsA").forward(request, response);
			return;
		}

		// ajouter inscription administrative depuis excel

		else if (path.equals("/add-inscriptionAexcel")) {
			this.getServletContext().getRequestDispatcher("/add-inscriptionAexcel.jsp").forward(request, response);
			return;
		}

		else if (path.equals("/add-inscriptionAexcel.php")) {

			Part filePart = request.getPart("fichier");

			if (filePart != null) {

				System.out.println(filePart.getName());
				System.out.println(filePart.getSize());
				System.out.println(filePart.getContentType());

				inputStream = filePart.getInputStream();
			}

			metierInscriptionsAdministrative.addEtExcel(inputStream);
			this.getServletContext().getRequestDispatcher("/inscriptionsA").forward(request, response);
			return;

		}

		// inscrire administratuvement
		else if (path.equals("/inscrireAd")) {
			System.out.println("inscription administratives ");

			request.setAttribute("etudiants", etudiants);
			List<String> massarListSupp = new ArrayList<String>();
			request.setAttribute("massarListSupp", massarListSupp);
			this.getServletContext().getRequestDispatcher("/inscrireAd.jsp").forward(request, response);
			return;
		}
		// choisir la filiere
		else if (path.equals("/choisir-filiere")) {

			request.setAttribute("fil", filieres);
			this.getServletContext().getRequestDispatcher("/choisir-filiere.jsp").forward(request, response);
			return;
		}
		// inscrire etape jsp
		else if (path.equals("/inscrire-etape")) {
			System.out.println("inscription");
			etapes = meInscriptionsPedagogique.listEtapes();

			String filiere = request.getParameter("filiere");

			for (int i = 0; i < filieres.size(); i++) {
				if (filiere.equals(filieres.get(i).getFiliere()))
					IDFiliere = filieres.get(i).getIDFiliere();

			}
			List<InscriptionAdministrative> insA = new ArrayList<InscriptionAdministrative>();
			for (int i = 0; i < inscriptionAdministratives.size(); i++) {
				if (Integer.parseInt(inscriptionAdministratives.get(i).getFiliere()) == IDFiliere)
					insA.add(inscriptionAdministratives.get(i));
			}
			List<Etape> etape = meInscriptionsPedagogique.getEtapbyFiliere(IDFiliere);
			request.setAttribute("fil", filieres);
			request.setAttribute("filiere", filiere);
			request.setAttribute("insA", insA);
			request.setAttribute("etudiants", etudiants);
			request.setAttribute("etape", etape);
			request.setAttribute("an_acad", An_acads);

			this.getServletContext().getRequestDispatcher("/inscrire-etape.jsp").forward(request, response);
			return;
		}
		// inscrire etape etudiants s�l�ction�es

		else if (path.equals("/inscrire-Etap.php")) {

			System.out.println("inscription � l'�tape");
			String etape = request.getParameter("etape");
			session.setAttribute("etape", etapes);
			System.out.println("etape=" + etape);
			String filiere = request.getParameter("filiere");

			request.setAttribute("an_acad", An_acads);

			List<InscriptionPedagogique> ListInsEtape = new ArrayList<InscriptionPedagogique>();

			int IDEtape = 0;
			for (int i = 0; i < etapes.size(); i++) {

				if (etapes.get(i).getLabelleEtape().equals(etape)) {
					System.out.print("hier i=" + i);
					IDEtape = etapes.get(i).getIDEtape();
				}
			}
			// request.setAttribute("etape",IDEtape);
			// session.setAttribute("etapechoisi",IDEtape);
			for (String IDIA : request.getParameterValues("idia")) {
				InscriptionPedagogique ins = new InscriptionPedagogique();
				ins.setIDEtape(IDEtape);
				System.out.println("idi==========" + IDIA);
				ins.setIDIA(Integer.parseInt(IDIA));

				String massarEtud = null;
				for (int i = 0; i < inscriptionAdministratives.size(); i++) {
					if (inscriptionAdministratives.get(i).getIDIA() == Integer.parseInt(IDIA)) {
						massarEtud = inscriptionAdministratives.get(i).getMassarEtud();

					}
				}
				ins.setMassarEtud(massarEtud);
				ins.setIDIA(Integer.parseInt(IDIA));

				meInscriptionsPedagogique.add(ins);
				int IDIP = 0;

				List<InscriptionPedagogique> listPedag = meInscriptionsPedagogique.getlistInP();

				for (int j = 0; j < listPedag.size(); j++) {

					if (listPedag.get(j).getIDIA() == Integer.parseInt(IDIA)) {
						IDIP = listPedag.get(j).getIDIP();

					}
				}

				ins.setIDIP(IDIP);
				ListInsEtape.add(ins);
			}
			List<InscriptionAdministrative> insA = new ArrayList<InscriptionAdministrative>();
			for (int i = 0; i < inscriptionAdministratives.size(); i++) {
				if (Integer.parseInt(inscriptionAdministratives.get(i).getFiliere()) == IDFiliere)
					insA.add(inscriptionAdministratives.get(i));
			}
			request.setAttribute("insA", insA);
			request.setAttribute("etudiants", etudiants);
			request.setAttribute("fil", filieres);

			request.setAttribute("etapes", etapes);

			session.setAttribute("insP", ListInsEtape);

			this.getServletContext().getRequestDispatcher("/inscrire-etape.jsp").forward(request, response);
			return;

		}

		// inscrire semestre jsp
		else if (path.equals("/inscrire-semestre")) {
			List<InscriptionPedagogique> ListInsEtape = (List<InscriptionPedagogique>) session.getAttribute("insP");

			System.out.println("ok++++++" + ListInsEtape.size());
			for (int i = 0; i < ListInsEtape.size(); i++) {
				System.out.println("ok++++++" + ListInsEtape.get(i).getMassarEtud());
			}
			System.out.println("inscription-semestre");

			List<Semestre> semestresChoisi = meInscriptionsPedagogique.getSembyEtape(ListInsEtape.get(0).getIDEtape());
			session.setAttribute("semestresChoisi", semestresChoisi);

			session.setAttribute("insP", ListInsEtape);
			request.setAttribute("etudiants", etudiants);
			request.setAttribute("insA", inscriptionAdministratives);
			request.setAttribute("etapes", etapes);
			request.setAttribute("semestre", semestres);
			this.getServletContext().getRequestDispatcher("/inscrire-semestre.jsp").forward(request, response);
			return;
		}

		else if (path.equals("/inscrire-Sem.php")) {
			List<InscriptionPedagogique> ListInsEtape = (List<InscriptionPedagogique>) session.getAttribute("insP");
			String semestre = request.getParameter("semestre");

			int IDSemestre = 0;

			List<InscriptionPedagogique> ListInsSem = new ArrayList<InscriptionPedagogique>();
			for (int i = 0; i < semestres.size(); i++) {

				if (semestres.get(i).getLabelleSemestre().equals(semestre)) {
					System.out.print("hier i=" + i);
					IDSemestre = semestres.get(i).getIDSemestre();
				}
			}
			session.setAttribute("semChoisi", IDSemestre);

			for (String IDIA : request.getParameterValues("idia")) {
				for (int i = 0; i < ListInsEtape.size(); i++) {
					if (ListInsEtape.get(i).getIDIA() == Integer.parseInt(IDIA)) {
						meInscriptionsPedagogique.addSem(ListInsEtape.get(i), IDSemestre);

						InscriptionPedagogique ins = new InscriptionPedagogique();
						ins.setIDEtape(ListInsEtape.get(i).getIDEtape());
						ins.setMassarEtud(ListInsEtape.get(i).getMassarEtud());
						ins.setIDSemestre(IDSemestre);
						int IDIP = 0;

						for (int j = 0; j < listPeda.size(); j++) {

							if (listPeda.get(j).getIDIA() == Integer.parseInt(IDIA)) {
								IDIP = listPeda.get(j).getIDIP();

							}
						}

						ins.setIDIP(IDIP);

						ins.setIDIA(Integer.parseInt(IDIA));

						ListInsSem.add(ins);
					}
				}

			}
			session.setAttribute("insS", ListInsSem);
			request.setAttribute("semestre", semestres);
			request.setAttribute("ListInsEtape", ListInsEtape);
			request.setAttribute("insA", inscriptionAdministratives);
			request.setAttribute("etudiants", etudiants);
			request.setAttribute("etapes", etapes);

			this.getServletContext().getRequestDispatcher("/inscrire-semestre.jsp").forward(request, response);
			return;

		}
		// inscrire semestre jsp
		else if (path.equals("/inscrire-module")) {
			List<InscriptionPedagogique> ListInsEtape = (List<InscriptionPedagogique>) session.getAttribute("insS");

			for (int i = 0; i < ListInsEtape.size(); i++) {
				System.out.println("ok++++++" + ListInsEtape.get(i).getMassarEtud());
			}
			System.out.println("inscription-semestre");
			request.setAttribute("semestre", semestres);

			request.setAttribute("insP", ListInsEtape);
			request.setAttribute("etudiants", etudiants);
			request.setAttribute("insA", inscriptionAdministratives);
			request.setAttribute("etapes", etapes);

			List<Module> moduleChoisi = meInscriptionsPedagogique.getModbySem((int) session.getAttribute("semChoisi"));
			session.setAttribute("moduleChoisi", moduleChoisi);
			request.setAttribute("modules", modules);

			this.getServletContext().getRequestDispatcher("/inscrire-module.jsp").forward(request, response);
			return;
		} else if (path.equals("/inscrire-Mod.php")) {
			List<InscriptionPedagogique> ListInsEtape = (List<InscriptionPedagogique>) session.getAttribute("insS");
			String module = request.getParameter("module");

			int IDModule = 0;

			List<InscriptionPedagogique> ListInsSem = new ArrayList<InscriptionPedagogique>();
			for (int i = 0; i < modules.size(); i++) {

				if (modules.get(i).getLabelleMod().equals(module)) {

					IDModule = modules.get(i).getIdModule();
				}
			}

			for (String IDIA : request.getParameterValues("idia")) {

				for (int i = 0; i < ListInsEtape.size(); i++) {
					if (ListInsEtape.get(i).getIDIA() == Integer.parseInt(IDIA)) {
						meInscriptionsPedagogique.addMod(ListInsEtape.get(i), IDModule);
						InscriptionPedagogique ins = new InscriptionPedagogique();
						ins.setIDEtape(ListInsEtape.get(i).getIDEtape());
						ins.setMassarEtud(ListInsEtape.get(i).getMassarEtud());

						ins.setIDModule(IDModule);
						ListInsSem.add(ins);
					}
				}

			}
			session.setAttribute("insM", ListInsSem);
			request.setAttribute("semestre", semestres);
			request.setAttribute("modules", modules);
			request.setAttribute("ListInsEtape", ListInsEtape);
			request.setAttribute("insA", inscriptionAdministratives);
			request.setAttribute("etudiants", etudiants);
			request.setAttribute("etapes", etapes);

			this.getServletContext().getRequestDispatcher("/inscrire-module.jsp").forward(request, response);
			return;

		} else if (path.equals("/home")) {
			this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
			return;

		}

	}

	public String aujourdhui() {
		final Date date = new Date();
		return new SimpleDateFormat("dd-MM-yyyy").format(date);
	}

}
