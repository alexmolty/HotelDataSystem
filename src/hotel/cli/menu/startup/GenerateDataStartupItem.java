package hotel.cli.menu.startup;

import hotel.app.resources.HotelApplContext;
import hotel.cli.items.HotelItem;
import hotel.generator.RandomHotelDataGenerator;
import hotel.model.Hotel;
import hotel.service.HotelManagingService;

public class GenerateDataStartupItem extends HotelItem {
    private final Hotel hotel;

    public GenerateDataStartupItem(HotelApplContext context, Hotel hotel) {
        super(context);
        this.hotel = hotel;
    }

    @Override
    public String displayName() {
        return "Generate new random mock data";
    }

    @Override
    public void perform() {
        inOut.outputLine(">>> Generating random mock data...");
        new RandomHotelDataGenerator(hotel, (HotelManagingService) managingService);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}