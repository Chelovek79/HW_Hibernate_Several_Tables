package service;

import tableClass.City;
import tableClass.Employee;

import java.util.List;

public interface CityDAO {

    void addToTableCity(City city, List<Employee> employeeList);

    void updateCity(int id, City city);

    void updateEmployeeLiveInCity(int cityId, int employeeId, Employee employee);

    void delOneCity(int id);

    void findCityWithId(int id);

    void getAllCity();
}
