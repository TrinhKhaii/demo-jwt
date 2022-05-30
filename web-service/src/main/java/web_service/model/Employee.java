package web_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/*
    Created by Trinh Khai
    Date: 25/05/2022
    Time: 15:46
*/
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String employeeName;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;


    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
