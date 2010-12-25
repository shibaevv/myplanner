package com.argus.financials.myplanner.gwt.main.client;

import com.argus.financials.myplanner.commons.client.BasePair;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("MainService")
public interface MainService extends RemoteService {

    BasePair[] findClients();

    void logout();

}