import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FileChooser {
	private JTextField studentFileChosen = new JTextField(), studentDir = new JTextField(),
			mentorFileChosen = new JTextField(), mentorDir = new JTextField();
	private WebsiteCoder wC;
	private Color ILITEPurple = new Color(112, 63, 144), ILITEGreen = new Color(106, 175, 54),
			noneGrey = new Color(64, 64, 64);
	private Font ILITEFont;
	private JFileChooser c = new JFileChooser();
	private JButton studentOpenButton = new JButton("Choose File"), mentorOpenButton = new JButton("Choose File"),
			createCode = new JButton("Save Created Code");
	private String fName = null, saveLocation = "";
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static JFrame frame;

	public static void main(String[] args) {
		frame = new JFrame();
		new FileChooser();
		run(frame, 400, 450);
	}

	public FileChooser() {
		try {
			ILITEFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/agency-fb.ttf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		frame.setResizable(false);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		studentOpenButton.addActionListener(new OpenL("student"));
		mentorOpenButton.addActionListener(new OpenL("mentor"));
		createCode.addActionListener(new SaveTo());
		studentDir.setEditable(false);
		mentorDir.setEditable(false);

		mainPanel.add(new StudentChooserUI(), BorderLayout.PAGE_START);
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new BorderLayout());
		tempPanel.add(new MentorChooserUI(), BorderLayout.PAGE_START);
		mainPanel.add(tempPanel, BorderLayout.CENTER);
		mainPanel.add(new CodeDataUI(), BorderLayout.PAGE_END);
		mainPanel.setBorder(BorderFactory.createLineBorder(ILITEPurple.darker(), 6));
		mainPanel.setVisible(true);
		mainPanel.setOpaque(true);

		frame.setTitle("File Chooser");
		frame.add(mainPanel);
	}

	private class StudentChooserUI extends JPanel {
		private InfoGuide iGuide = new InfoGuide();
		private MemberGuide mGuide = new MemberGuide();

		public StudentChooserUI() {
			setLayout(new BorderLayout());
			setOpaque(true);

			JLabel FCTitle = new JLabel();
			FCTitle.setFont(ILITEFont);

			JPanel FCTemp = new JPanel();
			FCTemp.setLayout(new BorderLayout());

			studentFileChosen.setEditable(false);
			studentFileChosen.setOpaque(true);
			studentFileChosen.setBackground(ILITEGreen);
			studentFileChosen.setHorizontalAlignment(JTextField.CENTER);
			studentFileChosen.setFont(ILITEFont.deriveFont(Font.BOLD, 20));
			studentFileChosen.setForeground(noneGrey);
			studentFileChosen.setText("No File Chosen");
			studentFileChosen.setFocusable(false);
			studentFileChosen.setDragEnabled(false);
			studentFileChosen.setBorder(BorderFactory.createLineBorder(noneGrey, 2));
			FCTemp.add(studentFileChosen, BorderLayout.CENTER);
			FCTemp.add(Box.createRigidArea(new Dimension(30, 10)), BorderLayout.LINE_END);
			FCTemp.setOpaque(true);

			studentOpenButton.setFont(ILITEFont.deriveFont(Font.PLAIN, 20));

			JPanel FCTempTitle = new JPanel();
			JLabel studentListTitle = new JLabel("Student List");

			JPanel mButtonPanel = new JPanel();
			JPanel mButtonFormatter = new JPanel();
			JButton mButton = new JButton(new ImageIcon(((new ImageIcon("src/PurpleFace.png").getImage()
					.getScaledInstance(30, 35, java.awt.Image.SCALE_SMOOTH)))));
			mButtonFormatter.setLayout(new BorderLayout());
			mButtonPanel.setLayout(new BorderLayout());
			mButtonPanel.add(Box.createVerticalStrut(5), BorderLayout.PAGE_START);
			mButton.setBackground(new Color(0, 0, 0, 0));
			mButton.setBorder(BorderFactory.createEmptyBorder());
			mButton.setOpaque(false);
			mButton.addActionListener(new mAction());
			mButtonFormatter.add(mButton, BorderLayout.LINE_END);
			mButtonFormatter.add(Box.createHorizontalStrut(5), BorderLayout.LINE_START);
			mButtonPanel.add(mButtonFormatter, BorderLayout.LINE_START);
			mButtonPanel.add(Box.createHorizontalStrut(125 - mButton.getIcon().getIconWidth() - 5),
					BorderLayout.LINE_END);

			JPanel qButtonPanel = new JPanel();
			JPanel qButtonFormatter = new JPanel();
			qButtonFormatter.setLayout(new BorderLayout());
			JButton qButton = new JButton(new ImageIcon(((new ImageIcon("src/QuestionMark.png").getImage()
					.getScaledInstance(30, 35, java.awt.Image.SCALE_SMOOTH)))));
			qButtonPanel.setLayout(new BorderLayout());
			qButtonPanel.add(Box.createVerticalStrut(5), BorderLayout.PAGE_START);
			qButton.setBackground(new Color(0, 0, 0, 0));
			qButton.setBorder(BorderFactory.createEmptyBorder());
			qButton.setOpaque(false);
			qButton.addActionListener(new qAction());
			qButtonFormatter.add(Box.createHorizontalStrut(5), BorderLayout.LINE_END);
			qButtonFormatter.add(qButton, BorderLayout.LINE_START);
			qButtonPanel.add(qButtonFormatter, BorderLayout.LINE_END);
			qButtonPanel.add(Box.createHorizontalStrut(125 - qButton.getIcon().getIconWidth() - 5),
					BorderLayout.LINE_START);

			studentListTitle.setFont(ILITEFont.deriveFont(Font.PLAIN, 25));
			studentListTitle.setHorizontalAlignment(JLabel.CENTER);
			studentListTitle.setForeground(Color.BLACK);
			studentListTitle.setOpaque(true);
			studentListTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, ILITEGreen));
			FCTempTitle.setLayout(new BorderLayout());
			FCTempTitle.add(studentListTitle, BorderLayout.CENTER);
			FCTempTitle.add(mButtonPanel, BorderLayout.LINE_START);
			FCTempTitle.add(qButtonPanel, BorderLayout.LINE_END);
			FCTempTitle.add(Box.createVerticalStrut(8), BorderLayout.PAGE_END);

			JPanel OBTemp = new JPanel();
			OBTemp.setLayout(new BorderLayout());
			OBTemp.setOpaque(true);

			JPanel openButtonFormatter = new JPanel();
			openButtonFormatter.setLayout(new BorderLayout());
			openButtonFormatter.setOpaque(true);
			openButtonFormatter.add(studentOpenButton, BorderLayout.CENTER);
			openButtonFormatter.add(Box.createVerticalStrut(2), BorderLayout.PAGE_START);
			openButtonFormatter.add(Box.createVerticalStrut(2), BorderLayout.PAGE_END);
			openButtonFormatter.add(Box.createHorizontalStrut(2), BorderLayout.LINE_START);
			openButtonFormatter.add(Box.createHorizontalStrut(2), BorderLayout.LINE_END);

			OBTemp.add(openButtonFormatter, BorderLayout.CENTER);
			OBTemp.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.LINE_END);
			OBTemp.add(Box.createRigidArea(new Dimension(30, 10)), BorderLayout.LINE_START);
			FCTemp.add(OBTemp, BorderLayout.LINE_START);
			FCTemp.add(FCTempTitle, BorderLayout.PAGE_START);

			add(FCTemp, BorderLayout.PAGE_START);
		}

		public class qAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (!iGuide.isVisible()) {
					iGuide.setVisible(true);
					iGuide.setLocation(frame.getLocation().x + 15, frame.getLocation().y + 15);
				}
			}
		}

		public class mAction implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (!mGuide.isVisible()) {
					mGuide.setVisible(true);
					mGuide.setLocation(frame.getLocation().x + 15, frame.getLocation().y + 15);
				}
			}
		}
	}

	private class MentorChooserUI extends JPanel {
		public MentorChooserUI() {
			setLayout(new BorderLayout());
			setOpaque(true);

			mentorOpenButton.setFont(ILITEFont.deriveFont(Font.PLAIN, 20));

			mentorFileChosen.setEditable(false);
			mentorFileChosen.setOpaque(true);
			mentorFileChosen.setBackground(ILITEGreen);
			mentorFileChosen.setHorizontalAlignment(JTextField.CENTER);
			mentorFileChosen.setFont(ILITEFont.deriveFont(Font.BOLD, 20));
			mentorFileChosen.setForeground(noneGrey);
			mentorFileChosen.setText("No File Chosen");
			mentorFileChosen.setFocusable(false);
			mentorFileChosen.setDragEnabled(false);
			mentorFileChosen.setBorder(BorderFactory.createLineBorder(noneGrey, 2));

			JPanel titleFormat = new JPanel();
			titleFormat.setLayout(new BorderLayout());
			titleFormat.setOpaque(true);
			JLabel mentorTitle = new JLabel("Mentor List");
			mentorTitle.setOpaque(true);
			mentorTitle.setFont(ILITEFont.deriveFont(Font.PLAIN, 25));
			mentorTitle.setForeground(Color.BLACK);
			mentorTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, ILITEGreen));
			mentorTitle.setHorizontalAlignment(JLabel.CENTER);
			titleFormat.add(Box.createHorizontalStrut(125), BorderLayout.LINE_START);
			titleFormat.add(Box.createHorizontalStrut(125), BorderLayout.LINE_END);
			titleFormat.add(mentorTitle, BorderLayout.CENTER);
			titleFormat.add(Box.createVerticalStrut(15), BorderLayout.PAGE_START);
			titleFormat.add(Box.createVerticalStrut(8), BorderLayout.PAGE_END);

			JPanel buttonFormatter = new JPanel();
			buttonFormatter.setOpaque(true);
			buttonFormatter.setLayout(new BorderLayout());
			buttonFormatter.add(mentorOpenButton, BorderLayout.CENTER);
			buttonFormatter.add(Box.createHorizontalStrut(2), BorderLayout.LINE_START);
			buttonFormatter.add(Box.createHorizontalStrut(2), BorderLayout.LINE_END);
			buttonFormatter.add(Box.createVerticalStrut(2), BorderLayout.PAGE_START);
			buttonFormatter.add(Box.createVerticalStrut(2), BorderLayout.PAGE_END);

			JPanel buttonField = new JPanel();
			buttonField.setLayout(new BorderLayout());
			buttonField.setOpaque(true);
			buttonField.add(buttonFormatter, BorderLayout.CENTER);
			buttonField.add(Box.createHorizontalStrut(30), BorderLayout.LINE_START);
			buttonField.add(Box.createHorizontalStrut(10), BorderLayout.LINE_END);

			JPanel textField = new JPanel();
			textField.setLayout(new BorderLayout());
			textField.setOpaque(true);
			textField.add(mentorFileChosen, BorderLayout.CENTER);
			textField.add(Box.createHorizontalStrut(30), BorderLayout.LINE_END);

			JPanel buttonTextField = new JPanel();
			buttonTextField.setLayout(new BorderLayout());
			buttonTextField.setOpaque(true);
			buttonTextField.add(buttonField, BorderLayout.LINE_START);
			buttonTextField.add(textField, BorderLayout.CENTER);

			add(titleFormat, BorderLayout.PAGE_START);
			add(buttonTextField, BorderLayout.PAGE_END);
		}
	}

	private class CodeDataUI extends JPanel {
		public CodeDataUI() {
			setLayout(new BorderLayout());
			createCode.setFont(ILITEFont.deriveFont(Font.PLAIN, 35));
			createCode.setAlignmentX(CENTER_ALIGNMENT);
			JLabel ILITEpic = new JLabel(new ImageIcon("src/ilite.png"));

			JPanel createCodeFormatter = new JPanel();
			createCodeFormatter.setLayout(new BorderLayout());
			createCodeFormatter.add(createCode, BorderLayout.CENTER);
			createCodeFormatter.add(Box.createHorizontalStrut(20), BorderLayout.LINE_START);
			createCodeFormatter.add(Box.createHorizontalStrut(20), BorderLayout.LINE_END);
			createCodeFormatter.add(Box.createVerticalStrut(5), BorderLayout.PAGE_END);

			JPanel picFormatter = new JPanel();
			picFormatter.setLayout(new BorderLayout());
			picFormatter.add(ILITEpic, BorderLayout.CENTER);
			picFormatter.add(Box.createVerticalStrut(15), BorderLayout.PAGE_END);

			add(Box.createVerticalStrut(3), BorderLayout.PAGE_START);
			add(picFormatter, BorderLayout.CENTER);
			add(createCodeFormatter, BorderLayout.PAGE_END);
		}

	}

	private class OpenL implements ActionListener {
		private String type = "";

		public OpenL(String type) {
			this.type = type;
		}

		public void actionPerformed(ActionEvent e) {
			if (fName != null) {
				c.setCurrentDirectory(new File(fName));
			}
			int rVal = c.showOpenDialog(FileChooser.frame);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				String s = c.getCurrentDirectory().toString() + "\\" + c.getSelectedFile().getName();
				fName = s;
				if (type.equals("student")) {
					studentFileChosen.setForeground(Color.BLACK);
					studentFileChosen.setText(c.getSelectedFile().getName());
					s = s.replaceAll("\\\\", "/");
					studentDir.setText(s);
				} else if (type.equals("mentor")) {
					mentorFileChosen.setForeground(Color.BLACK);
					mentorFileChosen.setText(c.getSelectedFile().getName());
					s = s.replaceAll("\\\\", "/");
					mentorDir.setText(s);
				}
			}
		}
	}

	private class SaveTo implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (!(studentDir.getText().length() > 0) && !(mentorDir.getText().length() > 0)) {
				JOptionPane.showMessageDialog(frame, "Select appropriate files.", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else if (!(mentorDir.getText().length() > 0)) {
				JOptionPane.showMessageDialog(frame, "Select a Mentor List file.", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else if (!(studentDir.getText().length() > 0)) {
				JOptionPane.showMessageDialog(frame, "Select a Student List file.", "Warning",
						JOptionPane.WARNING_MESSAGE);
			} else {
				JFileChooser c = new JFileChooser();
				int rVal = c.showDialog(FileChooser.frame, "Save To");
				if (rVal == JFileChooser.APPROVE_OPTION) {
					String s = c.getCurrentDirectory().toString() + "\\" + c.getSelectedFile().getName();
					s = s.replaceAll("\\\\", "/");
					saveLocation = s;

					wC = new WebsiteCoder(s, new File(studentDir.getText()), new File(mentorDir.getText()));
					wC.start();
				}
			}
		}
	}

	public static void run(JFrame frame, int width, int height) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setResizable(true);
		frame.setLocation((int) ((screenSize.getWidth() / 2) - frame.getWidth() / 2),
				(int) ((screenSize.getHeight() / 2) - frame.getHeight() / 2));
		frame.setVisible(true);
	}
}
