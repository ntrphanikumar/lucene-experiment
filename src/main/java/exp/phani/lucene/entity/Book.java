package exp.phani.lucene.entity;

public class Book implements Documentable {
    private String title;
    private String isbn;

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "{title: '" + title + "', isbn: '" + isbn + "'}";
    }
}
