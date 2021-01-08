package interfaces;

import java.io.InputStream;
import java.util.List;

import metierEntite.Etudiant;

public interface IInscriptionsEnligne {
	
	public boolean addEt(Etudiant etudiant);
	public List<Etudiant> listEt();
	public boolean deleteEt(String massarEtud);
	public Etudiant getEt(String massarEtud);
	public void updateEt(Etudiant etudiant);
	public void addEtExcel(InputStream file);
}
