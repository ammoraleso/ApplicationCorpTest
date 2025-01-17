package co.com.applicationcorp.config;

import co.com.applicationcorp.domain.*;
import co.com.applicationcorp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) {
        if (!libraryRepository.existsById(1L)) {
            List<Library> libraries = new ArrayList<>();
            Library library = new Library();
            library.setId(1L);
            library.setName("Library Test");
            library.setAddress("Address Test");

            libraryRepository.save(library);

            Book book1 = new Book();
            book1.setTitle("Book Test 1");
            book1.setAuthor("Author Test 1");
            book1.setStatus(Book.BookStatus.AVAILABLE);
            book1.setLibrary(library);

            Book book2 = new Book();
            book2.setTitle("Book Test 2");
            book2.setAuthor("Author Test 2");
            book2.setStatus(Book.BookStatus.BORROWED);
            book2.setLibrary(library);

            bookRepository.saveAll(List.of(book1, book2));

            libraries.add(library);
            // Initialize 10 members
            List<Member> members = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                Member member = new Member();
                member.setName("Member " + i);
                member.setLibraries(libraries);
                members.add(member);
            }

            memberRepository.saveAll(members);
        }

        if (teacherRepository.count() == 0 && courseRepository.count() == 0 && studentRepository.count() == 0) {
            // Create Teachers
            Teacher teacher1 = new Teacher();
            teacher1.setName("John Doe");
            teacher1.setSubject("Mathematics");

            Teacher teacher2 = new Teacher();
            teacher2.setName("Jane Smith");
            teacher2.setSubject("History");

            teacherRepository.saveAll(List.of(teacher1, teacher2));

            // Create Courses
            Course course1 = new Course();
            course1.setName("Algebra 101");
            course1.setDescription("Basic Algebra");
            course1.setTeacher(teacher1);

            Course course2 = new Course();
            course2.setName("World History");
            course2.setDescription("Overview of world history");
            course2.setTeacher(teacher2);

            courseRepository.saveAll(List.of(course1, course2));

            // Create Students
            List<Student> students = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                Student student = new Student();
                student.setName("Student " + i);
                student.setAge(18 + i);
                students.add(student);
            }

            studentRepository.saveAll(students);

            // Assign Students to Courses
            for (int i = 0; i < students.size(); i++) {
                if (i % 2 == 0) {
                    course1.getStudents().add(students.get(i));
                } else {
                    course2.getStudents().add(students.get(i));
                }
            }

            courseRepository.saveAll(List.of(course1, course2));
        }
    }
}
