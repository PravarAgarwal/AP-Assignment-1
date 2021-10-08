package com.pravarcoding;

import java.util.ArrayList;
import java.util.Scanner;

class Menu {

    public void showMenu() {
        System.out.println("Cowin Portal Initialized....");
        System.out.println("1. Add Vaccine");
        System.out.println("2. Register Hospital");
        System.out.println("3. Register Citizen");
        System.out.println("4. Add Slot for Vaccination");
        System.out.println("5. Book Slot for Vaccination");
        System.out.println("6. List all slots for a hospital");
        System.out.println("7. Check Vaccination Status");
        System.out.println("8. Exit");
        System.out.println("---------------------------------");
    }

}

class Slots {

}
class Citizen {
    String name;
    int age;
    long uniqueID;
    String vaccination_status;
    

    // CONSTRUCTOR FOR CITIZEN CLASS : 
    Citizen(String name, int age, long uniqueID) {
        vaccination_status = "REGISTERED";
        this.name = name;
        this.age = age;
        this.uniqueID = uniqueID;
    }
}
class Hospital {
    String name;
    long pincode;
    long uniqueID;
    
    
    // CONSTRUCTOR FOR HOSPITAL CLASS : 
    Hospital(String name, long pincode, long uniqueID) {
        this.name = name;
        this.pincode = pincode;
        this.uniqueID = uniqueID;

    }
}


public class Main {
    public static void main(String[] args) {
        // ARRAYS CREATED FOR STORING INFORMATION :
        ArrayList<Citizen> citizenArrayList = new ArrayList<>();

        // MENU OBJECT CREATED
        Menu m = new Menu();
        m.showMenu();

        // WHILE LOOP STARTED FOR SIMULATION
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("enter menu index value : ");

            int menu_index = sc.nextInt();

            // EXIT :
            if (menu_index == 8) {
                break;
            }
            else if (menu_index == 2) {
                System.out.print("Hospital Name: ");
                String name = sc.next();
                System.out.println();
            }
            // REGISTER CITIZEN :
            else if (menu_index == 3) {
                System.out.print("Citizen Name: ");
                String name = sc.next();
                System.out.print("Age: ");
                int age = sc.nextInt();
                System.out.print("Unique ID: ");
                long uniqueID = sc.nextLong();

                if (age > 18) {
                    Citizen citizen = new Citizen(name, age, uniqueID);
                    citizenArrayList.add(citizen);
                    System.out.println("Citizen name: " + name + " Age: " + age + " Unique ID: " + uniqueID);
                    System.out.println();
                    m.showMenu();
                }
                else {
                    System.out.println("Citizen name: " + name + " Age: " + age + " Unique ID: " + uniqueID);
                    System.out.println("Only above 18 are allowed");
                    System.out.println();
                    m.showMenu();
                }

            }
        }
    }
}
