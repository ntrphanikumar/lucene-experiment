package exp.phani.lucene;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

public class InMemoryFileStoredLuceneIndexSearch extends AbstractLuceneIndexSearch {
    public InMemoryFileStoredLuceneIndexSearch() throws IOException {
        super(new RAMDirectory(FSDirectory.open(Paths.get("_index/1")), null));
    }
    
    public static void main(String[] args) throws IOException, ParseException {
        LuceneIndexSearch luceneIndexSearch = new InMemoryFileStoredLuceneIndexSearch();
        luceneIndexSearch.index("Lucene in Action", "193398817");
        luceneIndexSearch.index("Lucene for Dummies", "55320055Z");
        luceneIndexSearch.index("Managing Gigabytes", "55063554A");
        luceneIndexSearch.index("The Art of Computer Science", "9900333X");
        luceneIndexSearch.search("lucene");
    }
}
