package api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class IntemService {

    @Value("${wiremock.url}")
    private String wiremockUrl;

    private final RestTemplate restTemplate;

    public IntemService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Item> getItems() {
        String url = wiremockUrl + "/items";
        List forObject = restTemplate.getForObject(url, List.class);
        return forObject;
    }
}
