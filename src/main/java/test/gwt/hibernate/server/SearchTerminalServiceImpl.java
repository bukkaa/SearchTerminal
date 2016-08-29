package test.gwt.hibernate.server;

import test.gwt.hibernate.client.SearchTerminalService;
import test.gwt.hibernate.shared.PointsEntity;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchTerminalServiceImpl extends RemoteServiceServlet implements SearchTerminalService {

    private List<PointsEntity> pointsList = new ArrayList<>();
    private SessionFactory sessionFactory;
    private Session session;

    /**
     * Constructor with Hibernate session initializing
     */
    public SearchTerminalServiceImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
    }

    /**
     * Get the specified Points from DB (MySQL) through Hibernate
     * @param search the assembled and validated inputs from TextBoxes
     * @return  the array of PointsEntity
     */

    @Override
    public List<PointsEntity> getPoints(String search) {

        String[] inputs = search.split(" ");
        Query query;

        // Send the full table to the GWT Module Loading
        if (inputs[0].equals("onModuleLoad")) {
            query = session.createQuery("from PointsEntity");
            pointsList = query.list();
        }
        else {
            // Make a query to Hibernate for specified Points
            query = session.createQuery("from PointsEntity where bankname = :bank and city = :city");
            query.setParameter("bank", inputs[0]);
            query.setParameter("city", "*");
            if (inputs.length == 2) query.setParameter("city", inputs[1]);
            pointsList = query.list();
        }

        // Close Hibernate session
        HibernateUtil.shutdown();
        return pointsList;
    }
}
