package com.rambosoftware.movieapp.loaders;

import com.sun.xml.bind.v2.runtime.output.SAXOutput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SqlDataLoad {

    public static void main(String [] args){

        System.out.println("Im alive !!");

        String csvFile = "src/main/resources/data/movie.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",\"";

        int count = 0;

        try {

         br = new BufferedReader(new FileReader(csvFile));

            br.readLine();
            String prefix = "https://image.tmdb.org/t/p/w600_and_h900_bestv2/";
            String movieName = "Buried Alive II";
            String url = null;
            Long year = 0L;

            while ((line = br.readLine()) != null) {
               // line = br.readLine();
               String[] csvLine = line.split(cvsSplitBy);

                Long id = Long.parseLong(csvLine[0]);

                String title = csvLine[1].substring(0, csvLine[1].length() - 7).trim();
                try {
                    year = Long.parseLong(csvLine[1].substring(csvLine[1].length() - 6, csvLine[1].length() - 2).trim());
                }catch (Exception e){
                    year = 0L;
                    count++;
                }

                String[] category = csvLine[2].split("[|]");
                String readyCategory = "";
                for(String s : category){
                    readyCategory = readyCategory + " " + s;
                }
                readyCategory = readyCategory.replace("\"", "").trim();

                System.out.println(id);
                System.out.println(title);
                System.out.println(readyCategory);
                System.out.println(year);

            }
            System.out.println(count + "@@@@@@@");

//
//                line = br.readLine();
//                String[] test = line.split(cvsSplitBy);
//                    for(int o = 0; o < test.length; o++){
//                    System.out.println(o + "\t" + test[o]);
//                }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    }

