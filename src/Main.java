import Domain.FiniteAutomata;
import Domain.LexicalAnalyzer;

import java.util.Scanner;

class Main{
    public static void main(String[] args)  {
        System.out.println("1. Test DFA");
        System.out.println("2. Scanner");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        if(option == 1){
            FiniteAutomata fn = new FiniteAutomata("src/res/FA.in");
            fn.runMenu();
        }
        else {
            LexicalAnalyzer lexicalAnalyzer1 = new LexicalAnalyzer("p1.txt");
            LexicalAnalyzer lexicalAnalyzer2 = new LexicalAnalyzer("p2.txt");
            LexicalAnalyzer lexicalAnalyzer3 = new LexicalAnalyzer("p3.txt");
            LexicalAnalyzer lexicalAnalyzer4 = new LexicalAnalyzer("p1err.txt");
        }
        //FiniteAutomata identifierDFA = new FiniteAutomata("src/res/identifier.in");

    }
}
