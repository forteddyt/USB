import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class CreateReceipts {

	private List<ReceiptInfo> receipts;

	public CreateReceipts(List<ReceiptInfo> r) {
		receipts = r;
	}

	public void create() {
		try {
			for (ReceiptInfo r : receipts) {
				InputStream is = new FileInputStream("receipt.docx");
				XWPFDocument doc = new XWPFDocument(is);

				List<XWPFParagraph> paras = doc.getParagraphs();

				XWPFDocument newdoc = new XWPFDocument();
				for (XWPFParagraph para : paras) {

					if (!para.getParagraphText().isEmpty()) {
						XWPFParagraph newpara = newdoc.createParagraph();
						copyAllRunsToAnotherParagraph(para, newpara, r);
					}
				}

				File newReceipt = new File(
						"Receipts/" + r.getFirstName() + "_" + r.getLastName() + "_" + r.getItteration() + ".docx");
				// Creates receipts in receipts folder.
				if (!newReceipt.exists()) {
					newReceipt.createNewFile();
				}

				FileOutputStream fos = new FileOutputStream(newReceipt);
				newdoc.write(fos);
				fos.flush();
				fos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Copy all runs from one paragraph to another, keeping the style unchanged
	private static void copyAllRunsToAnotherParagraph(XWPFParagraph oldPar, XWPFParagraph newPar, ReceiptInfo r) {
		final int DEFAULT_FONT_SIZE = 10;

		for (XWPFRun run : oldPar.getRuns()) {
			String textInRun = run.getText(0);
			if (textInRun == null || textInRun.isEmpty()) {
				continue;
			}

			textInRun = textInRun.replace("«Student_Name»", r.getFirstName() + " " + r.getLastName());
			textInRun = textInRun.replace("«Parent_Name»", r.getParentName());
			textInRun = textInRun.replace("«Amount»", r.getCost() + "");

			int fontSize = run.getFontSize();
			System.out.println(textInRun);

			// try {
			// InputStream pic = new FileInputStream("logo.png");
			// newRun.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, null, 0,
			// 0);
			// } catch (Exception e) {
			// e.printStackTrace();
			// }

			XWPFRun newRun = newPar.createRun();
			newPar.setAlignment(oldPar.getAlignment());
			newPar.setBorderBetween(oldPar.getBorderBetween());
			newPar.setBorderBottom(oldPar.getBorderBottom());
			newPar.setStyle(oldPar.getStyle());
			newPar.setBorderLeft(oldPar.getBorderLeft());
			newPar.setBorderRight(oldPar.getBorderRight());

			// Copy text
			newRun.setText(textInRun);

			// Apply the same style
			newRun.setFontSize((fontSize == -1) ? DEFAULT_FONT_SIZE : run.getFontSize());
			newRun.setFontFamily(run.getFontFamily());
			newRun.setBold(run.isBold());
			newRun.setItalic(run.isItalic());
			newRun.setStrike(run.isStrike());
			newRun.setColor(run.getColor());
			newRun.setTextPosition(run.getTextPosition());
			newRun.setKerning(run.getKerning());
			newRun.setSubscript(run.getSubscript());
			newRun.setEmbossed(run.isEmbossed());
		}
	}
}
