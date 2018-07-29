package exp.phani.lucene;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;

public interface LuceneIndexSearch {

    void index(String title, String isbn) throws IOException;

    void search(String title) throws ParseException, IOException;

}
