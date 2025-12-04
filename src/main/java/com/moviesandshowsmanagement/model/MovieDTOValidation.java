package com.moviesandshowsmanagement.model;

public class MovieDTOValidation {
    public static void validate(MovieDTO mdto){

        String title = mdto.getTitle();
        validateTitle(title);

        String language = mdto.getLanguage();
        validateLanguage(language);

        Integer duration_min = mdto.getDuration_min();
        validateDurationMin(duration_min);

        String certification = mdto.getCertification();
        validateCertification(certification);

        String status = mdto.getStatus();
        validateStatus(status);

        String created_at = mdto.getCreated_at();
        validateCreatedAt(created_at);
    }
    //    public static void validateId(Integer id){
//        if(id <= 0)
//            throw new RuntimeException("Id must be greater a positive number");
//    }
    public static void  validateTitle(String title){
        if(title == null || title.trim().isEmpty())
            throw new RuntimeException("Title cannot be empty");
        if(title.length() < 3 || title.length() > 50)
            throw new RuntimeException("Title must be between 3 and 50 characters");
    }
    public static void validateLanguage(String language){
        if(language == null || language.trim().isEmpty())
            throw new RuntimeException("Language cannot be empty");
        if(language.length() < 3 || language.length() > 50)
            throw new RuntimeException("Language must be between 3 and 50 characters");
    }
    public static void validateDurationMin(Integer duration_min){
        if(duration_min <= 0 || duration_min > 240 )
            throw new RuntimeException("Duration must be greater than 0 and less than 240");
    }
    public static void  validateCertification(String certification){
        if(certification == null || certification.trim().isEmpty())
            throw  new RuntimeException("Certification cannot be empty");
        if(certification.length() < 1 || certification.length() > 10)
            throw new RuntimeException("Certification must be between 3 and 10 characters");
    }
    public static void validateStatus(String status){
        if(status == null || status.trim().isEmpty())
            throw new RuntimeException("Status cannot be empty");
        if(status.length() < 3 || status.length() > 50)
            throw new RuntimeException("Status must be between 3 and 50 characters");
    }
    public static void  validateCreatedAt(String created_at){
        if(created_at == null || created_at.isEmpty())
            throw new RuntimeException("Date cannot be empty");
    }


}
