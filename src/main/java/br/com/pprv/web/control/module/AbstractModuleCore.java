/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.module;

import br.com.pprv.model.util.SelectDB;
import java.sql.Connection;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;

/**
 *
 * @author JorgeFonseca
 */
public abstract class AbstractModuleCore {

    @EJB
    private SelectDB selectDB;

    public EntityManager getEM() {
        return selectDB.getEntityManager();
    }

    public Connection getConnection() throws SQLException {
        Session session = getEM().unwrap(Session.class);
        SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
        ConnectionProvider connectionProvider = sfi.getConnectionProvider();
        return connectionProvider.getConnection();
    }
}
