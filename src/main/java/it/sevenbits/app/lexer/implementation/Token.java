package it.sevenbits.app.lexer.implementation;

import it.sevenbits.app.lexer.IToken;

public class Token implements IToken {
    private String name;
    private String lexeme;

    public Token(final String name, final String lexeme) {
        this.name = name;
        this.lexeme = lexeme;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLexeme() {
        return lexeme;
    }
}
