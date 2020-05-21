package Mastermind;
import acm.graphics.*;
import java.util.*;
import acm.program.*;
import acm.util.RandomGenerator;
import java.awt.*;

public class Board  extends GCompound {
	GRoundRect rectangle;
	public static final int NUM_PEGS = 4;
	public static final int PEG_DIAMETER=20;
	public static final int NUM_GUESSES = 10;
	public static final int BOARD_HEIGHT =  (NUM_GUESSES+1)*2*PEG_DIAMETER +PEG_DIAMETER;
	public static final int BOARD_WIDTH =NUM_PEGS*PEG_DIAMETER*2 + PEG_DIAMETER;


		
	public Board(){
		rectangle = new GRoundRect(BOARD_HEIGHT, BOARD_WIDTH);
		rectangle.setFilled(true);
		rectangle.setFillColor(Color.BLUE);
		add (rectangle);
		for (int k=0; k<NUM_PEGS; k++) {
			for (int i=0; i<NUM_GUESSES; i++) {
				GOval emptyPeg =new GOval(BOARD_HEIGHT-2*PEG_DIAMETER-PEG_DIAMETER*2*i, PEG_DIAMETER+ 2*PEG_DIAMETER*k, PEG_DIAMETER, PEG_DIAMETER);
				emptyPeg.setFilled(true);
				emptyPeg.setFillColor(Color.BLACK);
				add (emptyPeg);
			}
			GRect square = new GRect(PEG_DIAMETER, PEG_DIAMETER+ 2*PEG_DIAMETER*k, PEG_DIAMETER, PEG_DIAMETER);
			square.setFilled(true);
			square.setFillColor(Color.WHITE);
			add(square);
		}
	
	}
	}