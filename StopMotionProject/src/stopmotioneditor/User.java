package stopmotioneditor;

import java.util.ArrayList;
/**
 * Bahadır
 */
public class User {

    private String username;
    private ArrayList <Project> projects;

    public User(String userName){

        this.username = username;
        projects = new ArrayList<>();
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
    public ArrayList<Project> getProjects(){

        return projects;
    }
    public void removeProject(int removeIndex){
        
        projects.remove(projects.get(removeIndex));
    }
}