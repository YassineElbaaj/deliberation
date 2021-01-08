package metierEntite;

public class Module {
private  int idModule;
private String labelleMod;
 private int IDSemestre;
public Module() {
	super();
	// TODO Auto-generated constructor stub
}
public Module(int iDModule, String labelleMod, int iDSemestre) {
	super();
	idModule = iDModule;
	this.labelleMod = labelleMod;
	IDSemestre = iDSemestre;
}
public int getIdModule() {
	return idModule;
}
public void setIdModule(int idModule) {
	this.idModule = idModule;
}
public String getLabelleMod() {
	return labelleMod;
}
public void setLabelleMod(String labelleMod) {
	this.labelleMod = labelleMod;
}
public int getIDSemestre() {
	return IDSemestre;
}
public void setIDSemestre(int iDSemestre) {
	IDSemestre = iDSemestre;
}
@Override
public String toString() {
	return "Module [idModule=" + idModule + ", labelleMod=" + labelleMod + ", IDSemestre=" + IDSemestre + "]";
}


}
