package ru.vsu.cs.course1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DateReader {
    static String readFileAsString() throws Exception {
        String date = "";
        date = new String(Files.readAllBytes(Paths.get("Text.txt")));
        return date;
    }

    private static final String[] months = new String[]{
            "января",
            "февраля",
            "марта",
            "апреля",
            "мая",
            "июня",
            "июля",
            "августа",
            "сентября",
            "октября",
            "ноября",
            "декабря"
    };

    public static void clearWords(String[] words) {

        StringBuilder z = new StringBuilder();
        for (int l = 0; l < words.length; l++) {

            z.append(words[l]);
            if (z.charAt(z.length() - 1) == ',' || z.charAt(z.length() - 1) == '.') {
                z.delete(z.length() - 1, z.length());
                words[l] = z.toString();
            }
            z.delete(0, z.length());
        }
    }

    public static List<String> date(String text) {
        List<String> res = new ArrayList<>();
        String[] words = text.strip().split(" ");
        clearWords(words);

        for (int i = 0; i < words.length; i++) {

            if (isDate1(words[i]))
                res.add(words[i]);
            if (i < words.length - 2) {
                if (isDate2(words[i], words[i + 1], words[i + 2])) {
                    String[] arr = new String[]{words[i], words[i + 1], words[i + 2]};
                    res.add(String.join(" ", arr));
                }

            }
        }
        return res;
    }

    public static boolean isDate1(String word) {
        String[] dot = word.split("\\.");
        if (dot.length == 3) {
            if (dot[0].length() == 2 && dot[1].length() == 2 && dot[2].length() == 4) {
                try {
                    int day = Integer.parseInt(dot[0]);
                    int month = Integer.parseInt(dot[1]);
                    int year = Integer.parseInt(dot[2]);
                    if (month < 13 && month > 0)
                        if (day > 0 && day <= count(month))
                            return true;
                } catch (Exception ignored) {

                }
            }
        }

        return false;
    }

    public static boolean isDate2(String day, String month, String year) {
        try {
            int days = Integer.parseInt(day);
            int years = Integer.parseInt(year);
            int months = 1;
            for (String m : DateReader.months) {
                if (m.equals(month.toLowerCase())) {
                    break;
                }
                months++;
            }
            if (months < 13 && months > 0 && years > 0 && years < 10000)
                if (days > 0 && days <= count(months))
                    return true;
        } catch (Exception ignored) {

        }

        return false;
    }

    public static int count(int m) {
        return 28 + (m + m / 8) % 2 + 2 % m + 2 * (1 / m); //формула для рассчитывания кол-ва дней в любом месяце
    }
}
