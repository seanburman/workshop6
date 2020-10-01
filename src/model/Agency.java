package model;

public class Agency {
    private int AgencyId;

    public Agency(int agencyId) {
        AgencyId = agencyId;
    }

    public int getAgencyId() {
        return AgencyId;
    }

    public void setAgencyId(int agencyId) {
        AgencyId = agencyId;
    }
    @Override
    public String toString() {
        return AgencyId + "";
    }
}
