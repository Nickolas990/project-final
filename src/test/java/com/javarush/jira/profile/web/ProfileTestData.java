package com.javarush.jira.profile.web;

import com.javarush.jira.MatcherFactory;
import com.javarush.jira.common.util.JsonUtil;
import com.javarush.jira.profile.ContactTo;
import com.javarush.jira.profile.ProfileTo;
import com.javarush.jira.profile.internal.Contact;
import com.javarush.jira.profile.internal.Profile;

import java.util.Map;
import java.util.Set;

public class ProfileTestData {
    public static final long PROFILE_ID = 2;
    public static final long USER_ID = 1;
    public static final String ADMIN_MAIL = "admin@gmail.com";
    public static final String USER_MAIL = "user@gmail.com";

    public static final MatcherFactory.Matcher<Profile> PROFILE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(
            Profile.class);
    public static final MatcherFactory.Matcher<ProfileTo> TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(ProfileTo.class, "contacts", "mailNotifications");
    public static final Profile profile = new Profile(PROFILE_ID);

    public static final  Set<ContactTo> contacts =Set.of(new ContactTo("github", "adminGitHub"), new ContactTo("tg", "adminTg"), new ContactTo("mobile", "+79111111111"));
    public static final  Set<String> mailNotifications = Set.of("three_days_before_deadline", "two_days_before_deadline", "one_day_before_deadline", "deadline");
    public static Profile getExpected() {
        Profile expected = new Profile(PROFILE_ID);
        expected.setId(2L);
        expected.setMailNotifications(30);
        expected.setContacts(Set.of(new Contact(PROFILE_ID, "github", "adminGitHub"),
                new Contact(PROFILE_ID, "tg", "adminTg"),
                new Contact(PROFILE_ID, "mobile", "+79111111111")));
        return expected;
    }
    public static <T> String jsonWithContactsAndMailNotifications(T profile, Set<ContactTo> contacts,
                                                                  Set<String> mailNotifications) {
        return JsonUtil.writeSeveralAdditionalProps(profile, Map.of("contacts", contacts, "mailNotifications", mailNotifications));
    }
}
