package hu.ait.javademoapp.abstractclasses;

public class FiboSerie extends SerieGenerator {

    public FiboSerie(int size) {
        super(size);
    }

    @Override
    public Double getNextElement() {
        if(serie.size() == 0 || serie.size() == 1){
            return 1.0;
        }
        return serie.get(serie.size() - 2) + serie.get(serie.size() - 1);
    }
}
