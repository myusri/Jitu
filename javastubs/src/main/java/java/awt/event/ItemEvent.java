package java.awt.event;
import java.awt.ItemSelectable;

class ItemEvent extends AWTEvent {
  public static final int SELECTED = 1;
  public static final int DESELECTED = 2;
  public static final int ITEM_FIRST = 701;
  public static final int ITEM_LAST = 701;
  public static final int ITEM_STATE_CHANGED = 701;

  private Object item;
  private int stateChange;

  public ItemEvent(ItemSelectable s, int id, Object item, int stateChange) {
    super(s, id);
    this.item = item;
    this.stateChange = stateChange;
  }

  public ItemSelectable getItemSelectable() {
    return (ItemSelectable) getSource();
  }

  public Object getItem() {
    return item;
  }

  public int getStateChange() {
    return stateChange;
  }

  public String paramString() {
    return "ItemEvent";
  }
}
