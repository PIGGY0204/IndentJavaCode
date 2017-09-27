import java.io.*;
import java.util.*;

public class IndentJavaCode {
    private static final int NUMBER_OF_BLANK = 4;

    public static void main(String[] args) {
        String inFilePathname = null;
        if (args.length == 1) {
            inFilePathname = args[0];
            File inFile = new File(inFilePathname);
            File outFile = createOutFile(inFile);
            indentCode(inFile, outFile);
        } else if (args.length == 2) {
            indentCode(new File(args[0]), new File(args[1]));
        } else if (args.length > 2)
            System.out.println("please enter effective option");
        else if (args.length == 0) {
            Scanner input = new Scanner(System.in);
            System.out.print("please enter the source file path: ");
            inFilePathname = input.nextLine().trim();
            File inFile = new File(inFilePathname);
            File outFile = createOutFile(inFile);
            indentCode(inFile, outFile);
        }
    }

    public static void indentCode(File inFile, File outFile) {
        int number = 0;
        int tempIndent = 0;

        try {
            try (
                BufferedReader input = new BufferedReader(new FileReader(inFile));
                BufferedWriter output = new BufferedWriter(new FileWriter(outFile));
            ) {
                String line = input.readLine().trim();
                while (line != null) {
                    if (line.length() > 0) {
                        char firstChar = line.charAt(0);
                        if (firstChar == '}')
                            number--;
                    }

                    for (int i = 0; i < NUMBER_OF_BLANK * (number + tempIndent); i++)
                        output.write(" ");
                    output.write(line);
                    output.newLine();
                    tempIndent = 0;

                    if (line.length() > 0) {
                        char end = line.charAt(line.length() - 1);
                        if (end == '{')
                            number++;
                        else if (end != ';' && end != '}')
                            tempIndent = 1;
                    }

                    line = input.readLine().trim();
                }
                output.flush();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static File createOutFile(File inFile) {
        String outFilePathname = null;

        if (inFile.getParent() != null)
            outFilePathname = inFile.getParent() + "/indent_" + inFile.getName();
        else
            outFilePathname = "/indent_" + inFile.getName();

        File outFile = new File(outFilePathname);
        try {
            if (!outFile.exists())
                outFile.createNewFile();
        } catch(IOException ex) {
            System.out.println(ex);
        }

        return outFile;
    }
}
