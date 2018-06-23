Rasul Silva

### Description: 
This is a simple ATM simulation that stores data in an encrypted text file on disk.

### Classes:
#### User:
This is the user class which creates storage objects for the name, id, pin, and balance
This includes setters, getters, and a utility for printing out object info.
#### ATMsim:
Here we present a simple menu system to allow user to create account,
find account, or remove an account. Utility functions are included and
commented. 
#### Fileio:
This class provides static functions for reading from and writing to text files.
#### Cipher:
This is a very rudimentary and honestly unsafe encryption strategy. It can be
easily decoded, but for the purpose of a small project it will suffice. Ceasar's
cipher simply takes each char in a given text and replaces it with a new char 
that is of distance (key) away in the alphabet. The implementation here is quite
simple.


### Program Flow:
1. Decrypt text file from memory
2. Extract user information and occupy object array
3. Interact with array of user objects through menu choices: add accounts, remove accounts, make deposits and withdrawals, etc.
4. exit program and write state back to disk.


