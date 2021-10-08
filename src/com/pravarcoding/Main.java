package com.pravarcoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
class Reader1 {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }


    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                    reader.readLine() );
        }
        return tokenizer.nextToken();
    }
    static String nextLine() throws IOException {
        return reader.readLine();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}

class Slots {
    int hospital_id;
    String vaccine_name;
    int quantity;
    int day_number;
    int slot;

    // CONSTRUCTOR
    Slots(int hospital_id, String vaccine_name, int quantity, int day_number, int slot){
        this.hospital_id = hospital_id;
        this.vaccine_name = vaccine_name;
        this.quantity = quantity;
        this.day_number = day_number;
        this.slot = slot;
    }

}
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
    String name;
    private int age;
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
    int pincode;
    int uniqueID;
    
    
    // CONSTRUCTOR FOR HOSPITAL CLASS : 
    Hospital(String name, int pincode) {
        this.name = name;
        this.pincode = pincode;
        this.uniqueID = (int)(Math.random() * (999999 - 100000) + 100000);

    }
}


public class Main {
    public static void main(String[] args) throws IOException {
        // ARRAYS CREATED FOR STORING INFORMATION :
        ArrayList<Citizen> citizenArrayList = new ArrayList<>();
        ArrayList<Hospital> hospitalArrayList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> details_of_slot_registration_of_hospitals = new ArrayList<>();
        ArrayList<Vaccine> types_of_vaccines = new ArrayList<>();
        ArrayList<Slots> slotsArrayList = new ArrayList<>();

        // MENU OBJECT CREATED
        Menu m = new Menu();
        m.showMenu();

        // WHILE LOOP STARTED FOR SIMULATION
        while (true) {
            Scanner sc = new Scanner(System.in);
            Reader1.init(System.in);
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
            // REGISTERED HOSPITALS ADDING SLOTS FOR VACCINATIONs
            else if (menu_index == 4) {
                System.out.print("Enter Hospital ID: ");
                int hospitalID = sc.nextInt();


                System.out.print("Enter number of slots to be added: ");
                int no_of_slots = sc.nextInt();

                for (int i = 0; i < no_of_slots; i ++) {
                    System.out.print("Enter day number: ");
                    int day_number = sc.nextInt();

                    System.out.print("Enter Quantity: ");
                    int quantity = sc.nextInt();

                    System.out.println("Select vaccine: ");
                    for (int j = 0; j < types_of_vaccines.size(); j++) {
                        System.out.println(j + ". " + types_of_vaccines.get(j).name);
                    }
                    int vaccine = sc.nextInt();
                    Slots slot = new Slots(hospitalID, types_of_vaccines.get(vaccine).name, quantity, day_number, i);
                    slotsArrayList.add(slot);
                    System.out.println("Slot added by Hospital " + hospitalID + " for Day: " + day_number + ", Available Quantity: " + quantity + " of Vaccine " + types_of_vaccines.get(vaccine).name);
                }
                m.showMenu();
            }

            else if (menu_index == 5) {
                System.out.println("Enter patients Unique ID: ");
                long id = sc.nextLong();
                System.out.println("1. Search by area: ");
                System.out.println("2. Search by Vaccine: ");
                System.out.println("3. Exit");
                System.out.print("Enter option: ");
                int option = sc.nextInt();
                if (option == 1) {
                    System.out.print("Enter pincode: ");
                    int pincode = sc.nextInt();
                    for (int i = 0; i < hospitalArrayList.size(); i++) {
                        if (hospitalArrayList.get(i).pincode == pincode) {
                            System.out.println(hospitalArrayList.get(i).uniqueID + " " + hospitalArrayList.get(i).name);
                        }
                    }
                    System.out.println("Enter hospital ID: ");
                    int hospital_id_u_want_to_book = sc.nextInt();

                    ArrayList<String[]> fithPart = new ArrayList<>();
                    for (int i = 0; i < slotsArrayList.size(); i++) {
                        if ((slotsArrayList.get(i).hospital_id == hospital_id_u_want_to_book)) {
                            System.out.println(slotsArrayList.get(i).slot + " -> Day: " + slotsArrayList.get(i).day_number + " Available Qty: " + slotsArrayList.get(i).quantity + " Vaccine: " + slotsArrayList.get(i).vaccine_name);
                            String temp = (slotsArrayList.get(i).slot + " -> Day: " + slotsArrayList.get(i).day_number + " Available Qty: " + slotsArrayList.get(i).quantity + " Vaccine: " + slotsArrayList.get(i).vaccine_name);
                            String[] temp_arr = temp.split(" ");
                            fithPart.add(temp_arr);
                        }
                        else System.out.println("No Slots Available");
                    }
                    System.out.println("Choose Slot: ");
                    int choose_slot = sc.nextInt();
                    for (int i = 0; i < citizenArrayList.size(); i++) {
                        for (int j = 0; j < fithPart.size(); j ++) {
                            if ((citizenArrayList.get(i).uniqueID == id) && (fithPart.get(j)[0].equals(Integer.toString(choose_slot)))){
                                System.out.println(citizenArrayList.get(i).name + " vaccinated with " + fithPart.get(j)[8]);
                            }
                        }
                    }
                }
                if (option == 2) {
                    System.out.print("Enter Vaccine name: ");
                    String vaccine_name = sc.next();
                    String hos_name = null;
                    for (int i = 0; i < hospitalArrayList.size(); i++) {
                        if (slotsArrayList.get(i).vaccine_name == vaccine_name) {
                            for (int j = 0; j < hospitalArrayList.size(); j++) {
                                if (hospitalArrayList.get(j).uniqueID == slotsArrayList.get(i).hospital_id) {
                                    hos_name = hospitalArrayList.get(j).name;
                                }
                            }
                            System.out.println(slotsArrayList.get(i).hospital_id + " " + hos_name);
                        }
                    }
                    System.out.println("Enter hospital ID: ");
                    int hospital_id_u_want_to_book = sc.nextInt();

                    ArrayList<String[]> fithPart = new ArrayList<>();
                    for (int i = 0; i < slotsArrayList.size(); i++) {
                        if ((slotsArrayList.get(i).hospital_id == hospital_id_u_want_to_book)) {
                            System.out.println(slotsArrayList.get(i).slot + " -> Day: " + slotsArrayList.get(i).day_number + " Available Qty: " + slotsArrayList.get(i).quantity + " Vaccine: " + slotsArrayList.get(i).vaccine_name);
                            String temp = (slotsArrayList.get(i).slot + " -> Day: " + slotsArrayList.get(i).day_number + " Available Qty: " + slotsArrayList.get(i).quantity + " Vaccine: " + slotsArrayList.get(i).vaccine_name);
                            String[] temp_arr = temp.split(" ");
                            fithPart.add(temp_arr);
                        }
                        else System.out.println("No Slots Available");
                    }
                    System.out.println("Choose Slot: ");
                    int choose_slot = sc.nextInt();
                    for (int i = 0; i < citizenArrayList.size(); i++) {
                        for (int j = 0; j < fithPart.size(); j ++) {
                            if ((citizenArrayList.get(i).uniqueID == id) && (fithPart.get(j)[0].equals(Integer.toString(choose_slot)))){
                                System.out.println(citizenArrayList.get(i).name + " vaccinated with " + fithPart.get(j)[8]);
                            }
                        }
                    }
                }
            }
            // SHOWING THE SLOTS AVAILABLE WITH A HOSPITAL
            else if (menu_index == 6) {
                System.out.print("Enter Hospital ID: ");
                int hospital_id = sc.nextInt();
                for (int i = 0; i < slotsArrayList.size(); i++){
                    if (slotsArrayList.get(i).hospital_id == hospital_id) {
                        System.out.println("Day: " + slotsArrayList.get(i).day_number + " Vaccine: " + slotsArrayList.get(i).vaccine_name + " Available Qty: " + slotsArrayList.get(i).quantity);
                    }
                }
            }
        }
    }
}
