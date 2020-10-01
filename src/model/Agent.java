package model;

public class Agent {
    //create the private attributes
    private int AgentId;
    private String FirstName;
    private String LastName;
    private String MiddleInitial;
    private String BusinessPhone;
    private String Email;
    private String Position;
    private int AgencyId;

    public Agent(int agentId, String firstName, String middleInitial, String lastName,  String businessPhone, String email, String position, int agencyId) {
        AgentId = agentId;
        FirstName = firstName;
        LastName = lastName;
        MiddleInitial = middleInitial;
        BusinessPhone = businessPhone;
        Email = email;
        Position = position;
        AgencyId = agencyId;
    }

    public int getAgentId() {
        return AgentId;
    }

    public void setAgentId(int agentId) {
        AgentId = agentId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMiddleInitial() {
        return MiddleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        MiddleInitial = middleInitial;
    }

    public String getBusinessPhone() {
        return BusinessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        BusinessPhone = businessPhone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public int getAgencyId() {
        return AgencyId;
    }

    public void setAgencyId(int agencyId) {
        AgencyId = agencyId;
    }

}
