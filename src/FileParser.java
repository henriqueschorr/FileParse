import java.io.*;
import java.util.*;

public class FileParser {
    public static void main(String [] args) {

        // The name of the file to open.
        String fileName = "SentiLex-flex-PT01.txt";
        String fileCreateName = "lexicon.txt";

        String[] words = new String[2];
        String[] wordsAssist = new String[2];
        String[] attributes = new String[2];
        String[] attributesAssist = new String[2];
        String polarity = null;

        ArrayList<String> allWords = new ArrayList<>();

        // This will reference one line at a time
        String line = null;

        try {
//            File file = new File(fileCreateName);
//            if (!file.exists()) {
//                file.createNewFile();
//            }

            // Assume default encoding.
            FileWriter fileWriter =
                    new FileWriter(fileCreateName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            bufferedWriter.write("var lexicon = {");
            bufferedWriter.newLine();

            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                words = line.split(",");
                wordsAssist = words[1].split("\\.");
                words[1] = wordsAssist[0];

                attributes = wordsAssist[1].split("POL=");
                attributesAssist = attributes[1].split(";");
                polarity = attributesAssist[0];

                for (int i=0; i<words.length; i++) {
                    String[] wordComplex = new String[10];
                    wordComplex = words[i].split(" ");
                    if (!allWords.contains(words[i]) && wordComplex.length == 1) {
                        allWords.add(words[i]);
                        bufferedWriter.write("  \"" + words[i] + "\": " + polarity + ",");
                        bufferedWriter.newLine();
                    }
//                    System.out.println(allWords.size());
//                    System.out.println("Words in the document: " + words[i]);
                }

            }

            bufferedWriter.write("};");

//            for(int i=0; i<allWords.size(); i++){
//                System.out.println("words not duplicated: " + allWords.get(i));
//            }
//            System.out.println(polarity);

            // Always close files.
            bufferedReader.close();
            bufferedWriter.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
}
