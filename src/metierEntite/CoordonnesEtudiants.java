package metierEntite;

public class CoordonnesEtudiants {
	private int IDJEtudiants ;
	private String cne;//
	private String dip;//
	private String adressePerso;//
	private String adresseParent;//
	private String ville;//
	private String tele;//
	private String mail;//

	private String bourse;//

	public int getIDJEtudiants() {
		return IDJEtudiants;
	}

	public void setIDJEtudiants(int iDJEtudiants) {
		IDJEtudiants = iDJEtudiants;
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

	public String getBourse() {
		return bourse;
	}

	public void setBourse(String bourse) {
		this.bourse = bourse;
	}

	@Override
	public String toString() {
		return "Coordonn�sEtudiants [IDJEtudiants=" + IDJEtudiants + ", cne=" + cne + ", dip=" + dip + ", adressePerso="
				+ adressePerso + ", adresseParent=" + adresseParent + ", ville=" + ville + ", tele=" + tele + ", mail="
				+ mail + ", bourse=" + bourse + "]";
	}

	public CoordonnesEtudiants(int iDJEtudiants, String cne, String dip, String adressePerso, String adresseParent,
			String ville, String tele, String mail, String bourse) {
		super();
		IDJEtudiants = iDJEtudiants;
		this.cne = cne;
		this.dip = dip;
		this.adressePerso = adressePerso;
		this.adresseParent = adresseParent;
		this.ville = ville;
		this.tele = tele;
		this.mail = mail;
		this.bourse = bourse;
	}

	public CoordonnesEtudiants() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
