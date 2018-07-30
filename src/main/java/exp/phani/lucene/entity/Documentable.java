package exp.phani.lucene.entity;

import org.apache.lucene.document.Document;

public interface Documentable {
    Document toDocument();
}
