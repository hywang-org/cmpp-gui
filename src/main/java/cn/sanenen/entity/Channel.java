package cn.sanenen.entity;

public class Channel {
    /**
     * CMPP接口接入号
     */
    private String accessNo;

    /**
     * 通道ip
     */
    private String ip;

    /**
     * 通道端口
     */
    private Integer port;

    /**
     * 流速
     */
    private Integer speedLimit;

    /**
     * 登录名
     */
    private String loginAccount;

    /**
     * 登陆密码
     */
    private String loginPwd;

    /**
     * 
     */
    private String name;

    /**
     * cmpp版本，2、3
     */
    private Short cmppVersion;
    
	private String mobile;
	
    /**
     * CMPP接口接入号
     * @return access_no CMPP接口接入号
     */
    public String getAccessNo() {
        return accessNo;
    }

    /**
     * CMPP接口接入号
     * @param accessNo CMPP接口接入号
     */
    public void setAccessNo(String accessNo) {
        this.accessNo = accessNo == null ? null : accessNo.trim();
    }

    /**
     * 通道ip
     * @return ip 通道ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 通道ip
     * @param ip 通道ip
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 通道端口
     * @return port 通道端口
     */
    public Integer getPort() {
        return port;
    }

    /**
     * 通道端口
     * @param port 通道端口
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * 流速
     * @return speed_limit 流速
     */
    public Integer getSpeedLimit() {
        return speedLimit;
    }

    /**
     * 流速
     * @param speedLimit 流速
     */
    public void setSpeedLimit(Integer speedLimit) {
        this.speedLimit = speedLimit;
    }

    /**
     * 登录名
     * @return login_account 登录名
     */
    public String getLoginAccount() {
        return loginAccount;
    }

    /**
     * 登录名
     * @param loginAccount 登录名
     */
    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount == null ? null : loginAccount.trim();
    }

    /**
     * 登陆密码
     * @return login_pwd 登陆密码
     */
    public String getLoginPwd() {
        return loginPwd;
    }

    /**
     * 登陆密码
     * @param loginPwd 登陆密码
     */
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    /**
     * 
     * @return name 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * cmpp版本，2、3
     * @return cmpp_version cmpp版本，2、3
     */
    public Short getCmppVersion() {
        return cmppVersion;
    }

    /**
     * cmpp版本，2、3
     * @param cmppVersion cmpp版本，2、3
     */
    public void setCmppVersion(Short cmppVersion) {
        this.cmppVersion = cmppVersion;
    }
    
    public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}