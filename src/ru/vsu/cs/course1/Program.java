package ru.vsu.cs.course1;

import java.util.Locale;

import static ru.vsu.cs.course1.DateReader.date;
import static ru.vsu.cs.course1.DateReader.dates;


public class Program {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ROOT);

//        MyDate dateNow = MyDate.now();
//        System.out.println(dateNow);
//
//        dateNow.addDate(MyDate.Day.DAY, -5);
//        System.out.println(dateNow);
//
//        dateNow.setFormat("dd.MMMM.yyyy.ww");
//        System.out.println(dateNow);
//
//        dateNow.addDate(MyDate.Day.MONTH, +12);
//        dateNow.setFormat("d MMM y ww");
//        System.out.println(dateNow);
//
//        dateNow.setFormat("dd");
//        System.out.println(dateNow);
//
//        MyDate dateStart = new MyDate();
////        dateStart.addDate(MyDate.Day.DAY, +13);
//        dateStart.setFormat("dd.MMMM.yyyy.ww");
//        System.out.println(dateStart);
//
        String day = DateReader.readFileAsString();
        System.out.println(day);
        date(day);
        System.out.println(dates);

        MyDate check = new MyDate(2023, 10, 20);
        check.setFormat("dd MMMM yyyy");
        System.out.println(check);

        MyDate check1 = new MyDate(2023, 5, 13);
        check1.setFormat("dd MMMM yyyy");
        System.out.println(check1);

        MyDate check2 = new MyDate(2023, 12, 14);
        check2.setFormat("dd MMMM yyyy");
        System.out.println(check2);

//        System.out.println(MyDate.after(check, dateNow));
//        System.out.println(MyDate.before(check, dateNow));
//        System.out.println(check.getDays()+ " " + dateNow.getDays());
//
//        System.out.println(check.getDays() % 7 +1);
    }
}
