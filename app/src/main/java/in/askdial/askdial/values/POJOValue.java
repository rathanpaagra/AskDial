package in.askdial.askdial.values;

/**
 * Created by Admin on 26-Dec-16.
 */

public class POJOValue {


    String First_Level_Category_Name;
    String First_Level_Category_Id;
    String Company_name;
    String Company_category_name;

    //For Classifieds
    String Classifieds_Category_Name;
    String Classifieds_Category_ID;
    String Classifieds_id;
    String Classifieds_name;
    String Classifieds_area;
    String Classifieds_description;
    String Classified_image;
    String Classifieds_contact_person_mobile;
    String Classifieds_contact_person_Name;
    String Classifieds_image;
    String Classified_Contact_person_email;
    String Classifieds_amount;


    //For Events
    String events_message;
    String events_image;
    String events_id;
    String events_name;
    String events_start_date;
    String events_description;
    String events_end_date;
    String events_location;
    String added_on;


    private boolean Classified_ListingbyIdRecivedSuccess;
    private boolean Classified_ListingbyIdRecivedFailure;

    //Category Details POJO

    public boolean isClassified_ListingbyIdRecivedSuccess() {
        return Classified_ListingbyIdRecivedSuccess;
    }

    public void setClassified_ListingbyIdRecivedSuccess(boolean classified_ListingbyIdRecivedSuccess) {
        Classified_ListingbyIdRecivedSuccess = classified_ListingbyIdRecivedSuccess;
    }

    public boolean isClassified_ListingbyIdRecivedFailure() {
        return Classified_ListingbyIdRecivedFailure;
    }

    public void setClassified_ListingbyIdRecivedFailure(boolean classified_ListingbyIdRecivedFailure) {
        Classified_ListingbyIdRecivedFailure = classified_ListingbyIdRecivedFailure;
    }

    String company_city;
    String company_contact_person;
    String company_mobile2;
    String company_pincode;
    String company_address;
    String company_website;
    String company_landline;
    String company_fax;
    String company_toll_free;

    int categoryimages;
    String categorynames;

    String City_Id, City_Name, Area_Id, Area_Name;
    public String getCity_Id() {
        return City_Id;
    }

    public void setCity_Id(String city_Id) {
        City_Id = city_Id;
    }

    public String getCity_Name() {
        return City_Name;
    }

    public void setCity_Name(String city_Name) {
        City_Name = city_Name;
    }

    public String getArea_Id() {
        return Area_Id;
    }

    public void setArea_Id(String area_Id) {
        Area_Id = area_Id;
    }

    public String getArea_Name() {
        return Area_Name;
    }

    public void setArea_Name(String area_Name) {
        Area_Name = area_Name;
    }

    public int getCategoryimages() {
        return categoryimages;
    }

    public void setCategoryimages(int categoryimages) {
        this.categoryimages = categoryimages;
    }

    public String getCategorynames() {
        return categorynames;
    }

    public void setCategorynames(String categorynames) {
        this.categorynames = categorynames;
    }

    public POJOValue(int categoryimages, String categorynames) {

        this.categoryimages = categoryimages;
        this.categorynames = categorynames;
    }

    private boolean ListingbyIdRecivedSuccess;
    private boolean SearchKeywordSuccess;
    private boolean SearchKeyWordFailure;
    private boolean SEARCHCITY_Success;
    private boolean SEARCHArea_Success;

    private boolean CategoryAutosuggestList_Success;

    public boolean isCategoryAutosuggestList_Success() {
        return CategoryAutosuggestList_Success;
    }

    public void setCategoryAutosuggestList_Success(boolean categoryAutosuggestList_Success) {
        CategoryAutosuggestList_Success = categoryAutosuggestList_Success;
    }

    public boolean isCategoryAutosuggestList_Failure() {
        return CategoryAutosuggestList_Failure;
    }

    public void setCategoryAutosuggestList_Failure(boolean categoryAutosuggestList_Failure) {
        CategoryAutosuggestList_Failure = categoryAutosuggestList_Failure;
    }

    private boolean CategoryAutosuggestList_Failure;
    public boolean isSEARCHArea_Success() {
        return SEARCHArea_Success;
    }

    public void setSEARCHArea_Success(boolean SEARCHArea_Success) {
        this.SEARCHArea_Success = SEARCHArea_Success;
    }

    public boolean isSEARCHArea_Failure() {
        return SEARCHArea_Failure;
    }

    public void setSEARCHArea_Failure(boolean SEARCHArea_Failure) {
        this.SEARCHArea_Failure = SEARCHArea_Failure;
    }

    private boolean SEARCHArea_Failure;

    public boolean isSEARCHCITY_Success() {
        return SEARCHCITY_Success;
    }

    public void setSEARCHCITY_Success(boolean SEARCHCITY_Success) {
        this.SEARCHCITY_Success = SEARCHCITY_Success;
    }

    public boolean isSEARCHCITY_Failure() {
        return SEARCHCITY_Failure;
    }

    public void setSEARCHCITY_Failure(boolean SEARCHCITY_Failure) {
        this.SEARCHCITY_Failure = SEARCHCITY_Failure;
    }

    private boolean SEARCHCITY_Failure;

    public boolean isSearchKeywordSuccess() {
        return SearchKeywordSuccess;
    }

    public void setSearchKeywordSuccess(boolean searchKeywordSuccess) {
        SearchKeywordSuccess = searchKeywordSuccess;
    }

    public boolean isSearchKeyWordFailure() {
        return SearchKeyWordFailure;
    }

    public void setSearchKeyWordFailure(boolean searchKeyWordFailure) {
        SearchKeyWordFailure = searchKeyWordFailure;
    }

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

    public String getClassifieds_Category_Name() {
        return Classifieds_Category_Name;
    }

    public void setClassifieds_Category_Name(String classifieds_Category_Name) {
        Classifieds_Category_Name = classifieds_Category_Name;
    }

    public String getClassifieds_Category_ID() {
        return Classifieds_Category_ID;
    }

    public void setClassifieds_Category_ID(String classifieds_Category_ID) {
        Classifieds_Category_ID = classifieds_Category_ID;
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

    /*public POJOValue(String first_Level_Category_Name, String first_Level_Category_Id) {
        First_Level_Category_Name = first_Level_Category_Name;
        First_Level_Category_Id = first_Level_Category_Id;

    }*/

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

    ///////////////////////////////Classified Details//////////////////////////

    public String getClassifieds_id() {
        return Classifieds_id;
    }

    public void setClassifieds_id(String classifieds_id) {
        Classifieds_id = classifieds_id;
    }


    public String getClassifieds_name() {
        return Classifieds_name;
    }

    public void setClassifieds_name(String classifieds_name) {
        Classifieds_name = classifieds_name;
    }

    public String getClassifieds_area() {
        return Classifieds_area;
    }

    public void setClassifieds_area(String classifieds_area) {
        Classifieds_area = classifieds_area;
    }

    public String getClassifieds_description() {
        return Classifieds_description;
    }

    public void setClassifieds_description(String classifieds_description) {
        Classifieds_description = classifieds_description;
    }

    public String getClassifieds_contact_person_mobile() {
        return Classifieds_contact_person_mobile;
    }

    public void setClassifieds_contact_person_mobile(String classifieds_contact_person_mobile) {
        Classifieds_contact_person_mobile = classifieds_contact_person_mobile;
    }

    public String getClassifieds_image() {
        return Classifieds_image;
    }

    public void setClassifieds_image(String classifieds_image) {
        Classifieds_image = classifieds_image;
    }

    public String getClassified_Contact_person_email() {
        return Classified_Contact_person_email;
    }

    public void setClassified_Contact_person_email(String classified_Contact_person_email) {
        Classified_Contact_person_email = classified_Contact_person_email;
    }

    public String getClassifieds_amount() {
        return Classifieds_amount;
    }

    public void setClassifieds_amount(String classifieds_amount) {
        Classifieds_amount = classifieds_amount;
    }

    public String getClassifieds_contact_person_Name() {
        return Classifieds_contact_person_Name;
    }

    public void setClassifieds_contact_person_Name(String classifieds_contact_person_Name) {
        Classifieds_contact_person_Name = classifieds_contact_person_Name;
    }

    public String getClassified_image() {
        return Classified_image;
    }

    public void setClassified_image(String classified_image) {
        Classified_image = classified_image;

    }


   ///////////////////////////////Events Details//////////////////////////
    public String getEvents_message() {
        return events_message;
    }

    public void setEvents_message(String events_message) {
        this.events_message = events_message;
    }

    public String getEvents_image() {
        return events_image;
    }

    public void setEvents_image(String events_image) {
        this.events_image = events_image;
    }

    public String getEvents_id() {
        return events_id;
    }

    public void setEvents_id(String events_id) {
        this.events_id = events_id;
    }

    public String getEvents_name() {
        return events_name;
    }

    public void setEvents_name(String events_name) {
        this.events_name = events_name;
    }

    public String getEvents_start_date() {
        return events_start_date;
    }

    public void setEvents_start_date(String events_start_date) {
        this.events_start_date = events_start_date;
    }

    public String getEvents_description() {
        return events_description;
    }

    public void setEvents_description(String events_description) {
        this.events_description = events_description;
    }

    public String getEvents_end_date() {
        return events_end_date;
    }

    public void setEvents_end_date(String events_end_date) {
        this.events_end_date = events_end_date;
    }

    public String getEvents_location() {
        return events_location;
    }

    public void setEvents_location(String events_location) {
        this.events_location = events_location;
    }

    public String getAdded_on() {
        return added_on;
    }

    public void setAdded_on(String added_on) {
        this.added_on = added_on;
    }
    public boolean isMessageNoSuccess() {
        return MessageNoSuccess;
    }


}
