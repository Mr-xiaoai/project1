package azw.entity;

public class Categories {
	private int id;
	private String category;
	public Categories() {
		
	}
	public Categories(int id, String category) {
		super();
		this.id = id;
		this.category = category;
	}
	public Categories(String category) {
		super();
		
		this.category = category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return this.category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
