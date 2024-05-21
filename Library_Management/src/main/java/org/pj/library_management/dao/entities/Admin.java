package org.pj.library_management.dao.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "admin")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends Customer{
    @NotEmpty
    @Size(max = 20)
    private  String fullName;

}
