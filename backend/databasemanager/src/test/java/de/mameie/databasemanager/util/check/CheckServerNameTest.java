package de.mameie.databasemanager.util.check;

import de.mameie.databasemanager.sql.server.connection.DBServerSettings;
import de.mameie.databasemanager.util.check.exception.ServerNameNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CheckServerNameTest {

    @Test
    void exist() {
        for(String serverName:DBServerSettings.SERVER_NAMES){
            Assertions.assertTrue(CheckServerName.exist(serverName));
        }

    }

    @Test
    void throwServerNameNotFoundException() {
        Assertions.assertThrows(
                ServerNameNotFoundException.class,
                () -> CheckServerName.exist( "test"),
                "No server found by name: test"
        );
    }
}
