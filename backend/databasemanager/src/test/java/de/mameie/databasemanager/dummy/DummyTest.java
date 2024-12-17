package de.mameie.databasemanager.dummy;

import de.mameie.databasemanager.sql.server.connection.DBServerConnectionFactory;
import de.mameie.databasemanager.sql.server.connection.DBServerSettings;
import de.mameie.databasemanager.sql.server.database.connection.DBConnectionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootTest
public class DummyTest {

    @Test
    public void testA() throws SQLException {
        Connection con = DBServerConnectionFactory.getInstance(DBServerSettings.CLOUD_XXL).getConnection();
        DatabaseMetaData metaData = con.getMetaData();
        ResultSet databases = metaData.getCatalogs();
        while (databases.next()) {
            String dbName = databases.getString("TABLE_CAT");
            System.out.println("Database: " + dbName);
        }
        con.close();
    }


    @Test
    public void test() throws SQLException {
        Connection con = DBConnectionFactory.getInstance(DBServerSettings.CLOUD_XXL, "a_test").getConnection();

        // Tabellen erstellen
        String createCountriesTable = "CREATE TABLE Countries (\n" +
                "    CountryId int NOT NULL AUTO_INCREMENT,\n" +
                "    CountryName varchar(255) NOT NULL,\n" +
                "    PRIMARY KEY (CountryId)\n" +
                ")";

        String createPersonsTable = "CREATE TABLE Persons (\n" +
                "    Personid int NOT NULL AUTO_INCREMENT,\n" +
                "    PersonAktid int NOT NULL,\n" +
                "    LastName varchar(255) NOT NULL DEFAULT 'Mustermann',\n" +
                "    FirstName varchar(255) DEFAULT 'Max',\n" +
                "    Gender enum('Male','Female','Divers'),\n" +
                "    Age int,\n" +
                "    CountryId int,\n" +
                "    PRIMARY KEY (Personid,PersonAktid),\n" +
                "    FOREIGN KEY (CountryId) REFERENCES Countries(CountryId)\n" +
                ")";
        con.prepareStatement(createCountriesTable).execute();
        con.prepareStatement(createPersonsTable).execute();

        DatabaseMetaData metaData = con.getMetaData();

        // Tabelle "Countries" und "Persons" durchsuchen
        String[] tablesToCheck = {"Countries", "Persons"};
        for (String tableName : tablesToCheck) {
            System.out.println("Table: " + tableName);

            // Spaltendetails für jede Tabelle abrufen
            ResultSet columns = metaData.getColumns(null, null, tableName, null);
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String columnType = columns.getString("TYPE_NAME");
                int columnSize = columns.getInt("COLUMN_SIZE");
                boolean isNullable = columns.getInt("NULLABLE") == DatabaseMetaData.columnNullable;
                String autoIncrement = columns.getString("IS_AUTOINCREMENT");
                String defaultValue = columns.getString("COLUMN_DEF");

                System.out.printf("  Column Name: %s, Type: %s(%d), Nullable: %s, Auto Increment: %s, Default: %s%n",
                        columnName, columnType, columnSize, isNullable, autoIncrement, defaultValue);

                // Enum-Werte abrufen, falls der Typ Enum ist
                if (columnType.equalsIgnoreCase("enum")) {
                    String query = String.format("SHOW COLUMNS FROM %s LIKE '%s'", tableName, columnName);
                    ResultSet enumValues = con.prepareStatement(query).executeQuery();
                    if (enumValues.next()) {
                        String enumValueString = enumValues.getString("Type");
                        String[] enumValuesArray = enumValueString.substring(enumValueString.indexOf("(") + 1, enumValueString.indexOf(")")).split(",");
                        System.out.println("    Enum Values: ");
                        for (String value : enumValuesArray) {
                            System.out.println("      - " + value);
                        }
                    }
                }
            }

            // Primärschlüssel der Tabelle abrufen
            ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
            while (primaryKeys.next()) {
                String primaryKeyColumn = primaryKeys.getString("COLUMN_NAME");
                System.out.printf("  Primary Key Column: %s%n", primaryKeyColumn);
            }

            // Fremdschlüssel der Tabelle abrufen
            ResultSet foreignKeys = metaData.getImportedKeys(null, null, tableName);
            while (foreignKeys.next()) {
                String fkColumnName = foreignKeys.getString("FKCOLUMN_NAME");
                String pkTableName = foreignKeys.getString("PKTABLE_NAME");
                String pkColumnName = foreignKeys.getString("PKCOLUMN_NAME");

                System.out.printf("  Foreign Key: %s, References %s(%s)%n",
                        fkColumnName, pkTableName, pkColumnName);
            }
        }

        // Tabellen löschen
        //con.prepareStatement("DROP TABLE Persons").execute();
       // con.prepareStatement("DROP TABLE Countries").execute();
        con.close();
    }

}
