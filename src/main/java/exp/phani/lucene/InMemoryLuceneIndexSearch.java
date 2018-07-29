package exp.phani.lucene;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.RAMDirectory;

public class InMemoryLuceneIndexSearch extends AbstractLuceneIndexSearch {
    public InMemoryLuceneIndexSearch() {
        super(new RAMDirectory());
    }
    
    public static void main(String[] args) throws IOException, ParseException {
        LuceneIndexSearch luceneIndexSearch = new InMemoryLuceneIndexSearch();
        luceneIndexSearch.index("Lucene in Action", "193398817");
        luceneIndexSearch.index("Lucene for Dummies", "55320055Z");
        luceneIndexSearch.index("Managing Gigabytes", "55063554A");
        luceneIndexSearch.index("The Art of Computer Science", "9900333X");
        luceneIndexSearch.search("lucene");
    }
}
