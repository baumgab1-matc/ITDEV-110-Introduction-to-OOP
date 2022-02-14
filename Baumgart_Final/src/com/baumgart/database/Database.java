package com.baumgart.database;

import com.baumgart.model.Owner;
import com.baumgart.model.Walk;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//the "database" of the app
public class Database implements Serializable {

    private final long serialVersionUID = 983L;
    private List<Owner> owners;
    //holds a local data formatted as MM-dd-yyyy and a list of that days walks
    private final Map<String, List<Walk>> walks;

    public Database() {
        this.owners = new ArrayList<>();
        this.walks = new HashMap<>();
    }

    public Database(List<Owner> owners, Map<String, List<Walk>> walks) {
        this.owners = owners;
        this.walks = walks;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    public Map<String, List<Walk>> getWalks() {
        return walks;
    }


}
