package metierEntite;

public class Filiere {
	private int IDFiliere;
	private String filiere;
	
	public Filiere(int iDFiliere, String filiere) {
		super();
		IDFiliere = iDFiliere;
		this.filiere = filiere;
	}
	
	public Filiere() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIDFiliere() {
		return IDFiliere;
	}
	public void setIDFiliere(int iDFiliere) {
		IDFiliere = iDFiliere;
	}
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}

	@Override
	public String toString() {
		return "Filiere [IDFiliere=" + IDFiliere + ", filiere=" + filiere + "]";
	}
	
	
	
	
	
	

}
