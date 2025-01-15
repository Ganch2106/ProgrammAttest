package System.Resources.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class AnimalDatabase {
    public static void main(String[] args) {
        // Строка подключения
        String url = "jdbc:mysql://localhost/ДрузьяЧеловека?serverTimezone=Europe/Moscow&useSSL=false";
        String username = "root";  // Имя пользователя
        String password = "root";  // Пароль

        // Создаем подключение и выполняем запросы
        try {
            // Загружаем драйвер MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Устанавливаем соединение с базой данных
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Подключение к базе данных успешно!");

            // Создаем Statement для выполнения SQL запросов
            Statement statement = connection.createStatement();

            // Выполняем запрос к таблице AllAnimals
            String query = "SELECT * FROM AllAnimals";
            ResultSet resultSet = statement.executeQuery(query);

            // Обрабатываем результаты запроса
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String species = resultSet.getString("species");
                int age = resultSet.getInt("age");
                System.out.println("Name: " + name + ", Species: " + species + ", Age: " + age);
            }

            // Закрываем соединение и другие ресурсы
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Не найден драйвер MySQL: " + e.getMessage());
        }
    }
}
