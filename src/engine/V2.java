package engine;

public class V2 {
	
	public float x;
	public float y;
	
	public V2 (float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void normalize () {
		double len = len ();
		x /= len;
		y /= len;
	}
	
	public double len () {
		return Math.sqrt (x * x + y * y);
	}
	
	public static V2 diff (V2 a, V2 b) {
		return new V2 (b.x - a.x, b.y - a.y);
	}
	
	public V2 diff (V2 vec) {
		return diff (this, vec);
	}
	
	public static V2 add (V2 a, V2 b) {
		return new V2 (b.x + a.x, b.y + a.y);
	}
	
	public V2 add (V2 vec) {
		return add (this, vec);
	}
	
	public static double dot (V2 a, V2 b) {
		return a.x * b.x + a.y * b.y;
	}
	
	public double dot (V2 vec) {
		return dot (this, vec);
	}
	
	@Override
	public String toString () {
		return "[" + x + ", " + y + "]";
	}

}
