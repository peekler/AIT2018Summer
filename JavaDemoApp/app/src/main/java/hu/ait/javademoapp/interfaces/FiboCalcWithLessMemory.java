package hu.ait.javademoapp.interfaces;

public class FiboCalcWithLessMemory implements FibonacciCalculator {

    double penultimateNumber = 1;
    double lastNumber = 1;

    @Override
    public Double calculateNextFiboNumber() {
        double temp = penultimateNumber;
        penultimateNumber = lastNumber;
        lastNumber = temp + penultimateNumber;
        return lastNumber;
    }
}
