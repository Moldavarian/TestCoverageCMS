package moldovarian.testCoverageCms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class CoverageApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

//    @Test
//    void shouldReturnATeamWhenDataIsSaved() {
//        ResponseEntity<String> response = restTemplate.getForEntity("/coverage/team/1", String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        DocumentContext documentContext = JsonPath.parse(response.getBody());
//        Number id = documentContext.read("$.id");
//        assertThat(id).isEqualTo(1);
//
//        String name = documentContext.read("$.name");
//        assertThat(name).isEqualTo("Team X");
//    }
//
//    @Test
//    void shouldReturnAServiceWhenDataIsSaved() {
//        ResponseEntity<String> response = restTemplate.getForEntity("/coverage/service/1", String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        DocumentContext documentContext = JsonPath.parse(response.getBody());
//        Number id = documentContext.read("$.id");
//        assertThat(id).isEqualTo(1);
//
//        String name = documentContext.read("$.name");
//        assertThat(name).isEqualTo("Second microservice");
//
//        String description = documentContext.read("$.description");
//        assertThat(description).isEqualTo("Does fizz");
//
//        String team = documentContext.read("$.team");
//        assertThat(team).isEqualTo("First microservice");
//    }
}
