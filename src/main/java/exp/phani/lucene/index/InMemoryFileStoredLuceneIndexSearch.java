package exp.phani.lucene.index;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

import exp.phani.lucene.convertor.BookConvertor;
import exp.phani.lucene.convertor.DocumentConvertor;
import exp.phani.lucene.entity.Book;
import exp.phani.lucene.entity.Documentable;

public class InMemoryFileStoredLuceneIndexSearch<T extends Documentable> extends AbstractLuceneIndexSearch<T> {
    public InMemoryFileStoredLuceneIndexSearch(DocumentConvertor<T> convertor) throws IOException {
        super(new RAMDirectory(FSDirectory.open(Paths.get("build/_index/imfs")), null), convertor);
    }

    public static void main(String[] args) throws IOException, ParseException {
        LuceneIndexSearch<Book> luceneIndexSearch = new InMemoryFileStoredLuceneIndexSearch<>(new BookConvertor());
        luceneIndexSearch.index(new Book("Lucene in Action", "193398817"));
        luceneIndexSearch.index(new Book("Lucene for Dummies", "55320055Z"));
        luceneIndexSearch.index(new Book("Managing Gigabytes", "55063554A"));
        luceneIndexSearch.index(new Book("The Art of Computer Science", "9900333X"));
        luceneIndexSearch.search("title", "lucene").forEach(System.out::println);
    }
}
