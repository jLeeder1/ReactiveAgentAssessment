package MyCode;

import GivenCode.Field;
import GivenCode.Location;
import GivenCode.Vehicle;
import java.util.ArrayList;
import java.util.Random;

public class VehicleRandomMovement {

    public void MoveVehicleRandomly(Field field, Vehicle vehicle){
        ArrayList<Location> locations = field.getAllfreeAdjacentLocations(vehicle.getLocation());
        Random random = new Random();
        Location newLocation = locations.get(random.nextInt(locations.size()));
        UpdateLocation(field, newLocation, vehicle);
    }

    /**
     * Clears the location a vehicle is moving to in the field and moves the vehicle to that location
     * @param field
     * @param location
     * @param vehicle
     */
    private void UpdateLocation(Field field, Location location, Vehicle vehicle){
        field.clearLocation(location);
        field.place(vehicle, location);
        field.clearLocation(vehicle.getLocation());
        vehicle.setLocation(location);
    }
}
