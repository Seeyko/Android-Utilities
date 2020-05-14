package com.tomandrieu.utilities;

import com.tomandrieu.utilities.constants.UrlConstants;

public class SeeykoUrlUtils {
    public static String getInstaUrlFromPseudo(String pseudo) {
        return UrlConstants.INSTAGRAM_BASE_URL + "/" + pseudo;
    }

}
