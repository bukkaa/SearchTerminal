package test.gwt.hibernate.client;

import test.gwt.hibernate.shared.PointsEntity;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface SearchTerminalServiceAsync {
    void getPoints(String search, AsyncCallback<List<PointsEntity>> async);
}
