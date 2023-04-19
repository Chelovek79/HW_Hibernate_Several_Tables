import service.CityDAO;
import service.CityDAOImpl;
import tableClass.City;
import tableClass.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        CityDAO cityDAO = new CityDAOImpl();

// Добавление нового города в таблицу "city";
        City city = new City("Новосибирск");
        List<Employee> employeeList = new ArrayList<>(Arrays.asList(new Employee("Тамара", "Орлова", "жен.", 37),
                new Employee("Алексей", "Поздняк", "муж.", 19)));

        cityDAO.addToTableCity(city, employeeList);

// Обновление данных в таблице "city" по id;
        cityDAO.updateCity(27, new City("Радде"));

// Изменение сотрудника одного из городов по id города;
        Employee employee = new Employee("Екатерина", "Свиридова", "жен.", 25);
        cityDAO.updateEmployeeLiveInCity(27, 61, employee);

// Получение всего списка из таблицы "city";
        cityDAO.getAllCity();

// Получение списка городов с их жителями по "id";
        cityDAO.findCityWithId(27);

// Удаление города из таблицы "city";
        cityDAO.delOneCity(27);
    }
}
