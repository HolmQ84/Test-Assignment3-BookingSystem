package dto.employee;

public class EmployeeCreation {
    public final String firstname, lastname;

    public String getFirstname(){
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }

    public EmployeeCreation(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }


}
