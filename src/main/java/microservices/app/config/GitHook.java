package microservices.app.config;

import microservices.app.service.CommandRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GitHook {

    @Autowired
    private CommandRunner commandRunner;


    public String prePush() throws Exception {

        String result = commandRunner.runCommand("git config core.hooksPath .hooks");
        return result;

    }

}
