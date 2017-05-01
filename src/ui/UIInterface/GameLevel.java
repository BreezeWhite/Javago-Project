package ui.UIInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.UIManager;

import ui.MyButton.MyButton;
import ui.MyButton.ScrollButton;

public class GameLevel extends UI implements ActionListener{
	private UIManager manager;
	private ScrollButton scrButton[]; //使用自定義形狀（三角形）的按鈕，用來捲動選單
	private MyButton CharacterSelection[];
	private int scrButtonNum;
	private int characterNum;
	
	public GameLevel(UIManager manager){
		this.manager=manager;
		scrButtonNum=4;
		//characterNum=check_archive(island,character).availableNum();
		
		/*ScrollButton應該要成雙成對出現，一個爲向左滑動一個爲向右*/
		//scrButton[0]=new ScrollButton(name,"LeftTri","Character");
		//scrButton[1]=new ScrollButton(name,"LeftTri","Skill");
		//...
		for(int i=0;i<scrButtonNum;++i)
			scrButton[i].addActionListener(this);
		
		CharacterSelection=new MyButton[characterNum];
		//CharacterSelection[0]=new MyButton(name,new DetailDescription(ChosenCharacter));
		//CharacterSelection[1]=new MyButton(name,new DetailDescription(ChosenCharacter));
		//...
	}
	
	@Override
	public void printScreen(){
		/*Arrange the positions of button and other pictures...*/
		for(int i=0;i<characterNum;++i){
			//screen.add(CharacterSelection[i]);
			CharacterSelection[i].addActionListener(manager);
		}
	}

	@Override
	/*聆聽捲動事件的發生*/
	public void actionPerformed(ActionEvent e) {
		String col="Skill";//=((ProtoButton) e.getSource()).getRelatedCol();
		DisplayAnimation(col,"Right");
	}
	
	private void DisplayAnimation(String col,String dir){
		/*依據傳入的col，對不同的選單呈現捲動的動畫*/
	}
}
