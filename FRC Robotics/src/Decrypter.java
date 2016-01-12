import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decrypter {
	private File students, mentors;
	private Pattern sP, mP;
	private HashSet<Section> mySections = new HashSet<Section>();
	private Scanner scan;

	public Decrypter(File students, File mentors) {
		this.students = students;
		this.mentors = mentors;
		sP = Pattern.compile("([\\s\\w]+) (\\d+)   ([\\d\\w,:;\\s!]+)?    ([\\w]+)");
		mP = Pattern.compile("");
	}

	public void translate() {
		tStudents();
		tMentors();
	}

	public LinkedHashSet<String> parseSections() {
		LinkedHashSet<String> temp = new LinkedHashSet<String>();
		for (Section s : mySections) {
			if (!temp.contains(s.sectionName)) {
				temp.add(s.sectionName);
			}
		}
		return temp;
	}

	public void tStudents() {
		Matcher m;
		TreeMap<String, Student> tree = new TreeMap<String, Student>();
		try {
			scan = new Scanner(students);
			while (scan.hasNextLine()) {
				String line, n = "", y = "", f = "", t = "";
				line = scan.nextLine();
				m = sP.matcher(line);
				if (m.matches()) {
					n = m.group(1);
					y = m.group(2);
					f = m.group(3);
					t = m.group(4);
				}
				tree.put(t, new Student(n, y, f, t));
			}

			LinkedHashSet<String> h = parseSections();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scan != null) {
				scan.close();
			}
		}
	}

	public void tMentors() {

	}

	public class Section implements Comparable {
		private String sectionID, sectionName, sectionQuote;
		private LinkedHashSet<Members> myList;

		public Section(String sID, String sName, String sQuote) {
			this.sectionID = sID;
			this.sectionName = sName;
			this.sectionQuote = sQuote;
		}

		public LinkedHashSet<Members> getMyList() {
			return myList;
		}

		public boolean add(Members m) {
			return myList.add(m);
		}

		public int compareTo(Object o) {
			return sectionID.compareTo(((Section) o).sectionID);
		}

		public boolean equals(Object o) {
			return sectionName.equals(((Section) o).sectionName);
		}

		public String toCode() {
			String s = "[section id=\"" + sectionID + "\"]\n";
			for (Members m : myList) {
				if (m.getSubTeam().equals(sectionName)) {
					s += "\n" + m.toCode() + "\n";
				}
			}
			s += "[/section]\n";
			return s;
		}
	}

	public abstract class Members {
		public abstract String getType();

		public abstract String toCode();

		public abstract String getSubTeam();
	}

	public class Mentor extends Members implements Comparable {
		private String name, year, team, company, fact, memory, picture;

		public Mentor(String name, String year, String team, String company, String fact, String memory) {
			this.name = name;
			this.year = year;
			this.team = team;
			this.company = company;
			this.fact = fact;
			this.memory = memory;
		}

		public String getSubTeam() {
			return "Mentor";
		}

		public String toCode() {
			String s = "[member name=\"" + name + "\" image=\"" + picture + "\"]\n[yearjoined data=\"" + year
					+ "\"]\n[team data=\"" + team + "\"][company data=\"" + company + "\"]\n[funfact data=\"" + fact
					+ "\"]\n[memory data=\"" + memory + "\"]\n[/member]";
			return s;
		}

		public String getType() {
			return "Mentor";
		}

		public int compareTo(Object o) {
			return name.compareTo(((Mentor) o).name);
		}
	}

	public class Student extends Members implements Comparable {
		private String name, year, fact, team, picture;

		public Student(String name, String year, String fact, String team) {
			this.name = name;
			this.year = year;
			this.fact = fact;
			this.team = team;
		}

		public String getSubTeam() {
			return team;
		}

		public String toCode() {
			String s = "[member name=\"" + name + "\" image=\"" + picture + "\"]\n[yearjoined data=\"" + year
					+ "\"]\n[team data=\"" + team + "\"]\n[funfact data=\"" + fact + "\"]\n[/member]";
			return s;
		}

		public String getType() {
			return "Student";
		}

		public int compareTo(Object o) {
			return name.compareTo(((Student) o).name);
		}
	}
}
