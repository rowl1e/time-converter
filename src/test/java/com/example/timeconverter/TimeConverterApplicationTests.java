package com.example.timeconverter;

import com.example.timeconverter.model.TimeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TimeConverterApplicationTests {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testConvertEndpoint() {
        ResponseEntity<TimeResponse> entity = this.restTemplate.exchange("http://localhost:" + port + "/convert?timeInSeconds=1708204116", HttpMethod.GET, null, TimeResponse.class);
        assertThat(entity.getStatusCode().is2xxSuccessful()).isTrue();
        TimeResponse body = entity.getBody();
        if (body != null) {
            assertThat(body.getCurrentTimezone()).isNotNull();
            assertThat(body.getGMT()).isNotNull();
        }
    }

}
