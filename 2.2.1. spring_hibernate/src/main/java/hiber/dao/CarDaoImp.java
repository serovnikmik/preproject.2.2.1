package hiber.dao;

import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    private final SessionFactory sessionFactory;

    public CarDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @Transactional
    public List<Car> listCars() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Car", Car.class).getResultList();
    }

}
