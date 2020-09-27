package model;


import java.sql.Date;

public class TravelPackage {
    private int packageId;
    private String pkgName;
    private Date pkgStartDate;
    private Date pkgEndDate;
    private String pkgDesc;
    private Double pkgBasePrice;
    private Double pkgAgencyCommission;

    public TravelPackage(int packageId, String pkgName, Date pkgStartDate, Date pkgEndDate, String pkgDesc, Double pkgBasePrice, Double pkgAgencyCommission) {
        this.packageId = packageId;
        this.pkgName = pkgName;
        this.pkgStartDate = pkgStartDate;
        this.pkgEndDate = pkgEndDate;
        this.pkgDesc = pkgDesc;
        this.pkgBasePrice = pkgBasePrice;
        this.pkgAgencyCommission = pkgAgencyCommission;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public Date getPkgStartDate() {
        return pkgStartDate;
    }

    public void setPkgStartDate(Date pkgStartDate) {
        this.pkgStartDate = pkgStartDate;
    }

    public Date getPkgEndDate() {
        return pkgEndDate;
    }

    public void setPkgEndDate(Date pkgEndDate) {
        this.pkgEndDate = pkgEndDate;
    }

    public String getPkgDesc() {
        return pkgDesc;
    }

    public void setPkgDesc(String pkgDesc) {
        this.pkgDesc = pkgDesc;
    }

    public Double getPkgBasePrice() {
        return pkgBasePrice;
    }

    public void setPkgBasePrice(Double pkgBasePrice) {
        this.pkgBasePrice = pkgBasePrice;
    }

    public Double getPkgAgencyCommission() {
        return pkgAgencyCommission;
    }

    public void setPkgAgencyCommission(Double pkgAgencyCommission) {
        this.pkgAgencyCommission = pkgAgencyCommission;
    }

    @Override
    public String toString() {
        return pkgName;
    }
}
