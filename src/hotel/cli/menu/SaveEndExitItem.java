package hotel.cli.menu;

import hotel.app.resources.HotelApplContext;
import hotel.cli.items.HotelItem;

public class SaveEndExitItem extends HotelItem {
    public SaveEndExitItem(HotelApplContext context) {
        super(context);
    }

    @Override
    public String displayName() {
        return "Save end exit";
    }

    @Override
    public void perform() {
        try {
            context.saveAll();
            inOut.outputLine("Data saved successfully");
            System.exit(0);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to save data", e);
        }
    }
}
