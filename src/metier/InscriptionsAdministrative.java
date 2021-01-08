package metier;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import interfaces.IInscriptionsAdministrative;
import metierEntite.An_acad;
import metierEntite.Etablissement;
import metierEntite.Etudiant;
import metierEntite.Filiere;
import metierEntite.InscriptionAdministrative;

public class InscriptionsAdministrative implements IInscriptionsAdministrative {

	

	@Override
	public List<Etablissement> listEtab() {
		List<Etablissement> Etablissements = new ArrayList<Etablissement>();
		Connection conn = SingletonConnection.getConnectionBd();
		PreparedStatement ps;
		try {
			
			 ps = conn.prepareStatement("SELECT * from etablissements");
			 ResultSet rs = ps.executeQuery();
			 while(rs.next())
			 {
				 Etablissement etab=new Etablissement();
				 etab.setIDEtablissement(rs.getInt("IDEtablissement"));
				 etab.setEtablissement(rs.getString("etablissement"));
				 etab.setDescription(rs.getString("description"));
				 Etablissements.add(etab);
			 }
			
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		return Etablissements;
		
	}

	@Override
	public List<Filiere> listFil() {
		List<Filiere> Filieres = new ArrayList<Filiere>();
		Connection conn = SingletonConnection.getConnectionBd();
		PreparedStatement ps;
		try {
			
			 ps = conn.prepareStatement("SELECT * from filieres");
			 ResultSet rs = ps.executeQuery();
			 while(rs.next())
			 {
				 Filiere filiere=new Filiere();
				 filiere.setIDFiliere(rs.getInt("IDFiliere"));
				 filiere.setFiliere(rs.getString("filiere"));
				 Filieres.add(filiere);
			 }
			
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		return Filieres;
	}
	
	@Override
	public int getIdEtab(String etablissement) {
		Connection conn = SingletonConnection.getConnectionBd();
		PreparedStatement ps;
		int ID = 0;
		try {
			
			 ps = conn.prepareStatement("SELECT IDEtablissement from etablissements WHERE etablissement=?");
			 ps.setString(1, etablissement);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next())
			 {
				ID=rs.getInt("IDEtablissement");
			 }
			 
			
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		return ID;
		
	}

	@Override
	public int getIdFil(String filiere) {
		Connection conn = SingletonConnection.getConnectionBd();
		PreparedStatement ps;
		int ID = 0;
		try {
			
			 ps = conn.prepareStatement("SELECT IDFiliere from filieres WHERE filiere=?");
			 ps.setString(1, filiere);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next())
			 {
				ID=rs.getInt("IDFiliere");
			 }
			 
			
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		return ID;
		
	}

	

	@Override
	public boolean addAdmini(InscriptionAdministrative inscriptionAd) {
		
		Connection conn = SingletonConnection.getConnectionBd();
		
		PreparedStatement ps;
		
		int IDFiliere=getIdFil(inscriptionAd.getFiliere());
		int IDEtablissemnt=getIdEtab(inscriptionAd.getEtablissement());
		System.out.println("IDEtablissemnt="+IDEtablissemnt);
		System.out.println("IDFiliere"+IDFiliere);
		try {
		       ps = conn.prepareStatement("INSERT INTO inscriptionsadministrative (massarEtud,cne,dip,adressePerso,adresseParent,ville,tele,mail,an_acad,bourse,IDEtablissement,IDFiliere, date_validation,operateur_validant)"
		       		+ " VALUES ( ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?) ");
		     
		       ps.setString(1, inscriptionAd.getMassarEtud());
		       ps.setString(2, inscriptionAd.getCne());
		       ps.setString(3, inscriptionAd.getDip());
		       ps.setString(4, inscriptionAd.getAdressePerso());
		       ps.setString(5, inscriptionAd.getAdresseParent());
		       ps.setString(6, inscriptionAd.getVille());
		       ps.setString(7, inscriptionAd.getTele());
		       ps.setString(8, inscriptionAd.getMail());
		       int an_acad=inscriptionAd.getAn_acad();
		       ps.setInt(9, an_acad);
		       ps.setString(10, inscriptionAd.getBourse());
		       ps.setInt(11, IDEtablissemnt);
		       ps.setInt(12, IDFiliere);
		       ps.setString(13, inscriptionAd.getDate_validant());
		       ps.setString(14, inscriptionAd.getOperateur_validant());
		       
		       ps.executeUpdate();
		      
		      
		       ps.close();
		      
		       conn.close(); 
		       
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
    public List<InscriptionAdministrative> listInsA() {
        List<InscriptionAdministrative> InscriptionAdministrative = new ArrayList<InscriptionAdministrative>();
        Connection conn = SingletonConnection.getConnectionBd();
        PreparedStatement ps;
        try {
            ps=conn.prepareStatement("SELECT * from inscriptionsadministrative");

            ResultSet rs = ps.executeQuery();
            System.out.println("dans list inscription add");
            while(rs.next()) {


                InscriptionAdministrative IA=new InscriptionAdministrative();
                IA.setIDIA(rs.getInt("IDIA"));
                IA.setMassarEtud(rs.getString("massarEtud"));
                IA.setCne(rs.getString("cne"));
                IA.setDip(rs.getString("dip"));
                IA.setAdressePerso(rs.getString("adressePerso"));
                IA.setAdresseParent(rs.getString("adresseParent"));
                IA.setVille(rs.getString("ville"));
                IA.setTele(rs.getString("tele"));
                IA.setMail(rs.getString("mail"));;
                IA.setAn_acad(rs.getInt("an_acad"));
                IA.setBourse(rs.getString("bourse"));
                int etab=rs.getInt("IDEtablissement");
                IA.setEtablissement(String.valueOf(etab));
                int fil=rs.getInt("IDFiliere");
                IA.setFiliere(String.valueOf(fil));
                IA.setDate_validant(rs.getString("date_validation"));
                IA.setOperateur_validant(rs.getString("operateur_validant"));

                System.out.print(IA.toString());
                InscriptionAdministrative.add(IA);


            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return InscriptionAdministrative;
    }

	@Override
	public List<String> an_acad() {
		List<String> an_acad = new ArrayList<String>();
        Connection conn = SingletonConnection.getConnectionBd();
        PreparedStatement ps;
        try
        {
        	 ps=conn.prepareStatement("select an_acad from inscriptionsadministrative");
        	 ResultSet rs = ps.executeQuery();
             System.out.println("dans list ann�e");
             while(rs.next())
             {
            	 an_acad.add(rs.getString("an_acad"));
             }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return an_acad;
		
	}

	@Override
	public boolean deleteEt(int IDIA) {
		Connection conn = SingletonConnection.getConnectionBd();
		PreparedStatement ps;
		boolean ok = false;
		try {
		       ps = conn.prepareStatement("delete from inscriptionsadministrative where IDIA = ?");
		       ps.setInt(1, IDIA);
		       ok=ps.execute();
		       System.out.print("delete : ");
		       
		       ps.close();
		       conn.close();
		       
		       
		       
		       
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	public void addEtExcel(InputStream file) {
		ArrayList<InscriptionAdministrative> inscriptionAdministratives=new ArrayList<InscriptionAdministrative>();
		ArrayList<String> values=new ArrayList<String>();
		InscriptionAdministrative inA=new InscriptionAdministrative();
	System.out.print("Dans excel inscriptions adiministratives:"+file.toString());

	
		try
		{
			HSSFWorkbook wb=new HSSFWorkbook(file);// preciser que ce document est excel
			HSSFSheet sheet=wb.getSheetAt(0);
			Iterator rows=sheet.rowIterator();
			while(rows.hasNext())
			{
				System.out.println("   maha   ");
				values.clear();
				HSSFRow row=(HSSFRow) rows.next();
				Iterator cells=row.cellIterator();
			
				while(cells.hasNext())
				{
					
					
					HSSFCell cell= (HSSFCell) cells.next();
					
					if(cell.getCellType()==Cell.CELL_TYPE_STRING) 
						values.add(cell.getStringCellValue()); 
					else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC) 
						{values.add(Integer.toString((int)(cell.getNumericCellValue())));
						
						}
				
					
					
				}
				
					
					inA.setMassarEtud(values.get(0));
					inA.setCne(values.get(1));
					inA.setDip(values.get(2));
					inA.setAdressePerso(values.get(3));
					inA.setAdresseParent(values.get(4));
					inA.setVille(values.get(5));
					inA.setTele(values.get(6));
					inA.setMail(values.get(7));
					inA.setAn_acad(getIdAn_acad(values.get(8)));
					
					inA.setBourse(values.get(9));
					inA.setEtablissement(values.get(10));
					inA.setFiliere(values.get(11));
					String date_validant =aujourdhui();
					inA.setDate_validant(date_validant);
					inA.setOperateur_validant("admin");
					inscriptionAdministratives.add(inA);
					
					
			for (int i=1;i<inscriptionAdministratives.size();i++)
			{
			addAdmini(inscriptionAdministratives.get(i));
				
			}
			}
			
			
			System.out.println("size="+inscriptionAdministratives.size());
			
			
		}
		
		catch (IOException ex) {
			ex.printStackTrace();
			ex.getMessage();
		}
		
	}
	public int getIdAn_acad(String an_acad) {
		Connection conn = SingletonConnection.getConnectionBd();
		PreparedStatement ps;
		int ID = 0;
		try {
			
			 ps = conn.prepareStatement("SELECT IDan_acad from an_acad WHERE Labellean_acad=?");
			 ps.setString(1, an_acad);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next())
			 {
				ID=rs.getInt("IDan_acad");
			 }
			 
			
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return ID;
	}

	public List<An_acad> listAn() {
		List<An_acad> an_acad = new ArrayList<An_acad>();
		Connection conn = SingletonConnection.getConnectionBd();
		PreparedStatement ps;
		try {
			
			 ps = conn.prepareStatement("SELECT * from an_acad");
			 ResultSet rs = ps.executeQuery();
			 while(rs.next())
			 {
				 An_acad anAcad=new An_acad();
				
				 anAcad.setAn_acad(rs.getInt("IDan_acad"));
				 anAcad.setLabellean_acad(rs.getString("Labellean_acad"));
				 an_acad.add(anAcad);
			 }
			
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		return an_acad;
	}

	public String aujourdhui() {
	       final Date date = new Date();
	       return new SimpleDateFormat("dd-MM-yyyy").format(date);
	}

	



}
