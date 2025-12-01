package hiber.dao;

import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        String hql = "from Car";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Car.class).getResultList();
    }

}
