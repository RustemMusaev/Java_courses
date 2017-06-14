package ru.rustem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.rustem.DAO.TicketDAO;
import ru.rustem.model.Ticket;

import java.util.List;
import java.util.Map;

@Controller
public class TicketController {

    @Autowired
    TicketDAO ticketDAO;

    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    @ResponseBody
    public List<Ticket> findAll() {
        return ticketDAO.selectAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Map<String, Object> model) {
        List<Ticket> tickets = ticketDAO.selectAll();
        model.put("tickets", tickets);
        return "client";
    }

    @RequestMapping(value = "/ticket/add", method = RequestMethod.POST)
    @ResponseBody
    public int save(@RequestBody Ticket ticket) {
        return ticketDAO.insert(ticket);
    }

    @RequestMapping(value = "/ticket/update", method = RequestMethod.PUT)
    @ResponseBody
    public Ticket update(@RequestBody Ticket ticket) {
        Integer id = ticket.getId();
        ticketDAO.update(ticket);
        return ticketDAO.selectById(id);
    }

    @RequestMapping(value = "/ticket/delete/{id}")
    @ResponseBody
    public boolean delete(@PathVariable("id") Integer id) {
        return ticketDAO.delete(id);
    }
}
