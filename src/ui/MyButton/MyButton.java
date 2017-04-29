package ui.MyButton;

import javax.swing.JButton;
import ui.UIInterface.UI;

public class MyButton extends JButton{
	private static final long serialVersionUID = 6563016818067351761L;
	private String buttonName;
	private UI nextUI=null; //紀錄按下這個按鈕後該進入的下一個畫面
	
	public MyButton(String name,UI next){
		super(name);
		this.buttonName=name;
		nextUI=next;
	}
	
	public UI getNextUI(){
		return nextUI;
	}
	public String getButtonName(){
		return buttonName;
	}
}
