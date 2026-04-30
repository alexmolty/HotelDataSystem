package hotel.cli.items.manager.removing;

import hotel.app.resources.HotelApplContext;
import hotel.cli.items.HotelItem;
import hotel.model.RoomType;

public class RemoveRoomTypeItem extends HotelItem {
    public RemoveRoomTypeItem(HotelApplContext context) {
        super(context);
    }

    @Override
    public String displayName() {
        return "Remove room type";
    }

    @Override
    public void perform() {
        RoomType roomType = getExistingRoomType();
        if (roomType == null) return;
        boolean removed = managingService.removeRoomType(roomType.getRoomTypeName());
        inOut.outputLine(removed ? "Room type has been removed." : "Room type not found.");
    }
}
