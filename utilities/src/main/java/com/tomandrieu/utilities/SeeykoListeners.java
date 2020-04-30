package com.tomandrieu.utilities;

import org.json.JSONArray;
import org.json.JSONObject;

public interface SeeykoListeners {
    void load();

    interface SimpleCallbackListener extends SeeykoListeners {
        void callback(Object result);
    }

    interface JSONObjectListener extends SeeykoListeners {
        void callback(JSONObject result);
    }

    interface JSONArrayListener extends SeeykoListeners {
        void callback(JSONArray result);
    }
}