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