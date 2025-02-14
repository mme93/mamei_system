package mamei.de.module.sql.connection;

import lombok.Getter;
import lombok.Setter;
import mamei.de.core.utils.check.CheckParam;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Getter
@Setter
public class SqlConnectionContext {

    private String ip;
    private String userName;
    private String password;
    private String serverName;
    private String databaseName;

    public SqlConnectionContext(String ip, String userName, String password, String serverName) {
        CheckParam.isNotBlank(ip, "ip");
        CheckParam.isNotBlank(userName, "userName");
        CheckParam.isNotNull(password, "password");
        CheckParam.isNotBlank(serverName, "serverName");
        this.ip = ip;
        this.userName = userName;
        this.password = password;
        this.serverName = serverName;
    }

    public SqlConnectionContext(String ip, String userName, String password, String serverName, String databaseName) {
        CheckParam.isNotBlank(ip, "ip");
        CheckParam.isNotBlank(userName, "userName");
        CheckParam.isNotNull(password, "password");
        CheckParam.isNotBlank(serverName, "serverName");
        CheckParam.isNotBlank(databaseName, "databaseName");
        this.ip = ip;
        this.userName = userName;
        this.password = password;
        this.serverName = serverName;
        this.databaseName = databaseName;
    }

    public boolean isValidIp() {
        try {
            InetAddress.getByName(ip);
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "ConnectionContext{" +
                "ip='" + ip + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", serverName='" + serverName + '\'' +
                '}';
    }
}
