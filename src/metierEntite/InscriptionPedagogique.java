package metierEntite;

public class InscriptionPedagogique {
	
	private int IDIP;
	private int IDIA;
	private String massarEtud;
	private int IDEtape;
	private int IDSemestre;
	private int IDModule;
	
	public InscriptionPedagogique() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InscriptionPedagogique(int iDIP, int iDIA, String massarEtud, int iDEtape, int iDSemestre, int iDModule) {
		super();
		IDIP = iDIP;
		IDIA = iDIA;
		this.massarEtud = massarEtud;
		IDEtape = iDEtape;
		IDSemestre = iDSemestre;
		IDModule = iDModule;
	}

	public int getIDIP() {
		return IDIP;
	}

	public void setIDIP(int iDIP) {
		IDIP = iDIP;
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

	public int getIDEtape() {
		return IDEtape;
	}

	public void setIDEtape(int iDEtape) {
		IDEtape = iDEtape;
	}

	public int getIDSemestre() {
		return IDSemestre;
	}

	public void setIDSemestre(int iDSemestre) {
		IDSemestre = iDSemestre;
	}

	public int getIDModule() {
		return IDModule;
	}

	public void setIDModule(int iDModule) {
		IDModule = iDModule;
	}

	@Override
	public String toString() {
		return "InscriptionPedagogique [IDIP=" + IDIP + ", IDIA=" + IDIA + ", massarEtud=" + massarEtud + ", IDEtape="
				+ IDEtape + ", IDSemestre=" + IDSemestre + ", IDModule=" + IDModule + "]";
	}
	
	
}
