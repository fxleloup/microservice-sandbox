package net.bakaar.announce.cucumber.stepdefs;

import net.bakaar.announce.AnnounceApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = AnnounceApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
