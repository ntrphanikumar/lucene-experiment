package exp.phani.lucene.convertor;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

import exp.phani.lucene.entity.Book;

public class BookConvertor implements DocumentConvertor<Book>{

    @Override
    public Document toDocument(Book entity) {
        Document document = new Document();
        document.add(new TextField("title", entity.getTitle(), Field.Store.YES));
        document.add(new StringField("isbn", entity.getIsbn(), Field.Store.YES));
        return document;
    }

    @Override
    public Book fromDocument(Document document) {
        return new Book(document.get("title"), document.get("isbn"));
    }

}
