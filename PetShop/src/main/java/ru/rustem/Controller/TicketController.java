package ru.rustem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.rustem.DAO.TicketDAO;
import ru.rustem.MyBatisConnectionFactory;
import ru.rustem.model.Ticket;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TicketController {

    TicketDAO ticketDAO = new TicketDAO(MyBatisConnectionFactory.getSqlSessionFactory());

    @RequestMapping(value = "/tickets", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<Ticket>> findAll(){
        Map<String, List<Ticket>> tickets = new HashMap<String, List<Ticket>>();
        tickets.put("tickets", ticketDAO.selectAll());
        return tickets;
    }

    @RequestMapping(value = "/ticket/add", method = RequestMethod.POST)
    @ResponseBody
    public int save(@RequestBody Ticket ticket){
        return ticketDAO.insert(ticket);
    }

    @RequestMapping(value = "/ticket/update", method = RequestMethod.PUT)
    @ResponseBody
    public Ticket update(@RequestBody Ticket ticket){
        Integer id = ticket.getId();
        ticketDAO.update(ticket);
        return ticketDAO.selectById(id);
    }

    @RequestMapping(value = "/ticket/delete/{id}")
    @ResponseBody
    public boolean delete(@PathVariable("id") Integer id){
        return ticketDAO.delete(id);
    }
}
