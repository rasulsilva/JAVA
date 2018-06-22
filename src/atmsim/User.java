/*
Rasul Silva
ATMsimulation with encrypted write to text file:
This is the user class which creates storage objects for the name, id, pin, and balance
This includes setters, getters, and a utility for printing out object info.
*/
package atmsim;

public class User {

    private String name;
    private int id;
    private int pin;
    private int balance;

    User() {
        this.name = "default";
        this.id = 0000;
        this.pin = 0000;
        this.balance = 0;
    }

    User(String name, int id, int pin, int balance) {
        this.name = name;
        this.id = id;
        this.pin = pin;
        this.balance = balance;
    }

    public String get_name() {
        return name;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public int get_id() {
        return id;
    }

    public void set_id(int id) {
        this.id = id;
    }

    public int get_pin() {
        return pin;
    }

    public void set_pin(int pin) {
        this.pin = pin;
    }

    public int get_balance() {
        return balance;
    }

    public void set_balance(int change) {
        this.balance = balance + change;
    }

    public void print_account_info() {
        System.out.println("name      :" + name);
        System.out.println("id        :" + id);
        System.out.println("pin       :" + pin);
        System.out.println("balance   :" + balance);
    }

}
