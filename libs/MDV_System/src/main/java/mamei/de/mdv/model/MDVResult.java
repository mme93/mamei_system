package mamei.de.mdv.model;

public class MDVResult implements IMDVResult{

    private String context;
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
