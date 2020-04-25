package org.dfarras.simulation.core.start;

import org.dfarras.simulation.core.PlaceCapacityTracker;
import org.dfarras.simulation.elements.Person;
import org.dfarras.simulation.elements.places.Place;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class PersonFactory implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private DailyScheduleFactory dailyScheduleFactory;
    private PlaceCapacityTracker placeCapacityTracker;

    @Autowired
    public PersonFactory(DailyScheduleFactory dailyScheduleFactory, PlaceCapacityTracker placeCapacityTracker) {
        this.dailyScheduleFactory = dailyScheduleFactory;
        this.placeCapacityTracker = placeCapacityTracker;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Person getNewPerson(boolean infected, LocalTime when) {
        Person person = new Person();
        person.setInfected(infected);
        person.setDailySchedule(dailyScheduleFactory.getDailySchedule());
        placeCapacityTracker.updatePersonLocation(person, person.whereIAm(when), when);
        return person;
    }
}
