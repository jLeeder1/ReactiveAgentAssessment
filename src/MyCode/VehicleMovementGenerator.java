package MyCode;

import java.util.Random;

import GivenCode.Field;
import GivenCode.Location;
import GivenCode.Vehicle;

import javax.swing.text.html.parser.Entity;

public class VehicleMovementGenerator {

    /** TODO
     * To move randomly...
     * Implement a random method that chooses a direction to move in
     * If the chosen cell is out of bounds then make the chosen cell the wrapped version (either travelling from top to bottom or one of the sides)
     * Implement collision detection that checks the chosen cell for obstacles or samples (or crumbs)
     * If the chosen cell is clear then move to it
     *
     *  In Vehicle....
     *  Add this to Vehicle.actSimple() (probably doesn't need to be in an if just at the end
     */

    private Random random;

    public VehicleMovementGenerator(){
        random = new Random();
    }

    /**
     * Generate a direction to move, checks if that is a valid location and then moves the vehicle to that chosen location
     */
    public void MoveVehicle(Field field, Vehicle vehicle){
        boolean isMovingVertically = GenerateRandomBool();
        boolean isMovingPositive = GenerateRandomBool();
        Location newLocation;

        // Move up
        if(isMovingVertically && isMovingPositive){
            newLocation = new Location(vehicle.getLocation().getRow(), vehicle.getLocation().getCol() + 1);
        }
        // Move down
        else if(isMovingVertically){
            newLocation = new Location(vehicle.getLocation().getRow(), vehicle.getLocation().getCol() - 1);
        }
        // Move right
        else if(isMovingPositive){
            newLocation = new Location(vehicle.getLocation().getRow() + 1, vehicle.getLocation().getCol());
        }
        // Move left
        else{
            newLocation = new Location(vehicle.getLocation().getRow() - 1, vehicle.getLocation().getCol() + 1);
        }

        newLocation = WrapLocation(field, newLocation);

        UpdateLocation(field, newLocation, vehicle);
    }

    private void UpdateLocation(Field field, Location location, Vehicle vehicle){
        field.clearLocation(location);
        field.place(vehicle, location);
        field.clearLocation(vehicle.getLocation());
        vehicle.setLocation(location);
    }

    private Location WrapLocation(Field field, Location location){
        if(location.getRow() < 0){
            return new Location(field.getWidth() - 1, location.getCol());
        }
        else if(location.getRow() > field.getWidth() - 1){
            return new Location(0, location.getCol());
        }
        else if(location.getCol() < 0){
            return new Location(location.getRow(), field.getDepth() - 1);
        }
        else if(location.getCol() > field.getDepth() - 1){
            return new Location(location.getRow(), 0);
        }

        return location;
    }

    /**
     * Will return a random boolean that will be used to denote which direction to move (Horizontal or vertical then positive or negative)
     * @return
     */
    private boolean GenerateRandomBool(){
        return random.nextBoolean();
    }
}
