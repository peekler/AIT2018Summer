package hu.ait.javademoapp.abstractclasses;

import java.util.ArrayList;
import java.util.List;

public abstract class SerieGenerator {
    protected List<Double> serie;
    private int size;

    SerieGenerator(int size){
        this.size = size;
        serie = new ArrayList<>();
        generateSerie();
    }

    private void generateSerie(){
        for(int i = 0; i < size; i++){
            serie.add(getNextElement());
        }
    }

    public abstract Double getNextElement();

    public List<Double> getSerie() {
        return serie;
    }
}
