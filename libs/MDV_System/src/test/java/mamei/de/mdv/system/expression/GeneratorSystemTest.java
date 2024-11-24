package mamei.de.mdv.system.expression;

import mamei.de.mdv.system.System;
import mamei.de.mdv.system.module.ESystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GeneratorSystemTest {

    GeneratorSystem generator;

    @BeforeEach
    void setup(){
        generator = new GeneratorSystem(System.GENERATOR_SYSTEM);
    }

    @Test
    @DisplayName("Generate get correct system typ")
    void testSystemTyp(){
        Assertions.assertEquals(generator.getSystemTyp(), ESystem.GENERATOR);
    }
}
