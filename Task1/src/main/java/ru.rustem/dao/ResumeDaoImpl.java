package ru.rustem.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ResumeDaoImpl implements ResumeDao {

    private SessionFactory sessionFactory;

    @Autowired
    public ResumeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Resume> findAll() {
        return getSession().createQuery("FROM Resume").list();
    }

    @Override
    public Integer save(Resume resume) {
        getSession().saveOrUpdate(resume);
        return resume.getId();
    }

    @Override
    public List<Resume> findByPosition(String position) {
        Query query = getSession().createQuery("FROM Resume resume where resume.position LIKE :position"
                , Resume.class);
        List<Resume> resumes = (List<Resume>) query.setParameter("position", "%" + position + "%").list();
        return resumes;
    }

    private Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        return session;
    }
}
