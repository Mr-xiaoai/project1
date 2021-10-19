package azw.entity;

import java.util.ArrayList;
import java.util.List;

import azw.dao.DBHelper;

/**
 * (NEWS)
 * 
 * @author bianj
 * @version 1.0.0 2020-03-25
 */
public class News implements java.io.Serializable {
	/** 版本号 */
	private static final long serialVersionUID = 5105158734691678731L;

	/**  */
	private int id;
	private int cateid;
	/**  */
	private String title;

	/**  */
	private String content;

	/**  */
	private int userid;

	
	private String category;
	private String nickname;
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */

	public News() {

	}

	public News(int id,int cateid, String title, String content, int userid) {
		super();
		this.id = id;
		this.cateid=cateid;
		this.title = title;
		this.content = content;
		this.userid = userid;
	}

	public News(int cateid,String title, String content, int userid) {
		super();
		this.cateid=cateid;
		this.title = title;
		this.content = content;
		this.userid = userid;
	}

	public int getCateId() {
		return this.cateid;
	}

	
	public void setCateId(int cateid) {
		this.cateid = cateid;
	}


	public int getId() {
		return this.id;
	}

	/**
	 * 设置
	 * 
	 * @param id
	 * 
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * 设置
	 * 
	 * @param title
	 * 
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * 设置
	 * 
	 * @param content
	 * 
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取
	 * 
	 * @return
	 */
	public int getUserid() {
		return this.userid;
	}

	/**
	 * 设置
	 * 
	 * @param userid
	 * 
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}
}