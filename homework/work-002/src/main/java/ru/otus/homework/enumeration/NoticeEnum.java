package ru.otus.homework.enumeration;

public enum NoticeEnum {
    I_DONT_HAVE_QUESTIONS("Sorry. I don't have questions"),
    QUIZ_CSV_FILE_NOT_FOUND("Sorry. The quiz CSV file is not found"),
    QUIZ_CSV_FILE_READING_ERROR("Sorry. Quiz CSV file reading error"),
    QUIZ_CSV_FILE_PREPARATION_ERROR("Sorry. Quiz CSV file preparation error"),
    QUIZ_CSV_FILE_HAS_EMPTY_LINES_ERROR("Sorry. Quiz CSV file has empty lines"),
    UNHANDLED_EXCEPTION("Sorry. Something went wrong");

    private final String notice;

    private NoticeEnum(String notice) {
        this.notice = notice;
    }

    public String getNotice() {
        return this.notice;
    }
}