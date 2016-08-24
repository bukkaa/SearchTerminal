package cft.exam.server;

import cft.exam.client.SearchTerminalService;
import cft.exam.shared.PointsEntity;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SearchTerminalServiceImpl extends RemoteServiceServlet implements SearchTerminalService {

    private PointsEntity[] points;

    /**
     * Get the specified Points from DB (MySQL) through Hibernate
     * @param searchQuery the assembled and validated inputs from TextBoxes
     * @return  the array of PointsEntity
     */
    public PointsEntity[] getPoints(String searchQuery) {

        // TODO Make a query to Hibernate for specified Points

        // TODO Return the result array of Points

        /**
         * TEST AREA!!! DELETE LATER!
         */

        PointsEntity point1 = new PointsEntity();
        point1.setBankname("Sberbank");
        point1.setAddress("ADDRESS POINT 1");
        point1.setCountry("Russia");
        point1.setCity("NSK");
        PointsEntity point2 = new PointsEntity();
        point2.setBankname("Sberbank");
        point2.setAddress("ADDRESS POINT 2");
        point2.setCountry("Russia");
        point2.setCity("NSK");
        PointsEntity point3 = new PointsEntity();
        point3.setBankname("VTB");
        point3.setAddress("ADDRESS POINT 3");
        point3.setCountry("Russia");
        point3.setCity("MSK");
        PointsEntity point4 = new PointsEntity();
        point4.setBankname("VTB");
        point4.setAddress("ADDRESS POINT 4");
        point4.setCountry("Russia");
        point4.setCity("NSK");
        PointsEntity point5 = new PointsEntity();
        point5.setBankname("Sberbank");
        point5.setAddress("ADDRESS POINT 5");
        point5.setCountry("Russia");
        point5.setCity("MSK");

        points = new PointsEntity[]{point1, point2, point3, point4, point5};

        return points;
    }
}
