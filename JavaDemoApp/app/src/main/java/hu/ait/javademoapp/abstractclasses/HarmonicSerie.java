package hu.ait.javademoapp.abstractclasses;

public class HarmonicSerie extends SerieGenerator {
    public HarmonicSerie(int size) {
        super(size);
    }

    @Override
    public Double getNextElement() {
        if(serie.size() == 0){
            return 1.0;
        }
        return 1.0 / serie.size();
    }
}
