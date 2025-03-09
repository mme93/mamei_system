package mamei.de.module.sql.query.clause.alias;

import mamei.de.core.exception.NexusCoreIsEmptyException;
import mamei.de.core.exception.NexusCoreNullPointerException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SqlColumnAliasTest {

    String param = "nexuscore";
    String alias = "nc";

    @Test
    public void shouldSetAlias() {
        SqlColumnAlias sqlColumnAlias = new SqlColumnAlias(param, alias);
        String result = sqlColumnAlias.getParamWithAlias();
        assertEquals(result, String.format("%s AS %s", param, alias));
    }

    @Test
    public void hasAlias() {
        SqlColumnAlias sqlColumnAlias = new SqlColumnAlias(param, alias);
        String result = sqlColumnAlias.getAlias();
        assertEquals(result, alias);
    }

    @Test(expected = NexusCoreIsEmptyException.class)
    public void throwBlankExceptionByParam() {
        SqlColumnAlias sqlColumnAlias = new SqlColumnAlias("", alias);
        String result = sqlColumnAlias.getAlias();
        assertEquals(result, alias);
    }

    @Test(expected = NexusCoreNullPointerException.class)
    public void throwNullPointerExceptionByParam() {
        SqlColumnAlias sqlColumnAlias = new SqlColumnAlias(null, alias);
        String result = sqlColumnAlias.getAlias();
        assertEquals(result, alias);
    }

    @Test(expected = NexusCoreIsEmptyException.class)
    public void throwBlankExceptionByAlias() {
        SqlColumnAlias sqlColumnAlias = new SqlColumnAlias(param, "");
        String result = sqlColumnAlias.getAlias();
        assertEquals(result, alias);
    }

    @Test(expected = NexusCoreNullPointerException.class)
    public void throwNullPointerExceptionByAlias() {
        SqlColumnAlias sqlColumnAlias = new SqlColumnAlias(param, null);
        String result = sqlColumnAlias.getAlias();
        assertEquals(result, alias);
    }
}
