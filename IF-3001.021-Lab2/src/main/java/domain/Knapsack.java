package domain;

import java.util.Arrays;

public class Knapsack {

    private Item[] list; // Lista de objetos candidatos para la mochila
    private double capacity; // Capacidad máxima en la mochila

    public Knapsack(Item[] list, double capacity) {
        this.list = list;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        // Guardar la solucion de la mochila
        Item[] solution = solve();

        // Lista
        StringBuilder result = new StringBuilder("KNAPSACK PROBLEM\n_____________________\n")
                .append("MAX WEIGHT: ").append(this.capacity).append("\nITEMS LIST ADDED:\n\n")
                .append("\t\t\tName\t\t\t\tValue\t\t\tWeight\n");

        double totalWeight = 0;
        double totalValue = 0;

        // Recorrer Solution para recorrer las opciones en cada posición
        for (int i = 0; i < solution.length; i++) {
            Item it = solution[i];
            if (it == null) break; // Si es nulo es porque ya no hay más elementos en la tabla
            totalWeight += it.getWeight(); // Va sumando el peso del item
            totalValue += it.getValue(); // Sumando el valor
            // Traer cual valor del objeto (ToString)
            result.append("(").append(i + 1).append(")").append(it.toString()).append("\n");
        }

        result.append("---------------------------------------------------\n")
                .append("TOTALS: \t\t\t\t\t").append(totalValue).append("\t\t\t").append(totalWeight);

        return result.toString();
    }

    public Item[] solve() {
        // Ordenar la lista de objetos para maximizar ganancias
        bubbleSort();

        // Lista de elementos que se agregarán a la mochila
        Item[] knapsackList = new Item[this.list.length];

        double totalWeight = 0;
        int pos = 0;

        // Recorrer la lista de elementos y agregarlos a la mochila si hay espacio
        for (Item item : this.list) {
            if (totalWeight + item.getWeight() <= this.capacity) {
                // Si la adición de este elemento no excede la capacidad máxima
                totalWeight += item.getWeight();
                knapsackList[pos++] = item;
            }
        }

        // Devolver la lista de elementos agregados a la mochila
        return Arrays.copyOfRange(knapsackList, 0, pos);
    }

    private void bubbleSort() {
        int n = this.list.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (this.list[j].getValueWeight() < this.list[j + 1].getValueWeight()) {
                    // Swap arr[j] and arr[j+1]
                    Item temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}
