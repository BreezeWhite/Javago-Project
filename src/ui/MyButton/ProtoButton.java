package ui.MyButton;

import javax.swing.JButton;

public abstract class ProtoButton extends JButton{
	private static final long serialVersionUID = 1L;
	private String buttonName;
	
	public ProtoButton(String buttonName){
		super(buttonName);
		this.buttonName=buttonName;
	}
	public String getButtonName(){
		return buttonName;
	}
}
