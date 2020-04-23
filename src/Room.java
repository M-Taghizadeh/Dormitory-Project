
import java.util.ArrayList;

public class Room {
    int room_id;
    int room_capacity;
    ArrayList<Student> members = new ArrayList<Student>();
    int rental_cost;
    Block block;
    Manager manager;
    
    public Room(int room_id, int room_capacity, int rental_cost){
        this.room_id = room_id;
        this.room_capacity = room_capacity;
        this.rental_cost = rental_cost;
    }   
    
    public void add_member(Student student){
        if(this.room_capacity > 0){
            this.members.add(student);
            student.set_room(this);
        }else{
            System.out.println("Room Filled.");
        }
    }
    
    public ArrayList<Student> get_members(){
        return this.members;
    }

    public void print() {
        System.out.println("room_id : " + this.room_id + ", room_capacity : " + this.room_capacity + ", rental_cost : " + this.rental_cost);
        try {
            System.out.println("manager : " + this.manager.username + ", block : " + this.block.block_id + ", dorm name : " + this.block.dorm.name);
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }
    
    public void set_block(Block block){
        this.block = block;
    }
    
    public void set_manager(Manager manager){
        this.manager = manager;
    }
}
