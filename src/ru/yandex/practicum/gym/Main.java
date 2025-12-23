package ru.yandex.practicum.gym;

import java.io.PrintStream;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner;
    private static final Timetable timetable;

    public static void main(String[] args) {
        initializeSampleData();
        System.out.println("Вас приветствует Gym master.");
        boolean exit = false;

        while(!exit) {
            System.out.println("\nЧто вы хотите сделать?");
            System.out.println("1. Посмотреть расписание на неделю");
            System.out.println("2. Добавить новую тренировку");
            System.out.println("3. Посмотреть расписание за конкретный день");
            System.out.println("4. Показать количество занятий каждого тренера");
            System.out.println("5. Выйти");
            System.out.print("Введите номер команды: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    showWeekSchedule();
                    break;
                case 2:
                    addNewTraining();
                    break;
                case 3:
                    showScheduleForSelectedDay();
                    break;
                case 4:
                    showCoachTrainingCounts();
                    break;
                case 5:
                    exit = true;
                    System.out.println("До свидания!");
                    break;
                default:
                    System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }

        scanner.close();
    }

    private static void initializeSampleData() {
        Coach c1 = new Coach("Иванов", "Иван", "Иванович");
        Coach c2 = new Coach("Петров", "Пётр", "Петрович");
        Group g1 = new Group("Дети 8-10", Age.CHILD, 60);
        Group g2 = new Group("Взрослые", Age.ADULT, 90);
        timetable.addNewTrainingSession(new TrainingSession(g1, c1, DayOfWeek.MONDAY, new TimeOfDay(9, 0)));
        timetable.addNewTrainingSession(new TrainingSession(g2, c2, DayOfWeek.MONDAY, new TimeOfDay(10, 30)));
        timetable.addNewTrainingSession(new TrainingSession(g1, c1, DayOfWeek.MONDAY, new TimeOfDay(18, 0)));
        timetable.addNewTrainingSession(new TrainingSession(g2, c2, DayOfWeek.TUESDAY, new TimeOfDay(9, 0)));
    }

    private static void showWeekSchedule() {
        for(DayOfWeek day : DayOfWeek.values()) {
            List<TrainingSession> sessions = timetable.getTrainingSessionsForDay(day);
            System.out.println("\n" + String.valueOf(day) + ":");
            if (sessions.isEmpty()) {
                System.out.println("  Нет занятий");
            } else {
                sessions.forEach((s) -> System.out.println("  " + String.valueOf(s)));
            }
        }

    }

    private static void showScheduleForSelectedDay() {
        System.out.println("Выберите день недели:");

        for(int i = 0; i < DayOfWeek.values().length; ++i) {
            System.out.println(i + 1 + ". " + String.valueOf(DayOfWeek.values()[i]));
        }

        int dayNum = scanner.nextInt();
        if (dayNum >= 1 && dayNum <= 7) {
            DayOfWeek day = DayOfWeek.values()[dayNum - 1];
            List<TrainingSession> sessions = timetable.getTrainingSessionsForDay(day);
            System.out.println("\nРасписание на " + String.valueOf(day) + ":");
            if (sessions.isEmpty()) {
                System.out.println("Нет занятий");
            } else {
                PrintStream var10001 = System.out;
                Objects.requireNonNull(var10001);
                sessions.forEach(var10001::println);
            }

        } else {
            System.out.println("Неверный день");
        }
    }

    private static void addNewTraining() {
        try {
            System.out.print("Название группы: ");
            String title = scanner.nextLine();
            Group group = new Group(title, Age.ADULT, 60);
            System.out.print("Фамилия тренера: ");
            String surname = scanner.nextLine();
            System.out.print("Имя: ");
            String name = scanner.nextLine();
            System.out.print("Отчество: ");
            String middle = scanner.nextLine();
            Coach coach = new Coach(surname, name, middle);
            System.out.println("День недели:");

            for(int i = 0; i < DayOfWeek.values().length; ++i) {
                System.out.println(i + 1 + ". " + String.valueOf(DayOfWeek.values()[i]));
            }

            int dayNum = scanner.nextInt();
            DayOfWeek day = DayOfWeek.values()[dayNum - 1];
            System.out.print("Время (чч:мм): ");
            String timeStr = scanner.next();
            String[] parts = timeStr.split(":");
            TimeOfDay time = new TimeOfDay(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            timetable.addNewTrainingSession(new TrainingSession(group, coach, day, time));
            System.out.println("Тренировка добавлена!");
        } catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }

    }

    private static void showCoachTrainingCounts() {
        List<CounterOfTrainings> counts = timetable.getCountByCoaches();
        System.out.println("\nЗанятия по тренерам:");
        if (counts.isEmpty()) {
            System.out.println("Нет занятий");
        } else {
            for(CounterOfTrainings c : counts) {
                PrintStream var10000 = System.out;
                String var10001 = String.valueOf(c.getCoach());
                var10000.println(var10001 + " — " + c.getCount() + " занятий");
            }
        }

    }

    static {
        scanner = new Scanner(System.in);
        timetable = new Timetable();
    }
}