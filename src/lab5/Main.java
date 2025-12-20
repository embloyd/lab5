package lab5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.println("---Задание 1: Кэширующая дробь---");
        task1(sc);

        System.out.println("--- Задание 2: Структурные шаблоны (Кот и мяуканья) ---");
        task2(sc);


        sc.close();
    }

    private static void task1(Scanner sc) {

        try {
            System.out.println("1. Создаем базовую дробь:");
            System.out.print("Введите числитель: ");
            int num1 = sc.nextInt();
            System.out.print("Введите знаменатель: ");
            int den1 = sc.nextInt();

            BasicFraction basic = new BasicFraction(num1, den1);
            System.out.println("Создана дробь: " + basic);
            System.out.println("Вещественное значение: " + basic.getDecimalValue());

            System.out.println("2. Создаем кэширующую дробь:");
            System.out.print("Введите числитель: ");
            int num2 = sc.nextInt();
            System.out.print("Введите знаменатель: ");
            int den2 = sc.nextInt();

            CachedFraction cached = new CachedFraction(num2, den2);
            System.out.println("Создана дробь: " + cached);

            System.out.println("Демонстрация кэширования:");
            System.out.println("Первый вызов getDecimalValue():");
            double value1 = cached.getDecimalValue();
            System.out.println("Значение: " + value1);
            System.out.println("Кэш валиден? " + cached.isCacheValid());

            System.out.println("Второй вызов getDecimalValue():");
            double value2 = cached.getDecimalValue();
            System.out.println("Значение: " + value2);
            System.out.println("Кэш валиден? " + cached.isCacheValid());

            System.out.println("3. Изменение дроби (инвалидация кэша):");
            System.out.print("Введите новый числитель: ");
            int newNum = sc.nextInt();
            cached.setNumerator(newNum);

            System.out.println("Дробь после изменения: " + cached);
            System.out.println("Кэш валиден? " + cached.isCacheValid());

            System.out.println("Вызов getDecimalValue() после изменения:");
            double newValue = cached.getDecimalValue();
            System.out.println("Значение: " + newValue);
            System.out.println("Кэш валиден? " + cached.isCacheValid());


            System.out.println("4. Обработка отрицательных значений:");
            BasicFraction negative1 = new BasicFraction(3, -4);
            System.out.println("Дробь 3/-4: " + negative1);

            BasicFraction negative2 = new BasicFraction(-2, -3);
            System.out.println("Дробь -2/-3: " + negative2);

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        sc.nextLine(); // Очистка буфера
    }

    private static void task2(Scanner sc) {
        System.out.println("1. Демонстрация класса Cat");

        System.out.print("Введите имя кота для демонстрации: ");
        String catName = sc.nextLine();

        Cat cat1 = new Cat(catName);
        System.out.println("Создан: " + cat1.toString());

        System.out.print("Сколько раз мяукнуть? ");
        int meowCount = sc.nextInt();
        sc.nextLine(); //очистка буфера

        System.out.println("Кот мяукает " + meowCount + " раз:");
        for (int i = 0; i < meowCount; i++) {
            cat1.meow();
        }

        System.out.println("2. Подсчет мяуканий (декоратор) ");

        System.out.print("Введите имя кота для задания 2: ");
        String catName2 = sc.nextLine();

        Cat cat2 = new Cat(catName2);
        System.out.println("Создан: " + cat2.toString());

        //Создаем декоратор-счетчик
        MeowCounter counter = new MeowCounter(cat2);

        System.out.println("Выполняем код из задания:");
        System.out.println("Meowable m = new MeowCounter(new Cat(\"" + catName2 + "\"));");
        System.out.println("Funs.meowsCare(m);");
        System.out.println("System.out.println(...) // вывод количества мяуканий");

        System.out.println("Результат выполнения:");

        //Создаем "мяукающего" через счетчик
        Meowable m = counter;

        //Вызываем метод meowsCare
        Funs.meowsCare(m);

        //Выводим результат
        System.out.println("кот мяукал " + counter.getMeowCount() + " раз");

        sc.close();
    }

}