package hotel.cli.menu.startup;

import hotel.app.resources.DataManager;
import hotel.app.resources.HotelApplContext;
import hotel.cli.items.HotelItem;
import hotel.model.Hotel;
import hotel.service.FileService;

import java.nio.file.Files;
import java.nio.file.Path;

public class LoadDataStartupItem extends HotelItem {
    private final Hotel hotel;
    private final FileService fileService;


    public LoadDataStartupItem(HotelApplContext context, Hotel hotel, FileService fileService) {
        super(context);
        this.hotel = hotel;
        this.fileService = fileService;
    }

    @Override
    public String displayName() {
        return "Load existing data from files";
    }

    @Override
    public void perform() {
        if (Files.exists(Path.of("rooms.data"))) {
            DataManager.loadAllData(hotel, fileService);
        } else {
            inOut.outputLine(">>> Warning: Database files not found! Starting with an empty hotel.");
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}