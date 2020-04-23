
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Dormitory {
    public static Scanner input = new Scanner(System.in);
    public static ArrayList<Manager> Managers = new ArrayList<Manager>();

    public static void main(String[] args) {
        show_menu();
    }
    
    public static void show_menu(){
        clear_screen();
        int ch = 1;
        while (ch != 0){
            System.out.println("\033[1;36m" + "[Dormitory Software]");
            System.out.println("1:Login\n2:Signup\n3:Manager List\n0:Exit");
            ch = input.nextInt();
            
            if (ch == 1){
                Login();
            }
            else if (ch == 2){
                Signup();
            }
            else if (ch == 3){
                Manager_list();
            }
            else{
                System.out.println("Bye!");
            }
        }
    }
    
    public static void Login(){
        clear_screen();
        System.out.println("[Login page]");
        System.out.print("Username : ");
        String username = input.next();
        System.out.print("Password : ");
        String password = input.next();

        for (int i = 0; i<Managers.size(); i++){
            if (username.equals(Managers.get(i).username)){
                if (password.equals(Managers.get(i).password)){
                    MENU(Managers.get(i));
                    break;
                }
            }
        }
    }
    
    public static void Signup(){
        clear_screen();
        System.out.println("[Signup page]");
        System.out.print("Name : ");
        String name = input.next();
        System.out.print("Last Name : ");
        String last_name = input.next();
        System.out.print("User Name : ");
        String username = input.next();
        System.out.print("Password : ");
        String password = input.next();

        if (validation_signup(username)){
            Manager manager = new Manager(name, last_name, username, password);
            Managers.add(manager);
            MENU(manager);
        }else{
            System.out.println("Username Duplicated.");
        }
    }

    public static boolean validation_signup(String username){
        boolean flag = true;
        for (int i = 0; i<Managers.size(); i++){
            if (username.equals(Managers.get(i).username)){
                flag = false;
                break;
            }
        }
        return flag;
    }
    
    public static void MENU(Manager manager){
        
        int ch = 1;
        while (ch != 0){
            
            // Show MENU
            show_MENU(manager);
            ch = input.nextInt();
            
            // 01:Add new Student
            if (ch == 1){
                System.out.print("Name : ");
                String name = input.next();
                System.out.print("Last Name : ");
                String last_name = input.next();
                System.out.print("Student ID : ");
                String student_id = input.next();
                System.out.print("Field : ");
                String field = input.next();
                System.out.print("Entry Year : ");
                int entry_year = input.nextInt();
                manager.add_student(name, last_name, student_id, field, entry_year);
            }

            // 02:Delete Student
            else if (ch == 2){
                System.out.print("Enter Student ID : ");
                String student_id = input.next();
                manager.del_student(student_id);
            }

            // 03:Update Student
            else if (ch == 3){
                System.out.println("Enter Student ID for Update : ");
                String student_id = input.next();
                System.out.print("Change Name to : ");
                String name = input.next();
                System.out.print("Change Last Name to : ");
                String last_name = input.next();
                System.out.print("Change Field to : ");
                String field = input.next();
                System.out.print("Change Entry Year to : ");
                int entry_year = input.nextInt();
                manager.update_student(name, last_name, student_id, field, entry_year);
            }

            // 04:Search Students
            else if (ch == 4){
                System.out.println("[Search]");
                System.out.println("Entrer Studenr_ID for select room : ");
                String student_id = input.next();
                manager.search_student(student_id);
            }

            // 05:Get Student Roomate
            else if (ch == 5){
                manager.get_student_roomate();
            }

            // 06:Add New Dorm
            else if (ch == 6){
                System.out.print("Name : ");
                String name = input.next();
                System.out.print("Kind : ");
                String kind = input.next();
                manager.add_new_dorm(name, kind, manager);
            }

            // 07:Add New Block
            else if (ch == 7){
                manager.add_new_block();
            }

            // 08:Add new Room
            else if (ch == 8){
                manager.add_new_room();
            }

            
            // 09:Get Room Members
            else if (ch == 9){
                manager.get_room_members();
            }

            // 10:Delete All Students From Room
            else if (ch == 10){
                manager.del_all_student_from_room();
            }

            // 11:Delete All Empty Room
            else if (ch == 11){
                manager.del_all_empty_room();
            }

            // 12:Show info methods:
            else if (ch == 12){
                manager.show_all_dorms();
            }
            
            // 13:Show All Blocks  
            else if (ch == 13){
                manager.show_all_blocks();
            }
            
            // 14:Show All Rooms 
            else if (ch == 14){
                manager.show_all_rooms();
            }

            
            // 15:Show All Students 
            else if (ch == 15){
                manager.show_all_students();
            }
        } 
    }

    public static void show_MENU(Manager manager) {
        System.out.println("\n" +"Manager user name : "+ manager.username + "\n\t[MENU]");
        for (int i = 0; i<50; i++){
            System.out.print("-");
        }
        System.out.println();

        System.out.println("01:Add new Student");
        System.out.println("02:Delete Student");
        System.out.println("03:Update Student");
        System.out.println("04:Search Students");
        System.out.println("05:Get Student Roomate");
        System.out.println("06:Add New Dorm");
        System.out.println("07:Add New Block");
        System.out.println("08:Add new Room");
        System.out.println("09:Get Room Members");
        System.out.println("10:Delete All Students From Room");
        System.out.println("11:Delete All Empty Room"); 
        System.out.println("12:Show All Dorms"); 
        System.out.println("13:Show All Blocks"); 
        System.out.println("14:Show All Rooms"); 
        System.out.println("15:Show All Students"); 
        System.out.println("0:Exit");
    }

    public static void clear_screen() {
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }catch(IOException|InterruptedException e){
            System.out.println(e);
        }
    }

    public static void Manager_list(){
        System.out.println("---------------------------------------\n[Managers]\n");
        for (int i = 0; i<Managers.size(); i++){
            System.out.println(Managers.get(i).username);
        }
        System.out.println("---------------------------------------");
    }
}
