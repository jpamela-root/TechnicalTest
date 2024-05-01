package api;

import java.util.List;
import java.util.List;

public class PromotionService {

    public int calculateDiscount(List<Item> items) {
        int appleCount = 0;
        int orangeCount = 0;
        int bananaCount = 0;

        for (Item item : items) {
            switch (item.getName()) {
                case "apple":
                    appleCount++;
                    break;
                case "orange":
                    orangeCount++;
                    break;
                case "banana":
                    bananaCount++;
                    break;
            }
        }

        int appleDiscount = (appleCount / 2) * 100;
        int orangeDiscount = (orangeCount / 3) * 80;
        int bananaDiscount = (bananaCount / 2) * 50;

        return appleDiscount + orangeDiscount + bananaDiscount;
    }
}
