package com.example.timeconverter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TimeConverterApplicationTests {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testConvertEndpoint() {
        ResponseEntity<Map<String, String>> entity = this.restTemplate.exchange("http://localhost:" + port + "/convert?timeInSeconds=1708204116", HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, String>>() {});
        assertThat(entity.getStatusCode().is2xxSuccessful()).isTrue();
        Map<String, String> body = entity.getBody();
        if (body != null) {
            assertThat(body.get("currentTimezone")).isNotNull();
            assertThat(body.get("GMT")).isNotNull();
        }
    }

}
