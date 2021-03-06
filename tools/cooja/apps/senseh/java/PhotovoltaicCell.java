/**
 * 
 */

/**
 * @author raza
 *
 */
public class PhotovoltaicCell extends EnergySource {

	/**
	 * Implementation of Sanyo Amorton AM-1816 Photovoltaic  Cell
	 * Should there also be maximum power you can extract from the solar cell? 
	 */
	private LookupTable lxPwrLUT;
	private int numCells;
	
	public PhotovoltaicCell(String model, String lookupTableFile) {
		lxPwrLUT = new LookupTable(model, lookupTableFile);
		numCells=1;
		
	}
	
	public void setNumCells(int numCells){
		this.numCells = numCells; 
	}
	
	// Return output power in micro watts
	@Override
	public double getOutputPower(double lux){
		return numCells*lxPwrLUT.getY(lux);
	}
	
	// Returns energy in micro joule 
	@Override
	public double getOutputEnergy(double lux, double timeInterval ){
		return getOutputPower(lux)*timeInterval;
	}
	
	// Test function 
	public static void main(String[] args) {
		PhotovoltaicCell am1816 = new PhotovoltaicCell("Panasonic-AM1816", "/home/raza/raza@murphysvn/code/java/eclipseIndigo/Senseh/EnergySources/Panasonic-AM1816.lut");
		for (double lux=1.0; lux<=1000; lux=lux+1.0 ){
			System.out.println (lux +" "+am1816.getOutputPower(lux));
		}

	}
	

}
