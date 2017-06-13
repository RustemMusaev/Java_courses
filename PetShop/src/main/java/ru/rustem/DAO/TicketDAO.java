package ru.rustem.DAO;


import ru.rustem.model.Ticket;

import java.util.List;

public interface TicketDAO {

    public List<Ticket> selectAll();

    public Ticket selectById(int id);

    public int insert(Ticket ticket);

    public boolean update(Ticket ticket);

    public boolean delete(int id);
}
