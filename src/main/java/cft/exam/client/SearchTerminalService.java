package cft.exam.client;

import cft.exam.shared.PointsEntity;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("searchPoints")
public interface SearchTerminalService extends RemoteService {
    PointsEntity[] getPoints(String searchQuery);
}
