package api;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainTest {

    private static WireMockServer wireMockServer;

    @BeforeAll
    public static void setUp() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8089));
        wireMockServer.start();
        WireMock.configureFor("localhost", 8089);
    }

    @AfterAll
    public static void tearDown() {
        wireMockServer.stop();
    }

    @Test
    public void testMain() {
        // Given
        PaymentService paymentService = new PaymentService(new PromotionService());
        IntemService itemService = new IntemService(new RestTemplateBuilder().build());

        // When
        List<Item> items = itemService.getItems();
        assertNotNull(items);
        for (Item item : items) {
            paymentService.addItem(item);
        }

        // Then
        assertEquals(810, paymentService.getTotal());
        assertEquals(500, paymentService.getTotalWithPromotions());
        assertEquals(310, paymentService.getDiscount());
    }
}

