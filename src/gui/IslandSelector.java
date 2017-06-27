package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;


public class IslandSelector extends JPanel {

	public static IslandSelector getInstance() {
		return theIslandSelector;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(islands, 0, 0, 1080, 496, this);
	}

	private IslandSelector() {
		super(null);
		setPreferredSize(new Dimension(1080, 496));
		try {
			islands = ImageIO.read(IslandSelector.class.getResource("/textures/ui/islands_ver2.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		window = Window.getInstance();
		JButton spiritIsland = new JButton();
		spiritIsland.setOpaque(false);
		spiritIsland.setContentAreaFilled(false);
		spiritIsland.setBorderPainted(false);
		//spiritIsland.setBounds(5, 15, 85, 85); //for ver1 islands
		spiritIsland.setBounds(54, 150, 250, 200); //for ver2 islands
		spiritIsland.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.changeTo(FairyIslandBattleSelector.getInstance());
			}
		});
		add(spiritIsland);

		JButton robotIsland = new JButton();
		robotIsland.setOpaque(false);
		robotIsland.setContentAreaFilled(false);
		robotIsland.setBorderPainted(false);
		//robotIsland.setBounds(130, 20, 60, 30);
		robotIsland.setBounds(700, 210, 380, 280);
		robotIsland.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.changeTo(RobotIslandBattleSelector.getInstance());
			}
		});
		add(robotIsland);

		JButton beastIsland = new JButton();
		beastIsland.setOpaque(false);
		beastIsland.setContentAreaFilled(false);
		beastIsland.setBorderPainted(false);
		//beastIsland.setBounds(125, 70, 115, 70);
		beastIsland.setBounds(380, 0, 250, 200);
		beastIsland.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.changeTo(BeastIslandBattleSelector.getInstance());
			}
		});
		add(beastIsland);
	}

	private BufferedImage islands;
	private static IslandSelector theIslandSelector = new IslandSelector();
	private static final long serialVersionUID = 1L;
	private Window window;

}
