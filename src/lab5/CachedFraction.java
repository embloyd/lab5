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