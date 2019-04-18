package io.github.futurewl.imooc.java.authority.management.model;

import java.util.Date;

public class SysRole {
    private Integer id;

    private String name;

    private Integer type;

    private Integer status;

    private String remark;

    private String operator;

    private Date operatorTime;

    private byte[] operatorIp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public byte[] getOperatorIp() {
        return operatorIp;
    }

    public void setOperatorIp(byte[] operatorIp) {
        this.operatorIp = operatorIp;
    }
}