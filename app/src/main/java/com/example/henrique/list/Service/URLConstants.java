package com.example.henrique.list.Service;

public class URLConstants {

    // LOCAL
    //public static final String JSON_SERVER_URL = "http://192.168.0.13:8081//moti-json";

    // SERVER
    public static final String JSON_SERVER_URL = "http://192.169.198.138:8080//moti-json";

    // CUSTOMER SERVICE
    public static final String CUSTOMER_FIND = JSON_SERVER_URL + "/customer/find?id={id}";
    public static final String CUSTOMER_FIND_ALL = JSON_SERVER_URL + "/customer/findAll";
    public static final String CUSTOMER_FIND_CONTACTS_BY_PROFESSIONAL_ID = JSON_SERVER_URL + "/customer/findCustomerContactsByProfessionalId?professionalId={professionalId}";
    public static final String CUSTOMER_SAVE = JSON_SERVER_URL + "/customer/save";

    // DAILY SCHEDULE SERVICE
    public static final String DAILY_SCHEDULE_FIND_ALL_BY_PROFESSIONAL_ID = JSON_SERVER_URL + "/dailySchedule/findAllByProfessionalId?professionalId={professionalId}";
    public static final String DAILY_SCHEDULE_FIND_FIND_BY_PROFESSIONAL_ID_AND_DATE = JSON_SERVER_URL + "/dailySchedule/findByProfessionalIdAndDate?professionalId={professionalId}&date={date}";
    public static final String DAILY_SCHEDULE_FIND_ALL_BY_PROFESSIONAL_ID_AND_PERIOD = JSON_SERVER_URL + "/dailySchedule/findAllByProfessionalIdAndPeriod?professionalId={professionalId}&initialDate={initialDate}&finalDate={finalDate}";
    public static final String DAILY_SCHEDULE_SAVE = JSON_SERVER_URL + "/dailySchedule/save";
    public static final String DAILY_SCHEDULE_SAVE_ALL = JSON_SERVER_URL + "/dailySchedule/saveAll";

    // LOGIN SERVICE
    public static final String LOGIN = JSON_SERVER_URL + "/login/user?login={login}&password={password}";
    public static final String LOGIN_WITH_FACEBOOK = JSON_SERVER_URL + "/login/facebook?facebookLogin={facebookLogin}";
    public static final String LOGIN_VERIFY_EXISTING_USER = JSON_SERVER_URL + "/login/verifyExistingUser?login={login}";

    // PROFESSIONAL SERVICE
    public static final String PROFESSIONAL_FIND = JSON_SERVER_URL + "/professional/find?id={id}";
    public static final String PROFESSIONAL_FIND_ALL = JSON_SERVER_URL + "/professional/findAll";
    public static final String PROFESSIONAL_FIND_CONTACTS_BY_CUSTOMER_ID = JSON_SERVER_URL + "/professional/findProfessionalContactsByCustomerId?customerId={customerId}";
    public static final String PROFESSIONAL_SAVE = JSON_SERVER_URL + "/professional/save";

    // PROFESSION SERVICE
    public static final String PROFESSION_FIND_ALL = JSON_SERVER_URL + "/profession/findAll";

    // SCHEDULING SERVICE
    public static final String SCHEDULING_FIND_ALL_BY_CUSTOMER_ID = JSON_SERVER_URL + "/scheduling/findAllByCustomerId?customerId={customerId}";
    public static final String SCHEDULING_FIND_ALL_BY_PROFESSIONAL_ID = JSON_SERVER_URL + "/scheduling/findAllByProfessionalId?professionalId={professionalId}";
    public static final String SCHEDULING_FIND_UPCOMING_SCHEDULING_BY_CUSTOMER_ID = JSON_SERVER_URL + "/scheduling/findUpcomingSchedulingByCustomerId?customerId={customerId}";
    public static final String SCHEDULING_SAVE = JSON_SERVER_URL + "/scheduling/save";

    // SERVICE SERVICE
    public static final String SERVICE_FIND_ALL_BY_PROFESSIONAL_ID = JSON_SERVER_URL + "/service/findAllByProfessionalId?professionalId={professionalId}";
    public static final String SERVICE_SAVE = JSON_SERVER_URL + "/service/save";
}
