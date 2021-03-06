package Domain;

import java.util.*;

public class ProgramInternalForm {
    List<Pair<Integer,Integer>> pif = new ArrayList<>();
    private final HashMap<String, Integer> codification = new HashMap<>();

    private List<String> reservedWords =
            Arrays.asList("int", "string", "char", "for", "while", "if", "else", "read", "write","and");
    private List<String> separators = Arrays.asList("(", ")", ";", "{", "}","[", "]", " ", ":");
    private List<String> operators = Arrays.asList("+","-","*","/","<",">","<=","=",">=","!=","==");

    private final FiniteAutomata intDFA = new FiniteAutomata("src/res/integer.in");
    private final FiniteAutomata identifierDFA = new FiniteAutomata("src/res/identifier.in");

    public ProgramInternalForm() {
        createCodification();
    }

    public void createCodification(){
        codification.put("identifier",0);
        codification.put("constant",1);

        int code = 2;

        for (String reservedWord : reservedWords) {
            codification.put(reservedWord, code);
            code++;
        }

        for (String operator : operators) {
            codification.put(operator, code);
            code++;
        }

        for (String separator : separators) {
            codification.put(separator, code);
            code++;
        }
    }

    public boolean isReservedWord(String token) {
        return reservedWords.contains(token);
    }

    public boolean isOperator(String token) {
        return operators.contains(token);
    }

    public boolean isSeparator(String token) {
        return separators.contains(token);
    }

    public boolean isIdentifier(String token){
        //String pattern = "^[a-zA-Z]([a-z|A-Z])*([0-9])*$";
        //return token.matches(pattern);
        return identifierDFA.checkSequence(token);
    }

    public boolean isConstant(String token){
        //String numericPattern = "^([+-]?[1-9]\\d*|0)$";
        String charPattern = "^\'[a-zA-Z0-9_?!#*./%+=<>;)(}{ ]\'";
        String stringPattern = "^\"[a-zA-Z0-9_?!#*./%+=<>;)(}{ ]+\"";
        return intDFA.checkSequence(token) || token.matches(charPattern) || token.matches(stringPattern);
        //return token.matches(numericPattern) || token.matches(charPattern) || token.matches(stringPattern);
    }

    public void add(Integer position,Integer name){
        pif.add(new Pair<>(position,name));
    }

    public Integer getCode(String token) {
        return codification.get(token);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Pair<Integer,Integer> pair : pif){
            stringBuilder.append(pair.getKey() + " -> " + pair.getValue() + "\n");
        }
        return stringBuilder.toString();
    }
}
