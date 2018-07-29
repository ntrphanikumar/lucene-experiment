package exp.phani.lucene;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.FSDirectory;

public class PersistedLuceneIndexSearch extends AbstractLuceneIndexSearch {
    
    public PersistedLuceneIndexSearch() throws IOException {
        super(FSDirectory.open(Paths.get("_index")));
    }
    
    public static void main(String[] args) throws IOException, ParseException {
        LuceneIndexSearch luceneIndexSearch = new PersistedLuceneIndexSearch();
        luceneIndexSearch.index("Lucene in Action", "193398817");
        luceneIndexSearch.index("Lucene for Dummies", "55320055Z");
        luceneIndexSearch.index("Managing Gigabytes", "55063554A");
        luceneIndexSearch.index("The Art of Computer Science", "9900333X");
        luceneIndexSearch.search("lucene");
    }
}
