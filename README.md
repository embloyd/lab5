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
