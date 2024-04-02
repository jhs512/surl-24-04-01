package com.ll.surl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
// "kafka", "meilisearch", "rabbitmq" 없이 테스트 하려면 이렇게
@ActiveProfiles({"test"})
// "kafka", "meilisearch", "rabbitmq" 모드로 테스트 하려면 이렇게
// @ActiveProfiles({"test", "kafka", "meilisearch", "rabbitmq"})
class SurlApplicationTests {

    @Test
    void contextLoads() {
    }

}
