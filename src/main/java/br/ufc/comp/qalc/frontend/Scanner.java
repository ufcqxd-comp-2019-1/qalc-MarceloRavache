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
        if (source.getCurrentChar() == Source.EOF)

        {//EOF
            return new EOFToken(source.getCurrentLine(), source.getCurrentColumn());
        } else if (Character.isDigit(source.getCurrentChar()))

        { // NumberToken NUMBER
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            do {
                lexema.append(source.getCurrentChar());
                source.advance();
            } while (Character.isDigit(source.getCurrentChar()));

            String stringValue = lexema.toString();

            return new NumberToken(currentLine, lexemeStart, stringValue);
        } else if (source.getCurrentChar() == '$')

        { // VaridIdentifier or ResultIdentifier


            source.advance();
            if(Character.isDigit(source.getCurrentChar())) {
                StringBuilder lexema = new StringBuilder();

                long currentLine = source.getCurrentLine();
                long lexemeStart = source.getCurrentColumn();
                do {
                    lexema.append(source.getCurrentChar());
                    source.advance();
                } while (Character.isDigit(source.getCurrentChar()));
                String stringValue = lexema.toString();
                return new ResultIdentifierToken(currentLine, lexemeStart, stringValue);
            } else if(Character.isLetter(source.getCurrentChar())) {
                StringBuilder lexema = new StringBuilder();

                long currentLine = source.getCurrentLine();
                long lexemeStart = source.getCurrentColumn();
                do {
                    lexema.append(source.getCurrentChar());
                    source.advance();
                } while (Character.isLetter(source.getCurrentChar()));
                String stringValue = lexema.toString();
                return new VariableIdentifierToken(currentLine, lexemeStart, stringValue);
            }else{
                System.out.print("Error: ");
                System.out.println(getCurrentToken());
                return getNextToken();
            }

        } else if(source.getCurrentChar()=='@')

        {//FunctionIdentifier
            source.advance();
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();
            if((Character.isLetter(source.getCurrentChar())) ||(Character.isDigit(source.getCurrentChar()))) {
                do {
                    lexema.append(source.getCurrentChar());
                    source.advance();
                } while ((Character.isLetter(source.getCurrentChar())) || (Character.isDigit(source.getCurrentChar())));

                String stringValue = lexema.toString();

                return new FunctionIdentifierToken(currentLine, lexemeStart, stringValue);
            }else{//Erro
                System.out.print("Error: ");
                System.out.println(source.getCurrentChar());
                return getNextToken();
            }
        }else if(source.getCurrentChar()=='+')

        {//Plus
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new PlusToken(currentLine, lexemaStart, stringValue);
        }else if(source.getCurrentChar()=='-')

        {//Minus
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new MinusToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar()=='/')

        {//Div
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new DivToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar()=='*')

        {//Times
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new TimesToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar()=='(')

        {//LParen
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new LParenToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar()==')')

        {//RParen
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new RParenToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar()=='=')

        {//Atrib
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new AtribToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar()=='%')

        {//Mod
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new ModToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar()=='^')

        {//Pow
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new PowToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar()==';')

        {//Semi
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new SemiToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar()==',')

        {//Comma
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new CommaToken(currentLine, lexemaStart, stringValue);

        }else if(source.getCurrentChar()=='#')

        {//Comentario
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

        }else if(source.getCurrentChar() == ' ')

        {// Space
            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new WhiteToken(currentLine, lexemaStart, stringValue);

        }else {//Error
            System.out.println("ERRO! Lexer: ");
            System.out.print(source.getCurrentChar());

            long currentLine = source.getCurrentLine();
            long lexemaStart = source.getCurrentColumn();

            StringBuilder lexema = new StringBuilder();
            lexema.append(source.getCurrentChar());
            String stringValue = lexema.toString();

            source.advance();

            return new ErrorToken(currentLine, lexemaStart, stringValue);

        }

        //return null;
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
