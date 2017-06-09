package ru.rustem.model;

/**
 * Created by musaevrr on 07.06.2017.
 */
public class Support {
    private Integer id;
    private Ticket ticket;
    private Executor executor;

    public Support() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }
}
