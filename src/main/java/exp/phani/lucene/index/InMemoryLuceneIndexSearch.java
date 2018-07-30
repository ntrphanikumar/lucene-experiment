package exp.phani.lucene.index;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.RAMDirectory;

import exp.phani.lucene.convertor.BookConvertor;
import exp.phani.lucene.convertor.DocumentConvertor;
import exp.phani.lucene.entity.Book;
import exp.phani.lucene.entity.Documentable;

public class InMemoryLuceneIndexSearch<T extends Documentable> extends AbstractLuceneIndexSearch<T> {
    public InMemoryLuceneIndexSearch(DocumentConvertor<T> convertor) {
        super(new RAMDirectory(), convertor);
    }

    public static void main(String[] args) throws IOException, ParseException {
        LuceneIndexSearch<Book> luceneIndexSearch = new InMemoryLuceneIndexSearch<>(new BookConvertor());
        luceneIndexSearch.index(new Book("Lucene in Action", "193398817"));
        luceneIndexSearch.index(new Book("Lucene for Dummies", "55320055Z"));
        luceneIndexSearch.index(new Book("Managing Gigabytes", "55063554A"));
        luceneIndexSearch.index(new Book("The Art of Computer Science", "9900333X"));
        luceneIndexSearch.search("title", "lucene").forEach(System.out::println);
    }
}
