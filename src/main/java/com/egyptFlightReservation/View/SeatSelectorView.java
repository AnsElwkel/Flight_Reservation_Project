package com.egyptFlightReservation.View;

import com.egyptFlightReservation.Model.Seat.Seat;

import java.util.ArrayList;

/**
 * showMap ->
 *
 * First class from 1 to 3
* Second class from 4 to 10
* Economy class from 11 to 15
*
*    1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
* A  O  O  O  O  O  O  O  O  O  O  O  O  O  O  O
* B  O  O  O  O  O  O  O  O  O  O  O  O  O  O  O
* C  O  O  O  O  O  O  O  O  O  O  O  O  O  O  O
*
* D  O  O  O  X  O  O  O  O  O  O  O  O  O  O  O
* E  O  O  O  O  O  O  O  O  O  O  O  O  O  O  O
* F  O  O  O  O  O  O  O  O  O  O  O  O  O  O  O
 *
 * showInfoOfEachClass->
 * to show detail of seat and price
 * seats[][].displayAdvantage() first
 * seats[][].displayAdvantage() business
 * seats[][].displayAdvantage() economy
 *
 * selectSeat ->
 * enter the row character and column number to select the seat or -1 to cancel
 *
 */
public class SeatSelectorView {
    public SeatSelectorView() { }

    public void showMap(ArrayList<ArrayList<Seat>> seats){

        /**Map Problem
         * first x colmun is first class
         * second y colmun is business class
         * third z colmun is economy class
         *
         *
         * rows must be even number to divisible by 2
         * and _MAX_ROWS_ = 10 and _MIN_ROW_ = 4
         *
         * _MAX_COLUMN_ = 26 and  _MIN_COLMUN_ = 10
         *
         * First class from 1 to 3
         * Second class from 4 to 10
         * Economy class from 11 to 15
         *
         *    1  2  3  4  5  6  7  8  9 10 11 12 13 14 15
         * A  O  O  O  O  O  O  O  O  O  O  O  O  O  O  O
         * B  O  O  O  O  O  O  O  O  O  O  O  O  O  O  O
         * C  O  O  O  O  O  O  O  O  O  O  O  O  O  O  O
         *
         * D  O  O  O  X  O  O  O  O  O  O  O  O  O  O  O
         * E  O  O  O  O  O  O  O  O  O  O  O  O  O  O  O
         * F  O  O  O  O  O  O  O  O  O  O  O  O  O  O  O
         * */
        //

        for(var i : seats){
            for(Seat j : i)
                System.out.print((j.isAvailabilityStatus() ? "O" : "X") + " " );
            System.out.println();
        }
    }


}
