package com.egyptFlightReservation.Model;

import java.time.LocalDate;
import java.util.*;

import Tools.myTuple;
import com.egyptFlightReservation.Model.FileHandling.FileAdministrator;
import com.egyptFlightReservation.Model.FileHandling.FileSaver;
import com.egyptFlightReservation.Model.FileHandling.FolderCleaner;
import com.egyptFlightReservation.Model.Payment.*;
import com.egyptFlightReservation.Model.User.Admin;
import com.egyptFlightReservation.Model.User.Client;


public class Database {
    private static TreeMap<String, myTuple<Client, ArrayList<Booking>, ArrayList<Ticket>, ArrayList<PaymentMethod>>> userTable;
    /// ClientUserName Info
    private static TreeMap<String, Admin> adminTable;
    /// AdminName Info
    private static TreeMap<String, Airline> adminOperations;
    /// AdminName Airline
    private static TreeMap<LocalDate, ArrayList<Flight>> searchingTable;
    private static ArrayList<Airport> airports;
    /// save and load it
    private static String curUser;
    private static Database database;

    private Database() { // call it at begin of app
        adminOperations = new TreeMap<>();
        userTable = new TreeMap<>();
        adminTable = new TreeMap<>();
        searchingTable = new TreeMap<>();
        airports = new ArrayList<>();
    }



    public static Database getDatabase() {
        if (database == null)
            database = new Database();
        return database;
    }

    public static void addPremiumPoints(int gainedPremiumPoints) {
        userTable.get(curUser).getFirst().addPremiumPoints(gainedPremiumPoints);
    }
    public static void subtractPremiumPoints(int points){
        userTable.get(curUser).getFirst().subtractPremiumPoints(points);
    }




    public static int getUserPremiumPoints(){
        return userTable.get(curUser).getFirst().getPremiumPoints();
    }

    public static void addClient(Client client) {
        System.out.println(client.getName());
        if (client == null || client.getName() == null || client.getName().isEmpty()) {
            throw new IllegalArgumentException("Client name cannot be null or empty");
        }
        userTable.put(curUser, new myTuple<>(client, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }

    public static void addTicket(LocalDate DepDate, String flightNumber, Ticket ticket) {
        for (Flight flight : searchingTable.get(DepDate)) {
            if (flight.getFlight_number().equals(flightNumber)) {
                flight.addTicket(ticket);
                break;
            }
        }
    }

    public static ArrayList<PaymentMethod> getClientPayment() {
        return userTable.get(curUser).getFourth();
    }

    public static void addPaymentMethod(PaymentMethod paymentMethod) {
        userTable.get(curUser).getFourth().add(paymentMethod);
    }

    public static void addBooking(Booking booking) {
        userTable.get(curUser).getSecond().add(booking);
    }

    public static Airline getAirline() {
        for (String cur : adminOperations.keySet()) {
            System.out.println(cur + " in get airline function");
        }
        if (!adminOperations.containsKey(curUser)) {
            System.out.println(curUser + " not found in admin operations container");
        }
        return adminOperations.get(curUser);
    }

    public static boolean isUniqueUserName(String userName) {
        return !userTable.containsKey(userName);
    }

    public static boolean isCorrectLogin(String userName, String password) {
        if (!userTable.containsKey(userName))
            return false;
        return password.equals(userTable.get(userName).getFirst().getPassword());
    }

    public static boolean isAdmin(String userName, String password) {
        if (!adminTable.containsKey(userName))
            return false;
        return password.equals(adminTable.get(userName).getPassword());
    }

    public static String getCurUser() {
        return curUser;
    }

    public static void setCurUser(String username) {
        curUser = username;
    }

    public static void addNewFlight(Flight flight) {
        if (searchingTable.get(flight.getDepartureDate()) == null)
            searchingTable.put(flight.getDepartureDate(), new ArrayList<>());
        searchingTable.get(flight.getDepartureDate()).add(flight);
        adminOperations.get(curUser).addFlight(flight);
    }

    public static void removeFlight(String flightNumber) {
        if (adminOperations.containsKey(curUser))
            adminOperations.get(curUser).removeFlight(flightNumber);
        else
            System.out.println("Admin Name not contain in admin operations container");
    }


    public static Admin getAdmin() {
        if (adminTable == null || !adminTable.containsKey(curUser))
            return null;
        return adminTable.get(curUser);
    }

    public static void updateSchedule(String flightNumber, LocalDate departureDate, LocalDate arrivalDate) {
        adminOperations.get(curUser).setSchedule(flightNumber, departureDate, arrivalDate);
    }

    public static boolean expandCountOfSeats(String flightNumber, int newCountOfRows, int countOfFirstClass, int countOfBusinessClass, int countOfEconomyClass) {
        System.out.println("curUser " + curUser);
        return adminOperations.get(curUser).expandCountOfSeats(flightNumber, newCountOfRows, countOfFirstClass, countOfBusinessClass, countOfEconomyClass);
    }

    public static ArrayList<Flight> searchFlights(LocalDate fromDate, LocalDate toDate) {
        SortedMap<LocalDate, ArrayList<Flight>> resultFlights = searchingTable.subMap(fromDate, true, toDate, true);
        ArrayList<Flight> flights = new ArrayList<>();
        for (var cur : resultFlights.keySet())
            for (Flight flight : resultFlights.get(cur))
                flights.add(flight);

        return flights;
    }

    public static Client getClient() {
        if (!userTable.containsKey(curUser)) throw new IllegalArgumentException("User not found");
        if (userTable.get(curUser).getFirst() == null) throw new IllegalArgumentException("Client not found");
        return userTable.get(curUser).getFirst();
    }

    public static String[][] getBookingHistory() {
//        return userTable.get(curUser).getSecond();
        String[][] history = new String[userTable.get(curUser).getSecond().size()][11];
        int i = -1;
        for (var cur : userTable.get(curUser).getSecond()) {
            String[] tmp = {cur.getBookingId(), (cur.getBookingStatus().equals("true") ? "Done" : "Failed"),
                    String.valueOf(cur.getBookingDate()), cur.getAirlineName(), cur.getFlightNumber(),
                    cur.getDepartureAirport(), cur.getArrivalAirport(), cur.getDepartureDate(), cur.getArrivalDate(),
                    String.valueOf(cur.getCountOfSeats()), String.valueOf(cur.getTotalPrice())};
            history[++i] = tmp;
        }
        return history;
    }

    public static void addPassenger(String flightNumber, LocalDate departureDate) {
        for (Flight flight : searchingTable.get(departureDate))
            if (flight.getFlight_number().equals(flightNumber)) {
                flight.addPassenger(new Passenger(userTable.get(curUser).getFirst().getName(), UUID.randomUUID().toString(),
                        userTable.get(curUser).getFirst().getPhoneNumber(), userTable.get(curUser).getFirst().getEmail()));
            }
    }

    public static void addInSearchingTable(LocalDate departureDate, Flight flight) {
        if (!searchingTable.containsKey(departureDate))
            searchingTable.put(departureDate, new ArrayList<>());
        searchingTable.get(departureDate).add(flight);
    }


    /// Edit Client Information
    public static void editUsername(String username) {
        userTable.put(username, userTable.get(curUser));
        userTable.remove(curUser);
        curUser = username;
        userTable.get(curUser).getFirst().setUsername(username);
    }

    public static void editPassword(String password) {
        userTable.get(curUser).getFirst().setPassword(password);
    }

    public static void editEmail(String email) {
        userTable.get(curUser).getFirst().setEmail(email);
    }

    public static void editPhoneNumber(String phoneNumber) {
        userTable.get(curUser).getFirst().setPhoneNumber(phoneNumber);
    }

    public static void editFullName(String fullName) {
        userTable.get(curUser).getFirst().setName(fullName);
    }

    /// Main Admin Functions
    public static void createNewAdmin(String adminName, Admin admin, String AirlineName, String AirlineCode, String airlineLocation) {
        adminTable.put(adminName, admin);
        adminOperations.put(adminName, new Airline(AirlineName, AirlineCode, airlineLocation));
    }

    public static void addNewAirport(Airport airport) {
        if (airports == null)
            airports = new ArrayList<>();
        airports.add(airport);
    }

    public static String[][] getAllAirportsInfo(){
        if(airports != null && !airports.isEmpty()){
            String[][] info = new String[airports.size()][3];
            for(int i = 0 ;i < airports.size() ; ++i)
                info[i] = airports.get(i).toString().split(" ");
            return info;
        }
        return null;
    }

    public static boolean removeAirport(String airportCode){
        for(Airport airport : airports){
            if(airportCode.equals(airport.getAirportcode())){
                airports.remove(airport);
                return true;
            }
        }
        return false;
    }

    public static boolean removeAdmin(String adminUsername){
        if(!adminTable.containsKey(adminUsername))
            return false;
        adminTable.remove(adminUsername);
        return true;
    }

    public static boolean removeClient(String clientUsername){
        if(!userTable.containsKey(clientUsername))
            return false;
        userTable.remove(clientUsername);
        return true;
    }

    public static String[][] getAllAdminsInfo(){
        if(adminTable != null && !adminTable.isEmpty()){
            String[][] info = new String[adminTable.size()][5];
            int i = -1;
            for(String name : adminTable.keySet())
                info[++i] = adminTable.get(name).toString().split(" ");
            return info;
        }
        return null;
    }

    public static String[][] getAllClientsInfo(){
        if(userTable != null && !userTable.isEmpty()){
            String[][] info = new String[userTable.size()][8];
            int i = -1;
            for(String name : userTable.keySet()){
                info[++i] = userTable.get(name).getFirst().toString().split(" ");
                info[i][2].replaceAll("_" , " ");
            }

            return info;
        }
        return null;
    }


    /// loading Process
    public static void loadUserTable(String userName, String[] clientContent, String[] bookingHistory,
                                     String[] tickets, String[][] paymentMethods) {
        Client newClient = new Client(clientContent[0], clientContent[1], clientContent[2], clientContent[3]
                , clientContent[4], clientContent[5], clientContent[6], clientContent[7] , Integer.parseInt(clientContent[8]));

        ArrayList<PaymentMethod> methods = new ArrayList<>();
        for (int i = 0; i < (paymentMethods[0] == null ? 0 : paymentMethods[0].length); i++) {
            String[] tmp = paymentMethods[0][i].split(" ");
            DebitCard debitCard = new DebitCard(tmp[0], tmp[1], tmp[2], tmp[3], Double.parseDouble(tmp[4]));
            methods.add(debitCard);
        }
        for (int i = 0; i < (paymentMethods[1] == null ? 0 : paymentMethods[1].length); i++) {
            String[] tmp = paymentMethods[1][i].split(" ");
            MasterCard masterCard = new MasterCard(tmp[0], tmp[1], tmp[2], tmp[3], Double.parseDouble(tmp[4]));
            methods.add(masterCard);
        }
        for (int i = 0; i < (paymentMethods[2] == null ? 0 : paymentMethods[2].length); i++) {
            String[] tmp = paymentMethods[2][i].split(" ");
            PayPal payPal = new PayPal(tmp[0], Double.parseDouble(tmp[1]));
            methods.add(payPal);
        }
        for (int i = 0; i < (paymentMethods[3] == null ? 0 : paymentMethods[3].length); i++) {
            String[] tmp = paymentMethods[3][i].split(" ");
            VodafoneCash cash = new VodafoneCash(tmp[0], Double.parseDouble(tmp[1]));
            methods.add(cash);
        }

        //Booking
        ArrayList<Booking> bookings = new ArrayList<>();
        for (int i = 0; i < (bookingHistory == null ? 0 : bookingHistory.length); i++) {
            String[] tmp = bookingHistory[i].split(" ");

            Booking curBooking = new Booking(new ArrayList<String>(List.of(tmp)));
            bookings.add(curBooking);
        }

        userTable.put(userName, new myTuple<Client, ArrayList<Booking>, ArrayList<Ticket>, ArrayList<PaymentMethod>>
                (newClient, bookings, null, methods));
    }

    public static void loadAdminTable(String[] content) {
        for (String cur : content) {
            String[] tmp = cur.split(" ");
            adminTable.put(tmp[0], new Admin(tmp[0], tmp[1], tmp[2], tmp[3], tmp[4]));
        }
    }

    public static void loadAdminOperations(String[] airlineInfo, ArrayList<String[]> flightsInfo,
                                           ArrayList<String[]> passengersInfo,
                                           ArrayList<String[]> ticketsInfo,
                                           ArrayList<String[]> seatsInfo) {
        if (adminOperations == null)
            adminOperations = new TreeMap<>();

        String[] tmp = airlineInfo[0].split(" ");
        if (!adminOperations.containsKey(tmp[0])) {
            adminOperations.put(tmp[0], new Airline(tmp[1], tmp[2], tmp[3]));
        }

        if (flightsInfo != null && flightsInfo.size() > 0) {
            adminOperations.get(tmp[0]).setFlights(flightsInfo, passengersInfo, ticketsInfo, seatsInfo);
        }
    }

    public static void loadAirports(String[] content) {
        for (String cur : content) {
            String[] tmp = cur.split(" ");
//            System.out.println(tmp[0] + " " + tmp[1] + " " + tmp[2]);
            airports.add(new Airport(tmp[0], tmp[1], tmp[2]));
        }
    }

    /// Saving Process
    public static void saveAdminOperations() {
        if (adminOperations != null || adminOperations.size() != 0) {

            ArrayList<String> content = new ArrayList<>();
            for (String adminName : adminOperations.keySet()) {
                content.add(adminOperations.get(adminName).get_name());
            }

            FileSaver.testDir(FileAdministrator.ROOT_PATH + "Airline");
            FileSaver.save(FileAdministrator.ROOT_PATH + "Airline" + "/AirlineNames", content); /// AirlineNames file saved

            for (String airlineName : content) { // Make all airline directory
                FileSaver.testDir(FileAdministrator.ROOT_PATH + "Airline/" + airlineName);
            }

            /// Clean any airline folders that removed
            content.add("AirlineNames");
            FolderCleaner.cleanFolder(FileAdministrator.ROOT_PATH + "Airline", content);
            content.clear();


            for (String adminName : adminOperations.keySet()) { // load info file of each airline
                String tmp = adminName + " " + adminOperations.get(adminName).get_name() + " " +
                        adminOperations.get(adminName).get_ID() + " " + adminOperations.get(adminName).get_location();
                content.add(tmp);
                FileSaver.save(FileAdministrator.ROOT_PATH + "Airline/" + adminOperations.get(adminName).get_name() + "/info", content);
                content.clear();
            }

            for (String adminName : adminOperations.keySet()) { //Make flight folder and create flightNumber and folder for each flight and create and add info,passenegers,Tickets and Seats files for each flight  in each airline
                FileSaver.testDir(FileAdministrator.ROOT_PATH + "Airline/" + adminOperations.get(adminName).get_name() + "/Flight");
                if (!adminOperations.get(adminName).isEmptyFlights()) {
                    ArrayList<String> flightNums = adminOperations.get(adminName).getflightNumbers();
                    FileSaver.save(FileAdministrator.ROOT_PATH + "Airline/" + adminOperations.get(adminName).get_name() + "/Flight/flightNumber", flightNums);

                    for (String flightNum : flightNums) {
                        FileSaver.testDir(FileAdministrator.ROOT_PATH + "Airline/" + adminOperations.get(adminName).get_name() + "/Flight/" + flightNum);

                        content = adminOperations.get(adminName).getFlightInfo(flightNum);
                        FileSaver.save(FileAdministrator.ROOT_PATH + "Airline/" + adminOperations.get(adminName).get_name() + "/Flight/" + flightNum + "/info", content);
                        if (!adminOperations.get(adminName).isEmptyPassengers(flightNum)) {
                            content = adminOperations.get(adminName).getPassenegersInfoOfFlight(flightNum);
                            FileSaver.save(FileAdministrator.ROOT_PATH + "Airline/" + adminOperations.get(adminName).get_name() + "/Flight/" + flightNum + "/Passengers", content);
                        }
                        if (!adminOperations.get(adminName).isEmptyTickets(flightNum)) {
                            content = adminOperations.get(adminName).getTicketsInfoOfFlight(flightNum);
                            FileSaver.save(FileAdministrator.ROOT_PATH + "Airline/" + adminOperations.get(adminName).get_name() + "/Flight/" + flightNum + "/Ticket", content);
                        }

                        //load seats
                        content = adminOperations.get(adminName).getSeatsInfo(flightNum);
                        FileSaver.save(FileAdministrator.ROOT_PATH + "Airline/" + adminOperations.get(adminName).get_name() + "/Flight/" + flightNum + "/Seats", content);
                    }
                }

            }
        }
    }

    public static void saveAdminTable() {
        if (adminTable != null || adminTable.size() != 0) {
            ArrayList<String> content = new ArrayList<>();
            for (String adminName : adminTable.keySet())
                content.add(adminTable.get(adminName).toString());

            FileSaver.testDir(FileAdministrator.ROOT_PATH + "User/Admin");
            FileSaver.save(FileAdministrator.ROOT_PATH + "User/Admin/Admins", content);
        }
    }

    public static void saveUserTable() { /// create and save clientUsernames file and folder for each client contain info and booking and PaymentMethod folder contain 4 files for payments method
        if (userTable != null || userTable.size() != 0) {
            ArrayList<String> content = new ArrayList<>();
            for (String userName : userTable.keySet()) {
                content.add(userName);
            }
            FileSaver.testDir(FileAdministrator.ROOT_PATH + "User/Client");
            FileSaver.save(FileAdministrator.ROOT_PATH + "User/Client/ClientUsernames", content);
            content.add("ClientUsernames");
            FolderCleaner.cleanFolder(FileAdministrator.ROOT_PATH + "User/Client", content);
            content.clear();

            for (String userName : userTable.keySet()) {
                content.add(userTable.get(userName).getFirst().toString());

                FileSaver.testDir(FileAdministrator.ROOT_PATH + "User/Client/" + userName);
                FileSaver.save(FileAdministrator.ROOT_PATH + "User/Client/" + userName + "/info", content);

                content.clear();
                for (Booking booking : userTable.get(userName).getSecond()) {
                    content.add(booking.toString());
                }
                FileSaver.save(FileAdministrator.ROOT_PATH + "User/Client/" + userName + "/Booking", content);
                content.clear();


                ArrayList<String> depCard = new ArrayList<>(),
                                  masCard = new ArrayList<>(),
                                  payPal = new ArrayList<>(),
                                  vodafoneCash = new ArrayList<>();
                FileSaver.testDir(FileAdministrator.ROOT_PATH + "User/Client/" + userName + "/PaymentMethod");
                for (PaymentMethod paymentMethod : userTable.get(userName).getFourth()) {
                    if (paymentMethod instanceof DebitCard) {
                        depCard.add(paymentMethod.toString());
                    } else if (paymentMethod instanceof MasterCard) {
                        masCard.add(paymentMethod.toString());
                    } else if (paymentMethod instanceof PayPal) {
                        payPal.add(paymentMethod.toString());
                    } else if (paymentMethod instanceof VodafoneCash) {
                        vodafoneCash.add(paymentMethod.toString());
                    }
                }
                FileSaver.save(FileAdministrator.ROOT_PATH + "User/Client/" + userName + "/PaymentMethod/DebitCard", depCard);
                FileSaver.save(FileAdministrator.ROOT_PATH + "User/Client/" + userName + "/PaymentMethod/MasterCard", masCard);
                FileSaver.save(FileAdministrator.ROOT_PATH + "User/Client/" + userName + "/PaymentMethod/PayPal", payPal);
                FileSaver.save(FileAdministrator.ROOT_PATH + "User/Client/" + userName + "/PaymentMethod/VodafoneCash", vodafoneCash);
            }
        }
    }

    public static void saveAirports() {
        if (airports != null && airports.size() != 0) {
            ArrayList<String> content = new ArrayList<>();
            for (Airport airport : airports) {
                content.add(airport.toString());
            }
            FileSaver.save(FileAdministrator.ROOT_PATH + "Airports", content);
        }
    }

    public static void saveData() {
        saveAdminTable();
        saveAdminOperations();
        saveUserTable();
        saveAirports();
    }
}
