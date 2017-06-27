package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.JavaGo;

public class WaitingRoom extends JPanel{
	public static WaitingRoom getInstance(){
    	return waitingRoom;
    }
    
    private WaitingRoom(){
    	super(null);
		setPreferredSize(new Dimension(1080, 496));
		
		JButton startBattle = new JButton("開始遊戲");
		startBattle.setBounds(350,250,100,50);
		startBattle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Window.getInstance().changeToScreen();
				JavaGo.getInstance().start();
			}
		});
		add(startBattle);
    }
	
    private static WaitingRoom waitingRoom = new WaitingRoom();
    private static final long serialVersionUID = 1L;
}
