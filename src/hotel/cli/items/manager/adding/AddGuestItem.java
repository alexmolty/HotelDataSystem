package hotel.cli.items.manager.adding;

import hotel.app.resources.HotelApplContext;
import hotel.cli.items.HotelItem;
import hotel.model.Guest;

public class AddGuestItem extends HotelItem {
    public AddGuestItem(HotelApplContext context) {
        super(context);
    }


    @Override
    public String displayName() {
        return "Add guest";
    }

    @Override
    public void perform() {
        Guest guest = inputNewGuest();
        if (guest == null) return;
        managingService.registerGuest(guest);
        inOut.outputLine("Guest added successfully");
    }
}
