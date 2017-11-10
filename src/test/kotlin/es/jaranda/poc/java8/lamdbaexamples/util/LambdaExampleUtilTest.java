
package es.jaranda.poc.java8.lamdbaexamples.util;

import es.jaranda.poc.java8.lamdbaexamples.domain.Professor;
import es.jaranda.poc.java8.lamdbaexamples.domain.Student;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class LambdaExampleUtilTest {

    private static final String SUBJECT_MATHS = "Maths";
    private static final String SUBJECT_COMPUTING_SCIENE = "Computing Sciene";
    private static final String SUBJECT_HISTORY = "History";

    private static final Professor PROFESSOR_SMITH =
            new Professor("Smith", SUBJECT_MATHS);
    private static final Professor PROFESSOR_JACK =
            new Professor("Jack", SUBJECT_COMPUTING_SCIENE);
    private static final Professor PROFESSOR_JONSHON =
            new Professor("Jonhson", SUBJECT_HISTORY);

    private static final Student STUDENT_MICHAEL =
            new Student("Michael", 23,
                    Arrays.asList(PROFESSOR_SMITH, PROFESSOR_JACK)
            );
    private static final Student STUDENT_JOHN =
            new Student("Michael", 20,
                    Collections.singletonList(PROFESSOR_JACK)
            );
    private static final Student STUDENT_HELLEN =
            new Student("Hellen", 25,
                    Arrays.asList(PROFESSOR_JACK, PROFESSOR_JONSHON)
            );

    private final LambdaExampleUtil lambdaExampleUtil = new LambdaExampleUtil();

    @Test
    public void givenListOfStudentsWhenCallObtainAllSubjectThenRecieveAllSubjectsFromMinorOf25() {

        // Given
        final List<Student> students = Arrays.asList(
                STUDENT_MICHAEL, STUDENT_JOHN, STUDENT_HELLEN
        );

        // When
        final List<String> subjects =
                lambdaExampleUtil.
                        obtainAllSubjectsFromStudentsMinorOf25(students);

        // Then
        assertTrue("Should contains 'Computer Sciene'",
                subjects.contains(SUBJECT_COMPUTING_SCIENE));
        assertTrue("Should contains 'Maths'",
                subjects.contains(SUBJECT_MATHS));
        assertFalse("Should not contains 'History'), ",
                subjects.contains(SUBJECT_HISTORY));

        assertTrue("'Computer Science' should be unique",
                isUnique(subjects, SUBJECT_COMPUTING_SCIENE));
        assertTrue("'Maths' should be unique",
                isUnique(subjects, SUBJECT_MATHS));

    }

    @Test
    public void givenEmptyListOfStudentsWhenCallObtainAllSubjectThenHaveEmptyList() {

        // Given

        final Professor professorSmith = new Professor("Smith",
                "Maths");

        final List<Student> students = Collections.emptyList();

        // When
        final List<String> subjects =
                lambdaExampleUtil.
                        obtainAllSubjectsFromStudentsMinorOf25(students);

        // Then
        assertTrue("Subject list is not empty", subjects.isEmpty());

    }

    private <T> boolean isUnique(final List<T> items, final T item) {
        return items.stream().filter(x -> x == item).count() == 1;
    }

}
