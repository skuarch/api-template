package microservices.app;

import microservices.app.config.GitHook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class TemplateApplication implements ApplicationRunner {
    @Autowired
    private GitHook gitHook;

    public static void main(String[] args) {
        SpringApplication.run(TemplateApplication.class, args);
    }
    @Override
    public void run(ApplicationArguments arg0) throws Exception {
        gitHook.prePush();
    }

}
