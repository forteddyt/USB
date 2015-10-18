import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebsiteCoder extends Thread
{
    private static TreeSet<Section> sections = new TreeSet<Section>();
    private Scanner scan = null;
    private static PrintWriter pw = null;
    private static HashMap<String, String> ILITEPictures = new Picture()
            .getPictures();
    private String saveLoc;
    private File students, mentors;
    private static Decrypter d;

    public void run()
    {
        d.translate();
        gatherInfo(saveLoc);
        coder();
        stop();
    }

    public WebsiteCoder(String saveLoc, File students, File mentors)
    {
        this.saveLoc = saveLoc;
        this.students = students;
        this.mentors = mentors;
        d = new Decrypter(students, mentors);
    }

    private void gatherInfo(String saveLoc)
    {
        String initial = "[ul id=\"names\"][section id=\"coach\"][br]\n";
        String coach = "[pagehead subtitle=\"The person who runs things around here!\"]Coach[/pagehead]\n";
        String drakeInfo = "[member name=\"Professor Gail Drake\" image=\"http://2012.iliterobotics.org/images/mentors/gdrake.jpg\"]\n"
                + "[yearjoined data=\"2005\"]\n"
                + "[team data=\"Head Coach for FRC 1885, 8 FTC Teams, 1 FLL Team, “Teach the teachers”\"]\n"
                + "[company data=\"Battlefield High School, Northern Virginia Community College\"]\n"
                + "[funfact data=\"I am now 5 years past my “agreed” time period to teach and coach\"]\n"
                + "[memory data=\"Rookie World Championship “Rookie All Star”\"]\n"
                + "[/member]\n";

        Pattern division = Pattern
                .compile("([\\w\\s]+)-([\\w\\s]+)-([\\w\\s,!-;,]+)\\s?:\\s?");
        Pattern studentParser = Pattern.compile("\\s+?-(.*)-(.*)-(.*)-");
        Pattern mentorParser = Pattern
                .compile((" -(.*)-(.*)-(.*)-(.*)-(.*)-(.*)-"));
        try
        {
            scan = new Scanner(new File("src/memberList.txt"));
            File output = new File(saveLoc);
            if (!output.exists())
            {
                output.createNewFile();
            }
            String team = "";
            String teamID = "";
            String header = "";
            pw = new PrintWriter(output);
            pw.write(initial + "\n");
            pw.write(coach + "\n");
            pw.write(drakeInfo + "\n");

            Section sect = null;
            String s = scan.nextLine();

            while (scan.hasNextLine())
            {
                String name = "";
                String year = "";
                String fact = "";
                String company = "";
                String memory = "";
                Matcher m1 = division.matcher(s);
                Matcher m2 = studentParser.matcher(s);
                Matcher m3 = mentorParser.matcher(s);

                if (m1.matches())
                {
                    teamID = m1.group(1);
                    team = m1.group(2);
                    header = m1.group(3);
                    sect = new Section(teamID, team, header);
                    s = scan.nextLine();

                } else if (m2.matches() && !sect.sectionID.equals("mentors"))
                {
                    while (!m1.matches())
                    {
                        if (m2.matches() && scan.hasNextLine())
                        {
                            name = m2.group(1);
                            year = m2.group(2);
                            fact = m2.group(3);
                            Student m = new Student(name, year, team, fact);
                            sect.addStudent(m);
                            s = scan.nextLine();
                        } else
                        {
                            return;
                        }
                        m2 = studentParser.matcher(s);
                        m1 = division.matcher(s);
                    }
                } else if (m3.matches())
                {
                    while (!m1.matches())
                    {
                        if (m3.matches() && scan.hasNextLine())
                        {
                            name = m3.group(1);
                            year = m3.group(2);
                            team = m3.group(3);
                            company = m3.group(4);
                            fact = m3.group(5);
                            memory = m3.group(6);
                            Mentor m = new Mentor(name, year, team, company,
                                    fact, memory);
                            sect.addMentor(m);
                            s = scan.nextLine();
                        } else
                        {
                            return;
                        }
                        m3 = mentorParser.matcher(s);
                        m1 = division.matcher(s);
                    }
                }
                sections.add(sect);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (scan != null)
            {
                scan.close();
            }
        }
    }

    private static void coder()
    {
        for (Section s : sections)
        {
            if (s.getSectionID().equals("mentors"))
            {
                pw.write(s.toCode() + "\n\n");
                for (Mentor m : s.getMentors())
                {
                    pw.write(m.toCode() + "\n\n");
                }
            }
        }

        for (Section s : sections)
        {
            if (!s.getSectionID().equals("mentors"))
            {
                pw.write(s.toCode() + "\n\n");
                for (Student m : s.getStudents())
                {
                    pw.write(m.toCode() + "\n\n");
                }
            }
        }

        pw.write("[/section][/ul]");

        if (pw != null)
        {
            pw.close();
        }
    }

    private class Section implements Comparable
    {
        @Override
        public String toString()
        {
            return "Section [members=" + students + ", sectionID=" + sectionID
                    + ", sectionTitle=" + sectionTitle + ", sectionPhrase="
                    + sectionPhrase + "]";
        }

        public String toCode()
        {
            return "[/section]\n[section id=\"" + sectionID
                    + "\"] \n[pagehead subtitle=\"" + sectionPhrase + "\"]\n"
                    + sectionTitle + "\n[/pagehead]";
        }

        private TreeSet<Student> students = new TreeSet<Student>();
        private TreeSet<Mentor> mentors = new TreeSet<Mentor>();
        private String sectionID, sectionTitle, sectionPhrase;

        public String getSectionTitle()
        {
            return sectionTitle;
        }

        public void setSectionTitle(String sectionTitle)
        {
            this.sectionTitle = sectionTitle;
        }

        public String getSectionPhrase()
        {
            return sectionPhrase;
        }

        public void setSectionPhrase(String sectionPhrase)
        {
            this.sectionPhrase = sectionPhrase;
        }

        public TreeSet<Student> getStudents()
        {
            return students;
        }

        public TreeSet<Mentor> getMentors()
        {
            return mentors;
        }

        public String getSectionID()
        {
            return sectionID;
        }

        public Section(String sID, String sT, String sP)
        {
            sectionID = sID;
            sectionTitle = sT;
            sectionPhrase = sP;
        }

        public boolean addStudent(Student s)
        {
            return students.add(s);
        }

        public boolean addMentor(Mentor m)
        {
            return mentors.add(m);
        }

        public boolean removeMentor(String name)
        {
            Mentor rip = null;
            if (name.equals("Professor Gail Drake"))
            {
                return false;
            }

            for (Mentor m : mentors)
            {
                if (m.getName().equals(name))
                {
                    rip = m;
                }
            }
            if (rip != null)
            {
                mentors.remove(rip);
                return true;
            } else
            {
                return false;
            }
        }

        public boolean removeStudent(String name)
        {
            Student rip = null;
            if (name.equals("Professor Gail Drake"))
            {
                return false;
            }

            for (Student m : students)
            {
                if (m.getName().equals(name))
                {
                    rip = m;
                }
            }
            if (rip != null)
            {
                students.remove(rip);
                return true;
            } else
            {
                return false;
            }
        }

        @Override
        public int compareTo(Object o)
        {
            return sectionID.compareTo(((Section) o).sectionID);
        }
    }

    private class Mentor implements Comparable
    {
        private String name, joined, team, company, fact, memory, lastName;
        private Pattern finder = Pattern.compile("(.* )([\\w']+)");

        private Mentor(String name, String joined, String team, String company,
                String fact, String memory)
        {
            Matcher m1 = finder.matcher(name);

            this.name = name;
            this.joined = joined == null ? "" : joined;
            this.team = team == null ? "" : team;
            this.company = company == null ? "" : company;
            this.fact = fact == null ? "" : fact;
            this.memory = memory == null ? "" : memory;
            if (m1.matches())
            {
                this.lastName = m1.group(2) + m1.group(1);
            }
        }

        public String getFact()
        {
            return fact;
        }

        public void setFact(String fact)
        {
            this.fact = fact;
        }

        public String getMemory()
        {
            return memory;
        }

        public void setMemory(String memory)
        {
            this.memory = memory;
        }

        public String getName()
        {
            return name;
        }

        public String getJoined()
        {
            return joined;
        }

        public String getTeam()
        {
            return team;
        }

        public String getCompany()
        {
            return company;
        }

        @Override
        public int compareTo(Object o)
        {
            if (o instanceof Student)
            {
                return lastName.compareTo(((Student) o).lastName);
            } else
            {
                return lastName.compareTo(((Mentor) o).lastName);
            }
        }

        public String toCode()
        {
            // TreeMap<Integer, String> pix = new TreeMap<Integer, String>();
            String temp = name.replace(" ", "").toLowerCase();
            String link = "http://2012.iliterobotics.org/images/students/ph.jpg";

            // pix.put(0,
            // "http://2012.iliterobotics.org/images/students/ph.jpg");

            for (int i = 12; i < 25; i++)
            {
                if (ILITEPictures.get("frc_" + i + "_" + temp) != null)
                {
                    link = ILITEPictures.get("frc_" + i + "_" + temp);
                    // pix.put(i, link);
                }
            }

            // link = pix.get(pix.lastKey());

            String s = "[member name=\"" + name + "\" image=\"" + link
                    + "\"]\n[yearjoined data=\"" + joined
                    + "\"]\n[team data=\"" + team + "\"]\n"
                    + "[company data=\"" + company + "\"]\n"
                    + "[funfact data=\"" + fact + "\"]\n" + "[memory data=\""
                    + memory + "\"]\n" + "[/member]";
            return s;
        }
    }

    private class Student implements Comparable
    {
        private String name, joined, team, fact, lastName;
        private Pattern finder = Pattern.compile("(.* )([\\w']+)");

        @Override
        public String toString()
        {
            return "Member [name=" + name + ", joined=" + joined + ", team="
                    + team + ", fact=" + fact + "]";
        }

        public Student(String name, String joined, String team, String fact)
        {
            Matcher m1 = finder.matcher(name);

            this.name = name;
            this.joined = joined;
            this.team = team;
            this.fact = fact == null ? "" : fact;
            if (m1.matches())
            {
                this.lastName = m1.group(2) + m1.group(1);
            }
        }

        public String getName()
        {
            return name;
        }

        public String getJoined()
        {
            return joined;
        }

        public String getTeam()
        {
            return team;
        }

        public String getFact()
        {
            return fact;
        }

        public void updateFact(String s)
        {
            fact = s;
        }

        public String toCode()
        {
            String temp = name.replace(" ", "").toLowerCase();
            String link = "http://2012.iliterobotics.org/images/students/ph.jpg";

            for (int i = 12; i < 25; i++)
            {
                if (ILITEPictures.get("frc_" + i + "_" + temp) != null)
                {
                    link = ILITEPictures.get("frc_" + i + "_" + temp);
                }
            }

            String s = "[member name=\"" + name + "\" image=\"" + link
                    + "\"]\n[yearjoined data=\"" + joined
                    + "\"]\n[team data=\"" + team + "\"]\n"
                    + "[funfact data=\"" + fact + "\"]\n[/member]";
            return s;
        }

        @Override
        public int compareTo(Object o)
        {
            return lastName.compareTo(((Student) o).lastName);
        }
    }

    private static class Picture
    {
        HashMap<String, String> pictures = new HashMap<String, String>();
        Pattern godLike = Pattern
                .compile("<li[\\s\\w=\"-]+label=\"([\\d_\\w']+)\".*\\s+.*\\s+.*\\s+.*\\s+<img src=\"([\\w\\d:/.'-]+)\".*\\s+.*\\s+.*\\s+.*\\s+.*\\s+</li>");

        public Picture()
        {
            Scanner scan2 = null;
            try
            {
                scan2 = new Scanner(new File("src/PictureHTML.txt"));
                String name = "";
                String link = "";
                String s = "";
                while (scan2.hasNextLine())
                {
                    s += scan2.nextLine() + "\n";
                }
                Matcher m1 = godLike.matcher(s);
                while (m1.find())
                {
                    name = m1.group(1).toLowerCase();
                    link = m1.group(2);
                    pictures.put(name, link);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            } finally
            {
                if (scan2 != null)
                {
                    scan2.close();
                }
            }
        }

        public HashMap<String, String> getPictures()
        {
            return pictures;
        }
    }

}