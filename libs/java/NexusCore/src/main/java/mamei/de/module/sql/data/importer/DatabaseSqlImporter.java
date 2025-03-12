package mamei.de.module.sql.data.importer;

import mamei.de.core.exception.NexusCoreIllegalArgumentException;
import mamei.de.core.model.EFile;
import mamei.de.module.sql.connection.ConnectionCredentials;
import mamei.de.module.sql.executor.database.table.TableSqlExecutor;
import mamei.de.module.sql.executor.database.table.model.MetaData;
import mamei.de.module.sql.executor.database.table.model.Row;
import mamei.de.module.sql.executor.database.table.row.RowSqlExecutor;
import mamei.de.module.sql.extractor.MetaDataSqlExtractor;
import mamei.de.module.sql.query.dataset.ISqlDataset;
import mamei.de.module.sql.query.dataset.SqlDataset;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.*;

public class DatabaseSqlImporter {

    private ConnectionCredentials connectionContext;
    private MetaDataSqlExtractor metaDataSqlExtractor;
    private TableSqlExecutor tableSqlExecutor;

    public DatabaseSqlImporter(ConnectionCredentials connectionContext) throws SQLException {
        this.connectionContext = connectionContext;
        this.metaDataSqlExtractor = new MetaDataSqlExtractor(connectionContext);
        this.tableSqlExecutor = new TableSqlExecutor(connectionContext);
    }

    public void importJSONFile() {

    }

    public void importJSONString() {

    }

    public void importFromFile(EFile eFile, String filePath) throws SQLException {
        switch (eFile) {
            case CSV -> importCSV(filePath);
            case JSON -> importJSON(filePath);
            case TXT -> importTXT(filePath);
            default ->
                    throw new NexusCoreIllegalArgumentException(String.format("No file found by name %s", eFile.name()));
        }
    }

    private void importJSON(String filePath) {


    }

    private void importTXT(String filePath) {


    }


    private void importCSV(String filePath) throws SQLException {
        List<Row> rows = new ArrayList<>();
        List<String> headers = new ArrayList<>();
        try (Reader reader = new FileReader(filePath); CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            headers = csvParser.getHeaderNames();
            for (CSVRecord record : csvParser) {
                csvParser.getHeaderNames().forEach(columnName -> rows.add(new Row(Arrays.stream(record.get(columnName).split(";")).toList())));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (tableSqlExecutor.exist(connectionContext.getTableName())) {
            List<ISqlDataset> datasets = new ArrayList<>();
            for (Row row : rows) {
                for (int i = 0; i < row.getValues().size(); i++) {
                    datasets.add(SqlDataset.builder().addData(headers.get(i), row.getValues().get(i)).build());
                }
            }
            RowSqlExecutor rowSqlExecutor = new RowSqlExecutor(connectionContext);
            rowSqlExecutor.addRows(datasets, connectionContext.getTableName());
        }
    }

    /**
     * private void importCSV(String filePath) {
     * List<Map<String, String>> data = new ArrayList<>();
     * try (Reader reader = new FileReader(filePath);
     * CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
     * csvParser.getHeaderNames();
     * for (CSVRecord record : csvParser) {
     * Map<String, String> row = new HashMap<>();
     * for (String column : csvParser.getHeaderNames()) {
     * row.put(column, record.get(column));
     * }
     * data.add(row);
     * }
     * } catch (IOException e) {
     * throw new RuntimeException(e);
     * }
     * data.forEach(System.out::println);
     * }
     */

    private void saveTable() {

    }

    private boolean isMetaDataValid(List<String> headers) throws SQLException {
        List<MetaData> metaData = metaDataSqlExtractor.loadMetaData();

        return true;
    }
}
