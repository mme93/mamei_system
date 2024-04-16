package com.dashboard.database.constant;

import java.util.List;

import static java.util.Arrays.asList;

public class ComponentName {

    public final static String CHECK_LIST = "CHECK_LIST";
    public final static String CHECK_LIST_WITH_COUNT = "CHECK_LIST_WITH_COUNT";
    public final static String INPUT_FIELD_STRING = "INPUT_FIELD_STRING";
    public final static String INPUT_FIELD_NUMBER = "INPUT_FIELD_NUMBER";
    public final static String INPUT_FIELD_ANY = "INPUT_FIELD_ANY";
    public final static String INPUT_BOX_STRING = "INPUT_BOX_STRING";
    public final static String LABEL = "LABEL";
    public final static String TITLE = "TITLE";
    public final static String CHECK_BOX = "CHECK_BOX";
    public final static String CHOICE_BOX = "CHOICE_BOX";
    public final static String SEPARATOR = "SEPARATOR";
    public final static String IMAGE = "IMAGE";
    public final static String FILES = "FILES";

    public final static List<String> componentNameList = asList(CHECK_LIST, CHECK_LIST_WITH_COUNT, INPUT_FIELD_STRING,
            INPUT_FIELD_NUMBER, INPUT_FIELD_ANY, INPUT_BOX_STRING, LABEL, TITLE, CHECK_BOX, CHOICE_BOX, SEPARATOR, IMAGE,
            FILES);
}
