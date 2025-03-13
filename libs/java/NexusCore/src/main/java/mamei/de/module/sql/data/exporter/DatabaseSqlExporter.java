package mamei.de.module.sql.data.exporter;

import mamei.de.core.utils.CheckValue;
import mamei.de.module.sql.connection.ConnectionCredentials;
import mamei.de.module.sql.executor.database.table.model.MetaData;
import mamei.de.module.sql.executor.database.table.model.Row;
import mamei.de.module.sql.executor.database.table.model.Table;
import mamei.de.module.sql.executor.database.table.row.RowSqlExecutor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;


import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class DatabaseSqlExporter {

    private ConnectionCredentials connectionContext;
    private RowSqlExecutor rowSqlExecutor;

    public DatabaseSqlExporter(ConnectionCredentials connectionContext) {
        CheckValue.isNotNull(connectionContext, "connectionContext");
        this.connectionContext = connectionContext;
    }

    public void exportJSONFile() throws SQLException {

    }

    public void exportJSONString() {

    }

    public void exportCSV(String filePath) {
        try {
            Table table = loadTable();
            writeCsvFile(filePath, table);
        } catch (SQLException | IOException e) {
            throw new RuntimeException("Error exporting CSV", e);
        }
    }

    private void writeCsvFile(String filePath, Table table) throws IOException {
        String[] headers = table.getColumns().stream()
                .map(MetaData::getField)
                .toArray(String[]::new);

        try (CSVPrinter printer = new CSVPrinter(new FileWriter(filePath), CSVFormat.DEFAULT.withHeader(headers))) {
            for (Row row : table.getRows()) {
                printer.printRecord(row.getValues());
            }
        }
    }

    private Table loadTable() throws SQLException {
        return new RowSqlExecutor(connectionContext).loadData();
    }

}
