package fr.xebia.jqwik;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packages = "fr.xebia.jqwik")
class ProjectStructureTest {

    @ArchTest
    public static final ArchRule each_exercise_is_isolated = slices()
            .matching("fr.xebia.jqwik.(*)..").as("$1")
            .should().notDependOnEachOther()
            .as("Each exercise is isolated")
            .because("if modifying one impacts others, reordering commits and branches is going to be hell.");

}
