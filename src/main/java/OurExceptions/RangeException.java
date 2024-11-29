package OurExceptions;

public class RangeException extends Exception {
    public RangeException(int from, int to) {
        super("( Out of Range ) ! Range from " + from + " to " + to);
    }
}
