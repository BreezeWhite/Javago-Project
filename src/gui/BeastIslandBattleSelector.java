package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.JavaGo;

public class BeastIslandBattleSelector extends JPanel {

	public static BeastIslandBattleSelector getInstance() {
		return theBeastIslandBattleSelector;
	}

	private BeastIslandBattleSelector() {
		super(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_END;
		JButton level1 = new JButton("獸人島關卡一");
		level1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Window.getInstance().changeToScreen();
				JavaGo.getInstance().start();
			}
		});
		add(level1, c);

		++c.gridy;
		JButton level2 = new JButton("獸人島關卡二");
		level2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Window.getInstance().changeToScreen();
				JavaGo.getInstance().start();
			}
		});
		add(level2, c);

		++c.gridy;
		JButton level3 = new JButton("獸人島關卡三");
		level3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Window.getInstance().changeToScreen();
				JavaGo.getInstance().start();
			}
		});
		add(level3, c);
	}

	private static final long serialVersionUID = 1L;
	private static BeastIslandBattleSelector theBeastIslandBattleSelector = new BeastIslandBattleSelector();

}
