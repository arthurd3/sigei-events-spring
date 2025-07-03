package com.arthur.sigeievents.domain.entities;

import com.arthur.sigeievents.domain.enuns.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;


    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType ;

    @OneToMany(mappedBy = "ownerUser" , cascade = CascadeType.ALL)
    private List<Event> ownedEvents;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "participantes_evento",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_evento")
    )
    private List<Event> participatingEvents ;

    private int points;


}
