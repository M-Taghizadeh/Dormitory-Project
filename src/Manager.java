
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    // object variables
    String name;
    String last_name;    
    String username;
    String password;

    // class variables
    public static Scanner input = new Scanner(System.in);
    static ArrayList<Dorm> dorms = new ArrayList<Dorm>(); // main array list
    static ArrayList<Student> students = new ArrayList<Student>();
    
    // Constructor
    public Manager(String name, String last_name, String username, String password){
        this.name = name;
        this.last_name = last_name;        
        this.username = username;
        this.password = password;
    }

    // Methods:
    // 01:Add new Student
    public void add_student(String name, String last_name, String student_id, String field, int entry_year){
        Student student = new Student(name, last_name, student_id, field, entry_year);
        Manager.students.add(student);
    }
    
    // 02:Delete Student
    public void del_student(String student_id){
        for(int i = 0; i<Manager.students.size(); i++){
            if (students.get(i).student_id.equals(student_id)){
                students.remove(i);
            }
        }
    }

    // 03:Update Student
    public void update_student(String name, String last_name, String student_id, String field, int entry_year){
        for (int i = 0; i<students.size(); i++){
            if (students.get(i).student_id.equals(student_id)){
                students.get(i).update(name, last_name, student_id, field, entry_year);
                System.out.println("Change was successfuly.");
                break;
            }
        }
    }
    
    // 04:Search Students
    public void search_student(String student_id){

        // search student:
        for(int s = 0; s<students.size(); s++){
            if(students.get(s).student_id.equals(student_id)){

                if(dorms.size()>0){
                    System.out.println("Select Dorm name : ");
                    for (int i = 0; i<dorms.size(); i++){
                        System.out.println(dorms.get(i).name);
                    }
                    String dorm_name = input.next();
                    Dorm dorm = null;
                    for (int i = 0; i<dorms.size(); i++){
                        if (dorms.get(i).name.equals(dorm_name)){
                            dorm = dorms.get(i);
                            break;
                        }
                    }
        
                    if(dorm.blocks.size()>0){
                        System.out.println("Select Block ID : ");
                        for (int i = 0; i<dorm.blocks.size(); i++){
                            System.out.println(dorm.blocks.get(i).block_id);
                        }
                        int block_id = input.nextInt();
                        Block block = null;
                        for (int i = 0; i<dorm.blocks.size(); i++){
                            if (dorm.blocks.get(i).block_id == block_id){
                                block = dorm.blocks.get(i);
                                break;
                            }
                        }
        
                        if(block.rooms.size()>0){
                            System.out.println("Select Room ID : ");
                            for (int i = 0; i<block.rooms.size(); i++){
                                System.out.println(block.rooms.get(i).room_id);
                            }
                            int room_id = input.nextInt();
                            Room room = null;
                            for (int i = 0; i<block.rooms.size(); i++){
                                if (block.rooms.get(i).room_id == room_id){
                                    room = block.rooms.get(i);
                                    students.get(s).room = room;
                                    room.add_member(students.get(s));
                                    break;
                                }
                            }
                        }
                        else{
                            System.out.println("No room are registered in this block.");
                        }
                    }
                    else{
                        System.out.println("No block are registered in this dorm.");
                    }
        
                }else{
                    System.out.println("No dormitories are registered.");
                }

                break;
            }
        }
    }
    
    // 05:Get Student Roomate
    public void get_student_roomate(){
        System.out.println("[Get Student Roomate]");
        System.out.println("Entrer Studenr_ID : ");
        String student_id = input.next();
        for(int s = 0; s<students.size(); s++){
            if(students.get(s).student_id.equals(student_id)){
                if(students.get(s).room != null){
                    Room room = students.get(s).room;
                    ArrayList <Student> members = room.get_members();
                    for (int i = 0; i<members.size(); i++){
                        members.get(i).print();
                    }
                }
            }
        }
    }

    // 06:Add New Dorm
    public void add_new_dorm(String name, String kind, Manager manager){
        dorms.add(new Dorm(name, kind, manager));
    }
    
    // 07:Add New Block
    public void add_new_block(){
        System.out.print("Enter Block ID : ");
        int block_id = input.nextInt();
        System.out.print("Enter Number of floors : ");
        int number_of_floors = input.nextInt();
    
        // select dorm
        if(dorms.size()>0){
            System.out.println("Select Dorm name for this block : ");
            for (int i = 0; i<dorms.size(); i++){
                System.out.println(dorms.get(i).name);
            }
            String dorm_name = input.next();
            Dorm dorm = null;
            for (int i = 0; i<dorms.size(); i++){
                if (dorms.get(i).name.equals(dorm_name)){
                    dorm = dorms.get(i);
                    Block block = new Block(block_id, number_of_floors);
                    dorm.blocks.add(block);
                    block.set_dorm(dorm);
                    break;
                }
            }
        }else{
            System.out.println("No dormitories are registered.");
        }
    }
    
    // 08:Add new Room
    public void add_new_room(){
        System.out.print("Enter Room ID : ");
        int room_id = input.nextInt();
        System.out.print("Enter Room Capacity : ");
        int room_capacity = input.nextInt();
        System.out.print("Enter Rental Cost : ");
        int rental_cost = input.nextInt();
        
        // select dorm
        if(dorms.size()>0){
            System.out.println("Select Dorm name : ");
            for (int i = 0; i<dorms.size(); i++){
                System.out.println(dorms.get(i).name);
            }
            String dorm_name = input.next();
            Dorm dorm = null;
            for (int i = 0; i<dorms.size(); i++){
                if (dorms.get(i).name.equals(dorm_name)){
                    dorm = dorms.get(i);
                    break;
                }
            }

            if(dorm.blocks.size()>0){
                System.out.println("Select Block ID : ");
                for (int i = 0; i<dorm.blocks.size(); i++){
                    System.out.println(dorm.blocks.get(i).block_id);
                }
                int block_id = input.nextInt();
                Block block = null;
                for (int i = 0; i<dorm.blocks.size(); i++){
                    if (dorm.blocks.get(i).block_id == block_id){
                        block = dorm.blocks.get(i);
                        Room room = new Room(room_id, room_capacity, rental_cost);
                        block.rooms.add(room);
                        room.set_block(block);
                        room.set_manager(this);
                        break;
                    }
                }
            } else{
                System.out.println("No block are registered in this dorm.");
            }
        }else{
            System.out.println("No dormitories are registered.");
        }
    }

    // 09:Get Room Members
    public void get_room_members(){

        Room room = null;
        int find = 0;

        if(dorms.size()>0){
            System.out.println("Select Dorm name : ");
            for (int i = 0; i<dorms.size(); i++){
                System.out.println(dorms.get(i).name);
            }
            String dorm_name = input.next();
            Dorm dorm = null;
            for (int i = 0; i<dorms.size(); i++){
                if (dorms.get(i).name.equals(dorm_name)){
                    dorm = dorms.get(i);
                    break;
                }
            }

            if(dorm.blocks.size()>0){
                System.out.println("Select Block ID : ");
                for (int i = 0; i<dorm.blocks.size(); i++){
                    System.out.println(dorm.blocks.get(i).block_id);
                }
                int block_id = input.nextInt();
                Block block = null;
                for (int i = 0; i<dorm.blocks.size(); i++){
                    if (dorm.blocks.get(i).block_id == block_id){
                        block = dorm.blocks.get(i);
                        break;
                    }
                }

                if(block.rooms.size()>0){
                    System.out.println("Select Room ID : ");
                    for (int i = 0; i<block.rooms.size(); i++){
                        System.out.println(block.rooms.get(i).room_id);
                    }
                    int room_id = input.nextInt();
                    for (int i = 0; i<block.rooms.size(); i++){
                        if (block.rooms.get(i).room_id == room_id){
                            room = block.rooms.get(i);
                            find = 1;
                            break;
                        }
                    }
                }
                else{
                    System.out.println("No room are registered in this block.");
                }
            }
            else{
                System.out.println("No block are registered in this dorm.");
            }

        }else{
            System.out.println("No dormitories are registered.");
        }


        // print room members 
        if (find == 1){
            System.out.println("Room Members : ");
            for(int i = 0; i<room.members.size(); i++){
                room.members.get(i).print();
            }
        }
    }

    // 10:Delete All Students From Room
    public void del_all_student_from_room(){

        Dorm dorm = null;
        Block block = null;
        Room room = null;
        int find = 0;

        if(dorms.size()>0){
            System.out.println("Select Dorm name : ");
            for (int i = 0; i<dorms.size(); i++){
                System.out.println(dorms.get(i).name);
            }
            String dorm_name = input.next();
            for (int i = 0; i<dorms.size(); i++){
                if (dorms.get(i).name.equals(dorm_name)){
                    dorm = dorms.get(i);
                    break;
                }
            }

            if(dorm.blocks.size()>0){
                System.out.println("Select Block ID : ");
                for (int i = 0; i<dorm.blocks.size(); i++){
                    System.out.println(dorm.blocks.get(i).block_id);
                }
                int block_id = input.nextInt();
                for (int i = 0; i<dorm.blocks.size(); i++){
                    if (dorm.blocks.get(i).block_id == block_id){
                        block = dorm.blocks.get(i);
                        break;
                    }
                }

                if(block.rooms.size()>0){
                    System.out.println("Select Room ID : ");
                    for (int i = 0; i<block.rooms.size(); i++){
                        System.out.println(block.rooms.get(i).room_id);
                    }
                    
                    int room_id = input.nextInt();
                    for (int i = 0; i<block.rooms.size(); i++){
                        if (block.rooms.get(i).room_id == room_id){
                            room = block.rooms.get(i);
                            find = 1;
                            break;
                        }
                    }
                }
                else{
                    System.out.println("No room are registered in this block.");
                }
            }
            else{
                System.out.println("No block are registered in this dorm.");
            }

        }else{
            System.out.println("No dormitories are registered.");
        }

        // delete * member from room
        if(find == 1){
            // update student 
            for (int i = 0; i < room.members.size(); i++){
                room.members.get(i).delete_room(room);
            }   
            // delete room
            room.members = new ArrayList<Student>();
        }
    }

    // 11:Delete All Empty Room 
    public void del_all_empty_room(){
        System.out.println("---------------------------------------\n[Deleted All empty rooms]\n");
        for(int i = 0; i<dorms.size(); i++){
            for(int j = 0; j<dorms.get(i).blocks.size(); j++){
                for (int z =0; z<dorms.get(i).blocks.get(j).rooms.size(); z++){
                    if (dorms.get(i).blocks.get(j).rooms.get(z).members.size() == 0){
                        Room deleted_room = dorms.get(i).blocks.get(j).rooms.get(z);
                        dorms.get(i).blocks.get(j).rooms.remove(deleted_room);
                    }
                }
            }   
        }
        System.out.println("---------------------------------------");
        
    }
    
    // 12:Show info methods:
    public void show_all_dorms(){
        System.out.println("---------------------------------------\n[Dorms]\n");
        for(int i = 0; i<dorms.size(); i++){
            dorms.get(i).print();
        }
        System.out.println("---------------------------------------");
    }
    
    // 13:Show All Blocks 
    public void show_all_blocks(){
        System.out.println("---------------------------------------\n[Dorms]\n");
        for(int i = 0; i<dorms.size(); i++){
            System.out.println("Dorm name : " + dorms.get(i).name);
            for(int j = 0; j<dorms.get(i).blocks.size(); j++){
                dorms.get(i).blocks.get(j).print();
            }   
        }
        System.out.println("---------------------------------------");
    }

    // 14:Show All Rooms 
    public void show_all_rooms(){
        System.out.println("---------------------------------------\n[Dorms]\n");
        for(int i = 0; i<dorms.size(); i++){
            System.out.println("Dorm name : " + dorms.get(i).name);
            for(int j = 0; j<dorms.get(i).blocks.size(); j++){
                for (int z =0; z<dorms.get(i).blocks.get(j).rooms.size(); z++){
                    dorms.get(i).blocks.get(j).rooms.get(z).print();
                }
            }   
        }
        System.out.println("---------------------------------------");
    }

    // 15:Show All Students 
    public void show_all_students(){
        System.out.println("---------------------------------------\n[Students]\n");
        for(int i = 0; i < Manager.students.size(); i++){
            students.get(i).print();
        }
        System.out.println("---------------------------------------");
    }
    
}
