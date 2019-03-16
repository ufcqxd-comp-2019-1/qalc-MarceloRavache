package br.ufc.comp.qalc.frontend;

import br.ufc.comp.qalc.frontend.token.*;

import java.io.IOException;

/**
 * Analisador Léxico da linguagem.
 * <p>
 * Funciona como uma fonte de tokens, de onde o Analisador Sintático
 * deverá ler.
 *
 * @see Source
 */
public class Scanner {

    /**
     * Último token reconhecido.
     */
    protected Token currentToken;
    /**
     * Fonte de caracteres de onde extrair os tokens.
     */
    protected Source source;

    /**
     * Constrói um Analisador Léxico a partir de uma fonte de caracteres.
     *
     * @param sourceStream Fonte a ser utilizada.
     */
    public Scanner(Source sourceStream) {
        // FIXME Lidar corretamente com as exceções.
        this.source = sourceStream;
    }

    /**
     * Consome caracteres da fonte até que eles componham um lexema de
     * um dos tokens da linguagem, coinstrói um objeto representando esse
     * token associado ao lexema lido e o retorna.
     *
     * @return Token reconhecido.
     * @throws IOException Caso haja problema na leitura da fonte.
     */
    public Token getNextToken() throws IOException {
        // TODO Reconhecimento de tokens
        if (source.getCurrentChar() == Source.EOF) {//scan EOF
            return new EOFToken(source.getCurrentLine(), source.getCurrentColumn());
        } else if (Character.isDigit(source.getCurrentChar())) { // NumberToken scan NUMBER
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            do {
                lexema.append(source.getCurrentChar());
                source.advance();
            } while (Character.isDigit(source.getCurrentChar()));

            String stringValue = lexema.toString();

            return new NumberToken(currentLine, lexemeStart, stringValue);
        } else if (source.getCurrentChar() == '$') { // scan ResultIdentifier

            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();
            do {
                lexema.append(source.getCurrentChar());
                source.advance();
            } while (Character.isDigit(source.getCurrentChar()));

            String stringValue = lexema.toString();

            return new ResultIdentifierToken(currentLine, lexemeStart, stringValue);

        } else if (Character.isLetter(source.getCurrentChar())) {
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();
            do {
                lexema.append(source.getCurrentChar());
                source.advance();
            } while (Character.isLetter(source.getCurrentChar()));

            String stringValue = lexema.toString();

            return new VariableIdentifierToken(currentLine, lexemeStart, stringValue);

        }else if(source.getCurrentChar()=='@')

        { // NumberToken
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            do {
                lexema.append(source.getCurrentChar());
                source.advance();
            } while (Character.isDigit(source.getCurrentChar()));

            String stringValue = lexema.toString();

            return new NumberToken(currentLine, lexemeStart, stringValue);
        }else if(source.getCurrentChar()=='+')

        {
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new PlusToken(currentLine, lexemaStart, stringValue);
        }else if(source.getCurrentChar()=='-')

        {
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new MinusToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar()=='/')

        {
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new DivToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar()=='*')

        {
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new TimesToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar()=='(')

        {
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new LParenToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar()==')')

        {
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new RParenToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar()=='=')

        {
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new AtribToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar()=='%')

        {
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new ModToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar()=='^')

        {
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new PowToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar()==';')

        {
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new SemiToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar()==',')

        {
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new CommaToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar()=='#'){
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();

            do {
                lexema.append(source.getCurrentChar());
                source.advance();
            } while (((int) (source.getCurrentChar())) != 10 );

            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new ComToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar() == ' ') {
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new WhiteToken(currentLine, lexemaStart, stringValue);

        }else{
            System.out.println("ERRO! Lexer: ");
            System.out.print(source.getCurrentChar());
            
        }

        return null;
}

    /**
     * Obtém o último token reconhecido.
     *
     * @return O último token reconhecido.
     */
    public Token getCurrentToken() {
        return currentToken;
    }
}
