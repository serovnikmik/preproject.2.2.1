package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
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
   @Transactional(readOnly = true)
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      return sessionFactory.getCurrentSession().createQuery("from User").getResultList();
   }

   @Override
   @Transactional(readOnly = true)
   public User getUserByCarInfo(String carModel, int carSeries) {
       return sessionFactory.getCurrentSession()
               .createQuery(
                       "from User u where u.car.model=:carModel and u.car.series=:carSeries",
                       User.class)
               .setParameter("carModel", carModel)
               .setParameter("carSeries", carSeries)
               .getSingleResult();
   }

}
