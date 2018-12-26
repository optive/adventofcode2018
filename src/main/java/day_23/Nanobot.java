package day_23;

public class Nanobot {

    private final long x;
    private final long y;
    private final long z;
    private final long radius;

    public Nanobot(final long x, final long y, final long z, final long radius) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.radius = radius;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getZ() {
        return z;
    }

    public long getRadius() {
        return radius;
    }
}
