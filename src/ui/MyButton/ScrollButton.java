package ui.MyButton;

public class ScrollButton extends ProtoButton{
	
	private static final long serialVersionUID = 2376485717511219522L;
	private String relatedCol;//紀錄與此按鈕有關的捲動物件
	
	public ScrollButton(String buttonName,String shape/*有LeftTri跟RightTri兩種類型*/,
			String relatedCol) {
		super(buttonName);
		this.relatedCol=relatedCol;
		
		/*想辦法將按鈕形狀定義爲左三角或右三角*/
	}
	
	public String getRelatedCol(){
		return relatedCol;
	}
}
