package ru.rustem.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import ru.rustem.model.Ticket;

import java.util.List;

public class TicketDAO {

        private SqlSessionFactory sqlSessionFactory = null;

        public TicketDAO(SqlSessionFactory sqlSessionFactory){
            this.sqlSessionFactory = sqlSessionFactory;
        }

        @SuppressWarnings("unchecked")
        public List<Ticket> selectAll(){
            List<Ticket> list = null;
            SqlSession session = sqlSessionFactory.openSession();

            try {
                list = session.selectList("Ticket.selectAll");
            } finally {
                session.close();
            }
            System.out.println("selectAll() --> "+list);
            return list;

        }

        public Ticket selectById(int id){
            Ticket ticket = null;
            SqlSession session = sqlSessionFactory.openSession();
            try {
                ticket = session.selectOne("Ticket.selectById", id);

            } finally {
                session.close();
            }
            System.out.println("selectById("+id+") --> "+ticket);
            return ticket;
        }

        public int insert(Ticket ticket){
            int id = -1;
            SqlSession session = sqlSessionFactory.openSession();

            try {
                id = session.insert("Ticket.insert", ticket);
            } finally {
                session.commit();
                session.close();
            }
            System.out.println("insert("+ticket+") --> "+ticket.getId());
            return id;
        }

        public boolean update(Ticket ticket){
            int id = -1;
            SqlSession session = sqlSessionFactory.openSession();

            try {
                id = session.update("Ticket.update", ticket);

            } finally {
                session.commit();
                session.close();
            }
            System.out.println("update("+ticket+") --> updated");
            return true;
        }

        public boolean delete(int id){

            SqlSession session = sqlSessionFactory.openSession();

            try {
                session.delete("Ticket.delete", id);

            } finally {
                session.commit();
                session.close();
            }
            System.out.println("delete("+id+")");
            return true;
        }
}
