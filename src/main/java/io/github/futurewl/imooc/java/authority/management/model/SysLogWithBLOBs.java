package io.github.futurewl.imooc.java.authority.management.model;

public class SysLogWithBLOBs extends SysLog {
    private String oldvalue;

    private String newvalue;

    private byte[] operatorIp;

    public String getOldvalue() {
        return oldvalue;
    }

    public void setOldvalue(String oldvalue) {
        this.oldvalue = oldvalue == null ? null : oldvalue.trim();
    }

    public String getNewvalue() {
        return newvalue;
    }

    public void setNewvalue(String newvalue) {
        this.newvalue = newvalue == null ? null : newvalue.trim();
    }

    public byte[] getOperatorIp() {
        return operatorIp;
    }

    public void setOperatorIp(byte[] operatorIp) {
        this.operatorIp = operatorIp;
    }
}