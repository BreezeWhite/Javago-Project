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

import main.JavaGo;

public class CharacterSelection extends JPanel{
	public static CharacterSelection getInstance(){
		return theCharacterSelection;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(swordsman, 70, 50, 300, 284, this);
	}

	
	private CharacterSelection(){
		super(null);
		setPreferredSize(new Dimension(1080, 496));
		LoadCharacterImages();
		
		JButton[] select = new JButton[3];
		int buttonX = 170, offset = 320;
		for(int i=0;i<3;i+=1){
			select[i] = new JButton("選擇");
			select[i].setBounds(buttonX+i*offset,350,100,50);
			select[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Window.getInstance().changeTo(WaitingRoom.getInstance(theCharacterSelection));
				}
			});
			add(select[i]);
		}
		
	}
	
	private void LoadCharacterImages(){
		try {
			swordsman = ImageIO.read(IslandSelector.class.getResource("/textures/sheets/characters/sword.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private static CharacterSelection theCharacterSelection = new CharacterSelection();;
    private BufferedImage swordsman;
    private static final long serialVersionUID = 1L;
}
