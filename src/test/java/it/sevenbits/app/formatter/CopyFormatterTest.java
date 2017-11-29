package it.sevenbits.app.formatter;

import it.sevenbits.app.formatter.implementation.CopyFormatter;
import it.sevenbits.app.io.reader.IReader;
import it.sevenbits.app.io.reader.ReaderException;
import it.sevenbits.app.io.reader.implementation.StringReader;
import it.sevenbits.app.io.writer.IWriter;
import it.sevenbits.app.io.writer.WriterException;
import it.sevenbits.app.io.writer.implementation.StringWriter;
import it.sevenbits.app.lexer.ILexer;
import it.sevenbits.app.lexer.implementation.Lexer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CopyFormatterTest {
    private IFormatter formatter;

    @Before
    public void setUp() {

        formatter = new CopyFormatter();
    }

    @Test
    public void simpleTest() throws WriterException, ReaderException, FormatterException {
        IReader reader = new StringReader("Hello;world");
        Lexer lexer = new Lexer(reader);
        IWriter out = new StringWriter();
        formatter.format(lexer, out);
        assertEquals("Hello;\nworld", out.toString());
    }

    @Test
    public void secondSimpleTest() throws WriterException, ReaderException, FormatterException {
        IReader in = new StringReader("Hello{fdcs;}fcd;");
        IWriter out = new StringWriter();
        Lexer lexer = new Lexer(in);
        formatter.format(lexer, out);
        assertEquals("Hello {\n    fdcs;\n    }\nfcd", out.toString());
    }


}
