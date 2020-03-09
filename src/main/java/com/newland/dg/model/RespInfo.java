package com.newland.dg.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by hxw on 2017/11/8.
 */
public class RespInfo implements Serializable {
    private static final long serialVersionUID = 3276950235199932255L;
    public static final String RESP_SUCCESS = "1";
    public static final String RESP_FAILURE = "0";

    /**
     * 响应编码: 1: 表示成功；其他表示失败
     */
    private String respResult;
    /**
     * 响应失败时，对应的错误编号
     */
    @JsonInclude(Include.NON_NULL)
    private String respErrorCode;
    /**
     * 响应失败时，对应的错误描述
     */
    @JsonInclude(Include.NON_NULL)
    private String respErrorDesc;
    /**
     * 应答的业务数据
     */
    @JsonInclude(Include.NON_NULL)
    private Object respData;
    /**
     * 分页情况下,
     */
    @JsonInclude(Include.NON_NULL)
    private Integer dataTotalCount;

    @JsonInclude(Include.NON_NULL)
    private Integer isBilling;

    public RespInfo() {
        this(RespInfo.RESP_SUCCESS, null, null, null, null);
    }

    public RespInfo(Object respData) {
        this(RespInfo.RESP_SUCCESS, null, null, respData, null);
    }

    public RespInfo(Object respData, Integer dataTotalCount) {
        this(RespInfo.RESP_SUCCESS, null, null, respData, dataTotalCount);
    }

    public RespInfo(String respErrorCode, String respErrorDesc) {
        this(RespInfo.RESP_FAILURE, respErrorCode, respErrorDesc, null, null);
    }

    public RespInfo(String respResult, String respErrorCode, String respErrorDesc, Object respData) {
        this(respResult, respErrorCode, respErrorDesc, respData, null);
    }

    public RespInfo(String respResult, String respErrorCode, String respErrorDesc, Object respData, Integer dataTotalCount) {
        this.respResult = respResult;
        this.respErrorCode = respErrorCode;
        this.respErrorDesc = respErrorDesc;
        this.respData = respData;
        this.dataTotalCount = dataTotalCount;
    }

    public String getRespResult() {
        return respResult;
    }

    public void setRespResult(String respResult) {
        this.respResult = respResult;
    }

    public String getRespErrorCode() {
        return respErrorCode;
    }

    public void setRespErrorCode(String respErrorCode) {
        this.respErrorCode = respErrorCode;
    }

    public String getRespErrorDesc() {
        return respErrorDesc;
    }

    public void setRespErrorDesc(String respErrorDesc) {
        this.respErrorDesc = respErrorDesc;
    }

    public Object getRespData() {
        return respData;
    }

    public void setRespData(Object respData) {
        this.respData = respData;
    }

    public Integer getDataTotalCount() {
        return dataTotalCount;
    }

    public void setDataTotalCount(Integer dataTotalCount) {
        this.dataTotalCount = dataTotalCount;
    }

    public Integer getIsBilling() {
        return isBilling;
    }

    public void setIsBilling(Integer isBilling) {
        this.isBilling = isBilling;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
