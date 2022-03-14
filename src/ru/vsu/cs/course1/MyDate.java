package ru.vsu.cs.course1;

import java.time.LocalDate;

public class MyDate {
    public enum Day {
        DAY(1), WEEK(7), MONTH(31), YEAR(365);

        private int days;

        public int getDays() {
            return days;
        }

        Day(int days) {
            this.days = days;
        }
    }

    public enum Month {
        JANUARY("January", 31), FEBRUARY("February", 28), MARCH("March", 31), APRIL("April", 30),
        MAY("May", 31), JUNE("June", 30), JULY("July", 31), AUGUST("August", 31), SEPTEMBER("September", 30),
        OCTOBER("October", 31), NOVEMBER("November", 30), DECEMBER("December", 31);

        private int days;
        private String name;

        public int getDays() {
            return days;
        }

        Month(String name, int days) {
            this.days = days;
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("%s", this.name);
        }
    }

    public enum Week{
        MONDAY("Monday "), TUESDAY("Tuesday"), WEDNESDAY( "Wednesday"), THURSDAY("Thursday"),
        FRIDAY("Friday"), SATURDAY("Saturday"), SUNDAY("Sunday");

        private String name;

        Week(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return String.format("%s", this.name);
        }
    }

    private Format format = new Format("dd.MM.yyyy");

    private int days;

    public int getDays() {
        return days;
    }

    MyDate(int year, int month, int day) {
        parseDate(year, month, day);
    }

    MyDate() {
        this(1, 1, 1);
    }

    public static MyDate now() {
        LocalDate date = LocalDate.now();
        return new MyDate(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
    }

    public void setFormat(String pattern) {
        this.format = new Format(pattern);
    }

    private void checkArguments(int year, int month, int day) {
        if (year <= 0) {
            throw new IllegalArgumentException("incorrect argument [year]...");
        } else if (month <= 0 || month > Month.values().length) {
            throw new IllegalArgumentException("incorrect argument [month]...");
        } else {
            int daysToMonth = Month.values()[month - 1].getDays();
            if (isLeapYear(year) && month == 2) {
                daysToMonth++;
            }
            if (day <= 0 || day > daysToMonth) {
                throw new IllegalArgumentException("incorrect argument [day]...");
            }
        }
    }

    private String parseDaysToString1() {
        int year = this.days / Day.YEAR.getDays();
        year -= countLeapYears(1, year) / Day.YEAR.getDays();
        int yearOnRet;
        if (year >= 1918) {
            this.days += 13;
        }
        int daysDiv = this.days - ((year) * Day.YEAR.getDays() + countLeapYears(1, year));
        if (daysDiv < 0) {
            daysDiv = this.days - ((year - 1) * Day.YEAR.getDays() + countLeapYears(1, year - 1));
            yearOnRet = year - 1;
        } else {
            yearOnRet = year;
        }
        int flagDays = 0;
        int[] daysMonths1 = new int[Month.values().length];
        for (int i = 0; i < daysMonths1.length; i++) {
            daysMonths1[i] = Month.values()[i].getDays();
            if (i == 1 && isLeapYear(year)) {
                daysMonths1[i]++;
            }
        }
        int monthCount1 = 0;
        for (int days : daysMonths1) {
            if (flagDays + days <= daysDiv) {
                flagDays += days;
                monthCount1++;
            } else {
                break;
            }
        }
        int day = daysDiv - flagDays ;
        int dayWeek = (day % 7 == 0) ? 7 : (day % 7 + 1);
        if (yearOnRet == year - 1) {
            day++;
        }
        return this.format.parse(yearOnRet, monthCount1 + 1, day, dayWeek);
    }

    private String parseDaysToString() {
        int year = this.days / Day.YEAR.getDays() + 1;
        year -= countLeapYears(1, year) / Day.YEAR.getDays();
        int daysDiv = this.days - ((year - 1) * Day.YEAR.getDays() + countLeapYears(1, year - 1));
        int flagDays = 0;
        int[] daysMonths = new int[Month.values().length];
        for (int i = 0; i < daysMonths.length; i++) {
            daysMonths[i] = Month.values()[i].getDays();
            if (i == 1 && isLeapYear(year)) {
                daysMonths[i]++;
            }
        }
        int monthCount = 1;
        for (int days : daysMonths) {
            if (flagDays + days <= daysDiv) {
                flagDays += days;
                monthCount++;
            } else {
                break;
            }
        }
        int day = daysDiv - flagDays + 1;
        int dayWeek = (day % 7 == 0) ? 7 : (day % 7 + 1);
        return monthCount < 9 && monthCount != 1 ? this.format.parse(year, monthCount, day, dayWeek) : parseDateSepDec();
    }

    private String parseDateSepDec(){
            int year1 = this.days / Day.YEAR.getDays() + 1;
            year1 -= countLeapYears(1, year1) / Day.YEAR.getDays();
//            if (year1 >= 1918) {
//                this.days += 13;
//            }
            int daysDiv1 = this.days - ((year1 - 2) * Day.YEAR.getDays() + countLeapYears(1, year1 - 2));
            int flagDays1 = 0;
            int[] daysMonths2 = new int[Month.values().length];
            for (int i = 0; i < daysMonths2.length; i++) {
                daysMonths2[i] = Month.values()[i].getDays();
                if (i == 1 && isLeapYear(year1)) {
                    daysMonths2[i]++;
                }
            }
            int monthCount2 = 1;
            for (int days : daysMonths2) {
                if (flagDays1 + days <= daysDiv1) {
                    flagDays1 += days;
                    monthCount2++;
                } else {
                    break;
                }
            }
            int day1 = daysDiv1 - flagDays1 + 2;
            int dayWeek1 = (day1 % 7 == 0) ? 7 : (day1 % 7 + 1);
            return this.format.parse(year1 - 1, monthCount2, day1, dayWeek1);
    }

    private boolean isLeapYear(int year) { //определение високосного года
        return year > 0 && year % 4 == 0 && (year % 100 != 0 || (year % 100 == 0 && year % 400 == 0));
    }

    private int countLeapYears(int start, int end) { // счет кол-ва високосных лет в диапазоне
        int count = 0;
        for (int year = start; year <= end; year++) {
            if (isLeapYear(year)) {
                count++;
            }
        }
        return count;
    }

    private void parseDate(int year, int month, int day) {
        checkArguments(year, month, day);
        this.days += (year - 1) * Day.YEAR.getDays() + countLeapYears(1, year - 1);
        for (int i = 0; i < month - 1; i++) {
            this.days += Month.values()[i].getDays();
        }
        if (isLeapYear(year) && month - 1 >= 2) {
            this.days++;
        }
        this.days += --day * Day.DAY.getDays();
    }

    public void addDate(Day type, int count) {
        this.days += type.getDays() * count;
    }

    public static boolean after(MyDate one, MyDate two){
        return (one.getDays() > two.getDays());
    }
    public static boolean before(MyDate one, MyDate two){
        return (one.getDays() < two.getDays());
    }

    @Override
    public String toString() {
        return parseDaysToString();
    }

    private class Format {
        private String[] patterns;
        private char prefix;

        Format(String pattern) {
            getFormat(pattern);
        }

        private void getFormat(String pattern) {
            String[] formats;
            char prefix;
            if (pattern != null) {
                pattern = pattern.trim();
                prefix = getPrefix(pattern);
                if (prefix != 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int index = 0; index < pattern.length(); index++) {
                        if (pattern.charAt(index) != prefix) {
                            sb.append(pattern.charAt(index));
                        } else {
                            sb.append("\n");
                        }
                    }
                    formats = sb.toString().split("\n");
                    for (String format : formats) {
                        if (!checkSymbols(format)) {
                            throw new IllegalArgumentException("incorrect pattern format");
                        }
                    }
                } else {
                    if (checkSymbols(pattern)) {
                        formats = new String[]{pattern};
                    } else {
                        throw new IllegalArgumentException("incorrect pattern format");
                    }
                }
            } else {
                throw new IllegalArgumentException("incorrect pattern format");
            }
            this.prefix = prefix;
            this.patterns = formats;
        }

        private boolean checkSymbols(String symbols) {
            return symbols.chars().distinct().count() == 1;
        }

        private char getPrefix(String pattern) {
            char prefix = 0;
            pattern = pattern.trim().replace("y", "").replaceAll("M", "").
                    replaceAll("d", "").replaceAll("w", "");
            if (pattern.length() > 0 && checkSymbols(pattern)) {
                prefix = pattern.charAt(0);
            }
            return prefix;
        }


        private String getDay(String pattern, int day) {
            String result = "";
            if (pattern.equals("d")) {
                result = String.format("%d", day);
            } else {
                result = String.format("%s%d", day < 10 ? "0" : "", day);
            }
            return result;
        }

        private String getMonth(String pattern, int month) {
            String result = "";
            switch (pattern.length()) {
                case 1:
                    result = String.format("%d", month);
                    break;
                case 2:
                    result = String.format("%s%d", month < 10 ? "0" : "", month);
                    break;
                case 3:
                    result = String.format("%s", Month.values()[month - 1].toString().substring(0, 3).toLowerCase());
                    break;
                default:
                    result = String.format("%s", Month.values()[month - 1].toString());
            }
            return result;
        }

        private String correctYear(int year) {
            StringBuilder sb = new StringBuilder();
            for (int count = 0; count < 4; count++) {
                sb.append(year % 10);
                year /= 10;
            }
            return sb.reverse().toString();
        }

        private String getYear(String pattern, int year) {
            String result = "";
            switch (pattern.length()) {
                case 1:
                    result = String.format("%s", correctYear(year));
                    break;
                case 2:
                    result = String.format("%s", correctYear(year).substring(2, 4));
                    break;
                default:
                    result = String.format("%s", correctYear(year));
            }
            return result;
        }

        private String getDayWeek(String pattern, int dayWeek){
            String result = "";
            switch (pattern.length()) {
                case 1:
                    result = String.format("%s", Week.values()[dayWeek - 1].toString().substring(0, 3).toLowerCase());
                    break;
                default:
                    result = String.format("%s", Week.values()[dayWeek - 1].toString());
            }
            return result;
        }

        private void checkArguments(String arg) {
            if (arg.charAt(0) == 'd' && arg.length() > 2) {
                throw new IllegalArgumentException("incorrect format day");
            } else if (arg.charAt(0) == 'M' && arg.length() > 4) {
                throw new IllegalArgumentException("incorrect format month");
            } else if (arg.charAt(0) == 'y' && (arg.length() == 3 || arg.length() > 4)) {
                throw new IllegalArgumentException("incorrect format year");
            } else if (arg.charAt(0) == 'w' && (arg.length() > 3)) {
            throw new IllegalArgumentException("incorrect format week day");
            }
        }

        public String parse(int year, int month, int day, int dayWeek) {
            StringBuilder sb = new StringBuilder();
            for (String arg : this.patterns) {
                checkArguments(arg);
                if (arg.charAt(0) == 'd') {
                    sb.append(getDay(arg, day)).append(this.prefix);
                } else if (arg.charAt(0) == 'y') {
                    sb.append(getYear(arg, year)).append(this.prefix);
                } else if (arg.charAt(0) == 'M'){
                    sb.append(getMonth(arg, month)).append(this.prefix);
                } else {
                    sb.append(getDayWeek(arg, dayWeek)).append(this.prefix);
                }
            }
            return sb.delete(sb.length() - 1, sb.length()).toString();
        }
    }

}