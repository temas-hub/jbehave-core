/*
 * Created on 29-Aug-2004
 * 
 * (c) 2003-2004 ThoughtWorks Ltd
 *
 * See license.txt for license details
 */
package com.thoughtworks.jbehave.extensions.story.base;

import com.thoughtworks.jbehave.extensions.jmock.UsingJMock;
import com.thoughtworks.jbehave.extensions.story.domain.Scenario;
import com.thoughtworks.jbehave.extensions.story.visitor.Visitor;

/**
 * @author <a href="mailto:dan.north@thoughtworks.com">Dan North</a>
 */
public class StoryBaseBehaviour implements UsingJMock {

    public void shouldPassItselfIntoVisitor() throws Exception {
        // setup
        StoryBase story = new StoryBase("role", "feature", "benefit");
        Mock visitor = new Mock(Visitor.class);
        
        // expect
        visitor.expectsOnce("visitStory", story);

        // execute
        story.accept((Visitor) visitor.proxy());
        
        // verify - done by mock
    }
    
    public void shouldTellScenariosToAcceptVisitor() throws Exception {
        // given...
        StoryBase story = new StoryBase("role", "feature", "benefit");
        Mock scenario1 = new Mock(Scenario.class, "scenario1");
        Mock scenario2 = new Mock(Scenario.class, "scenario2");
        Visitor visitor = Visitor.NULL;

        scenario1.expectsOnce("accept", visitor);
        scenario2.expectsOnce("accept", visitor);
        
        // when...
        story.addScenario((Scenario) scenario1.proxy());
        story.addScenario((Scenario) scenario2.proxy());
        story.accept(visitor);
        
        // then... verified by framework
    }
}
