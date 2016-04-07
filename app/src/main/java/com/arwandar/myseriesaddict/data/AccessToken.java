package com.arwandar.myseriesaddict.data;

import com.arwandar.myseriesaddict.data.dto.UserTest;

/**
 * Created by olivi on 01/04/2016.
 */
public class AccessToken {

    private String token;
    private String tokenType;

    public String getAccessToken() {
        return token;
    }

    public String getTokenType() {
        // OAuth requires uppercase Authorization HTTP header value for token type
        if ( ! Character.isUpperCase(tokenType.charAt(0))) {
            tokenType =
                    Character
                            .toString(tokenType.charAt(0))
                            .toUpperCase() + tokenType.substring(1);
        }

        return tokenType;
    }

    //private UserTest user;
    //private String token;
    //private String hash;
    //private String[] errors;

   // public String getAccessToken() {
    //    return token;
    //}


}

