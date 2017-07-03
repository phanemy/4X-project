package mapWidget;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import map.Tiles;

public class TilesImageView extends ImageView{
	/*private final Image grassImage = new Image(getClass().getResourceAsStream("grassTile.png"));
	private final Image hillImage = new Image(getClass().getResourceAsStream("hillTile.png"));
	private final Image mountainImage = new Image(getClass().getResourceAsStream("mountainTile.png"));
	private final Image waterImage = new Image(getClass().getResourceAsStream("waterTile.png"));
	*/public static final int imageSize = 120;
	private Tiles tile;
	private final int defaultSize;//= (int) waterImage.getHeight();
	
	public TilesImageView(Tiles t){
		super();
		tile=t;
		setImage();
		defaultSize = (int) this.getImage().getHeight();
	}
		
	public void updateSize(float zoom)
	{
		this.setScaleX(zoom);
		this.setScaleY(zoom);
	}
	
	private void setImage()
	{
		switch((int)tile.getHeight())
		{
			case 0:
				super.setImage(new Image(getClass().getResourceAsStream("waterTile.png")));
				break;
			case 1:
				super.setImage(new Image(getClass().getResourceAsStream("grassTile.png")));
				break;
			case 2:
				super.setImage(new Image(getClass().getResourceAsStream("hillTile.png")));
				break;
			default:
				super.setImage(new Image(getClass().getResourceAsStream("grassTile.png")));
				break;
		}
	}
}
