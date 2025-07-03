package com.arthur.sigeievents.domain.entities;

import com.arthur.sigeievents.domain.enuns.EventType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventName;
    private String eventDescription;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    private Date eventDate;
    private Long eventSize;
    private String eventImage;
    private String eventLocation;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id", nullable = false)
    private User ownerUser;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "palestrantes_evento",
            joinColumns = @JoinColumn(name = "id_evento"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario")
    )
    private List<User> eventSpeakers = new ArrayList<>();

    @ManyToMany(mappedBy = "participatingEvents")
    private List<User> eventParticipants = new ArrayList<>();


    public boolean addParticipant(User user) {

        if (this.eventParticipants.size() >= this.eventSize) {
            return false;
        }
        if (this.ownerUser.equals(user)) {
            return false;
        }

        if (this.eventParticipants.contains(user)) {
            return false;
        }

        this.eventParticipants.add(user);

        if (user.getParticipatingEvents() != null && !user.getParticipatingEvents().contains(this)) {
            user.getParticipatingEvents().add(this);
        }

        return true;

    }

    public void removeParticipant(User user) {
        this.eventParticipants.remove(user);

        if (user.getParticipatingEvents() != null) {
            user.getParticipatingEvents().remove(this);
        }
    }

}
