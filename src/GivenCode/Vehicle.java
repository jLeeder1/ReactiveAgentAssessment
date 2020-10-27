package GivenCode;

import MyCode.CollisionDetection;
import MyCode.VehicleRandomMovement;

import java.util.ArrayList;

public class Vehicle extends Entity{
	public boolean carryingSample;
	private VehicleRandomMovement vehicleRandomMovement;
	
	public Vehicle(Location l){
		super(l);	
		this.carryingSample = false;

		vehicleRandomMovement = new VehicleRandomMovement();
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
		vehicleRandomMovement.MoveVehicleRandomly(f,this);
	}

	private void UpdateLocation(Field field, Location location){
		field.clearLocation(location);
		field.place(this, location);
		field.clearLocation(this.location);
		this.setLocation(location);
	}
}
