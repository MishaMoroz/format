package it.sevenbits.app.lexer;

public interface ILexer {

    boolean hasMoreTokens();

    IToken readToken() throws LexerException;

}
