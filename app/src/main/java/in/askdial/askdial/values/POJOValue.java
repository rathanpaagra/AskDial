package in.askdial.askdial.values;

/**
 * Created by Admin on 26-Dec-16.
 */

public class POJOValue {


    String First_Level_Category_Name;
    String First_Level_Category_Id;
    String Company_name;
    String Company_category_name;

    String company_city;
    String company_contact_person;
    String company_mobile2;
    String company_pincode;
    String company_address;
    String company_website;
    String company_landline;
    String company_fax;
    String company_toll_free;

    private boolean ListingbyIdRecivedSuccess;

    public boolean isListingbyIdRecivedSuccess() {
        return ListingbyIdRecivedSuccess;
    }

    public void setListingbyIdRecivedSuccess(boolean listingbyIdRecivedSuccess) {
        ListingbyIdRecivedSuccess = listingbyIdRecivedSuccess;
    }

    public boolean isListingbyIdRecivedFailure() {
        return ListingbyIdRecivedFailure;
    }

    public void setListingbyIdRecivedFailure(boolean listingbyIdRecivedFailure) {
        ListingbyIdRecivedFailure = listingbyIdRecivedFailure;
    }

    private boolean ListingbyIdRecivedFailure;

    public String getCompany_city() {
        return company_city;
    }

    public void setCompany_city(String company_city) {
        this.company_city = company_city;
    }

    public String getCompany_contact_person() {
        return company_contact_person;
    }

    public void setCompany_contact_person(String company_contact_person) {
        this.company_contact_person = company_contact_person;
    }

    public String getCompany_mobile2() {
        return company_mobile2;
    }

    public void setCompany_mobile2(String company_mobile2) {
        this.company_mobile2 = company_mobile2;
    }

    public String getCompany_pincode() {
        return company_pincode;
    }

    public void setCompany_pincode(String company_pincode) {
        this.company_pincode = company_pincode;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getCompany_website() {
        return company_website;
    }

    public void setCompany_website(String company_website) {
        this.company_website = company_website;
    }

    public String getCompany_landline() {
        return company_landline;
    }

    public void setCompany_landline(String company_landline) {
        this.company_landline = company_landline;
    }

    public String getCompany_fax() {
        return company_fax;
    }

    public void setCompany_fax(String company_fax) {
        this.company_fax = company_fax;
    }

    public String getCompany_toll_free() {
        return company_toll_free;
    }

    public void setCompany_toll_free(String company_toll_free) {
        this.company_toll_free = company_toll_free;
    }

    public String getCompany_landmark() {
        return company_landmark;
    }

    public void setCompany_landmark(String company_landmark) {
        this.company_landmark = company_landmark;
    }

    String company_landmark;

    public String getCompany_category_name() {
        return Company_category_name;
    }

    public void setCompany_category_name(String company_category_name) {
        Company_category_name = company_category_name;
    }

    String Company_area;
    String Category_mobile1;
    String Company_email;
    String Company_lisiting_id;

    public String getCompany_lisiting_id() {
        return Company_lisiting_id;
    }

    public void setCompany_lisiting_id(String company_lisiting_id) {
        Company_lisiting_id = company_lisiting_id;
    }

    public String getCompany_mobile1() {
        return Company_mobile1;
    }

    public void setCompany_mobile1(String company_mobile1) {
        Company_mobile1 = company_mobile1;
    }

    String Company_mobile1;

    public boolean isSearchExists() {
        return SearchExists;
    }

    public void setSearchExists(boolean searchExists) {
        SearchExists = searchExists;
    }

    public boolean isNoSearchfExist() {
        return NoSearchfExist;
    }

    public void setNoSearchfExist(boolean noSearchfExist) {
        NoSearchfExist = noSearchfExist;
    }

    public boolean isLoginSuccess() {
        return LoginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        LoginSuccess = loginSuccess;
    }

    public boolean isLoginFailure() {
        return LoginFailure;
    }

    public void setLoginFailure(boolean loginFailure) {
        LoginFailure = loginFailure;
    }

    public boolean isCategory_found() {
        return Category_found;
    }

    private boolean SearchExists;
    private boolean NoSearchfExist;

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
