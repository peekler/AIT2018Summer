package hu.ait.javademoapp.inheritance;

public class Button extends View {
    private String text;

    public Button(int id, int width, int height, String text) {
        super(id, width, height);
        this.text = text;
    }

    @Override
    public void click() {
        super.click();
        //do something more than what is in the parent
    }
}
