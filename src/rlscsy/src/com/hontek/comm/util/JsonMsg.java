package com.hontek.comm.util;

import java.io.Serializable;

public class JsonMsg implements Serializable {
    private static final long serialVersionUID = 7939373463108604235L;
    private String            success          = "";
    private String            error            = "";
    private String            msg              = "";
    
    public String getSuccess () {
        return success;
    }
    
    public void setSuccess ( String success ) {
        this.success = success;
    }
    
    public String getError () {
        return error;
    }
    
    public void setError ( String error ) {
        this.error = error;
    }

    public String getMsg () {
        return msg;
    }

    public void setMsg ( String msg ) {
        this.msg = msg;
    }
    
}
