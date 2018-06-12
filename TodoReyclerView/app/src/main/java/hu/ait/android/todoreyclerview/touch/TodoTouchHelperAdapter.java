package hu.ait.android.todoreyclerview.touch;

public interface TodoTouchHelperAdapter {

    public void onItemDismissed(int position);

    public void onItemMoved(int fromPosition, int toPosition);
}
