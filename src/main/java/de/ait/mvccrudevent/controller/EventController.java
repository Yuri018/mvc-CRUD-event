package de.ait.mvccrudevent.controller;

import de.ait.mvccrudevent.entity.Event;
import de.ait.mvccrudevent.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/event")
    public String eventMain(Model model){
        Iterable<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return "event-main";
    }

    @GetMapping("/event/add")
    public String addEvent(Model model){
        return "event-add";
    }

    @PostMapping("/event/add")
    public String addEvent(@RequestParam("name") String name,
                           @RequestParam("date") String date,
                           @RequestParam("description") String description
                           ){
        Event event = new Event(name, description, LocalDate.parse(date));
        eventRepository.save(event);
        return "redirect:/event";
    }

    @GetMapping("/event/{id}")
    public String eventDetails(@PathVariable(value = "id") Long id, Model model){
        if (!eventRepository.existsById(id)){
            System.out.println("No such event with id = " + id);
            return "redirect:/event";
        }
        Optional<Event> event = eventRepository.findById(id);
        List<Event> res = new ArrayList<>();
        event.ifPresent(res::add);
        model.addAttribute("event", res);
        return "event-datails";
    }
}
