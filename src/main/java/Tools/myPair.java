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

    public myPair() {
        first = null;
        second = null;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public void setPair(myPair<K, V> pair) {
        first = pair.getFirst();
        second = pair.getSecond();
    }

    public V getSecond() {
        return second;
    }
}