package com.trdictionaryapp;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.Scanner;
import io.restassured.path.json.JsonPath;

public class App 
{
    public static void main( String[] args )
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Lütfen aratmak istediğiniz sözcüğü girin");
        String word = scan.next();
        System.out.println("Aranıyor...");
        Get(word);
        scan.close();


    }

    public static void  Get(String word){
        Response response = given().when().get("https://api.dictionaryapi.dev/api/v2/entries/tr/"+word);
        JsonPath json = response.jsonPath();
        System.out.println();
        
        try {
            json.getString("[0].origin").equals(null);
            System.out.println("Kelime kökü : "+json.getString("[0].origin"));
            System.out.println();
        } catch (Exception e) {
            System.out.println("Kelime kökü : Bilinmiyor");
            System.out.println();
        }
        System.out.println("Anlamı      : "+json.getString("[0].meanings[0].definitions[0].definition"));


        try {
            json.getString("[0].meanings[0].definitions[1].definition").equals(null);
            System.out.println("Anlamı      : "+json.getString("[0].meanings[0].definitions[1].definition"));
            System.out.println("Anlamı      : "+json.getString("[0].meanings[0].definitions[2].definition"));
        } catch (Exception e) {
            
        }

    }
}
