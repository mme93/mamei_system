package mamei.de.mdv.model;

import mamei.de.mdv.datasets.DataSet;

public class MDVResult implements IMDVResult{

    private String context;
    private DataSet dataSet;


    public MDVResult(String context) {
        this.context=context;
    }

    @Override
    public String getResultAsString() {
        return context;
    }

    @Override
    public String getResultAsJsonString() {
        return context;
    }
}
