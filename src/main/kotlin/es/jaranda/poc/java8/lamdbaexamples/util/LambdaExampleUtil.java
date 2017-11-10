
package es.jaranda.poc.java8.lamdbaexamples.util;

import es.jaranda.poc.java8.lamdbaexamples.domain.Professor;
import es.jaranda.poc.java8.lamdbaexamples.domain.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LambdaExampleUtil {

    private static final int MAX_AGE = 25;

    public List<String> obtainAllSubjectsFromStudentsMinorOf25(
            final List<Student> students) {

        return students.stream()
                .filter( student -> student.getAge() < MAX_AGE)
                .flatMap(student -> student.getProfessors().stream())
                .map(Professor::getSubject)
                .distinct()
                .collect(Collectors.toList());

    }

}
