package proj02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**************************************************************
 * Menu
 * Prompts a menu selection and which gets user input
 *************************************************************/
class Menu
{
    Scanner scan = new Scanner(System.in).useDelimiter("\n");
    String firstName;
    String lastName;
    String street;
    String city;
    String state;
    int zip;
    String phone;
    String email;

    /**
     * Prompts user input of first name and stores the input
     * @return first name
     */
    public String prompt_FirstName()
    {
        System.out.println("First Name:");

        //if scanner does not contain letter, return error message and start over
        while (!scan.hasNext("[A-Za-z]+"))
        {
            System.out.println("Wrong input!\n" + "First Name: ");
            scan.next();
        }

        firstName = scan.next();
        return firstName;
    }

    /**
     * Prompts user input of last name and stores the input
     * @return last name
     */
    public String prompt_LastName() {
        System.out.println("Last Name:");

        //if scanner does not contain letter, return error message and start over
        while (!scan.hasNext("[A-Za-z]+"))
        {
            System.out.println("Wrong input!\n" + "Last Name: ");
            scan.next();
        }

        lastName = scan.next();
        return lastName;
    }

    /**
     * Prompts user input of street and stores the input
     * @return street
     */
    public String prompt_Street()
    {
        System.out.println("Street:");

        //if scanner contains numerical value, return error message and start over
        while (scan.hasNextInt())
        {
            System.out.println("Wrong input!\n" + "Street: ");
            scan.next();
        }
        street = scan.next();
        return street;
    }

    /**
     * Prompts user input of city and stores the input
     * @return city
     */
    public String prompt_City() {

        System.out.println("City: ");

        //if scanner contains numerical value, return error message and start over
        while (scan.hasNextInt())
        {
            System.out.println("Wrong input!\n" + "City: ");
            scan.next();
        }

        city = scan.next();
        return city;
    }

    /**
     * Prompts user input of state and stores the input
     * @return state
     */
        public String prompt_State() {
        System.out.println("State: ");

        //if scanner contains numerical value, return error message and start over
        while (scan.hasNextInt())
        {
            System.out.println("Wrong input!\n" + "State: ");
            scan.next();
        }
        state = scan.next();
        return state;
    }

    /**
     * Prompts user input of zip and stores the input
     * @return zip
     */
    public int prompt_Zip() {
        System.out.println("Zip:");

        //if scanner does not contain numerical value, return error message and start over
        while(!scan.hasNextInt())
        {
            System.out.println("Wrong input!\n" + "Zip: ");
            scan.next();
        }
        zip = Integer.parseInt(scan.next());
        return zip;
    }

    /**
     * Prompts user input of phone number and stores the input
     * @return phone number
     */
    public String prompt_Telephone()
    {
        System.out.println("Phone Number(xxx-xxx-xxxx):");

        //if scanner does not contain letter, return error message and start over
        while(scan.hasNext("[A-Za-z]+"))
       {
           System.out.println("Wrong input!\n" + "Phone Number(xxx-xxx-xxxx):");
            scan.next();
       }
        phone = scan.next();
        return phone;
    }

    /**
     * Prompts user input of email and stores the input
     * @return email
     */
    public String prompt_Email()
    {
        System.out.println("Email:");

        //if scanner does not contain letter, return error message and start over
        while(scan.hasNextInt())
        {
            System.out.println("Wrong input!\n" + "Email:");
            scan.next();
        }
        email = scan.next();
        return email;
    }

    public AddressEntry setPrompt()
    {
        prompt_FirstName();
        prompt_LastName();
        prompt_Street();
        prompt_City();
        prompt_State();
        prompt_Zip();
        prompt_Telephone();
        prompt_Email();
        AddressEntry list = new AddressEntry(firstName, lastName, street, city, state, zip, phone, email);
        return list;
    }


    /**
     * Displays menu for user selection and directs to particular methods by the selection
     * @throws IOException
     */

    public void menu(AddressBook ab) throws IOException{
        Character minInput = 'a';
        Character maxInput = 'f';

        //prints out menu prompt unless user does not want to
        while(true) {
            System.out.println("***********************************");
            System.out.println("               Menu                ");
            System.out.println("a. Getting address from file");
            System.out.println("b. Add address");
            System.out.println("c. Remove address");
            System.out.println("d. Find address");
            System.out.println("e. Listing address\n");
            System.out.println("f. Quit");
            System.out.println("***********************************");

            //avoids case sensitivity
            Character info = scan.next().toLowerCase().charAt(0);

            //does different task by user selection
            switch (info)
            {
                // reads file from the directory that user provides
                case 'a':
                    System.out.println("Enter directory of your file: ");
                    System.out.println("FYI, no quotation mark or extension needed");
                    String fileName = scan.next() + ".txt";

                    File file = new File(fileName);
                    ab.readFromFile(file);
                    break;

                // adds AddressEntry to the add method in AddressBook
                case 'b':
                    ab.add(setPrompt());
                    break;

                // gets last name from user and calls remove method from AddressBook
                case 'c':
                    System.out.println("Enter last name you want to remove");
                    System.out.println("You don't have to enter whole last name");
                    String removeName = scan.next();
                    ab.remove(removeName + ".*");
                    break;

                // gets last name from user and calls find method from AddressBook
                case 'd':
                    System.out.println("Enter last name you want to find");
                    System.out.println("You don't have to enter whole last name");
                    String lastName = scan.next();
                    ab.find(lastName + ".*");
                    break;

                // calls list method from AddressBook
                case 'e':
                    ab.list();
                    break;

                // exits the program
                case 'f':
                    System.out.println("Exiting");
                    System.exit(0);

                // if user select a value besides a to f, error message will print out
                default:
                    System.out.println("Please select between a through f");
                    break;
            }

        }

    }
}