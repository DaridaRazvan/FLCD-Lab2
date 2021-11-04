import Domain.LexicalAnalyzer;

class Main{
    public static void main(String[] args)  {
        LexicalAnalyzer lexicalAnalyzer1 = new LexicalAnalyzer("p.txt");
        LexicalAnalyzer lexicalAnalyzer2 = new LexicalAnalyzer("p2.txt");
        LexicalAnalyzer lexicalAnalyzer3 = new LexicalAnalyzer("p3.txt");
        LexicalAnalyzer lexicalAnalyzer4 = new LexicalAnalyzer("p1err.txt");
    }
}
