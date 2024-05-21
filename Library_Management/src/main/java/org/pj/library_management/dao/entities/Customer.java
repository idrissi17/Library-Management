package org.pj.library_management.dao.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;


@Entity
@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AllArgsConstructor
@NoArgsConstructor
public abstract class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Size(max = 20)
    private String username;
    @NotEmpty
    @Size(max = 60)  
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;
}
