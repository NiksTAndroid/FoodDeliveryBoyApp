package com.prometteur.fooddeliveryboy.PojoModels;

import com.google.gson.annotations.SerializedName;

public class CommonResponse {


    @SerializedName("status")
    private Integer status;
    @SerializedName("msg")
    private String msg;
    @SerializedName("result")
    private Boolean result;

    @SerializedName("status")
    public Integer getStatus() {
        return status;
    }

    @SerializedName("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    @SerializedName("msg")
    public String getMsg() {
        return msg;
    }

    @SerializedName("msg")
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @SerializedName("result")
    public Boolean getResult() {
        return result;
    }

    @SerializedName("result")
    public void setResult(Boolean result) {
        this.result = result;
    }

}
