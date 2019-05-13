package com.wildcodeschool.onTheRoad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Controller
@SpringBootApplication
public class OnTheRoadApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnTheRoadApplication.class, args);
    }

    @RequestMapping("/doctor/{number}")
    @ResponseBody
    public Doctor chosenDoctor(@PathVariable int number, @RequestParam(required = false) boolean details) {

        Doctor chris = new Doctor("Christopher Eccleston", 9);
        Doctor dave = new Doctor("David Tennant", 10);
        Doctor matt = new Doctor("Matt Smith", 11);
        Doctor pete = new Doctor("Peter Capaldi", 12);
        Doctor jodie = new Doctor("Jodie Whittaker", 13);

        List<Doctor> doctors = new ArrayList<>();

        doctors.add(chris);
        doctors.add(dave);
        doctors.add(matt);
        doctors.add(pete);
        doctors.add(jodie);

        ExtendedDoctor chris1 = new ExtendedDoctor("Christopher Eccleston", 9, 13, 41);
        ExtendedDoctor dave1 = new ExtendedDoctor("David Tennant", 10, 47, 34);
        ExtendedDoctor matt1 = new ExtendedDoctor("Matt Smith", 11, 44, 27);
        ExtendedDoctor pete1 = new ExtendedDoctor("Peter Capaldi", 12, 40, 55);
        ExtendedDoctor jodie1 = new ExtendedDoctor("Jodie Whittaker", 13, 11, 35);

        List<ExtendedDoctor> extendedDoctors = new ArrayList<>();

        extendedDoctors.add(chris1);
        extendedDoctors.add(dave1);
        extendedDoctors.add(matt1);
        extendedDoctors.add(pete1);

        if (number < 9) {
            throw new ResponseStatusException(HttpStatus.SEE_OTHER);
        }

        if (number > 13) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Impossible de récupérer l'incarnation " + number);
        }

        if (details) {
            return extendedDoctors.get(number - 9);
        }
        return doctors.get(number - 9);
    }

    class Doctor {

        private String name;
        private int number;

        public Doctor(String name, int number) {
            this.name = name;
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public int getNumber() {
            return number;
        }
    }

    class ExtendedDoctor extends Doctor {

        private int numberOfEpisodes;
        private int ageAtStart;

        public ExtendedDoctor(String name, int number, int numberOfEpisodes, int ageAtStart) {
            super(name, number);
            this.numberOfEpisodes = numberOfEpisodes;
            this.ageAtStart = ageAtStart;
        }

        public int getNumberOfEpisodes() {
            return numberOfEpisodes;
        }

        public int getAgeAtStart() {
            return ageAtStart;
        }
    }
}

