package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getUserByCarModelAndSeries(String model, int series) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM User user WHERE userCar.model = :model AND userCar.series = :series", User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .uniqueResult();
    }

    @Override
    public List<User> listUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User")
                .getResultList();
    }

}
