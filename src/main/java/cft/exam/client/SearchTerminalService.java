package cft.exam.client;

import cft.exam.shared.PointsEntity;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("searchPoints")
public interface SearchTerminalService extends RemoteService {
    List<PointsEntity> getPoints(String search);
}
