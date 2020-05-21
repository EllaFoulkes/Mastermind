package Mastermind;
import acm.graphics.*;
import java.util.*;
import acm.program.*;
import acm.util.RandomGenerator;
import java.awt.*;

public class Peg extends GCompound{
	private Color color;
	
	public Peg(Color x) {
		color=x;
		//double x, double y, double width, double height, double start, double sweep)
		GArc cap= new GArc (0, 10, 20, 20, 0, 180);
		GRoundRect stub= new GRoundRect(6, 19, 8, 17);
		cap.setFilled(true);
		cap.setFillColor(color);
		stub.setFilled(true);
		stub.setFillColor(color);
		color=x;
		add (stub);
		add(cap);
	}
	
	public void setColor(Color x) {
		this.color=x;
	}
	
	public Color getColor() {
		return color;
	}
	
}
