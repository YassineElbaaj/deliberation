package metierEntite;

public class An_acad {
  private int An_acad;
  private String Labellean_acad;
public An_acad(int an_acad, String labellean_acad) {
	super();
	An_acad = an_acad;
	Labellean_acad = labellean_acad;
}
public An_acad() {
	super();
	// TODO Auto-generated constructor stub
}
public int getAn_acad() {
	return An_acad;
}
public void setAn_acad(int an_acad) {
	An_acad = an_acad;
}
public String getLabellean_acad() {
	return Labellean_acad;
}
public void setLabellean_acad(String labellean_acad) {
	Labellean_acad = labellean_acad;
}
@Override
public String toString() {
	return "An_acad [An_acad=" + An_acad + ", Labellean_acad=" + Labellean_acad + "]";
}
  
}
