package com.example.data.service;

import com.example.data.exception.StudentNotFoundException;
import com.example.data.exception.ValidationException;
import com.example.data.pojo.Student;
import com.example.data.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;

// TODO 3.1: @Service + @Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    private static final Pattern EMAIL = Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[\\w.-]+$");

    // TODO 3.2: field final StudentRepository + constructor injection (KHÔNG dùng @Autowired trên field)

    @Override
    // TODO 3.3: @Transactional — normalize → validate → existsByEmailIgnoreCase → save
    public Student create(Student s) { throw new UnsupportedOperationException("TODO 3.3"); }

    @Override
    // TODO 3.4: findById(...).orElseThrow(() -> new StudentNotFoundException(id))
    public Student findById(Long id) { throw new UnsupportedOperationException("TODO 3.4"); }

    @Override
    // TODO 3.5: findAllByOrderByIdAsc()
    public List<Student> findAll() { throw new UnsupportedOperationException("TODO 3.5"); }

    @Override
    // TODO 3.6: @Transactional — findById (entity managed) → normalize → validate
    //           → existsByEmailIgnoreCaseAndIdNot → copy field → save
    public Student update(Student s) { throw new UnsupportedOperationException("TODO 3.6"); }

    @Override
    // TODO 3.7: @Transactional — !existsById → StudentNotFoundException; ngược lại deleteById
    public void delete(Long id) { throw new UnsupportedOperationException("TODO 3.7"); }

    @Override
    // TODO 3.8
    public List<Student> search(String keyword) { throw new UnsupportedOperationException("TODO 3.8"); }

    private void normalize(Student s) {
        // TODO 3.9
    }

    private void validate(Student s) {
        // TODO 3.10
    }
}