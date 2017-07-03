package mapWidget;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import map.Map;
import map.Vector2;

public class MapGroup extends Group{
	
	private TilesImageView[][] tileImageMap;
	private Map map;
	private Vector2 cameraFocus = new Vector2(0,0);
	private Vector2 cameraSize;
	private Vector2 formatMap;
	private float zoom;
	private final int imageDefaultSize;
	private int imageActualtSize;
	public MapGroup(Map map)
	{
		super();
		this.map = map;
		imageActualtSize = imageDefaultSize = TilesImageView.imageSize;
		formatMap = new Vector2(map.getWidth()*imageActualtSize, map.getHeight()*imageActualtSize);
		cameraSize = new Vector2(1280, 720);
		zoom = 1f;
		setFocus(0, 0);
		createTileMap();
		DragContext dg = new DragContext();
		this.setOnMousePressed(e->
		{
			dg.mouseAnchorX = e.getX();
            dg.mouseAnchorY = e.getY();
            e.consume();
		});
		
		this.setOnMouseDragged(e->
		{
			setFocus(cameraFocus.getX() - (int)((e.getX() - dg.mouseAnchorX)),
					 cameraFocus.getY() - (int)((e.getY() - dg.mouseAnchorY)));
			dg.mouseAnchorX = e.getX();
            dg.mouseAnchorY = e.getY();
            updateTileMap();
			e.consume();
		});
		
		this.setOnScroll(e->{
			if(e.getDeltaY()<0)
			{
				updateZoom(-1);
			}
			if(e.getDeltaY()>0)
			{
				updateZoom(1);
			}
		});	

		this.setFocusTraversable(true);

	}
	
	private void updateZoom(int i)
	{
		if(i==-1)
		{
			if(zoom>0.1)
			{
				if(updateImageSize(zoom-0.1f))
				{
					zoom-=0.1;
				}
			}
		}
		else
		{
			if(zoom<1f)
			{
				if(updateImageSize(zoom+0.1f))
				{
					zoom+=0.1;
				}
			}
		}
	}
	
	private boolean updateImageSize(float newZoom)
	{
		int tempSize = (int) (imageDefaultSize * newZoom);
		if(map.getWidth() * tempSize >= cameraSize.getX() && map.getHeight() * tempSize >= cameraSize.getY())
		{
			imageActualtSize = tempSize;
			formatMap.setX(map.getWidth()*imageActualtSize);
			formatMap.setY(map.getHeight()*imageActualtSize);
			for(int y = 0; y < map.getHeight(); y++)
			{
				for(int x = 0; x < map.getWidth(); x++)
				{
					tileImageMap[x][y].updateSize(newZoom);
				}
			}
			
			setFocus(cameraFocus.getX()-(map.getWidth()*(imageDefaultSize-imageActualtSize)),
					cameraFocus.getY()-(map.getHeight()*(imageDefaultSize-imageActualtSize)));
			updateTileMap();
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void setFocus(int x,int y)
	{
		if(x%formatMap.getX()-(cameraSize.getX()/2)<0)
		{
			x=cameraSize.getX()/2;
		}//-((imageDefaultSize-imageActualtSize)/2)
		else if(x%formatMap.getX()-(cameraSize.getX()/2)>formatMap.getX()-cameraSize.getX())
		{
			x=formatMap.getX()-(cameraSize.getX()/2);
		}
		if(y%formatMap.getY()-(cameraSize.getY()/2)<0)
		{
			y=cameraSize.getY()/2;
		}
		else if(y%formatMap.getY()-(cameraSize.getY()/2)>formatMap.getY()-cameraSize.getY())
		{
			y=formatMap.getY()-(cameraSize.getY()/2);
		}
		cameraFocus.setX(x%formatMap.getX());
		cameraFocus.setY(y%formatMap.getY());
	}
	
	private void createTileMap(){
		tileImageMap = new TilesImageView[map.getWidth()][map.getHeight()];
		for(int y = 0; y < map.getHeight(); y++)
		{
			for(int x = 0; x < map.getWidth(); x++)
			{
				tileImageMap[x][y] = new TilesImageView(map.getTilesMap()[x][y]);
				tileImageMap[x][y].setLayoutX((x*imageActualtSize)-(cameraFocus.getX()-cameraSize.getX()/2)-((imageDefaultSize-imageActualtSize)/2));
				tileImageMap[x][y].setLayoutY((y*imageActualtSize)-(cameraFocus.getY()-cameraSize.getY()/2)-((imageDefaultSize-imageActualtSize)/2));
				this.getChildren().add(tileImageMap[x][y]);
			}
		}
	}

	private void updateTileMap()
	{
		for(int y = 0; y < map.getHeight(); y++)
		{
			for(int x = 0; x < map.getWidth(); x++)
			{
				tileImageMap[x][y].setLayoutX((x*imageActualtSize)-(cameraFocus.getX()-cameraSize.getX()/2)-((imageDefaultSize-imageActualtSize)/2));
				tileImageMap[x][y].setLayoutY((y*imageActualtSize)-(cameraFocus.getY()-cameraSize.getY()/2)-((imageDefaultSize-imageActualtSize)/2));
			}
		}
	}
	
    private static final class DragContext {
        public double mouseAnchorX;
        public double mouseAnchorY;
        public double initialTranslateX;
        public double initialTranslateY;
        
    }
}

