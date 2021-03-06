package stopmotioneditor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
/**
 * Bahadır
 */
public class User implements Comparable {

    private String username;
    private ArrayList <Project> projects;
    private BufferedImage avatar;
    static ArrayList<User> users = Database.getAllUsers();

    public User(String username){

        this.username = username;
        this.projects = new ArrayList<>();
        this.avatar = Database.getRandomAvatar();
    }


    public void addProject(Project project){

        projects.add(project);
    }


    public Project deliverProject(int projectNumber){

        return projects.get(projectNumber-1);
    }

    public String getUsername(){

        return username;
    }
    
    public BufferedImage getAvatar() {
        return this.avatar;
    }
    
    public void setAvatar (BufferedImage avatar) {
        this.avatar = avatar;
    }
    public ArrayList<Project> getProjects(){

        return projects;
    }
    public void removeProject(int removeIndex){
        
        projects.remove(projects.get(removeIndex));
    }
    
    // this method exists to check if the two instance of two User class is same or not, returns -1 if the given object is not an Users
    public int compareTo( Object o ){
        User user;
        if ( o instanceof User){
            user = (User)o;
            
            if ( this.username.equals(user.username)){
                return 0;
            }
        }
        return -1;
    }
}