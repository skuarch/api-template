package microservices.app.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
class GitHookTest {

    @Autowired
    private GitHook gitHook;

    @Test
    void prePush() throws Exception {

        // given
        assertNotNull(gitHook);

        // when
        String result = gitHook.prePush();

        // then
        assertNotNull(result);

    }
}
