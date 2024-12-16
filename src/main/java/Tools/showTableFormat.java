package Tools;

import java.util.ArrayList;

public class showTableFormat {
    public static void show(String tableName, String[] titles, String[][] data) {
        int[] columnWidths = new int[titles.length];
        for (int i = 0; i < titles.length; i++) {
            columnWidths[i] = titles[i].length();
            for (String[] row : data)
                columnWidths[i] = Math.max(columnWidths[i], row[i].length());
        }

        int totalWidth = 1;
        for (int width : columnWidths) {
            totalWidth += width + 3;
        }

        ArrayList<Character> line = createLine(totalWidth);

        printLine(line);
        printCenteredTitle(tableName, totalWidth);
        printLine(line);
        printRow(titles, columnWidths);
        printLine(line);

        for (String[] row : data)
            printRow(row, columnWidths);
        printLine(line);
    }

    private static ArrayList<Character> createLine(int width) {
        ArrayList<Character> line = new ArrayList<>();
        line.add('+');
        for (int i = 1; i < width - 1; i++)
            line.add('-');
        line.add('+');
        return line;
    }

    private static void printLine(ArrayList<Character> line) {
        for (char c : line)
            System.out.print(c);
        System.out.println();
    }

    private static void printCenteredTitle(String title, int width) {
        int padding = (width - title.length() - 2) / 2; // -2 for the '|' characters
        System.out.print('|');
        for (int i = 0; i < padding; i++)
            System.out.print(' ');

        System.out.print(title);
        for (int i = 0; i < width - title.length() - padding - 2; i++)
            System.out.print(' ');

        System.out.println('|');
    }

    private static void printRow(String[] row, int[] columnWidths) {
        System.out.print('|');
        for (int i = 0; i < row.length; i++) {
            System.out.print(' ');
            System.out.print(row[i]);
            int spaces = columnWidths[i] - row[i].length() + 1;
            for (int j = 0; j < spaces; j++)
                System.out.print(' ');

            System.out.print('|');
        }
        System.out.println();
    }
}