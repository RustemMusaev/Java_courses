package ru.rustem.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import ru.rustem.config.MyBatisConnectionFactory;
import ru.rustem.model.Ticket;

import java.util.List;

@Component
public class TicketDAOImpl implements TicketDAO {

    private SqlSessionFactory sqlSessionFactory = null;

    public TicketDAOImpl() {
        this.sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
    }

    public List<Ticket> selectAll() {
        List<Ticket> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            list = session.selectList("Ticket.selectAll");
        } finally {
            session.close();
        }
        return list;

    }

    public Ticket selectById(int id) {
        Ticket ticket = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            ticket = session.selectOne("Ticket.selectById", id);

        } finally {
            session.close();
        }
        return ticket;
    }

    public int insert(Ticket ticket) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.insert("Ticket.insert", ticket);
        } finally {
            session.commit();
            session.close();
        }
        return id;
    }

    public boolean update(Ticket ticket) {
        int id = -1;
        SqlSession session = sqlSessionFactory.openSession();

        try {
            id = session.update("Ticket.update", ticket);

        } finally {
            session.commit();
            session.close();
        }
        return true;
    }

    public boolean delete(int id) {

        SqlSession session = sqlSessionFactory.openSession();

        try {
            session.delete("Ticket.delete", id);

        } finally {
            session.commit();
            session.close();
        }
        return true;
    }
}
