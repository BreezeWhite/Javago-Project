package ui.MyButton;

import ui.UIInterface.UI;

public class MyButton extends ProtoButton{
	private static final long serialVersionUID = -1220721411352461164L;
	private UI nextUI=null; //紀錄按下這個按鈕後該進入的下一個畫面
	
	public MyButton(String name,UI next){
		super(name);
		nextUI=next;
	}
	
	public UI getNextUI(){
		return nextUI;
	}
}
