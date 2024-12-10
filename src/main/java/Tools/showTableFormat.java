package Tools;

public class showTableFormat {
    public static void show(String[] titles, String[][] data) {
        int[] columnWidths = new int[titles.length];
        for (int i = 0; i < titles.length; i++)
            columnWidths[i] = titles[i].length();

        for (String[] row : data)
            for (int i = 0; i < row.length; i++)
                columnWidths[i] = Math.max(columnWidths[i], row[i].length());



        StringBuilder headerRow = new StringBuilder("|");
        for (int i = 0; i < titles.length; i++) {
            headerRow.append(" ").append(String.format("%-" + columnWidths[i] + "s", titles[i])).append(" |");
        }
        System.out.println(headerRow); ///First row is header row (titles)

        // Print a divider
        StringBuilder divider = new StringBuilder("+");
        for (int width : columnWidths) {
            divider.append("-".repeat(width + 2)).append("+");
        }
        System.out.println(divider);

        for (String[] row : data) {
            StringBuilder dataRow = new StringBuilder("|");
            for (int i = 0; i < row.length; i++)
                dataRow.append(" ").append(String.format("%-" + columnWidths[i] + "s", row[i])).append(" |");
            System.out.println(dataRow);
        }
    }
}
