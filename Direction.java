package fbtool.math.Sphere;
import java.lang.Math;

public class Direction {
	// A class storing the direction of something(e.g. celestial body)
	public double azimuth, altitude;
	public Sphere sphere;
	
	public Direction(Sphere sph, double azi, double alt) {
		sphere = sph;
		azimuth = azi;
		altitude = alt;
	}
	
	/*public void rotateAlongX(double radian) {
		double radianFromX = Math.acos(Math.cos(altitude) * Math.cos(azimuth - Math.PI/2));
		double startRadian = Math.acos(Math.sin(azimuth - Math.PI/2) / Math.sin(radianFromX));
		if(altitude < 0)
			startRadian = -startRadian;
		radian += startRadian;
		azimuth = Math.PI/2 + Math.asin(Math.sin(radianFromX)*Math.cos(radian));
		altitude = radianFromX*Math.sin(radian);
	}
	*/
	
	public void rotateAlongX(double radian) {
		azimuth -= Math.PI/2;
		rotateAlongY(radian);
		azimuth += Math.PI/2;
	}
	
	public void rotateAlongY(double radian) {
		double radianFromY = Math.acos(Math.cos(altitude) * Math.cos(azimuth));
		double startRadian = Math.asin(Math.sin(altitude) / Math.sin(radianFromY));
		if(Math.sin(azimuth) < 0)
			startRadian = Math.PI - startRadian;
		radian += startRadian;
		altitude = Math.asin(Math.sin(radianFromY) * Math.sin(radian));
		azimuth = Math.acos(Math.cos(radianFromY) / Math.cos(altitude));
		if(Math.cos(radian) < 0)
			azimuth = 2*Math.PI - azimuth;
	}
	
	public void convertToSphere(Sphere sph) {
		rotateAlongY(sphere.rotation);
		rotateAlongX(-sphere.altitude);
		azimuth += sphere.azimuth - sph.azimuth;
		rotateAlongX(sph.altitude);
		rotateAlongY(-sph.rotation);
		sphere = sph;
	}
}