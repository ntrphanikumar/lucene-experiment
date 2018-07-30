package exp.phani.lucene.entity;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

public class Book implements Documentable {
    private String title, isbn;
    
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
    public Document toDocument() {
        Document document = new Document();
        document.add(new TextField("title", title, Field.Store.YES));
        document.add(new StringField("isbn", isbn, Field.Store.YES));
        return document;
    }
}
