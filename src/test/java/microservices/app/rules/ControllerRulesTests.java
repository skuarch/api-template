package microservices.app.rules;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.RestController;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@SpringBootTest
@ActiveProfiles("local")
public class ControllerRulesTests {

    @Test
    public void ruleForControllersInControllerPackage() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("microservices.app");

        ArchRule rule = classes()
            .that()
            .areAnnotatedWith(RestController.class)
            .should()
            .resideInAPackage("..controller..");

        rule.check(importedClasses);
    }

}
