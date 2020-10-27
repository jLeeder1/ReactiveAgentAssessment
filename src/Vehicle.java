import java.util.ArrayList;

public class Vehicle extends Entity{
	public boolean carryingSample;
	private VehicleMovement vehicleMovement;
	
	public Vehicle(Location l){
		super(l);	
		this.carryingSample = false;

		vehicleMovement = new VehicleMovement();
	}

	public void act(Field f, Mothership m, ArrayList<Rock> rocksCollected)
	{
		// actCollaborative(f,m,rocksCollected);
		actSimple(f,m,rocksCollected);
	}

	public void actCollaborative(Field f, Mothership m, ArrayList<Rock> rocksCollected){
		//complete this method

		// Move randomly
		vehicleMovement.MoveVehicleRandomly(f,this);
	}

	public void actSimple(Field f, Mothership m, ArrayList<Rock> rocksCollected){

		// If at mother ship drop sample
		if(carryingSample == true && f.isNeighbourTo(this.location, Mothership.class)){
			carryingSample = false;
		}

		// Travel back to Mother ship with sample
		else if(carryingSample == true && !f.isNeighbourTo(this.location, Mothership.class)){
			vehicleMovement.MoveVehicleUpSignalGradient(f, this);
		}

		// If detect a sample pick it up
		else if(f.isNeighbourTo(this.location, Rock.class)){
			Location loc = f.getNeighbour(this.location, Rock.class);
			carryingSample = true;
			rocksCollected.add((Rock) f.getObjectAt(loc));
		}

		// Move randomly
		vehicleMovement.MoveVehicleRandomly(f,this);
	}
}
