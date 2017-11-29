package it.sevenbits.app.formatter;

import it.sevenbits.app.io.reader.IReader;
import it.sevenbits.app.io.writer.IWriter;
import it.sevenbits.app.lexer.ILexer;

public interface IFormatter {

    void format(ILexer lexer, IWriter out) throws FormatterException;
}
