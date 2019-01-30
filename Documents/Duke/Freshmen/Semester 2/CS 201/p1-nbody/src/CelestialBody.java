

/**
 * Celestial Body class for NBody
 * @author Neil Reddy
 *
 */
public class CelestialBody {
	
	//instance variables:
	private double myXPos;
	private double myYPos;
	private double myMass;
	private double myXVel;
	private double myYVel;
	private String myFilename;
	
	//constructors:
	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFilename = filename; 
	}
	
	/**
	 * Copy constructor: copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */
	public CelestialBody(CelestialBody b){
		myXPos = b.myXPos;
		myYPos = b.myYPos;
		myXVel = b.myXVel;
		myYVel = b.myYVel;
		myMass = b.myMass;
		myFilename = b.myFilename; 
	}

	
	//getter methods
	/**
	 * Return x-position of this body
	 * @return value of the x-position
	 */
	public double getX() 
	{
		return myXPos;
	}
	
	/**
	 * Return y-position of this body
	 * @return value of the y-position
	 */
	public double getY() 
	{
		return myYPos;
	}
	
	/**
	 * Return x-velocity of this Body
	 * @return value of x-velocity
	 */
	public double getXVel() 
	{
		return myXVel;
	}
	
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() 
	{
		return myYVel;
	}
	
	/**
	 * Return mass of this body
	 * @return value of the mass
	 */
	public double getMass() 
	{
		return myMass;
	}
	
	/**
	 * Return the string for the filename
	 * @return string of the filename
	 */
	public String getName() 
	{
		return myFilename;
	}

	
	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		//using distance formula: r^2 = changeInX^2+changeInY^2
		double xDifference = this.myXPos - b.myXPos;
		xDifference = xDifference*xDifference;
		double yDifference = this.myYPos - b.myYPos;
		yDifference = yDifference*yDifference;	
		double rSquared = xDifference+yDifference;
		return Math.sqrt(rSquared);
	}

	/**
	 * Return the force exerted on this CelestialBody by another
	 * @param p - the other celestial body 
	 * @return the force exerted by p on this CelestialBody
	 */
	public double calcForceExertedBy(CelestialBody p) {
		double g = 6.67*Math.pow(10,-11);
		double rSquared = Math.pow(this.calcDistance(p), 2);
		
		return g * this.myMass * p.myMass/rSquared;
	}

	/**
	 * Return the force in the x direction on this CelestialBody by another
	 * @param p - the other CelestialBody
	 * @return the value of the force in the x-direction of p on this
	 */
	public double calcForceExertedByX(CelestialBody p) {
		double f = this.calcForceExertedBy(p);
		double r = this.calcDistance(p);
		double dx = p.myXPos - this.myXPos;
		
		return f*dx/r;
	}
	
	/**
	 * Return the force in the y direction on this CelestialBody by another
	 * @param p - the other CelestialBody
	 * @return the value of the force in the y-direction of p on this
	 */
	public double calcForceExertedByY(CelestialBody p) {
		double f = this.calcForceExertedBy(p);
		double r = this.calcDistance(p);
		double dy = p.myYPos - this.myYPos;

		return f*dy/r;
	}

	/**
	 * Return the net force exerted in the x-direction on this CelestialBody by others
	 * @param bodies - the array of other CelestialBodies
	 * @return net force exerted in the x-direction by the CelestialBodies in bodies on this CelestialBody
	 */
	public double calcNetForceExertedByX(CelestialBody[] bodies) 
	{
		double sum = 0;
		for (CelestialBody b: bodies)
		{
			if(!b.equals(this)) 
			{
				sum += this.calcForceExertedByX(b);
				
			}	
			
		}
		return sum;
	}

	/**
	 * Return the net force exerted in the y direction on this CelestialBody by others
	 * @param bodies - the array of other CelestialBodies
	 * @return net force exerted in the y-direction by the CelestialBodies in bodies on this CelestialBody
	 */
	public double calcNetForceExertedByY(CelestialBody[] bodies) 
	{
		double sum = 0;
		for (CelestialBody b: bodies)
		{
			if(!b.equals(this)) 
			{
				sum += this.calcForceExertedByY(b);
				
			}		
		}
		return sum;
	}

	
	/**
	 * Mutator function updates the instance variables of the CelestialBody
	 * @param deltaT - the time steps
	 * @param xforce - the net force exerted in the x-direction on this body by all other bodies 
	 * @param yforce - the net force exerted in the y-direction on this body by all other bodies 
	 */
	public void update(double deltaT, double xforce, double yforce) 
	{
		double ax = xforce / this.myMass;
	    double ay = yforce / this.myMass;
	    
	    double nvx = this.myXVel + deltaT*ax; 
	    double nvy = this.myYVel + deltaT*ay;
	    
	    double nx = this.myXPos + deltaT*nvx;
	    double ny = this.myYPos + deltaT*nvy;
	    
	    myXPos = nx;
	    myYPos = ny;
	    myXVel = nvx;
	    myYVel = nvy;
	    
	}

	/**
	 * Draw function
	 */
	public void draw() {
		StdDraw.picture(myXPos,myYPos, "images/" + myFilename);
	}
}
