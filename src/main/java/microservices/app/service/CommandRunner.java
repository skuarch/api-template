package microservices.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class CommandRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final int SLEEP_ONE_SEC = 1000;

    public String runCommand(String cmd) throws IOException, InterruptedException {

        String line = "";
        Process process = null;
        String commandResult = "";
        String regex = " ";

        try {

            logger.info("Running command");
            String[] commandArray = cmd.split(regex);
            Runtime runtime = Runtime.getRuntime();
            process = runtime.exec(commandArray);
            process.waitFor();
            Thread.sleep(SLEEP_ONE_SEC);

            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                while ((line = bufferedReader.readLine()) != null) {
                    logger.info(line);
                    commandResult += line;
                }
            }

            logger.info("Command '" + cmd + "' executed successfully!");
            destroyProcess(process);

        } catch (IOException | InterruptedException e) {
            logger.error("Execution of command '" + cmd + "' was unsuccessfully", e);
            throw e;
        } finally {
            destroyProcess(process);
        }

        return commandResult;
    }

    public void destroyProcess(Process process) {

        if (process != null) {
            process.destroy();
        }

    }

}
