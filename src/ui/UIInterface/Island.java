package ui.UIInterface;

import ui.UIManager;
import ui.MyButton.MyButton;

public class Island extends UI{
	private UIManager manager;
	private MyButton button[];
	private int numButton;
	private IslandName islandName;
	
	public Island(IslandName islandName,UIManager manager){
		this.manager=manager;
		this.islandName=islandName;
		
		//numButton=check_archive(islandName).num_level_passed; //根據島的名字決定要讀取那個檔案
		numButton=10; //This line should be deleted after finished the above line
		button=new MyButton[numButton];
		
		/*根據島的名字再決定要設定出哪些選單*/
		//button[0]=new MyButton("Plot1_Name",new Plot1());
		//button[1]=new MyButton("Level1_Name",new Level1());
		//button[2]=new MyButton("Plot2_Name",new Plot2());
		//button[3]=new MyButton("Level2_Name",new Leve2());
		//...
	}
	
	@Override
	public void printScreen(){
		/*Arrange the positions of button and other pictures...*/
		for(int i=0;i<numButton;++i){
			//screen.add(button[i]);
			button[i].addActionListener(manager);
		}
	}
}
