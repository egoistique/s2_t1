package ru.vsu.cs.course1;

import java.util.Locale;

import static ru.vsu.cs.course1.DateReader.date;
import static ru.vsu.cs.course1.DateReader.dates;


public class Program {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ROOT);

        MyDate dateNow = MyDate.now();
        System.out.println(dateNow);

        dateNow.addDate(MyDate.Day.DAY, -5);
        System.out.println("Прибавление дней: " + dateNow);

        dateNow.setFormat("dd.MMMM.yyyy.ww");
        System.out.println("Демонстрация работы формата: " + dateNow);

        dateNow.addDate(MyDate.Day.MONTH, +12);
        dateNow.setFormat("d MMM y ww");
        System.out.println("Прибавление месяцев: " + dateNow); //приприбавлении месяцев они учитываются как 31 день

        dateNow.setFormat("dd");
        System.out.println("Демонстрация работы формата: " + dateNow);

        String day = DateReader.readFileAsString();
        System.out.println(day);
        date(day);
        System.out.println("Прочитанные из файла даты: " + dates);

        MyDate check = new MyDate(2022, 10, 13);
        check.setFormat("dd MMMM yyyy ww");
        System.out.println(check);

        MyDate check1 = new MyDate(2022, 11, 1);
        check1.setFormat("dd MMMM yyyy ww");
        System.out.println(check1);

        MyDate check2 = new MyDate(2022, 3, 17);
        check2.setFormat("dd MMMM yyyy ww");
        System.out.println(check2);

        System.out.println(MyDate.after(check, dateNow));
        System.out.println(MyDate.before(check, dateNow));
//        System.out.println(check.getDays()+ " " + dateNow.getDays());
    }
}
