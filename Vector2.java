public class Vector2 {

	double x, y = 0;

	public Vector2() {

	}

	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void set(Vector2 v) {
		this.x = v.x;
		this.y = v.y;
	}

	public void set(double[] source) {
		if (source.length > 0)
			this.x = source[0];
		if (source.length > 1)
			this.y = source[1];
	}

	public Vector2 copy() {
		return new Vector2(x, y);
	}

	public static Vector2 copy(Vector2 v) {
		return new Vector2(v.x, v.y);
	}

	public double mag() {
		return Math.sqrt(x*x + y*y);
	}

	public static double mag(Vector2 v) {
		return Math.sqrt(v.x*v.x + v.y*v.y);
	}

	public double magSq() {
		return x*x + y*y;
	}

	public static double magSq(Vector2 v) {
		return v.x*v.x + v.y*v.y;
	}

	public void add(double x, double y) {
		this.x += x;
		this.y += y;
	}

	public void add(Vector2 v) {
		this.x += v.x;
		this.y += v.y;
	}

	public static Vector2 add(Vector2 v1, Vector2 v2) {
		return new Vector2(v1.x + v2.x, v1.y + v2.y);
	}

	public void sub(double x, double y) {
		this.x -= x;
		this.y -= y;
	}

	public void sub(Vector2 v) {
		this.x -= v.x;
		this.y -= v.y;
	}

	public static Vector2 sub(Vector2 v1, Vector2 v2) {
		return new Vector2(v1.x - v2.x, v1.y - v2.y);
	}

	public void mult(double scalar) {
		this.x *= scalar;
		this.y *= scalar;
	}

	public static Vector2 mult(Vector2 v, double scalar) {
		return new Vector2(v.x * scalar, v.y * scalar);
	}

	public void div(double scalar) {
		this.x /= scalar;
		this.y /= scalar;
	}

	public static Vector2 div(Vector2 v, double scalar) {
		return new Vector2(v.x / scalar, v.y / scalar);
	}

	public double dist(Vector2 v) {
		double x = this.x - v.x;
		double y = this.y - v.y;
		return Math.sqrt(x*x + y*y);
	}

	public static double dist(Vector2 v1, Vector2 v2) {
		double x = v1.x - v2.x;
		double y = v1.y - v2.y;
		return Math.sqrt(x*x + y*y);
	}

	public double dot(Vector2 v) {
		return this.x * v.x + this.y * v.y;
	}

	public static double dot(Vector2 v1, Vector2 v2) {
		return v1.x * v2.x + v1.y * v2.y;
	}

	public void normalize() {
		this.div(this.mag());
	}

	public static Vector2 normalize(Vector2 v) {
		return Vector2.div(v, Vector2.mag(v));
	}

	public void limit(double max) {
		double len = mag();
		if (len > max)
			setMag(max);
	}

	public static Vector2 limit(Vector2 v, double max) {
		double len = v.mag();
		Vector2 ret = v.copy();
		if (len > max) {
			ret.setMag(max);
		}
		return ret;
	}

	public void setMag(double mag) {
		normalize();
		mult(mag);
	}

	public static void setMag(Vector2 v, double mag) {
		Vector2 ret = v.copy();
		ret.normalize();
		ret.mult(mag);
	}

}
