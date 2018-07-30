package exp.phani.lucene.index;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.FSDirectory;

import exp.phani.lucene.entity.Book;
import exp.phani.lucene.entity.Documentable;

public class PersistedLuceneIndexSearch<T extends Documentable> extends AbstractLuceneIndexSearch<T> {
    
    public PersistedLuceneIndexSearch() throws IOException {
        super(FSDirectory.open(Paths.get("build/_index/plis")));
    }
    
    public static void main(String[] args) throws IOException, ParseException {
        LuceneIndexSearch<Book> luceneIndexSearch = new PersistedLuceneIndexSearch<>();
        luceneIndexSearch.index(new Book("Lucene in Action", "193398817"));
        luceneIndexSearch.index(new Book("Lucene for Dummies", "55320055Z"));
        luceneIndexSearch.index(new Book("Managing Gigabytes", "55063554A"));
        luceneIndexSearch.index(new Book("The Art of Computer Science", "9900333X"));
        luceneIndexSearch.search("lucene");
    }
}
