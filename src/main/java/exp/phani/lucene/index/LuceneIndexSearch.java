package exp.phani.lucene.index;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;

import exp.phani.lucene.entity.Documentable;

public interface LuceneIndexSearch<T extends Documentable> {

    void index(T entity) throws IOException;

    void search(String title) throws ParseException, IOException;

}
