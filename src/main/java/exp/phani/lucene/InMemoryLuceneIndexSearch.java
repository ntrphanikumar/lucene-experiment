package exp.phani.lucene;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.RAMDirectory;

public class InMemoryLuceneIndexSearch {
    private final StandardAnalyzer analyzer = new StandardAnalyzer();
    private final RAMDirectory directory = new RAMDirectory();
    
    public void index(String title, String isbn) throws IOException {
        IndexWriter w = new IndexWriter(directory, new IndexWriterConfig(analyzer));
        addDoc(w, title, isbn);
        w.close();
    }

    private void addDoc(IndexWriter w, String title, String isbn) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("title", title, Field.Store.YES));
        doc.add(new StringField("isbn", isbn, Field.Store.YES));
        w.addDocument(doc);
    }
    
    public void search() throws ParseException, IOException {
        Query q = new QueryParser("title", analyzer).parse("lucene");
        int hitsPerPage = 10;
        IndexReader reader = DirectoryReader.open(directory);
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;
        System.out.println("Found " + hits.length + " hits.");
        for(int i=0;i<hits.length;++i) {
            int docId = hits[i].doc;
            Document d = searcher.doc(docId);
            System.out.println((i + 1) + ". " + d.get("isbn") + "\t" + d.get("title"));
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        InMemoryLuceneIndexSearch inMemoryLuceneIndexSearch = new InMemoryLuceneIndexSearch();
        inMemoryLuceneIndexSearch.index("Lucene in Action", "193398817");
        inMemoryLuceneIndexSearch.index("Lucene for Dummies", "55320055Z");
        inMemoryLuceneIndexSearch.index("Managing Gigabytes", "55063554A");
        inMemoryLuceneIndexSearch.index("The Art of Computer Science", "9900333X");
        inMemoryLuceneIndexSearch.search();
        
        inMemoryLuceneIndexSearch = new InMemoryLuceneIndexSearch();
        inMemoryLuceneIndexSearch.index("Lucene in Action", "193398817");
        inMemoryLuceneIndexSearch.search();
    }
}
