package fu.de200027.pojo;

import jakarta.persistence.*;
import lombok.*;

import javax.management.ConstructorParameters;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fullName;


    @Column(unique = true)
    private String email;

    private LocalDate hireDate;
    private boolean active;
    @Transient
    private int yearsOfService ;


    public int getYearsOfService() {
        if (hireDate == null) return 0;
        return Period.between(hireDate, LocalDate.now()).getYears();


    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", hireDate=" + hireDate +
                ", active=" + active +
                ", yearsOfService=" + yearsOfService +
                '}';
    }
}