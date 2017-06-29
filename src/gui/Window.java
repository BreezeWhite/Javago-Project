package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import graphics.Screen;
import main.JavaGo;

public class Window extends JFrame implements ComponentListener {

	public static final int DEFAULT_SCALE = 2, DEFAULT_SCREEN_WIDTH = 500,
			DEFAULT_SCREEN_HEIGHT = (int) ((double) DEFAULT_SCREEN_WIDTH * 9.0 / 16.0), INFO_PANEL_WIDTH = 100;

	public void changeTo(Component nextComponent) {
		changeTo(nextComponent, false);
	}

	public void changeTo(Component nextComponent, boolean centre) {
		screenOn = false;
		if (curComponent != null) {
			getContentPane().remove(curComponent);
		}
		curComponent = nextComponent;
		getContentPane().add(curComponent, BorderLayout.CENTER);
		pack();
		if (centre) {
			setLocationRelativeTo(null); // Centre window.
		}
	}

	public void changeToScreen() {
		gamePanel = new JPanel(new BorderLayout());
		infoPanel = new JPanel(new GridBagLayout());
		infoPanel.setPreferredSize(new Dimension(INFO_PANEL_WIDTH, DEFAULT_SCREEN_HEIGHT * DEFAULT_SCALE));
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		infoPanel.add(JavaGo.stats, c);
		screenPanel = new JPanel();
		screen = Screen.getInstance();
		screenPanel.add(screen);
		gamePanel.add(screenPanel, BorderLayout.CENTER);
		gamePanel.add(infoPanel, BorderLayout.EAST);
		changeTo(gamePanel);
		setSize(new Dimension(DEFAULT_SCREEN_WIDTH * DEFAULT_SCALE + INFO_PANEL_WIDTH,
				DEFAULT_SCREEN_HEIGHT * DEFAULT_SCALE));
		setLocationRelativeTo(null);
		screenOn = true;
		screen.requestFocus();
	}

	public static Window getInstance() {
		if (theWindow == null) {
			theWindow = new Window(JavaGo.TITLE, DEFAULT_SCREEN_WIDTH, DEFAULT_SCREEN_HEIGHT, DEFAULT_SCALE);
		}
		return theWindow;
	}

	private Component curComponent = null;
	JPanel gamePanel;
	JPanel infoPanel;
	Screen screen;
	private boolean screenOn = false;
	JPanel screenPanel;
	private static final long serialVersionUID = 1L;
	private static Window theWindow;

	private Window(String title, int width, int height, int scale) {
		setLayout(new BorderLayout());
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menuFile = new JMenu("檔案");
		menuBar.add(menuFile);
		JMenuItem menuFileReturn = new JMenuItem("回去");
		menuFileReturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JavaGo javaGo = JavaGo.getInstance();
				if (javaGo.isExecuting()) {
					javaGo.stop();
				}
				theWindow.changeTo(IslandSelector.getInstance(), true);
			}
		});
		menuFile.add(menuFileReturn);
		JMenuItem menuFileExit = new JMenuItem("結束");
		menuFileExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JavaGo javaGo = JavaGo.getInstance();
				if (javaGo.isExecuting()) {
					javaGo.stop();
				}
				dispose();
			}
		});
		menuFile.add(menuFileExit);
		setResizable(true);
		setTitle(title);
		// add(screen); // Fills from with this instance of Screen, which is a
		// subclass of Canvas, which in turn is a subclass
		// of Component.
		pack(); // Resizes frame to be same size as Component added above.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addComponentListener(this);
		setVisible(true);
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent e) {
		if (screenOn) {
			Component c = (Component) e.getSource();
			Dimension newSize = c.getSize();
			infoPanel.setPreferredSize(new Dimension(INFO_PANEL_WIDTH, (int) newSize.getHeight()));
			infoPanel.revalidate();
			screen.resize(new Dimension((int) newSize.getWidth() - infoPanel.getWidth(), (int) newSize.getHeight()));
			screen.revalidate();
			screenPanel.revalidate();
			gamePanel.revalidate();
			getContentPane().revalidate();
			revalidate();
		}
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

}
