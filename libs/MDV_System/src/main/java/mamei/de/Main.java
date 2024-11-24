package mamei.de;

import mamei.de.mdv.system.data.set.IDataSet;
import mamei.de.mdv.system.generator.expression.PersonGenerator;

public class Main {
    public static void main(String[] args) {
        PersonGenerator generator = new PersonGenerator();
        IDataSet personDataSet = generator.generate(10);

        personDataSet.getDataList().forEach(data -> {
            System.out.println(data.getAllAttributes());
        });
    }
}