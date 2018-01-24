package lpdata.domain;

public class Host {
	private String host_ids;
	private String name;
	private String ip;
	private Integer port;
	private String os_type;
	private String os_version;
	private String os_arch;
	private String login_name;
	private String login_pwd;
	private Integer defaultRName;
	private Integer disabledGuest;
	private Integer lockedPolicy;
	private Integer pwdcomplex;
	private String public_key;
	private String private_key;
	private String auth_type;
	private String record_hash;
	private Integer status;
	private String skip_dir;
	private Integer part_number;

	public String getHost_ids() {
		return host_ids;
	}

	public void setHost_ids(String host_ids) {
		this.host_ids = host_ids;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getOs_type() {
		return os_type;
	}

	public void setOs_type(String os_type) {
		this.os_type = os_type;
	}

	public String getOs_version() {
		return os_version;
	}

	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}

	public String getOs_arch() {
		return os_arch;
	}

	public void setOs_arch(String os_arch) {
		this.os_arch = os_arch;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getLogin_pwd() {
		return login_pwd;
	}

	public void setLogin_pwd(String login_pwd) {
		this.login_pwd = login_pwd;
	}

	public Integer getDefaultRName() {
		return defaultRName;
	}

	public void setDefaultRName(Integer defaultRName) {
		this.defaultRName = defaultRName;
	}

	public Integer getDisabledGuest() {
		return disabledGuest;
	}

	public void setDisabledGuest(Integer disabledGuest) {
		this.disabledGuest = disabledGuest;
	}

	public Integer getLockedPolicy() {
		return lockedPolicy;
	}

	public void setLockedPolicy(Integer lockedPolicy) {
		this.lockedPolicy = lockedPolicy;
	}

	public Integer getPwdcomplex() {
		return pwdcomplex;
	}

	public void setPwdcomplex(Integer pwdcomplex) {
		this.pwdcomplex = pwdcomplex;
	}

	public String getPublic_key() {
		return public_key;
	}

	public void setPublic_key(String public_key) {
		this.public_key = public_key;
	}

	public String getPrivate_key() {
		return private_key;
	}

	public void setPrivate_key(String private_key) {
		this.private_key = private_key;
	}

	public String getAuth_type() {
		return auth_type;
	}

	public void setAuth_type(String auth_type) {
		this.auth_type = auth_type;
	}

	public String getRecord_hash() {
		return record_hash;
	}

	public void setRecord_hash(String record_hash) {
		this.record_hash = record_hash;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSkip_dir() {
		return skip_dir;
	}

	public void setSkip_dir(String skip_dir) {
		this.skip_dir = skip_dir;
	}

	public Integer getPart_number() {
		return part_number;
	}

	public void setPart_number(Integer part_number) {
		this.part_number = part_number;
	}

}
