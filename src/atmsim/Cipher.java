/*
Rasul Silva
ATMsimulation with encrypted write to text file:
This is a very rudimentary and honestly unsafe encryption sttrategy. It can be
easily decoded, but for the purpose of a small project it will suffice. Ceasar's
cipher simpy takes each char in a given text and replaces it with a new char 
that is if distance (key) away in the alphabet. The implementation here is quite
simple.


note:
I will leave the encrypt and decrypt functions separate in case I want to make 
them more complex
*/
package atmsim;
import java.io.*;

public class Cipher {

    private int key1;
    private int key2;

    public Cipher(int key1in) {
        key1 = key1in;
    }

    public void ceasarcipherencrypt(InputStream instr, OutputStream outstr) throws IOException {
        //read in byte by byte and replace each byte with its "shifted" counterpart.
        int flag = 0;
        while (flag == 0) {
            int next = instr.read();
            if (next == -1) {
                flag = 1;
            } else {
                byte nextbyte = (byte) next;
                byte modbyte = (byte) (nextbyte + key1);
                outstr.write(modbyte);
            }
        }
    }
    
    public void ceasarcipherdecrypt(InputStream instr, OutputStream outstr) throws IOException {
        //same thing except we subtract the key value instead of add.
        int flag = 0;
        while (flag == 0) {
            int next = instr.read();
            if (next == -1) {
                flag = 1;
            } else {
                byte nextbyte = (byte) next;
                byte modbyte = (byte) (nextbyte - key1);
                outstr.write(modbyte);
            }
        }
    }
;
}
