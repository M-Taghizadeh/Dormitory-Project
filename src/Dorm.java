
import java.util.ArrayList;

public class Dorm {
    String name;
    String kind;
    int number_of_block;
    Manager manager;
    int number_of_member;
    ArrayList<Block> blocks = new ArrayList<Block>();
    
    public Dorm(String name, String kind, Manager manager){
        this.name = name;
        this.kind = kind;
        this.manager = manager;
    }
    
    public void add_block(Block block){
        this.blocks.add(block);
        block.set_dorm(this);
    }
    
    public ArrayList<Block> get_blocks(){
        return this.blocks;
    }

    public void print() {
        System.out.println("name : " + this.name + ", kind : " + this.kind + ", manager : " + this.manager.username);
        try {
            System.out.println("number of block : " + this.number_of_block);
        } catch (Exception e) {
            System.out.println("Dorm havent any Block");
        }
    }   
    
}
