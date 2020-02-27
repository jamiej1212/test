package proj02;

import java.io.File;
import java.util.*;
import java.io.*;

/**************************************************************
 * AddressBook
 * Using objects gets from AddressEntry and Menu class
 * to invoke various methods
 *
 *************************************************************/
public class AddressBook
{
    ArrayList<AddressEntry> table = new ArrayList<>();
    AddressEntry user;
    Scanner scan = new Scanner(System.in);
    Menu menu = new Menu();


    /**
     * Gets information from AddressEntry and adds them to the list.
     *
     * @param entry information that we are going to add in the collection
     */
    public void add(AddressEntry entry)
    {
        // add information into contact list
        table.add(entry);
        System.out.println(entry.toString());
        System.out.println("\nAdded Successfully!");

    }

    /**
     * Iterates through the collection.
     * Sorts the collection using sort and compare methods
     * Prints the collection
     */
    public void list()
    {
        // sorts the list alphabetically
        Collections.sort(table, new Comparator<AddressEntry>()
        {
            @Override
            public int compare(AddressEntry first, AddressEntry second)
            {
                if (first.getLastName().equals(second.getLastName()))
                    return first.getFirstName().compareTo(second.getFirstName());
                else
                    return first.getLastName().compareTo(second.getLastName());
            }
        });

        // prints out contents in the list
        for (int i = 0; i < table.size(); i++)
        {
            System.out.println(i + 1 + ".\n" + table.get(i).toString());
        }

    }

    /**
     * Reads and prints information from a file
     * Stores information into the collection
     *
     * @param file file that this method gets information from
     * @throws IOException
     */
    public void readFromFile(File file) throws IOException
    {
        Scanner scanFile = new Scanner(new FileReader(file));
        ArrayList<AddressEntry> read = new ArrayList<>();

        // reads information from file and stores in variables
        while (scanFile.hasNextLine())
        {
            String firstName = scanFile.nextLine();
            String lastName = scanFile.nextLine();
            String street = scanFile.nextLine();
            String city = scanFile.nextLine();
            String state = scanFile.nextLine();
            int zip = Integer.parseInt(scanFile.nextLine());
            String phone = scanFile.nextLine();
            String email = scanFile.nextLine();

            user = new AddressEntry(firstName, lastName, street, city, state, zip, phone, email);
            table.add(user);
            read.add(user);
        }


        // prints out how many addresses are loaded from file and exists in the list
        System.out.println(read.size() + " Address(es) loaded");
        System.out.println(table.size() + " Address(es) currently in the list");
    }

    /**
     * Finds and prints a list of contacts that user wants to find
     *
     * @param startOf_lastName full or a part of (from beginning) last name
     *                         that user entered
     */
    public void find(String startOf_lastName)
    {
        ArrayList<AddressEntry> match = new ArrayList<>();

        // compares user input to the last names in the list
        for (int i = 0; i < table.size(); i++)
        {
            if (table.get(i).getLastName().toLowerCase().matches(startOf_lastName.toLowerCase()))
            {
                // if match founds, store them in a list
                match.add(table.get(i));
            }
        }

        System.out.println(match.size() + " contact(s) found");

        // prints what founded
        for (int i = 0; i < match.size(); i++)
        {
            System.out.println(i + "\n" + match.get(i).toString());
        }
    }

    /**
     * Prints a list of information that matches with user input
     * Removes an object in collection which is chosen by user
     *
     * @param startOf_lastName full or a part of (from beginning) last name
     *                         that user entered
     */
    public void remove(String startOf_lastName)
    {
        // compares user input to the last names in the list
        ArrayList<AddressEntry> match = new ArrayList<>();
        for (int i = 0; i < table.size(); i++)
        {
            if (table.get(i).getLastName().toLowerCase().matches(startOf_lastName.toLowerCase()))
            {
                // if match is found, store them in a list
                match.add(table.get(i));
            }
        }

        // prints out list
        for (int i = 0; i < match.size(); i++)
        {
            System.out.println(i + "\n" + match.get(i).toString());
        }

        System.out.println("Please select a number");
        int select = Integer.parseInt(scan.next());

        // if user input matches with the list index, remove the content
        for (int i = 0; i < match.size(); i++)
        {
            if (select == i)
            {
                for (AddressEntry element : table)
                {
                    if (match.get(select).getPhone().equals(element.getPhone()))
                    {
                        System.out.println("Are you sure? (Y/N)");
                        String answer = scan.next();

                        // asks for user permission to delete the content
                        if (answer.toLowerCase().equals("y"))
                        {
                            table.remove(element);
                            break;
                        } else
                            System.out.println("Canceled");
                    }
                }
            }
        }
        System.out.println("Removed Successfully!");


    }
}