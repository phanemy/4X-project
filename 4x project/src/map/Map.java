package map;

public class Map {
		private float[][] map;
		private Tiles[][] mapTile;
		private int mapWidth;
		private int mapHeight;
		
		public Map(int mapWidth, int mapHeight, float scale, int octaves, float persistance, float lacunarity)
		{
			this.mapWidth = mapWidth;
			this.mapHeight = mapHeight;
			map = Noise.GenerateNoiseMap(mapWidth, mapHeight, scale, octaves, persistance, lacunarity);
			//map = Noise.generateNoiseMap2(mapWidth, mapHeight);
			setMapHeigth();
			setMapTiles();
		}
		
		public float[][] getHeightMap()
		{
			return map;
		}
		
		public Tiles[][] getTilesMap()
		{
			return mapTile;
		}
		
		public int getWidth()
		{
			return mapWidth;
		}
		
		public int getHeight()
		{
			return mapHeight;
		}
		
		private void setMapHeigth()
		{
			for(int y = 0; y < mapHeight; y++)
			{
				for(int x = 0; x < mapWidth; x++)
				{
					if(map[x][y]<=0.2)
					{
						map[x][y] = 0;
					}
					else if(map[x][y]<=0.5)
					{
						map[x][y] = 1;
					}
					else if(map[x][y]<=0.7)
					{
						map[x][y] = 2;
					}
					else
					{
						map[x][y] = 3;
					}
				}
			}
		}

		private void setMapTiles()
		{
			mapTile = new Tiles[mapWidth][mapHeight];
			for(int y = 0; y < mapHeight; y++)
			{
				for(int x =0; x < mapWidth; x++)
				{
					mapTile[x][y] = new Tiles(x,y,map[x][y]);
				}
			}
		}
		
		private void affiche() {
			String temp ;
			 for(int j = 0; j < mapHeight; j++){
				 temp = "";
				 for(int i = 0; i < mapWidth; i++)
				 {
					 temp+= " "+map[i][j];
				 }
				 System.out.println(temp);
			 }
		}
		
		public static void main(String args[])
		{
			 Map test = new Map(16, 9, 7.6f,4,0.5f,2);
			 test.affiche();
		}

	}

