package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.UIInterface.*;


public class UIManager implements ActionListener{
	private UI cur_state=null;
	
	public UIManager(){
		/*檢查若是有遊戲存檔，則跳過開頭動畫，直接進入世界地圖*/
		//if(check_archive().exist())
			cur_state=new UI_WorldMap(this);
	}
	
	/*當還未進入戰鬥時需停留在UIManager內部*/
	public void Start(UI state){
		cur_state=state;
		Start();
	}
	public void Start(){
		//使用while迴圈以停留在UIManager內部
		/*while(!(cur_state instanceof Battle)){
		}
		return;*/
	}
	
	
	@Override
	/*MyButton事件的聆聽者，每按下一個按鈕，都代表着進入下一個畫面*/
	public void actionPerformed(ActionEvent e) {
		//cur_state=((ProtoButton) e.getSource()).getUI();
		cur_state.printScreen();
	}
}