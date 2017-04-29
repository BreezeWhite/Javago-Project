package ui.UIInterface;

//所有畫面類別的父類別
public abstract class UI {
	public void printScreen(){
		//所有的子類別都應該override這個函式，以呈現屬於自己的畫面
		//This function may need to pass the screen object to modify it.
	}
}
