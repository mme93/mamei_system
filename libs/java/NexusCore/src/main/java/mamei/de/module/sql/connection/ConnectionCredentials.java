package mamei.de.module.sql.connection;

import lombok.Getter;
import lombok.Setter;
import mamei.de.core.utils.CheckValue;
import mamei.de.core.utils.CompareValue;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Getter
@Setter
public class ConnectionCredentials {

    private String ip;
    private String userName;
    private String password;
    private String serverName;
    private String databaseName;
    private String tableName;

    public ConnectionCredentials(String ip, String userName, String password, String serverName) {
        CheckValue.isNotBlank(ip, "ip");
        CheckValue.isNotBlank(userName, "userName");
        CheckValue.isNotNull(password, "password");
        CheckValue.isNotBlank(serverName, "serverName");
        this.ip = ip;
        this.userName = userName;
        this.password = password;
        this.serverName = serverName;
    }

    public ConnectionCredentials(String ip, String userName, String password, String serverName, String databaseName) {
        CheckValue.isNotBlank(ip, "ip");
        CheckValue.isNotBlank(userName, "userName");
        CheckValue.isNotNull(password, "password");
        CheckValue.isNotBlank(serverName, "serverName");
        CheckValue.isNotBlank(databaseName, "databaseName");
        this.ip = ip;
        this.userName = userName;
        this.password = password;
        this.serverName = serverName;
        this.databaseName = databaseName;
    }

    public ConnectionCredentials(String ip, String userName, String password, String serverName, String databaseName, String tableName) {
        CheckValue.isNotBlank(ip, "ip");
        CheckValue.isNotBlank(userName, "userName");
        CheckValue.isNotNull(password, "password");
        CheckValue.isNotBlank(serverName, "serverName");
        CheckValue.isNotBlank(databaseName, "databaseName");
        CheckValue.isNotBlank(tableName, "tableName");
        this.ip = ip;
        this.userName = userName;
        this.password = password;
        this.serverName = serverName;
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    public boolean isValidIp() {
        try {
            InetAddress.getByName(ip);
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }


    public String getIp() {
        if (CompareValue.isNotNull(databaseName)) {
            return String.format("%s/%s", ip, databaseName);
        }
        return ip;
    }

    public boolean match(ConnectionCredentials context) {
        return this.ip == context.getIp() &&
                this.serverName == context.getServerName() &&
                this.databaseName == context.getDatabaseName();
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
