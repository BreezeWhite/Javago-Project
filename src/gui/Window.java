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
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import graphics.Screen;
import main.JavaGo;

public class Window extends JFrame {

	public static Window getInstance() {
		return theWindow;
	}

	public Screen getScreen() {
		return screen;
	}

	private Window(String title, int width, int height, int scale) {
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
		screen = new Screen(width, height, scale);
		setResizable(true);
		setTitle(title);
		// add(screen); // Fills from with this instance of Screen, which is a
		// subclass of Canvas, which in turn is a subclass
		// of Component.
		pack(); // Resizes frame to be same size as Component added above.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void changeTo(Component nextComponent) {
		changeTo(nextComponent, false);
	}

	public void changeTo(Component nextComponent, boolean centre) {
		if (curComponent != null) {
			remove(curComponent);
		}
		curComponent = nextComponent;
		add(curComponent);
		pack();
		if (centre) {
			setLocationRelativeTo(null); // Centre window.
		}
	}

	public void changeToScreen() {
		JPanel gamePanel = new JPanel(new BorderLayout());
		gamePanel.add(screen, BorderLayout.CENTER);
		JPanel screenPanel = new JPanel();
		screenPanel.add(screen);
		screenPanel.addComponentListener(new ComponentListener() {
			// This method is called after the component's size changes
			public void componentResized(ComponentEvent e) {
				Component c = (Component) e.getSource();

				// Get new size
				Dimension newSize = c.getSize();
				screen.resize(newSize);
			}

			@Override
			public void componentHidden(ComponentEvent e) {
			}

			@Override
			public void componentMoved(ComponentEvent e) {
			}

			@Override
			public void componentShown(ComponentEvent e) {
			}
		});
		JPanel infoPanel = new JPanel(new GridBagLayout());
		infoPanel.setPreferredSize(new Dimension(INFO_PANEL_WIDTH, defaultScreenHeight * defaultScale));
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		infoPanel.add(new JLabel("HP: 1000"), c);
		gamePanel.add(screenPanel, BorderLayout.CENTER);
		gamePanel.add(infoPanel, BorderLayout.EAST);
		changeTo(gamePanel);
		setSize(new Dimension(defaultScreenWidth * defaultScale + INFO_PANEL_WIDTH, defaultScreenHeight * defaultScale));
		setLocationRelativeTo(null);
	}

	private Component curComponent = null;
	private static final int defaultScale = 2, defaultScreenWidth = 500,
			defaultScreenHeight = (int) ((double) defaultScreenWidth * 9.0 / 16.0), INFO_PANEL_WIDTH = 100;
	private Screen screen;
	private static final long serialVersionUID = 1L;
	private static Window theWindow = new Window(JavaGo.TITLE, defaultScreenWidth, defaultScreenHeight, defaultScale);

}
