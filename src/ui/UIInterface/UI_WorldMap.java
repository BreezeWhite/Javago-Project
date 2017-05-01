package ui.UIInterface;

import ui.UIManager;
import ui.MyButton.MyButton;

/*世界地圖物件，進入遊戲後，除開場動畫外的第一個畫面*/
public class UI_WorldMap extends UI{
	private int numIsland;
	private UIManager manager;
	
	//以MyButton物件代表每個可以進入的島嶼
	private MyButton islands[];
	
	public UI_WorldMap(UIManager manager
			/*You may need to pass screen object to be added components to*/){
		this.manager=manager;
		numIsland=4; //Total number of islands
		islands=new MyButton[numIsland];
		
		//island[0]=new MyButton("Name1",new island1(IslandName.RobotIsland));
		//island[1]=new MyButton("Name2",new island2(IslandName.OrcsIsland));
		//...
		
		
	}
	
	@Override
	public void printScreen(){
		for(int i=0;i<numIsland;++i){
			//screen.add(islands[i]);
			islands[i].addActionListener(manager);
		}
		/*And other works for arranging the screen*/
	}
}
