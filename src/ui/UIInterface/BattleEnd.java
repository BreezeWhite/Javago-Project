package ui.UIInterface;

import ui.UIManager;
import ui.MyButton.MyButton;

/*顯示戰鬥結束後的獎勵*/
public class BattleEnd extends UI{
	private UIManager manager;
	private MyButton checkButton;
	
	public BattleEnd(UIManager manager,IslandName island){
		this.manager=manager;
		checkButton=new MyButton("OK",new Island(island,manager));
	}
	
	@Override
	public void printScreen(){
		checkButton.addActionListener(manager);
		//Arrange other items such as buttons, list of awards, pictures, ...
	}
}
