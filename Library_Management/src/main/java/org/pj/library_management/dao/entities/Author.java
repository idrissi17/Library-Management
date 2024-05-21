package org.pj.library_management.dao.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer author_id;
    @Size(max = 20)
    private String fullName;
    @Size(max = 50)
    private String email;
    @Column(length = 100)
    private String address;
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;
    @NotNull
    @Min(0)
    private Integer age;
    @OneToMany(mappedBy = "author")
    private Collection<Book>books=new ArrayList<>();
    

}
