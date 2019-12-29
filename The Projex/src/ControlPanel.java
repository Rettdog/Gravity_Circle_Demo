import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;

public class ControlPanel extends JPanel implements ActionListener {

	public JLabel title = new JLabel("Control Panel");
	public JLabel massLabel = new JLabel("Mass:");
	public JLabel speedLabel = new JLabel("Max Speed:");
	public JLabel modes = new JLabel("Modes:");
	public JLabel clearLabel = new JLabel("Options:");
	public JSlider massSlider = new JSlider();
	public JSlider maxSpeed = new JSlider();
	public JToggleButton placeMoving = new JToggleButton("Moving Mass");
	public JToggleButton placePin = new JToggleButton("Pinned Mass");
	public JToggleButton pausedToggle = new JToggleButton("Paused");
	public JToggleButton gravityToggle = new JToggleButton("Gravity OFF");
	public JToggleButton arrowField = new JToggleButton("Vector Field OFF");
	public JButton clear = new JButton("Clear All");

	int width;
	int height;

	public ControlPanel(int width, int height) {
		massSlider.setSize(150, 20);
		massSlider.setMinimum(1);
		massSlider.setMaximum(100);
		massLabel.setPreferredSize(new Dimension(200, 30));
		modes.setPreferredSize(new Dimension(200, 30));
		speedLabel.setPreferredSize(new Dimension(200, 30));
		pausedToggle.setSelected(true);
		maxSpeed.setMinimum(0);
		maxSpeed.setMaximum(200);
		clearLabel.setPreferredSize(new Dimension(200,30));
		
		placeMoving.addActionListener(this);
		placePin.addActionListener(this);
		
		pausedToggle.addActionListener(this);
		gravityToggle.addActionListener(this);
		arrowField.addActionListener(this);
		clear.addActionListener(this);
		
		

		this.width = width;
		this.height = height;
		this.setSize(width, height);

		this.add(title);
		this.add(massLabel);
		this.add(massSlider);
		this.add(placeMoving);
		this.add(placePin);
		
		this.add(speedLabel);
		this.add(maxSpeed);
		this.add(modes);
		this.add(pausedToggle);
		this.add(gravityToggle);
		this.add(clearLabel);
		this.add(arrowField);
		this.add(clear);

	}

	public int getMassValue() {
		return massSlider.getValue();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(placeMoving)) {
			placePin.setSelected(false);
			Gravity_Circle_Demo.gravityPanel.clickState = ClickState.MOVING_MASS;
		}
		if (e.getSource().equals(placePin)) {
			placeMoving.setSelected(false);
			Gravity_Circle_Demo.gravityPanel.clickState = ClickState.PINNED_MASS;
		}
		if(!placeMoving.isSelected()&&!placePin.isSelected()) {
			Gravity_Circle_Demo.gravityPanel.clickState = ClickState.NULL;
		}
		if(e.getSource().equals(pausedToggle)) {
			if(pausedToggle.isSelected()) {
				//when turned on
				pausedToggle.setText("Paused");
				//gravityToggle.setSelected(false);
				Gravity_Circle_Demo.gravityPanel.changeState(State.PAUSE_MODE);
				
			}else {
				
				pausedToggle.setText("Playing");
				//gravityToggle.setSelected(true);
				if(Gravity_Circle_Demo.gravityPanel.gravityON) {
					Gravity_Circle_Demo.gravityPanel.changeState(State.GRAVITY_MODE);
				}else {
					Gravity_Circle_Demo.gravityPanel.changeState(State.DRIFT_MODE);
				}
			}
		}
		if(e.getSource().equals(gravityToggle)) {
			if(gravityToggle.isSelected()) {
				//when turned on
				gravityToggle.setText("Gravity ON");
				System.out.println("ON");
				//pausedToggle.setSelected(false);
				Gravity_Circle_Demo.gravityPanel.gravityON = true;
				
			}else {
				
				gravityToggle.setText("Gravity OFF");
				//pausedToggle.setSelected(false);
				Gravity_Circle_Demo.gravityPanel.gravityON = false;
			}
			
			if(Gravity_Circle_Demo.gravityPanel.state == State.DRIFT_MODE && Gravity_Circle_Demo.gravityPanel.gravityON) {
				Gravity_Circle_Demo.gravityPanel.changeState(State.GRAVITY_MODE);
			}
		}
		if(e.getSource().equals(clear)) {
			System.out.println("clearing all pins");
			Gravity_Circle_Demo.gravityPanel.manager.clearPins();
		}
		if(e.getSource().equals(arrowField)) {
			if(arrowField.isSelected()) {
				//when turned on
				arrowField.setText("Vector Field ON");
				
				//pausedToggle.setSelected(false);
				Gravity_Circle_Demo.gravityPanel.showVectors = true;
				
			}else {
				
				arrowField.setText("Vector Field OFF");
				//pausedToggle.setSelected(false);
				Gravity_Circle_Demo.gravityPanel.showVectors = false;
			}
		}
	}

}
