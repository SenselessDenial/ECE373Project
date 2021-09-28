package game;

import java.awt.Color;

public class Picture extends Component {
	
	public Vector2 offset;
	private Texture image;
	
	public Picture(Texture image, boolean isActive) {
		offset = new Vector2(0, 0);
		this.image = image;
		this.isActive = isActive;
		this.isVisible = true;
	}
	
	public Picture(Texture image){
		this(image, false);
	}
	
	public Picture(String filename) {
		this(new Texture(filename), false);
	}
	
	public Vector2 getRenderPos() {
		if (this.getEntity() != null)
			return this.getEntity().position.subtract(offset);
		else
			return offset.inverse();
	}
	
	public int getWidth() {
		return image.getWidth();
	}
	
	public int getHeight() {
		return image.getHeight();
	}
	
	public void center() {
		offset.x = (float)getWidth() / 2;
		offset.y = (float)getHeight() / 2;
	}
	
	public Picture tint(Color color) {
		Picture p = new Picture(image);
		
		p.image = p.image.tint(color);
		
		return p;
	}
	
	public Picture border(Color color) {
		Picture p = new Picture(image);
		
		p.image = p.image.getBorder(color);
		
		return p;
	}
	
	
	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		image.render(getRenderPos());
	}

}
