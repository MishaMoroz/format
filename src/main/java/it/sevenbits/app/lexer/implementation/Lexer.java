package it.sevenbits.app.lexer.implementation;

import it.sevenbits.app.io.closable.ClosableException;
import it.sevenbits.app.io.reader.ReaderException;
import it.sevenbits.app.lexer.ILexer;
import it.sevenbits.app.io.reader.IReader;
import it.sevenbits.app.lexer.IToken;
import it.sevenbits.app.lexer.LexerException;

import java.io.IOException;

public class Lexer implements ILexer {
    private IReader reader;
    public Lexer(final IReader reader) {

        this.reader = reader;
    }

    @Override
    public boolean hasMoreTokens() {
        try {
            return reader.hasNext();
        } catch (ReaderException e){
            throw new ExceptionInInitializerError(e);
        }
    }

    @Override
    public IToken readToken() throws LexerException{
        try {
            String name;
            String lexeme;
            while (reader.hasNext()) {
                char ch = reader.getChar();
                switch (ch) {
                    case '{': name = "openBracket"; break;
                    case '}': name = "closeBracket"; break;
                    case ';': name = "semicolon"; break;
                    case '\n': name = "newLine"; break;
                    case ' ': name = "whitespace"; break;
                    default: name = "singleChar"; break;
                }
                lexeme = Character.toString(ch);
                return new Token(name, lexeme);
            }
        } catch (ReaderException e) {
            throw new LexerException(e);
        }
        return new Token(" ", " ");
    }
}