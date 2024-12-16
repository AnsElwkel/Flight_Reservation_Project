package Tools;

import com.egyptFlightReservation.Model.Flight;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;


public class myPair<K, V> {
    private K first;
    private V second;

    public myPair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public static TreeMap<myPair<String, String>, ArrayList<Flight>> getPairArrayListTreeMap() {
        Comparator<myPair<String, String>> pairComparator = new Comparator<myPair<String, String>>() {
            @Override
            public int compare(myPair<String, String> pair1, myPair<String, String> pair2) {
                // Compare first element
                int firstCompare = pair1.getFirst().compareTo(pair2.getFirst());
                if (firstCompare != 0) {
                    return firstCompare;
                }
                // If first elements are the same, compare the second element
                return pair1.getSecond().compareTo(pair2.getSecond());
            }
        };
        TreeMap<myPair<String, String>, ArrayList<Flight>> mp = new TreeMap<>(pairComparator);
        return mp;
    }

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }
}