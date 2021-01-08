package interfaces;

import java.util.List;

import metier.InscriptionsPedagogique;
import metierEntite.Etape;
import metierEntite.Module;
import metierEntite.InscriptionAdministrative;
import metierEntite.InscriptionPedagogique;
import metierEntite.Semestre;


public interface IInscriptionsPedagogique {
	
	public List<Etape> listEtapes();
	
	public List<Semestre> listSe();
	public List<Module> listMod();
	public void add(InscriptionPedagogique inscriptionPedagogique);
	public List <Etape>getEtapbyFiliere(int IDFiliere);
	public List <Semestre>getSembyEtape(int IDEtape);
	public List <Module>getModbySem(int IDSem);
	public void addSem(InscriptionPedagogique inscriptionPedagogique,int IDSemestre);
	public void addMod(InscriptionPedagogique inscriptionPedagogique,int IDModule);
	public List getlistInP();

	
	

	
	

}
