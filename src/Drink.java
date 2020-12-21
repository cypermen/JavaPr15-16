public final class Drink implements Item{
    private double cost;
    private String name;
    private String description;


    public Drink(String name, String description) {
        this(0.0, description, name);
    }

    public Drink(double cost, String name, String description) {
        if(cost < 0 || name.equals("") || description.equals(""))
            throw new IllegalArgumentException("Цена должна быть больше или равна 0, имя и описания не должны быть пустыми");
        this.cost = cost;
        this.name = name;
        this.description = description;

    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

}