package hotel.app;

import cli.*;
import hotel.app.resources.HotelApplContext;
import hotel.cli.items.*;
import hotel.cli.items.accountant.*;
import hotel.cli.items.guest.*;
import hotel.cli.items.manager.adding.*;
import hotel.cli.items.manager.removing.*;
import hotel.cli.items.manager.showinfo.*;
import hotel.cli.menu.*;
import hotel.cli.menu.startup.*;
import hotel.model.Hotel;
import hotel.service.*;

import java.util.List;

import static hotel.app.resources.DataManager.*;

public class HotelApplCLI {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("Pacific Resort");
        HotelManagingService manager = new HotelManagingService(hotel);
        HotelInfoService infoService = new HotelInfoService(hotel);
        FileService fileService = new FileService(hotel);
        InputOutput inOut = new ConsoleInputOutput();

        HotelApplContext context = new HotelApplContext(
                inOut,
                infoService,
                fileService,
                manager,
                BOOKINGS_FILE,
                ROOMS_FILE,
                GUESTS_FILE,
                ROOM_TYPES_FILE,
                DATE_FORMAT);

        inOut.outputLine(".------------------------------.");
        inOut.outputLine("| Welcome to Hotel Data System |");
        inOut.outputLine("'------------------------------'");
        List<Item> startupItems = List.of(
                new LoadDataStartupItem(context, hotel, fileService),
                new GenerateDataStartupItem(context, hotel),
                new EmptyHotelStartupItem(context)
        );
        Menu startupMenu = new Menu(startupItems, inOut);
        startupMenu.runMenu();
        Menu menu = new Menu(getMainMenuItems(context), inOut);
        menu.runMenu();
    }

    private static List<Item> getMainMenuItems(HotelApplContext context) {
        return List.of(
                new SubMenuItem("Guest", context.getInOut(), getGuestItems(context)),
                new SubMenuItem("Manager", context.getInOut(), getManagerItems(context)),
                new SubMenuItem("Accountant", context.getInOut(), getAccountantItems(context)),
                new SaveEndExitItem(context)
        );
    }

    private static List<Item> getAccountantItems(HotelApplContext context) {
        return List.of(
                new ShowAllStatisticsItem(context),
                new ShowBookingsForSelectedDay(context),
                new ShowMostPopularRoomTypesForRangeAgeItem(context),
                new ShowOccupancyForDateItem(context),

                new BackItem()
        );
    }

    private static List<Item> getManagerItems(HotelApplContext context) {
        return List.of(
                new AddRoomTypeItem(context),
                new AddRoomItem(context),
                new AddGuestItem(context),

                new ShowRoomTypesItem(context),
                new ShowRoomsItem(context),
                new ShowGuestsItem(context),
                new ShowBookingsItem(context),

                new RemoveRoomTypeItem(context),
                new RemoveRoomItem(context),
                new RemoveGuestItem(context),
                new RemoveBookingItem(context),

                new BackItem()
        );
    }

    private static List<Item> getGuestItems(HotelApplContext context) {
        return List.of(
                new RegisterGuestItem(context),
                new ShowRoomTypesItem(context),
                new ShowAvailableRoomsItem(context),
                new CreateBookingItem(context),
                new ShowMyBookingsItem(context),
                new CancelMyBookingItem(context),

                new BackItem()
        );
    }
}