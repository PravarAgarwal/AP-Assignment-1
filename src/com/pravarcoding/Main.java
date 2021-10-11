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


class Slots_Methods_Class {
    static Scanner sc = new Scanner(System.in);
    public static void book_slot_by_pincode(long uniqueID_citizen, int pincode, ArrayList<Hospital> hospitalArrayList, ArrayList<Slots> slotsArrayList, ArrayList<Citizen> citizenArrayList, ArrayList<Vaccine> vaccineArrayList, ArrayList<String[]> fithPart) {
        for (int i = 0; i < hospitalArrayList.size(); i++) {
            // for printing the available hospitals in the location with pincode.
            if (hospitalArrayList.get(i).getPincode() == pincode) {
                System.out.println(hospitalArrayList.get(i).getUniqueID() + " " + hospitalArrayList.get(i).getName());
            }
        }
        System.out.println("Enter hospital ID: ");
        int hospital_id_u_want_to_book = sc.nextInt();
        int count = 0;


        for (int t = 0; t < slotsArrayList.size(); t++) {
            // for printing the slots available for the respective chosen hospital.
            if ((slotsArrayList.get(t).getHospital_id() == hospital_id_u_want_to_book)) {
                for (int r = 0; r < citizenArrayList.size(); r++) {
                    if ((citizenArrayList.get(r).getUniqueID() == uniqueID_citizen) && (citizenArrayList.get(r).getDueDate() <= slotsArrayList.get(t).getDay_number())) {
                        count++;
                        System.out.println(slotsArrayList.get(t).getSlot() + " -> Day: " + slotsArrayList.get(t).getDay_number() + " Available Qty: " + slotsArrayList.get(t).getQuantity() + " Vaccine: " + slotsArrayList.get(t).getVaccine_name());
                        String temp = (slotsArrayList.get(t).getSlot() + " -> Day: " + slotsArrayList.get(t).getDay_number() + " Available Qty: " + slotsArrayList.get(t).getQuantity() + " Vaccine: " + slotsArrayList.get(t).getVaccine_name());
                        String[] temp_arr = temp.split(" ");
                        fithPart.add(temp_arr);
                    }
                }
            }
        }
        if (count > 0) {
            System.out.println("Choose Slot: ");
            int choose_slot = sc.nextInt();
            String temp_vaccine = slotsArrayList.get(choose_slot).getVaccine_name();
            for (int u = 0; u < citizenArrayList.size(); u++) {
                if (citizenArrayList.get(u).getUniqueID() == uniqueID_citizen && citizenArrayList.get(u).getVaccination_status().equals("PARTIALLY VACCINATED") && citizenArrayList.get(u).getVaccine_received().equals(temp_vaccine)) {

                    // once the slot is chosen, the associated quantity of vaccine with it is lowered by 1
                    for (int i = 0; i < slotsArrayList.size(); i++) {
                        if (slotsArrayList.get(i).getSlot() == choose_slot && (slotsArrayList.get(i).getHospital_id() == hospital_id_u_want_to_book)) {
                            slotsArrayList.get(i).updateQuantity();
                        }
                    }
                    for (int i = 0; i < citizenArrayList.size(); i++) {
                        for (int j = 0; j < fithPart.size(); j++) {
                            if ((citizenArrayList.get(i).getUniqueID() == uniqueID_citizen) && (fithPart.get(j)[0].equals(Integer.toString(choose_slot)))) {
                                System.out.println(citizenArrayList.get(i).getName() + " vaccinated with " + fithPart.get(j)[8]);

                                // updating the vaccine received by the patient
                                citizenArrayList.get(i).setVaccine_received(fithPart.get(j)[8]);

                                // incrementing the number of doses received by the patient
                                citizenArrayList.get(i).setNumber_of_doses_recieved(citizenArrayList.get(i).getNumber_of_doses_received() + 1);

                                for (int k = 0; k < vaccineArrayList.size(); k++) {
                                    if (vaccineArrayList.get(k).getName().equals(fithPart.get(j)[8])) {

                                        // updating the value of due date
                                        citizenArrayList.get(i).setDueDate(vaccineArrayList.get(k).getGap_between_doses());

                                        // handling the status of vaccination of patient
                                        if (citizenArrayList.get(i).getNumber_of_doses_received() > 0 && citizenArrayList.get(i).getNumber_of_doses_received() < vaccineArrayList.get(k).getNumber_of_doses()) {
                                            citizenArrayList.get(i).setVaccination_status("PARTIALLY VACCINATED");
                                        } else if (citizenArrayList.get(i).getNumber_of_doses_received() == vaccineArrayList.get(k).getNumber_of_doses()) {
                                            citizenArrayList.get(i).setVaccination_status("FULLY VACCINATED");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else if (citizenArrayList.get(u).getUniqueID() == uniqueID_citizen && citizenArrayList.get(u).getVaccination_status().equals("REGISTERED")) {

                    // once the slot is chosen, the associated quantity of vaccine with it is lowered by 1
                    for (int i = 0; i < slotsArrayList.size(); i++) {
                        if (slotsArrayList.get(i).getSlot() == choose_slot && (slotsArrayList.get(i).getHospital_id() == hospital_id_u_want_to_book)) {
                            slotsArrayList.get(i).updateQuantity();
                        }
                    }
                    for (int i = 0; i < citizenArrayList.size(); i++) {
                        for (int j = 0; j < fithPart.size(); j++) {
                            if ((citizenArrayList.get(i).getUniqueID() == uniqueID_citizen) && (fithPart.get(j)[0].equals(Integer.toString(choose_slot)))) {
                                System.out.println(citizenArrayList.get(i).getName() + " vaccinated with " + fithPart.get(j)[8]);

                                // updating the vaccine received by the patient
                                citizenArrayList.get(i).setVaccine_received(fithPart.get(j)[8]);

                                // incrementing the number of doses received by the patient
                                citizenArrayList.get(i).setNumber_of_doses_recieved(citizenArrayList.get(i).getNumber_of_doses_received() + 1);

                                for (int k = 0; k < vaccineArrayList.size(); k++) {
                                    if (vaccineArrayList.get(k).getName().equals(fithPart.get(j)[8])) {

                                        // updating the value of due date
                                        citizenArrayList.get(i).setDueDate(vaccineArrayList.get(k).getGap_between_doses());

                                        // handling the status of vaccination of patient
                                        if (citizenArrayList.get(i).getNumber_of_doses_received() > 0 && citizenArrayList.get(i).getNumber_of_doses_received() < vaccineArrayList.get(k).getNumber_of_doses()) {
                                            citizenArrayList.get(i).setVaccination_status("PARTIALLY VACCINATED");
                                        } else if (citizenArrayList.get(i).getNumber_of_doses_received() == vaccineArrayList.get(k).getNumber_of_doses()) {
                                            citizenArrayList.get(i).setVaccination_status("FULLY VACCINATED");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else System.out.println("chosen option cannot be provided");
                // two conditions possible : 1 ) already fully vaccinated 2 ) vaccine mixing
            }


        } else {
            System.out.println("no slots available");

          }
    }
    public static void book_slot_by_vaccine(long uniqueID_citizen, String vaccine_name, ArrayList<Hospital> hospitalArrayList, ArrayList<Slots> slotsArrayList, ArrayList<Citizen> citizenArrayList, ArrayList<Vaccine> vaccineArrayList, ArrayList<String[]> fithPart) {
        for (int i = 0; i < slotsArrayList.size(); i++) {
            if (slotsArrayList.get(i).getVaccine_name().equals(vaccine_name)){
                for (int j = 0; j < hospitalArrayList.size(); j ++) {
                    if (slotsArrayList.get(i).getHospital_id() == hospitalArrayList.get(j).getUniqueID()){
                        System.out.println(slotsArrayList.get(i).getHospital_id() + " " + hospitalArrayList.get(j).getName());
                    }
                }
            }

        }
        System.out.println("Enter hospital ID: ");
        int hospital_id_u_want_to_book = sc.nextInt();
        int var = 0;
        for (int p = 0; p < slotsArrayList.size(); p++) {
            if (slotsArrayList.get(p).getHospital_id() == hospital_id_u_want_to_book && slotsArrayList.get(p).getVaccine_name().equals(vaccine_name)) {

                for (int q = 0; q < citizenArrayList.size(); q++) {
                    if ((citizenArrayList.get(q).getUniqueID() == uniqueID_citizen) && (citizenArrayList.get(q).getDueDate() <= slotsArrayList.get(p).getDay_number())) {
                        var++;
                        System.out.println(slotsArrayList.get(p).getSlot() + " -> Day: " + slotsArrayList.get(p).getDay_number() + " Available Qty: " + slotsArrayList.get(p).getQuantity() + " Vaccine: " + slotsArrayList.get(p).getVaccine_name());
                        String temp = (slotsArrayList.get(p).getSlot() + " -> Day: " + slotsArrayList.get(p).getDay_number() + " Available Qty: " + slotsArrayList.get(p).getQuantity() + " Vaccine: " + slotsArrayList.get(p).getVaccine_name());
                        String[] temp_arr = temp.split(" ");
                        fithPart.add(temp_arr);
                    }

                }




                if (var == 0) {
                    System.out.println("No slots available");
                    return;
                } else {
                    System.out.println("Choose Slot: ");
                    int choose_slot = sc.nextInt();
                    // once the slot is chosen, the associated quantity of vaccine with it is lowered by 1
                    for (int i = 0; i < slotsArrayList.size(); i++) {
                        if (slotsArrayList.get(i).getSlot() == choose_slot && (slotsArrayList.get(i).getHospital_id() == hospital_id_u_want_to_book)) {

                            slotsArrayList.get(i).updateQuantity();
                        }
                    }
                    boolean flag = false;
                    for (int i = 0; i < citizenArrayList.size(); i++) {
                        for (int j = 0; j < fithPart.size(); j++) {
                            if ((citizenArrayList.get(i).getUniqueID() == uniqueID_citizen) && (fithPart.get(j)[0].equals(Integer.toString(choose_slot)))) {
                                flag = true;
                                System.out.println(citizenArrayList.get(i).getName() + " vaccinated with " + fithPart.get(j)[8]);

                                // updating the vaccine received by the patient
                                citizenArrayList.get(i).setVaccine_received(fithPart.get(j)[8]);

                                // incrementing the number of doses received by the patient
                                citizenArrayList.get(i).setNumber_of_doses_recieved(citizenArrayList.get(i).getNumber_of_doses_received() + 1);
                                for (int k = 0; k < vaccineArrayList.size(); k++) {
                                    if (vaccineArrayList.get(k).getName().equals(fithPart.get(j)[8])) {

                                        // updating the value of due date
                                        citizenArrayList.get(i).setDueDate(vaccineArrayList.get(k).getGap_between_doses());

                                        // handling the status of vaccination of patient
                                        if (citizenArrayList.get(i).getNumber_of_doses_received() > 0 && citizenArrayList.get(i).getNumber_of_doses_received() < vaccineArrayList.get(k).getNumber_of_doses()) {
                                            citizenArrayList.get(i).setVaccination_status("PARTIALLY VACCINATED");
                                        } else if (citizenArrayList.get(i).getNumber_of_doses_received() == vaccineArrayList.get(k).getNumber_of_doses()) {
                                            citizenArrayList.get(i).setVaccination_status("FULLY VACCINATED");
                                        }
                                    }
                                }
                            }
                            if (flag) break;
                        }
                        if (flag) break;
                    }
                }
            }


        }

    }
    public static void status_function (long patient_id, ArrayList<Hospital> hospitalArrayList, ArrayList<Slots> slotsArrayList, ArrayList<Citizen> citizenArrayList, ArrayList<Vaccine> vaccineArrayList) {
        // to print the status of vaccination

        for (int i = 0; i < citizenArrayList.size(); i++) {
            if (citizenArrayList.get(i).getUniqueID() == patient_id) {
                String temp = citizenArrayList.get(i).getVaccination_status();
                System.out.println(temp);

            }
        }

        for (int i = 0; i < citizenArrayList.size(); i++) {
            if (citizenArrayList.get(i).getUniqueID() == patient_id) {
                System.out.println("Vaccine Given: " + citizenArrayList.get(i).getVaccine_received());
                System.out.println("Number of Doses given: " + citizenArrayList.get(i).getNumber_of_doses_received());
                for (int j = 0; j < vaccineArrayList.size(); j++) {
                    if (vaccineArrayList.get(j).getNumber_of_doses() > citizenArrayList.get(i).getNumber_of_doses_received()) {
                        if (vaccineArrayList.get(j).getName().equals(citizenArrayList.get(i).getVaccine_received())) {
                            int nextDoseDueDate = citizenArrayList.get(i).getDueDate();

                            System.out.println("Next Dose due date: " + nextDoseDueDate);
                        }
                    }
                }
            }
        }
    }
}
class Slots {
    static Scanner sc = new Scanner(System.in);
    private final int hospital_id;
    private final String vaccine_name;
    private int quantity;
    private int day_number;
    private int slot;

    // getter functions
    public int getHospital_id() {
        return this.hospital_id;
    }
    public String getVaccine_name() {
        return this.vaccine_name;
    }
    public int getQuantity() {
        return this.quantity;
    }

    public int getDay_number() {
        return day_number;
    }

    public int getSlot() {
        return slot;
    }

    // setter functions

    public void setDay_number (int day_number) {
        this.day_number = day_number;
    }
    public void setSlot ( int slot) {
        this.slot = slot;
    }
    public void updateQuantity () {
        this.quantity--;
    }


    // CONSTRUCTOR
    Slots(int hospital_id, String vaccine_name, int quantity, int day_number, int slot) {
        this.hospital_id = hospital_id;
        this.vaccine_name = vaccine_name;
        this.quantity = quantity;
        this.day_number = day_number;
        this.slot = slot;
    }
}
class Vaccine {
    private final String name;
    private final int number_of_doses;
    private final int gap_between_doses;

    // getter functions
    public String getName() {
        return this.name;
    }
    public int getNumber_of_doses() {
        return this.number_of_doses;
    }
    public int getGap_between_doses() {
        return this.gap_between_doses;
    }

    // CONSTRUCTOR FOR VACCINE CLASS
    Vaccine(String name, int number_of_doses, int gap_between_doses) {
        this.name = name;
        this.number_of_doses = number_of_doses;
        this.gap_between_doses = gap_between_doses;
    }
}
class Citizen {
    private final String name;
    private final int age;
    private final long uniqueID;
    private String vaccination_status;
    private int number_of_doses_received;
    private String vaccine_received;
    private int dueDate;

    // getter functions
    public String getName() {
        return this.name;
    }
    public int getAge() {
        return this.age;
    }
    public long getUniqueID() {
        return this.uniqueID;
    }
    public int getNumber_of_doses_received() {
        return number_of_doses_received;
    }
    public String getVaccination_status() {
        return vaccination_status;
    }

    public String getVaccine_received() {
        return vaccine_received;
    }

    public int getDueDate() {
        return dueDate;
    }

    // setter functions
    public void setVaccination_status (String vaccination_status) {
        this.vaccination_status = vaccination_status;
    }
    public void setNumber_of_doses_recieved (int number_of_doses_received) {
        this.number_of_doses_received = number_of_doses_received;
    }
    public void setVaccine_received (String vaccine_received) {
        this.vaccine_received = vaccine_received;
    }
    public void setDueDate (int dueDate) {
        this.dueDate += dueDate;
    }

    // CONSTRUCTOR FOR CITIZEN CLASS : 
    Citizen(String name, int age, long uniqueID) {
        vaccination_status = "REGISTERED";
        this.name = name;
        this.age = age;
        this.uniqueID = uniqueID;
        this.vaccine_received = "nil";
        this.dueDate = 1;


    }
}
class Hospital {
    private final String name;
    private final int pincode;
    private final int uniqueID;

    public String getName () {
        return this.name;
    }
    public int getPincode() {
        return this.pincode;
    }
    public int getUniqueID() {
        return this.uniqueID;
    }
    
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
        ArrayList<Slots> slotsArrayList = new ArrayList<>();


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
                System.out.println("Hospital name: " + name + " PinCode: " + pincode + " Unique ID: " + hospital.getUniqueID());
                System.out.println(hospitalArrayList);
                m.showMenu();
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
                for (int k = 0; k < hospitalArrayList.size(); k++) {
                    if (hospitalArrayList.get(k).getUniqueID() == hospitalID) {
                        System.out.print("Enter number of slots to be added: ");
                        int no_of_slots = sc.nextInt();

                        for (int i = 0; i < no_of_slots; i ++) {
                            System.out.print("Enter day number: ");
                            int day_number = sc.nextInt();

                            System.out.print("Enter Quantity: ");
                            int quantity = sc.nextInt();

                            System.out.println("Select vaccine: ");
                            for (int j = 0; j < types_of_vaccines.size(); j++) {
                                System.out.println(j + ". " + types_of_vaccines.get(j).getName());
                            }
                            int vaccine = sc.nextInt();
                            Slots slot = new Slots(hospitalID, types_of_vaccines.get(vaccine).getName(), quantity, day_number, i);
                            slot.setDay_number(day_number);
                            slotsArrayList.add(slot);
                            System.out.println("Slot added by Hospital " + hospitalID + " for Day: " + day_number + ", Available Quantity: " + quantity + " of Vaccine " + types_of_vaccines.get(vaccine).getName());
                        }
                    }
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
                    System.out.println("Enter pincode: ");
                    int pincode = sc.nextInt();
                    ArrayList<String[]> fithPart = new ArrayList<>();
                    Slots_Methods_Class.book_slot_by_pincode(id, pincode, hospitalArrayList, slotsArrayList, citizenArrayList, types_of_vaccines, fithPart);
                }
                if (option == 2) {
                    System.out.println("Enter Vaccine name: ");
                    String vaccine_name_entered = sc.next();
                    ArrayList<String[]> fithPart = new ArrayList<>();
                    Slots_Methods_Class.book_slot_by_vaccine(id, vaccine_name_entered, hospitalArrayList, slotsArrayList, citizenArrayList, types_of_vaccines, fithPart);
                }


            }
            // SHOWING THE SLOTS AVAILABLE WITH A HOSPITAL
            else if (menu_index == 6) {
                System.out.print("Enter Hospital ID: ");
                int hospital_id = sc.nextInt();
                for (int i = 0; i < slotsArrayList.size(); i++){
                    if (slotsArrayList.get(i).getHospital_id() == hospital_id) {
                        System.out.println("Day: " + slotsArrayList.get(i).getDay_number() + " Vaccine: " + slotsArrayList.get(i).getVaccine_name() + " Available Qty: " + slotsArrayList.get(i).getQuantity());
                    }
                }
            }
            else if (menu_index == 7) {
                System.out.println("Enter patient ID: ");
                long patientID = sc.nextLong();
                Slots_Methods_Class.status_function(patientID, hospitalArrayList, slotsArrayList, citizenArrayList, types_of_vaccines);
            }
        }
    }
}

