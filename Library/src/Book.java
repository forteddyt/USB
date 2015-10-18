public class Book {
    private String author, title, genre;
    private final long ISBN;

    public Book(long ISBN, String author, String title, String genre) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public long getISBN() {
        return ISBN;
    }

    @Override
    public String toString() {
        return "Book [author=" + author + ", title=" + title + ", genre="
                + genre + ", ISBN=" + ISBN + "]";
    }

}
