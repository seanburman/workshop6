package model;

public class TripType {
    private Character tripTypeId;
    private String tripTypeName;

    public TripType(Character tripTypeId, String tripTypeName) {
        this.tripTypeId = tripTypeId;
        this.tripTypeName = tripTypeName;
    }

    public Character getTripTypeId() {
        return tripTypeId;
    }

    public void setTripTypeId(Character tripTypeId) {
        this.tripTypeId = tripTypeId;
    }

    public String getTripTypeName() {
        return tripTypeName;
    }

    public void setTripTypeName(String tripTypeName) {
        this.tripTypeName = tripTypeName;
    }
}
