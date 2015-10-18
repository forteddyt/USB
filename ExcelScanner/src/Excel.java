import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

	private static File f = new File("input.xlsx");
	private static List<ReceiptInfo> receipts = new ArrayList<ReceiptInfo>();
	private static CreateReceipts cr = null;

	public static void main(String[] args) throws InvalidFormatException,
			IOException {
		receiptInformation();
		cr = new CreateReceipts(receipts);
		cr.create();
	}

	public static void receiptInformation() {
		try {
			FileInputStream fis = new FileInputStream(f); // fis will be the
															// xlsx file that
															// you read from

			XSSFWorkbook workBook = new XSSFWorkbook(fis); // workBook will be
															// the XSSF version
															// of fis

			XSSFSheet sheet = workBook.getSheetAt(0); // sheet will be the sheet
														// within that XSSF file
														// you read from

			Iterator<Row> rowI = sheet.iterator(); // create an iterator for the
													// sheet
			rowI.next();
			while (rowI.hasNext()) { // traverse the rows of the file
				Row row = rowI.next(); // stores current row information

				Iterator<Cell> cellI = row.cellIterator(); // this will iterate
															// through the cells
															// in the specific
															// row
				int cellAt = 0;
				String f = "", l = "", p = "";
				int c = -1;
				while (cellI.hasNext()) {
					Cell cell = cellI.next(); // stores current cell information
					switch (cell.getColumnIndex()) {
					case 1:
						f = cell.getStringCellValue();
						break;
					case 2:
						l = cell.getStringCellValue();
						break;
					case 5:
						p = cell.getStringCellValue();
						break;
					case 13:
						if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
							c = (int) (cell.getNumericCellValue());
						}
					}
					cellAt++;
				}
				if (!f.equals("") && !l.equals("") && !p.equals("") && c != -1) {
					receipts.add(new ReceiptInfo(f, l, p, c));
				}
			}
			Collections.sort(receipts);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String toFormat(String str) {
		Pattern p = Pattern.compile("([\\w\\.\\-&]+ ?)");
		Matcher m = p.matcher(str);

		String temp = str;

		if (str.equals("") || str.contains("@")) {
			return temp;
		} else {
			temp = "";
			while (m.find()) {
				String t = m.group(1);
				if (!t.equals("Toledo-Cruz") && !t.equals("VanOverschelde")
						&& !t.equals("DuPree")) // special cases
					temp += t.substring(0, 1).toUpperCase()
							+ t.substring(1).toLowerCase();
			}
		}
		return temp;
	}
}