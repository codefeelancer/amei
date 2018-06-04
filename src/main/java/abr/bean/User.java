package abr.bean;

import lombok.Data;

@Data
public class User {
	
	public class Priority{
		public static final String SUPER = "SUPER";
		public static final String GENERAL = "GENERAL";	
	}

	private Integer id;
	private String username;
    private String password;
	/**
	 * Administrator's priority
	 *<ul> SuperAdmin can create all kinds of admin</ul>
	 *<ul> GeneralAdmin cannot create other admin.</ul>
	 */
    private String priority;
    
	public User() {

	}
	
	public User(String username, String password, String priority) {
		this.username = username;
		this.password = password;
		this.priority = priority;
	}
	
	public User(Integer id, String username, String password, String priority) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", priority=" + priority + "]";
	}
	
	
}
