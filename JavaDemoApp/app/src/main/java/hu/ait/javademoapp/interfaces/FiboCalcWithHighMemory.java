package hu.ait.javademoapp.interfaces;

public class FiboCalcWithHighMemory implements FibonacciCalculator {
    double[] numbers = new double[100];
    int lastfiboNumberIndex = 1;

    public FiboCalcWithHighMemory(){
        numbers[0] = 1;
        numbers[1] = 1;
        lastfiboNumberIndex = 1;
    }

    @Override
    public Double calculateNextFiboNumber() {
        numbers[lastfiboNumberIndex + 1] = numbers[lastfiboNumberIndex] + numbers[lastfiboNumberIndex - 1];
        lastfiboNumberIndex++;
        return numbers[lastfiboNumberIndex];
    }
}
