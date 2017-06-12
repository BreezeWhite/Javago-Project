package entities;

public enum Direction {
	UP(1, Math.toRadians(270)), UP_RIGHT(2, Math.toRadians(315)), RIGHT(5, Math.toRadians(0)), DOWN_RIGHT(8,
			Math.toRadians(45)), DOWN(7, Math.toRadians(90)), DOWN_LEFT(6,
					Math.toRadians(135)), LEFT(3, Math.toRadians(180)), UP_LEFT(0, Math.toRadians(225));

	Direction(int index, double directionAngleRadians) {
		this.index = index;
		this.directionAngleRadians = directionAngleRadians;
	}

	public double getDirectionAngleRadians() {
		return directionAngleRadians;
	}
	
	public int getIndex() {
		return index;
	}

	private double directionAngleRadians;
	private int index;

}