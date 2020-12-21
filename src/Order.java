public interface Order {
    boolean add(Item item);
    boolean remove(String dishName);
    int removeAll(String dishName);
    int dishQuantity();
    int dishQuantity(String dishName);
    Object[] getDishes();
    double costTotal();
    Object[] dishesNames();
    Object[] sortedDishesByCostDesc();
}
