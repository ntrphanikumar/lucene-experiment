package exp.phani.lucene.index;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;

import exp.phani.lucene.entity.Documentable;

public interface LuceneIndexSearch<T extends Documentable> {

    void index(T entity) throws IOException;

    List<T> search(String field, String value) throws ParseException, IOException;

}
