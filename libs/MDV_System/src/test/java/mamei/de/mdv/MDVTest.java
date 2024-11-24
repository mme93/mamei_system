package mamei.de.mdv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static mamei.de.mdv.system.System.GENERATOR_SYSTEM;

public class MDVTest {

    MDV mdv;

    @BeforeEach
    void setup(){
        mdv = MDV.builder().withGenerator().build();
    }

    @Test
    @DisplayName("Has default generator")
    void testDefaultGenerator(){
        Assertions.assertTrue(mdv.getLoadedSystemNames().contains(GENERATOR_SYSTEM));
    }
}
