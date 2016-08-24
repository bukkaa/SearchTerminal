package cft.exam.client;

import cft.exam.shared.PointsEntity;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface SearchTerminalServiceAsync {
    void getPoints(String search, AsyncCallback<List<PointsEntity>> async);
}
