package fu.de200027.pojo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "location")
    private String location;

    // TODO 2.3: Inverse side (OneToMany)
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();

    public Department() {
    }

    public Department(String name, String location) {
        this.name = name;
        this.location = location;
    }


}