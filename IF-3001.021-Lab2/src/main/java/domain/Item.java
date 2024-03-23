package domain;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Item {

    //Para
    private StringProperty name;
    private DoubleProperty value;
    private DoubleProperty weight;

    private double valueWeight; // Maximizar las ganancias

    public Item(String name, double value, double weight) {
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleDoubleProperty(value);
        this.weight = new SimpleDoubleProperty(weight);
        this.valueWeight = value / weight; // Aunque no se vaya a usar por ahora, se debe inicializar
    }

    public StringProperty nameProperty() {
        return name;
    }

    public double getValue() {
        return value.get();
    }

    public DoubleProperty valueProperty() {
        return value;
    }

    public double getWeight() {
        return weight.get();
    }

    public DoubleProperty weightProperty() {
        return weight;
    }

    public double getValueWeight() {
        return valueWeight;
    }

    public void setValueWeight(double valueWeight) {
        this.valueWeight = valueWeight;
    }


    //////////////////////////////////////////////////////



    @Override
    public String toString() {
        return String.format("%20s, %12.2f,%12.2f", this.name.getValue(), this.value.getValue(), this.weight.getValue());
    }


}
