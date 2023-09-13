package net.sanabria.paltov.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ResponseRest {
    private ArrayList<HashMap<String, String>> metadata = new ArrayList<HashMap<String, String>>();

    public ArrayList<HashMap<String, String>> getMetadata(){
        return metadata;
    }

    public void setMetadata(String ... data) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("response", data[0]);
        map.put("code", data[1]);
        map.put("message", data[2]);
        map.put("date", data[3]);

        metadata.add(map);
    }
}
