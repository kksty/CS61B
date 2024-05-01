public class NBody {
    public static double readRadius(String fileName){
        In in = new In(fileName);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    public static Planet[] readPlanets(String fileName){
        In in = new In(fileName);
        int N = in.readInt();
        in.readDouble();
        Planet[] planet = new Planet[N];
        for (int i = 0; i < N; i++) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            planet[i] = new Planet(xPos, yPos, xVel, yVel, mass, img);
        }
        return planet;
    }

    public static void main(String[] args) {
         double T = Double.valueOf(args[0]);
         double dt = Double.valueOf(args[1]);
         String filename = String.valueOf(args[2]);
         double r = readRadius(filename);
         Planet[] planets = readPlanets(filename);
         StdDraw.setXscale(-r,r);
         StdDraw.setYscale(-r,r);
         StdDraw.enableDoubleBuffering();

        int num = planets.length;
        for (double time = 0; time < T; time += dt) {
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            for(int i = 0; i < num; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for(int i = 0; i < num; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "./images/starfield.jpg");
            for (Planet planet : planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
