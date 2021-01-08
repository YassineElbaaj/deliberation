package metierEntite;

public class Note {

	
	
	private int id;
	private double note;
	private int module;
	private String etudiant;
	
	
	public Note( double note, int module, String etudiant) {

		this.note = note;
		this.module = module;
		this.etudiant = etudiant;
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
	@Override
	public String toString() {
		return "Note [id=" + id + ", note=" + note + ", module=" + module + ", etudiant=" + etudiant + "]";
	}
	
	
	
	
}
