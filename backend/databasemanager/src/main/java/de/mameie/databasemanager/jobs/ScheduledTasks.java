package de.mameie.databasemanager.jobs;

import de.mameie.databasemanager.sql.server.connection.DBServerConnectionFactory;
import de.mameie.databasemanager.sql.server.database.connection.DBConnectionFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

@Component
public class ScheduledTasks {

    /**
     * Clears the list of server connections every 7 hours.
     *
     * @throws NoSuchFieldException if the 'instances' field is not found.
     * @throws IllegalAccessException if the 'instances' field cannot be accessed.
     */
    @Scheduled(fixedRate = 7 * 60 * 60 * 1000)
    public void clearServerConnectionTask() throws NoSuchFieldException, IllegalAccessException {
        clearConnectionList(DBConnectionFactory.class);
    }

    /**
     * Clears the list of database connections every 7 hours.
     *
     * @throws NoSuchFieldException if the 'instances' field is not found.
     * @throws IllegalAccessException if the 'instances' field cannot be accessed.
     */
    @Scheduled(fixedRate = 7 * 60 * 60 * 1000)
    public void clearDatabaseConnectionTask() throws NoSuchFieldException, IllegalAccessException {
        clearConnectionList(DBServerConnectionFactory.class);
    }

    /**
     * Clears the list of connections for the specified class.
     *
     * @param clazz the class whose connections list should be cleared.
     * @throws NoSuchFieldException if the 'instances' field is not found.
     * @throws IllegalAccessException if the 'instances' field cannot be accessed.
     */
    private void clearConnectionList(Class<?> clazz) throws NoSuchFieldException, IllegalAccessException {
        Field instancesField = clazz.getDeclaredField("instances");
        instancesField.setAccessible(true);
        List<?> instances = (List<?>) instancesField.get(null);
        for (Object instance : instances) {
            if (instance instanceof DBConnectionFactory) {
                DBConnectionFactory dbConnection = (DBConnectionFactory) instance;
                try {
                    dbConnection.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (instance instanceof DBServerConnectionFactory) {
                DBServerConnectionFactory dbServerConnection = (DBServerConnectionFactory) instance;
                try {
                    dbServerConnection.closeConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        instances.clear();
    }
}
