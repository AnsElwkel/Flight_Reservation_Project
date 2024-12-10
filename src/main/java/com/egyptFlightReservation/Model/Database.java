package com.egyptFlightReservation.Model;
import java.time.LocalDate;
import java.util.*;

import Tools.myTuple;
import Tools.myPair;
import com.egyptFlightReservation.Model.Payment.*;
import com.egyptFlightReservation.Model.User.Admin;
import com.egyptFlightReservation.Model.User.Client;


public class Database {
    private static TreeMap<String , myTuple<Client, ArrayList<Booking> , ArrayList<Ticket> , ArrayList<PaymentMethod> >> userTable;//loading done
    private static TreeMap<String , Admin > adminTable; //loading done
    private static TreeMap<LocalDate , ArrayList<Flight>> searchingTable;
    private static TreeMap<String , Airline> adminOperations;
    private static ArrayList<Airport> airports;
    private static String curUser;
    private static Database database ;

    private Database(){ // call it at begin of app
        adminOperations = new TreeMap<>();
        userTable = new TreeMap<>();
        adminTable = new TreeMap<>();
        searchingTable = new TreeMap<>();
    }

    public static Database getDatabase(){
        if(database == null)
            database = new Database();
        return database;
    }

    public static Airline getAirline(){
        return adminOperations.get(curUser);
    }
    public static boolean isUniqueUserName(String userName){
        return !userTable.containsKey(userName);
    }
    public static boolean isCorrectLogin(String userName, String password){
        if(!userTable.containsKey(userName))
            return false;
        return password.equals(userTable.get(userName).getFirst().getPassword());
    }
    public static boolean isAdmin(String userName, String password){
        if(!adminTable.containsKey(userName))
            return false;
        return password.equals(adminTable.get(userName).getPassword());
    }
    public static String getCurUser(){
        return curUser;
    }
    public static void setCurUser(String username){
        curUser = username;
    }
    public static void addNewFlight(Flight flight){
        searchingTable.get(flight.getDepartureDate()).add(flight);
        adminOperations.get(curUser).addFlight(flight);
    }
    public static void removeFlight(String flightNumber){
        if(adminOperations.containsKey(curUser))
            adminOperations.get(curUser).removeFlight(flightNumber);
        else
            System.out.println("Admin Name not contain in admin operations container");
    }
    public static void loadUserTable(String userName , String[] clientContent, String[] bookingHistory,
                                     String[] tickets, String[][] paymentMethods){
        Client newClient = new Client(clientContent[0] , clientContent[1] , clientContent[2] , clientContent[3]
                ,clientContent[4] , clientContent[5] , clientContent[6] , clientContent[7]);
        ArrayList<Ticket> newTickets = new ArrayList<>();
        for(String ticket : tickets){
            String[] tmp = ticket.split(" ");
            Ticket curTicket = new Ticket(tmp[0],Boolean.parseBoolean(tmp[1]),Double.parseDouble(tmp[2]) , tmp[3] , tmp[4],tmp[5],tmp[6],tmp[7]);
            newTickets.add(curTicket);
        }

        ArrayList<PaymentMethod> methods = new ArrayList<>();
        for(int i = 0;i < paymentMethods[0].length;i++){
            String[] tmp = paymentMethods[0][i].split(" ");
            DebitCard debitCard = new DebitCard(tmp[0],tmp[1],tmp[2],tmp[3] , Integer.parseInt(tmp[4]));
            methods.add(debitCard);
        }
        for(int i = 0;i < paymentMethods[1].length;i++){
            String[] tmp = paymentMethods[1][i].split(" ");
            MasterCard masterCard = new MasterCard(tmp[0],tmp[1],tmp[2],tmp[3] , Integer.parseInt(tmp[4]));
            methods.add(masterCard);
        }
        for(int i = 0;i < paymentMethods[2].length;i++){
            String[] tmp = paymentMethods[2][i].split(" ");
            PayPal payPal = new PayPal(tmp[0],tmp[1], Integer.parseInt(tmp[2]));
            methods.add(payPal);
        }
        for(int i = 0;i < paymentMethods[3].length;i++){
            String[] tmp = paymentMethods[3][i].split(" ");
            VodafoneCash cash = new VodafoneCash(tmp[0],tmp[1],Integer.parseInt(tmp[2]));
            methods.add(cash);
        }

        //Booking
        ArrayList<Booking> bookings = new ArrayList<>();
        for(int i = 0;i < bookingHistory.length;i++){
            String[] tmp = bookingHistory[i].split(" ");
            Booking curBooking = new Booking(tmp);
            bookings.add(curBooking);
        }

        userTable.put(userName , new myTuple<Client, ArrayList<Booking>,ArrayList<Ticket>,ArrayList<PaymentMethod>>
                                     ( newClient , bookings ,newTickets, methods));

    }
    public static void saveAdmin(String adminName, Admin admin , String AirlineName , String AirlineCode , String airlineLocation){
        adminTable.put(adminName,admin);
        adminOperations.put(adminName , new Airline(AirlineName , AirlineCode ,airlineLocation));
    }
    public static Admin getAdmin(){
        return adminTable.get(curUser);
    }
    public static void updateSchedule(String flightNumber ,String departureDate, String arrivalDate){
        adminOperations.get(curUser).setSchedule(flightNumber,departureDate,arrivalDate);
    }
    public static boolean expandCountOfSeats(String flightNumber,int newCountOfRows , int countOfFirstClass,int countOfBusinessClass ,int countOfEconomyClass){
         return adminOperations.get(flightNumber).expandCountOfSeats(flightNumber,newCountOfRows, countOfFirstClass, countOfBusinessClass, countOfEconomyClass);
    }
    public static ArrayList<Flight> searchFlights(LocalDate fromDate , LocalDate toDate){
        SortedMap<LocalDate, ArrayList<Flight>> resultFlights = searchingTable.subMap(fromDate, true, toDate, true);
        ArrayList<Flight> flights = new ArrayList<>();
        for(var cur : resultFlights.keySet())
            for(Flight flight : resultFlights.get(cur))
                flights.add(flight);

        return flights;
    }
    public static Client getClient(){
        return userTable.get(curUser).getFirst();
    }
    public static String[][] getBookingHistory(){
//        return userTable.get(curUser).getSecond();
        String[][] history = new String[userTable.get(curUser).getSecond().size()][11];
        int i = -1;
        for(var cur : userTable.get(curUser).getSecond()){
            String[] tmp = {cur.getBookingId() , (cur.getBookingStatus() ? "Done" : "Failed") ,
                    String.valueOf(cur.getBookingDate()), cur.getAirlineName() , cur.getFlightNumber() ,
                    cur.getDepartureAirport() , cur.getArrivalAirport() , cur.getDepartureDate() , cur.getArrivalDate() ,
                    String.valueOf(cur.getCountOfSeats()) , String.valueOf(cur.getTotalPrice())};
            history[++i] = tmp;
        }
        return history;
    }

}
