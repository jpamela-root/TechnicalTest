package api;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {

    private List<Item> cart;
    private PromotionService promotionService;

    public PaymentService() {
        this.cart = new ArrayList<>();
        this.promotionService = new PromotionService();
    }

    public void addItem(Item item) {
        cart.add(item);
    }

    public int getTotal() {
        int total = 0;
        for (Item item : cart) {
            total += item.getPrice();
        }
        return total;
    }

    public int getTotalWithPromotions() {
        int total = getTotal();
        int discount = promotionService.calculateDiscount(cart);
        return total - discount;
    }

    public int getDiscount() {
        return promotionService.calculateDiscount(cart);
    }
}
