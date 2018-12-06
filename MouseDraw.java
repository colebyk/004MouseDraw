package mousedraw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MouseDraw extends JFrame {

	JMenuBar mainMenuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("File");
	JMenuItem newMenuItem = new JMenuItem("New");
	JMenuItem exitMenuItem = new JMenuItem("Exit");
	JMenu boardColorMenu = new JMenu("BoardColor");
	JMenuItem blackMenuItem = new JMenuItem("Black");
	JMenuItem yellowMenuItem = new JMenuItem("Yellow");
	JMenuItem redMenuItem = new JMenuItem("Red");
	JMenuItem fineMenuItem = new JMenuItem("Fine");
	JMenuItem smallMenuItem = new JMenuItem("Small");
	JMenuItem mediumMenuItem = new JMenuItem("Medium");
	JMenuItem largeMenuItem = new JMenuItem("Large");

	JMenu penSizeMenu = new JMenu("PenSize");

	JPanel drawPanel = new JPanel();
	JPanel colorPanel = new JPanel();
	JLabel[] colorLabel = new JLabel[18];

	JLabel leftColorLabel = new JLabel();
	JLabel rightColorLabel = new JLabel();

	Color leftColor, rightColor, drawColor;
	
	String penSize;
	

	Integer xPrevious, yPrevious;

	Graphics2D g2D;

	// constructor
	public MouseDraw() {

		// frame constructor
		setTitle("Mouse Draw by Coleby K. Oct 2018");
		setResizable(false);
		setSize(800, 600);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitForm(e);
			}

		});

		getContentPane().setLayout(new GridBagLayout());

		setJMenuBar(mainMenuBar);
		
		mainMenuBar.setBackground(new Color(204,245,255));

		mainMenuBar.add(fileMenu);
		fileMenu.setMnemonic('F');
		fileMenu.add(newMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);

		mainMenuBar.add(boardColorMenu);
		boardColorMenu.setMnemonic('B');
		boardColorMenu.add(blackMenuItem);
		boardColorMenu.addSeparator();
		boardColorMenu.add(yellowMenuItem);
		yellowMenuItem.setForeground(new Color(175,175,0));
		boardColorMenu.addSeparator();
		boardColorMenu.add(redMenuItem);
		redMenuItem.setForeground(new Color(255,0,0));

		mainMenuBar.add(penSizeMenu);
		penSizeMenu.setMnemonic('P');
		penSizeMenu.add(fineMenuItem);
		penSizeMenu.addSeparator();
		penSizeMenu.add(smallMenuItem);
		penSizeMenu.addSeparator();
		penSizeMenu.add(mediumMenuItem);
		penSizeMenu.addSeparator();
		penSizeMenu.add(largeMenuItem);

		newMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMenuItemActionPerformed(e);
			}

		});
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitMenuItemActionPerformed(e);
			}

		});
		
		blackMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blackMenuItemPerformed(e);
			}
		});
		
		yellowMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yellowMenuItemPerformed(e);
			}
		});
		
		redMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				redMenuItemPerformed(e);
			}
		});
		
		fineMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fineMenuItemPerformed(e);
			}
		});
		
		smallMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				smallMenuItemPerformed(e);
			}
		});
		
		mediumMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediumMenuItemPerformed(e);
			}
		});
		
		largeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				largeMenuItemPerformed(e);
			}
		});

		drawPanel.setPreferredSize(new Dimension(500, 400));
		drawPanel.setBackground(Color.BLACK);

		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		gridConstraints.gridheight = 2;
		gridConstraints.insets = new Insets(10, 10, 10, 10);
		getContentPane().add(drawPanel, gridConstraints);
		
		

		drawPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				drawPanelMousePressed(e);
			}

		});

		drawPanel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				drawPanelMouseDragged(e);
			}
		});

		drawPanel.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				drawPanelMouseReleased(e);
			}
		});
		
		

		leftColorLabel.setPreferredSize(new Dimension(40, 40));
		leftColorLabel.setOpaque(true);
		leftColorLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 0;
		gridConstraints.anchor = GridBagConstraints.NORTH;
		gridConstraints.insets = new Insets(10, 5, 10, 10);
		getContentPane().add(leftColorLabel, gridConstraints);

		rightColorLabel.setPreferredSize(new Dimension(40, 40));
		rightColorLabel.setOpaque(true);
		rightColorLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 2;
		gridConstraints.gridy = 0;
		gridConstraints.anchor = GridBagConstraints.NORTH;
		gridConstraints.insets = new Insets(10, 5, 10, 10);
		getContentPane().add(rightColorLabel, gridConstraints);

		colorPanel.setPreferredSize(new Dimension(80, 300));
		colorPanel.setBorder(BorderFactory.createTitledBorder("Colors"));
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 1;
		gridConstraints.gridwidth = 2;
		gridConstraints.anchor = GridBagConstraints.NORTH;
		gridConstraints.insets = new Insets(10, 10, 10, 10);
		getContentPane().add(colorPanel, gridConstraints);
		
		colorPanel.setLayout(new GridBagLayout());
		int j = 0;
		for (int i = 0; i < 18; i++) {
			colorLabel[i] = new JLabel();
			colorLabel[i].setPreferredSize(new Dimension(30, 30));
			colorLabel[i].setOpaque(true);
			colorLabel[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			gridConstraints = new GridBagConstraints();
			gridConstraints.gridx = j;
			gridConstraints.gridy = i - j * 9;
			colorPanel.add(colorLabel[i], gridConstraints);
			if (i == 8) {
				j++;
			}

			colorLabel[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					colorMousePressed(e);
				}

			});

		} // end of color panel / listener loop
		colorLabel[0].setBackground(new Color(255, 153, 153));
		colorLabel[1].setBackground(new Color(255,0,0));
		colorLabel[2].setBackground(new Color(153,0,0));
		colorLabel[3].setBackground(new Color(173, 235, 173));
		colorLabel[4].setBackground(new Color(70, 210, 70));
		colorLabel[5].setBackground(new Color(30, 123, 30));
		colorLabel[6].setBackground(new Color(153, 153, 255));
		colorLabel[7].setBackground(new Color(26, 26, 255));
		colorLabel[8].setBackground(new Color(0, 0, 102));
		colorLabel[9].setBackground(new Color(0,255,255));
		colorLabel[10].setBackground(new Color(0,200,200));
		colorLabel[11].setBackground(new Color(0,150,150));
		colorLabel[12].setBackground(new Color(255,255,0));
		colorLabel[13].setBackground(new Color(200,200,0));
		colorLabel[14].setBackground(new Color(150,150,0));
		colorLabel[15].setBackground(new Color(255,0,255));
		colorLabel[16].setBackground(new Color(200,0,200));
		colorLabel[17].setBackground(new Color(150,0,150));
		

		leftColorLabel.setBackground(colorLabel[0].getBackground());
		rightColorLabel.setBackground(colorLabel[7].getBackground());
		
		pack();
		setLocationRelativeTo(null);

		g2D = (Graphics2D) drawPanel.getGraphics();

	} // end of constructor

	private void exitForm(WindowEvent e) {
		g2D.dispose();
		System.exit(0);

	}

	private void newMenuItemActionPerformed(ActionEvent e) {
		int response;
		response = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new drawing?", "New Drawing", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			g2D.setPaint(drawPanel.getBackground());
			g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));
		}

	}
	
	private void blackMenuItemPerformed(ActionEvent e) {
		drawPanel.setBackground(Color.BLACK);
	}
	private void yellowMenuItemPerformed(ActionEvent e) {
		drawPanel.setBackground(Color.YELLOW);
	}
	private void redMenuItemPerformed(ActionEvent e) {
		drawPanel.setBackground(Color.RED);
	}
	private void fineMenuItemPerformed(ActionEvent e) {
		g2D.setStroke(new BasicStroke(1));
	}
	private void smallMenuItemPerformed(ActionEvent e) {
		g2D.setStroke(new BasicStroke(3));
	}
	private void mediumMenuItemPerformed(ActionEvent e) {
		g2D.setStroke(new BasicStroke(6));
	}
	private void largeMenuItemPerformed(ActionEvent e) {
		g2D.setStroke(new BasicStroke(10));
	}

	private void drawPanelMousePressed(MouseEvent e) {
		// if left button or right button clicked, set color and start drawing process
		if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3) {
			xPrevious = e.getX();
			yPrevious = e.getY();
			System.out.println("X = " + xPrevious + "; Y = " + yPrevious);
			if (e.getButton() == MouseEvent.BUTTON1) {
				drawColor = leftColor;
			} else {
				drawColor = rightColor;
			}

		}

	}

	private void drawPanelMouseDragged(MouseEvent e) {
		
		//if drawing, connect previous point with new point
		Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, e.getX(), e.getY());
		Paint drawColor2 = drawColor;
		g2D.setPaint(drawColor2);
		g2D.draw(myLine);
		xPrevious = e.getX();
		yPrevious = e.getY();
		//System.out.println("mouse x, y = " + xPrevious + ", " + yPrevious);
		
	}

	private void drawPanelMouseReleased(MouseEvent e) {
		// if left or right button released, connect last point
		if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3) {
			Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, e.getX(), e.getY());
			Paint drawColor2 = drawColor;
			g2D.setPaint(drawColor2);
			g2D.draw(myLine);
			}

	}

	private void colorMousePressed(MouseEvent e) {
		// decide which color was selected and which button was used
		Component clickedColor = e.getComponent();
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftColor = clickedColor.getBackground();
			leftColorLabel.setBackground(leftColor);
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			rightColor = clickedColor.getBackground();
			rightColorLabel.setBackground(rightColor);
		}

	}

	private void exitMenuItemActionPerformed(ActionEvent e) {
		int response;
		response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Program",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.NO_OPTION) {
			return;
		} else {
			exitForm(null);
		}

	}

} // end of MouseDraw class
