package NPC;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ReadFilesTest {
    // get user to chose if they want a random line or if they want to chose their line

    public static void main(String[] args) throws IOException {

        readFile();
        System.out.println("Would you like to read another line?");

        boolean loop = true;

        while (loop) {

            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();

            if (userInput.equalsIgnoreCase("YES") || userInput.equalsIgnoreCase("Y")) {
                readFile();
                System.out.println("Would you like to read another line?");
            } else {
                System.out.println("Finished reading files");
                loop = false;
            }

        }

    }

    private static void readFile() throws IOException {

        System.out.println("Do you want to chose the line or do you want a random line.");
        System.out.println("Enter 'chose' or 'random': ");

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        if (userInput.equalsIgnoreCase("chose") || userInput.equalsIgnoreCase("c")) {
            choseLine();
        } else if (userInput.equalsIgnoreCase("random") || userInput.equalsIgnoreCase("r")) {
            randomLine();
        } else {
            System.out.println("You did not enter 'chose' or 'random'");
            readFile();
        }

    }

    private static void getLine(int lineNumber) throws IOException {

        System.out.println("The text that is on line " + lineNumber + " is:");

        String specificLine = Files.readAllLines(Path.of(fileName)).get(lineNumber - 1);

        System.out.println(specificLine);

    }

    private static void choseLine() throws IOException {

        System.out.println("Enter the line that you want the program to read: ");
        Scanner input = new Scanner(System.in);
        int lineNumber = input.nextInt();

        int fileSize = fileSize();

        if (lineNumber <= 0) {
            System.out.println("The line number needs to be between 1 and " + fileSize);
            choseLine();
        } else if (lineNumber > fileSize) {
            System.out.println("The line number needs to be between 1 and " + fileSize);
            choseLine();
        }
        // Just a test for now, i need to figure out how to get the length (ie number of lines) of a file

        getLine(lineNumber);

    }

    private static void randomLine() throws IOException {

        int numLines = fileSize();

        int lineNumber = (int) (Math.random() * numLines) - 1;

        getLine(lineNumber);
    }

    private static int fileSize() throws FileNotFoundException {
        // look at the file and return how many lines it has

        File newFile = new File(fileName);
        Scanner scan = new Scanner(newFile);

        int lineCounter = 0;

        while (scan.hasNext()) {
            lineCounter++;
            scan.next();
        }

        return lineCounter;
    }

    private final static String fileName = "/Users/Callum/IdeaProjects/AlphabetNumbers.txt";

}
