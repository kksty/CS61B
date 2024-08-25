package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;


import java.util.LinkedList;
import java.util.List;

public class Room extends RectangleHelper {
    private int width;
    private int height;
    private final Position p;

    public Room(int width, int height, Position p) {
        this.width = width;
        this.height = height;
        this.p = p;
    }

    private void printRoom(TETile[][] world) {
        // 打印垂直墙
        for (int i = 0; i < height; i++) {
            // 如果边缘崩溃，请更新 height 和 width。
            if (p.y + i == world[0].length - 1) {
                height = i + 1;
                break;
            }
            world[p.x][p.y + i] = Tileset.WALL; //房间左下角开始->左边一列开始填充
            if (p.x + width - 1 < world.length - 1) { //判断起始位置+房间宽度是否小于数组x
                world[p.x + width - 1][p.y + i] = Tileset.WALL; //房间的右边一列墙壁填充
            } else {
                width = world.length - p.x; //此时越界了，更新房间宽度
            }
        }
        // 打印水平墙。
        for (int i = 0; i < width; i++) {
            world[p.x + i][p.y] = Tileset.WALL;
            world[p.x + i][p.y + height - 1] = Tileset.WALL;
        }

        // 打印地板.
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                world[p.x + j][p.y + i] = Tileset.FLOOR;
            }
        }
    }

    private static Room randomRoom(Random RANDOM, TETile[][] world) {

        int xP = RANDOM.nextInt((int) (0.5 * (world.length - 2))) * 2;
        int yP = RANDOM.nextInt((int) (0.5 * (world[0].length - 2))) * 2;
        Position p = new Position(xP, yP);

        int height = (3 + RANDOM.nextInt(2)) * 2 - 1;
        int width = (3 + RANDOM.nextInt(2)) * 2 - 1;
        return new Room(width, height, p);
    }

    /*
     * 重叠检查方法。首先检查房间内是否有点。
     * 然后计算 4 个角点。
     * 最后检查房间内的四个点。
     */
    private boolean containPosition(Position p) {
        return (p.x <= this.p.x + width - 1 && p.x >= this.p.x)
                && (p.y <= this.p.y + height - 1 && p.y >= this.p.y);
    }

    private boolean isOverlap(Room r) {
        Position[] p = cornerPositions(r.p, r.width, r.height);
        for (int i = 0; i < 4; i++) {
            if (containPosition(p[i])) {
                return true;
            }
        }
        return false;
    }

    private static void removeOverlaps(List<Room> rooms) {
        for (int i = 0; i < rooms.size(); i++) {
            for (int j = i + 1; j < rooms.size(); j++) {
                if (rooms.get(i).isOverlap(rooms.get(j)) || rooms.get(j).isOverlap(rooms.get(i))) { // 摆脱一种特定的条件。
                    rooms.remove(j);
                    j--;
                }
            }
        }
    }

    /**
     * 生成房间和返回房间。
     */
    public static List<Room> roomGenerator(Random RANDOM, TETile[][] world) {
        int numOfRooms = 100 + RANDOM.nextInt(50);
        List<Room> rooms = new LinkedList<>();
        for (int i = 0; i < numOfRooms; i++) {
            rooms.add(randomRoom(RANDOM, world));
        }
        removeOverlaps(rooms); //删除覆盖的房间
        for (Room r : rooms) {
            r.printRoom(world); //打印房间到数组
        }
        return rooms;
    }


    /**
     * 在 Room 的边缘创建 4 个随机位置。
     */
    private Position[] randomRoomPositions(Random RANDOM) {
        int widthRandom1 = 1 + RANDOM.nextInt(width - 3);
        int heightRandom1 = 1 + RANDOM.nextInt(height - 3);
        int widthRandom2 = -(1 + RANDOM.nextInt(width - 3));
        int heightRandom2 = -(1 + RANDOM.nextInt(height - 3));
        Position[] pArray = new Position[4];

        pArray[0] = new Position(p.x + widthRandom1, p.y);
        pArray[1] = new Position(p.x, p.y + heightRandom1);
        pArray[2] = new Position(p.x + width - 1 + widthRandom2, p.y + height - 1);
        pArray[3] = new Position(p.x + width - 1, p.y + height - 1 + heightRandom2);
        return pArray;
    }

    public void randomRemoveWalls(Random RANDOM, TETile[][] world) {
        Position[] randomPositions = randomRoomPositions(RANDOM);
        for (int i = 0; i < 4; i++) {
            int whichEdge = RANDOM.nextInt(4);
            if (!isOnEdge(randomPositions[whichEdge], world) && !isInDeadEnd(randomPositions[whichEdge], world)) {
                world[randomPositions[whichEdge].x][randomPositions[whichEdge].y] = Tileset.FLOOR;
            }
        }
    }


}