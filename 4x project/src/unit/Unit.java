package unit;

import exception.EnumException;
import exception.XmlException;
import utilities.XmlReader;

public class Unit {
	private String name;
	private int mvtSpeed;
	private  DmgType weaponType;
	private  int dmgValue;
	private  int initialHp;
	private  int actualHp;
	private  int bluntDef;
	private  int sharpDef;
	private  int rangeDef;
	private  int creationTime;
	private  int woodRequired;
	private  int stoneRequired;
	private  int silverRequired;
	private  int unKeep;
	private  int popValue;
	
	public Unit(int unitId)
	{
		try {
			name = XmlReader.findAtributeForUnitId(unitId, "name");
			mvtSpeed = Integer.parseInt(XmlReader.findAtributeForUnitId(unitId, "mvtSpeed"));
			weaponType = DmgType.convertFromString(XmlReader.findAtributeForUnitId(unitId, "dmgType"));
			dmgValue = Integer.parseInt(XmlReader.findAtributeForUnitId(unitId, "dmgValue"));
			initialHp = Integer.parseInt(XmlReader.findAtributeForUnitId(unitId, "initialHp"));
			actualHp = initialHp;
			bluntDef = Integer.parseInt(XmlReader.findAtributeForUnitId(unitId, "bluntDef"));
			sharpDef = Integer.parseInt(XmlReader.findAtributeForUnitId(unitId, "sharpDef"));
			rangeDef = Integer.parseInt(XmlReader.findAtributeForUnitId(unitId, "rangeDef"));
			creationTime = Integer.parseInt(XmlReader.findAtributeForUnitId(unitId, "creationTime"));;
			woodRequired = Integer.parseInt(XmlReader.findAtributeForUnitId(unitId, "woodRequired"));
			stoneRequired = Integer.parseInt(XmlReader.findAtributeForUnitId(unitId, "stoneRequired"));
			silverRequired = Integer.parseInt(XmlReader.findAtributeForUnitId(unitId, "silverRequired"));
			unKeep = Integer.parseInt(XmlReader.findAtributeForUnitId(unitId, "silverUnKeep"));
			popValue = Integer.parseInt(XmlReader.findAtributeForUnitId(unitId, "popValue"));
		} catch (XmlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EnumException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return name;
	}
	
	public int getMvtSpeed() {
		return mvtSpeed;
	}
	public DmgType getWeaponType() {
		return weaponType;
	}
	public int getDmgValue() {
		return dmgValue;
	}
	public int getInitialHp() {
		return initialHp;
	}
	public int getBluntDef() {
		return bluntDef;
	}
	public int getSharpDef() {
		return sharpDef;
	}
	public int getRangeDef() {
		return rangeDef;
	}
	public int getCreationTime() {
		return creationTime;
	}
	public int getWoodRequired() {
		return woodRequired;
	}
	public int getStoneRequired() {
		return stoneRequired;
	}
	public int getSilverRequired() {
		return silverRequired;
	}
	public int getUnKeep() {
		return unKeep;
	}
	public int getPopValue() {
		return popValue;
	}
	public int getActualHp() {
		return actualHp;
	}
	public void setActualHp(int actualHp) {
		this.actualHp = actualHp;
	}

	public static void main(String[] args)
	{
		Unit u = new Unit(0);
		System.out.println("a b "+u.getName());
		System.out.println("a b "+u.getWeaponType());
		System.out.println("a b "+u.getSharpDef());
		Unit u1 = new Unit(1);
		System.out.println("a b "+u1.getName());
		System.out.println("a b "+u1.getWeaponType());
		System.out.println("a b "+u1.getSharpDef());
		Unit u2 = new Unit(2);
		System.out.println("a b "+u2.getName());
		System.out.println("a b "+u2.getWeaponType());
		System.out.println("a b "+u2.getSharpDef());

	}
	
}
