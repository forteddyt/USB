import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book implements Comparable
{
    private List<String> madeBy = new ArrayList<String>();
    private List<String> genres = new ArrayList<String>();
    private String author, title, edition, locOfPub, pubComp, exInfo;
    private String ISBN;
    private int dateOfPub, numPages;

    public Book(String author, String title, List<String> madeBy,
            String edition, String locOfPub, String pubComp, int dateOfPub,
            String exInfo, int numPages, String ISBN, List<String> genres)
    {
        this.madeBy = madeBy;
        this.author = author;
        this.title = title;
        this.edition = edition;
        this.locOfPub = locOfPub;
        this.pubComp = pubComp;
        this.dateOfPub = dateOfPub;
        this.exInfo = exInfo;
        this.numPages = numPages;
        this.ISBN = ISBN;
        this.genres = genres;
    }

    public List<String> getMadeBy()
    {
        return madeBy;
    }

    public List<String> getGenres()
    {
        return genres;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getTitle()
    {
        return title;
    }

    public String getEdition()
    {
        return edition;
    }

    public String getLocOfPub()
    {
        return locOfPub;
    }

    public String getPubComp()
    {
        return pubComp;
    }

    public String getExInfo()
    {
        return exInfo;
    }

    public String getISBN()
    {
        return ISBN;
    }

    public int getDateOfPub()
    {
        return dateOfPub;
    }

    @Override
    public String toString()
    {
        String a = "";
        Pattern p = Pattern.compile("(.*,) (.*)");
        for (String s : madeBy)
        {
            Matcher m = p.matcher(s);
            if (m.matches())
            {
                a = a + m.group(2) + " " + m.group(1) + " ";
            }
        }
        a = a.substring(0, a.length() - 2);
        return "Author: " + author + "\nTitle: " + title + "\nAuthors: " + a
                + "\nPublished: " + pubComp + ", " + locOfPub + ", "
                + dateOfPub + "\nISBN: " + ISBN + "\nPages: " + numPages
                + "\nSubjects: " + genres.toString().replaceAll("[\\[\\]]", "")
                + ".";
    }

    public String toShotrenedString()
    {
        return author + ", " + title + ", " + dateOfPub;
    }

    @Override
    public int compareTo(Object o)
    {
        // TODO Auto-generated method stub
        return toString().compareTo(((Book) o).toString());
    }
}
