package com.rambosoftware.movieapp.loaders;

import com.rambosoftware.movieapp.models.Movie;
import com.rambosoftware.movieapp.models.MovieDetails;
import com.rambosoftware.movieapp.repositories.MovieRepository;
import com.rambosoftware.movieapp.services.MovieService;
import com.rambosoftware.movieapp.services.springdatajpa.JpaMovieService;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;


public class SqlDataLoad {


    public static void main(String... args) {

    }
}





