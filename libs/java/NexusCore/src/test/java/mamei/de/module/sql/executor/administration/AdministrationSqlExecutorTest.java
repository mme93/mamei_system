package mamei.de.module.sql.executor.administration;

import mamei.de.module.sql.connection.SqlConnectionContext;
import mamei.de.module.sql.query.clause.create.SqlCreateUser;
import mamei.de.module.sql.query.clause.drop.SqlDropUser;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdministrationSqlExecutorTest {

    private SqlConnectionContext context = new SqlConnectionContext("jdbc:h2:file:./data/mydb", "sa", "test", "test");

    @Test
    public void createUser() {
        String sql = "SELECT * FROM INFORMATION_SCHEMA.USERS"; // H2 User-Tabelle

        try (Connection conn = DriverManager.getConnection("jdbc:h2:file:./data/mydb", "sa", "test");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Liste der H2-Benutzer:");
            while (rs.next()) {
                String userName = rs.getString("USER");
                boolean admin = rs.getBoolean("ADMIN");
                System.out.println("- " + userName + " (Admin: " + admin + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (MockedStatic<SqlCreateUser> mockedStatic = mockStatic(SqlCreateUser.class)) {
            SqlCreateUser.SqlCreateUserBuilder builderMock = Mockito.mock(SqlCreateUser.SqlCreateUserBuilder.class);
            SqlCreateUser createUserMock = Mockito.mock(SqlCreateUser.class);

            mockedStatic.when(SqlCreateUser::builder).thenReturn(builderMock);

            when(builderMock.createUser(anyString(), anyString())).thenReturn(builderMock);
            when(builderMock.withPassword(anyString())).thenReturn(builderMock);
            when(builderMock.build()).thenReturn(createUserMock);

            when(createUserMock.toSql()).thenReturn("CREATE USER testUser PASSWORD '123'");

            SystemUser user = new SystemUser("testUser", "%", null);
            AdministrationSqlExecutor administrationSqlExecutor = new AdministrationSqlExecutor(context);
            int isCreated = administrationSqlExecutor.createSystemUser(user, "123");

            verify(createUserMock).toSql();
            assertEquals(isCreated,0);
        }
    }

    @Test
    public void deleteUser(){
        try (MockedStatic<SqlDropUser> mockedStatic = mockStatic(SqlDropUser.class)) {
            SqlDropUser.SqlDropUserBuilder builderMock = Mockito.mock(SqlDropUser.SqlDropUserBuilder.class);
            SqlDropUser dropUserMock = Mockito.mock(SqlDropUser.class);

            mockedStatic.when(SqlDropUser::builder).thenReturn(builderMock);

            when(builderMock.dropUser(anyString(), anyString())).thenReturn(builderMock);
            when(builderMock.build()).thenReturn(dropUserMock);

            when(dropUserMock.toSql()).thenReturn("DROP USER testUser");

            SystemUser user = new SystemUser("testUser", "%", null);
            AdministrationSqlExecutor administrationSqlExecutor = new AdministrationSqlExecutor(context);
            int isDeleted = administrationSqlExecutor.deleteSystemUser(user);

            verify(dropUserMock).toSql();
            assertEquals(isDeleted,1);
        }
    }


}
