package com.arthur.sigeievents.controllers;

import com.arthur.sigeievents.config.security.model.UserLogged;
import com.arthur.sigeievents.controllers.modelAttributes.ModelAttributes;
import com.arthur.sigeievents.controllers.request.EventRequestDTO;
import com.arthur.sigeievents.controllers.response.EventResponseDTO;
import com.arthur.sigeievents.usecases.events.*;
import com.arthur.sigeievents.usecases.pdf.PdfService;
import com.arthur.sigeievents.usecases.pdf.PdfTicketService;
import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.List;


@Controller
@AllArgsConstructor
public class EventController extends ModelAttributes {

    private EditEvent editEvent;
    private RegisterEvent registerEvent;
    private JoinEvent joinEvent;
    private FindEvent findEvent;
    private PdfService pdfService;
    private PdfTicketService ticketService;
    private FindJoinedEvents findJoinedEvents;

    @GetMapping("/admin/showEvents")
    public String showAllEventsPage(Model model) {
        model.addAttribute("contentFragment", "/pages/admin/showEvents");
        model.addAttribute("pageTitle", "Zezin Eventos , confira os melhores Eventos");
        return "layouts/master";
    }


    @PostMapping("/registerEvent")
    public String registerEvent(@ModelAttribute EventRequestDTO eventDTO , @AuthenticationPrincipal UserLogged userDetails){

        Long ownerId = userDetails.getId();
        var registrationResult = registerEvent.registerEvent(eventDTO,ownerId);

        if (registrationResult.isPresent()) {
            return "redirect:/myEvents";
        }
        return "redirect:/createEvents?erro";
    };

    @PostMapping("/joinEvent/{id}")
    public String joinEvent(@PathVariable(name = "id") Long eventId , @AuthenticationPrincipal UserLogged userDetails){
        Long userId = userDetails.getId();
        boolean successJoin = joinEvent.joinEvent(userId,eventId);

        if(successJoin){
            return "redirect:/myTickets";
        }
        return "redirect:/?erro";
    }

    @PostMapping("/editEvent/{id}")
    public String editEvent(@PathVariable Long id ,@ModelAttribute EventRequestDTO eventDTO){
        var returnEvent = editEvent.editEvent(id,eventDTO);

        if (returnEvent.isPresent()) {
            return "redirect:/myEvents";
        }
        return "redirect:/createEvents?erro-ao-editar";
    };


    @GetMapping("/editEvent/{id}")
    public String editEventPage(@PathVariable Long id, Model model) {
        var returnedEvent = findEvent.findEventResponse(id).orElseThrow();
        model.addAttribute("eventId", returnedEvent.id());
        model.addAttribute("Event", returnedEvent);
        model.addAttribute("contentFragment", "pages/createEvents");
        model.addAttribute("pageTitle", "Zezin Eventos , confira os melhores Eventos");
        return "layouts/master";
    }

    @GetMapping("/createEvents")
    public String createEventPage(@AuthenticationPrincipal UserLogged userLogged, Model model) {

        boolean hasPermission = userLogged.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN") || role.equals("ROLE_ORGANIZER"));

        model.addAttribute("pageTitle", "Zezin Eventos , confira os melhores Eventos");

        if(hasPermission) {
            model.addAttribute("eventId", 0);
            model.addAttribute("Event", new EventRequestDTO(null, null, null, null, null, null, null));
            model.addAttribute("contentFragment", "pages/createEvents");
        }
        else {
            model.addAttribute("contentFragment", "pages/becomeOrganizer");
        }

        return "layouts/master";
    }

    @GetMapping("/generate-event-pdf/{id}")
    public ResponseEntity<Resource> generateEventPdf(@PathVariable Long id, @AuthenticationPrincipal UserLogged userLogged) throws DocumentException, IOException {

        var eventFinded = findEvent.findEventResponse(id).orElseThrow();
        boolean isAdmin = userLogged.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (eventFinded.ownerId().equals(userLogged.getId()) || isAdmin) {

            byte[] pdfBytes = pdfService.generateEventPdf(eventFinded);
            ByteArrayResource resource = new ByteArrayResource(pdfBytes);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=evento-" + id + ".pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(pdfBytes.length)
                    .body(resource);
        }

        return ResponseEntity.status(403).build();

    }


    @GetMapping("/generate-ticket-pdf/{id}")
    public ResponseEntity<Resource> generateEventTicketPdf(@PathVariable Long id, @AuthenticationPrincipal UserLogged userLogged) throws DocumentException, IOException {

        var eventFinded = findEvent.findEventResponse(id).orElseThrow();
        List<EventResponseDTO> userJoinedEvent = findJoinedEvents.findJoinedEvents(userLogged.getId());

        for (EventResponseDTO eventResponseDTO : userJoinedEvent) {

            if(eventFinded.id().equals(eventResponseDTO.id()))
            {
                byte[] pdfBytes = ticketService.generateEventPdf(eventFinded , userLogged);
                ByteArrayResource resource = new ByteArrayResource(pdfBytes);

                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=evento-" + id + ".pdf");

                return ResponseEntity.ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_PDF)
                        .contentLength(pdfBytes.length)
                        .body(resource);
            }

        }
        return ResponseEntity.status(403).build();
    }



}
