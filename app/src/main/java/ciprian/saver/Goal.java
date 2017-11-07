package ciprian.saver;

/**
 * Created by Ciprian on 07-11-2017.
 * Ciprian - Goal Main Class
 * Attributes: id, GoalName, Description, Sum;
 * Constructor: empty, all parameter, three parameter
 * Getter & Setters: all attributes
 */
public class Goal {

    //Private Variable
    private int _id;
    private String _goal_name;
    private String _description;
    private int _sum;

    //empty constructor
    public Goal() {
    }

    //all parameter in Constructor
    public Goal(int _id, String _description, String _goal_name, int _sum) {
        this._id = _id;
        this._description = _description;
        this._goal_name = _goal_name;
        this._sum = _sum;
    }

    //three parameter Constructor
    public Goal(String _goal_name, String _description, int _sum) {
        this._goal_name = _goal_name;
        this._description = _description;
        this._sum = _sum;
    }


    //Getters for  all fields


    public int get_id() {
        return _id;
    }

    public String get_description() {
        return _description;
    }

    public String get_goal_name() {
        return _goal_name;
    }

    public int get_sum() {
        return _sum;
    }



    //Setters for all fields
    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_goal_name(String _goal_name) {
        this._goal_name = _goal_name;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public void set_sum(int _sum) {
        this._sum = _sum;
    }




}
