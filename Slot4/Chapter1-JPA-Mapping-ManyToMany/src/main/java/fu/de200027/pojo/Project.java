package com.example.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_code", unique = true)
    private String projectCode;

    @Column(name = "project_name")
    private String projectName;

    private BigDecimal budget;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    // TODO 5.3: Inverse side của quan hệ N-N
    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees = new HashSet<>();

    public Project() {
    }

    public Project(String projectCode, String projectName,
                   BigDecimal budget, LocalDate startDate,
                   LocalDate endDate) {
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.budget = budget;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // TODO 5.4: equals/hashCode dựa trên projectCode, không dùng id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;

        Project project = (Project) o;

        // Không dùng id vì id chỉ được sinh sau khi persist.
        // Dùng projectCode làm business key để equals/hashCode ổn định.
        return projectCode != null && projectCode.equals(project.projectCode);
    }

    @Override
    public int hashCode() {
        return projectCode != null ? projectCode.hashCode() : 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}