package exp.phani.lucene.index;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
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
import org.apache.lucene.store.Directory;

import exp.phani.lucene.entity.Documentable;

public abstract class AbstractLuceneIndexSearch<T extends Documentable> implements LuceneIndexSearch<T> {
    private final StandardAnalyzer analyzer = new StandardAnalyzer();
    private final Directory directory;
    
    public AbstractLuceneIndexSearch(Directory directory) {
        this.directory = directory;
    }
    
    @Override
    public final void index(T entity) throws IOException {
        IndexWriter w = new IndexWriter(directory, new IndexWriterConfig(analyzer));
        w.addDocument(entity.toDocument());
        w.close();
    }
    
    @Override
    public final void search(String title) throws ParseException, IOException {
        Query q = new QueryParser("title", analyzer).parse(title);
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
    
}
