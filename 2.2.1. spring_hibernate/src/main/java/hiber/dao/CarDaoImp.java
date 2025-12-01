package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public void add(Car car){
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @Transactional
    public List<Car> listCars(){
        CriteriaBuilder cb = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        Root<Car> root = cq.from(Car.class);
        cq.select(root);

        return sessionFactory.getCurrentSession()
                .createQuery(cq).getResultList();
    }

}
