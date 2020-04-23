public class Student {
    String name;
    String last_name;
    String student_id;
    String field;
    int entry_year;
    Room room;
    int debit;
    
    public Student(String name, String last_name, String student_id, String field, int entry_year){
        this.name = name;
        this.last_name = last_name;
        this.student_id = student_id;
        this.field = field;
        this.entry_year = entry_year;
        this.debit = 0;
    }
    
    public void update(String name, String last_name, String student_id, String field, int entry_year){
        this.name = name;
        this.last_name = last_name;
        this.student_id = student_id;
        this.field = field;
        this.entry_year = entry_year;
    }
    
    public void print() {
        System.out.println("name : " + this.name + ", last name : " + this.last_name + ", id : " + this.student_id + ", debit : " + this.debit);
        try {
            System.out.println("room id : " + this.room.room_id + ", block : " + this.room.block.block_id + ", dorm name : " + this.room.block.dorm.name);
        } catch (Exception e) {
            System.out.println("Student havent any room");
        }
    }   

    public void set_room(Room room){
        this.room = room;
        this.debit -= room.rental_cost; 
        this.room.room_capacity -= 1;
    }

    public void delete_room(Room room){
        this.room = null;
        this.debit += room.rental_cost; 
        this.room.room_capacity += 1;
    }
}
