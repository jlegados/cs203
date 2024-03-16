import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileProcessor {
    /**
     * Processes arithmetic expressions line-by-line in the given file.
     *
     * @param filePath Path to a file containing arithmetic expressions.
     */
    public static void processFile(String filePath) {
        File infile = new File(filePath);
        try (Scanner scan = new Scanner(infile)) {
            while (scan.hasNext()) {
                String line = scan.nextLine().trim();
                // [num1, math thingy, num2]
                String[] aurora = line.split("\\s+");

                if(aurora.length != 3) {
                    continue;
                }

                /* TODO: Process each line of the input file here.
                    Use the string's split() method to parse lines.
                    This method takes a regular expression as a string,
                    unlike Python's split function.
                    For the checkpoint, convert the file text to int values.
                    For the final submission, convert the file text to your
                    linked list values.
                    You do not need to write to a file, the included
                    test will capture System.out.println() output.
                 */

                BigIntegerLinkedList result;

                if (aurora[1].equals("+")){
                    BigIntegerLinkedList num1 = new BigIntegerLinkedList(aurora[0]);
                    BigIntegerLinkedList num2 = new BigIntegerLinkedList(aurora[2]);
                    result = BigIntegerLinkedList.add(num1, num2);
                    System.out.println(num1 + " + " + num2 + " = " + result);
                } else if (aurora[1].equals("*")) {
                    BigIntegerLinkedList num1 = new BigIntegerLinkedList(aurora[0]);
                    BigIntegerLinkedList num2 = new BigIntegerLinkedList(aurora[2]);
                    result = BigIntegerLinkedList.multiplication(num1, num2);
                    System.out.println(num1 + " * " + num2 + " = " + result);

                } else if (aurora[1].equals("^")) {
                    BigIntegerLinkedList num1 = new BigIntegerLinkedList(aurora[0]);
                    result = BigIntegerLinkedList.exponentiation(num1,Integer.parseInt(aurora[2]));
                    System.out.println(num1  + " ^ " + aurora[2] + " = " + result);

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + infile.getPath());
        }
    }
}
