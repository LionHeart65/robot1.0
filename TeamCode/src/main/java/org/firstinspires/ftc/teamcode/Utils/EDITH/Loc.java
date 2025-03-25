package org.firstinspires.ftc.teamcode.tests.StealTheMoon.EDITH;

class Loc{
    public int x;
    public int y;
    public int dist;
    public boolean searched = false;
    public boolean canSeeTarget;
    public double cost;

    public Loc(int x, int y, int dist, int[] TARGET, int[][] mask){
      this.x = x;
      this.y = y;
      this.dist = dist;
      this.canSeeTarget = !Search.isBlocked(x, y, TARGET[0], TARGET[1], mask);

    }
    public Loc(int x, int y, int[] TARGET, int[][] mask){
      this.x = x;
      this.y = y;
      this.canSeeTarget = !Search.isBlocked(x, y, TARGET[0], TARGET[1], mask);
      if (this.canSeeTarget){
          this.dist = (int)(Math.hypot(x - TARGET[0], y - TARGET[1]) * 1000);
      }else{
          this.dist = 1000000000;//"infinity"
      }
      this.cost = this.dist;
    }

    public Loc(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Loc(){}
}
