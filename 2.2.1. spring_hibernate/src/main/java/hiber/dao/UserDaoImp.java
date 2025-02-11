package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void getUserByCar(String model, int series) {

        String hql2 = "SELECT u FROM User u JOIN u.userCar c WHERE c.model = :model AND c.series = :series";
        Query query = sessionFactory.getCurrentSession().createQuery(hql2, User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        System.out.println(query.list().toString());

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

}
