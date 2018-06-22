/*
Rasul Silva
ATMsimulation with encrypted write to text file:
Here we present a simple menu system to allow user to create account,
find account, or remove an account. Utility functions are included and
commented. 
High level Program flow is as follows: 
1. decrypt text file using Cipher object. 
2. read decrypted file into array of user objects.
3. modify array through logic provided in main method
4. upon exit encrypt array again and store in text file.

Encrypted file: crypt.txt
Decrypted file: ATMsim.txt

In this program the intermediary decrypted file is left visible for
user to view format. However, this is obviously impractical for real
applications.
*/
package atmsim;
import java.io.*;
import java.util.Scanner;

public class ATMsim {

    public static int searchforname(User[] array, String name) {
        //look through userarray for object with name, return index if found and -1 if not
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {//because get_name() breaks on null object
                if (array[i].get_name().equals(name)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static User[] insertintoarray(User userobject, User[] userarray) {
        //insert object into user array at first available spot, return userarray after insertion
        for (int i = 0; i < userarray.length; i++) {
            if (userarray[i] == null) {//if spot is empty then insert object
                userarray[i] = userobject;
                return userarray;
            }
        }
        System.out.println("memory is full");
        return null;
    }

    public static User[] removefromarray(String name, User[] userarray) {
        //remove item from array, upon removal shift all following objects down to save space
        int retval = searchforname(userarray, name);//retval is index of object if found, -1 otherwise
        if (retval == -1) {
            System.out.println("item does not exist in memory");
        } else {
            userarray[retval] = null;//"remove" item by making it null
            for (int i = retval + 1; i < userarray.length; i++) {
                userarray[i - 1] = userarray[i];//shift all items back
            }
            userarray[userarray.length - 1] = null;//manually nullify last item
        }

        return userarray;
    }

    public static void printarray(User[] userarray) {//print array contents by name
        System.out.print("array contents: (");
        for (int i = 0; i < userarray.length; i++) {
            if (userarray[i] != null) {
                System.out.print(userarray[i].get_name() + ",");
            } else {
                System.out.print("null,");
            }
        }
        System.out.print(") \n");
    }

    public static void main(String[] args) {
        //settings
        int key1 = 3;//key for ceasar cipher
        int arraysize = 7;

        int exitflag = 0;//flags and intermediary values
        int retval;
        int choice;
        String nametemp = null;
        int idtemp = 0;
        int pintemp = 0;
        int idpass;
        int pinpass;

        User[] userarray = new User[arraysize];

        Scanner in = new Scanner(System.in);

        try {
            InputStream instr = new FileInputStream("crypt.txt");
            OutputStream outstr = new FileOutputStream("ATMsim.txt");

            Cipher cipherobject = new Cipher(key1);//create cipher object with key value from which to encrypt and decrypt
            cipherobject.ceasarcipherdecrypt(instr, outstr);//decrypt file

            instr.close();
            outstr.close();
        } catch (IOException e) {
            System.out.println("Error is " + e);
        }

        userarray = Fileio.readtextfile("ATMsim.txt", arraysize);

        while (exitflag == 0) {

            //printarray(userarray);
            System.out.println("choose options:");//main menu
            System.out.println("1. create account");
            System.out.println("2. find account");
            System.out.println("3. exit");
            String consolein = in.nextLine();

            switch (consolein) {
                case "1"://create account sub-menu
                    System.out.println("CREATE ACCOUNT:");
                    System.out.print("name: ");
                    nametemp = in.nextLine();
                    if (searchforname(userarray, nametemp) != -1) {
                        System.out.println("name is already in system!");
                        break;
                    }

                    try {
                        System.out.print("id: ");
                        idtemp = Integer.parseInt(in.nextLine());
                        System.out.print("pin: ");
                        pintemp = Integer.parseInt(in.nextLine());
                        User usertemp = new User(nametemp, idtemp, pintemp, 0);
                        userarray = insertintoarray(usertemp, userarray);
                        System.out.println("item has been inserted with the following fields:");
                        System.out.println("name: " + nametemp);
                        System.out.println("id  : " + idtemp);
                        System.out.println("pin : " + pintemp);
                        System.out.println("balance:" + 0);
                    } catch (NumberFormatException e) {//number format exception thrown by parseInt function
                        System.out.println("please input an integer for both the id and pin");
                    }

                    break;
                case "2"://find account
                    System.out.println("FIND ACCOUNT:");
                    System.out.print("name: ");
                    nametemp = in.nextLine();

                    retval = searchforname(userarray, nametemp);
                    //System.out.println("retval:" + retval);

                    if (retval != -1) {
                        try {

                            System.out.println("Enter id and pin:");
                            System.out.print("id: ");
                            idpass = Integer.parseInt(in.nextLine());
                            System.out.print("pin:");
                            pinpass = Integer.parseInt(in.nextLine());

                            if ((pinpass != userarray[retval].get_pin()) | (idpass != userarray[retval].get_id())) {//verify correct password and id
                                System.out.println("incorrect id or password!");
                                break;
                            }

                            System.out.println("what would you like to do?");
                            System.out.println("1. deposit");
                            System.out.println("2. withdraw");
                            System.out.println("3. remove ");
                            choice = Integer.parseInt(in.nextLine());
                            switch (choice) {
                                case 1:
                                    System.out.print("how much would you like to deposit?: ");
                                    choice = Integer.parseInt(in.nextLine());
                                    userarray[retval].set_balance(choice);
                                    break;
                                case 2:
                                    System.out.println("how much would you like to withdraw?: ");
                                    choice = Integer.parseInt(in.nextLine());
                                    userarray[retval].set_balance(-choice);
                                    break;
                                case 3:
                                    System.out.println("are you sure you would like to remove account?:");
                                    System.out.println("1. yes       2. no");
                                    choice = Integer.parseInt(in.nextLine());
                                    switch (choice) {
                                        case 1:
                                            userarray = removefromarray(nametemp, userarray);
                                            break;
                                        case 2:
                                            System.out.println("removal aborted");
                                            break;
                                        default:
                                            System.out.println("invalid input");
                                            break;
                                    }
                                    break;
                                default:
                                    System.out.println("invalid input");
                                    break;
                            }
                            retval = 0;
                        } catch (NumberFormatException e) {
                            System.out.println("please enter a integer for all id, pin, and balance inputs");
                        }
                    }////
                    else if (retval == -1) {
                        System.out.println("name was not found in system");
                    }

                    break;
                case "3":
                    Fileio.writetextfiledataarray(userarray, "ATMsim.txt");
                    try {
                        InputStream instr = new FileInputStream("ATMsim.txt");
                        OutputStream outstr = new FileOutputStream("crypt.txt");
                        Cipher cipherobject = new Cipher(key1);
                        cipherobject.ceasarcipherencrypt(instr, outstr);

                        instr.close();
                        outstr.close();
                    } catch (IOException e) {
                        System.out.println("Error is " + e);
                    }
                    exitflag = 1;
                    break;

                default:
                    System.out.println("invalid input: please enter either 1, 2 or 3");

            }

        }

    }

}
