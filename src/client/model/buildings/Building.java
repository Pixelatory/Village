package client.model.buildings;

import client.engine.renderPrimitives.Rectangle;
import client.model.attack.Generative;
import client.model.village.Attackable;
import client.model.village.Movable;
import client.model.village.Upgradable;
import client.utility.Area;
import client.utility.Position;

import java.awt.*;
import java.io.Serializable;

/**
 * General abstract class that encompasses everything a building should do/have.
 * 
 * @author 6177000
 * @see ProductionBuilding
 */
public abstract class Building implements Upgradable, Attackable, Generative, Movable, Serializable {
	private float hp;
	private int level = 1;
	private Position pos;
	private Area area;
	private Rectangle rect;
	private Rectangle upgradingRect;
	private boolean isUpgrading = false;
	private boolean isMovable = true;
	private int upgradeTime;
	
	/**
	 * A function which sets the initial hp value, and position of the newly created Building.
	 * @param xPos int value
	 * @param yPos int value
	 */
	public Building(int xPos, int yPos) {
		this.hp = maxHP();
		this.pos = new Position(xPos,yPos);
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

	protected void setArea(int width, int height) {
		this.area = new Area(width,height);
	}
	
	public float HP() {
		return hp;
	}
	
	public int level() {
		return level;
	}

	public int getLevel() {
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
		if(isMovable()) {
			pos.setX(xPos);
			rect.setX(xPos);
			upgradingRect.setX(xPos);
		}
	}

	/**
	 * A function which sets the x position of a Building, and updates the rectangle positions as well.
	 * 
	 * @param yPos int value
	 */
	public void setYPos(int yPos) {
		if(isMovable()) {
			pos.setY(yPos);
			rect.setY(yPos);
			upgradingRect.setY(yPos);
		}
	}

	public Position getPosition() {
		return pos;
	}
	
	/**
	 * A function which returns the rectangle that represents a Building.
	 * 
	 * @return a Rectangle
	 * @see client.engine.renderPrimitives.Rectangle
	 */
	public Rectangle getRect() {
		if(!isUpgrading() && HP() > 0)
			return rect;
		else if (!isUpgrading() && HP() <= 0) {
			Rectangle tmp = new Rectangle(rect);
			tmp.setColour(tmp.getColour().darker());
			return tmp;
		} else if (isUpgrading() && HP() > 0)
			return upgradingRect;
		else if (isUpgrading() && HP() <= 0) {
			Rectangle tmp = new Rectangle(upgradingRect);
			tmp.setColour(tmp.getColour().darker());
			return tmp;
		}

		return new Rectangle(0,0,10,10, Color.white);
	}

	protected void setDefaultRect(Rectangle rect) {
		this.rect = rect;
	}

	protected void setUpgradingRect(Rectangle rect) {
		this.upgradingRect = rect;
	}

	protected Rectangle getDefaultRect() {
		return this.rect;
	}

	protected Rectangle getUpgradingRect() {
		return this.upgradingRect;
	}
	
	/**
	 * A function which sets the level of this Building.
	 * 
	 * @param level int value
	 */
	public void setLevel(int level) {
		this.level = level;
		this.hp = maxHP();
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
		else
			this.hp -= amount;
	}

	public boolean isMovable() {
		return this.isMovable;
	}

	public void setMovable(boolean value) {
		this.isMovable = value;
	}

	public void setUpgradeTime(int timeLeft) {
		this.upgradeTime = timeLeft;
	}

	public int getUpgradeTime() {
		return upgradeTime;
	}

	public abstract Object clone();
}