package microservices.app.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
class CommandRunnerTest {

    @Autowired
    private CommandRunner commandRunner;

    @Test
    public void testRunCommand() throws Exception {

        // Given
        String cmd = "java -version";

        // When
        String result = commandRunner.runCommand(cmd);

        // Then
        assertNotNull(result);

    }

    @Test
    public void testCommandNegative() throws Exception {

        // given
        String cmd = null;
        NullPointerException error = null;

        // then
        Exception ex = assertThrows(Exception.class, () -> {
            commandRunner.runCommand(cmd);
        });

        // then
        assertNotNull(ex);

    }


    @Test
    public void testCommandNegative2() throws Exception{

        // given
        String cmd = " ";
        ArrayIndexOutOfBoundsException error = null;

        // then
        Exception ex = assertThrows(Exception.class, () -> {
            commandRunner.runCommand(cmd);
        });

        // then
        assertNotNull(ex);

    }

    @Test
    public void testCommandNegative3() throws Exception {

        // given
        String cmd = "not";

        // then
        Exception ex = assertThrows(IOException.class, () -> {
            commandRunner.runCommand(cmd);
        });

        // then
        assertNotNull(ex);

    }

    @Test
    public void testCommandNegative4() throws Exception {

        // given
        String cmd = "not valid command";

        // then
        Exception ex = assertThrows(Exception.class, () -> {
            commandRunner.destroyProcess(Runtime.getRuntime().exec(cmd));
        });

        // then
        assertNotNull(ex);

    }

    @Test
    public void testGetPidNegative() throws Exception {

        // given
        String cmd = " ";

        // then
        Exception ex = assertThrows(Exception.class, () ->{
            commandRunner.runCommand(cmd);
        });

        // then
        assertNotNull(ex);

    }

}
