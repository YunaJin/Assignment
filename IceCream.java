public class IceCream extends DessertItem {
    private int cost;		// in cents

    public IceCream() {
        this("", 0);
    }

    public IceCream(String name, int cost) {
        super(name);
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

}