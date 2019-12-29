import java.awt.Graphics;
import java.util.ArrayList;

public class ObjectManager {
	ArrayList<GravityPin> gravityPins = new ArrayList<GravityPin>();
	int maxgravityPins = 10;
	Arrow[][] arrowField = new Arrow[51][51];
	
	public ObjectManager() {
		for(int i = 0;i<arrowField.length;i++) {
			for(int j = 0;j<arrowField[i].length;j++) {
				arrowField[i][j] = new Arrow(10*i,10*j,10,10);
				System.out.println("i: "+i+" j: "+j);
			}
		}
	}
	public void addPin(int x, int y, int mass) {
		if(gravityPins.size()<maxgravityPins) {
		gravityPins.add(new GravityPin(x,y,10,10,mass));
		}else {
			System.out.println("Max Pins Reached");
		}
//		double[] accelerations = new double[] {0,0};
//		gravityList.add(accelerations);
	}
	
	public void removePin(int x, int y){
		if(gravityPins.size()==0) {
			return;
		}
		int removeThis=0;
		float distance = 100;
		for(int i = 0;i<gravityPins.size();i++) {
			if(getDistance(x,y,gravityPins.get(i).x,gravityPins.get(i).y) <distance) {
				removeThis = i;
			}
		}
		if(getDistance(x,y,gravityPins.get(removeThis).x,gravityPins.get(removeThis).y) <20) {
			gravityPins.remove(removeThis);
		}
	}
	
	public void clearPins() {
		for(int i = gravityPins.size()-1;i>=0;i--) {
			gravityPins.remove(i);
		}
	}
	
	public float getDistance(int x1, int y1, int x2, int y2) {
		return (float) Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
	}

	public void updateGravityPins() {
		
		for(int i = 0;i<gravityPins.size();i++) {
			GravityPanel.getObject().xList[i]=gravityPins.get(i).getXForce(GravityPanel.getObject().x,GravityPanel.getObject().y,GravityPanel.getObject().mass);
			GravityPanel.getObject().yList[i]=gravityPins.get(i).getYForce(GravityPanel.getObject().x,GravityPanel.getObject().y,GravityPanel.getObject().mass);
		}
	}
	
	public void updateArrows() {
		for(int i = 0;i<arrowField.length;i++) {
			for(int j = 0;j<arrowField[i].length;j++) {
				for(int k = 0;k<gravityPins.size();k++) {
					arrowField[i][j].xList[k] = 0;
					arrowField[i][j].yList[k] = 0;
				}
			
				
			}
		}
		
		for(int i = 0;i<arrowField.length;i++) {
			for(int j = 0;j<arrowField[i].length;j++) {
				for(int k = 0;k<gravityPins.size();k++) {
					arrowField[i][j].xList[k] = gravityPins.get(k).getXForce(arrowField[i][j].x, arrowField[i][j].y, GravityPanel.getObject().mass);
					arrowField[i][j].yList[k] = gravityPins.get(k).getYForce(arrowField[i][j].x, arrowField[i][j].y, GravityPanel.getObject().mass);
				}
				
				arrowField[i][j].update();
				
			}
		}
	}
	
	public void drawArrows(Graphics g) {
		for(int i = 0;i<arrowField.length;i++) {
			for(int j = 0;j<arrowField[i].length;j++) {
				arrowField[i][j].draw(g);
			}
		}
	}
	
	
	
	public void drawAll(Graphics g) {
		for(GravityPin pin : gravityPins) {
			pin.draw(g);
		}
	}
	
}
