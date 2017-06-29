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


public class BeastIslandBattleSelector extends JPanel {

	public static BeastIslandBattleSelector getInstance() {
		return theBeastIslandBattleSelector;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(beastIsland, 0, 0, 700, 496, this);
	}
	
	private BeastIslandBattleSelector() {
		super(null);
		setPreferredSize(new Dimension(1080, 496));
		try {
			beastIsland = ImageIO.read(IslandSelector.class.getResource("/textures/ui/beast_island.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JButton level1 = new JButton("獸人島關卡一");
		level1.setBounds(buttonX,buttonY,width,height);
		level1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Window.getInstance().changeToScreen();
				Window.getInstance().changeTo(CharacterSelection.getInstance());
			}
		});
		add(level1);
		
		buttonY += offset;
		JButton level2 = new JButton("獸人島關卡二");
		level2.setBounds(buttonX,buttonY,width,height);
		level2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Window.getInstance().changeToScreen();
				Window.getInstance().changeTo(CharacterSelection.getInstance());			}
		});
		add(level2);
		
		buttonY += offset;
		JButton level3 = new JButton("獸人島關卡三");
		level3.setBounds(buttonX,buttonY,width,height);
		level3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Window.getInstance().changeToScreen();
				Window.getInstance().changeTo(CharacterSelection.getInstance());			}
		});
		add(level3);
	}

	private static final long serialVersionUID = 1L;
	private static BeastIslandBattleSelector theBeastIslandBattleSelector = new BeastIslandBattleSelector();
	private int buttonX=770,buttonY=100,width=250,height=50,offset=100;
	private BufferedImage beastIsland;
}
