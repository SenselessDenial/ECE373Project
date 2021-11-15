package gameLogic;

import game.Entity;
import game.Scene;
import game.Texture;
import game.components.Picture;
import game.enums.Buttons;
import game.util.Input;
import game.util.Logger;
import game.util.Vector2;
import game.util.Interfaces.Action;

public class NonogramCell extends Entity {
	//Fields
		public Picture picture;
		private Texture unchecked;
		private Texture checked;
		private Texture marked;
		private int state;
		
		//Constructors
		public NonogramCell(Vector2 pos, Scene scene, Texture unchecked, Texture checked, Texture marked) {
			super(pos, scene);
			position = pos;
			this.state = 0;
			this.unchecked = unchecked;
			this.checked = checked;
			this.marked = marked;
			this.setPicture(new Picture(this, unchecked));
			add(picture);
		}
		
		public NonogramCell(Scene scene) {
			this(new Vector2(0,0), scene, null, null, null);
		}
		
		//Methods
		@Override
		public void update() {
			super.update();
			
			if (this.collide(Input.getMousePos()) && Input.released(Buttons.LeftButton)) {
				this.swapTexture(1);
			}
			
			else if (this.collide(Input.getMousePos()) && Input.released(Buttons.RightButton)) {
				this.swapTexture(2);
			}
		}
		
		public void setPicture(Picture picture) {
			this.remove(picture);
			this.picture = picture;
			add(picture);
			this.setHitbox(picture.getWidth(), picture.getHeight());
		}
		
		public void swapTexture(int state) {
			if (state == 1 && this.picture.getTexture() != checked) {
				this.state = 1;
				this.picture.setTexture(checked);
			}
			else if (state == 2 && this.picture.getTexture() != marked) {
				if (this.picture.getTexture() != checked) {
					this.state = 2;
					this.picture.setTexture(marked);
				}
			}
			else {
				this.picture.setTexture(unchecked);
				this.state = 0;
			}
			setPicture(this.picture);
			Logger.Log(this.state);
		}
		
		public int getState() {
			return this.state;
		}
		
		public void setState(int state) {
			if (state < 0 || state > 2) {
				return;
			}
			else {
				this.state = state;
				if (state == 0) {
					this.picture.setTexture(unchecked);
				}
				else if (state == 1) {
					this.picture.setTexture(checked);
				}
				else {
					this.picture.setTexture(marked);
				}
				setPicture(this.picture);
			}
		}
}
