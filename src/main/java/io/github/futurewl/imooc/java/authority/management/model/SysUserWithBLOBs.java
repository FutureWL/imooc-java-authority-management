package io.github.futurewl.imooc.java.authority.management.model;

public class SysUserWithBLOBs extends SysUser {
    private byte[] password;

    private byte[] operatorIp;

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public byte[] getOperatorIp() {
        return operatorIp;
    }

    public void setOperatorIp(byte[] operatorIp) {
        this.operatorIp = operatorIp;
    }
}