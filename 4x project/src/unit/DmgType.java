package unit;

import exception.EnumException;

public enum DmgType {
	SHARP,BLUNT,RANGE;
	
	public static DmgType convertFromString(String s)throws EnumException
	{
		switch (s) {
		case "sharp":
			return DmgType.SHARP;
		case "blunt":
			return DmgType.BLUNT;
		case "range":
			return DmgType.RANGE;
		default:
			throw new EnumException(s+" n'est aps un type valable");
		}
	}
}
