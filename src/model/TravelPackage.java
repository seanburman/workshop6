package model;


import java.sql.Date;

public class TravelPackage {
    private int packageId;
    private Date PkgStartDate;
    private Date PkgEndDate;
    private String PkgDesc;
    private Double PkgBasePrice;
    private Double PkgAgencyCommision;

    public TravelPackage(int packageId, Date pkgStartDate, Date pkgEndDate, String pkgDesc, Double pkgBasePrice, Double pkgAgencyCommision) {
        this.packageId = packageId;
        PkgStartDate = pkgStartDate;
        PkgEndDate = pkgEndDate;
        PkgDesc = pkgDesc;
        PkgBasePrice = pkgBasePrice;
        PkgAgencyCommision = pkgAgencyCommision;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public Date getPkgStartDate() {
        return PkgStartDate;
    }

    public void setPkgStartDate(Date pkgStartDate) {
        PkgStartDate = pkgStartDate;
    }

    public Date getPkgEndDate() {
        return PkgEndDate;
    }

    public void setPkgEndDate(Date pkgEndDate) {
        PkgEndDate = pkgEndDate;
    }

    public String getPkgDesc() {
        return PkgDesc;
    }

    public void setPkgDesc(String pkgDesc) {
        PkgDesc = pkgDesc;
    }

    public Double getPkgBasePrice() {
        return PkgBasePrice;
    }

    public void setPkgBasePrice(Double pkgBasePrice) {
        PkgBasePrice = pkgBasePrice;
    }

    public Double getPkgAgencyCommision() {
        return PkgAgencyCommision;
    }

    public void setPkgAgencyCommision(Double pkgAgencyCommision) {
        PkgAgencyCommision = pkgAgencyCommision;
    }
}
