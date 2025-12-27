# Мурай Анастасия ИТ-3 Лабораторная №5
# Задание 1
## Задача 1
### Текст задачи
В	класс	Дробь,	добавить	интерфейс	на	два	метода:	получение	вещественного	значения,	установка	
числителя	и	установка	знаменателя.	
Сгенерировать	такую	версию	дроби,	которая	будет	кэшировать	вычисление	вещественного	
значения.	
Если	раннее	в	вашем	варианте	не	было	Дроби,	то	создайте	сущность Дробь	со	следующими	
особенностями:	
• Имеет	числитель:	целое	число	
• Имеет	знаменатель:	целое	число	
• Дробь	может	быть	создана	с	указанием	числителя	и	знаменателя		
• Может	вернуть	строковое	представление	вида	“числитель/знаменатель”	
• Необходимо корректно обрабатывать отрицательные значения. Учтите, что знаменатель не может 
быть отрицательным. 	
• Переопределите	метод	сравнения	объектов	по	состоянию	таким	образом,	чтобы	две	дроби	
считались	одинаковыми	тогда,	когда	у	них	одинаковые	значения	числителя	и	знаменателя.
### Алгоритм решения
Создала интерфейс FractionInterface.java
```java
package lab5;

public interface FractionInterface {

    double getDecimalValue();

    void setNumerator(int numerator);

    void setDenominator(int denominator);

    int getNumerator();

    int getDenominator();

    String toString();
}
```
Создала класс BasicFraction.java (базовая реализация)
```java
package lab5;

//Базовая реализация дроби
public class BasicFraction implements FractionInterface {
    protected int numerator;
    protected int denominator;

    public BasicFraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть 0");
        }

        //Обработка отрицательных значений
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    //Сокращение дроби
    private void simplify() {
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;
    }

    //НОД
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    @Override
    public double getDecimalValue() {
        return (double) numerator / denominator;
    }

    @Override
    public void setNumerator(int numerator) {
        this.numerator = numerator;
        simplify();
    }

    @Override
    public void setDenominator(int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть 0");
        }

        if (denominator < 0) {
            this.numerator = -this.numerator;
            denominator = -denominator;
        }

        this.denominator = denominator;
        simplify();
    }

    @Override
    public int getNumerator() {
        return numerator;
    }

    @Override
    public int getDenominator() {
        return denominator;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BasicFraction other = (BasicFraction) obj;
        return numerator == other.numerator && denominator == other.denominator;
    }

    @Override
    public int hashCode() {
        return 31 * numerator + denominator;
    }
}
```
Создала класс CachedFraction.java (кэширующая версия)
```java
package lab5;

//Кэширующая версия дроби
public class CachedFraction extends BasicFraction {
    private Double cachedValue;  //кэшированное значение
    private boolean cacheValid;

    public CachedFraction(int numerator, int denominator) {
        super(numerator, denominator);
        this.cacheValid = false;
    }

    @Override
    public double getDecimalValue() {
        if (!cacheValid) {
            cachedValue = (double) numerator / denominator;
            cacheValid = true;
        }
        return cachedValue;
    }

    @Override
    public void setNumerator(int numerator) {
        super.setNumerator(numerator);
        cacheValid = false;  //инвалидируем кэш
    }

    @Override
    public void setDenominator(int denominator) {
        super.setDenominator(denominator);
        cacheValid = false;  //инвалидируем кэш
    }

    // Дополнительный метод для проверки состояния кэша
    public boolean isCacheValid() {
        return cacheValid;
    }
}
```

# Задание 2
## Задача 1
### Текст задачи
Необходимо воспользоваться классом Кот и методом принимающим всех мяукающих из задачи 2.5.4. 
Необходимо таким образом передать кота в указанный метод, что бы после окончания его работы 
узнать сколько раз мяукал кот за время его работы. На рисунке показан пример работы. Перед вызовом 
метода создаем кота, отправляем ссылку на кота в метод, после окончания его работы выводим 
количество мяуканий на экран. Кота изменять нельзя. 
 
Если	раннее	в	вашем	варианте	не	было	Кота,	то	создайте		
1. сущность	Кот,	которая	описывается	следующим	образом:	
• Имеет	Имя	(строка)	
• Для	создания	необходимо	указать	имя	кота.	
• Может	быть	приведен	к	текстовой	форме	вида:	“кот:	Имя”	
• Может	помяукать,	что	приводит	к	выводу	на	экран	следующего	текста:	“Имя:	мяу!”,	
вызвать	мяуканье	можно	без	параметров.	
2. интерфейс	Мяуканье: разработайте метод, который принимает набор объектов способных 
мяукать и вызывает мяуканье у каждого объекта. Мяукающие объекты должны иметь метод со 
следующей сигнатурой: 
public void meow();
### Алгоритм решения
Создала класс  Meowable.java (интерфейс)
```java
package lab5;

public interface Meowable {
    void meow();
}
```
Создала Cat.java (Класс Кот)
```java
package lab5;

public class Cat implements Meowable {
    private String name;

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public void meow() {
        System.out.println(name + ": мяу!");
    }

    @Override
    public String toString() {
        return "кот: " + name;
    }

    public String getName() {
        return name;
    }
}
```
Создала MeowCounter.java (декоратор-счетчик)
```java
package lab5;

public class MeowCounter implements Meowable {
    private Meowable meowable;
    private int meowCount = 0;

    public MeowCounter(Meowable meowable) {
        this.meowable = meowable;
    }

    @Override
    public void meow() {
        meowable.meow();
        meowCount++;
    }

    public int getMeowCount() {
        return meowCount;
    }

    public void resetCounter() {
        meowCount = 0;
    }
}
```
Создала Funs.java (вспомогательный класс)
```java
package lab5;

public class Funs {
    public static void meowsCare(Meowable meowable) {
        System.out.println("meowsCare запущен");
        for (int i = 0; i < 5; i++) {
            meowable.meow();
        }
        System.out.println("meowsCare завершен");
    }

    public static void meowNTimes(Meowable meowable, int times) {
        for (int i = 0; i < times; i++) {
            meowable.meow();
        }
    }
}
```

# Задание 3
## Задача 7
### Текст задачи
Составить	программу,	которая	формирует	список	L,	включив	в	него	по	одному	разу	элементы,	
которые	входят	одновременно	в	оба	списка	L1	и	L2.	
### Алгоритм решения
класс InsertionList.java:
``` java
package lab5;

import java.util.*;

public class IntersectionList<T> {
    private List<T> result;

    public IntersectionList(List<T> list1, List<T> list2) {
        if (list1 == null || list2 == null) {
            throw new IllegalArgumentException("Списки не могут быть null.");
        }

        Set<T> set1 = new LinkedHashSet<>(list1);
        Set<T> set2 = new HashSet<>(list2);

        set1.retainAll(set2);
        this.result = new ArrayList<>(set1);
    }

    public List<T> getResult() {
        return new ArrayList<>(result);
    }
}
```

# Задание 4
## Задача 8
### Текст задачи
Имеется	список	учеников	разных	школ,	сдававших	экзамен	по	информатике,	с	указанием	их	
фамилии,	имени,	школы	и	набранного	балла.	Напишите	программу,	которая	будет	определять	
двух	учеников	школы	№	50,	которые	лучше	всех	сдали	информатику,	и	выводить	на	экран	их	
фамилии	и	имена.	
Если	наибольший	балл	набрали	более	двух	человек,	нужно	вывести	только	их	количество.	Если	
наибольший	балл	набрал	один	человек,	а	следующий	балл	набрало	несколько	человек,	нужно	
вывести	только	фамилию	и	имя	лучшего.	Известно,	что	информатику	сдавали	не	менее	5	
учеников	школы	№	50.	
На	вход	программе	в	первой	строке	подается	количество	учеников	списке	N.	В	каждой	из	
последующих	N	строк	находится	информация	в	следующем	формате:	
<Фамилия><Имя><Школа><Балл>	
где	<Фамилия>	–	строка,	состоящая	не	более,	чем	из	20	символов	без	пробелов,	<Имя>–	строка,	
состоящая	не	более,	чем	из	20	символов	без	пробелов,	<Школа>	–	целое	число	от	1	до	99,	<Балл>	–	
целое	число	от	1	до	100.	
Пример	входной	строки:	
Иванов	Сергей	50	87	
Пример	выходных	данных,	когда	найдено	два	лучших:	
Иванов	Сергей	
Сергеев	Иван	
Если	больше	двух	учеников	набрали	высший	балл,	то	программа	должна	вывести	их	количество.	
Пример	вывода	в	этом	случае:	
8	
Если	высший	балл	набрал	один	человек,	а	следующий	балл	набрало	несколько	человек,	то	
программа	должна	вывести	только	фамилию	и	имя	лучшего.	Пример	вывода	в	этом	случае:	
Иванов	Сергей	
### Алгоритм решения
Создан текстовый файл input.txt, реализация в main:
``` java
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
```

# Задание 5
## Задача 4
### Текст задачи
Файл	содержит	текст	на	русском	языке.	Напечатать	в	алфавитном	порядке	все	глухие	согласные	
буквы,	которые	входят	в	каждое	нечетное	слово.	
### Алгоритм решения
Создан текстовый файл text.txt, реализация в main:
``` java
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
```
