package com.wonder.entity;

import java.io.Serializable;
import java.sql.Date;

public class Employee implements Serializable{

    private Integer id;
    private String name;
    private Double salary;
    private Date hiredate;
    private Integer depId;

    public Employee() {
    }

    public Employee(Integer id, String name, Double salary, Date hiredate, Integer depId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.hiredate = hiredate;
        this.depId = depId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", hiredate=" + hiredate +
                ", depId=" + depId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != null ? !id.equals(employee.id) : employee.id != null) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (salary != null ? !salary.equals(employee.salary) : employee.salary != null) return false;
        if (hiredate != null ? !hiredate.equals(employee.hiredate) : employee.hiredate != null) return false;
        return depId != null ? depId.equals(employee.depId) : employee.depId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (hiredate != null ? hiredate.hashCode() : 0);
        result = 31 * result + (depId != null ? depId.hashCode() : 0);
        return result;
    }
}
