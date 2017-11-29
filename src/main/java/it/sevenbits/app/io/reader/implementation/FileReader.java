package it.sevenbits.app.io.reader.implementation;

import it.sevenbits.app.io.closable.ClosableException;
import it.sevenbits.app.io.closable.IClosable;
import it.sevenbits.app.io.reader.IReader;
import it.sevenbits.app.io.reader.ReaderException;
import it.sevenbits.app.io.writer.WriterException;

import java.io.*;

public class FileReader implements IReader, IClosable {

    private BufferedReader bufferedReader;
    private int prevSymbolId;
    private int currentSymbolId;

    public FileReader(final String path) throws ReaderException {
        try {
            InputStream fileStream =
                    new FileInputStream(new File(path));
            Reader fileReader =
                    new InputStreamReader(fileStream, "utf-8");
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            throw new ReaderException("Opening file error", e);
        } catch (UnsupportedEncodingException e) {
            throw new ReaderException("Unsupported encoding", e);
        }
    }

    @Override
    public boolean hasNext() throws ReaderException {
        return currentSymbolId > -1;
    }

    @Override
    public char getChar() throws ReaderException {
        try{
            prevSymbolId = currentSymbolId;
            currentSymbolId = bufferedReader.read();
            ;
            return (char) prevSymbolId;
        } catch (IOException e){
            throw new ReaderException(e);
        }

    }

    @Override
    public void close() throws ClosableException {
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new ClosableException("Closing stream error", e);
        }
    }
}
