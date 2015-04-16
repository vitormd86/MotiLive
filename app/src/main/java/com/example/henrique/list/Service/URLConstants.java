package com.example.henrique.list.Service;

public class URLConstants {
    // SERVER
    public static final String JSON_SERVER_URL = "http://192.169.198.138:8080//moti-json";

    // CUSTOMER SERVICE
    public static final String CUSTOMER_FIND = "/customer/find?id={id}";
    public static final String CUSTOMER_FIND_ALL = "/customer/findAll";
    public static final String CUSTOMER_SAVE = "/customer/save";

    // DAILY SCHEDULE SERVICE
    public static final String DAILY_SCHEDULE_FIND_ALL_BY_PROFESSIONAL_ID = "/dailySchedule/findAllByProfessionalId?professionalId={professionalId}";
    public static final String DAILY_SCHEDULE_SAVE = "/dailySchedule/save";
    public static final String DAILY_SCHEDULE_SAVE_ALL = "/dailySchedule/saveAll";

    // LOGIN SERVICE
    public static final String LOGIN = "/login/user?login={login}&password={password}";
    public static final String LOGIN_WITH_FACEBOOK = "/login/facebook?facebookLogin={facebookLogin}";
    public static final String LOGIN_VERIFY_EXISTING_USER = "/login/verifyExistingUser?login={login}";

    // PROFESSIONAL SERVICE
    public static final String PROFESSIONAL_FIND = "/professional/find?id={id}";
    public static final String PROFESSIONAL_FIND_ALL = "/professional/findAll";
    public static final String PROFESSIONAL_SAVE = "/professional/save";

    // SCHEDULING SERVICE
    public static final String SCHEDULING_FIND_ALL_BY_CUSTOMER_ID = "/scheduling/findAllByCustomerId?customerId={customerId}";
    public static final String SCHEDULING_FIND_ALL_BY_PROFESSIONAL_ID = "/scheduling/findAllByProfessionalId?professionalId={professionalId}";
    public static final String SCHEDULING_SAVE = "/scheduling/save";

    // SERVICE SERVICE
    public static final String SERVICE_FIND_ALL_BY_PROFESSIONAL_ID = "/service/findAllByProfessionalId?professionalId={professionalId}";
    public static final String SERVICE_SAVE = "/service/save";
}
