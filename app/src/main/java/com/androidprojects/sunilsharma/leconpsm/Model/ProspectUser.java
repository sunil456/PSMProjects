package com.androidprojects.sunilsharma.leconpsm.Model;

/**
 * Created by sunil sharma on 12/25/2017.
 */

public class ProspectUser
{
    private String $id;


    private int ID;
    private String CNAME;
    private String COMPANYNAME;
    private String Add1;
    private String Add2;
    private String HeadOffice;
    private String PhoneNo;
    private String AlternateNo;
    private String Mobile;
    private String Email;
    private String Website;
    private String Category;
    private String DOB;
    private String DOA;
    private String Remark;
    private int CUSTOMERTYPE;
    private String STYPE;
    private int AREA;
    private int City;
    private int CState;
    private int Country;
    private int User;
    private String LandMark;
    private String Type;


    public ProspectUser()
    {

    }

    /** we use This Constructor for showing data in cardview OR prospect_customer_entry_layout */
    public ProspectUser(String $id , String CNAME, String COMPANYNAME, String add1,
                        String mobile, String email, int AREA, String landMark, String type) {
        this.$id = $id;
        this.CNAME = CNAME;
        this.COMPANYNAME = COMPANYNAME;
        Add1 = add1;
        Mobile = mobile;
        Email = email;
        this.AREA = AREA;
        LandMark = landMark;
        Type = type;
    }


    public ProspectUser(String $id, int ID, String CNAME, String COMPANYNAME, String add1,
                        String add2, String headOffice, String phoneNo,
                        String alternateNo, String mobile, String email,
                        String website, String category, String DOB, String DOA,
                        String remark, int CUSTOMERTYPE, String STYPE, int AREA,
                        int city, int CState, int country, int user, String landMark, String type) {
        this.$id = $id;
        this.ID = ID;
        this.CNAME = CNAME;
        this.COMPANYNAME = COMPANYNAME;
        Add1 = add1;
        Add2 = add2;
        HeadOffice = headOffice;
        PhoneNo = phoneNo;
        AlternateNo = alternateNo;
        Mobile = mobile;
        Email = email;
        Website = website;
        Category = category;
        this.DOB = DOB;
        this.DOA = DOA;
        Remark = remark;
        this.CUSTOMERTYPE = CUSTOMERTYPE;
        this.STYPE = STYPE;
        this.AREA = AREA;
        City = city;
        this.CState = CState;
        Country = country;
        User = user;
        LandMark = landMark;
        Type = type;
    }


    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCNAME() {
        return CNAME;
    }

    public void setCNAME(String CNAME) {
        this.CNAME = CNAME;
    }

    public String getCOMPANYNAME() {
        return COMPANYNAME;
    }

    public void setCOMPANYNAME(String COMPANYNAME) {
        this.COMPANYNAME = COMPANYNAME;
    }

    public String getAdd1() {
        return Add1;
    }

    public void setAdd1(String add1) {
        Add1 = add1;
    }

    public String getAdd2() {
        return Add2;
    }

    public void setAdd2(String add2) {
        Add2 = add2;
    }

    public String getHeadOffice() {
        return HeadOffice;
    }

    public void setHeadOffice(String headOffice) {
        HeadOffice = headOffice;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getAlternateNo() {
        return AlternateNo;
    }

    public void setAlternateNo(String alternateNo) {
        AlternateNo = alternateNo;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getDOA() {
        return DOA;
    }

    public void setDOA(String DOA) {
        this.DOA = DOA;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public int getCUSTOMERTYPE() {
        return CUSTOMERTYPE;
    }

    public void setCUSTOMERTYPE(int CUSTOMERTYPE) {
        this.CUSTOMERTYPE = CUSTOMERTYPE;
    }

    public String getSTYPE() {
        return STYPE;
    }

    public void setSTYPE(String STYPE) {
        this.STYPE = STYPE;
    }

    public int getAREA() {
        return AREA;
    }

    public void setAREA(int AREA) {
        this.AREA = AREA;
    }

    public int getCity() {
        return City;
    }

    public void setCity(int city) {
        City = city;
    }

    public int getCState() {
        return CState;
    }

    public void setCState(int CState) {
        this.CState = CState;
    }

    public int getCountry() {
        return Country;
    }

    public void setCountry(int country) {
        Country = country;
    }

    public int getUser() {
        return User;
    }

    public void setUser(int user) {
        User = user;
    }

    public String getLandMark() {
        return LandMark;
    }

    public void setLandMark(String landMark) {
        LandMark = landMark;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
