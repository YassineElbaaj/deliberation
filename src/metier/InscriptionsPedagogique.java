package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import interfaces.IInscriptionsPedagogique;
import metierEntite.Etape;
import metierEntite.InscriptionAdministrative;
import metierEntite.InscriptionPedagogique;
import metierEntite.Semestre;
import metierEntite.Module;


public class InscriptionsPedagogique implements IInscriptionsPedagogique {

	@Override
	public List<Etape> listEtapes() {
		Connection conn = SingletonConnection.getConnectionBd();
		List<Etape> Etapes = new ArrayList<Etape>();
		PreparedStatement ps;
		try {
			 ps = conn.prepareStatement("select * from etape");
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()) {
				 Etape et=new Etape();
				 et.setIDEtape(rs.getInt("IDEtape"));
				 et.setLabelleEtape(rs.getString("LabelleEtape"));
				 et.setIDFiliere(rs.getInt("IDFiliere"));
				 Etapes.add(et);
			 }
			 ps.close();
		     conn.close();
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		return Etapes;
	}
	@Override
    public List<Semestre> listSe() {
         Connection conn = SingletonConnection.getConnectionBd();
            List<Semestre> Se = new ArrayList<Semestre>();
            PreparedStatement ps;
            try {
                 ps = conn.prepareStatement("select * from semestre");
                 ResultSet rs = ps.executeQuery();
                 while(rs.next()) {
                     Semestre se=new Semestre();
                     se.setIDSemestre(rs.getInt("IDSemestre"));
                     se.setLabelleSemestre(rs.getString("LabelleSem"));
                     se.setIDEtape(rs.getInt("IDEtape"));
                     Se.add(se);
                 }
                 ps.close();
                 conn.close();

            }

            catch (Exception e) {
                e.printStackTrace();
            }
            return Se;
        }
	@Override
	public void add(InscriptionPedagogique inscriptionPedagogique) {
		 Connection conn = SingletonConnection.getConnectionBd();
		 PreparedStatement ps;
		 try {
            ps=conn.prepareStatement("insert into inscriptionspedagogique(IDIA,massarEtud,IDEtape)values(?,?,?)");
            ps.setInt(1, inscriptionPedagogique.getIDIA());
            ps.setString(2, inscriptionPedagogique.getMassarEtud());
            ps.setInt(3, inscriptionPedagogique.getIDEtape());
           
            ps.executeUpdate();
            
            ps.close();
             conn.close();

        }

        catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	@Override
	public List getlistInP() {
		 Connection conn = SingletonConnection.getConnectionBd();
		 List<InscriptionPedagogique> insP=new ArrayList<InscriptionPedagogique>();
		 PreparedStatement ps;
		 try {
            ps = conn.prepareStatement("select * from inscriptionspedagogique");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
            	InscriptionPedagogique se=new InscriptionPedagogique();
                se.setIDIA(rs.getInt("IDIA"));
                se.setIDEtape(rs.getInt("IDEtape"));
                se.setMassarEtud(rs.getString("massarEtud"));
                se.setIDModule(rs.getInt("IDModule"));
                se.setIDSemestre(rs.getInt("IDSemestre"));
                se.setIDIP(rs.getInt("IDIP"));
                insP.add(se);
                
            }
            ps.close();
             conn.close();

        }

        catch (Exception e) {
            e.printStackTrace();
        }
		return insP;
	}
	@Override
	public void addSem(InscriptionPedagogique inscriptionPedagogique, int IDSemestre) {
		 Connection conn = SingletonConnection.getConnectionBd();
		 PreparedStatement ps;
		 
		 try {
	            ps = conn.prepareStatement("UPDATE inscriptionspedagogique SET IDSemestre=? where IDIP=?");

	            ps.setInt(1, IDSemestre);
	            System.out.println("hammmmmmmmmmmmmmmmmid idip="+ inscriptionPedagogique.getIDIP());
	            ps.setInt(2, inscriptionPedagogique.getIDIP());
	            ps.executeUpdate();
	            ps.close();
	             conn.close();
	             
	             System.out.println("SEMESTRE UPDATED");

	        }

	        catch (Exception e) {
	            e.printStackTrace();
	        }
		 
	}
	@Override
	public List<Module> listMod() {
		Connection conn = SingletonConnection.getConnectionBd();
        List<Module> module = new ArrayList<Module>();
        PreparedStatement ps;
        try {
             ps = conn.prepareStatement("select * from module");
             ResultSet rs = ps.executeQuery();
             while(rs.next()) {
                 Module mod=new Module();
                 mod.setIdModule(rs.getInt("IDModule"));
                 mod.setLabelleMod(rs.getString("LabelleMod"));
                 mod.setIDSemestre(rs.getInt("IDSemestre"));
                 module.add(mod);
                 
             }
             ps.close();
             conn.close();

        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return module;
	}
	@Override
	public List<Semestre> getSembyEtape(int IDEtape) {
		  Connection conn = SingletonConnection.getConnectionBd();
          List<Semestre> Se = new ArrayList<Semestre>();
          PreparedStatement ps;
          try {
               ps = conn.prepareStatement("select * from semestre where IDEtape=?");
               ps.setInt(1, IDEtape);
               ResultSet rs = ps.executeQuery();
               
               while(rs.next()) {
                   Semestre se=new Semestre();
                   se.setIDSemestre(rs.getInt("IDSemestre"));
                   se.setLabelleSemestre(rs.getString("LabelleSem"));
                   se.setIDEtape(rs.getInt("IDEtape"));
                   Se.add(se);
               }
               ps.close();
               conn.close();

          }

          catch (Exception e) {
              e.printStackTrace();
          }
          return Se;
	}
	@Override
	public void addMod(InscriptionPedagogique inscriptionPedagogique, int IDModule) {
		Connection conn = SingletonConnection.getConnectionBd();
		 PreparedStatement ps;
		 System.out.println("Module UPDATED1");
		 try {
	            ps = conn.prepareStatement("UPDATE inscriptionspedagogique SET IDModule=? where IDIP=?");

	            ps.setInt(1, IDModule);
	            ps.setInt(2, inscriptionPedagogique.getIDIP());
	            ps.executeUpdate();
	            ps.close();
	            conn.close();
	             
	             System.out.println("Module UPDATED");

	        }

	        catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}
	@Override
	public List<Module> getModbySem(int IDSemestre) {
		  Connection conn = SingletonConnection.getConnectionBd();
          List<Module> Se = new ArrayList<Module>();
          PreparedStatement ps;
          try {
               ps = conn.prepareStatement("select * from module where IDSemestre=?");
               ps.setInt(1, IDSemestre);
               ResultSet rs = ps.executeQuery();
               
               while(rs.next()) {
                   Module se=new Module();
                   se.setIdModule(rs.getInt("IDModule"));
                   se.setLabelleMod(rs.getString("LabelleMod"));
                   se.setIDSemestre(rs.getInt("IDSemestre"));
                   Se.add(se);
               }
               
               ps.close();
               conn.close();

          }

          catch (Exception e) {
              e.printStackTrace();
          }
          return Se;
	}
	@Override
	public List<Etape> getEtapbyFiliere(int IDFiliere) {
		Connection conn = SingletonConnection.getConnectionBd();
        List<Etape> Se = new ArrayList<Etape>();
        PreparedStatement ps;
        try {
             ps = conn.prepareStatement("select * from etape where IDFiliere=?");
             ps.setInt(1, IDFiliere);
             ResultSet rs = ps.executeQuery();
             
             while(rs.next()) {
                Etape etape=new Etape();
                etape.setIDEtape(rs.getInt("IDEtape"));
                etape.setLabelleEtape(rs.getString("LabelleEtape"));
                etape.setIDFiliere(rs.getInt("IDFiliere"));
                Se.add(etape);
                
             }
            
             ps.close();
             conn.close();

        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return Se;
	}
	
}
