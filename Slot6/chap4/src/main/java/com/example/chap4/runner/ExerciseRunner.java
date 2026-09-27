package com.example.chap4.runner;

import com.example.chap4.pojo.Gender;
import com.example.chap4.pojo.Student;
import com.example.chap4.service.DepartmentService;
import com.example.chap4.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Component
@Order(2)
@RequiredArgsConstructor
public class ExerciseRunner implements CommandLineRunner {

    private final DepartmentService departmentService;
    private final StudentService studentService;

    @Override
    public void run(String... args) {
        todo6();
        todo7();
        todo9();
        todo10();
        todo11();
    }

    private void title(String t) {
        System.out.println("\n===== " + t + " =====");
    }

    private void printList(String label, Collection<?> list) {
        System.out.println("-- " + label + ":");
        list.forEach(o -> System.out.println("   " + o));
        System.out.println("   -> " + list.size() + " record(s)");
    }

    private void todo6() {
        title("TODO 6: count / findById / existsById");
        System.out.println("Departments: " + departmentService.count());
        System.out.println("Students   : " + studentService.count());

        studentService.findById(1L).ifPresentOrElse(
                s -> System.out.println("findById(1)  -> " + s),
                () -> System.out.println("findById(1)  -> Not found"));

        System.out.println("findById(99) -> " + studentService.findById(99L)
                .map(Object::toString)
                .orElse("Not found"));

        System.out.println("existsById(4) department -> " + departmentService.existsById(4L));
    }

    private void todo7() {
        title("TODO 7: Sort & Pageable");

        // (a) All students order by GPA desc
        printList("All students order by GPA desc", studentService.findAllOrderByGpaDesc());

        // (b) Page index 1 (trang thu 2), size 3, sort theo fullName
        Page<Student> page = studentService.findPage(1, 3, "fullName");
        printList("Page index " + page.getNumber() + " (size " + page.getSize() + ")", page.getContent());
        System.out.println("totalElements=" + page.getTotalElements()
                + ", totalPages=" + page.getTotalPages()
                + ", hasNext=" + page.hasNext()
                + ", hasPrevious=" + page.hasPrevious());
    }
    private void todo8() {
        title("TODO 8: findBy / existsBy / countBy");
        for (String code : List.of("AI002", "XX999")) {
            System.out.println("findByStudentCode(" + code + ") -> " +
                    studentService.findByStudentCode(code).map(Object::toString).orElse("Not found"));
        }
        System.out.println("isEmailExisted(binh.tt@fpt.edu.vn) -> "
                + studentService.isEmailExisted("binh.tt@fpt.edu.vn"));
        System.out.println("countActive -> " + studentService.countActive());
    }
    private void todo9() {
        title("TODO 9: Containing / EndingWith / IsNull");
        printList("fullName contains 'nguyen'", studentService.searchByName("nguyen"));
        printList("email domain 'gmail.com'", studentService.findByEmailDomain("gmail.com"));
        printList("email is null", studentService.findWithoutEmail());
    }
    private void todo10() {
        title("TODO 10: Between / And / True / After");
        printList("GPA in [3.0, 3.6] desc", studentService.findByGpaRange(3.0, 3.6));
        printList("MALE & active", studentService.findActiveByGender(Gender.MALE));
        printList("dob after 2005-01-01", studentService.findBornAfter(LocalDate.of(2005, 1, 1)));
    }
    private void todo11() {
        title("TODO 11: Nested Property / Top / IsEmpty");

        printList("Students in department 'SE'", studentService.findByDepartmentCode("SE"));
        printList("Top 3 students by GPA desc", studentService.findTop3HighestGpa());
        printList("Departments without students", departmentService.findEmptyDepartments());
    }

}