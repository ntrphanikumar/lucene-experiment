package exp.phani.lucene.index;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
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

import exp.phani.lucene.convertor.DocumentConvertor;
import exp.phani.lucene.entity.Documentable;

public abstract class AbstractLuceneIndexSearch<T extends Documentable> implements LuceneIndexSearch<T> {
    private final StandardAnalyzer analyzer = new StandardAnalyzer();
    private final Directory directory;
    private final DocumentConvertor<T> convertor;

    public AbstractLuceneIndexSearch(Directory directory, DocumentConvertor<T> convertor) {
        this.directory = directory;
        this.convertor = convertor;
    }

    @Override
    public final void index(T entity) throws IOException {
        IndexWriter w = new IndexWriter(directory, new IndexWriterConfig(analyzer));
        w.addDocument(convertor.toDocument(entity));
        w.close();
    }

    @Override
    public final List<T> search(String field, String value) throws ParseException, IOException {
        Query q = new QueryParser(field, analyzer).parse(value);
        int hitsPerPage = 10;
        IndexReader reader = DirectoryReader.open(directory);
        final IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs docs = searcher.search(q, hitsPerPage);
        ScoreDoc[] hits = docs.scoreDocs;
        System.out.println("Found " + hits.length + " hits.");
        return Arrays.asList(hits).stream().map(h -> {
            try {
                return convertor.fromDocument(searcher.doc(h.doc));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

}
