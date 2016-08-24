package cft.exam.client;

import cft.exam.shared.PointsEntity;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SearchTerminalServiceAsync {
    // TODO Add a method to ask server for list of points
    void getPoints(String searchQuery, AsyncCallback<PointsEntity[]> async);
}
