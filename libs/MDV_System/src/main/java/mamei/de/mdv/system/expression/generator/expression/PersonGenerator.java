package mamei.de.mdv.system.expression.generator.expression;

import mamei.de.mdv.system.data.Data;
import mamei.de.mdv.system.data.IData;
import mamei.de.mdv.system.data.entities.person.Person;
import mamei.de.mdv.system.data.set.DataSet;
import mamei.de.mdv.system.data.set.IDataSet;
import mamei.de.mdv.system.expression.generator.Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonGenerator extends Generator {

    private static final String[] FIRST_NAMES = {"John", "Jane", "Alice", "Bob"};
    private static final String[] LAST_NAMES = {"Smith", "Doe", "Brown", "Johnson"};

    @Override
    public IDataSet generate(int amount) {
        List<Data> dataList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < amount; i++) {
            Data personData = new Data(Person.IDENTIFIER);
            personData.setAttribute("firstName", FIRST_NAMES[random.nextInt(FIRST_NAMES.length)]);
            personData.setAttribute("lastName", LAST_NAMES[random.nextInt(LAST_NAMES.length)]);
            personData.setAttribute("age", random.nextInt(50) + 20);
            dataList.add(personData);
        }

        validateGeneratedData(dataList.stream().map(data -> (IData) data).toList());
        IDataSet dataSet = new DataSet(1, "GeneratedPersons");
        dataSet.addData(dataList.stream().map(data -> (IData) data).toList());
        return dataSet;
    }
}