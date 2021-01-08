package metierEntite;

public class InscriptionAdministrative {
	private int IDIA;
	private String massarEtud;
	private String cne;//
	private String dip;//
	private String adressePerso;//
	private String adresseParent;//
	private String ville;//
	private String tele;//
	private String mail;//
	private int an_acad;
	private String bourse;//
	
	private String etablissement;
	private String filiere;
	private String date_validant;
	private String operateur_validant;
	public InscriptionAdministrative(int iDIA, String massarEtud, String cne, String dip, String adressePerso,
			String adresseParent, String ville, String tele, String mail, int an_acad, String bourse,
			String etablissement, String filiere, String date_validant, String operateur_validant) {
		super();
		IDIA = iDIA;
		this.massarEtud = massarEtud;
		this.cne = cne;
		this.dip = dip;
		this.adressePerso = adressePerso;
		this.adresseParent = adresseParent;
		this.ville = ville;
		this.tele = tele;
		this.mail = mail;
		this.an_acad = an_acad;
		this.bourse = bourse;
		this.etablissement = etablissement;
		this.filiere = filiere;
		this.date_validant = date_validant;
		this.operateur_validant = operateur_validant;
	}
	public InscriptionAdministrative() {
		// TODO Auto-generated constructor stub
	}
	public int getIDIA() {
		return IDIA;
	}
	public void setIDIA(int iDIA) {
		IDIA = iDIA;
	}
	public String getMassarEtud() {
		return massarEtud;
	}
	public void setMassarEtud(String massarEtud) {
		this.massarEtud = massarEtud;
	}
	public String getCne() {
		return cne;
	}
	public void setCne(String cne) {
		this.cne = cne;
	}
	public String getDip() {
		return dip;
	}
	public void setDip(String dip) {
		this.dip = dip;
	}
	public String getAdressePerso() {
		return adressePerso;
	}
	public void setAdressePerso(String adressePerso) {
		this.adressePerso = adressePerso;
	}
	public String getAdresseParent() {
		return adresseParent;
	}
	public void setAdresseParent(String adresseParent) {
		this.adresseParent = adresseParent;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getAn_acad() {
		return an_acad;
	}
	public void setAn_acad(int an_acad) {
		this.an_acad = an_acad;
	}
	public String getBourse() {
		return bourse;
	}
	public void setBourse(String bourse) {
		this.bourse = bourse;
	}
	public String getEtablissement() {
		return etablissement;
	}
	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	public String getDate_validant() {
		return date_validant;
	}
	public void setDate_validant(String date_validant) {
		this.date_validant = date_validant;
	}
	public String getOperateur_validant() {
		return operateur_validant;
	}
	public void setOperateur_validant(String operateur_validant) {
		this.operateur_validant = operateur_validant;
	}
	
	@Override
	public String toString() {
		return "InscriptionAdministrative [IDIA=" + IDIA + ", massarEtud=" + massarEtud + ", cne=" + cne + ", dip="
				+ dip + ", adressePerso=" + adressePerso + ", adresseParent=" + adresseParent + ", ville=" + ville
				+ ", tele=" + tele + ", mail=" + mail + ", an_acad=" + an_acad + ", bourse=" + bourse
				+ ", etablissement=" + etablissement + ", filiere=" + filiere + ", date_validant=" + date_validant
				+ ", operateur_validant=" + operateur_validant + "]";
	}
	
	
	
	
}
