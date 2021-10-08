package com.pravarcoding;

import java.util.*;


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

//class Slots {
//    // CONSTRUCTOR
//    Slots(){
//
//    }
//    // METHOD TO ADD SLOTS BY THE HOSPITAL
//    public static void add_slot_for_vaccination (int hospitalID, int no_of_slots, int day_number, int quantity) {
//
//    }
//
//}
class Vaccine {
    String  name;
    private int number_of_doses;
    private int gap_between_doses;

    // CONSTRUCTOR FOR VACCINE CLASS
    Vaccine(String name, int number_of_doses, int gap_between_doses) {
        this.name = name;
        this.number_of_doses = number_of_doses;
        this.gap_between_doses = gap_between_doses;
    }
}


class Citizen {
    private String name;
    private int age;
    private int uniqueID;
    String vaccination_status;
    

    // CONSTRUCTOR FOR CITIZEN CLASS : 
    Citizen(String name, int age, int uniqueID) {
        vaccination_status = "REGISTERED";
        this.name = name;
        this.age = age;
        this.uniqueID = uniqueID;
    }
}
class Hospital {
    private String name;
    private int pincode;
    int uniqueID;
    
    
    // CONSTRUCTOR FOR HOSPITAL CLASS : 
    Hospital(String name, int pincode) {
        this.name = name;
        this.pincode = pincode;
        this.uniqueID = (int)(Math.random() * (999999 - 100000) + 100000);

    }
}


public class Main {
    public static void main(String[] args) {
        // ARRAYS CREATED FOR STORING INFORMATION :
        ArrayList<Citizen> citizenArrayList = new ArrayList<>();
        ArrayList<Hospital> hospitalArrayList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> details_of_slot_registration_of_hospitals = new ArrayList<>();
        ArrayList<Vaccine> types_of_vaccines = new ArrayList<>();
        ArrayList<String> vaccine_names = new ArrayList<>();

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
            else if (menu_index == 1) {
                System.out.print("Vaccine Name: ");
                String name = sc.next();

                System.out.print("Number of Doses: ");
                int number_of_doses = sc.nextInt();

                System.out.print("Gap between Doses: ");
                int gap_between_doses = sc.nextInt();

                Vaccine vaccine = new Vaccine(name, number_of_doses, gap_between_doses);
                System.out.println("Vaccine name: " + name + " Number of Doses: " + number_of_doses + " Gap betweeen Doses: " + gap_between_doses);
                types_of_vaccines.add(vaccine);
                m.showMenu();
            }
            // REGISTER HOSPITAL:
            else if (menu_index == 2) {
                System.out.print("Hospital Name: ");
                String name = sc.next();
                System.out.print("PinCode: ");
                int pincode = sc.nextInt();

                Hospital hospital = new Hospital(name, pincode);
                hospitalArrayList.add(hospital);
                System.out.println("Hospital name: " + name + " PinCode: " + pincode + " Unique ID: " + hospital.uniqueID);

            }
            // REGISTER CITIZEN :
            else if (menu_index == 3) {
                System.out.print("Citizen Name: ");
                String name = sc.next();
                System.out.print("Age: ");
                int age = sc.nextInt();
                System.out.print("Unique ID: ");
                int uniqueID = sc.nextInt();

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
            // REGISTERED HOSPITALS ADDING SLOTS FOR VACCINATIONs
            // LOGIC BEHIND THIS : we used combined_parameters_array to combine details and appended it to the details_of_slot_registration_of_hospitals.
            else if (menu_index == 4) {
                System.out.print("Enter Hospital ID: ");
                int hospitalID = sc.nextInt();


                System.out.print("Enter number of slots to be added: ");
                int no_of_slots = sc.nextInt();

                for (int i = 0; i < no_of_slots; i ++) {
                    ArrayList<Integer> combined_parameters_array = new ArrayList<>();
                    combined_parameters_array.add(hospitalID);
                    System.out.print("Enter day number: ");
                    int day_number = sc.nextInt();
                    combined_parameters_array.add(day_number);

                    System.out.print("Enter Quantity: ");
                    int quantity = sc.nextInt();
                    combined_parameters_array.add(quantity);

                    System.out.println("Select vaccine: ");
                    for (int j = 0; j < types_of_vaccines.size(); j++) {
                        System.out.println(i + ". " + types_of_vaccines.get(0).name);
                    }
                    int vaccine = sc.nextInt();

                    combined_parameters_array.add(vaccine);
                    System.out.println("Slot added by Hospital " + hospitalID + " for Day: " + day_number + ", Available Quantity: " + quantity + " of Vaccine " + types_of_vaccines.get(vaccine).name);
                    details_of_slot_registration_of_hospitals.add(combined_parameters_array);

                } m.showMenu();
            }
            else if (menu_index == 6) {
                System.out.print("Enter Hospital ID: ");
                int hospital_id = sc.nextInt();
                for (int i = 0; i < details_of_slot_registration_of_hospitals.size(); i++) {
                    if(details_of_slot_registration_of_hospitals.get(i).get(0) == hospital_id) {
//                        if ( == 0) {
                            System.out.println("Day: " + details_of_slot_registration_of_hospitals.get(i).get(1) + " Vaccine: " + types_of_vaccines.get(details_of_slot_registration_of_hospitals.get(i).get(3)).name + " Available Qty: " + details_of_slot_registration_of_hospitals.get(i).get(2));
//                        }
//                        else if (details_of_slot_registration_of_hospitals.get(i).get(3) == 1) {
//                            System.out.println("Day: " + details_of_slot_registration_of_hospitals.get(i).get(1) + " Vaccine: covax Available Qty: " + details_of_slot_registration_of_hospitals.get(i).get(2));

//                        }
                    }
                }
            }
        }
    }
}
