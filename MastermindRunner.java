package Mastermind;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.*;
import acm.program.*;
//import acm.util.RandomGenerator;

public class MastermindRunner extends GraphicsProgram {
	GRect control; 
	Peg theSelected;
	int roundNum=0;
	Board board;
	GOval [] thatRound;
	Color[] mastersCode;
	int pegsPlaced=0;
	GLabel submitButton;
	int numRed=0;
	int numWhite=0;
	int numPinkPegs=0;
	int numBluePegs=0;
	int numGreenPegs=0;
	int numWhitePegs=0;
	int numYellowPegs=0;
	int numPurplePegs=0;
//	GCanvas board;
	
	public void run() {
		addMouseListeners();
		setUpBoard();	

		setMasters();
		thatRound= new GOval[4];
		for (int i =0; i<thatRound.length; i++) {
//			thatRound[i]= new Peg(Color.BLACK);
			thatRound[i]=new GOval(20,20);
			thatRound[i].setFilled(true);
			thatRound[i].setColor(Color.BLACK);
		
		}
		for (int i=0; i<4; i++) {
			add (new Peg(mastersCode[i]), 30*i,500 );
		}
		while (roundNum<=10) {
			System.out.println("Round num:"+roundNum);
			//addMouseListeners();
			oneTurn();
			//addMouseListeners();

		}
		board.removeAll();
		add (new GLabel("You lost!", 20,20));
		for (int i=0; i<mastersCode.length; i++) {
			add (new Peg(mastersCode[i]), 40, 20+ 2*20*i);
		}

		
	}
	
	public void setUpBoard() {
		board = new Board();	
		add(board, 20, 20);
		control = new GRect(20, 300, 260, 30);
		control.setFilled(true);
		control.setFillColor(Color.BLACK);
		add (control);
		add (new Peg(Color.PINK), 40, 292);
		add (new Peg(Color.CYAN), 80, 292);
		add (new Peg(Color.GREEN), 120, 292);
		add (new Peg(Color.WHITE), 160, 292);
		add (new Peg(Color.YELLOW), 200, 292);
		add (new Peg(Color.MAGENTA), 240, 292);
		add (new GLabel("Welcome to Mastermind!"), 20, 350);
		add (new GLabel("Try to guess the random secret code."), 20, 370);
		add (new GLabel("It is made of four pegs of any of the six colors above."), 20, 390);
		add (new GLabel("A color can be used more than once or not at all!"), 20, 410);
		add (new GLabel("Don't forget that order matters... oh! And you only have ten tries!"), 20, 430);
		add (new GLabel("A red oval means that you have guessed a correct color, ",350,300));
		add (new GLabel("in a correct location ",350,320));
		add (new GLabel("And a white oval means that you guessed a correct color ",350,340));
		add (new GLabel("in an inncorrect location ",350,360));
		submitButton = new GLabel("Click here to enter guess", 40+board.getWidth(), 40);
		add (submitButton);
	}
	
	public void setMasters() {
		int num;
		mastersCode= new Color[4];
		for (int i=0; i<mastersCode.length; i++) {
			num=(int)(Math.random()*6+1);
			if (num==1) {
				mastersCode[i]=Color.PINK;
				numPinkPegs++;
			} else if(num==2) {
				mastersCode[i]=Color.CYAN;
				numBluePegs++;
			} else if(num==3) {
				mastersCode[i]=Color.GREEN;
				numGreenPegs++;
			} else if(num==4) {
				mastersCode[i]=Color.WHITE;
				numWhitePegs++;
			} else if(num==5) {
				mastersCode[i]=Color.YELLOW;
				numYellowPegs++;
			} else if(num==6) {
				mastersCode[i]=Color.MAGENTA;
				numPurplePegs++;
			}
		}
	}
	
	public void oneTurn() {
//		thatRound= new Peg[4];
		


		waitForClick();
	}

	public void mousePressed(MouseEvent e) {
		if (getElementAt(40+board.getWidth(), 60)!=null)
		remove(getElementAt(40+board.getWidth(), 60));
		if (e.getX()>=control.getX() &&e.getX()<= control.getX()+control.getWidth() && e.getY()>=control.getY() &&e.getY()<= control.getY()+control.getHeight()) {
			if (getElementAt(e.getX(), e.getY()).getColor()!=Color.BLACK){
				getNearestPeg(e.getX(), e.getY());
				add (theSelected, e.getX(), e.getY());
//				theSelected=getElementAt(e.getX(), e.getY());
//				System.out.print("heya");
				//addMouseListeners();

			}
		}
	}
	
	public void getNearestPeg(int x, int y) {
		if (x<=70) {
			theSelected= (new Peg(Color.PINK));
		} else if (x<=110) {
			theSelected=(new Peg(Color.CYAN));
		} else if (x<=150) {
			theSelected= (new Peg(Color.GREEN));
		} else if (x<=190) {
			theSelected= (new Peg(Color.WHITE));
		} else if (x<=230) {
			theSelected= (new Peg(Color.YELLOW));
		} else {
			theSelected= (new Peg(Color.MAGENTA));
		}
	}
	
	
	public void mouseDragged(MouseEvent e) {
		theSelected.setLocation(e.getX()-10, e.getY()-10);
	}

	public void mouseReleased(MouseEvent e) {
		GOval circle=new GOval(20,20);
		circle.setFilled(true);
		circle.setColor(theSelected.getColor());
		circle.setFillColor(theSelected.getColor());
		if (theSelected.getX()<board.getWidth()-((roundNum+1)*2*20) || theSelected.getX()>board.getWidth()-((roundNum)*2*20)) {
			remove(theSelected);
		} else if (!getElementAt(e.getX(),e.getY()).equals(submitButton)){
			System.out.println("releasingMouse");

			pegsPlaced++;

			if (theSelected.getY()<=60) {

				thatRound[0]=circle;
				System.out.println("thatRound[0].getColor"+thatRound[0].getColor());
//				thatRound[0]= new Peg(theSelected.getColor());
//				thatRound[0].setFillColor(theSelected.getColor());
			} else if(theSelected.getY()<=100) {
				thatRound[1]=circle;
				System.out.println("thatRound[1].getColor"+thatRound[1].getColor());
//				thatRound[1].setFillColor(theSelected.getColor());
			} else if (theSelected.getY()<=140) {
				thatRound[2]=circle;
				System.out.println("thatRound[2].getColor"+thatRound[2].getColor());
//				thatRound[2].setFillColor(theSelected.getColor());
			} else {
				thatRound[3]= circle;
				System.out.println("thatRound[3].getColor"+thatRound[3].getColor());
//				thatRound[3].setFillColor(theSelected.getColor());
			}
			remove(theSelected);
			updateBoard();

		}
	}
	
	public void updateBoard() {
		for (int i=0; i<thatRound.length; i++) {
			remove (thatRound[i]);
			add (thatRound[i], board.getWidth()-(roundNum*40+20), (40*i+40) );
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if (getElementAt(e.getX(), e.getY()).equals(submitButton)) {
//			for (GOval x: thatRound) {
//				
//			}
			if (pegsPlaced<4) {
				add (new GLabel("You have only placed " + pegsPlaced+ " pegs, please add "+ (4-pegsPlaced)+ " more!"), 40+board.getWidth(), 60);
			} else {
			compareGuess();

			System.out.println("Red pegs"+numRed);
			System.out.println("white pegs"+ numWhite);
			addCorrectnessPegs();
			System.out.println("in compare4"+roundNum);
			roundNum++;
			System.out.println("in compare4"+roundNum);
			setNewArr();
			}
		}
	}
	
	//counts both red and white
		public void compareGuess() {
			numRed=0;
			numWhite=0;
			int numPinkPegs1=numPinkPegs;
			int numBluePegs1=numBluePegs;
			int numGreenPegs1=numGreenPegs;
			int numWhitePegs1=numWhitePegs;
			int numPurplePegs1=numPurplePegs;
			int numYellowPegs1=numYellowPegs;
//
			System.out.println("in compare2");
			for (int i=0; i<thatRound.length; i++) {
//				System.out.println("index"+i+"num"+oneDecreasedOfThatColor(thatRound[i].getColor())+1);
				for (int k=0; k<mastersCode.length; k++) {
					if (thatRound[i].getColor().equals(mastersCode[i])) {
						numRed++;
						if (oneDecreasedOfThatColor(thatRound[i].getColor())>-1) {
							oneIncreasedOfThatColor(thatRound[i].getColor());
							numWhite--;
						} else {
							oneDecreasedOfThatColor(thatRound[i].getColor());
						}
						break;
					} else if (thatRound[i].getColor().equals(mastersCode[k]) && k!=i) {
						if (oneDecreasedOfThatColor(thatRound[i].getColor())>-1) {
//							oneIncreasedOfThatColor(thatRound[i].getColor());
//						oneDecreasedOfThatColor(thatRound[i].getColor());
						numWhite++;
						break;
						} else {
							oneIncreasedOfThatColor(thatRound[i].getColor());
						}
					}
				}
				}
			numPinkPegs=  numPinkPegs1;
			numBluePegs=numBluePegs1;
			numGreenPegs= numGreenPegs1;
			numWhitePegs=numWhitePegs1;
			numPurplePegs=numPurplePegs1;
			numYellowPegs= numYellowPegs1;
//				alreadyRed=false;
//				alreadyWhite=false;
			//}
			pegsPlaced=0;
			TestWin();
		}
		
		public int oneDecreasedOfThatColor (Color x) {
			if (x.equals(Color.PINK)) {
				System.out.println("pink"+numPinkPegs);
				return(numPinkPegs-=1);
			} else if (x.equals(Color.CYAN)) {
				System.out.println("blue"+numBluePegs);
				return(numBluePegs-=1);
			}else if (x.equals(Color.GREEN)) {
				System.out.println("green"+numGreenPegs);
				return(numGreenPegs-=1);
			}else if (x.equals(Color.WHITE)) {
				System.out.println("white"+numWhitePegs);
				return(numWhitePegs-=1);
			}else if (x.equals(Color.YELLOW)) {
				System.out.println("yellow"+numYellowPegs);
				return(numYellowPegs-=1);
			} else {
				System.out.println("purble"+numPurplePegs);
				return(numPurplePegs-=1);
			}
		}
		public int oneIncreasedOfThatColor (Color x) {
			if (x.equals(Color.PINK)) {
				System.out.println("pink"+numPinkPegs);
				return(numPinkPegs+=1);
			} else if (x.equals(Color.CYAN)) {
				System.out.println("blue"+numBluePegs);
				return(numBluePegs+=1);
			}else if (x.equals(Color.GREEN)) {
				System.out.println("green"+numGreenPegs);
				return(numGreenPegs+=1);
			}else if (x.equals(Color.WHITE)) {
				System.out.println("white"+numWhitePegs);
				return(numWhitePegs+=1);
			}else if (x.equals(Color.YELLOW)) {
				System.out.println("yellow"+numYellowPegs);
				return(numYellowPegs+=1);
			} else {
				System.out.println("purble"+numPurplePegs);
				return(numPurplePegs+=1);
			}
		}
		
		public void TestWin() {
			if (numRed==4) {
				board.removeAll();
				add (new GLabel("Congrats you won!", 20,20));
				for (int i=0; i<mastersCode.length; i++) {
					add (new Peg(mastersCode[i]), 40, 20+ 2*20*i);
				}
			}
		}
		
		public void addCorrectnessPegs() {

		
		int counter=0;
		while(counter<numRed) {
			GOval correcter=new GOval(5,7);
			correcter.setFilled(true);
			correcter.setFillColor(Color.RED);
			add (correcter, board.getWidth()-20-roundNum*40, 40+board.getHeight()+counter*12);
			counter++;
		}
		counter=0;
		while(counter<numWhite) {
			GOval correcter=new GOval(5,7);
			correcter.setFilled(true);
			correcter.setFillColor(Color.WHITE);
			add (correcter, board.getWidth()-10-roundNum*40, 40+board.getHeight()+counter*12);
			counter++;
		}
		System.out.println("adding corect2");

	}
	
		public void setNewArr() {
			for (int i =0; i<thatRound.length; i++) {
//				thatRound[i]= new Peg(Color.BLACK);
				thatRound[i]=new GOval(20,20);
				thatRound[i].setFilled(true);
				thatRound[i].setColor(Color.BLACK);
			
			}
		}

	

	

	
	

	

	

	

	

	

}
