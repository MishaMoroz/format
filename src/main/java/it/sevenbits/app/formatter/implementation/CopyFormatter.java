package it.sevenbits.app.formatter.implementation;

import it.sevenbits.app.formatter.FormatterException;
import it.sevenbits.app.formatter.IFormatter;
import it.sevenbits.app.io.reader.IReader;
import it.sevenbits.app.io.reader.ReaderException;
import it.sevenbits.app.io.writer.IWriter;
import it.sevenbits.app.io.writer.WriterException;
import it.sevenbits.app.lexer.ILexer;
import it.sevenbits.app.lexer.IToken;
import it.sevenbits.app.lexer.LexerException;
import it.sevenbits.app.lexer.implementation.Token;
import org.omg.CORBA.WrongTransaction;

import java.io.IOException;

public class CopyFormatter implements IFormatter {


    @Override
    public void format(final ILexer lexer, final IWriter out) throws FormatterException {
        try {
            int indent = 0;
            boolean newLine = true;
            IToken prevToken = new Token("", "");
            while (lexer.hasMoreTokens()) {
                IToken token = lexer.readToken();
                String name = token.getName();
                String lexeme = token.getLexeme();
                System.out.println(lexeme);
                if(!(newLine && name.equals("whitespace"))){
                    if(prevToken.getName().equals("newLine") && name.equals("closeBracket")){
                        indent(out, --indent);
                        write(out, lexeme);
                        continue;
                    }
                    newLine = false;
                    if(name.equals("semicolon")){
                        lexeme += "\n";
                        newLine=true;
                    }
                    if(name.equals("openBracket")) {
                        if(!prevToken.getName().equals("whitespace")) {
                            lexeme = " " + lexeme;
                        }
                        lexeme += "\n";
                        newLine=true;
                        indent++;
                    }
                    if(name.equals("closeBracket")){
                        lexeme += "\n";
                        newLine=true;
                        indent--;
                    }

                        write(out, lexeme);
                    if(newLine){
                        indent(out, indent);
                    }

                }
                prevToken = token;
            }
        } catch (WriterException e) {
            throw new FormatterException("Writing error", e);
        } catch (LexerException e) {

        }
    }

    public void write(IWriter out, String lexeme) throws WriterException {
        char[] ch = lexeme.toCharArray();
        for(char c : ch){
            out.write(c);
        }
    }

    public void indent(IWriter out, int indent) throws WriterException {
        for(int i=0; i < indent*4; i++){
            out.write(' ');
        }
    }
}