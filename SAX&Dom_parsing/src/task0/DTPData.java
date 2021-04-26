package task0;

public class DTPData {
    private String regName;
    private String district;
    private String COORD_L;
    private String COORD_W;


    public DTPData(String regName, String district, String COORD_L, String COORD_W) {
        this.regName = regName;
        this.district = district;
        this.COORD_L = COORD_L;
        this.COORD_W = COORD_W;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCOORD_L() {
        return COORD_L;
    }

    public void setCOORD_L(String COORD_L) {
        this.COORD_L = COORD_L;
    }

    public String getCOORD_W() {
        return COORD_W;
    }

    public void setCOORD_W(String COORD_W) {
        this.COORD_W = COORD_W;
    }
    @Override
    public String toString() {
        return "DTPData{" +
                "regName='" + regName + '\'' +
                ", district='" + district + '\'' +
                ", COORD_L='" + COORD_L + '\'' +
                ", COORD_W='" + COORD_W + '\'' +
                '}';
    }
}
