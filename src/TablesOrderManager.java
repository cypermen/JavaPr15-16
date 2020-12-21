import java.util.Arrays;
import java.util.Collection;

public class TablesOrderManager implements Order{
    private final CircularDoublyLinkedList<Item> data;

    public TablesOrderManager() {
        this.data = new CircularDoublyLinkedList<>();
    }

    public TablesOrderManager(Collection<Item> collection) {
        this.data = new CircularDoublyLinkedList<>(collection);
    }

    @Override
    public boolean add(Item item) {
        return data.add(item);
    }

    @Override
    public boolean remove(String dishName) {
        Item item = Arrays.stream(
                data.toArray())
                .filter(i -> i.getName().equals(dishName))
                .findFirst()
                .orElse(null);
        if (item == null)
            return false;
        return data.remove(item);
    }

    @Override
    public int removeAll(String dishName) {
        return (int) Arrays.stream(
                data.toArray())
                .filter(i -> i.getName().equals(dishName))
                .map(data::remove)
                .count();
    }

    @Override
    public int dishQuantity() {
        return data.getSize();
    }

    @Override
    public int dishQuantity(String dishName) {
        return (int) Arrays.stream(
                data.toArray())
                .filter(i -> i.getName().equals(dishName))
                .count();
    }

    @Override
    public Item[] getDishes() {
        return data.toArray();
    }

    @Override
    public double costTotal() {
        return (int) Arrays.stream(
                data.toArray())
                .mapToDouble(Item::getCost)
                .sum();
    }

    @Override
    public Object[] dishesNames() {
        return  Arrays.stream(
                data.toArray())
                .map(Item::getName)
                .toArray();
    }

    @Override
    public Object[] sortedDishesByCostDesc() {
        return Arrays.stream(
                data.toArray())
                .sorted((dishn1, dishn2) -> (int) (dishn2.getCost() - dishn1.getCost()))
                .toArray();
    }
}
