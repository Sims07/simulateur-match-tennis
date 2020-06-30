package sc.match.tennis.match.simulator;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.Test;

public class DependencyRuleTest {

    @Test
    public void domainLayerDoesNotDependOnApplicationLayer() {
        ArchRuleDefinition.noClasses()
                .that()
                .resideInAPackage("sc.match.tennis.match.simulator.domain..")
                .should()
                .dependOnClassesThat()
                .resideInAPackage("sc.match.tennis.match.simulator.application..")
                .check(new ClassFileImporter().importPackages("sc.match.tennis.match.simulator"));
    }
}
