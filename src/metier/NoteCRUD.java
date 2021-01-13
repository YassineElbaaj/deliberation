package metier;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import metierEntite.Note;

public class NoteCRUD {

	public void ajouterNote(Note note) throws SQLException {
		Connection conn = SingletonConnection.getConnectionBd();

		PreparedStatement ps;
		ps = conn.prepareStatement("INSERT INTO Note (note, module,session, etudiant) VALUES (?,?,?,?)");
		System.out.println(note);
		ps.setDouble(1, note.getNote());
		ps.setInt(2, note.getModule());
		ps.setString(3, note.getSession());
		ps.setString(4, note.getEtudiant());

		ps.executeUpdate();

	}

	public void importerNotes(InputStream file) throws IOException, SQLException {
		XSSFWorkbook wb = new XSSFWorkbook(file);
		// creating a Sheet object to retrieve the object
		XSSFSheet sheet = wb.getSheetAt(0);
		// evaluating cell type
		DataFormatter dataFormatter = new DataFormatter();
		FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
		Iterator<Row> rows = sheet.rowIterator();
		Row row = rows.next();
		ArrayList<String> modules = new ArrayList<String>();
		Iterator<Cell> cells = row.cellIterator();
		cells.next();
		cells.next();
		cells.next();
		Connection conn = SingletonConnection.getConnectionBd();

		PreparedStatement ps;
		while (cells.hasNext()) {
			Cell cell = cells.next();
			modules.add(dataFormatter.formatCellValue(cell));

		}

		rows = sheet.rowIterator();
		rows.next();
		try {
			while (rows.hasNext()) {
				row = rows.next();
				cells = row.cellIterator();
				cells.next();
				cells.next();
				cells.next();

				int i = 0;
				while (cells.hasNext()) {
					Cell cell = cells.next();

					// System.out.println(note.toString());
					ps = conn.prepareStatement("INSERT INTO Note (note, module, session, etudiant) VALUES (?,?,?,?)");

					String massar = row.getCell(0).getStringCellValue();
					double note = cell.getNumericCellValue();
					int module = getModuleId(modules.get(i));
					ps.setDouble(1, note);
					ps.setInt(2, module);
					ps.setString(3, "Ordinaire");
					ps.setString(4, massar);

					ps.executeUpdate();

					i++;
				}
			}
		} catch (NullPointerException e) {

		} catch (SQLIntegrityConstraintViolationException e) {

		} catch (NoSuchElementException e) {

		}

	}

	public void importerNotesRatt(InputStream file) throws IOException, SQLException {
		XSSFWorkbook wb = new XSSFWorkbook(file);
		// creating a Sheet object to retrieve the object
		XSSFSheet sheet = wb.getSheetAt(0);
		// evaluating cell type
		DataFormatter dataFormatter = new DataFormatter();
		FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
		Iterator<Row> rows = sheet.rowIterator();
		Row row = rows.next();
		ArrayList<String> modules = new ArrayList<String>();
		Iterator<Cell> cells = row.cellIterator();
		cells.next();
		cells.next();
		cells.next();
		Connection conn = SingletonConnection.getConnectionBd();

		PreparedStatement ps;
		while (cells.hasNext()) {
			Cell cell = cells.next();
			modules.add(dataFormatter.formatCellValue(cell));

		}

		rows = sheet.rowIterator();
		rows.next();
		try {
			while (rows.hasNext()) {
				row = rows.next();
				cells = row.cellIterator();
				cells.next();
				cells.next();
				cells.next();

				int i = 0;
				while (cells.hasNext()) {
					Cell cell = cells.next();

					// System.out.println(note.toString());
					ps = conn.prepareStatement("INSERT INTO Note (note, module, session, etudiant) VALUES (?,?,?,?)");

					String massar = row.getCell(0).getStringCellValue();
					double note = cell.getNumericCellValue();
					int module = getModuleId(modules.get(i));
					ps.setDouble(1, note);
					ps.setInt(2, module);
					ps.setString(3, "Rattrapage");
					ps.setString(4, massar);

					ps.executeUpdate();

					i++;
				}
			}
		} catch (NullPointerException e) {

		} catch (SQLIntegrityConstraintViolationException e) {

		} catch (NoSuchElementException e) {

		}

	}

	public int getModuleId(String nom) throws SQLException {
		Connection conn = SingletonConnection.getConnectionBd();

		PreparedStatement ps;
		ps = conn.prepareStatement("select IDModule from module where LabelleMod=?");
		ps.setString(1, nom);

		ResultSet rs = ps.executeQuery();
		rs.next();

		return rs.getInt("IDModule");
	}

	public void deliberation(int semestre) throws SQLException, IOException {
		Connection conn = SingletonConnection.getConnectionBd();
		Workbook workbook = new XSSFWorkbook();
		CreationHelper createHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("Deliberation");
        Font headerFont = workbook.createFont();
        headerFont.setBold(false);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLACK.getIndex());
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);
		PreparedStatement ps;
		ArrayList<String> noms = new ArrayList<String>();
		ps = conn.prepareStatement("select NomFr from etudiants where semestre=?");
		ps.setInt(1, semestre);
		System.out.println(semestre);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			noms.add(rs.getString("NomFr"));
			
		}
		ArrayList<String> modules = new ArrayList<String>();
		ps=conn.prepareStatement("select LabelleMod from module where semestre=?");
		ps.setInt(1, semestre);
		
		rs=ps.executeQuery();
		modules.add("nom");
		while(rs.next()) {
			modules.add(rs.getString("LabelleMod"));
		}
		modules.add("moyenne");
		modules.add("résultat du semestre " + semestre);


      
        for(int i = 0; i < modules.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(modules.get(i));
            cell.setCellStyle(headerCellStyle);
        }
        
        

        // Closing the workbook
      
		int rowNum = 1;
		
		for (String nom : noms) {
			ArrayList<Note> notes = new ArrayList<Note>();
			Row row = sheet.createRow(rowNum++);
			int colNum=0;
			row.createCell(colNum).setCellValue(nom);
			colNum++;  
			ps = conn.prepareStatement(
					"select n.id from Note n, etudiants e, module m WHERE n.etudiant=e.massarEtud and n.module=m.IDModule and m.semestre=? and e.NomFr=? and n.session=?");
			ps.setInt(1, semestre);
			ps.setString(2, nom);
			ps.setString(3, "Ordinaire");
			boolean valide=true;
			ResultSet rs1 = ps.executeQuery();
			double total=0;
			while(rs1.next()) {
				Note note = getNoteParId(rs1.getInt("id"));
				notes.add(note);
				if(note.getNote()<10) {
					ps = conn.prepareStatement("select note from Note where session=? and module=?");
					ps.setString(1, "Rattrapage");
					ps.setInt(2, note.getModule());
					
					ResultSet rs2 = ps.executeQuery();
					
					rs2.next();
					if(rs2.getDouble("note")<10) {
						valide=false;
					}
					total+=rs2.getDouble("note");
					row.createCell(colNum).setCellValue(rs2.getDouble("note"));
					colNum++;
				}
				else {
					total+=note.getNote();
					row.createCell(colNum).setCellValue(note.getNote());
					colNum++;
				}
			}
			row.createCell(6).setCellValue(total/5);
			if(valide==false) {
				row.createCell(7).setCellValue("semestre non validé");
			}
			else {
				row.createCell(7).setCellValue("semestre validé");
			}
			System.out.println(nom + " " + (valide==true?"semestre validé":"semestre non validé"));
			
		}
		
		
		FileOutputStream fileOut = new FileOutputStream("/home/yassine/eclipse-workspace/miniprojet/deliberation.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
	}
	
	public Note getNoteParId(int id) throws SQLException {
		Connection conn = SingletonConnection.getConnectionBd();

		PreparedStatement ps;
		ps = conn.prepareStatement("select * from Note where id = ?");
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		Note note = new Note();
		while(rs.next()) {
			note.setEtudiant(rs.getString("etudiant"));
			note.setId(rs.getInt("id"));
			note.setModule(rs.getInt("module"));
			note.setNote(rs.getDouble("note"));
			note.setSession(rs.getString("session"));
		}
		
		return note;
	}

}
