package stopmotioneditor;

import java.util.ArrayList;
/**
 * Bahadır
 */
public class User {

    String userName;
    String password;
    ArrayList <Project> projects;
    static ArrayList<User> users = new ArrayList<>();

    public User(String userName, String password){

        this.userName = userName;
        this.password = password;
        projects = new ArrayList<>();
        users.add(this);
    }


    public void addProject(Project project){

        projects.add(project);
    }


    public Project deliverProject(int projectNumber){

        return projects.get(projectNumber-1);
    }


    public final void changePassword(String newPassword){

        password = newPassword;
    }
    public String getUserName(){

        return userName;
    }
    public String getPassword(){

        return password;
    }
    public ArrayList<Project> getProjects(){

        return projects;
    }
    public void removeProject(int removeIndex){
        
        projects.remove(projects.get(removeIndex));
    }
}