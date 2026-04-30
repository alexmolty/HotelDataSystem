package hotel.cli.menu.startup;

import hotel.app.resources.HotelApplContext;
import hotel.cli.items.HotelItem;

public class EmptyHotelStartupItem extends HotelItem {
    public EmptyHotelStartupItem(HotelApplContext context) {
        super(context);
    }

    @Override
    public String displayName() {
        return "Start with an empty hotel database";
    }

    @Override
    public void perform() {
        inOut.outputLine(">>> Starting with a clean database...");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}