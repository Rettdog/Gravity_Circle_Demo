import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class MainObject extends Object{
	
	public double xSpeed;
	public double ySpeed;
	public double xAcceleration;
	public double yAcceleration;
	double[][] gravityList = new double[10][2];
	double[] xList = new double[10];
	double[] yList = new double[10];
	public double mass;
	//public int radius;

	MainObject(int x,int y, int width, int height) {
		super(x, y, width, height);
		xSpeed = 0;
		ySpeed = 0;
		xAcceleration = 0;
		yAcceleration = 0;
		mass = 50;
		//radius = 20;
		
	}
	
	public void update() {
		if(Gravity_Circle_Demo.gravityPanel.state!=State.DRIFT_MODE) {
		xAcceleration = 0;
		yAcceleration = 0;
		for(int i = 0;i<gravityList.length;i++) {
			xAcceleration += xList[i]/mass;
			yAcceleration += yList[i]/mass;
		}
		
		xSpeed+=xAcceleration;
		//System.out.println("yAccel: "+yAcceleration);
		//System.out.println("ySpeed: "+ySpeed);
		ySpeed+=yAcceleration;
		}
		double max = Gravity_Circle_Demo.gravityPanel.maxSpeed;
		if(Math.sqrt(Math.pow(xSpeed, 2)+Math.pow(ySpeed, 2))>max) {
			double angle = Math.atan(ySpeed/xSpeed); 
			xSpeed = max * Math.cos(angle);
			ySpeed = max * Math.sin(angle);
		}
		x+=xSpeed;
		y+=ySpeed;
		
		if(x>500) {
			xSpeed*=-1;
			x=500;
		}
		if(x<0) {
			xSpeed*=-1;
			x=0;
		}
		if(y>500) {
			ySpeed*=-1;
			y=500;
		}
		if(y<0) {
			ySpeed*=-1;
			y=0;
		}
	
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(x-width, y-height, width*2, height*2);
		//System.out.println("Circle is being drawn at x: "+x+" y: "+y);
	}
	
}
