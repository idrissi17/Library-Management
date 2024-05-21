package org.pj.library_management.dao.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@ToString
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
public class Student extends Person {
    @Size(max = 20)
    private String fullName;
    @Size(max = 20)
    private String email;
    @Column(length = 100)
    private String address;
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z0-9\\s]*")
    private String school;
    private String yearLevel;
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;
    @NotNull
    @Min(0)
    private int age;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Transaction> transactions = new ArrayList<>();





}
