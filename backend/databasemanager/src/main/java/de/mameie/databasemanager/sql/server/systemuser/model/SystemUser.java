package de.mameie.databasemanager.sql.server.systemuser.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SystemUser {

    private String name;
    private String host;
    private String grant;

}
