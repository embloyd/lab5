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