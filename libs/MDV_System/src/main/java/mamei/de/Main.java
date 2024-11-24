package mamei.de;

import mamei.de.mdv.MDV;
import mamei.de.mdv.system.expression.DataGeneratorSystem;

import static mamei.de.mdv.system.System.DATA_GENERATOR_SYSTEM;

public class Main {

    public static void main(String[] args) {

        MDV mdv = MDV.builder().addSystem(DATA_GENERATOR_SYSTEM).build();
        DataGeneratorSystem system= (DataGeneratorSystem) mdv.getCastedSystem(DATA_GENERATOR_SYSTEM);
        System.out.println(system.getSystemName());
        system.action("asdfg");
    }
}