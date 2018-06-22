/*
Rasul Silva
ATMsimulation with encrypted write to text file:
This class provides static functions for reading from and writing to text files.
*/
package atmsim;
import java.io.*;
import java.util.Scanner;

public class Fileio {

    public static String[] tokenizeline(String linein) {
        String[] tokens = linein.split("\\$");
        return tokens;
    }

    public static void writetextfiledataarray(User[] userarray, String filename) {
        int index = 0;
        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);

            while (userarray[index] != null) {
                User usertemp = userarray[index];
                bw.write(usertemp.get_name() + "$" + usertemp.get_id() + "$" + usertemp.get_pin() + "$" + usertemp.get_balance() + "$");
                bw.newLine();
                index++;
            }

            bw.close();
        } catch (IOException ex) {
            System.out.println(
                    "Error writing to file '"
                    + filename + "'");
        }

    }

    ;
    
    public static User[] readtextfile(String filename, int arraysize) {
        User[] userarray = new User[arraysize];
        String line = null;
        String[] tokens = null;
        int arrayindex = 0;

        try {
            FileReader fr = new FileReader(filename);// FileReader reads text files in the default encoding.
            BufferedReader br = new BufferedReader(fr);// Always wrap FileReader in BufferedReader.

            while ((line = br.readLine()) != null) {
                tokens = tokenizeline(line);
                if (tokens.length != 1) {
                    userarray[arrayindex] = new User(tokens[0], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
                    arrayindex++;
                }
            }

            br.close(); // Always close files.
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '"
                    + filename + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                    + filename + "'");
        }

        return userarray;
    }
;
}
