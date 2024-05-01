
public class Planet  {
    public double xxPos; //当前x位置
    public double yyPos; //当前y位置
    public double xxVel; //当前x方向速度
    public double yyVel; //当前y方向速度
    public double mass; //质量
    public String imgFileName;
    static final double G = 6.67E-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
       this.xxPos = xP;
       this.yyPos = yP;
       this.xxVel = xV;
       this.yyVel = yV;
       this.mass = m;
       this.imgFileName = img;
    }
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    //计算两个天体之间的距离
     public double calcDistance(Planet p){
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        double rSquared = dx * dx + dy * dy;
        return Math.sqrt(rSquared);
    }

    //计算给定物体对该物体施加的力。
    public double calcForceExertedBy(Planet p){
        double F = ((G * p.mass * this.mass ) / (calcDistance(p) * calcDistance(p)));
        return F;
    }

    //分别计算 X Y 方向上施加的力
    public double calcForceExertedByX(Planet p){
        double Fx = ((calcForceExertedBy(p) * (p.xxPos - this.xxPos)) / calcDistance(p));
        return Fx;
    }
    public double calcForceExertedByY(Planet p){
        double Fy = ((calcForceExertedBy(p) * (p.yyPos - this.yyPos)) / calcDistance(p));
        return Fy;
    }

    //接收一个 Planet 数组，并计算该数组中所有 Planet 对当前 Planet 施加的净 X 和净 Y 力
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double Fnx = 0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (!this.equals(allPlanets[i])){
                Fnx += calcForceExertedByX(allPlanets[i]);
            }
        }
        return Fnx;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double Fny = 0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (!this.equals(allPlanets[i])){
                Fny += calcForceExertedByY(allPlanets[i]);
            }
        }
        return Fny;
    }

    // 施加的力将导致物体运动,短时间内物体速度和位置的变化
    public void update(double dt, double fX, double fY){
        double ax = fX / this.mass;
        double ay = fY / this.mass;
        double vx = this.xxVel + dt * ax;
        double vy = this.yyVel + dt * ay;
        double px = this.xxPos + dt * vx;
        double py = this.yyPos + dt * vy;
        this.xxPos = px;
        this.yyPos = py;
        this.xxVel = vx;
        this.yyVel = vy;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }



}