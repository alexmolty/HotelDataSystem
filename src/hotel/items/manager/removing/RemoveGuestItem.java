package hotel.items.manager.removing;

import hotel.app.resources.HotelApplContext;
import hotel.items.HotelItem;
import hotel.model.Guest;

public class RemoveGuestItem extends HotelItem {
    public RemoveGuestItem(HotelApplContext context) {
        super(context);
    }

    @Override
    public String displayName() {
        return "Remove guest";
    }

    @Override
    public void perform() {
        Guest guest = getExistingGuest();
        if (guest == null) return;
        boolean removed = managingService.removeGuest(guest.getId());
        inOut.outputLine(removed ? "Guest has been removed successfully" : "Guest not found");
    }
}
