package in.askdial.askdial.values;

/**
 * Created by Admin on 26-Dec-16.
 */

public class POJOValue {


    String First_Level_Category_Name, First_Level_Category_Id, Company_name, Company_area, Category_mobile1, Company_email;

    boolean LoginSuccess;
    boolean LoginFailure;
    boolean MessageSuccess;
    boolean MessageNoSuccess;
    boolean Category_found;


    boolean MobileNoExist;
    boolean MobileExist;
    boolean CompanyArea;
    boolean CompanyAreaExists;
    boolean CompanyEmailExist;
    boolean CompanyEmailNoExist;


    public void setFirst_Level_Category_Name(String first_Level_Category_Name) {
        First_Level_Category_Name = first_Level_Category_Name;
    }

    public void setFirst_Level_Category_Id(String first_Level_Category_Id) {
        First_Level_Category_Id = first_Level_Category_Id;
    }

    public POJOValue() {

    }

    public POJOValue(String first_Level_Category_Name, String first_Level_Category_Id) {
        First_Level_Category_Name = first_Level_Category_Name;
        First_Level_Category_Id = first_Level_Category_Id;

    }

    public String getFirst_Level_Category_Name() {
        return First_Level_Category_Name;
    }

    public String getFirst_Level_Category_Id() {
        return First_Level_Category_Id;
    }

    public boolean isMessageSuccess() {
        return MessageSuccess;
    }

    public boolean isMobileNoExist() {
        return MobileNoExist;
    }

    public boolean isMobileExist() {
        return MobileExist;
    }

    public void setCategory_found(boolean category_found) {
        Category_found = category_found;

    }

    public boolean isCompanyArea() {
        return CompanyArea;
    }

    public boolean isCompanyEmailExist() {
        return CompanyEmailExist;
    }

    public boolean isCompanyAreaExists() {
        return CompanyAreaExists;
    }

    public boolean isCompanyEmailNoExist() {
        return CompanyEmailNoExist;
    }

    public void setMessageSuccess(boolean messageSuccess) {
        MessageSuccess = messageSuccess;
    }

    public void setMobileNoExist(boolean mobileNoExist) {
        MobileNoExist = mobileNoExist;
    }

    public void setMobileExist(boolean mobileExist) {
        MobileExist = mobileExist;
    }

    public void setCompanyArea(boolean companyArea) {
        CompanyArea = companyArea;
    }

    public void setCompanyAreaExists(boolean companyAreaExists) {
        CompanyAreaExists = companyAreaExists;
    }

    public void setCompanyEmailExist(boolean companyEmailExist) {
        CompanyEmailExist = companyEmailExist;
    }

    public void setCompanyEmailNoExist(boolean companyEmailNoExist) {
        CompanyEmailNoExist = companyEmailNoExist;
    }

    public POJOValue(String company_name, String company_area, String category_mobile1, String company_email) {
        Company_name = company_name;
        Company_area = company_area;
        Category_mobile1 = category_mobile1;
        Company_email = company_email;
    }

    public String getCompany_name() {
        return Company_name;
    }

    public String getCompany_area() {
        return Company_area;
    }

    public String getCategory_mobile1() {
        return Category_mobile1;
    }

    public String getCompany_email() {
        return Company_email;
    }

    public void setCompany_name(String company_name) {
        Company_name = company_name;
    }

    public void setCompany_area(String company_area) {
        Company_area = company_area;
    }

    public void setCategory_mobile1(String category_mobile1) {
        Category_mobile1 = category_mobile1;
    }

    public void setCompany_email(String company_email) {
        Company_email = company_email;
    }

    public void setMessageNoSuccess(boolean messageNoSuccess) {
        MessageNoSuccess = messageNoSuccess;
    }

    public boolean isMessageNoSuccess() {
        return MessageNoSuccess;
    }

}
