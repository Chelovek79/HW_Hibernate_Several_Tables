package service;

import tableClass.City;
import tableClass.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class CityDAOImpl implements CityDAO {

    @Override
    public void addToTableCity(City city, List<Employee> employeeList) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(city);
        employeeList.stream()
                .peek(x -> x.setCity(city))
                .forEachOrdered(entityManager::persist);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void updateCity(int id, City city) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery(
                "UPDATE City с SET " +
                        "с.cityName = :cityname " +
                        "WHERE с.id = :id");
        query.setParameter("cityname", city.getCityName());
        query.setParameter("id", id);
        query.executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void updateEmployeeLiveInCity(int cityId, int employeeId, Employee newEmployee) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        City city = entityManager.find(City.class, cityId);
        city.getEmployeeList().stream()
                .filter(x -> x.getId() == employeeId)
                .peek(x -> x.setFirstName(newEmployee.getFirstName()))
                .peek(x -> x.setLastName(newEmployee.getLastName()))
                .peek(x -> x.setGender(newEmployee.getGender()))
                .peek(x -> x.setAge(newEmployee.getAge()))
                .forEachOrdered(entityManager::persist);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void delOneCity(int id) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        City city = entityManager.find(City.class, id);
        entityManager.remove(city);

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void findCityWithId(int id) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        City city = entityManager.find(City.class, id);
        entityManager.getTransaction().commit();

        System.out.println(city.getCityName());
        city.getEmployeeList().stream()
                .forEach(System.out::println);

        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void getAllCity() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("select c from City c order by c.id");
        List<City> cityList = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

        for (City city : cityList) {
            System.out.println(city.getCityName());
            city.getEmployeeList().stream().forEach(System.out::println);
        }
    }
}
