package Mastermind;
import acm.graphics.GCanvas;


public class Canvas extends GCanvas{

	private GCanvas canvas;


	public Canvas() {
		canvas = new GCanvas();
	}
	
	public GCanvas getCanvas() {
		return canvas;
	}
	
}
