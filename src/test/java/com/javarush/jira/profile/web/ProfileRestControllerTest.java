package com.javarush.jira.profile.web;

import com.javarush.jira.AbstractControllerTest;
import com.javarush.jira.common.util.JsonUtil;
import com.javarush.jira.profile.ContactTo;
import com.javarush.jira.profile.ProfileTo;
import com.javarush.jira.profile.internal.Contact;
import com.javarush.jira.profile.internal.Profile;
import com.javarush.jira.profile.internal.ProfileMapper;
import com.javarush.jira.profile.internal.ProfileRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Set;

import static com.javarush.jira.profile.web.ProfileRestController.REST_URL;
import static com.javarush.jira.profile.web.ProfileTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


class ProfileRestControllerTest extends AbstractControllerTest {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileMapper profileMapper;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getCurrentUser() throws Exception {

        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TO_MATCHER.contentJson(profileMapper.toTo(profile)));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        Profile expected = getExpected();
        Set<ContactTo> contacts =Set.of(new ContactTo("github", "adminGitHub"), new ContactTo("tg", "adminTg"), new ContactTo("mobile", "+79111111111"));
        Set<String> mailNotifications = Set.of("three_days_before_deadline", "two_days_before_deadline", "one_day_before_deadline", "deadline");

        Profile updated = profileRepository.getExisted(PROFILE_ID);
        ProfileTo updateTo = profileMapper.toTo(updated);

        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWithContactsAndMailNotifications(updateTo, contacts, mailNotifications)))
                .andDo(print())
                .andExpect(status().isNoContent());


        PROFILE_MATCHER.assertMatch(profileRepository.getExisted(PROFILE_ID), expected);

    }
}
