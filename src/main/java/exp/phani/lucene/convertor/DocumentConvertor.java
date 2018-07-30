package exp.phani.lucene.convertor;

import org.apache.lucene.document.Document;

import exp.phani.lucene.entity.Documentable;

public interface DocumentConvertor<T extends Documentable> {
    Document toDocument(T entity);
    
    T fromDocument(Document document);
}
