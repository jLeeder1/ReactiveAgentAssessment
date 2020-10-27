package GivenCode;

import MyCode.VehicleMovementGenerator;

import java.util.ArrayList;
import java.util.Random;

public class Vehicle extends Entity{
	public boolean carryingSample;
	private VehicleMovementGenerator vehicleMovementGenerator;
	
	public Vehicle(Location l){
		super(l);	
		this.carryingSample = false;

		vehicleMovementGenerator = new VehicleMovementGenerator();
	}

	public void act(Field f, Mothership m, ArrayList<Rock> rocksCollected)
	{
		// actCollaborative(f,m,rocksCollected);
		actSimple(f,m,rocksCollected);
	}
	
	
	public void actCollaborative(Field f, Mothership m, ArrayList<Rock> rocksCollected){
		//complete this method
	}

	public void actSimple(Field f, Mothership m, ArrayList<Rock> rocksCollected){
		//complete this method
		vehicleMovementGenerator.MoveVehicle(f,this);
	}

	private void UpdateLocation(Field field, Location location){
		field.clearLocation(location);
		field.place(this, location);
		field.clearLocation(this.location);
		this.setLocation(location);
	}
}
