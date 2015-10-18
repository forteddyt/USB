import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Library1
{
    private static List<Book> books = new ArrayList<Book>();
    private static Map<String, ArrayList<Book>> authorKey = new TreeMap<String, ArrayList<Book>>();
    private static Map<String, ArrayList<Book>> titleKey = new TreeMap<String, ArrayList<Book>>();
    private static Map<String, ArrayList<Book>> subjectKey = new TreeMap<String, ArrayList<Book>>();
    private static Map<String, Book> ISBNKey = new HashMap<String, Book>();
    private static Scanner scan = new Scanner(System.in);
    private static String exit = "\nEnter 0 to return to the Select Interface.\nEnter -1 to leave The Library.";

    public Library1(String course) {
        compileBooks(course);
        authorKey = byAuthor();
        titleKey = byTitle();
        subjectKey = bySubject();
        ISBNKey = byISBN();
    }

    public static void main(String[] args) {
        Library1 lib = new Library1("src/Catalog.txt");
        lib.UI();
    }

    public void UI() {
        System.out.println("\t\t***** Welcome to The Library! ***** ");
        selectInterface();
        System.exit(0);
    }

    public void selectInterface() {
        System.out
                .print("\nSearch options: \n1) Authors\n2) Title\n3) Genre\n4) ISBN\n");

        System.out.println(exit);
        double temp = scan.nextDouble();
        scan.nextLine();
        while (temp < -1 || temp > 4) {
            System.out
                    .println("You cannot select that index. Try a different index.\n\nSearch options: \n1) Authors\n2) Title\n3) Genre\n4) ISBN\n");
            System.out.println(exit);
            temp = scan.nextInt();
            scan.nextLine();
        }
        if (temp == 0)
            selectInterface();
        else
            choices((int) temp);
    }

    public void choices(int n) {
        if (n == -1) {
            System.out
                    .println(" --- Thanks for visiting! Come again soon! --- ");
            return;
        }
        if (n == 0)
            selectInterface();
        if (n == 1) {
            String nLine = "";
            System.out
                    .print("\t\t*** Search by Author ***\nWhat author would you like to search for? (last name, first name (middle initial(s)))\n");
            System.out.println(exit);
            nLine = scan.nextLine();
            try {
                if (Integer.parseInt(nLine) <= 0
                        && Integer.parseInt(nLine) >= -1) {
                    choices(Integer.parseInt(nLine));
                    return;
                }
                throw new Exception();
            } catch (Exception e) {
                ArrayList<Book> authors = authorKey.get(nLine);
                if (authors == null) {
                    System.out.println("There were no authors found.\n");
                    choices(1);
                    return;
                }
                displayBooks(authors);
            }
        }
        if (n == 2) {
            String nLine = "";
            System.out
                    .print("\t\t*** Search by Title ***\nWhat title would you like to search for?\n");
            System.out.println(exit);
            nLine = scan.nextLine();
            try {
                if (Integer.parseInt(nLine) <= 0
                        && Integer.parseInt(nLine) >= -1) {
                    choices(Integer.parseInt(nLine));
                    return;
                }
                throw new Exception();
            } catch (Exception e) {
                ArrayList<Book> titles = titleKey.get(nLine);
                if (titles == null) {
                    System.out.println("There were no titles found.");
                    choices(2);
                    return;
                }
                displayBooks(titles);
            }
        }
        if (n == 3) {
            String nLine = "";
            System.out
                    .print("\t\t*** Search by Genre ***\nWhat genre would you like to search for?\n");
            System.out.println(exit);
            nLine = scan.nextLine();
            try {
                if (Integer.parseInt(nLine) <= 0
                        && Integer.parseInt(nLine) >= -1) {
                    choices(Integer.parseInt(nLine));
                    return;
                }
                throw new Exception();
            } catch (Exception e) {
                ArrayList<Book> genres = subjectKey.get(nLine);
                if (genres == null) {
                    System.out.println("There were no genres found.");
                    choices(3);
                    return;
                }
                displayBooks(genres);
            }
        }
        if (n == 4) {
            String nLine = "";
            System.out
                    .print("\t\t*** Search by ISBN ***\nWhat ISBN would you like to search for?\n");
            System.out.println(exit);
            nLine = scan.nextLine();
            try {
                if (Integer.parseInt(nLine) <= 0
                        && Integer.parseInt(nLine) >= -1) {
                    choices(Integer.parseInt(nLine));
                    return;
                }
                throw new Exception();
            } catch (Exception e) {
                Book ISBNs = ISBNKey.get(nLine);
                if (ISBNs == null) {
                    System.out.println("There were no ISBNs found.");
                    choices(4);
                    return;
                }
                displayBooks(ISBNs);
            }
        }
    }

    public void displayBooks(Book b)/* test */{
        System.out.println("\n*** Details for ISBN: " + b.getISBN() + " ***\n"
                + b.toString() + "\n" + exit);

        String nLine = scan.nextLine();
        if (Integer.parseInt(nLine) >= -1 && Integer.parseInt(nLine) <= 0) {
            choices(Integer.parseInt(nLine));
        } else {
            System.out
                    .println("\nThat index does not exist. Try a different index.");
            displayBooks(b);
        }
    }

    public void displayBooks(ArrayList<Book> list) {
        int tracker = 1;
        Map<Integer, Book> tempA = new HashMap<Integer, Book>();
        System.out.print("\nBooks found: \n");
        for (Book b : list) {
            tempA.put(tracker, b);
            System.out.println(tracker + ") " + b.toShotrenedString());
            tracker++;
        }
        System.out
                .println("\nWhich book would you like more information on? (number)");
        System.out.println(exit);
        int nLine = scan.nextInt();
        scan.nextLine();
        while (nLine < 0 || nLine >= tempA.size()) {
            nLine = scan.nextInt();
            scan.nextLine();
        }
        if (nLine >= -1 && nLine <= 0)
            choices(nLine);
        else {
            while (nLine < -1 || nLine >= tracker) {
                System.out
                        .println("\nThat index does not exist. Try a different index.\n");
                System.out.print("Books found: \n");
                tracker = 1;
                for (Book b : list) {
                    tempA.put(tracker, b);
                    System.out.println(tracker + ") " + b.toShotrenedString());
                    tracker++;
                }
                System.out.println(exit);
                nLine = scan.nextInt();
                scan.nextLine();
                if (nLine >= -1 && nLine <= 0) {
                    choices(nLine);
                    return;
                }
            }
            System.out.println("\n*** Details for '"
                    + tempA.get(nLine).toShotrenedString() + "' ***\n"
                    + tempA.get(nLine).toString());
            System.out.print("\nPress enter to continue searching.");
            scan.nextLine();
            System.out.println("");
            displayBooks(list);
        }
    }

    public Map<String, ArrayList<Book>> byAuthor() {
        Map<String, ArrayList<Book>> temp = new TreeMap<String, ArrayList<Book>>();
        for (Book b : books) {
            for (String s : b.getMadeBy()) {
                if (temp.containsKey(s))
                    temp.get(s).add(b);
                else {
                    ArrayList<Book> temp2 = new ArrayList<Book>();
                    temp2.add(b);
                    temp.put(s, temp2);
                }
            }
        }
        return temp;
    }

    public Map<String, ArrayList<Book>> byTitle() {
        Map<String, ArrayList<Book>> temp = new TreeMap<String, ArrayList<Book>>();
        for (Book b : books) {
            if (temp.containsKey(b.getTitle()))
                temp.get(b.getTitle()).add(b);
            else {
                ArrayList<Book> temp2 = new ArrayList<Book>();
                temp2.add(b);
                temp.put(b.getTitle(), temp2);
            }
        }
        return temp;
    }

    public Map<String, ArrayList<Book>> bySubject() {
        Map<String, ArrayList<Book>> temp = new TreeMap<String, ArrayList<Book>>();
        for (Book b : books) {
            for (String s : b.getGenres()) {
                if (temp.containsKey(s))
                    temp.get(s).add(b);
                else {
                    ArrayList<Book> temp2 = new ArrayList<Book>();
                    temp2.add(b);
                    temp.put(s, temp2);
                }
            }
        }
        return temp;
    }

    public Map<String, Book> byISBN() {
        Map<String, Book> temp = new HashMap<String, Book>();
        for (Book b : books)
            temp.put(b.getISBN(), b);
        return temp;
    }

    public void compileBooks(String source) {
        Scanner fin = null;
        Pattern l3 = Pattern
                .compile("(by)? (([\\w\\.']*) ?([\\w\\.']*))[,\\.]");
        Pattern l4 = Pattern.compile("-- (.*) -- (.*) : (.*), (\\d+)\\.");
        Pattern l5 = Pattern.compile("\\b(.*), (\\d+)p\\.");
        Pattern l6 = Pattern.compile("ISBN (.*)");
        Pattern l7 = Pattern.compile("\\d+\\. ([\\w ]*)[,\\.] ?");

        try {
            fin = new Scanner(new File(source));
            while (fin.hasNextLine()) {
                String author = null, title = null, edition = null, locOfPub = null, pubComp = null, exInfo = null, ISBN = null;
                int dateOfPub = 0, numPages = 0;
                List<String> genres = new ArrayList<String>();
                List<String> madeBy = new ArrayList<String>();

                author = fin.nextLine();
                title = fin.nextLine();
                Matcher m3 = l3.matcher(fin.nextLine());
                while (m3.find()) {
                    madeBy.add(m3.group(4) + ", " + m3.group(3));
                }
                Matcher m4 = l4.matcher(fin.nextLine());
                if (m4.matches()) {
                    edition = m4.group(1);
                    locOfPub = m4.group(2);
                    pubComp = m4.group(3);
                    dateOfPub = Integer.parseInt(m4.group(4));
                }
                Matcher m5 = l5.matcher(fin.nextLine());
                if (m5.matches()) {
                    exInfo = m5.group(1);
                    numPages = Integer.parseInt(m5.group(2));
                }
                Matcher m6 = l6.matcher(fin.nextLine());
                if (m6.matches())
                    ISBN = m6.group(1);
                Matcher m7 = l7.matcher(fin.nextLine());
                while (m7.find()) {
                    genres.add(m7.group(1));
                }

                books.add(new Book(author, title, madeBy, edition, locOfPub,
                        pubComp, dateOfPub, exInfo, numPages, ISBN, genres));
                if (fin.hasNextLine())
                    fin.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fin != null)
                fin.close();
        }
    }
}

/*
 * 
 * Alele, Whiff, Only the Sickest Flicks: A Battlecry of the Silver 1, 42 , 123
 * 
 * Cabbagecannon, Ian, The Race to Platinum, 2015 Yay, No-A, How to Get to Gold:
 * Featuring Nandonine, 2015 , Actaoml
 * 
 * Wolf, Eric, Halfway to Second, 2014 , Alex
 * 
 * Bell, Roland, Leg Exercise: Putting Your Entire Team on your Back, 2015 ,
 * Bell
 * 
 * Lamperouge, Lelouch, How To Conquer Ladies and the World Simultaneously: A
 * True Story, 9999 , C2
 * 
 * Alele, Whiff, Only the Sickest Flicks: A Battlecry of the Silver 1, 42 , D
 * 
 * Alele, Whiff, Only the Sickest Flicks: A Battlecry of the Silver 1, 42 , ET
 * 
 * West, Kanye, Why I am the Most Humble Person in the Universe., 3005 , God
 * 
 * Alele, Whiff, Only the Sickest Flicks: A Battlecry of the Silver 1, 42 ,
 * Gunner
 * 
 * Hare, "Peppy", Useless Aerospace Maneuvers: The History of the Barrel Roll,
 * 3104 , Hare
 * 
 * Alele, Whiff, Only the Sickest Flicks: A Battlecry of the Silver 1, 42 ,
 * Hunter
 * 
 * Alele, Whiff, Only the Sickest Flicks: A Battlecry of the Silver 1, 42 ,
 * KlunchK3y
 * 
 * Alele, Whiff, Only the Sickest Flicks: A Battlecry of the Silver 1, 42 ,
 * Lawwww
 * 
 * Alele, Whiff, Only the Sickest Flicks: A Battlecry of the Silver 1, 42 ,
 * Maikelele
 * 
 * Martin, George, The Dragons are Coming, 2012 , Martin
 * 
 * Alele, Whiff, Only the Sickest Flicks: A Battlecry of the Silver 1, 42 ,
 * Noodle
 * 
 * Alele, Whiff, Only the Sickest Flicks: A Battlecry of the Silver 1, 42 ,
 * Partyman
 * 
 * Nayshun, Puncayshun, How to Get Sub 1:42:xx, 2014 , Puncayshun
 * 
 * Bell, Roland, Leg Exercise: Putting Your Entire Team on your Back, 2015 ,
 * Roland
 * 
 * Alele, Whiff, Only the Sickest Flicks: A Battlecry of the Silver 1, 42 ,
 * Smack
 * 
 * Alele, Whiff, Only the Sickest Flicks: A Battlecry of the Silver 1, 42 , Tide
 * 
 * West, Kanye, Why I am the Most Humble Person in the Universe., 3005 , Weezy
 * 
 * Alele, Whiff, Only the Sickest Flicks: A Battlecry of the Silver 1, 42 , Wolf
 * 
 * Alele, Whiff, Only the Sickest Flicks: A Battlecry of the Silver 1, 42 , Yan
 * 
 * Yay, No-A, How to Get to Gold: Featuring Nandonine, 2015 , Yay
 */