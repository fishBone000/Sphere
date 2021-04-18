package fbtool.math.Sphere;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.lang.Math;

public class Sun {
	static double longitude = 181, latitude = 91;
	static LocalDate currentDate = LocalDate.now();
	static long dateDiff = ChronoUnit.DAYS.between(LocalDate.of(currentDate.getYear(), 3, 21), currentDate);
	public static void main(String [] args) {
		switch(args.length) {
		case 0:
			break;
		default:
		case 2:
			longitude = Double.parseDouble(args[1]);
		case 1:
			latitude = Double.parseDouble(args[0]);
			break;
		}
		Scanner scanner = new Scanner(System.in);
		while(longitude < -180 | longitude > 180) {
			System.out.println("Invalid longitude. Input a new one.");
			longitude = scanner.nextDouble();
		}
		while(latitude < -90 | latitude > 90) {
			System.out.println("Invalid latitude. Input a new one.");
			latitude = scanner.nextDouble();	
		}
		System.out.println("Input UTC hour.");
		int hour = (int)scanner.nextDouble();
		while(hour < 0 | hour > 23) {
			System.out.println("Invalid UTC hour. Input a new one.");
			hour = (int)scanner.nextDouble();
		}
		System.out.println("Input UTC minute.");
		int minute = (int)scanner.nextDouble();
		while(minute < 0 | minute > 59) {
			System.out.println("Invalid UTC minute. Input a new one.");
			minute = (int)scanner.nextDouble();
		}
		scanner.close();
		
		Direction direction = getDirection(hour, minute);
		System.out.println(Math.toDegrees(direction.azimuth) + "-" + Math.toDegrees(direction.altitude));
	}
	
	public static Direction getDirection(int hour, int minute) {
		double directRadian = Math.toRadians(23.5)*Math.sin(dateDiff * Math.PI / ((double)365/2));
		double azimuthOnEarth = (double)(hour*60 + minute)/1440*2*Math.PI + Math.PI;
		Direction directionOnEarth = new Direction(new Sphere(0, 0, 0), azimuthOnEarth, directRadian);
		Sphere local = new Sphere(Math.toRadians((-longitude+180)%360), Math.toRadians(90-latitude), 0);
		directionOnEarth.convertToSphere(local);
		return directionOnEarth;
	}
}
