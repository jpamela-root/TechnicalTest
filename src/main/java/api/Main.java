package api;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import java.util.List;

public class Main {

    private static final int WIREMOCK_PORT = 8089;
    private static WireMockServer wireMockServer;

    public static void main(String[] args) {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(WIREMOCK_PORT));
        wireMockServer.start();
        WireMock.configureFor("localhost", WIREMOCK_PORT);

        PaymentService paymentService = new PaymentService();
        List<Item> items = IntemService.getItems();

        for (Item item : items) {
            paymentService.addItem(item);
        }

        System.out.println("Total: " + paymentService.getTotal());
        System.out.println("Total with promotions: " + paymentService.getTotalWithPromotions());
        System.out.println("Discount: " + paymentService.getDiscount());

        wireMockServer.stop();
    }
}
