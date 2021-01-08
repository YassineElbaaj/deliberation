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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import interfaces.IInscriptionsEnligne;
import metierEntite.Etudiant;

public class InscriptionsEnligne implements IInscriptionsEnligne {
	 private Map<String, String> erreurs = new HashMap<String, String>();
	 boolean a=false;
	 public boolean addEt(Etudiant etudiant){
		Connection conn = SingletonConnection.getConnectionBd();
		PreparedStatement ps;
		
		try {
		       ps = conn.prepareStatement("insert into etudiants values(?, ?, ?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
		       
		       ps.setString(1,etudiant.getNomFr());
		       ps.setString(2,etudiant.getNomAr());
		       ps.setString(3,etudiant.getPrenomAr());
		       ps.setString(4,etudiant.getPrenomFr());
		       ps.setString(5,etudiant.getAcad());
		       ps.setString(6,etudiant.getAn_Bac());
		       ps.setString(7,etudiant.getCin());
		      
		       
		       ps.setString(8, etudiant.getDate1in());
		       java.sql.Date date_sql = new java.sql.Date(etudiant.getDateN().getTime());
		       ps.setDate(9, date_sql);
		       
		 
		       ps.setString(10,etudiant.getVilleBac());
		       ps.setString(11,etudiant.getLieuN_ar());
		       ps.setString(12,etudiant.getLieuN_fr());
		       ps.setString(13,etudiant.getVilleNaissance());
		       ps.setString(14,etudiant.getLycee());
		       ps.setString(15,etudiant.getMassarEtud());
		       ps.setString(16,etudiant.getMt());
		       ps.setString(17,etudiant.getNationalite());
		       
		       ps.setString(18,etudiant.getProvince());
		       ps.setString(19,etudiant.getsBac());
		       ps.setString(20,etudiant.getSexe());
		       ps.setString(21,etudiant.getRegion());
		       
		      
		       
		       ps.setString(22,etudiant.getEtatPhy());
		       ps.setString(23,etudiant.getGroupSocio());
		       
		       
		       
		       ps.setBlob(24, etudiant.getPhoto());
		      
			      
		      
		      
		       ps.executeUpdate();
		       a=true;
		      
		       ps.close();
		      
		       conn.close(); 
		       
		       
				
			}
			
			catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	 }
	 
	 	

		@Override
		public List<Etudiant> listEt() {
			List<Etudiant> Etudiants = new ArrayList<Etudiant>();
			Connection conn = SingletonConnection.getConnectionBd();
			PreparedStatement ps;
			try {
				ps = conn.prepareStatement("SELECT * from etudiants");
				ResultSet rs = ps.executeQuery();
				System.out.println("dans list etudiant");
				while(rs.next()) {
					System.out.println("dans list etudiantok");
					Etudiant etudiant=new Etudiant();
					etudiant.setNomFr(rs.getString("NomFr"));
					etudiant.setNomAr(rs.getString("NomAr"));	
					etudiant.setPrenomAr(rs.getString("PrenomAr"));	
					etudiant.setPrenomFr(rs.getString("PrenomFr"));	
					etudiant.setAcad(rs.getString("acad"));
					etudiant.setAn_Bac(rs.getString("an_Bac"));
					etudiant.setCin(rs.getString("cin"));
					etudiant.setDate1in(rs.getString("date1in"));
					etudiant.setDateN(rs.getDate("dateN"));
					etudiant.setVilleBac(rs.getString("villeBac"));
					etudiant.setLieuN_ar(rs.getString("lieuN_ar"));
					etudiant.setLieuN_fr(rs.getString("lieuN_fr"));
					etudiant.setVilleNaissance(rs.getString("villeNaissance"));
					etudiant.setLycee(rs.getString("lycee"));
					etudiant.setMassarEtud(rs.getString("massarEtud"));
					etudiant.setMt(rs.getString("mt"));
					etudiant.setNationalite(rs.getString("nationalite"));
					etudiant.setProvince(rs.getString("province"));
					etudiant.setsBac(rs.getString("sBac"));
					etudiant.setSexe(rs.getString("sexe"));
					etudiant.setRegion(rs.getString("region"));
					etudiant.setEtatPhy(rs.getString("etatPhy"));
					etudiant.setGroupSocio(rs.getString("groupSocio"));
					etudiant.setPhoto(rs.getBinaryStream("photo"));
					System.out.print(etudiant.toString());
					Etudiants.add(etudiant);
					
				}
				 
				ps.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return Etudiants;
		}

		@Override
		public boolean deleteEt(String massarEtud) {
			Connection conn = SingletonConnection.getConnectionBd();
			PreparedStatement ps;
			boolean ok = false;
			try {
			       ps = conn.prepareStatement("delete from etudiants where massarEtud = ?");
			       ps.setString(1, massarEtud);
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
		public Etudiant getEt(String massarEtud) {
			Connection conn = SingletonConnection.getConnectionBd();
			PreparedStatement ps;
			Etudiant etudiant =new Etudiant();
			
			try {
			       ps = conn.prepareStatement("select * from etudiants where massarEtud = ?");
			       ps.setString(1, massarEtud);
			       ResultSet rs = ps.executeQuery();
			       System.out.print("getEtudiant");
			       
			       while(rs.next()) {
						etudiant.setNomFr(rs.getString("NomFr"));
						etudiant.setNomAr(rs.getString("NomAr"));	
						etudiant.setPrenomAr(rs.getString("PrenomAr"));	
						etudiant.setPrenomFr(rs.getString("PrenomFr"));	
						etudiant.setAcad(rs.getString("acad"));
						etudiant.setAn_Bac(rs.getString("an_Bac"));
						etudiant.setCin(rs.getString("cin"));
						etudiant.setDate1in(rs.getString("date1in"));
						etudiant.setDateN(rs.getDate("dateN"));
						etudiant.setVilleBac(rs.getString("villeBac"));
						etudiant.setLieuN_ar(rs.getString("lieuN_ar"));
						etudiant.setLieuN_fr(rs.getString("lieuN_fr"));
						etudiant.setVilleNaissance(rs.getString("villeNaissance"));
						etudiant.setLycee(rs.getString("lycee"));
						etudiant.setMassarEtud(rs.getString("massarEtud"));
						etudiant.setMt(rs.getString("mt"));
						etudiant.setNationalite(rs.getString("nationalite"));
						etudiant.setProvince(rs.getString("province"));
						etudiant.setsBac(rs.getString("sBac"));
						etudiant.setSexe(rs.getString("sexe"));
						etudiant.setRegion(rs.getString("region"));
						etudiant.setEtatPhy(rs.getString("etatPhy"));
						etudiant.setGroupSocio(rs.getString("groupSocio"));
						etudiant.setPhoto(rs.getBinaryStream("photo"));
					}
			       ps.close();
			       conn.close();
			       
			       
			       
			       
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
			
			return etudiant;
		}
		
		public void updateEt(Etudiant etudiant) {
			Connection conn = SingletonConnection.getConnectionBd();
			PreparedStatement ps;
			
			
			
			
			try
			{
				ps = conn.prepareStatement("UPDATE etudiants SET NomFr=?,NomAr=?,PrenomAr=?,PrenomFr=?,acad=?,an_Bac=?,cin=?,dateN=?"
						+ ",villeBac=?,lieuN_ar=?,lieuN_fr=?,villeNaissance=?,lycee=?,massarEtud=?,mt=?,nationalite=?,"
						+ "province=?,sBac=?,sexe=?,region=?,etatPhy=?,GroupSocio=? WHERE massarEtud=?");
				ps.setString(1, etudiant.getNomFr());
				ps.setString(2, etudiant.getNomAr());
				ps.setString(3, etudiant.getPrenomAr());
				ps.setString(4, etudiant.getPrenomFr());
				ps.setString(5, etudiant.getAcad());
				ps.setString(6, etudiant.getAn_Bac());
				ps.setString(7, etudiant.getCin());
				java.sql.Date date_sql = new java.sql.Date(etudiant.getDateN().getTime());
				ps.setDate(8, date_sql);
				ps.setString(9, etudiant.getVilleBac());
				ps.setString(10, etudiant.getLieuN_ar());
				ps.setString(11, etudiant.getLieuN_fr());
				ps.setString(12, etudiant.getVilleNaissance());
				ps.setString(13, etudiant.getLycee());
				ps.setString(14, etudiant.getMassarEtud());
				ps.setString(15, etudiant.getMt());
				ps.setString(16, etudiant.getNationalite());
				ps.setString(17, etudiant.getProvince());
				ps.setString(18, etudiant.getsBac());
				ps.setString(19, etudiant.getSexe());
				ps.setString(20, etudiant.getRegion());
				ps.setString(21, etudiant.getEtatPhy());
				ps.setString(22, etudiant.getGroupSocio());
				ps.executeUpdate();
			    ps.close();
			
			}
			
			catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}

		
			public void addEtExcel(InputStream file) {
			
		
			ArrayList<Etudiant> Etudiants=new ArrayList<Etudiant>();
			ArrayList<String> values=new ArrayList<String>();
			Etudiant e=new Etudiant();
		System.out.print("iciiiiiiiiiiiii"+file.toString());
	
		
			try
			{
				
				System.out.print("iciiiiiiiiiiiii1");
			
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
					
						
						e.setNomFr(values.get(0));
						e.setPrenomFr(values.get(1));
						e.setNomAr(values.get(2));
						e.setPrenomAr(values.get(3));
						e.setAcad(values.get(4));
						e.setAn_Bac(values.get(5));
						e.setCin(values.get(6));
						e.setDate1in(values.get(7));
						String date=values.get(8);
						System.out.println("date="+date);
						Date dateN=null;
						try {
							dateN = new SimpleDateFormat("dd-MM-yyyy").parse(date);
							System.out.println("DATE "+dateN);
							
						} catch (ParseException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
						
						java.sql.Date date_sql=null;
						
						e.setDateN(dateN);
						System.out.println("dateN="+date_sql);
						e.setVilleBac(values.get(9));
						e.setLieuN_ar(values.get(10));
						e.setLieuN_fr(values.get(11));
						e.setVilleNaissance(values.get(12));
						e.setLycee(values.get(13));
						e.setMassarEtud(values.get(14));
						e.setMt(values.get(15));
						e.setNationalite(values.get(16));
						e.setProvince(values.get(17));
						e.setsBac(values.get(18));
						e.setSexe(values.get(19));
						e.setRegion(values.get(20));
						e.setEtatPhy(values.get(21));
						e.setGroupSocio(values.get(22));
						e.setPhoto(null);
					
				
				Etudiants.add(e);
				for (int i=1;i<Etudiants.size();i++)
				{
				addEt(Etudiants.get(i));
					
				}
				}
				
				
				System.out.println("siz="+Etudiants.size());
				
				
			}
			
			catch (IOException ex) {
				ex.printStackTrace();
				ex.getMessage();
			}
			
			
		}
		public void setErreur( String champ, String message ) {
			erreurs.put( champ, message );
		}

		public Map<String, String> getErreurs() {
			return erreurs;
		}






		

}
