import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Library
{
    private List<Book> books = new ArrayList<Book>();
    private Map<String, ArrayList<Book>> authorKey = new TreeMap<String, ArrayList<Book>>();
    private Map<String, ArrayList<Book>> titleKey = new TreeMap<String, ArrayList<Book>>();
    private Map<String, ArrayList<Book>> subjectKey = new TreeMap<String, ArrayList<Book>>();
    private Map<String, Book> ISBNKey = new HashMap<String, Book>();
    private Scanner scan = new Scanner(System.in);
    private String exit = "\nEnter 0 to return to the Select Interface.\nEnter -1 to leave The Library.";
    private boolean active = true;
    private boolean choice = true;

    public Library(String course)
    {
        compileBooks(course);
        authorKey = byAuthor();
        titleKey = byTitle();
        subjectKey = bySubject();
        ISBNKey = byISBN();
    }

    public static void main(String[] args)
    {
        Library lib = new Library("src/cardfile.txt");
        lib.UI();
    }

    public void UI()
    {
        System.out.println("\t\t***** Welcome to The Library! ***** ");
        selectInterface();
        System.exit(0);
    }

    public void selectInterface()
    {
        while (active)
        {
            System.out
                    .print("\nSearch options: \n1) Authors\n2) Title\n3) Genre\n4) ISBN\n");
            System.out.println(exit);
            int temp = -2;
            if (scan.hasNextInt())
            {
                temp = scan.nextInt();
            }
            scan.nextLine();
            while (temp < -1 || temp > 4)
            {
                System.out
                        .println("\t\tYou cannot select that index. Try a different index.\n\nSearch options: \n1) Authors\n2) Title\n3) Genre\n4) ISBN\n");
                System.out.println(exit);
                if (scan.hasNextInt())
                {
                    temp = scan.nextInt();
                }
                scan.nextLine();
            }
            choices(temp);
        }
        System.out
                .print("\t\t***** Thanks for visiting The Library, come again! *****");
    }

    public void choices(int n)
    {
        choice = true;
        if (n == -1)
        {
            active = false;
            return;
        } else if (n == 0)
        {
            return;
        } else
        {
            while (n >= 1 && n <= 4 && choice)
            {
                String by = "";
                switch (n)
                {
                case 1:
                    by = "Author";
                    break;
                case 2:
                    by = "Title";
                    break;
                case 3:
                    by = "Genre";
                    break;
                case 4:
                    by = "ISBN";
                    break;
                }
                System.out
                        .println("\t\t***** Search by "
                                + by
                                + " *****\nWhat "
                                + (by == "ISBN" ? by : by.toLowerCase())
                                + " would you like to search for?"
                                + (by == "Author" ? "(last name, first name (middle initial(s))"
                                        : "") + "\n" + exit);
                String searchBy = scan.nextLine();
                if (searchBy.equals("-1"))
                {
                    active = false;
                    return;
                } else if (searchBy.equals("0"))
                {
                    return;
                } else
                {
                    try
                    {
                        switch (n)
                        {
                        case 1:
                            displayBooks(authorKey.get(searchBy), by);
                            break;
                        case 2:
                            displayBooks(titleKey.get(searchBy), by);
                            break;
                        case 3:
                            displayBooks(subjectKey.get(searchBy), by);
                            break;
                        case 4:
                            displayBooks(ISBNKey.get(searchBy));
                            break;
                        }
                    } catch (Exception e)
                    {
                        System.out.println("\t\tThe " + by.toLowerCase() + " "
                                + searchBy + " was not found.");
                    }
                }
            }
        }
    }

    public void displayBooks(ArrayList<Book> list, String by)
    {
        List<Book> tempA = new ArrayList<Book>();
        if (list != null)
        {
            System.out
                    .print("\n--Which book would you like more information on?--\n");
        }
        for (Book b : list)
        {
            tempA.add(b);
        }
        Collections.sort(tempA);
        for (int i = 0; i < tempA.size(); i++)
        {
            System.out.println(i + 1 + ") " + tempA.get(i).toShotrenedString());
        }
        System.out.println(exit);
        String nLine = scan.nextLine();
        try
        {
            boolean choice2 = true;
            while (choice2)
            {
                if (nLine.equals("-1"))
                {
                    active = false;
                    choice = false;
                    return;
                } else if (nLine.equals("0"))
                {
                    choice = false;
                    return;
                } else if (isNumber(nLine) && Integer.parseInt(nLine) > 0
                        && Integer.parseInt(nLine) <= tempA.size())
                {
                    System.out
                            .println(tempA.get(Integer.parseInt(nLine) - 1)
                                    + "\n\n--Press enter to get details of a different book--");
                    nLine = scan.nextLine();
                } else
                {
                    System.out
                            .println("\t\tYou cannot select that index. Try a different index.");
                }
                System.out
                        .print("\n--Which book would you like more information on?--\n");
                for (int i = 0; i < tempA.size(); i++)
                {
                    System.out.println(i + 1 + ") "
                            + tempA.get(i).toShotrenedString());
                }
                System.out.println("\n" + exit);
                nLine = scan.nextLine();
            }
        } catch (Exception e)
        {
            System.out
                    .println("\t\tYou cannot select that index. Try a different index.");
        }
    }

    public void displayBooks(Book b)/* ISBN */
    {
        System.out.println("\n*** Details for ISBN: " + b.getISBN() + " ***\n"
                + b.toString()
                + "\n\n--Press enter to search a different ISBN--" + exit);
        String nLine = scan.nextLine();
        if (nLine.equals("-1"))
        {
            active = false;
            choice = false;
            return;
        } else if (nLine.equals("0"))
        {
            choice = false;
            return;
        }
    }

    public Map<String, ArrayList<Book>> byAuthor()
    {
        Map<String, ArrayList<Book>> temp = new TreeMap<String, ArrayList<Book>>();
        for (Book b : books)
        {
            for (String s : b.getMadeBy())
            {
                if (temp.containsKey(s))
                    temp.get(s).add(b);
                else
                {
                    ArrayList<Book> temp2 = new ArrayList<Book>();
                    temp2.add(b);
                    temp.put(s, temp2);
                }
            }
        }
        return temp;
    }

    public Map<String, ArrayList<Book>> byTitle()
    {
        Map<String, ArrayList<Book>> temp = new TreeMap<String, ArrayList<Book>>();
        for (Book b : books)
        {
            if (temp.containsKey(b.getTitle()))
                temp.get(b.getTitle()).add(b);
            else
            {
                ArrayList<Book> temp2 = new ArrayList<Book>();
                temp2.add(b);
                temp.put(b.getTitle(), temp2);
            }
        }
        return temp;
    }

    public Map<String, ArrayList<Book>> bySubject()
    {
        Map<String, ArrayList<Book>> temp = new TreeMap<String, ArrayList<Book>>();
        for (Book b : books)
        {
            for (String s : b.getGenres())
            {
                if (temp.containsKey(s))
                    temp.get(s).add(b);
                else
                {
                    ArrayList<Book> temp2 = new ArrayList<Book>();
                    temp2.add(b);
                    temp.put(s, temp2);
                }
            }
        }
        return temp;
    }

    public Map<String, Book> byISBN()
    {
        Map<String, Book> temp = new HashMap<String, Book>();
        for (Book b : books)
            temp.put(b.getISBN(), b);
        return temp;
    }

    public void compileBooks(String source)
    {
        Scanner fin = null;
        Pattern l3 = Pattern
                .compile("(by)? ([\"\\w\\.\\-']+)([ \\w\\-'()\\.\"]+?)([\"\\w\\.'\\-]+)?[,.]");
        Pattern l4 = Pattern.compile("-- (.*) -- (.*) : (.*), (\\d+)\\.");
        Pattern l5 = Pattern.compile("\\b(.*), (\\d+)p\\.");
        Pattern l6 = Pattern.compile("ISBN (.*)");
        Pattern l7 = Pattern.compile("\\d+\\. *([\\w \\-]*)[,\\.] ?");
        try
        {
            fin = new Scanner(new File(source));
            while (fin.hasNextLine())
            {
                String author = null, title = null, edition = null, locOfPub = null, pubComp = null, exInfo = null, ISBN = null;
                int dateOfPub = 0, numPages = 0;
                List<String> genres = new ArrayList<String>();
                List<String> madeBy = new ArrayList<String>();

                author = fin.nextLine();
                title = fin.nextLine();
                Matcher m3 = l3.matcher(fin.nextLine());
                while (m3.find())
                {
                    madeBy.add((m3.group(4) != null ? m3.group(4) + ", " : "")
                            + (m3.group(3) != null ? m3.group(2).substring(0,
                                    m3.group(2).length()) : m3.group(2))
                            + (m3.group(3) != null ? (m3.group(4) != null ? m3
                                    .group(3).substring(0,
                                            m3.group(3).length() - 1) : m3
                                    .group(3)
                                    .substring(0, m3.group(3).length())) : ""));
                }
                Matcher m4 = l4.matcher(fin.nextLine());
                if (m4.matches())
                {
                    edition = m4.group(1);
                    locOfPub = m4.group(2);
                    pubComp = m4.group(3);
                    dateOfPub = Integer.parseInt(m4.group(4));
                }
                Matcher m5 = l5.matcher(fin.nextLine());
                if (m5.matches())
                {
                    exInfo = m5.group(1);
                    numPages = Integer.parseInt(m5.group(2));
                }
                Matcher m6 = l6.matcher(fin.nextLine());
                if (m6.matches())
                {
                    ISBN = m6.group(1);
                }
                Matcher m7 = l7.matcher(fin.nextLine());
                while (m7.find())
                {
                    genres.add(m7.group(1));
                }
                books.add(new Book(author, title, madeBy, edition, locOfPub,
                        pubComp, dateOfPub, exInfo, numPages, ISBN, genres));
                if (fin.hasNextLine())
                {
                    fin.nextLine();
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (fin != null)
                fin.close();
        }
    }

    public boolean isNumber(String s)
    {
        try
        {
            Integer.parseInt(s);
        } catch (Exception e)
        {
            return false;
        }
        return true;
    }
}