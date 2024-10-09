package orm;

import lombok.extern.java.Log;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

@Log
public class DSLContextProvider {
    static {
        try {
            Class.forName(org.postgresql.Driver.class.getName());
        } catch (ClassNotFoundException ex) {
            log.log(Level.SEVERE, ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public static DSLContext getContext() {
        Connection conn;

        try {
            conn = DriverManager.getConnection(
                    JDBCProperties.getUrl(),
                    JDBCProperties.getUsername(),
                    JDBCProperties.getPassword());
        } catch (SQLException ex) {
            log.log(Level.SEVERE, ex.getMessage());
            throw new RuntimeException("Could not connect to the database");
        }
        return DSL.using(conn, SQLDialect.POSTGRES);
    }
}
