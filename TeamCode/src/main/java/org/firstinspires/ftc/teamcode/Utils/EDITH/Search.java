package org.firstinspires.ftc.teamcode.tests.StealTheMoon.EDITH;

class Search extends Thread{
    final int DIST_TO_SEARCH = 5;
    Loc point;
    Loc[][] map;
    int[][] mask;
    public Search(Loc point, Loc[][] map, int[][] mask){
        this.point = point;
        this.map = map;
        this.mask = mask;
    }
    public static boolean isBlocked(int x0, int y0, int x1, int y1, int[][] mask){
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;
        int err = dx - dy;

        while (true) {
            if (mask[x0][y0] != 0){
                return true;
            }
            if (x0 == x1 && y0 == y1) break; // End of line

            int e2 = 2 * err;
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
        return false;
    }
    public void run(){
        for (int i = -DIST_TO_SEARCH; i <= DIST_TO_SEARCH; i++){
            if (i + point.x >= 144 || i + point.x < 0) continue;
            for (int j = -DIST_TO_SEARCH; j <= DIST_TO_SEARCH; j++){
                if (j + point.y >= 144 || j + point.y < 0 || mask[point.x + i][point.y + j] != 0 || map[i + point.x][j + point.y].canSeeTarget) continue;
                int newDist;
                if (j == 0 || i == 0){
                    newDist = Math.max(Math.abs(i), Math.abs(j)) * 1000 + point.dist;
                } else if ((Math.abs(i) == Math.abs(j))){
                    newDist = Math.abs(i) * 1414 + point.dist;
                } else if ((Math.abs(i) == 2 * Math.abs(j))){
                    newDist = Math.abs(i) * 2236 + point.dist;
                } else if ((Math.abs(i) * 2 == Math.abs(j))){
                    newDist = Math.abs(j) * 2236 + point.dist;
                }else{
                    continue;//newDist = Math.hypot(i, j) + point.dist;
                }
                if (isBlocked(point.x, point.y, point.x + i, point.y + j, mask))continue;
                if (newDist < map[i + point.x][j + point.y].dist){
                    map[i + point.x][point.y + j].dist = newDist;
                }
            }
        }
    }
}
