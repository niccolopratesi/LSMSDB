package it.unipi.CardsGallery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    //Repository necessari

    @GetMapping
    public String getStatistics() {
        //????? bisogna capire come restituire la roba in json

        //non credo convenga fare 1 richiesta http per ogni statistica xD

        return null;
    }

}
