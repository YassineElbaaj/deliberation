package interfaces;

import java.io.InputStream;
import java.util.List;

import metierEntite.An_acad;
import metierEntite.Etablissement;
import metierEntite.Etudiant;
import metierEntite.Filiere;
import metierEntite.InscriptionAdministrative;

public interface IInscriptionsAdministrative {
	
	
	
	public List<Etablissement> listEtab();
	public List<Filiere> listFil();
	public List <An_acad> listAn();
	public int getIdEtab(String etablissement);
	public int getIdFil(String filiere);

	public boolean addAdmini(InscriptionAdministrative inscriptionAd);
	public List<InscriptionAdministrative> listInsA();
	public List<String> an_acad();
	public int getIdAn_acad(String an_acad);
	public boolean deleteEt(int IDIA);
	public void addEtExcel(InputStream file);

}
