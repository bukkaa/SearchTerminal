package cft.exam.server;

import cft.exam.client.SearchTerminalService;
import cft.exam.shared.PointsEntity;
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

        // TODO Ошибка при иниципализации сессии гибернейт. NoSuchMethodError и что то еще

        // Initialize the Hibernate session
//        sessionFactory = HibernateUtil.getSessionFactory();
//        session = sessionFactory.openSession();

        // Make a queries to Hibernate for specified Points
        query = session.createQuery("from PointsEntity");
        pointsList = query.list();

        // Close Hibernate session
        HibernateUtil.shutdown();

        return pointsList;

//        if (inputs[1].isEmpty()) {
//            query = session.createQuery("from PointsEntity where bankname = " + inputs[0]);
//            pointsList = query.list();
//            return pointsList;
//        }
//        else
//        {
//            query = session.createQuery("from PointsEntity where bankname = " + inputs[0] + " and city = " + inputs[1]);
//            pointsList = query.list();
//            return pointsList;
//        }
        /**
         * TEST AREA!!! DELETE LATER!
         */
//        PointsEntity point1 = new PointsEntity();
//        point1.setBankname("Sberbank");
//        point1.setAddress("ADDRESS POINT 1");
//        point1.setCountry("Russia");
//        point1.setCity("NSK");
//        PointsEntity point2 = new PointsEntity();
//        point2.setBankname("Sberbank");
//        point2.setAddress("ADDRESS POINT 2");
//        point2.setCountry("Russia");
//        point2.setCity("NSK");
//        PointsEntity point3 = new PointsEntity();
//        point3.setBankname("VTB");
//        point3.setAddress("ADDRESS POINT 3");
//        point3.setCountry("Russia");
//        point3.setCity("MSK");
//        PointsEntity point4 = new PointsEntity();
//        point4.setBankname("VTB");
//        point4.setAddress("ADDRESS POINT 4");
//        point4.setCountry("Russia");
//        point4.setCity("NSK");
//        PointsEntity point5 = new PointsEntity();
//        point5.setBankname("Sberbank");
//        point5.setAddress("ADDRESS POINT 5");
//        point5.setCountry("Russia");
//        point5.setCity("MSK");
//
//        pointsList.add(point1);
//        pointsList.add(point2);
//        pointsList.add(point3);
//        pointsList.add(point4);
//        pointsList.add(point5);

        //return pointsList;
    }
}
