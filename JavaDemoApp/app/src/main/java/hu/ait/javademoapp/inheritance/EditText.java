package hu.ait.javademoapp.inheritance;

public class EditText extends View {
    private String hint;

    public EditText(int id, int width, int height, String hint) {
        super(id, width, height);
        this.hint = hint;
    }

    public String getHint() {
        return hint;
    }
}

