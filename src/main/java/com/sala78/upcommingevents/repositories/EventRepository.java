package com.sala78.upcommingevents.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sala78.upcommingevents.models.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    public Optional<Event> findByTitle(String title);
}
