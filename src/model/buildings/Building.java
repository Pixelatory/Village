package model.buildings;

import engine.renderPrimitives.Rectangle;
import org.w3c.dom.css.Rect;
import utility.Area;
import utility.Position;
import model.village.Attackable;
import model.village.Upgradable;

import java.awt.*;

/**
 * General abstract class that encompasses everything a building should do/have.
 * 
 * @author 6177000
 * @see ProductionBuilding
 */
public abstract class Building implements Upgradable, Attackable {
	protected float hp;
	protected int level = 1;
	protected Position pos;
	protected Area area;
	protected Rectangle rect;
	protected Rectangle upgradingRect;
	protected boolean isUpgrading = false;
	protected boolean isDestroyed = false;
	
	/**
	 * A function which sets the initial hp value, and position of the newly created Building.
	 * @param xPos int value
	 * @param yPos int value
	 */
	public Building(int xPos, int yPos) {
		this.hp = maxHP();
		this.pos = new Position(xPos,yPos);
	}

	public void setDestroyed(boolean value) {
		this.isDestroyed = value;
	}

	public boolean isDestroyed() {
		return this.isDestroyed;
	}
	
	/**
	 * A getter function which returns the name of this building.
	 * 
	 * @return string value
	 */
	public abstract String getName();
	
	public boolean canUpgrade() {
		return level() < maxLevel();
	}
	
	public void performUpgrade() {
		if(canUpgrade()) {
			this.level++;
			this.hp = maxHP();
		}
	}
	
	public float HP() {
		return hp;
	}
	
	public int level() {
		return level;
	}
	
	/**
	 * A getter function which returns the height of a Building.
	 * 
	 * @return int value
	 */
	public int height() {
		return area.getHeight();
	}
	
	/**
	 * A getter function which returns the width of a Building.
	 * 
	 * @return int value
	 */
	public int width() {
		return area.getWidth();
	}
	
	/**
	 * A getter function which returns the area of a Building.
	 * 
	 * @return int value
	 */
	public int area() {
		return area.getArea();
	}
	
	/**
	 * A getter function which returns the x position of a Building.
	 * 
	 * @return int value
	 */
	public int xPos() {
		return pos.getX();
	}
	
	/**
	 * A getter function which returns the y position of a Building.
	 * 
	 * @return int value
	 */
	public int yPos() {
		return pos.getY();
	}
	
	/**
	 * A function which sets the x position of a Building, and updates the rectangle positions as well.
	 * 
	 * @param xPos int value
	 */
	public void setXPos(int xPos) {
		pos.setX(xPos);
		rect.setX(xPos);
		upgradingRect.setX(xPos);
	}

	/**
	 * A function which sets the x position of a Building, and updates the rectangle positions as well.
	 * 
	 * @param yPos int value
	 */
	public void setYPos(int yPos) {
		pos.setY(yPos);
		rect.setY(yPos);
		upgradingRect.setY(yPos);
	}
	
	/**
	 * A function which returns the rectangle that represents a Building.
	 * 
	 * @return a Rectangle
	 * @see engine.renderPrimitives.Rectangle
	 */
	public Rectangle getRect() {
		if(!isUpgrading() && !isDestroyed())
			return rect;
		else if (!isUpgrading() && isDestroyed()) {
			Rectangle tmp = rect;
			tmp.setColour(tmp.getColour().darker());
			return new Rectangle(tmp);
		} else if (isUpgrading() && !isDestroyed())
			return upgradingRect;
		else if (isUpgrading() && isDestroyed()) {
			Rectangle tmp = upgradingRect;
			tmp.setColour(tmp.getColour().darker());
			return new Rectangle(tmp);
		}

		return new Rectangle(0,0,10,10, Color.white);
	}
	
	/**
	 * A function which sets the level of this Building.
	 * 
	 * @param level int value
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	public boolean isUpgrading() {
		return isUpgrading;
	}
	
	public void setUpgrading(boolean isUpgrading) {
		this.isUpgrading = isUpgrading;
	}
	
	public void takeDamage(float amount) {
		if(this.hp - amount <= 0)
			this.hp = 0;
		this.hp -= amount;
	}
}