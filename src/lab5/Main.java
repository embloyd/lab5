package lab5;


import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.println("---Задание 1: Кэширующая дробь---");
        task1(sc);

        System.out.println("--- Задание 2: Структурные шаблоны ---");
        task2(sc);

        System.out.println("--- Задание 3: Общие элементы списков ---");
        task3(sc);

        System.out.println("--- Задание 4: Мап ---");
        task4FromFile(sc);

        System.out.println("--- Задание 5: Сет---");
        task5();

        sc.close();




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


    }

    private static void task3(Scanner sc) {
        System.out.println("Поиск общих элементов в двух списках");

        System.out.println("\nСоздание списка L1");
        System.out.print("Введите числа через пробел: ");
        List<Integer> L1 = readIntegerList(sc);

        System.out.println("\nСоздание списка L2");
        System.out.print("Введите числа через пробел: ");
        List<Integer> L2 = readIntegerList(sc);

        try {
            IntersectionList<Integer> intersection = new IntersectionList<>(L1, L2);
            List<Integer> common = intersection.getResult();

            System.out.println("\nРезультаты");
            System.out.println("Список L1: " + L1);
            System.out.println("Список L2: " + L2);
            System.out.println("Общие элементы (список L): " + common);
            System.out.println("Количество общих элементов: " + common.size());

            if (common.isEmpty()) {
                System.out.println("Общих элементов не найдено.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void task4FromFile(Scanner sc) {

        String fileName = "src/input.txt";

        System.out.println("Чтение данных из файла: " + fileName);

        List<Student> students = readStudentsFromFile(fileName);

        if (students == null || students.isEmpty()) {
            System.out.println("Ошибка: не удалось прочитать данные.");
            return;
        }

        // Сортируем по баллам (от большего к меньшему)
        students.sort((s1, s2) -> s2.getScore() - s1.getScore());

        // Находим максимальный балл
        int maxScore = students.get(0).getScore();

        // Считаем, сколько учеников набрали максимальный балл
        int count = 0;
        for (Student s : students) {
            if (s.getScore() == maxScore) {
                count++;
            } else {
                break;
            }
        }

        // Вывод результата
        System.out.println("Результаты:");

        if (count > 2) {
            System.out.println(count);
        } else if (count == 2) {
            System.out.println(students.get(0).getFullName());
            System.out.println(students.get(1).getFullName());
        } else if (count == 1) {
            System.out.println(students.get(0).getFullName());
        }
    }

    private static void task5() {
        String fileName = "src/text.txt";

        System.out.println("Чтение файла: " + fileName);

        try {
            // 1. Читаем текст из файла
            String text = readTextFromFile(fileName);
            if (text == null || text.isEmpty()) {
                System.out.println("Файл пуст или не найден.");
                return;
            }

            System.out.println("Исходный текст:");
            System.out.println(text);

            // 2. Получаем нечетные слова
            List<String> oddWords = getOddWords(text);
            System.out.println("\nНечетные слова (1, 3, 5...): " + oddWords);

            // 3. Находим общие глухие согласные
            Set<Character> commonVoiceless = findCommonVoicelessConsonants(oddWords);

            // 4. Выводим результат
            System.out.println("Результат:");
            if (commonVoiceless.isEmpty()) {
                System.out.println("Нет общих глухих согласных в нечетных словах.");
            } else {
                System.out.println("Глухие согласные, которые есть в каждом нечетном слове:");
                System.out.println(commonVoiceless);
            }

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    // Чтение текста из файла
    private static String readTextFromFile(String fileName) throws IOException {
        StringBuilder text = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append(" ");
            }
        }

        return text.toString().trim();
    }

    // Получение нечетных слов (1, 3, 5...)
    private static List<String> getOddWords(String text) {
        List<String> words = new ArrayList<>();

        // Разбиваем текст на слова (учитывая русские буквы)
        String[] allWords = text.toLowerCase().split("[^а-яёА-ЯЁ]+");

        // Берем только нечетные по порядку (индексы 0, 2, 4...)
        for (int i = 0; i < allWords.length; i += 2) {
            if (!allWords[i].isEmpty()) {
                words.add(allWords[i]);
            }
        }

        return words;
    }

    // Нахождение общих глухих согласных
    private static Set<Character> findCommonVoicelessConsonants(List<String> words) {
        if (words.isEmpty()) {
            return new TreeSet<>();
        }

        // Множество глухих согласных русского языка
        Set<Character> voicelessConsonants = new HashSet<>(Arrays.asList(
                'п', 'ф', 'к', 'т', 'ш', 'с', 'х', 'ц', 'ч', 'щ'
        ));

        // Начинаем со всеми глухими согласными
        Set<Character> commonConsonants = new HashSet<>(voicelessConsonants);

        // Для каждого слова находим пересечение
        for (String word : words) {
            Set<Character> wordConsonants = new HashSet<>();

            // Находим все глухие согласные в этом слове
            for (char c : word.toCharArray()) {
                if (voicelessConsonants.contains(c)) {
                    wordConsonants.add(c);
                }
            }

            // Пересечение с уже найденными общими
            commonConsonants.retainAll(wordConsonants);

            // Если уже пусто, дальше можно не проверять
            if (commonConsonants.isEmpty()) {
                break;
            }
        }

        // Сортируем в алфавитном порядке
        return new TreeSet<>(commonConsonants);
    }


    // для 4
    private static List<Student> readStudentsFromFile(String fileName) {
        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Читаем количество учеников
            String line = br.readLine();
            int n = Integer.parseInt(line.trim());

            // Читаем данные каждого ученика
            for (int i = 0; i < n; i++) {
                line = br.readLine();
                if (line == null) break;

                String[] parts = line.trim().split("\\s+");
                if (parts.length != 4) continue;

                String lastName = parts[0];
                String firstName = parts[1];
                int school = Integer.parseInt(parts[2]);
                int score = Integer.parseInt(parts[3]);

                // Берем только школу №50
                if (school == 50) {
                    students.add(new Student(lastName, firstName, school, score));
                }
            }

            System.out.println("Прочитано учеников из школы №50: " + students.size());

        } catch (Exception e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return null;
        }

        return students;
    }

    private static void analyzeSchool50(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("Нет учеников из школы №50.");
            return;
        }

        // Сортируем по баллам (по убыванию)
        students.sort((s1, s2) -> s2.getScore() - s1.getScore());

        int highestScore = students.get(0).getScore();

        // Считаем учеников с высшим баллом
        int topScoreCount = 0;
        for (Student student : students) {
            if (student.getScore() == highestScore) {
                topScoreCount++;
            } else {
                break;
            }
        }

        // Вывод согласно условию
        System.out.println("Результаты школы №50:");
        System.out.println("Высший балл: " + highestScore);

        if (topScoreCount > 2) {
            // Случай 1: Более 2 учеников с высшим баллом
            System.out.println(topScoreCount);
        }
        else if (topScoreCount == 1) {
            // Случай 2: Один лучший ученик
            System.out.println(students.get(0).getFullName());
        }
        else if (topScoreCount == 2) {
            // Случай 3: Два лучших ученика
            System.out.println(students.get(0).getFullName());
            System.out.println(students.get(1).getFullName());
        }

        // Дополнительная информация (для наглядности)
        System.out.println("Топ-5 учеников школы №50");
        for (int i = 0; i < Math.min(5, students.size()); i++) {
            System.out.printf("%d. %s - %d баллов%n",
                    i + 1,
                    students.get(i).getFullName(),
                    students.get(i).getScore());
        }
    }

    //для 3
    private static List<Integer> readIntegerList(Scanner scanner) {
        String line = scanner.nextLine().trim();
        List<Integer> list = new ArrayList<>();

        if (line.isEmpty()) {
            return list;
        }

        String[] parts = line.split("\\s+");
        for (String part : parts) {
            try {
                int num = Integer.parseInt(part);
                list.add(num);
            } catch (NumberFormatException e) {
                System.out.println("Предупреждение: '" + part + "' не является целым числом и будет пропущено.");
            }
        }

        return list;
    }


}