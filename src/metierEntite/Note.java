package metierEntite;

public class Note {

	private int id;
	private double note;
	private int module;
	private String session;
	private String etudiant;

	public Note(double note, int module, String session, String etudiant) {

		this.note = note;
		this.module = module;
		this.session = session;
		this.etudiant = etudiant;
	}
	
	public Note() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getNote() {
		return note;
	}

	public void setNote(double note) {
		this.note = note;
	}

	public int getModule() {
		return module;
	}

	public void setModule(int module) {
		this.module = module;
	}

	public String getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(String etudiant) {
		this.etudiant = etudiant;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", note=" + note + ", module=" + module + ", session=" + session + ", etudiant="
				+ etudiant + "]";
	}
	
	
}