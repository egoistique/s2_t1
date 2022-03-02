package ru.vsu.cs.course1;

import java.util.Locale;

import static ru.vsu.cs.course1.DateReader.date;


public class Program {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ROOT);

        MyDate dateNow = MyDate.now();
        System.out.println(dateNow);

        dateNow.addDate(MyDate.Day.DAY, -5);
        System.out.println(dateNow);

        dateNow.setFormat("dd.MMMM.yyyy");
        System.out.println(dateNow);

        dateNow.addDate(MyDate.Day.YEAR, +1);
        dateNow.setFormat("d MMM y");
        System.out.println(dateNow);

        dateNow.setFormat("dd");
        System.out.println(dateNow);

        MyDate dateStart = new MyDate();
        System.out.println(dateStart);

        String day = DateReader.readFileAsString();
        System.out.println(day);
        System.out.println(date(day));

        MyDate check = new MyDate(2020, 7, 14);
        check.setFormat("dd MMMM yy");
        System.out.println(check);

        System.out.println(MyDate.after(check, dateNow));
        System.out.println(MyDate.before(check, dateNow));
        System.out.println(check.getDays()+ " " + dateNow.getDays());
    }
}
