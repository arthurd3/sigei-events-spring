package com.arthur.sigeievents.repositories;

import com.arthur.sigeievents.domain.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventsRepository extends JpaRepository<Event, Long> {
    List<Event> findByOwnerUser_Id(Long userId);

    List<Event> findByEventParticipants_Id(Long userId);
}
