import java.util.HashMap;
import java.util.Objects;

public class InternetOrder {


    private final HashMap<String, Order> orders;

    public InternetOrder() {
        this.orders = new HashMap<>();
    }

    public boolean add(String address, Order order) throws OrderAlreadyAddedException {
        if(orders.containsKey(address))
            throw new OrderAlreadyAddedException(address);
        orders.put(address, order);
        return true;
    }

    public Order getOrder(String address) {
        return Objects.requireNonNull(
                orders.entrySet()
                        .stream()
                        .filter(elem -> elem.getKey().equals(address))
                        .findFirst()
                        .orElse(null))
                .getValue();
    }


    public int dishQuantityInternet(String dishName){
        int quantityInternet = 0;
        for (Order o: orders.values()) {
            quantityInternet += o.dishQuantity(dishName);
        }
        return quantityInternet;
    }

    public double costTotalInternet(){
        double cost = 0;
        for (Order o: orders.values()) {
            cost += o.costTotal();
        }
        return cost++;
    }

    public boolean remove(String address) {
        orders.computeIfPresent(
                address,
                (key, value) -> orders.remove(key));
        return true;
    }

    public boolean add(String address, Item item) {
        return getOrder(address).add(item);
    }

    public Object[] getOrders() {
        return  orders.values().toArray();
    }
}
