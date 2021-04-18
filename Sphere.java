package fbtool.math.Sphere;

public class Sphere {
	/*
	 * |Z   /Y
	 * |   /
	 * |  /
	 * | /
	 * |/_________X
	 * 
	 * A sphere is based on a coordinate system as shown above.
	 */
	public double azimuth, altitude, rotation; // rotation along Y axis.
	// The first 2 variables state where the Y azis is pointing at in a default sphere.
	// The 3rd variable states how much it was rotated along Y axis.
	public Sphere(double azi, double alt, double rot) {
		azimuth = azi;
		altitude = alt;
		rotation = rot;
	}
	

}
