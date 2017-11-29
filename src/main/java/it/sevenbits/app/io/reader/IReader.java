package it.sevenbits.app.io.reader;

public interface IReader {

    boolean hasNext() throws ReaderException;

    char getChar() throws ReaderException;
}
