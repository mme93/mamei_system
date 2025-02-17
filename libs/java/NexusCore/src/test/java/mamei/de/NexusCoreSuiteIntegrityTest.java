package mamei.de;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.fail;

/**
 * This class verifies the integrity of test suites in the NexusCore project.
 * It ensures that:
 * <ul>
 *     <li>All test classes are included in at least one test suite.</li>
 *     <li>All test suites (except the parent suite) are referenced in another suite.</li>
 * </ul>
 * If any test class or suite is missing, the respective test will fail.
 */
public class NexusCoreSuiteIntegrityTest {

    private static final String BASE_PACKAGE = "mamei.de";
    private static final Class<?> PARENT_SUITE = NexusCoreAllTestSuite.class;

    /**
     * Ensures that all test classes in the project are part of at least one test suite.
     *
     * @throws ClassNotFoundException if a class cannot be found during package scanning.
     */
    @Test
    public void testAllTestClassesAreInASuite() throws ClassNotFoundException {
        Set<Class<?>> allTestClasses = findAllTestClasses(BASE_PACKAGE);
        Set<Class<?>> allSuiteClasses = findAllSuites(BASE_PACKAGE);

        Set<Class<?>> testClassesInSuites = getAllSuiteMembers(allSuiteClasses);

        Set<Class<?>> missingTests = new HashSet<>(allTestClasses);
        missingTests.removeAll(testClassesInSuites);

        if (!missingTests.isEmpty()) {
            fail("Folgende Testklassen sind in keiner Suite enthalten: " + missingTests);
        }
    }

    /**
     * Ensures that all test suites (except the parent suite) are included in at least one other suite.
     *
     * @throws ClassNotFoundException if a class cannot be found during package scanning.
     */
    @Test
    public void testAllSuitesAreReferencedSomewhere() throws ClassNotFoundException {
        Set<Class<?>> allSuites = findAllSuites(BASE_PACKAGE);
        Set<Class<?>> referencedSuites = getAllSuiteMembers(allSuites);

        allSuites.remove(PARENT_SUITE);

        Set<Class<?>> unreferencedSuites = new HashSet<>(allSuites);
        unreferencedSuites.removeAll(referencedSuites);

        if (!unreferencedSuites.isEmpty()) {
            fail("The following suites are not included anywhere: " + unreferencedSuites);
        }
    }

    /**
     * Finds all test classes in the specified package that contain at least one method annotated with {@code @Test}.
     *
     * @param packageName the base package to scan.
     * @return a set of test classes.
     * @throws ClassNotFoundException if a class cannot be found during scanning.
     */
    private Set<Class<?>> findAllTestClasses(String packageName) throws ClassNotFoundException {
        return findClasses(packageName).stream()
                .filter(clazz -> Arrays.stream(clazz.getMethods()).anyMatch(m -> m.isAnnotationPresent(Test.class)))
                .collect(Collectors.toSet());
    }

    /**
     * Finds all test suites in the specified package that are annotated with {@code @RunWith(Suite.class)}.
     *
     * @param packageName the base package to scan.
     * @return a set of suite classes.
     * @throws ClassNotFoundException if a class cannot be found during scanning.
     */
    private Set<Class<?>> findAllSuites(String packageName) throws ClassNotFoundException {
        return findClasses(packageName).stream()
                .filter(clazz -> clazz.isAnnotationPresent(RunWith.class) &&
                        clazz.getAnnotation(RunWith.class).value().equals(Suite.class))
                .collect(Collectors.toSet());
    }

    /**
     * Extracts all test and suite classes that are listed within {@code @Suite.SuiteClasses}.
     *
     * @param suites a set of test suites to extract members from.
     * @return a set of all classes included in the provided suites.
     */
    private Set<Class<?>> getAllSuiteMembers(Set<Class<?>> suites) {
        Set<Class<?>> suiteMembers = new HashSet<>();
        for (Class<?> suite : suites) {
            Suite.SuiteClasses suiteClasses = suite.getAnnotation(Suite.SuiteClasses.class);
            if (suiteClasses != null) {
                suiteMembers.addAll(Arrays.asList(suiteClasses.value()));
            }
        }
        return suiteMembers;
    }

    /**
     * Finds all Java classes in the specified package.
     *
     * @param packageName the base package to scan.
     * @return a set of found classes.
     * @throws ClassNotFoundException if a class cannot be found during scanning.
     */
    private Set<Class<?>> findClasses(String packageName) throws ClassNotFoundException {
        String path = packageName.replace('.', '/');
        File directory = new File("src/test/java/" + path);
        Set<Class<?>> classes = new HashSet<>();

        if (directory.exists() && directory.isDirectory()) {
            for (File file : Objects.requireNonNull(directory.listFiles())) {
                if (file.getName().endsWith(".java")) {
                    String className = packageName + "." + file.getName().replace(".java", "");
                    classes.add(Class.forName(className));
                }
            }
        }
        return classes;
    }
}
