package map;

import city.City;
import unit.Unit;

public class Tiles {
	private Vector2 position;
	private float height;
	private Unit unitIn;
	private City cityIn;
	
	public Tiles(int x, int y, float height){
		position = new Vector2(x,y);
		this.height =height;
		unitIn = null;
		cityIn = null;
	}
	
	public float getHeight()
	{
		return height;
	}
}
