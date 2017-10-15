public class Cookie extends DessertItem {
    private int number;
    private int pricePerDozen;		// in cents
    private int cost;

    public Cookie(String name, int number, int pricePerDozen) {
        super(name);
        this.number = number;
        this.pricePerDozen = pricePerDozen;
    }

    public int getCost() {
        cost = (int)Math.round(number / 12.0 * pricePerDozen);
        return cost;
    }

    public int getNumber() {
        return number;
    }

    public int getPricePerDozen() {
        return pricePerDozen;
    }

}