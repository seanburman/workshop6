package model;

import java.util.Date;

public class Booking {
    private int bookingId;
    private Date bookingDate;
    private String bookingNo;
    private int travelerCount;
    private int customerId;
    private Character tripTypeId;
    private int packageId;

    public Booking(int bookingId, Date bookingDate, String bookingNo, int travelerCount, int customerId, Character tripTypeId, int packageId) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.bookingNo = bookingNo;
        this.travelerCount = travelerCount;
        this.customerId = customerId;
        this.tripTypeId = tripTypeId;
        this.packageId = packageId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.bookingNo = bookingNo;
    }

    public int getTravelerCount() {
        return travelerCount;
    }

    public void setTravelerCount(int travelerCount) {
        this.travelerCount = travelerCount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Character getTripTypeId() {
        return tripTypeId;
    }

    public void setTripTypeId(Character tripTypeId) {
        this.tripTypeId = tripTypeId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

}
