
import java.util.ArrayList;

public class Block {
    int block_id;
    int number_of_floors;
    ArrayList<Room> rooms = new ArrayList<Room>();
    Dorm dorm;
    
    public Block(int block_id, int number_of_floors){
        this.block_id = block_id;
        this.number_of_floors = number_of_floors;
    }
    
    public void add_room(Room room){
        this.rooms.add(room);
        room.set_block(this);
    }
    
    public ArrayList<Room> get_rooms(Room room){
        return this.rooms;
    }

    public void print(){
        System.out.println("block id : " + this.block_id + ", number_of_floors : " + this.number_of_floors + ", dorm name : " + this.dorm.name);
    }

    public void set_dorm(Dorm dorm) {
        this.dorm = dorm;
    }
}
