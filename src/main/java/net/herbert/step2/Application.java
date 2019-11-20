package net.herbert.step2;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import net.herbert.step2.model.City;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

    public static void main(String... args) throws IOException {
        var server = HttpServer.create(new InetSocketAddress(8080), 0);
        var context = server.createContext("/cities");
        context.setHandler(Application::handleRequest);
        server.start();
    }

    private static void handleRequest(HttpExchange exchange) throws IOException {
        var response = getCitiesFromDatabase().stream()
                .map(City::getName)
                .collect(Collectors.joining(","));
        exchange.sendResponseHeaders(200, response.getBytes().length);
        try (var output = exchange.getResponseBody()) {
            output.write(response.getBytes());
        }
    }

    private static List<City> getCitiesFromDatabase() {
        var cities = new ArrayList<City>();
        try (var connection = DriverManager.getConnection("jdbc:h2:./cities", "test", "");
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery("SELECT * FROM cities")) {
            Class.forName("org.h2.Driver");
            while (resultSet.next()) {
                var city = new City();
                city.setName(resultSet.getString("name"));
                city.setCountry(resultSet.getString("country"));
                city.setSubcountry(resultSet.getString("subcountry"));
                city.setGeonameid(resultSet.getInt("geonameid"));
                cities.add(city);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
