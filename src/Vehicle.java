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
		actCollaborative(f,m,rocksCollected);
		// actSimple(f,m,rocksCollected);
	}

	public void actCollaborative(Field f, Mothership m, ArrayList<Rock> rocksCollected){
		//complete this method

		// If at mother ship drop sample
		if(carryingSample == true && f.isNeighbourTo(this.location, Mothership.class)){
			carryingSample = false;
			return;
		}

		// If carrying sample and not at base drop crumb and travel up signal gradient
		if(carryingSample == true && !f.isNeighbourTo(this.location, Mothership.class)){
			f.dropCrumbs(this.location, 2);
			vehicleMovement.MoveVehicleUpSignalGradient(f, this);
			return;
		}

		// If detect a sample pick it up
		if(f.isNeighbourTo(this.location, Rock.class)){
			Location loc = f.getNeighbour(this.location, Rock.class);
			carryingSample = true;
			rocksCollected.add((Rock) f.getObjectAt(loc));
			return;
		}

		// If sense crumbs pick one up and travel down gradient
		if(f.getCrumbQuantityAt(this.location) > 0){
			f.pickUpACrumb(this.location);
			vehicleMovement.MoveVehicleDownCrumbGradient(f, this);
			return;
		}

		// Move randomly
		vehicleMovement.MoveVehicleRandomly(f,this);
	}

	public void actSimple(Field f, Mothership m, ArrayList<Rock> rocksCollected){

		// If at mother ship drop sample
		if(carryingSample == true && f.isNeighbourTo(this.location, Mothership.class)){
			carryingSample = false;

		}

		// Travel back to Mother ship with sample
		if(carryingSample == true && !f.isNeighbourTo(this.location, Mothership.class)){
			vehicleMovement.MoveVehicleUpSignalGradient(f, this);
			return;
		}

		// If detect a sample pick it up
		if(f.isNeighbourTo(this.location, Rock.class)){
			Location loc = f.getNeighbour(this.location, Rock.class);
			carryingSample = true;
			rocksCollected.add((Rock) f.getObjectAt(loc));
			return;
		}

		// Move randomly
		vehicleMovement.MoveVehicleRandomly(f,this);
	}
}
