import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;


public class Painter {

	public static void main(String[] args){
		JFrame application = new JFrame("Joe's Masterpiece"); //create application
		application.setLayout(new BorderLayout());
		Panel paintPanel = new Panel();
		JPanel colorbuttons = new JPanel();
		JPanel sizebuttons = new JPanel();


		//color buttons
		JButton blackButton = new JButton("Black");	//Black button
		JButton blueButton = new JButton("Blue");	//Blue Button
		JButton redButton = new JButton("Red");		//Red button
		JButton greenButton = new JButton("Green");	//Green button

		// size buttons and clear
		JButton small = new JButton("Small");
		JButton medium = new JButton("Medium");
		JButton large = new JButton("Large");
		JButton clear = new JButton("Clear");

		
		//action listeners for color buttons
		blackButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				paintPanel.color = Color.black;	//black
			} 
		} );

		blueButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				paintPanel.color = Color.blue;	//blue
			} 
		} );

		small.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				paintPanel.size = 8;
			} 
		} );

		medium.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				paintPanel.size = 12;
			} 
		} );

		large.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				paintPanel.size = 16;	
			} 
		} );

		clear.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				paintPanel.clear();	
			} 
		} );

		redButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				paintPanel.color = Color.red;	//red
			} 
		} );

		greenButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				paintPanel.color = Color.GREEN; //green
			} 
		} );

		
		//add colors to color JPanel
		colorbuttons.setLayout(new GridLayout(4,1));
		colorbuttons.add(blackButton);
		colorbuttons.add(blueButton);
		colorbuttons.add(redButton);
		colorbuttons.add(greenButton);

		//add sizes to size JPanel
		sizebuttons.setLayout(new GridLayout(4,1));
		sizebuttons.add(small);
		sizebuttons.add(medium);
		sizebuttons.add(large);
		sizebuttons.add(clear);


		//add JPanels to application
		application.add(colorbuttons, BorderLayout.WEST);
		application.add(sizebuttons, BorderLayout.EAST);
		application.add(paintPanel, BorderLayout.CENTER);
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.setSize(400, 200);
		application.setVisible(true);
	}




}
