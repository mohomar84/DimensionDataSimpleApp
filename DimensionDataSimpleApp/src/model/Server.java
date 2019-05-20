package model; 

public class Server
{
	
	public int id;

	public Server(){}

	public String name;

	public Server(int id, String name ){
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}