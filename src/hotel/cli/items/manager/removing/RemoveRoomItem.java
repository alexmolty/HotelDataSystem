package hotel.cli.items.manager.removing;

import hotel.app.resources.HotelApplContext;
import hotel.cli.items.HotelItem;
import hotel.model.Room;

public class RemoveRoomItem extends HotelItem {
    public RemoveRoomItem(HotelApplContext context) {
        super(context);
    }

    @Override
    public String displayName() {
        return "Remove room";
    }

    @Override
    public void perform() {
        Room room = getExistingRoom();
        if (room == null) return;
        boolean removed = managingService.removeRoom(room.getRoomNumber());
        inOut.outputLine(removed ? "Room has been removed." : "Room not found.");
    }
}
