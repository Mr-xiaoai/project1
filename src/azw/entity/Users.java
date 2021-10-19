package azw.entity;

public class Users {
	private int id;
	private String username;
	private String pwd;
	private String email;
	private String nickname;
	private String role;
	private String thumb;
	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public Users(){}
	
	public Users( String username, String pwd, String email) {
		super();
		
		this.username = username;
		this.pwd = pwd;
		this.email = email;
	}

	public Users(int id, String username, String pwd, String email) {
		super();
		this.id = id;
		this.username = username;
		this.pwd = pwd;
		this.email = email;
	}
	public String getRole() {
		return this.role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getNickName() {
		return this.nickname;
	}
	public void setNickName(String nickname) {
		this.nickname = nickname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", pwd=" + pwd
				+ ", email=" + email + "]";
	}

	
}
