package Tools;

import java.util.ArrayList;

public class Menu {
    public static void show(String[] list) {
        for (int i = 1; i <= list.length; ++i)
            System.out.println(i + "- " + list[i - 1]);
    }
}
