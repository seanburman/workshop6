package model;

public class TripType {
    private String tripTypeId;
    private String tripTypeName;

    public TripType(String tripTypeId, String tripTypeName) {
        this.tripTypeId = tripTypeId;
        this.tripTypeName = tripTypeName;
    }

    public String getTripTypeId() {
        return tripTypeId;
    }

    public void setTripTypeId(String tripTypeId) {
        this.tripTypeId = tripTypeId;
    }

    public String getTripTypeName() {
        return tripTypeName;
    }

    public void setTripTypeName(String tripTypeName) {
        this.tripTypeName = tripTypeName;
    }

    @Override
    public String toString() {
        return tripTypeName;
    }
}
