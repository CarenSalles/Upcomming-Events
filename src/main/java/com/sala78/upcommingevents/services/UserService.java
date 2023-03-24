package com.sala78.upcommingevents.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sala78.upcommingevents.models.Event;
import com.sala78.upcommingevents.models.User;
import com.sala78.upcommingevents.repositories.EventRepository;
import com.sala78.upcommingevents.repositories.UserRepository;

@Service
public class UserService {
    
    private UserRepository repository;

    @Autowired
    private EventRepository eventRepository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User store(User user) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodePassword = encoder.encode(user.getPassword());

        user.setPassword(encodePassword);
        return repository.save(user);
    }

    public List<User> listAll() {
        
        return repository.findAll();
    }

    public User addEvent(String username, Long idEvent){

        User userDB = repository.findByUsername(username).orElseThrow();  
        
        Event eventDBForId = eventRepository.findById(idEvent).orElseThrow();

        Set<Event> events = userDB.getEvents();
        
        events.add(eventDBForId);

        userDB.setEvents(events);

        return repository.save(userDB);
    }

    public User deleteEventOfUser(String username, Long idEvent){
        User userDB = repository.findByUsername(username).orElseThrow();      
        
        Set<Event> events = new HashSet<>();

        for (Event event : userDB.getEvents()) {
            
            if(event.getId() != idEvent) events.add(event);

        }

        userDB.setEvents(events);

        return repository.save(userDB);
    }

    public Set<Event> listAllEventsOfUser(String username){
        User userDB = repository.findByUsername(username).orElseThrow();

        return userDB.getEvents();
    }
}
