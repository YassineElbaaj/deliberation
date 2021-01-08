package metier;

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
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import metierEntite.Note;

public class NoteCRUD {

	public void ajouterNote(Note note) throws SQLException {
		Connection conn = SingletonConnection.getConnectionBd();

		PreparedStatement ps;
		ps = conn.prepareStatement("INSERT INTO Note (note, module, etudiant) VALUES (?,?,?)");
		System.out.println(note);
		ps.setDouble(1, note.getNote());
		ps.setInt(2, note.getModule());
		ps.setString(3, note.getEtudiant());

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
					ps = conn.prepareStatement("INSERT INTO Note (note, module, etudiant) VALUES (?,?,?)");

					String massar = row.getCell(0).getStringCellValue();
					double note = cell.getNumericCellValue();
					int module = getModuleId(modules.get(i));
					ps.setDouble(1, note);
					ps.setInt(2, module);
					ps.setString(3, massar);

					ps.executeUpdate();

					i++;
				}
			}
		} catch (NullPointerException e) {

		} catch (SQLIntegrityConstraintViolationException e) {

		}
		catch(NoSuchElementException e) {
			
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

}
