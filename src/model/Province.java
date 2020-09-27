package model;

public class Province {
    private String provinceName;
    private String provinceAbbreviation;

    public Province(String provinceName, String provinceAbbreviation) {
        this.provinceName = provinceName;
        this.provinceAbbreviation = provinceAbbreviation;
    }

    public String getProvinceAbbreviation() {
        return provinceAbbreviation;
    }

    public void setProvinceAbbreviation(String provinceAbbreviation) {
        this.provinceAbbreviation = provinceAbbreviation;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
//might not need this
}
