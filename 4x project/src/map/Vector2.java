package map;

public class Vector2 {
	private int x;
	private int y;
	
	public Vector2(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public boolean Equals(Vector2 vec)
	{
		return ((vec.x == x) && (vec.y == y));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Vector2 add(Vector2 vec)
	{
		return new Vector2(x+vec.x,y+vec.y);
	}
	
	public Vector2 remove(Vector2 vec)
	{
		return new Vector2(x-vec.x,y-vec.y);
	}
}
