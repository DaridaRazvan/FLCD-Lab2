package Domain;

import Domain.SymbolTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class LexicalAnalyzer {
    String file;

    SymbolTable symbolTable = new SymbolTable();
    ProgramInternalForm programInternalForm = new ProgramInternalForm();

    boolean isLexicallyCorrect = true;
    int errorLine = 0;
    List<String> invalidTokens = new ArrayList<>();

    public final String DELIMITERS = "()[]{}:; ";

    public LexicalAnalyzer(String file) {
        this.file = file;
        scan(file);
        printToFile(file);
    }

    public void scan(String file){
        this.isLexicallyCorrect = true;
        this.errorLine = 0;
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                if(this.isLexicallyCorrect)
                    this.errorLine += 1;
                String line = myReader.nextLine();
                System.out.println(line);
                StringTokenizer stringTokenizer = new StringTokenizer(line,DELIMITERS,true);
                while(stringTokenizer.hasMoreTokens()){
                    //System.out.println("Here:  " + stringTokenizer.nextToken());
                    String token = stringTokenizer.nextToken();
                    if(programInternalForm.isReservedWord(token) ||
                            programInternalForm.isSeparator(token) ||
                            programInternalForm.isOperator(token)){
                        programInternalForm.add(programInternalForm.getCode(token), "-1");
                    }
                    else if(programInternalForm.isIdentifier(token)){
                        symbolTable.insert(token);
                        programInternalForm.add(0,token);
                    }
                    else if(programInternalForm.isConstant(token)){
                        symbolTable.insert(token);
                        programInternalForm.add(1,token);
                    }
                    else{
                        invalidTokens.add(token);
                        this.isLexicallyCorrect = false;
                    }

                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    void printToFile(String file){
        try {
            FileWriter myWriter = new FileWriter("Pif_"+file);
            myWriter.write(this.toString());
            myWriter.close();

            FileWriter myWriter2 = new FileWriter("St_"+file);
            myWriter2.write(symbolTable.toString());
            myWriter2.close();

            if(!isLexicallyCorrect){
                FileWriter myWriter3 = new FileWriter("error_"+file);
                myWriter3.write("Lexical error: " + errorLine + '\n');
                for(String error: invalidTokens){
                    myWriter3.write(error + " ");
                }
                myWriter3.close();
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "LexicalAnalyzer{\n" +
                "programInternalForm=\n" + programInternalForm +
                "\n}";
    }
}
